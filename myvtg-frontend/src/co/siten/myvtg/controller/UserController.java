
package co.siten.myvtg.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.siten.myvtg.bean.MessageBean;
import co.siten.myvtg.model.myvtg.User;
import co.siten.myvtg.service.UserService;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.MessageUtil;

@RestController
@RequestMapping(value = "/api/${version}")
public class UserController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService service;

	@Autowired
	private MessageUtil messageUtil;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public Page<User> searchData(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "5") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "fullName") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "0") int sortType,
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {

		Page<User> resultPage = service.findPaginated(page, size, sortBy, sortType, name);
		return resultPage;
	}

	@RequestMapping(value = "/users/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> get(@RequestParam(value = "id", required = true) String id) {
		User data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(data, HttpStatus.OK);
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessageBean> create(@Valid @RequestBody User inputData) {
		try {

			if (service.isExist(inputData)) {
				return new ResponseEntity<MessageBean>(new MessageBean("1", "Username đã tồn tại"),
						HttpStatus.BAD_REQUEST);
			}

			User data = new User();
			// set info from inputData into data
			data.setUsername(inputData.getUsername());

			data.setPassword(inputData.getPassword());
			data.setFullName(inputData.getFullName());
			data.setRole(inputData.getRole());

			// auto set info
			data.setStatus(1);
			data.setCreatedBy(getCurrentUserId());
			data.setCreatedTime(CommonUtil.getCurrentTime());

			service.save(data);

			return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data),
					HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("", e);
			return new ResponseEntity<MessageBean>(new MessageBean("", e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/users", method = RequestMethod.PUT)
	public ResponseEntity<MessageBean> update(@Valid @RequestBody User inputData) {

		String id = inputData.getId();
		try {
			User data = service.findById(id);
			if (data == null) {
				return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage("")),
						HttpStatus.NOT_FOUND);
			}

			User exist = service.findByUserName(inputData.getUsername());
			if (exist != null && exist.getId() != data.getId()) {
				return new ResponseEntity<MessageBean>(
						new MessageBean("username đã tồn tại", messageUtil.getMessage("")), HttpStatus.BAD_REQUEST);
			}

			// TODO setAll Information
			data.setUsername(inputData.getUsername());
			data.setPassword(inputData.getPassword());
			data.setFullName(inputData.getFullName());
			data.setRole(inputData.getRole());

			// auto set info
			data.setLastUpdatedTime(CommonUtil.getCurrentTime());
			data.setLastUpdatedBy(getCurrentUserId());

			service.save(data);
			return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error("", e);
			return new ResponseEntity<MessageBean>(new MessageBean("", e.getMessage()), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/users", method = RequestMethod.DELETE)
	public ResponseEntity<MessageBean> delete(@RequestParam(value = "id", required = true) String id) {
		User data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage("")),
					HttpStatus.NOT_FOUND);
		}
		data.setStatus(0);

		service.save(data);
		return new ResponseEntity<MessageBean>(HttpStatus.OK);
	}

}
