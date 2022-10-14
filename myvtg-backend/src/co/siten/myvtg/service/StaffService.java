package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;

public interface StaffService {

    BaseResponseBean getStaffs(RequestBean request, String language);
}
