

package co.siten.myvtg.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
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
import co.siten.myvtg.model.myvtg.AdvertBanner;
import co.siten.myvtg.model.myvtg.Hobby;
import co.siten.myvtg.model.myvtg.Job;
import co.siten.myvtg.service.IJobService;
import co.siten.myvtg.service.JobService;
import co.siten.myvtg.util.MessageUtil;
import co.siten.myvtg.util.CommonUtil;

@RestController
@RequestMapping(value = "/api/${version}")
public class JobController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(JobController.class);
	@Autowired
	private IJobService service;

	@Autowired
	private MessageUtil messageUtil;


	@RequestMapping(value = "/jobs", method = RequestMethod.GET)
	public Page<Job> search(
			@RequestParam(value = "page" , required = false, defaultValue ="0") int page,
			@RequestParam(value = "size", required = false, defaultValue ="5") int size,
			@RequestParam(value = "sortBy", required = false, defaultValue = "name") String sortBy,
			@RequestParam(value = "sortType", required = false, defaultValue = "0") int sortType,
			@RequestParam(value = "language", required = false) String language,
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {

		Page<Job> resultPage = service.findPaginated(page, size, sortBy, sortType,
				language, name);
		return resultPage;
	}


	@RequestMapping(value = "/jobs/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MessageBean> get(@RequestParam(value = "id", required = true) long id) {
		Job data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", ""),HttpStatus.OK);
		}
		return new ResponseEntity<MessageBean>(new MessageBean("", "",data), HttpStatus.OK);
	}


	@RequestMapping(value = "/jobs", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<MessageBean> create(@Valid @RequestBody Job inputData) {
		try {

			List<Job> job = service.findByNameAndLanguage(inputData.getName(),inputData.getLanguage());
			
			if (job.size() > 0) {
				return new ResponseEntity<MessageBean>(new MessageBean("1", messageUtil.getMessage("jobs.nameExisted")),HttpStatus.OK);
			}
			
			Job data = new Job();
			//set info from inputData into data
			data.setName(inputData.getName());
			data.setLanguage(inputData.getLanguage());
			data.setDes(inputData.getDes());
			
			service.save(data);

			return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data),HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("", e);
			return new ResponseEntity<MessageBean>(new MessageBean("500", e.getMessage()), HttpStatus.OK);
		}


	}


	@RequestMapping(value = "/jobs", method = RequestMethod.PUT)
	public ResponseEntity<MessageBean> update(@Valid @RequestBody Job inputData) {

		long id = inputData.getId();
		try {
		Job data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")),HttpStatus.OK);
		}
		
		
		List<Job> exists = service.findByNameAndLanguage(inputData.getName(),inputData.getLanguage());
		
		if (exists.size() > 0) {
			Job j = exists.get(0);
			if(j.getId() != inputData.getId()){
				return new ResponseEntity<MessageBean>(new MessageBean("1", messageUtil.getMessage("jobs.nameExisted")),HttpStatus.OK);
			}
		}
		
		
		// TODO setAll Information
		data.setName(inputData.getName());
		data.setLanguage(inputData.getLanguage());
		data.setDes(inputData.getDes());

		service.save(data);
		return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("", e);
			return new ResponseEntity<MessageBean>(new MessageBean("500", e.getMessage()), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/jobs", method = RequestMethod.DELETE)
	public ResponseEntity<MessageBean> delete(@RequestParam(value = "id" , required = true)  int id) {
		Job data = service.findById(id);
		if (data == null) {
			return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("")),HttpStatus.OK);
		}
		service.delete(data);
		
		return new ResponseEntity<MessageBean>(HttpStatus.OK);
	}

}
