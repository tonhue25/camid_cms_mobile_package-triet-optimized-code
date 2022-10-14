

package co.siten.myvtg.controller;

import javax.servlet.http.HttpServletRequest;
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
import co.siten.myvtg.model.myvtg.AdvertBanner;
import co.siten.myvtg.service.IAdvertBannerService;
import co.siten.myvtg.util.MessageUtil;
import co.siten.myvtg.util.ApproveWrapper;
import co.siten.myvtg.util.CommonUtil;

@RestController
@RequestMapping(value = "/api/${version}")
public class AdvertBannerController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(AdvertBannerController.class);
	@Autowired
	private IAdvertBannerService service;

	@Autowired
	private MessageUtil messageUtil;


	@RequestMapping(value = "/advertbanners", method = RequestMethod.GET)
	public Page<AdvertBanner> search(
			@RequestParam(value = "page" , required = false, defaultValue ="0") int page,
			@RequestParam(value = "size", required = false, defaultValue ="5") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "des") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "0") int sortType,
			//@RequestParam(value = "language", required = false) String language,
			@RequestParam(value = "des", required = false, defaultValue = "") String des) {

		Page<AdvertBanner> resultPage = service.findPaginated(page, size, sortBy, sortType, des);
		
		return resultPage;
	}

	@RequestMapping(value = "/advertbanners/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageBean> get(@RequestParam(value = "id" , required = true)  String id) {
		AdvertBanner data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", ""), HttpStatus.OK);
		}
		return new ResponseEntity<MessageBean>(new MessageBean("", "",data), HttpStatus.OK);
	}


	@RequestMapping(value = "/advertbanners", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessageBean> create(@Valid @RequestBody AdvertBanner inputData,HttpServletRequest request) {
		
		ResponseEntity<MessageBean> resp ;
		
		try {

			AdvertBanner data = new AdvertBanner();
			//set info from inputData into data
			data.setType(inputData.getType());
			data.setDes(inputData.getDes());
			data.setSourceLink(inputData.getSourceLink());
			data.setAdvImgUrl(inputData.getAdvImgUrl());
			
			if(inputData.getShowPage()==null) data.setShowPage(1);
			else data.setShowPage(inputData.getShowPage());
			
			//auto set info
			data.setApproved(0);
			data.setStatus(1);
			data.setCreatedBy(getCurrentUserId());
			data.setCreatedTime(CommonUtil.getCurrentTime());

			service.save(data);
			
			resp = new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data),
				HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("", e);
			resp = new ResponseEntity<MessageBean>(new MessageBean("500", e.getMessage()), HttpStatus.OK);
		}
		
		return resp;
	}


	@RequestMapping(value = "/advertbanners", method = RequestMethod.PUT)
	public ResponseEntity<MessageBean> update(@Valid @RequestBody AdvertBanner inputData) {

		String id = inputData.getId();
	try {
		AdvertBanner data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")),HttpStatus.OK);
		}
		// TODO setAll Information
		data.setType(inputData.getType());
		data.setDes(inputData.getDes());
		data.setSourceLink(inputData.getSourceLink());
		data.setAdvImgUrl(inputData.getAdvImgUrl());
		
		data.setShowPage(inputData.getShowPage());
		
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

	@RequestMapping(value = "/advertbanners", method = RequestMethod.DELETE)
	public ResponseEntity<MessageBean> delete(@RequestParam(value = "id" , required = true)  String id) {
		AdvertBanner data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")),HttpStatus.OK);
		}
		data.setStatus(0);
		
		service.save(data);
		return new ResponseEntity<MessageBean>(HttpStatus.OK);
	}

	
	@RequestMapping(value = "/advertbanners/approve", method = RequestMethod.PUT )
	public ResponseEntity<MessageBean> approve(@RequestBody ApproveWrapper approve) {
		
		service.Approve(approve.forAll, approve.ids,approve.active);
		
		return new ResponseEntity<MessageBean>(HttpStatus.OK);
	}
}
