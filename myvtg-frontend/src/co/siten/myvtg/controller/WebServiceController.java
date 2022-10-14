
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
import co.siten.myvtg.exception.MyResourceNotFoundException;
import co.siten.myvtg.model.myvtg.Webservice;
import co.siten.myvtg.service.IWebServiceService;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.MessageUtil;

@RestController
@RequestMapping(value = "/api/${version}")
public class WebServiceController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(WebServiceController.class);
	@Autowired
	private IWebServiceService service;

	@Autowired
	private MessageUtil messageUtil;

	@RequestMapping(value = "/webservices/list", method = RequestMethod.GET)
	public List<Webservice> getList() {
		return service.getList();
	}

	@RequestMapping(value = "/webservices", method = RequestMethod.GET)
	public Page<Webservice> searchData(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "5") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "wsName") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "0") int sortType,
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {

		Page<Webservice> resultPage = service.findPaginated(page, size, sortBy, sortType, name);
		return resultPage;
	}

	@RequestMapping(value = "/webservices/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageBean> get(@RequestParam(value = "id", required = true) String id) {
		Webservice data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", ""), HttpStatus.OK);
		}
		return new ResponseEntity<MessageBean>(new MessageBean("", "", data), HttpStatus.OK);
	}

	@RequestMapping(value = "/webservices", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessageBean> create(@Valid @RequestBody Webservice inputData) {
		try {
			Webservice data = new Webservice();
			// set info from inputData into data
			data.setWsName(inputData.getWsName());
			data.setWsType(inputData.getWsType());
			data.setUrl(inputData.getUrl());
			data.setParams(inputData.getParams());
			data.setUsername(inputData.getUsername());
			data.setPassword(inputData.getPassword());
			data.setRspCodeSucc(inputData.getRspCodeSucc());
			data.setXpathResponseCode(inputData.getXpathResponseCode());
			data.setXpathExtension01(inputData.getXpathExtension01());
			data.setXpathExtension02(inputData.getXpathExtension02());
			data.setXpathExtension03(inputData.getXpathExtension03());
			data.setDes(inputData.getDes());
			data.setRawXml(inputData.getRawXml());

			// auto set info
			data.setStatus(1);
			data.setCreatedBy(getCurrentUserId());
			data.setCreatedTime(CommonUtil.getCurrentTime());

			service.save(data);

			return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data),
					HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("", e);
			return new ResponseEntity<MessageBean>(new MessageBean("500", e.getMessage()), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/webservices", method = RequestMethod.PUT)
	public ResponseEntity<MessageBean> update(@Valid @RequestBody Webservice inputData) {

		try {
			String id = inputData.getId();
			Webservice data = service.findById(id);
			if (data == null) {
				return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")),
						HttpStatus.OK);
			}

			// TODO setAll Information
			data.setWsName(inputData.getWsName());
			data.setWsType(inputData.getWsType());
			data.setUrl(inputData.getUrl());
			data.setParams(inputData.getParams());
			data.setUsername(inputData.getUsername());
			data.setPassword(inputData.getPassword());
			data.setDes(inputData.getDes());
			data.setRawXml(inputData.getRawXml());
			data.setRspCodeSucc(inputData.getRspCodeSucc());
			data.setXpathResponseCode(inputData.getXpathResponseCode());
			data.setXpathExtension01(inputData.getXpathExtension01());
			data.setXpathExtension02(inputData.getXpathExtension02());
			data.setXpathExtension03(inputData.getXpathExtension03());

			// auto set info

			data.setLastUpdatedTime(CommonUtil.getCurrentTime());
			data.setLastUpdatedBy(getCurrentUserId());

			service.save(data);
			return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error("", e);
			return new ResponseEntity<MessageBean>(new MessageBean("500", e.getMessage()), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/webservices", method = RequestMethod.DELETE)
	public ResponseEntity<MessageBean> delete(@RequestParam(value = "id", required = true) String id) {
		Webservice data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")), HttpStatus.OK);
		}

		if (!service.deleteable(data.getId())) {
			return new ResponseEntity<MessageBean>(
					new MessageBean("1", messageUtil.getMessage("webServices.unDeleteable")), HttpStatus.OK);
		}
		data.setStatus(0);

		service.save(data);
		return new ResponseEntity<MessageBean>(HttpStatus.OK);
	}

}
