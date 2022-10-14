

package co.siten.myvtg.controller;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import co.siten.myvtg.bean.MessageBean;
import co.siten.myvtg.exception.MyResourceNotFoundException;
import co.siten.myvtg.model.myvtg.ExperienceLink;
import co.siten.myvtg.service.IExperienceLinkService;
import co.siten.myvtg.util.MessageUtil;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.ApproveWrapper;

@RestController
@RequestMapping(value = "/api/${version}")
public class ExperienceLinkController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(ExperienceLinkController.class);
	@Autowired
	private IExperienceLinkService service;

	@Autowired
	private MessageUtil messageUtil;


	@RequestMapping(value = "/experiencelinks", method = RequestMethod.GET)
	public Page<ExperienceLink> search(
			@RequestParam(value = "page" , required = false, defaultValue ="0") int page,
			@RequestParam(value = "size", required = false, defaultValue ="5") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "0") int sortType,
			@RequestParam(value = "language", required = false) String language,
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {

		Page<ExperienceLink> resultPage = service.findPaginated(page, size, sortBy, sortType,
				language, name);
		return resultPage;
	}


	@RequestMapping(value = "/experiencelinks/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageBean> get(@RequestParam(value = "id", required = true) String id) {
		ExperienceLink data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", ""), HttpStatus.OK);
		}
		return new ResponseEntity<MessageBean>(new MessageBean("", "",data), HttpStatus.OK);
	}


	@RequestMapping(value = "/experiencelinks", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessageBean> create(@Valid @RequestBody ExperienceLink inputData) {
		try {

			ExperienceLink data = new ExperienceLink();
			//set info from inputData into data
			data.setName(inputData.getName());
			data.setLanguage(inputData.getLanguage());
			data.setShortDes(inputData.getShortDes());
			data.setFullDes(inputData.getFullDes());
			data.setIconUrl(inputData.getIconUrl());
			data.setSourceLink(inputData.getSourceLink());
			

			//auto set info
			data.setApproved(0);
			data.setStatus(1);
			data.setCreatedBy(getCurrentUserId());
			data.setCreatedTime(CommonUtil.getCurrentTime());

			service.save(data);

			return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data),HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("", e);
			return new ResponseEntity<MessageBean>(new MessageBean("500", e.getMessage()), HttpStatus.OK);
		}


	}


	@RequestMapping(value = "/experiencelinks", method = RequestMethod.PUT)
	public ResponseEntity<MessageBean> update(@Valid @RequestBody ExperienceLink inputData) {
		String id = inputData.getId();
	try {
		ExperienceLink data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")),HttpStatus.OK);
		}
		// TODO setAll Information
		data.setName(inputData.getName());
		data.setLanguage(inputData.getLanguage());
		data.setShortDes(inputData.getShortDes());
		data.setFullDes(inputData.getFullDes());
		data.setIconUrl(inputData.getIconUrl());
		data.setSourceLink(inputData.getSourceLink());
		
		//auto set info
		data.setApproved(0);
		data.setLastUpdatedTime(CommonUtil.getCurrentTime());
		data.setLastUpdatedBy(getCurrentUserId());

		service.save(data);
		return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("", e);
			return new ResponseEntity<MessageBean>(new MessageBean("500", e.getMessage()), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/experiencelinks", method = RequestMethod.DELETE)
	public ResponseEntity<MessageBean> delete(@RequestParam(value = "id" , required = true)  String id) {
		ExperienceLink data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")),HttpStatus.OK);
		}
		data.setStatus(0);
		
		service.save(data);
		return new ResponseEntity<MessageBean>(HttpStatus.OK);
	}

	@RequestMapping(value = "/experiencelinks/approve", method = RequestMethod.PUT )
	public ResponseEntity<MessageBean> approve(@RequestBody ApproveWrapper approve) {
		
		service.Approve(approve.forAll, approve.ids,approve.active);
		
		return new ResponseEntity<MessageBean>(HttpStatus.OK);
	}
}
