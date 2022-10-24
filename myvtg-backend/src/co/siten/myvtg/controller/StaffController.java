package co.siten.myvtg.controller;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.service.StaffService;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.ResponseUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/${version}/staff")
public class StaffController extends BaseController {

    @Autowired
    private StaffService staffService;

    @Autowired
    ResponseUtil responseUtil;

    @RequestMapping(value = "wsGetStaffs", method = RequestMethod.POST)
    public ResponseEntity<?> wsGetStaffs(@RequestBody RequestBean request) {
        String language = "en";
        try {
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            BaseResponseBean bean = staffService.getStaffs(request, language);
            return baseResponse(request, bean);
        } catch (Exception ex) {
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "ex.des.fai.", "ex.common.system.error.", language));
        }
    }

    @RequestMapping(value = "wsAddStaff", method = RequestMethod.POST)
    public ResponseEntity<?> wsAddStaff(@RequestBody RequestBean request) {
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.error.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
//            if(DataUtil.isNullObject(authorizedToken(request))){
//                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_UNAUTHORIZED, "ex.des.fai.", "ex.common.authorize.error.", language));
//            }
            BaseResponseBean bean = staffService.addStaff(request, language);
            return baseResponse(request, bean);
        } catch (Exception ex) {
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "ex.des.fai.", "ex.common.system.error.", language));
        }
    }

    @RequestMapping(value = "wsDeleteStaff", method = RequestMethod.POST)
    public ResponseEntity<?> wsDeleteStaff(@RequestBody RequestBean request) {
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.error.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            BaseResponseBean bean = staffService.deleteStaff(request, language);
            return baseResponse(request, bean);
        } catch (Exception ex) {
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "ex.des.fai.", "ex.common.system.error.", language));
        }
    }

    @RequestMapping(value = "wsGetStaffByPhone", method = RequestMethod.POST)
    public ResponseEntity<?> wsGetStaffByPhone(@RequestBody RequestBean request) {
        String language = "en";
        try {
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.error.", language));
            }
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            BaseResponseBean bean = staffService.getStaffByPhone(request, language);
            return baseResponse(request, bean);
        } catch (Exception ex) {
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "ex.des.fai.", "ex.common.system.error.", language));
        }
    }

    @RequestMapping(value = "wsExportExcel", method = RequestMethod.POST)
    public ResponseEntity<?> wsExportExcel(@RequestBody RequestBean request, HttpServletResponse response) {
        String language = "en";
        try {
            response.setHeader("Content-Disposition", "attachment; filename=agents.xlsx");
            if (DataUtil.isNullObject(request) || DataUtil.isNullOrEmpty(request.getWsRequest())) {
                return baseResponse(request, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.error.", language));
            }
            BaseResponseBean bean = staffService.excelExport(language, response, request);
            return baseResponse(request, bean);
        } catch (Exception ex) {
            return baseResponse(responseUtil.responseBean(Constants.ERROR_SYSTEM, "ex.des.fai.", "ex.common.system.error.", language));
        }
    }

    @RequestMapping(value = "wsImportExcel", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity<?> wsImportExcel(@RequestParam MultipartFile multipartFile) {
        String language = "en";
        try {
            if (DataUtil.isNullObject(multipartFile)) {
                return baseResponse(multipartFile, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.error.", language));
            }
            BaseResponseBean bean = staffService.excelImport(multipartFile, language);
            return baseResponse(bean);
        } catch (Exception ex) {
            return baseResponse(responseUtil.responseBean(Constants.ERROR_SYSTEM, "ex.des.fai.", "ex.common.system.error.", language));
        }
    }

    @RequestMapping(value = "wsImportCSV", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity<?> wsImportCSV(@RequestParam MultipartFile multipartFile) {
        String language = "en";
        try {
            if (DataUtil.isNullObject(multipartFile)) {
                return baseResponse(multipartFile, responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, "ex.des.fai.", "ex.common.parameter.error.", language));
            }
            BaseResponseBean bean = staffService.csvImport(multipartFile, language);
            return baseResponse(bean);
        } catch (Exception ex) {
            return baseResponse(responseUtil.responseBean(Constants.ERROR_SYSTEM, "ex.des.fai.", "ex.common.system.error.", language));
        }
    }
}
