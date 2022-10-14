package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;

public interface EmoneyService {

    public BaseResponseBean getAccountInfo(RequestBean request, String language);

    public BaseResponseBean confirm(RequestBean request, String language);

    public BaseResponseBean resendOtp(RequestBean request, String language);
}
