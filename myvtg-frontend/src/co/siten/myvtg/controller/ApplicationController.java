package co.siten.myvtg.controller;

import co.siten.myvtg.bean.ApplicationSelect2Bean;
import co.siten.myvtg.bean.MessageBean;
import co.siten.myvtg.model.myvtg.Application;
import co.siten.myvtg.service.ApplicationService;
import co.siten.myvtg.util.ApproveWrapper;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.MessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/${version}")
public class ApplicationController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationController.class);
	@Autowired
	private ApplicationService service;

	@Autowired
	private MessageUtil messageUtil;


	@RequestMapping(value = "/applications", method = RequestMethod.GET)
	public Page<Application> search(
			@RequestParam(value = "page" , required = false, defaultValue ="0") int page,
			@RequestParam(value = "size", required = false, defaultValue ="5") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "0") int sortType,
			@RequestParam(value = "language", required = false) String language,

			@RequestParam(value = "name", required = false, defaultValue = "") String name) {

		Page<Application> resultPage = service.findPaginated(page, size, sortBy, sortType,
				language, name);
		return resultPage;
	}


	@RequestMapping(value = "/applications/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageBean> get(@RequestParam(value = "id", required = true) String id) {
		Application data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", ""),HttpStatus.OK);
		}
		return new ResponseEntity<MessageBean>(new MessageBean("", "",data), HttpStatus.OK);
	}


	@RequestMapping(value = "/applications", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessageBean> create(@Valid @RequestBody Application inputData) {
		try {

			Application data = new Application();
			//set info from inputData into data
			data.setName(inputData.getName());
			data.setLanguage(inputData.getLanguage());
			data.setShortDes(inputData.getShortDes());
			data.setFullDes(inputData.getFullDes());
			data.setIconUrl(inputData.getIconUrl());
			data.setIosLink(inputData.getIosLink());
			data.setAndroidLink(inputData.getAndroidLink());
			
			

			//auto set info
			data.setStatus(1);
			data.setApproved(0);
			data.setCreatedBy(getCurrentUserId());
			data.setCreatedTime(CommonUtil.getCurrentTime());

			service.save(data);

			return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data),HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("", e);
			return new ResponseEntity<MessageBean>(new MessageBean("500", e.getMessage()), HttpStatus.OK);
		}


	}


	@RequestMapping(value = "/applications", method = RequestMethod.PUT)
	public ResponseEntity<MessageBean> update(@Valid @RequestBody Application inputData) {
		String id = inputData.getId();
	try {
		Application data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")),HttpStatus.OK);
		}
		// TODO setAll Information
		data.setName(inputData.getName());
		data.setLanguage(inputData.getLanguage());
		data.setShortDes(inputData.getShortDes());
		data.setFullDes(inputData.getFullDes());
		data.setIconUrl(inputData.getIconUrl());
		data.setIosLink(inputData.getIosLink());
		data.setAndroidLink(inputData.getAndroidLink());
		
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

	@RequestMapping(value = "/applications", method = RequestMethod.DELETE)
	public ResponseEntity<MessageBean> delete(@RequestParam(value = "id" , required = true)  String id) {
		Application data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")),HttpStatus.OK);
		}
		data.setStatus(0);
		service.save(data);
		return new ResponseEntity<MessageBean>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/applications/approve", method = RequestMethod.PUT )
	public ResponseEntity<MessageBean> approve(@RequestBody ApproveWrapper approve) {
		
		service.Approve(approve.forAll, approve.ids,approve.active);
		
		return new ResponseEntity<MessageBean>(HttpStatus.OK);
	}

	@RequestMapping(value = "/applications/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageBean> getAll() {
		List<ApplicationSelect2Bean> data = service.getAll();
		return new ResponseEntity<MessageBean>(new MessageBean("", "", data), HttpStatus.OK);
	}
}
