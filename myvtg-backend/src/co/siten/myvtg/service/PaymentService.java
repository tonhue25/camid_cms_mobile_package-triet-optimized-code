package co.siten.myvtg.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import co.siten.myvtg.dao.PaymentDao;
import co.siten.myvtg.model.myvtg.Sub;
import co.siten.myvtg.model.payment.DebitSub;

@org.springframework.stereotype.Service("PaymentService")
@Transactional(rollbackFor = Exception.class, value = "paymenttransaction")
public class PaymentService {
	@Autowired
	PaymentDao paymentDao;

	public BigDecimal callGetHotChargeSub(long subId) {
		return paymentDao.callGetHotChargeSub(subId);
	}

	public BigDecimal getDevPostByIsdn(String isdn) {
		return paymentDao.getDevPostByIsdn(isdn);
	}

	public BigDecimal getDebPreMonthPostByIsdn(String isdn) {
		return paymentDao.getDebPreMonthPostByIsdn(isdn);
	}

	public BigDecimal getDataRemainForPostPaidMetfone(long subId, List<String> productCodeList) {
		return paymentDao.getDataRemainForPostPaidMetfone(subId, productCodeList);
	}

	public BigDecimal getDataRemainForPostPaid(long subId, String productCodeList) {
		return paymentDao.getDataRemainForPostPaid(subId, productCodeList);
	}

	public List<Object[]> getListDataRemainForPostPaidMetfone(long subId, List<String> productCodeList) {
		return paymentDao.getListDataRemainForPostPaidMetfone(subId, productCodeList);
	}

	public List<Object[]> getListDataRemainForPostPaid(long subId, String productCodeList) {
		return paymentDao.getListDataRemainForPostPaid(subId, productCodeList);
	}

	public DebitSub getDevPostByIsdn(Sub sub) {
		return paymentDao.getDevPostByIsdn(sub);
	}

}
