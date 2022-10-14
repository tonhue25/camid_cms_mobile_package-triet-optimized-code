package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;

public interface AuthService {

    BaseResponseBean signIn(RequestBean requestBean, String language);
}
