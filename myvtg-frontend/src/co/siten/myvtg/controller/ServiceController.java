package co.siten.myvtg.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import co.siten.myvtg.model.myvtg.Service;
import co.siten.myvtg.model.myvtg.ServiceGroup;
import co.siten.myvtg.service.IServiceGroupService;
import co.siten.myvtg.service.IServiceService;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.ApproveWrapper;
import co.siten.myvtg.util.MessageUtil;

@RestController
@RequestMapping(value = "/api/${version}")
public class ServiceController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);
	@Autowired
	private IServiceService service;
	
	
	@Autowired
	private IServiceGroupService serviceGroup;
	
	@Autowired
	private MessageUtil messageUtil;
	
	@RequestMapping(value = "/services", method = RequestMethod.GET)
	public Page<Service> search(
			@RequestParam(value = "page" , required = false, defaultValue ="0") int page, 
			@RequestParam(value = "size", required = false, defaultValue ="5") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "0") int sortType,
			@RequestParam(value = "serviceGroupId", required = false) String serviceGroupId,
			@RequestParam(value = "language", required = false) String language,
			@RequestParam(value = "serviceType", required = false, defaultValue="2") int serviceType,
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {

		Page<Service> resultPage = service.findServicePaginated(page, size, sortBy, sortType, serviceGroupId,
				language, name,serviceType);
		
		List<String> serviceGroupIds = new ArrayList<String>();
		resultPage.getContent().forEach(item->serviceGroupIds.add(item.getServiceGroupId()));
		
		List<ServiceGroup> serviceGroups  = serviceGroup.findByIds(serviceGroupIds);
		
		resultPage.getContent().forEach(service->{
			for(ServiceGroup sg : serviceGroups){
				if(sg.getId().equals(service.getServiceGroupId())){
					service.setServiceGroupName(sg.getName());
				}
			}
		});
		
		return resultPage;
	}

	@RequestMapping(value = "/services/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageBean> get(@RequestParam(value = "id", required = true) String id) {
		
		Service data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", ""),HttpStatus.OK);
		}
		return new ResponseEntity<MessageBean>(new MessageBean("", "",data), HttpStatus.OK);
	}

	@RequestMapping(value = "/services", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessageBean> create(@Valid @RequestBody Service inputData) {
		try {
			
			if(serviceGroup.findById(inputData.getServiceGroupId())==null){
				return new ResponseEntity<MessageBean>(new MessageBean("1",  messageUtil.getMessage("services.serviceGroupIdNotExist")), HttpStatus.OK);
			}
			
			if(service.findOneByCodeAndLanguageAndServiceType(inputData.getCode(),inputData.getLanguage(),inputData.getServiceType())!=null){
				return new ResponseEntity<MessageBean>(new MessageBean("2", messageUtil.getMessage("services.codeExisted")), HttpStatus.OK);
			}
					
			Service data = new Service();
			
			data.setCode(inputData.getCode());
			data.setName(inputData.getName());
			data.setServiceGroupId(inputData.getServiceGroupId());
			data.setLanguage(inputData.getLanguage());
			data.setShortCode(inputData.getShortCode());
			data.setActionType(inputData.getActionType());
			data.setServiceType(inputData.getServiceType());
			data.setShortDes(inputData.getShortDes());
			data.setFullDes(inputData.getFullDes());
			data.setIconUrl(inputData.getIconUrl());
			data.setImgDesUrl(inputData.getImgDesUrl());
			data.setWebLink(inputData.getWebLink());
			
			data.setIsMultPlan(inputData.getIsMultPlan());
			
			//Auto set info
			data.setStatus(1);
			data.setApproved(0);
			data.setCreatedTime(CommonUtil.getCurrentTime());
			data.setCreatedBy(getCurrentUserId());
			
			service.save(data);
			
			return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data),HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("", e);
			return new ResponseEntity<MessageBean>(new MessageBean("500", e.getMessage()), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/services", method = RequestMethod.PUT)
	public ResponseEntity<MessageBean> update(@Valid @RequestBody Service inputData) {
		try {
			String id = inputData.getId();
			Service data = service.findById(id);
			if (data == null) {
				return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")),HttpStatus.OK);
			}
			
			if(serviceGroup.findById(inputData.getServiceGroupId())==null){
				return new ResponseEntity<MessageBean>(new MessageBean("1", messageUtil.getMessage("services.serviceGroupIdNotExist")), HttpStatus.OK);
			}
			
			Service exist = service.findOneByCodeAndLanguageAndServiceType(inputData.getCode(),inputData.getLanguage(),inputData.getServiceType());
			
			if(exist !=null && !exist.getId().equals(id)){
				return new ResponseEntity<MessageBean>(new MessageBean("2", messageUtil.getMessage("services.codeExisted")), HttpStatus.OK);
			}
			
			// TODO setAll Information
			data.setCode(inputData.getCode());
			data.setName(inputData.getName());
			data.setServiceGroupId(inputData.getServiceGroupId());
			data.setLanguage(inputData.getLanguage());
			data.setShortCode(inputData.getShortCode());
			data.setActionType(inputData.getActionType());
			data.setServiceType(inputData.getServiceType());
			data.setShortDes(inputData.getShortDes());
			data.setFullDes(inputData.getFullDes());
			data.setIconUrl(inputData.getIconUrl());
			data.setImgDesUrl(inputData.getImgDesUrl());
			data.setWebLink(inputData.getWebLink());
			
			data.setIsMultPlan(inputData.getIsMultPlan());
			
			//auto set info
			data.setApproved(0);
			data.setLastUpdatedTime(CommonUtil.getCurrentTime());
			data.setLastUpdatedBy(getCurrentUserId());
			
			service.save(data);
			return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("", e);
			// TODO: handle exception
			return new ResponseEntity<MessageBean>(new MessageBean("500", e.getMessage()), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/services", method = RequestMethod.DELETE)
	public ResponseEntity<MessageBean> delete(@RequestParam(value = "id" , required = true)  String id) {
		
		Service data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")),HttpStatus.OK);
		}
		
		if(!service.deleteable(data.getId())){
			return new ResponseEntity<MessageBean>(new MessageBean("1", messageUtil.getMessage("services.unDeleteable")),HttpStatus.OK);
		}
		
		data.setStatus(0);
		
		
		service.save(data);
		return new ResponseEntity<MessageBean>(HttpStatus.OK);
	}
	

	@RequestMapping(value = "/services/approve", method = RequestMethod.PUT )
	public ResponseEntity<MessageBean> approve(@RequestBody ApproveWrapper approve) {
			service.Approve(approve.forAll, approve.ids,approve.active);
		return new ResponseEntity<MessageBean>(HttpStatus.OK);
	}
}
