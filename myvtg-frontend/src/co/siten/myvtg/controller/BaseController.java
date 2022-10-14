package co.siten.myvtg.controller;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import co.siten.myvtg.bean.MessageBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.dao.UserDao;
import co.siten.myvtg.util.AES;
import co.siten.myvtg.validation.ValidationError;
import co.siten.myvtg.validation.ValidationErrorBuilder;

public class BaseController {
	private static final Logger logger = Logger.getLogger(BaseController.class);
	@Autowired
	UserDao userDao;
	@Autowired
	private HttpServletRequest context;

	public BaseController() {

	}

	public boolean isAdmin() {
		Object role = context.getHeaders("role");
		if (role != null && role.toString().equals("admin")) {
			return true;
		}
		return false;
	}

	public boolean isContentManager() {
		Object role = context.getHeaders("role");
		if (role != null && role.toString().equals("content-manager")) {
			return true;
		}
		return false;
	}

	public String getCurrentUserId() {
		return getPrincipal();
	}

	public String getPrincipal() {
		String userName = null;
		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			userName = "";
		} else {
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (principal instanceof UserDetails) {
				userName = ((UserDetails) principal).getUsername();
			}
		}
		return userName;
	}

	protected boolean validateRequest(RequestBean request) {
		String apiKey = request.getApiKey();
		try {
			StackTraceElement stackTraceElements = Thread.currentThread().getStackTrace()[2];
			logger.info("APIKEY: " + apiKey);
			Method method = Class.forName(stackTraceElements.getClassName())
					.getMethod(stackTraceElements.getMethodName());

			String value = method.getAnnotation(RequestMapping.class).value()[0];
			value = value.substring(1, value.length());
			String methodStr = (method.getAnnotation(RequestMapping.class).method()[0]).name();
			String output = AES.encrypt(value + "." + methodStr); // encrypt
			return apiKey.equalsIgnoreCase(output);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ValidationError handleException(MethodArgumentNotValidException exception) {
		return createValidationError(exception);
	}

	private ValidationError createValidationError(MethodArgumentNotValidException e) {

		return ValidationErrorBuilder.fromBindingErrors(e.getBindingResult());
	}

	protected ResponseEntity<MessageBean> responseSuccess(String code, String content, HttpStatus status) {

		return new ResponseEntity<MessageBean>(new MessageBean(code, content), status);
	}
}
