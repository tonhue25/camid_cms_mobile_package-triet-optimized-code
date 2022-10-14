package co.siten.myvtg.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Date;
import java.util.List;

@JsonInclude(Include.NON_NULL)
public class MetfoneBean {

    private String prefix;
    private String fee;
    private String commitDuration;
    private Integer total;
    private String totalPage;
    private String totalSize;
    private Long register;
    private Long permission;
    private Long isNeedUpdateInfo;
    private String contentMsg;
    private String authenkey;
    private String linkGame;
    private String fbShare;
    private Long type;
    private Long status;
    private String totalAmount;
    private Date lastUpdate;
    private Object category;
    private String captchaCode;
    private String timeOut;
    private Long isTiktok;
    private Long isSabay;
    private String policy;
    private String totalSucc;
    private String totalErr;
    private String pinCode;
    private String numSpin;
    private List<Object> lstNumberToBuy;
    private List<Object> lstOrderedNumber;
    private List<Object> lstHisTopup;
    private List<Object> resultList;
    private List<Object> listGift;
    private List<Object> listLoyaltyTelcoGift;
    private List<IsdnInfoBean> listAccountPoint;
    private List<Object> listMember;
    private List<Object> listService;

    private List<Object> lisDonateEmoney;
    private List<Object> lisDonateMocha;

    public String getNumSpin() {
        return numSpin;
    }

    public void setNumSpin(String numSpin) {
        this.numSpin = numSpin;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public List<Object> getLstNumberToBuy() {
        return lstNumberToBuy;
    }

    public void setLstNumberToBuy(List<Object> lstNumberToBuy) {
        this.lstNumberToBuy = lstNumberToBuy;
    }

    public List<Object> getLstOrderedNumber() {
        return lstOrderedNumber;
    }

    public void setLstOrderedNumber(List<Object> lstOrderedNumber) {
        this.lstOrderedNumber = lstOrderedNumber;
    }

    public List<Object> getLstHisTopup() {
        return lstHisTopup;
    }

    public void setLstHisTopup(List<Object> lstHisTopup) {
        this.lstHisTopup = lstHisTopup;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public String getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(String totalSize) {
        this.totalSize = totalSize;
    }

    public Long getRegister() {
        return register;
    }

    public void setRegister(Long register) {
        this.register = register;
    }

    public List<Object> getResultList() {
        return resultList;
    }

    public void setResultList(List<Object> resultList) {
        this.resultList = resultList;
    }

    public Long getPermission() {
        return permission;
    }

    public void setPermission(Long permission) {
        this.permission = permission;
    }

    public String getAuthenkey() {
        return authenkey;
    }

    public void setAuthenkey(String authenkey) {
        this.authenkey = authenkey;
    }

    public List<Object> getListLoyaltyTelcoGift() {
        return listLoyaltyTelcoGift;
    }

    public void setListLoyaltyTelcoGift(List<Object> listLoyaltyTelcoGift) {
        this.listLoyaltyTelcoGift = listLoyaltyTelcoGift;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public List<Object> getListGift() {
        return listGift;
    }

    public void setListGift(List<Object> listGift) {
        this.listGift = listGift;
    }

    public List<IsdnInfoBean> getListAccountPoint() {
        return listAccountPoint;
    }

    public void setListAccountPoint(List<IsdnInfoBean> listAccountPoint) {
        this.listAccountPoint = listAccountPoint;
    }

    public String getLinkGame() {
        return linkGame;
    }

    public void setLinkGame(String linkGame) {
        this.linkGame = linkGame;
    }

    public String getFbShare() {
        return fbShare;
    }

    public void setFbShare(String fbShare) {
        this.fbShare = fbShare;
    }

    public String getCommitDuration() {
        return commitDuration;
    }

    public void setCommitDuration(String commitDuration) {
        this.commitDuration = commitDuration;
    }

    public Long getIsNeedUpdateInfo() {
        return isNeedUpdateInfo;
    }

    public void setIsNeedUpdateInfo(Long isNeedUpdateInfo) {
        this.isNeedUpdateInfo = isNeedUpdateInfo;
    }

    public String getContentMsg() {
        return contentMsg;
    }

    public void setContentMsg(String contentMsg) {
        this.contentMsg = contentMsg;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }


    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Object> getLisDonateEmoney() {
        return lisDonateEmoney;
    }

    public void setLisDonateEmoney(List<Object> lisDonateEmoney) {
        this.lisDonateEmoney = lisDonateEmoney;
    }

    public List<Object> getLisDonateMocha() {
        return lisDonateMocha;
    }

    public void setLisDonateMocha(List<Object> lisDonateMocha) {
        this.lisDonateMocha = lisDonateMocha;
    }

    public Object getCategory() {
        return category;
    }

    public void setCategory(Object category) {
        this.category = category;
    }
    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public Long getIsTiktok() {
        return isTiktok;
    }

    public void setIsTiktok(Long isTiktok) {
        this.isTiktok = isTiktok;
    }


    public Long getIsSabay() {
        return isSabay;
    }

    public void setIsSabay(Long isSabay) {
        this.isSabay = isSabay;
    }

    public List<Object> getListMember() {
        return listMember;
    }

    public void setListMember(List<Object> listMember) {
        this.listMember = listMember;
    }

    public List<Object> getListService() {
        return listService;
    }

    public void setListService(List<Object> listService) {
        this.listService = listService;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getTotalSucc() {
        return totalSucc;
    }

    public void setTotalSucc(String totalSucc) {
        this.totalSucc = totalSucc;
    }

    public String getTotalErr() {
        return totalErr;
    }

    public void setTotalErr(String totalErr) {
        this.totalErr = totalErr;
    }

}
