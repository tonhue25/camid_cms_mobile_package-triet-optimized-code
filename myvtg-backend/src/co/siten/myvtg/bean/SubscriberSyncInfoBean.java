/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.bean;

import java.util.Date;

/**
 *
 * @author LuatNC
 */
public class SubscriberSyncInfoBean {
        private String subServiceCode;
        private Date registerTime;
        private Date cancelTime;
        private int state;
        
        public SubscriberSyncInfoBean() {
        }

        public SubscriberSyncInfoBean(String subServiceCode, Date registerTime, Date cancelTime, int state) {
                super();
                this.subServiceCode = subServiceCode;
                this.registerTime = registerTime;
                this.cancelTime = cancelTime;
                this.state = state;
        }

        public String getSubServiceCode() {
                return subServiceCode;
        }

        public void setSubServiceCode(String subServiceCode) {
                this.subServiceCode = subServiceCode;
        }

        public Date getRegisterTime() {
                return registerTime;
        }

        public void setRegisterTime(Date registerTime) {
                this.registerTime = registerTime;
        }

        public Date getCancelTime() {
                return cancelTime;
        }

        public void setCancelTime(Date cancelTime) {
                this.cancelTime = cancelTime;
        }

        public int getState() {
                return state;
        }

        public void setState(int state) {
                this.state = state;
        }
}
