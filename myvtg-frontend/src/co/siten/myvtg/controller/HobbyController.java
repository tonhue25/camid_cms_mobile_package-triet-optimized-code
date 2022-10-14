

package co.siten.myvtg.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.siten.myvtg.bean.MessageBean;
import co.siten.myvtg.exception.MyResourceNotFoundException;
import co.siten.myvtg.model.myvtg.Hobby;
import co.siten.myvtg.model.myvtg.Job;
import co.siten.myvtg.service.IHobbyService;
import co.siten.myvtg.util.MessageUtil;
import co.siten.myvtg.util.CommonUtil;

@RestController
@RequestMapping(value = "/api/${version}")
public class HobbyController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(HobbyController.class);
	@Autowired
	private IHobbyService service;

	@Autowired
	private MessageUtil messageUtil;

	@RequestMapping(value = "/hobbys", method = RequestMethod.GET)
	public Page<Hobby> search(
			@RequestParam(value = "page" , required = false, defaultValue ="0") int page,
			@RequestParam(value = "size", required = false, defaultValue ="5") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "0") int sortType,
			@RequestParam(value = "language", required = false) String language,
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {

		Page<Hobby> resultPage = service.findPaginated(page, size, sortBy, sortType,
				language, name);
		return resultPage;
	}


	@RequestMapping(value = "/hobbys/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageBean> get(@RequestParam(value = "id", required = true) long id) {
		Hobby data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", ""),HttpStatus.OK);
		}
		return new ResponseEntity<MessageBean>(new MessageBean("", "",data), HttpStatus.OK);
	}


	@RequestMapping(value = "/hobbys", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessageBean> create(@Valid @RequestBody Hobby inputData) {
		try {


			List<Hobby> exists = service.findByNameAndLanguage(inputData.getName(),inputData.getLanguage());
			
			if (exists.size() > 0) {
				return new ResponseEntity<MessageBean>(new MessageBean("1", messageUtil.getMessage("hobbys.nameExisted")),HttpStatus.OK);
			}
			
			Hobby data = new Hobby();
			//set info from inputData into data
			data.setName(inputData.getName());
			data.setLanguage(inputData.getLanguage());
			data.setDes(inputData.getDes());
			
			
			service.save(data);

			return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data),
				HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("", e);
			return new ResponseEntity<MessageBean>(new MessageBean("500", e.getMessage()), HttpStatus.OK);
		}


	}


	@RequestMapping(value = "/hobbys", method = RequestMethod.PUT)
	public ResponseEntity<MessageBean> update(@Valid @RequestBody Hobby inputData) {
		long id = inputData.getId();
	try {
		Hobby data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")),HttpStatus.OK);
		}
		
		List<Hobby> exists = service.findByNameAndLanguage(inputData.getName(),inputData.getLanguage());
		
		if (exists.size() > 0) {
			Hobby h = exists.get(0);
			if(h.getId() != inputData.getId()){
				return new ResponseEntity<MessageBean>(new MessageBean("1", messageUtil.getMessage("hobbys.nameExisted")),HttpStatus.OK);
			}
		}
		
		// TODO setAll Information
		data.setName(inputData.getName());
		data.setLanguage(inputData.getLanguage());
		data.setDes(inputData.getDes());

		service.save(data);
		return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("", e);
			return new ResponseEntity<MessageBean>(new MessageBean("500", e.getMessage()), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/hobbys", method = RequestMethod.DELETE)
	public ResponseEntity<MessageBean> delete(@RequestParam(value = "id" , required = true)  int id) {
		Hobby data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")),HttpStatus.OK);
		}
		service.delete(data);
		
		return new ResponseEntity<MessageBean>(HttpStatus.OK);
	}

}
