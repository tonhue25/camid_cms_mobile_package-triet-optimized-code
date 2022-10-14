

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
import co.siten.myvtg.model.myvtg.ServiceGroup;
import co.siten.myvtg.service.ServiceGroupService;
import co.siten.myvtg.service.ServiceService;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.MessageUtil;

@RestController
@RequestMapping(value = "/api/${version}")
public class ServiceGroupController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(ServiceGroupController.class);
	@Autowired
	private ServiceGroupService service;

	
	@Autowired
	private MessageUtil messageUtil;


	@RequestMapping(value = "/servicegroups", method = RequestMethod.GET)
	public Page<ServiceGroup> search(
			@RequestParam(value = "page" , required = false, defaultValue ="0") int page,
			@RequestParam(value = "size", required = false, defaultValue ="5") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "0") int sortType,
			@RequestParam(value = "language", required = false) String language,
			
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {

		
		Page<ServiceGroup> resultPage = service.findServicePaginated(page, size, sortBy, sortType,
				language, name);
		return resultPage;
	}
	

	@RequestMapping(value = "/servicegroups/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageBean> get(@RequestParam(value = "id", required = true) String id) {
		ServiceGroup data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", ""),HttpStatus.OK);
		}
		return new ResponseEntity<MessageBean>(new MessageBean("", "",data), HttpStatus.OK);
	}


	@RequestMapping(value = "/servicegroups", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessageBean> create(@Valid @RequestBody ServiceGroup inputData) {
		try {
			
			if(service.findOneByCodeAndLanguage(inputData.getCode(),inputData.getLanguage())!=null){
				return new ResponseEntity<MessageBean>(new MessageBean("2", messageUtil.getMessage("serviceGroups.codeAndLanguageExisted")), HttpStatus.OK);
			}
			
			
			ServiceGroup data = new ServiceGroup();
			//set info from inputData into data			
			data.setCode(inputData.getCode());
			data.setName(inputData.getName());
			data.setShortDes(inputData.getShortDes());
			data.setLanguage(inputData.getLanguage());
		
			//auto set info
			
			data.setStatus(1);
			data.setCreatedTime(CommonUtil.getCurrentTime());
			data.setCreatedBy(getCurrentUserId());
			
			
			service.save(data);

			return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data),HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("", e);
			return new ResponseEntity<MessageBean>(new MessageBean("500", e.getMessage()), HttpStatus.OK);
		}

		
	}


	@RequestMapping(value = "/servicegroups", method = RequestMethod.PUT)
	public ResponseEntity<MessageBean> update(@Valid @RequestBody ServiceGroup inputData) {

		String id = inputData.getId();
	try {
		ServiceGroup data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")),HttpStatus.OK);
		}
		
		ServiceGroup exist = service.findOneByCodeAndLanguage(inputData.getCode(),inputData.getLanguage());
		if(exist !=null && !exist.getId().equals(id)){
			return new ResponseEntity<MessageBean>(new MessageBean("2", messageUtil.getMessage("serviceGroups.codeAndLanguageExisted")), HttpStatus.OK);
		}
		
		
		// TODO setAll Information
		data.setCode(inputData.getCode());
		data.setName(inputData.getName());
		data.setShortDes(inputData.getShortDes());
		
		data.setLanguage(inputData.getLanguage());
				
		//auto set info
		data.setStatus(1);
		data.setLastUpdatedTime(CommonUtil.getCurrentTime());
		data.setLastUpdatedBy(getCurrentUserId());
		
		service.save(data);
		return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data), HttpStatus.OK);
		
		} catch (Exception e) {
			logger.error("", e);
			return new ResponseEntity<MessageBean>(new MessageBean("500", e.getMessage()), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/servicegroups", method = RequestMethod.DELETE)
	public ResponseEntity<MessageBean> delete(@RequestParam(value = "id" , required = true)  String id) {
		ServiceGroup data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")),HttpStatus.OK);
		}
		
		
		if(!service.deleteable(data.getId())){
			return new ResponseEntity<MessageBean>(new MessageBean("1", messageUtil.getMessage("serviceGroups.unDeleteable")),HttpStatus.OK);
		}
		
		
		data.setStatus(0);
		service.save(data);
		return new ResponseEntity<MessageBean>(HttpStatus.OK);
	}
	
}
