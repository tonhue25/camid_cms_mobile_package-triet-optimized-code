

package co.siten.myvtg.controller;
import java.util.List;

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
import co.siten.myvtg.model.myvtg.Action;
import co.siten.myvtg.model.myvtg.ActionPK;
import co.siten.myvtg.service.IActionService;
import co.siten.myvtg.service.IWebServiceService;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.MessageUtil;

@RestController
@RequestMapping(value = "/api/${version}")
public class ActionController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(ActionController.class);
	@Autowired
	private IActionService service;
	
	@Autowired
	private IWebServiceService webservice;
	

	@Autowired
	private MessageUtil messageUtil;


	@RequestMapping(value = "/actions", method = RequestMethod.GET)
	public Page<Action> searchData(
			@RequestParam(value = "page" , required = false, defaultValue ="0") int page,
			@RequestParam(value = "size", required = false, defaultValue ="5") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "0") int sortType,
			@RequestParam(value = "subServiceCode", required = false) String subServiceCode)
		{

		Page<Action> resultPage = service.findPaginated(page, size, sortBy, sortType, subServiceCode);
		
		
		return resultPage;
	}
			
	@RequestMapping(value = "/actions/list", method = RequestMethod.GET)
	public List<Action> list()
	{
		List<Action> result = service.findByStatus(1);
		return result;
	}

	@RequestMapping(value = "/actions/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageBean> get(
			@RequestParam(value = "subServiceCode" , required = true) String subServiceCode,
			@RequestParam(value = "actionType" , required = true) int actionType,
			@RequestParam(value = "channelType" , required = true) int channelType)
	{
		Action data = service.findOneBySubServiceCodeAndActionTypeAndChannelType(subServiceCode, actionType, channelType);
		
		if (data==null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404",messageUtil.getMessage("notFound")),HttpStatus.OK);
		}
		return new ResponseEntity<MessageBean>(new MessageBean("","",data), HttpStatus.OK);
	}

	@RequestMapping(value = "/actions", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessageBean> create(@Valid @RequestBody Action inputData) {
		try {
			
			if(service.isExist(inputData)){
				return new ResponseEntity<MessageBean>(new MessageBean("1", messageUtil.getMessage("actions.existed")), HttpStatus.OK);
			}
			
			if(webservice.findById(inputData.getWsId())==null){
				return new ResponseEntity<MessageBean>(new MessageBean("2", messageUtil.getMessage("actions.WsIdNotExist")), HttpStatus.OK);
			}
			
			Action data = new Action();
			//set info from inputData into data
			
			data.setId(inputData.getId());
			data.setName(inputData.getName());
			data.setSyntax(inputData.getSyntax());
			data.setChannel(inputData.getChannel());
			data.setWsId(inputData.getWsId());
			
			//auto set info
			data.setStatus(1);
			data.setCreatedTime(CommonUtil.getCurrentTime());
			data.setCreatedBy(getCurrentUserId());
			
			service.save(data);

			return new ResponseEntity<MessageBean>(new MessageBean("", "", data),HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("", e);
			return new ResponseEntity<MessageBean>(new MessageBean("500", e.getMessage()), HttpStatus.OK);
		}		
	}

	@RequestMapping(value = "/actions", method = RequestMethod.PUT)
	public ResponseEntity<MessageBean> update(@Valid @RequestBody Action inputData) {

	try {
		
		Action data = service.findOneBySubServiceCodeAndActionTypeAndChannelType(inputData.getId().getSubServiceCode(), inputData.getId().getActionType(), inputData.getId().getChannelType());
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", ""),HttpStatus.OK);
		}
		
		
		
		if(webservice.findById(inputData.getWsId())==null){
			return new ResponseEntity<MessageBean>(new MessageBean("2", messageUtil.getMessage("actions.WsIdNotExist")), HttpStatus.OK);
		}
		
		ActionPK oldId = inputData.getId();
		
		if(!inputData.newId.equals(oldId)){
			Action exist = service.findOneBySubServiceCodeAndActionTypeAndChannelType(inputData.newId.getSubServiceCode(),inputData.newId.getActionType(),inputData.newId.getChannelType());
			
			if(exist != null){
				return new ResponseEntity<MessageBean>(new MessageBean("1", messageUtil.getMessage("actions.existed")), HttpStatus.OK);
			}
			
			data.setId(inputData.newId);
		}

		
		// TODO setAll Information
		
		data.setName(inputData.getName());
		data.setSyntax(inputData.getSyntax());
		data.setWsId(inputData.getWsId());
		data.setChannel(inputData.getChannel());
		
		//auto set info
		data.setLastUpdatedBy(getCurrentUserId());
		data.setLastUpdatedTime(CommonUtil.getCurrentTime());
		
		service.save(data);
		
		if(!inputData.newId.equals(oldId)){
			Action oldData = service.findOneBySubServiceCodeAndActionTypeAndChannelType(oldId.getSubServiceCode(), oldId.getActionType(), oldId.getChannelType());
			service.delete(oldData);
		}
		
		return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("", e);
			return new ResponseEntity<MessageBean>(new MessageBean("500", e.getMessage()), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/actions", method = RequestMethod.DELETE)
	public ResponseEntity<MessageBean> delete(@RequestParam(value = "subServiceCode" , required = true) String subServiceCode,
			@RequestParam(value = "actionType" , required = true) int actionType,
			@RequestParam(value = "channelType" , required = true) int channelType) {
		

		Action data = service.findOneBySubServiceCodeAndActionTypeAndChannelType(subServiceCode, actionType, channelType);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("notFound")),
					HttpStatus.OK);
		}
		data.setStatus(0);

		service.save(data);
		return new ResponseEntity<MessageBean>(HttpStatus.OK);
	}

}
