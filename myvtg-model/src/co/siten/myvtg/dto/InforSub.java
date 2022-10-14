/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dto;

import co.siten.myvtg.dto.DataInfo;
import java.util.HashMap;
import java.sql.Timestamp;
/**
 *
 * @author daibq
 */
public class InforSub {

    private String basic = "0"; // usd
    private String promotion = "0"; // usd
    private String lastCharge;
    private String nextCharge;
    private String expireDateAddOn;
    private String expireDateYoutube;
    private boolean statusMi;
    private boolean statusAddOn;
    private boolean statusYoutube;
    private HashMap<String, DataInfo> mDataInfo;
    private String productMi;
    private String productAddOn;
    private String productYoutube;
    private String[] lstBalance;
    private String[] lstExpire;
    private String[] lstPrice;
    private String []lstPricePlan;
    private Timestamp activeStop;
    @Override
    public String toString() {
        StringBuilder br = new StringBuilder();
        br.append("\n<BASIC>").append(basic).append("</BASIC>");
        br.append("\n<PROMOTION>").append(promotion).append("</PROMOTION>");
        br.append("\n<LAST_CHARGE>").append(lastCharge).append("</LAST_CHARGE>");
        br.append("\n<NEXT_CHARGE>").append(nextCharge).append("</NEXT_CHARGE>");

        br.append("\n<DATA_DETAIL>");
        for (String key : mDataInfo.keySet()) {
            DataInfo detail = mDataInfo.get(key);
            br.append("\n\r\t<DATA_").append(key).append(">");
            br.append("\n\t\r\t\r<TOTAL>").append(detail.getTotal()).append("</TOTAL>");
            br.append("\n\t\r\t\r<DETAIL>").append(detail.getDetail()).append("</TOTAL>");
            br.append("\n\r\t</DATA_").append(key).append(">");
        }
        br.append("\n</DATA_DETAIL>");

        return br.toString();
    }

    public Timestamp getActiveStop() {
        return activeStop;
    }

    public void setActiveStop(Timestamp activeStop) {
        this.activeStop = activeStop;
    }

    public String[] getLstPricePlan() {
        return lstPricePlan;
    }

    public void setLstPricePlan(String[] lstPricePlan) {
        this.lstPricePlan = lstPricePlan;
    }

   

    public String[] getLstBalance() {
        return lstBalance;
    }

    public void setLstBalance(String[] lstBalance) {
        this.lstBalance = lstBalance;
    }

    public String[] getLstExpire() {
        return lstExpire;
    }

    public void setLstExpire(String[] lstExpire) {
        this.lstExpire = lstExpire;
    }

    public String[] getLstPrice() {
        return lstPrice;
    }

    public void setLstPrice(String[] lstPrice) {
        this.lstPrice = lstPrice;
    }

    public boolean isStatusMi() {
        return statusMi;
    }

    public void setStatusMi(boolean statusMi) {
        this.statusMi = statusMi;
    }

    public boolean isStatusAddOn() {
        return statusAddOn;
    }

    public void setStatusAddOn(boolean statusAddOn) {
        this.statusAddOn = statusAddOn;
    }

    public boolean isStatusYoutube() {
        return statusYoutube;
    }

    public void setStatusYoutube(boolean statusYoutube) {
        this.statusYoutube = statusYoutube;
    }

    public String getExpireDateAddOn() {
        return expireDateAddOn;
    }

    public void setExpireDateAddOn(String expireDateAddOn) {
        this.expireDateAddOn = expireDateAddOn;
    }

    public String getExpireDateYoutube() {
        return expireDateYoutube;
    }

    public void setExpireDateYoutube(String expireDateYoutube) {
        this.expireDateYoutube = expireDateYoutube;
    }

    public String getProductAddOn() {
        return productAddOn;
    }

    public void setProductAddOn(String productAddOn) {
        this.productAddOn = productAddOn;
    }

    public String getProductYoutube() {
        return productYoutube;
    }

    public void setProductYoutube(String productYoutube) {
        this.productYoutube = productYoutube;
    }

    public String getProductMi() {
        return productMi;
    }

    public void setProductMi(String productMi) {
        this.productMi = productMi;
    }

    public String getBasic() {
        return basic;
    }

    public void setBasic(String basic) {
        this.basic = basic;
    }

    public String getLastCharge() {
        return lastCharge;
    }

    public void setLastCharge(String lastCharge) {
        this.lastCharge = lastCharge;
    }

    public HashMap<String, DataInfo> getmDataInfo() {
        return mDataInfo;
    }

    public void setmDataInfo(HashMap<String, DataInfo> mDataInfo) {
        this.mDataInfo = mDataInfo;
    }

    public String getNextCharge() {
        return nextCharge;
    }

    public void setNextCharge(String nextCharge) {
        this.nextCharge = nextCharge;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }
}
