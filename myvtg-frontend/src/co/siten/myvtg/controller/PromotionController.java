

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
import co.siten.myvtg.exception.MyResourceNotFoundException;
import co.siten.myvtg.model.myvtg.Promotion;
import co.siten.myvtg.model.myvtg.Service;
import co.siten.myvtg.service.IPromotionService;
import co.siten.myvtg.service.PromotionService;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.ApproveWrapper;
import co.siten.myvtg.util.MessageUtil;

@RestController
@RequestMapping(value = "/api/${version}")
public class PromotionController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(PromotionController.class);
	@Autowired
	private IPromotionService service;

	@Autowired
	private MessageUtil messageUtil;


	@RequestMapping(value = "/promotions", method = RequestMethod.GET)
	public Page<Promotion> search(
			@RequestParam(value = "page" , required = false, defaultValue ="0") int page,
			@RequestParam(value = "size", required = false, defaultValue ="5") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "0") int sortType,
			@RequestParam(value = "language", required = false) String language,
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {

		Page<Promotion> resultPage = service.findPaginated(page, size, sortBy, sortType,
				language, name);
		return resultPage;
	}


	@RequestMapping(value = "/promotions/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageBean> get(@RequestParam(value = "id", required = true) String id) {
		Promotion data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", ""),HttpStatus.OK);
		}
		return new ResponseEntity<MessageBean>(new MessageBean("", "",data), HttpStatus.OK);
	}


	@RequestMapping(value = "/promotions", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessageBean> create(@Valid @RequestBody Promotion inputData) {
		try {
			
			if(service.findOneByCodeAndLanguage(inputData.getCode(),inputData.getLanguage())!=null){
				return new ResponseEntity<MessageBean>(new MessageBean("1",  messageUtil.getMessage("promotions.codeExisted")), HttpStatus.OK);
			}
						
			Promotion data = new Promotion();
			//set info from inputData into data
			data.setName(inputData.getName());
			data.setCode(inputData.getCode());
			data.setEffectiveTime(inputData.getEffectiveTime());
			data.setExpiredTime(inputData.getExpiredTime());
			data.setPublishedTime(inputData.getPublishedTime());
			data.setType(inputData.getType());
			data.setLanguage(inputData.getLanguage());
			data.setShortDes(inputData.getShortDes());
			data.setFullDes(inputData.getFullDes());
			data.setIconUrl(inputData.getIconUrl());
			data.setImgDesUrl(inputData.getImgDesUrl());
			

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


	@RequestMapping(value = "/promotions", method = RequestMethod.PUT)
	public ResponseEntity<MessageBean> update(@Valid @RequestBody Promotion inputData) {

		String id = inputData.getId();
	try {
		Promotion data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")),HttpStatus.OK);
		}
		
		Promotion exist = service.findOneByCodeAndLanguage(inputData.getCode(),inputData.getLanguage());
		if(exist !=null && !exist.getId().equals(id)){
			return new ResponseEntity<MessageBean>(new MessageBean("2", messageUtil.getMessage("promotions.codeExisted")), HttpStatus.OK);
		}
		
		// TODO setAll Information
		data.setName(inputData.getName());
		data.setEffectiveTime(inputData.getEffectiveTime());
		data.setExpiredTime(inputData.getExpiredTime());
		data.setPublishedTime(inputData.getPublishedTime());
		data.setType(inputData.getType());
		data.setLanguage(inputData.getLanguage());
		data.setShortDes(inputData.getShortDes());
		data.setFullDes(inputData.getFullDes());
		data.setIconUrl(inputData.getIconUrl());
		data.setImgDesUrl(inputData.getImgDesUrl());
		
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

	@RequestMapping(value = "/promotions", method = RequestMethod.DELETE)
	public ResponseEntity<MessageBean> delete(@RequestParam(value = "id" , required = true)  String id) {
		Promotion data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")),HttpStatus.OK);
		}
		
		if(!service.deleteable(data.getId())){
			return new ResponseEntity<MessageBean>(new MessageBean("1", messageUtil.getMessage("promotions.unDeleteable")),HttpStatus.OK);
		}
		data.setStatus(0);
		
		service.save(data);
		return new ResponseEntity<MessageBean>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/promotions/approve", method = RequestMethod.PUT )
	public ResponseEntity<MessageBean> approve(
			@RequestBody ApproveWrapper approve) {
		
		service.Approve(approve.forAll, approve.ids,approve.active);
		
		return new ResponseEntity<MessageBean>(HttpStatus.OK);
	}

}
