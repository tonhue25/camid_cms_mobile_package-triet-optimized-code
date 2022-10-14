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
public class SubRelProductBean {
        private Long subId;
        private Date staDate;
        private Date regDate;
        private Date cancelDate;
        private String relProductCode;
        private Integer status;

        public SubRelProductBean(Long subId, Date staDate, Date regDate, Date cancelDate, String relProductCode, Integer status) {
                this.subId = subId;
                this.staDate = staDate;
                this.regDate = regDate;
                this.cancelDate = cancelDate;
                this.relProductCode = relProductCode;
                this.status = status;
        }

        public Long getSubId() {
                return subId;
        }

        public void setSubId(Long subId) {
                this.subId = subId;
        }

        public Date getStaDate() {
                return staDate;
        }

        public void setStaDate(Date staDate) {
                this.staDate = staDate;
        }

        public Date getRegDate() {
                return regDate;
        }

        public void setRegDate(Date regDate) {
                this.regDate = regDate;
        }

        public Date getCancelDate() {
                return cancelDate;
        }

        public void setCancelDate(Date cancelDate) {
                this.cancelDate = cancelDate;
        }

        public String getRelProductCode() {
                return relProductCode;
        }

        public void setRelProductCode(String relProductCode) {
                this.relProductCode = relProductCode;
        }

        public Integer getStatus() {
                return status;
        }

        public void setStatus(Integer status) {
                this.status = status;
        }
        
}
