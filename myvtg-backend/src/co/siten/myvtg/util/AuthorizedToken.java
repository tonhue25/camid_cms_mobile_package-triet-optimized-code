package co.siten.myvtg.util;

import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


public class AuthorizedToken extends BaseController {
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    ResponseUtil responseUtil;

    public  ResponseEntity<?> authorizedToken(RequestBean requestBean, String language){
        String username = jwtUtils.loadUserNameFormJwtToken(requestBean.getToken());
        if(DataUtil.isNullOrEmpty(username)){
            return baseResponse(requestBean, responseUtil.responseBean(Constants.ERROR_UNAUTHORIZED, "ex.des.fai.", "ex.common.authorize.error.", language));
        }
        return null;
    }
}

