
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
import co.siten.myvtg.model.myvtg.Shop;
import co.siten.myvtg.service.IShopService;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.MessageUtil;

@RestController
@RequestMapping(value = "/api/${version}")
public class ShopController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
	@Autowired
	private IShopService service;

	@Autowired
	private MessageUtil messageUtil;

	@RequestMapping(value = "/shops", method = RequestMethod.GET)
	public Page<Shop> search(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "5") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "0") int sortType,
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {

		Page<Shop> resultPage = service.findPaginated(page, size, sortBy, sortType, name);
		return resultPage;
	}

	@RequestMapping(value = "/shops/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageBean> get(@RequestParam(value = "id", required = true) String id) {
		Shop data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", ""), HttpStatus.OK);
		}
		return new ResponseEntity<MessageBean>(new MessageBean("", "", data), HttpStatus.OK);
	}

	@RequestMapping(value = "/shops", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessageBean> create(@Valid @RequestBody Shop inputData) {
		try {

			// if(service.isExist(inputData)){
			// return new ResponseEntity<MessageBean>(new MessageBean("1", "Đã
			// tồn tại"), HttpStatus.BAD_REQUEST);
			// }

			Shop data = new Shop();
			// set info from inputData into data
			data.setName(inputData.getName());
			data.setAddr(inputData.getAddr());
			data.setProvinceId(inputData.getProvinceId());
			data.setDistrictId(inputData.getDistrictId());
			data.setIsdn(inputData.getIsdn());
			data.setOpenTime(inputData.getOpenTime());
			data.setLongitude(inputData.getLongitude());
			data.setLatitude(inputData.getLatitude());

			data.setType(inputData.getType());

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

	@RequestMapping(value = "/shops", method = RequestMethod.PUT)
	public ResponseEntity<MessageBean> update(@Valid @RequestBody Shop inputData) {

		String id = inputData.getId();
		try {
			Shop data = service.findById(id);
			if (data == null) {
				return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")),
						HttpStatus.OK);
			}

			// TODO setAll Information
			data.setName(inputData.getName());
			data.setAddr(inputData.getAddr());
			data.setProvinceId(inputData.getProvinceId());
			data.setDistrictId(inputData.getDistrictId());
			data.setIsdn(inputData.getIsdn());
			data.setOpenTime(inputData.getOpenTime());
			data.setLongitude(inputData.getLongitude());
			data.setLatitude(inputData.getLatitude());

			data.setType(inputData.getType());

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

	@RequestMapping(value = "/shops", method = RequestMethod.DELETE)
	public ResponseEntity<MessageBean> delete(@RequestParam(value = "id", required = true) String id) {
		Shop data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")), HttpStatus.OK);
		}
		data.setStatus(0);

		service.save(data);
		return new ResponseEntity<MessageBean>(HttpStatus.OK);
	}

}
