package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.model.myvtg.Agent;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface StaffService {

    BaseResponseBean getStaffs(RequestBean request, String language);

    BaseResponseBean addStaff(RequestBean requestBean, String language);

    BaseResponseBean deleteStaff(RequestBean requestBean, String language);

    BaseResponseBean getStaffByPhone(RequestBean request, String language);

    List<Agent> csvImport(InputStream is);

    BaseResponseBean excelImport(MultipartFile multipartFile, String language);

    BaseResponseBean csvImport(MultipartFile multipartFile, String language);

    BaseResponseBean excelExport(String language, HttpServletResponse response , RequestBean requestBean);
}
