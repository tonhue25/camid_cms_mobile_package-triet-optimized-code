package co.siten.myvtg.controller;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.service.StaffService;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.ResponseUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/${version}/staff")
public class StaffController extends BaseController{

    private static final Logger logger = Logger.getLogger(ServiceGroupController.class);

    @Autowired
    private StaffService staffService;

    @Autowired
    ResponseUtil responseUtil;

    @RequestMapping(value = "wsGetStaffs", method = RequestMethod.POST)
    public ResponseEntity<?> wsGetStaffs(@RequestBody RequestBean request) {
        logger.info("### Start wsGetStaffs API of StaffController");
        String language = "en";
        try {
            language = request.getWsRequest().containsKey("language") ? request.getWsRequest().get("language").toString() : language;
            BaseResponseBean bean = staffService.getStaffs(request, language);
            logger.info("### End wsGetStaffs API of StaffController");
            return baseResponse(request, bean);
        } catch (Exception ex) {
            logger.error("Exception: ", ex);
            return baseResponse(request, responseUtil.responseBean(Constants.ERROR_SYSTEM, "ex.des.fai.", "ex.common.system.error.", language));
        }
    }
}
