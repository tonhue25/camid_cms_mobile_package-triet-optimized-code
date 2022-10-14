
package co.siten.myvtg.controller;

import java.io.File;
import java.io.FileOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.siten.myvtg.bean.DashboardInfoBean;
import co.siten.myvtg.bean.FileUploadBean;
import co.siten.myvtg.bean.MessageBean;
import co.siten.myvtg.service.DashboardService;
import co.siten.myvtg.service.FTPService;
import co.siten.myvtg.util.MessageUtil;

@RestController
@RequestMapping(value = "/api/${version}")
public class GeneralController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(GeneralController.class);
	@Autowired
	private DashboardService dashboardService;

	@Autowired
	private FTPService ftpService;

	@Autowired
	private MessageUtil messageUtil;

	@RequestMapping(value = "/dashboardInfo", method = RequestMethod.GET)
	public ResponseEntity<MessageBean> getDashboardInfo() {
		try {
			DashboardInfoBean data = dashboardService.getDashboardInfo();
			return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error("", e);
			return new ResponseEntity<MessageBean>(new MessageBean("500", e.getMessage()), HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value = "/getMessage", method = RequestMethod.GET)
	public ResponseEntity<MessageBean> getMessage(@RequestParam(value = "key", required = false) String key) {
		return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage(key)),HttpStatus.OK);
	}

	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessageBean> uploadImage(@Valid @RequestBody FileUploadBean bean) {

		try {
			String content = bean.getContent();
			if (content.contains(",")) {
				content = content.split(",")[1];
			}
			byte[] imageByte = Base64Utils.decodeFromString(content);

			File tempFile = File.createTempFile("myvtg", "image", null);
			FileOutputStream fos = new FileOutputStream(tempFile);
			fos.write(imageByte);
			String fileName = java.util.UUID.randomUUID().toString() + "." + bean.getExtension();

			String url = ftpService.uploadFileToFtp(tempFile, fileName, bean.getFolder());
			fos.close();
			return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), url), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("", e);
			return new ResponseEntity<MessageBean>(new MessageBean("200", e.getMessage()), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(ModelMap model, HttpServletRequest request) {
		boolean isAuthenticated = getPrincipal() != null ? true : false;
		if (isAuthenticated) {
			return "redirect:/";
		}
		return "login";
	}

}
