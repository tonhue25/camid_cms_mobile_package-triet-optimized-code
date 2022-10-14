

package co.siten.myvtg.controller;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
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
import co.siten.myvtg.model.myvtg.HotNew;
import co.siten.myvtg.service.IHotNewService;
import co.siten.myvtg.service.INewService;
import co.siten.myvtg.service.IPromotionService;
import co.siten.myvtg.service.IServiceService;
import co.siten.myvtg.util.MessageUtil;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.ListHotNewsWrapper;

@RestController
@RequestMapping(value = "/api/${version}")
public class HotNewController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(HotNewController.class);
	@Autowired
	private IHotNewService service;

	@Autowired 
	IServiceService serviceService;
	
	@Autowired
	IPromotionService promotionService;
	
	@Autowired
	INewService newsService;
	
	
	@Autowired
	private MessageUtil messageUtil;


	@RequestMapping(value = "/hotnews", method = RequestMethod.GET)
	public Page<HotNew> search(
			@RequestParam(value = "page" , required = false, defaultValue ="0") int page,
			@RequestParam(value = "size", required = false, defaultValue ="5") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "priority") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "0") int sortType,
			@RequestParam(value = "state", required = false,defaultValue = "2") int state,
			@RequestParam(value = "language", required = false) String lang,
			@RequestParam(value = "from", required = false)  Date from,
			@RequestParam(value = "to", required = false) Date to) {
			
			Page<HotNew> resultPage = service.findPaginated(page, size, sortBy, sortType,lang,state,from,to);
		
		return resultPage;
	}


	
	@RequestMapping(value = "/hotnews/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageBean> get(@RequestParam(value = "id", required = true) String id,@RequestParam(value = "type", required = true) int type) {
		
		
		HotNew data = service.findByIdAndType(id,type);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", ""),HttpStatus.OK);
		}
		return new ResponseEntity<MessageBean>(new MessageBean("", "",data), HttpStatus.OK);
	}


	@RequestMapping(value = "/hotnews", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessageBean> create(@Valid @RequestBody HotNew inputData) {
		try {

			if(service.isExist(inputData)){
				return new ResponseEntity<MessageBean>(new MessageBean("1",messageUtil.getMessage("hotnews.IdAndTypeExisted") ), HttpStatus.OK);
			}

			HotNew data = new HotNew();
			//set info from inputData into data
			data.setId(inputData.getId());
			data.setType(inputData.getType());
			
			if(data.getType()==0 && serviceService.findById(data.getId())==null){
					return new ResponseEntity<MessageBean>(new MessageBean("1", messageUtil.getMessage("services.notExist")), HttpStatus.OK);
			}
			else if(data.getType()==1 && promotionService.findById(data.getId())==null){
				return new ResponseEntity<MessageBean>(new MessageBean("2",messageUtil.getMessage("promotions.notExist")), HttpStatus.OK);
			}
			else if(data.getType()==2 && newsService.findById(data.getId())==null){
				return new ResponseEntity<MessageBean>(new MessageBean("3", messageUtil.getMessage("news.notExist")), HttpStatus.OK);
			}
			
			
			if(inputData.getPriority()==null) data.setPriority(0);
			else data.setPriority(inputData.getPriority());
			
			data.setEffectTime(inputData.getEffectTime());
			data.setExpiredTime(inputData.getExpiredTime());
			data.setAdImgUrl(inputData.getAdImgUrl());
			data.setLanguage(inputData.getLanguage());
			data.setState(inputData.getState());

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


	@RequestMapping(value = "/hotnews", method = RequestMethod.PUT)
	public ResponseEntity<MessageBean> update(@Valid @RequestBody HotNew inputData) {

		String id = inputData.getOldId();
		int type = inputData.getOldType();
		
	try {
		HotNew data = service.findOneByIdAndType(id,type);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404",messageUtil.getMessage("hotnews.notFound")),HttpStatus.OK);
		}
		// TODO setAll Information
		
		data.setId(inputData.getId());
		data.setType(inputData.getType());
		
		if(!id.equals(data.getId()) || type!=data.getType()){
			if(service.isExist(data)){
				return new ResponseEntity<MessageBean>(new MessageBean("1", messageUtil.getMessage("hotnews.IdAndTypeExisted")), HttpStatus.OK);
			}
		}
		
		if(data.getType()==0 && serviceService.findById(data.getId())==null){
			return new ResponseEntity<MessageBean>(new MessageBean("1", messageUtil.getMessage("services.notExist")), HttpStatus.OK);
		}
		else if(data.getType()==1 && promotionService.findById(data.getId())==null){
			return new ResponseEntity<MessageBean>(new MessageBean("1", messageUtil.getMessage("promotions.notExist")), HttpStatus.OK);
		}
		else if(data.getType()==2 && newsService.findById(data.getId())==null){
			return new ResponseEntity<MessageBean>(new MessageBean("1", messageUtil.getMessage("news.notExist")), HttpStatus.OK);
		}
		
		data.setPriority(inputData.getPriority());
		data.setEffectTime(inputData.getEffectTime());
		data.setExpiredTime(inputData.getExpiredTime());
		data.setAdImgUrl(inputData.getAdImgUrl());
		data.setLanguage(inputData.getLanguage());
		data.setState(inputData.getState());
		
		//auto set info
		data.setApproved(0);
		data.setLastUpdatedTime(CommonUtil.getCurrentTime());
		data.setLastUpdatedBy(getCurrentUserId());

		service.save(data);
		
		if(!id.equals(data.getId())){
			service.delete(service.findOneByIdAndType(id,type));
		}
		
		return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data), HttpStatus.OK);
		
		} catch (Exception e) {
			logger.error("", e);
			return new ResponseEntity<MessageBean>(new MessageBean("500", e.getMessage()), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/hotnews", method = RequestMethod.DELETE)
	public ResponseEntity<MessageBean> delete(@RequestParam(value = "id" , required = true)  String id,@RequestParam(value = "type" , required = true)  int type) {
		HotNew data = service.findByIdAndType(id,type);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")),HttpStatus.OK);
		}
		
		if(!service.deleteable(data.getId())){
			return new ResponseEntity<MessageBean>(new MessageBean("1", messageUtil.getMessage("hotnews.unDeleteable")),HttpStatus.OK);
		}
		data.setStatus(0);
		service.save(data);
		return new ResponseEntity<MessageBean>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/hotnews/approve", method = RequestMethod.PUT )
	public ResponseEntity<MessageBean> approve(
			@RequestBody ListHotNewsWrapper approve) {
		
		service.Approve(approve.forAll, approve.ids,approve.active);
		
		return new ResponseEntity<MessageBean>(HttpStatus.OK);
	}

}
