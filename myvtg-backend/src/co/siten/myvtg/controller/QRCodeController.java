package co.siten.myvtg.controller;

import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.bean.ResponseBean;
import co.siten.myvtg.bean.ResponseErrorBean;
import co.siten.myvtg.bean.ResponseSuccessBean;
import co.siten.myvtg.service.AccountService;
import co.siten.myvtg.service.LoyaltyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RestController
@RequestMapping("/api/${version}/accounts")
public class QRCodeController extends BaseController{
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    LoyaltyService loyaltyService;

    @RequestMapping(value = "/wsGenerateQRCode", method = RequestMethod.POST)
    public ResponseEntity<ResponseBean> wsGenerateQRCode(@RequestBody RequestBean request) {
        try {
         

            ResponseSuccessBean bean = new ResponseSuccessBean();
            String isdn = request.getWsRequest().get("isdn").toString();
            String qrCode = loyaltyService.generateQrCodeByIsdn(isdn);

            LinkedHashMap<String, Object> response = new LinkedHashMap<>();
            if (qrCode != null && !qrCode.trim().isEmpty()) {
                response.put("qrCode", qrCode);
                bean.setWsResponse(response);
            }

            return responseSuccessNoLog(request, bean);
        } catch (Exception e) {
            logger.error("", e);
            return responseError(request, e);
        }
    }
}
