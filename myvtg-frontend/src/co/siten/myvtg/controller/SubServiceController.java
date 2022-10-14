package co.siten.myvtg.controller;

import co.siten.myvtg.bean.MessageBean;
import co.siten.myvtg.bean.SubserviceSelect2Bean;
import co.siten.myvtg.model.myvtg.Service;
import co.siten.myvtg.model.myvtg.SubService;
import co.siten.myvtg.service.IServiceService;
import co.siten.myvtg.service.ISubServiceService;
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
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/${version}")
public class SubServiceController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(SubServiceController.class);
	@Autowired
	private ISubServiceService service;

	@Autowired
	private IServiceService serviceService;
	
	@Autowired
	private MessageUtil messageUtil;

	@RequestMapping(value = "/subservices", method = RequestMethod.GET)
	public Page<SubService> search(
			@RequestParam(value = "page" , required = false, defaultValue ="0") int page,
			@RequestParam(value = "size", required = false, defaultValue ="5") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "0") int sortType,
			@RequestParam(value = "serviceId", required = false) String serviceId,
			@RequestParam(value = "language", required = false) String language,

			@RequestParam(value = "name", required = false, defaultValue = "") String name) {

		Page<SubService> resultPage = service.findServicePaginated(page, size, sortBy, sortType, serviceId,language,name);
		
		List<String> serviceIds = new ArrayList<String>();
		resultPage.getContent().forEach(item->serviceIds.add(item.getServiceId()));
		
		List<Service> services  = serviceService.findByIds(serviceIds);
		
		resultPage.getContent().forEach(subService->{
			for(Service s : services){
				if(s.getId().equals(subService.getServiceId())){
					subService.setServiceName(s.getName());
				}
			}
		});
		
		return resultPage;
	}


	@RequestMapping(value = "/subservices/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageBean> get(@RequestParam(value = "id", required = true) String id) {
		SubService data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", ""),HttpStatus.OK);
		}
		return new ResponseEntity<MessageBean>(new MessageBean("", "",data), HttpStatus.OK);
	}


	@RequestMapping(value = "/subservices", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessageBean> create(@Valid @RequestBody SubService inputData) {
		try {
			
			if(service.isExist(inputData)){
				return new ResponseEntity<MessageBean>(new MessageBean("1",messageUtil.getMessage("subServices.codeAndLanguageExisted")), HttpStatus.OK);
			}
			
			
			if(serviceService.findById(inputData.getServiceId())==null){
				return new ResponseEntity<MessageBean>(new MessageBean("2",messageUtil.getMessage("subServices.serviceIdNotExist")  ), HttpStatus.OK);
			}
			
			
			SubService data = new SubService();

			//set info from inputData into data			
			data.setCode(inputData.getCode());
			data.setName(inputData.getName());
			data.setServiceId(inputData.getServiceId());
			data.setLanguage(inputData.getLanguage());
			data.setShortDes(inputData.getShortDes());
			data.setFullDes(inputData.getFullDes());
			data.setIconUrl(inputData.getIconUrl());
			data.setType(inputData.getType());
			data.setVolume(inputData.getVolume());
			data.setPrice(inputData.getPrice());
			data.setUnit(inputData.getUnit());
			
			//auto set info
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


	@RequestMapping(value = "/subservices", method = RequestMethod.PUT)
	public ResponseEntity<MessageBean> update(@Valid @RequestBody SubService inputData) {

		String id = inputData.getId();
	try {
		SubService data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")),HttpStatus.OK);
		}
		
		SubService exist = service.findOneByCodeAndLanguage(inputData.getCode(),inputData.getLanguage());
		if(exist !=null && !exist.getId().equals(id)){
			return new ResponseEntity<MessageBean>(new MessageBean("1", messageUtil.getMessage("subServices.codeAndLanguageExisted")), HttpStatus.OK);
		}
		
		if(serviceService.findById(inputData.getServiceId())==null){
			return new ResponseEntity<MessageBean>(new MessageBean("2",messageUtil.getMessage("subServices.serviceIdNotExist")), HttpStatus.OK);
		}
		
		// TODO setAll Information
		data.setCode(inputData.getCode());
		data.setName(inputData.getName());
		data.setServiceId(inputData.getServiceId());
		data.setLanguage(inputData.getLanguage());
		data.setShortDes(inputData.getShortDes());
		data.setFullDes(inputData.getFullDes());
		data.setIconUrl(inputData.getIconUrl());
		data.setType(inputData.getType());
		data.setVolume(inputData.getVolume());
		data.setPrice(inputData.getPrice());
		data.setUnit(inputData.getUnit());
				
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

	
	@RequestMapping(value = "/subservices", method = RequestMethod.DELETE)
	public ResponseEntity<MessageBean> delete(@RequestParam(value = "id" , required = true)  String id) {
		SubService data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")),HttpStatus.OK);
		}

		if(!service.deleteable(data.getId())){
			return new ResponseEntity<MessageBean>(new MessageBean("1", messageUtil.getMessage("subServices.unDeleteable")),HttpStatus.OK);
		}
		
		data.setStatus(0);
		
		service.save(data);
		return new ResponseEntity<MessageBean>(HttpStatus.OK);
	}

	@RequestMapping(value = "/subservices/approve", method = RequestMethod.PUT )
	public ResponseEntity<MessageBean> approve(
			@RequestBody ApproveWrapper approve) {
		
		service.Approve(approve.forAll, approve.ids,approve.active);
		
		return new ResponseEntity<MessageBean>(HttpStatus.OK);
	}

	@RequestMapping(value = "/subservices/getAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageBean> getAll() {
		List<SubserviceSelect2Bean> data = service.getAll();
		return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data), HttpStatus.OK);
	}
}
