package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.dto.PackageInforDTO;
import co.siten.myvtg.dto.PackageInternetDTO;
import co.siten.myvtg.dto.ResponseDto;
import org.apache.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author daibq
 */
public interface MyMetfoneBusinessService {

    public BaseResponseBean getOTPByService(RequestBean request, String language);

    public BaseResponseBean getOTPIshare(RequestBean request, String language);

    public BaseResponseBean getOTPKeepChangeSim(RequestBean request, String language);

    public BaseResponseBean confirmOTP(RequestBean request, String language);

    public BaseResponseBean ishareTranfer(RequestBean request, String language);

    public BaseResponseBean changeIsdnKeepSim(RequestBean request, String language);

    public BaseResponseBean getListNumberToBuy(RequestBean request, String language);

    public BaseResponseBean getListOrderNumberHistory(RequestBean request, String language);

    public BaseResponseBean lockIsdnToBuy(RequestBean request, String language);

    public BaseResponseBean checkPackageByISDN(RequestBean request, String language);

    public BaseResponseBean chargeHisInMonth(RequestBean request, String language);

    public BaseResponseBean syncIsdnInfo(RequestBean request, String language);

    public BaseResponseBean wsForgotPassword(RequestBean request, String language);

    public BaseResponseBean getAllUserServicesByIsdn(RequestBean request, String language);

    public BaseResponseBean checkUseMetfoneCare(RequestBean request, String language);

    public BaseResponseBean sendEmail(RequestBean request, String language);

    public ResponseDto inviteUseMetfone(RequestBean request, String language);

    public ResponseDto doRedeemInviteOrLoginFirst(RequestBean request, String language);

    public ResponseDto checkLogin(RequestBean request, String language);

    public ResponseDto validateRedeemInviteOrLoginFirst(RequestBean request, String language);

    public BaseResponseBean checkUpdateInfomation(RequestBean request, String language);

    public BaseResponseBean checkIsNiceNumber(RequestBean request, String language);

    public BaseResponseBean getComType(RequestBean request, String language);

    public BaseResponseBean getComTypeCamID(RequestBean request, String language);

    public BaseResponseBean submitComplaintMyMetfone(RequestBean request, String language);

    public BaseResponseBean getProcessList(RequestBean request, String language);

    public BaseResponseBean getProcessListCamID(RequestBean request, String language);

    public BaseResponseBean getComplaintHistory(RequestBean request, String language);

    public BaseResponseBean reopenComplain(RequestBean request, String language);

    public BaseResponseBean closeComplain(RequestBean request, String language);

    public BaseResponseBean rateComplaint(RequestBean request, String language);//

    public BaseResponseBean getTotalDonateCovid(RequestBean request, String language);

    public BaseResponseBean getDonateEmoney(RequestBean request, String language);

    public BaseResponseBean getDonateMocha(RequestBean request, String language);

    public BaseResponseBean wsGetCategoryNews(RequestBean request, String language);

    public BaseResponseBean getCaptcha(RequestBean request, String language);

    public BaseResponseBean checkAddMemberSabay(RequestBean request, String language);

    public BaseResponseBean getListMemberSabay(RequestBean request, String language);

    public BaseResponseBean addMemberSayBay(RequestBean request, String language);

    public BaseResponseBean removeMemberSabay(RequestBean request, String language);

    public BaseResponseBean checkAddMemberTiktok(RequestBean request, String language);

    public BaseResponseBean getListMemberTiktok(RequestBean request, String language);

    public BaseResponseBean addMemberTiktok(RequestBean request, String language);

    public BaseResponseBean removeMemberSabayTiktok(RequestBean request, String language);

    public BaseResponseBean getConsultant(RequestBean request, String language);

    public BaseResponseBean wsPayAdvance(RequestBean request, String language);

    public BaseResponseBean getNewsCovid19List(RequestBean request, String language);

    public BaseResponseBean addNewsCovid19(RequestBean request, String language);

    public BaseResponseBean updateNewsCovid19(RequestBean request, String language);

    public BaseResponseBean deleteNewsCovid19(RequestBean request, String language);

    public BaseResponseBean getDetailNewsCovid19(RequestBean request, String language);

    public BaseResponseBean checkResgisterMyMetfone(RequestBean request, String language);

    public BaseResponseBean getListCodeOnFiredBaseConsole(RequestBean request, String language);

    public BaseResponseBean getSubInfo(RequestBean request, String language);

    public BaseResponseBean getListProvince(String language);

    public BaseResponseBean getListDistrict(String provinceCode, String language);

    public BaseResponseBean getListCommune(String provinceCode, String districtCode, String language);

    public BaseResponseBean getPackageInfor(Long month, String language);

    public BaseResponseBean camIdInternetRegister(RequestBean request, String language, HttpServletRequest http);

    public PackageInforDTO getPackageInternetById(Long id);

    public BaseResponseBean getAccountInfoForPayment(RequestBean request, String language);

    public BaseResponseBean getPaymentHistoryById(RequestBean request, String language);

    public BaseResponseBean addPaymentHistory(RequestBean request, String language);

    public BaseResponseBean updatePaymentHistory(RequestBean request, String language);

    public BaseResponseBean getSummaryPackage(String language);

    public BaseResponseBean filterComplaint(RequestBean request, String language);

    public BaseResponseBean getListAccountFtthByIsdn(RequestBean request, String language);

    public BaseResponseBean getListComplaintType(RequestBean request, String language);

    public BaseResponseBean getListComplaintGroupType(RequestBean request, String language);

    public BaseResponseBean checkComplaintNotification(RequestBean request, String language);

    public BaseResponseBean updateComplaint(RequestBean request, String language);

    public BaseResponseBean ChangeCardAddNew(RequestBean request,String language);

    public BaseResponseBean exChangeCardGetlist(RequestBean request, String language);

}
