/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.model.payment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author LuatNC
 */
@Entity
@Table(name="DEBIT_SUB")
@NamedQuery(name="DebitSub.findAll", query="SELECT v FROM DebitSub v")
public class DebitSub implements Serializable {
        private static final long serialVersionUID = 1L;
    
        @Id
        @Column(name="SUB_ID")
	private String subId;
        
        @Temporal(TemporalType.DATE)
	@Column(name="BILL_CYCLE")
	private Date billCycle;
        
        @Column(name="STA_OF_CYCLE")
	private BigDecimal staOfCycle;
        
        @Column(name="TOT_CHARGE")
	private BigDecimal totCharge;
        
        @Column(name="PAYMENT")
	private BigDecimal payment;
        
        @Column(name="PROMOTION")
	private BigDecimal promotion;
        
        @Column(name="DISCOUNT")
	private BigDecimal discount;

        public DebitSub() {
        }

        public String getSubId() {
                return subId;
        }

        public void setSubId(String subId) {
                this.subId = subId;
        }

        public Date getBillCycle() {
                return billCycle;
        }

        public void setBillCycle(Date billCycle) {
                this.billCycle = billCycle;
        }

        public BigDecimal getStaOfCycle() {
                return staOfCycle;
        }

        public void setStaOfCycle(BigDecimal staOfCycle) {
                this.staOfCycle = staOfCycle;
        }

        public BigDecimal getTotCharge() {
                return totCharge;
        }

        public void setTotCharge(BigDecimal totCharge) {
                this.totCharge = totCharge;
        }

        public BigDecimal getPayment() {
                return payment;
        }

        public void setPayment(BigDecimal payment) {
                this.payment = payment;
        }

        public BigDecimal getPromotion() {
                return promotion;
        }

        public void setPromotion(BigDecimal promotion) {
                this.promotion = promotion;
        }

        public BigDecimal getDiscount() {
                return discount;
        }

        public void setDiscount(BigDecimal discount) {
                this.discount = discount;
        }
}
