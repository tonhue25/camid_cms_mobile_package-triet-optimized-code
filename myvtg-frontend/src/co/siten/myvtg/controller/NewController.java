package co.siten.myvtg.controller;

import co.siten.myvtg.bean.MessageBean;
import co.siten.myvtg.bean.NewsSelect2Bean;
import co.siten.myvtg.model.myvtg.New;
import co.siten.myvtg.service.INewService;
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
public class NewController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(NewController.class);
	@Autowired
	private INewService service;

	@Autowired
	private MessageUtil messageUtil;

	@RequestMapping(value = "/news", method = RequestMethod.GET)
	public Page<New> search(
			@RequestParam(value = "page" , required = false, defaultValue ="0") int page,
			@RequestParam(value = "size", required = false, defaultValue ="5") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "0") int sortType,
			@RequestParam(value = "language", required = false) String language,

			@RequestParam(value = "name", required = false, defaultValue = "") String name) {

		Page<New> resultPage = service.findPaginated(page, size, sortBy, sortType,
				language, name);
		return resultPage;
	}


	@RequestMapping(value = "/news/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageBean> get(@RequestParam(value = "id", required = true) String id) {
		New data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", ""),HttpStatus.OK);
		}
		return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data), HttpStatus.OK);
	}


	@RequestMapping(value = "/news", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessageBean> create(@Valid @RequestBody New inputData) {
		try {

//			if(service.isExist(inputData)){
//				return new ResponseEntity<MessageBean>(new MessageBean("1", "New Đã tồn tại"), HttpStatus.OK);
//			}

			New data = new New();
			//set info from inputData into data
			data.setName(inputData.getName());
			data.setSourceLink(inputData.getSourceLink());
			data.setEffectiveTime(inputData.getEffectiveTime());
			data.setExpiredTime(inputData.getExpiredTime());
			data.setPublishedTime(inputData.getPublishedTime());
			data.setLanguage(inputData.getLanguage());
			data.setIconUrl(inputData.getIconUrl());
			data.setImgDesUrl(inputData.getImgDesUrl());
			
			data.setContent(inputData.getContent());
			
			
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


	@RequestMapping(value = "/news", method = RequestMethod.PUT)
	public ResponseEntity<MessageBean> update(@Valid @RequestBody New inputData) {
		String id = inputData.getId();
	try {
		New data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")),HttpStatus.OK);
		}
		
		// TODO setAll Information
		data.setName(inputData.getName());
		data.setSourceLink(inputData.getSourceLink());
		data.setEffectiveTime(inputData.getEffectiveTime());
		data.setExpiredTime(inputData.getExpiredTime());
		data.setPublishedTime(inputData.getPublishedTime());
		data.setLanguage(inputData.getLanguage());
		data.setIconUrl(inputData.getIconUrl());
		data.setImgDesUrl(inputData.getImgDesUrl());
		data.setContent(inputData.getContent());
		
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

	@RequestMapping(value = "/news", method = RequestMethod.DELETE)
	public ResponseEntity<MessageBean> delete(@RequestParam(value = "id" , required = true)  String id) {
		New data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")),HttpStatus.OK);
		}
		
		if(!service.deleteable(data.getId())){
			return new ResponseEntity<MessageBean>(new MessageBean("1", messageUtil.getMessage("news.unDeleteable")),HttpStatus.OK);
		}
		data.setStatus(0);
		
		service.save(data);
		return new ResponseEntity<MessageBean>(HttpStatus.OK);
	}

	@RequestMapping(value = "/news/approve", method = RequestMethod.PUT )
	public ResponseEntity<MessageBean> approve( 
			@RequestBody ApproveWrapper approve) {
		
		service.Approve(approve.forAll, approve.ids,approve.active);
		
		return new ResponseEntity<MessageBean>(HttpStatus.OK);
	}

	@RequestMapping(value = "/news/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageBean> getAll() {
		List<NewsSelect2Bean> data = service.getAll();
		return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data), HttpStatus.OK);
	}
}
