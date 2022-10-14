package co.siten.myvtg.controller;

import co.siten.myvtg.bean.MessageBean;
import co.siten.myvtg.bean.ReportNotiCustomer;
import co.siten.myvtg.bean.ReportNotificationCampaign;
import co.siten.myvtg.bean.ServiceGroupTypeBean;
import co.siten.myvtg.dto.RequestDto;
import co.siten.myvtg.model.apigw.Notification;
import co.siten.myvtg.service.INotificationService;
import co.siten.myvtg.util.ApproveWrapper;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.DataUtil;
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
import java.util.List;

@RestController
@RequestMapping(value = "/api/${version}")
public class NotificationController extends BaseController {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(NotificationController.class);
    @Autowired
    private INotificationService service;

    @Autowired
    private MessageUtil messageUtil;

    @RequestMapping(value = "/notifications/list", method = RequestMethod.GET)
    public List<Notification> getList() {
        return service.getList();
    }

    @RequestMapping(value = "/notifications", method = RequestMethod.GET)
    public Page<Notification> searchData(@RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "20") int size,
            @RequestParam(value = "sortBy", required = false, defaultValue = "startTime") String sortBy,
            @RequestParam(value = "sortType", required = false, defaultValue = "1") int sortType,
            @RequestParam(value = "startTime", required = false) String startTime,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "title", required = false) String title) {
        return service.findPaginated(page, size, sortBy, sortType, startTime, status, title);
    }

    @RequestMapping(value = "/notifications/type", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageBean> get() {
        ServiceGroupTypeBean data = service.getNotificationType();
        if (data == null) {
            return new ResponseEntity<MessageBean>(new MessageBean("404", ""), HttpStatus.OK);
        }
        return new ResponseEntity<MessageBean>(new MessageBean("", "", data), HttpStatus.OK);
    }

    @RequestMapping(value = "/notifications/get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageBean> get(@RequestParam(value = "id", required = true) Long id) {
        Notification data = service.findById(id);
        if (data == null) {
            return new ResponseEntity<MessageBean>(new MessageBean("404", ""), HttpStatus.OK);
        }
        return new ResponseEntity<MessageBean>(new MessageBean("", "", data), HttpStatus.OK);
    }

    @RequestMapping(value = "/notifications", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<MessageBean> create(@Valid @RequestBody Notification inputData) {
        try {
            Notification data = new Notification();
            // set info from inputData into data
            data.setId(data.getId());
            data.setEndTime(inputData.getEndTime());
            data.setStartTime(inputData.getStartTime());
            data.setIcon(inputData.getIcon());
            data.setImage(inputData.getImage());
            data.setCreatedTime(CommonUtil.getCurrentTime());
            data.setCreatedBy(null);
            data.setLastDeviceTokenId(null);
            data.setListTestPhone(inputData.getListTestPhone());
            //ata.setNotificationType(inputData.getNotificationType());
            //daibq. tam thoi de mac dinh type = 5 (hien thi chi tiet)
            data.setNotificationType("5");
            data.setStatus(1);
            data.setMessage(inputData.getMessage());
            data.setLastUpdatedBy(null);
            data.setLastUpdatedTime(CommonUtil.getCurrentTime());
            data.setServiceCode(inputData.getServiceCode());
            data.setServiceName(inputData.getServiceName());
            data.setTitle(inputData.getTitle());
            data.setIsSave(inputData.getIsSave());
            data.setNotificationProgramType(inputData.getNotificationProgramType());
            data.setCode(inputData.getCode());
            data.setContentFromCampaign(inputData.getContentFromCampaign());

            service.save(data);
//			saveLog(null, data);
            return new ResponseEntity<MessageBean>(new MessageBean("", "", data), HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("", e);
            return new ResponseEntity<MessageBean>(new MessageBean("500", e.getMessage()), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/notifications", method = RequestMethod.PUT)
    public ResponseEntity<MessageBean> update(@Valid @RequestBody Notification inputData) {
        try {
            Notification data = service.findById(inputData.getId());
            if (data == null) {
                return new ResponseEntity<MessageBean>(new MessageBean("404", ""), HttpStatus.OK);
            }
//			saveLog(data, inputData);
            // set info from inputData into data
            data.setId(data.getId());
            data.setEndTime(inputData.getEndTime());
            data.setStartTime(inputData.getStartTime());
            data.setIcon(inputData.getIcon());
            data.setImage(inputData.getImage());
            data.setLastDeviceTokenId(null);
            data.setListTestPhone(inputData.getListTestPhone());
            data.setNotificationType(inputData.getNotificationType());
            data.setMessage(inputData.getMessage());
            data.setLastUpdatedBy(null);
            data.setLastUpdatedTime(CommonUtil.getCurrentTime());
            data.setServiceCode(inputData.getServiceCode());
            data.setServiceName(inputData.getServiceName());
            data.setTitle(inputData.getTitle());
            data.setIsSave(inputData.getIsSave());
            data.setNotificationProgramType(inputData.getNotificationProgramType());
            data.setStatus(inputData.getStatus());
            data.setCode(inputData.getCode());
            if (inputData.getNotificationProgramType() == 0) {
                data.setContentFromCampaign(0);
            } else {
                data.setContentFromCampaign(inputData.getContentFromCampaign());
            }
            data.setTest_status(inputData.getTest_status());
            service.save(data);

            return new ResponseEntity<MessageBean>(new MessageBean("", messageUtil.getMessage(""), data),
                    HttpStatus.OK);
        } catch (Exception e) {
            logger.error("", e);
            return new ResponseEntity<MessageBean>(new MessageBean("500", e.getMessage()), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/notifications", method = RequestMethod.DELETE)
    public ResponseEntity<MessageBean> delete(@RequestParam(value = "id", required = true) Long id) {
        Notification data = service.findById(id);
        if (data == null) {
            return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("notFound")),
                    HttpStatus.OK);
        }
        service.delete(data);
//		saveLog(data, null);
        return new ResponseEntity<MessageBean>(HttpStatus.OK);
    }

    @RequestMapping(value = "/notifications/approve", method = RequestMethod.PUT)
    public ResponseEntity<MessageBean> approve(@RequestBody ApproveWrapper approve) {

        service.Approve(approve.forAll, approve.ids, approve.active);

        return new ResponseEntity<MessageBean>(HttpStatus.OK);
    }

    @RequestMapping(value = "/notifications/reportByCampaign", method = RequestMethod.GET)
    public List<ReportNotificationCampaign> reportCampaign(@RequestParam(name = "code", required = false) String code,
            @RequestParam(name = "fromDate", required = false) String fromDate,
            @RequestParam(name = "toDate", required = false) String toDate) {
        return service.reportNotiForCamp(code, fromDate, toDate);
    }

    @RequestMapping(value = "/notifications/reportByCustomerUsed", method = RequestMethod.GET)
    public List<ReportNotiCustomer> reportByCustomerUsed(@RequestParam(name = "isdn", required = false) String isdn,
            @RequestParam(name = "fromDate", required = false) String fromDate,
            @RequestParam(name = "toDate", required = false) String toDate) {
        return service.reportNotiCus(isdn, fromDate, toDate);
    }

    @RequestMapping(value = "/notifications/checkCode", method = RequestMethod.GET)
    public Boolean checkCode(@RequestParam(name = "code", required = true) String code,
            @RequestParam(name = "id", required = false) Long id) {
        return service.checkIsExistCode(code, id);
    }

    @RequestMapping(value = "/wsPushNotifications", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageBean> pushNo(@RequestBody RequestDto request) {
        logger.error("Start pushNo API of NotificationController ");
        try {
            String language = "en";
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getIsdns()) || DataUtil.isNullOrEmpty(request.getIdNo())) {
                logger.error("Error isdn or idNo is null or empty ");
                return new ResponseEntity<MessageBean>(new MessageBean("404", messageUtil.getMessage("myMetfone.webServices.empty." + language)),
                        HttpStatus.OK);
            }
            service.pushNo(request.getIsdns().split(","), Long.parseLong(request.getIdNo()));
            return new ResponseEntity<MessageBean>(new MessageBean("200", messageUtil.getMessage("myMetfone.Ishare.des.succ." + language)),
                    HttpStatus.OK);
        } catch (Exception e) {
            logger.error("", e);
            return new ResponseEntity<MessageBean>(new MessageBean("500", e.getMessage()), HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/wsGetListNoForPush", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Notification> wsGetListNoForPush() {
        logger.error("Start wsGetListNoForPush API of NotificationController ");
        return service.getListNoForPush();
    }

}
