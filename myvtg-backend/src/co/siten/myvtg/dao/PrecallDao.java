package co.siten.myvtg.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.bean.PostageInfoBean;
import co.siten.myvtg.util.Constants;

/**
 * 
 * @author thomc
 *
 */

@Qualifier("precalltx")
@Repository("PrecallDao")
public class PrecallDao extends AbstractPrecallDao<Object> {
	public List<co.siten.myvtg.bean.PostageDetailInfoBean> getPostageDetailInfoCall(String isdn, Date startTime,
			Date endTime, String countryCode, int size, int page, String order) {
		String selectResult = "SELECT new co.siten.myvtg.bean.PostageDetailInfoBean(s.calledNumber , " + " 0, "
				+ "s.startTime, s.duration, s.basicBalance, s.promBalance) ";
		String from = " FROM co.siten.myvtg.model.precall.VSelfcareDetailCall s "
				+ "WHERE (s.callingNumber= :isdn OR s.callingNumber= :msisdn) and s.startTime >= :startTime and s.startTime <= :endTime"
				+ " ORDER BY s.startTime desc ";

		Query query = getSession().createQuery(selectResult + from);
		query.setString("isdn", isdn);
		query.setString("msisdn", countryCode + isdn);
		query.setDate("startTime", startTime);
		query.setDate("endTime", endTime);
		query.setFirstResult((page - 1) * size);
		if (size > 0) {
			query.setMaxResults(size);
			query.setFirstResult((page - 1) * size);
		}
		List<co.siten.myvtg.bean.PostageDetailInfoBean> rList = query.list();
		return rList;
	}

	public List<co.siten.myvtg.bean.PostageDetailInfoBean> getPostageDetailInfoSms(String isdn, Date startTime,
			Date endTime, String countryCode, int size, int page, String order) {
		// Thay dữ liệu của câu lệnh select vào một đối tượng luôn.
		StringBuilder sb = new StringBuilder("SELECT new co.siten.myvtg.bean.PostageDetailInfoBean(" + "CASE "
				+ "WHEN ( s.callingNumber= :msisdn) THEN s.calledNumber "
				+ "WHEN ( s.callingNumber= :msisdn) THEN s.calledNumber END as isdn, "
				+ "CASE " + "WHEN (s.callingNumber= :msisdn) THEN 0 "
				+ "WHEN (s.callingNumber= :msisdn) THEN 1 END as direction, "
				+ "s.startTime, s.duration, s.basicBalance, s.promBalance) "
				+ "FROM co.siten.myvtg.model.precall.VSelfcareDetailSms s "
				+ "WHERE (s.callingNumber= :msisdn) and s.startTime >= :startTime and s.startTime <= :endTime"
				+ " ORDER BY s.startTime desc ");
		Query query = getSession().createQuery(sb.toString());
	//	query.setString("isdn", isdn);
		query.setString("msisdn", countryCode + isdn);
		query.setDate("startTime", startTime);
		query.setDate("endTime", endTime);
		query.setFirstResult((page - 1) * size);
		if (size > 0) {
			query.setMaxResults(size);
			query.setFirstResult((page - 1) * size);
		}
		List<co.siten.myvtg.bean.PostageDetailInfoBean> rList = query.list();
		return rList;
	}

	public List<co.siten.myvtg.bean.PostageDetailInfoBean> getPostageDetailInfoOther(Environment environment,
			String isdn, Date startTime, Date endTime, String countryCode, int size, int page, String order) {
		StringBuilder otherSql = new StringBuilder();
		otherSql.append(
				"SELECT DISTINCT new co.siten.myvtg.bean.PostageDetailInfoBean(c.callingNumber, 0, c.startTime, 0 , 0 , (c.duration) )"
						+ "FROM co.siten.myvtg.model.precall.VSelfcareDetailOther c "
						+ " WHERE (c.callingNumber= :msisdn) and "
						+ " c.startTime >= :startTime and c.startTime < :endTime " + " ORDER BY c.startTime desc "
						+ " ");

		Query query = getSession().createQuery(otherSql.toString());
		//query.setString("isdn", isdn);
		query.setString("msisdn", countryCode + isdn);
		query.setDate("startTime", startTime);
		query.setDate("endTime", endTime);

		if (size > 0) {
			query.setMaxResults(size);
			query.setFirstResult((page - 1) * size);
		}

		List<co.siten.myvtg.bean.PostageDetailInfoBean> rList = query.list();
		return rList;

	}
   
	/**
	 * @NamDH1: Added:20171109 for request change from VTC
	 * @param environment
	 * @param isdn
	 * @param startTime
	 * @param endTime
	 * @param countryCode
	 * @param size
	 * @param page
	 * @param order
	 * @return
	 */
	public List<co.siten.myvtg.bean.PostageDetailInfoBean> getPostageDetailInfoData(Environment environment,
			String isdn, Date startTime, Date endTime, String countryCode, int size, int page, String order) {
		StringBuilder otherSql = new StringBuilder();
		otherSql.append(
				"SELECT DISTINCT new co.siten.myvtg.bean.PostageDetailInfoBean(c.calledNumber, 0, c.startTime, c.duration , (c.basicBalance) , (c.promBalance) )"
						+ "FROM co.siten.myvtg.model.precall.VSelfcareDetailData c "
						+ " WHERE (c.callingNumber= :msisdn) and "
						+ " c.startTime >= :startTime and c.startTime < :endTime " + " ORDER BY c.startTime desc "
						+ " ");

		Query query = getSession().createQuery(otherSql.toString());
		// query.setString("isdn", isdn);
		query.setString("msisdn", countryCode + isdn);
		query.setDate("startTime", startTime);
		query.setDate("endTime", endTime);

		if (size > 0) {
			query.setMaxResults(size);
			query.setFirstResult((page - 1) * size);
		}

		List<co.siten.myvtg.bean.PostageDetailInfoBean> rList = query.list();
		return rList;

	}
	/**
	 * @NamDH1: Added:20171109 for request change from VTC
	 * @param environment
	 * @param isdn
	 * @param startTime
	 * @param endTime
	 * @param countryCode
	 * @param size
	 * @param page
	 * @param order
	 * @return
	 */

	public List<co.siten.myvtg.bean.PostageDetailInfoBean> getPostageDetailInfoVas(Environment environment,
			String isdn, Date startTime, Date endTime, String countryCode, int size, int page, String order) {
		StringBuilder otherSql = new StringBuilder();
		otherSql.append(
				"SELECT DISTINCT new co.siten.myvtg.bean.PostageDetailInfoBean(c.calledNumber, 0, c.startTime, 0 , (c.basicBalance) , (c.promBalance) )"
						+ "FROM co.siten.myvtg.model.precall.VSelfcareDetailVas c "
						+ " WHERE (c.callingNumber= :msisdn) and "
						+ " c.startTime >= :startTime and c.startTime < :endTime " + " ORDER BY c.startTime desc "
						+ " ");

		Query query = getSession().createQuery(otherSql.toString());
	//	query.setString("isdn", isdn);
		query.setString("msisdn", countryCode + isdn);
		query.setDate("startTime", startTime);
		query.setDate("endTime", endTime);

		if (size > 0) {
			query.setMaxResults(size);
			query.setFirstResult((page - 1) * size);
		}

		List<co.siten.myvtg.bean.PostageDetailInfoBean> rList = query.list();
		return rList;

	}
	
	public PostageInfoBean getPostageInfo(Environment environment, String isdn, int subType, Date startDate,

			Date endDate, String type, String countryCode) {
		StringBuilder callSql = new StringBuilder(
				"SELECT DISTINCT new co.siten.myvtg.bean.PostageInfoBean(SUM(basicBalance) , SUM(promBalance) , COUNT(c.startTime)) FROM  co.siten.myvtg.model.precall.VSelfcareDetailCall c "
						+ "WHERE (c.callingNumber= :msisdn) and "
						+ "c.startTime >= :startTime and c.startTime < :endTime ");

		StringBuilder smsSql = new StringBuilder(
				"SELECT DISTINCT new co.siten.myvtg.bean.PostageInfoBean(SUM(basicBalance) , SUM(promBalance), COUNT(c.startTime) ) FROM  co.siten.myvtg.model.precall.VSelfcareDetailSms c "
						+ "WHERE ( c.callingNumber= :msisdn) and "
						+ "c.startTime >= :startTime and c.startTime < :endTime ");

		StringBuilder otherSql = new StringBuilder();
		otherSql.append(
				"SELECT DISTINCT new co.siten.myvtg.bean.PostageInfoBean(SUM(basicBalance) , SUM(promBalance), COUNT(c.startTime) ) FROM co.siten.myvtg.model.precall.VSelfcareDetailOther c "
						+ " WHERE (c.callingNumber= :msisdn) and "
						+ " c.startTime >= :startTime and c.startTime < :endTime ");
		
		
		if (!"others".equalsIgnoreCase(type)) {

			Query query = getSession().createQuery(callSql.toString());

			//query.setString("isdn", isdn);
			query.setString("msisdn", countryCode + isdn);
			query.setDate("startTime", startDate);
			query.setDate("endTime", endDate);

			Query query2 = getSession().createQuery(smsSql.toString());
		//	query2.setString("isdn", isdn);
			query2.setString("msisdn", countryCode + isdn);
			query2.setDate("startTime", startDate);
			query2.setDate("endTime", endDate);

			Query query3 = getSession().createQuery(otherSql.toString());
		//	query3.setString("isdn", isdn);
			query3.setString("msisdn", countryCode + isdn);
			query3.setDate("startTime", startDate);
			query3.setDate("endTime", endDate);

						
			PostageInfoBean bean1 = (PostageInfoBean) query.uniqueResult();
			PostageInfoBean bean2 = (PostageInfoBean) query2.uniqueResult();
			PostageInfoBean bean = new PostageInfoBean();

			bean.setBasic(bean1.getBasic().add(bean2.getBasic()));
			bean.setProm(bean1.getProm().add(bean2.getProm()));

			bean.setCallFee(bean1.getBasic().add(bean1.getProm()));
			bean.setSmsFee(bean2.getBasic().add(bean2.getProm()));
			bean.setCallRc(bean1.getCount());
			bean.setSmsRc(bean2.getCount());

			if ("all".equalsIgnoreCase(type)) {
				PostageInfoBean bean3 = (PostageInfoBean) query3.uniqueResult();				
				bean.setBasic(bean.getBasic().add(bean3.getBasic()));
				bean.setProm(bean.getProm().add(bean3.getProm()));
				bean.setOtherFee(bean3.getBasic().add(bean3.getProm()));
				bean.setOtherRc(bean3.getCount());
				
			} else {
				bean.setOtherRc(0L);
				bean.setOtherFee(BigDecimal.ZERO);
			}

			return bean;
		} else {
			Query query3 = getSession().createQuery(otherSql.toString());
			//query3.setString("isdn", isdn);
			query3.setString("msisdn", countryCode + isdn);
			query3.setDate("startTime", startDate);
			query3.setDate("endTime", endDate);

			PostageInfoBean bean3 = (PostageInfoBean) query3.uniqueResult();
			PostageInfoBean bean = new PostageInfoBean();
			bean.setBasic(BigDecimal.ZERO);
			bean.setProm(BigDecimal.ZERO);
			bean.setCallFee(BigDecimal.ZERO);
			bean.setSmsFee(BigDecimal.ZERO);
			bean.setOtherFee(bean3.getBasic().add(bean3.getProm()));
			bean.setOtherRc(bean3.getCount());
			return bean;
		}
	}
		/**
		 * @namdh1 add for split data, vas from other
		 * @param environment
		 * @param isdn
		 * @param subType
		 * @param startDate
		 * @param endDate
		 * @param type
		 * @param countryCode
		 * @return
		 */
		public PostageInfoBean getPostageInfoSplitDataVas(Environment environment, String isdn, int subType, Date startDate,

				Date endDate, String type, String countryCode) {
			StringBuilder callSql = new StringBuilder();
                        callSql.append("SELECT DISTINCT new co.siten.myvtg.bean.PostageInfoBean(SUM(basicBalance) , SUM(promBalance) , COUNT(c.startTime)) FROM  co.siten.myvtg.model.precall.VSelfcareDetailCall c "
							+ "WHERE (c.callingNumber= :msisdn) and "
							+ "c.startTime >= :startTime and c.startTime < :endTime ");

			StringBuilder smsSql = new StringBuilder();
                        smsSql.append("SELECT DISTINCT new co.siten.myvtg.bean.PostageInfoBean(SUM(basicBalance) , SUM(promBalance), COUNT(c.startTime) ) FROM  co.siten.myvtg.model.precall.VSelfcareDetailSms c "
							+ "WHERE (c.callingNumber= :msisdn) and "
							+ "c.startTime >= :startTime and c.startTime < :endTime ");
			
			StringBuilder dataSql = new StringBuilder();
			dataSql.append("SELECT DISTINCT new co.siten.myvtg.bean.PostageInfoBean(SUM(basicBalance) , SUM(promBalance), COUNT(c.startTime) ) FROM co.siten.myvtg.model.precall.VSelfcareDetailData c "
							+ " WHERE (c.callingNumber= :msisdn) and "
							+ " c.startTime >= :startTime and c.startTime < :endTime ");
			
			StringBuilder vasSql = new StringBuilder();
			vasSql.append("SELECT DISTINCT new co.siten.myvtg.bean.PostageInfoBean(SUM(basicBalance) , SUM(promBalance), COUNT(c.startTime) ) FROM co.siten.myvtg.model.precall.VSelfcareDetailVas c "
							+ " WHERE (c.callingNumber= :msisdn) and "
							+ " c.startTime >= :startTime and c.startTime < :endTime ");
			
              
			if (!"others".equalsIgnoreCase(type)) {

				Query query = getSession().createQuery(callSql.toString());

			//	query.setString("isdn", isdn);
				query.setString("msisdn", countryCode + isdn);
				query.setDate("startTime", startDate);
				query.setDate("endTime", endDate);

				Query query2 = getSession().createQuery(smsSql.toString());
			//	query2.setString("isdn", isdn);
				query2.setString("msisdn", countryCode + isdn);
				query2.setDate("startTime", startDate);
				query2.setDate("endTime", endDate);

				//NamDH1 Begin
				Query qrData = getSession().createQuery(dataSql.toString());
			//	qrData.setString("isdn", isdn);
				qrData.setString("msisdn", countryCode + isdn);
				qrData.setDate("startTime", startDate);
				qrData.setDate("endTime", endDate);
				
				Query qrVas = getSession().createQuery(vasSql.toString());
			//	qrVas.setString("isdn", isdn);
				qrVas.setString("msisdn", countryCode + isdn);
				qrVas.setDate("startTime", startDate);
				qrVas.setDate("endTime", endDate);
				
				//NamDH1 End
				
				PostageInfoBean bean1 = (PostageInfoBean) query.uniqueResult();
				PostageInfoBean bean2 = (PostageInfoBean) query2.uniqueResult();
				PostageInfoBean bean = new PostageInfoBean();

				

				bean.setCallFee(bean1.getBasic().add(bean1.getProm()));
				bean.setSmsFee(bean2.getBasic().add(bean2.getProm()));
				bean.setCallRc(bean1.getCount());
				bean.setSmsRc(bean2.getCount());
				
				PostageInfoBean beanData = (PostageInfoBean) qrData.uniqueResult();
				PostageInfoBean beanVas = (PostageInfoBean) qrVas.uniqueResult();
				//NamDH1 Add
				bean.setDataFee(beanData.getBasic().add(beanData.getProm()));
				bean.setDataRc(beanData.getCount());
				
				bean.setVasFee(beanVas.getBasic().add(beanVas.getProm()));
				bean.setVasRc(beanVas.getCount());
				
				bean.setBasic(bean1.getBasic().add(bean2.getBasic()).add(beanData.getBasic()).add(beanVas.getBasic()));
				bean.setProm(bean1.getProm().add(bean2.getProm()).add(beanData.getProm()).add(beanVas.getProm()));
                                /*
				if ("all".equalsIgnoreCase(type)) {
					PostageInfoBean bean3 = (PostageInfoBean) qrVas.uniqueResult();
				//	PostageInfoBean beanData = (PostageInfoBean) qrData.uniqueResult();
				//	PostageInfoBean beanVas = (PostageInfoBean) qrVas.uniqueResult();
					
				//	bean.setBasic(bean.getBasic());
				//	bean.setProm(bean.getProm());
					//bean.setOtherFee(bean3.getBasic().add(bean3.getProm()));
				//	bean.setOtherRc(bean3.getCount());
					//NamDH1 Add
				//	bean.setDataFee(beanData.getBasic().add(beanData.getProm()));
				//	bean.setDataRc(beanData.getCount());
					
				//	bean.setVasFee(beanVas.getBasic().add(beanVas.getProm()));
				//	bean.setVasRc(beanVas.getCount());
					
				} else {
					bean.setOtherRc(0L);
					bean.setOtherFee(BigDecimal.ZERO);
				}
                                */

				return bean;
			} else {
				Query query3 = getSession().createQuery(dataSql.toString());
			//	query3.setString("isdn", isdn);
				query3.setString("msisdn", countryCode + isdn);
				query3.setDate("startTime", startDate);
				query3.setDate("endTime", endDate);

				PostageInfoBean bean3 = (PostageInfoBean) query3.uniqueResult();
				PostageInfoBean bean = new PostageInfoBean();
				bean.setBasic(BigDecimal.ZERO);
				bean.setProm(BigDecimal.ZERO);
				bean.setCallFee(BigDecimal.ZERO);
				bean.setSmsFee(BigDecimal.ZERO);
				bean.setDataFee(BigDecimal.ZERO);
			//	bean.setDataRc(dataRc);
				bean.setVasFee(BigDecimal.ZERO);
				bean.setOtherFee(bean3.getBasic().add(bean3.getProm()));
				bean.setOtherRc(bean3.getCount());
				return bean;
			}
                        
                        /*
			if("data".equalsIgnoreCase(type)) {
				//NamDH1 Begin
				Query qrData = getSession().createQuery(dataSql.toString());
			//	qrData.setString("isdn", isdn);
				qrData.setString("msisdn", countryCode + isdn);
				qrData.setDate("startTime", startDate);
				qrData.setDate("endTime", endDate);
				PostageInfoBean beanData = (PostageInfoBean) qrData.uniqueResult();
				PostageInfoBean bean = new PostageInfoBean();
				bean.setBasic(BigDecimal.ZERO);
				bean.setProm(BigDecimal.ZERO);
				bean.setCallFee(BigDecimal.ZERO);
				bean.setSmsFee(BigDecimal.ZERO);
				bean.setDataFee(beanData.getBasic().add(beanData.getProm()));
				bean.setVasFee(BigDecimal.ZERO);
				
				bean.setDataRc(beanData.getCount());
				
				return bean;
				
				
			} else if("vas".equalsIgnoreCase(type)) {
				Query queryVas = getSession().createQuery(vasSql.toString());
			//	queryVas.setString("isdn", isdn);
				queryVas.setString("msisdn", countryCode + isdn);
				queryVas.setDate("startTime", startDate);
				queryVas.setDate("endTime", endDate);

				PostageInfoBean beanVas = (PostageInfoBean) queryVas.uniqueResult();
				PostageInfoBean bean = new PostageInfoBean();
				bean.setBasic(BigDecimal.ZERO);
				bean.setProm(BigDecimal.ZERO);
				bean.setCallFee(BigDecimal.ZERO);
				bean.setSmsFee(BigDecimal.ZERO);
				bean.setDataFee(BigDecimal.ZERO);
				
				bean.setVasFee(beanVas.getBasic().add(beanVas.getProm()));
				bean.setVasRc(beanVas.getCount());
				return bean;
			} else if("sms".equalsIgnoreCase(type)) {
				//NamDH1 Begin
				Query querySMS = getSession().createQuery(smsSql.toString());
			//	querySMS.setString("isdn", isdn);
				querySMS.setString("msisdn", countryCode + isdn);
				querySMS.setDate("startTime", startDate);
				querySMS.setDate("endTime", endDate);
				PostageInfoBean beanSms = (PostageInfoBean) querySMS.uniqueResult();
				PostageInfoBean bean = new PostageInfoBean();
				bean.setBasic(BigDecimal.ZERO);
				bean.setProm(BigDecimal.ZERO);
				bean.setCallFee(BigDecimal.ZERO);
				bean.setSmsFee(BigDecimal.ZERO);
				bean.setDataFee(beanSms.getBasic().add(beanSms.getProm()));
				bean.setVasFee(BigDecimal.ZERO);
				bean.setDataRc(beanSms.getCount());
				
				return bean;
			} else if("call".equalsIgnoreCase(type)) {
				//NamDH1 Begin
				Query queryCall = getSession().createQuery(callSql.toString());
				//queryCall.setString("isdn", isdn);
				queryCall.setString("msisdn", countryCode + isdn);
				queryCall.setDate("startTime", startDate);
				queryCall.setDate("endTime", endDate);
				PostageInfoBean beanCall = (PostageInfoBean) queryCall.uniqueResult();
				PostageInfoBean bean = new PostageInfoBean();
				bean.setBasic(BigDecimal.ZERO);
				bean.setProm(BigDecimal.ZERO);
				bean.setCallFee(BigDecimal.ZERO);
				bean.setSmsFee(BigDecimal.ZERO);
				bean.setDataFee(BigDecimal.ZERO);
				bean.setVasFee(BigDecimal.ZERO);
				bean.setDataRc(beanCall.getCount());
				
				return bean;
			} else if("default".equalsIgnoreCase(type)) {
				Query queryCall = getSession().createQuery(callSql.toString());

			//	queryCall.setString("isdn", isdn);
				queryCall.setString("msisdn", countryCode + isdn);
				queryCall.setDate("startTime", startDate);
				queryCall.setDate("endTime", endDate);

				Query querySMS = getSession().createQuery(smsSql.toString());
			//	querySMS.setString("isdn", isdn);
				querySMS.setString("msisdn", countryCode + isdn);
				querySMS.setDate("startTime", startDate);
				querySMS.setDate("endTime", endDate);
				
				Query qrVas = getSession().createQuery(vasSql.toString());
	
				qrVas.setString("msisdn", countryCode + isdn);
				qrVas.setDate("startTime", startDate);
				qrVas.setDate("endTime", endDate);
				
				PostageInfoBean beanCall = (PostageInfoBean) queryCall.uniqueResult();
				PostageInfoBean beanSMS = (PostageInfoBean) querySMS.uniqueResult();
			
				PostageInfoBean beanVas = (PostageInfoBean) qrVas.uniqueResult();
				
				PostageInfoBean bean = new PostageInfoBean();
				BigDecimal totalBasic = BigDecimal.ZERO;
				BigDecimal totalProm  = BigDecimal.ZERO;
				totalBasic = totalBasic.add(beanCall.getBasic()) ;
				totalBasic = totalBasic.add(beanSMS.getBasic());
				totalBasic = totalBasic.add(beanVas.getBasic());
				bean.setBasic(totalBasic);
				
		
				totalProm = totalProm.add(beanCall.getProm()) ;
				totalProm = totalProm.add(beanSMS.getProm());
				totalProm = totalProm.add(beanVas.getProm());
				bean.setProm(totalProm);
				

			
				bean.setCallFee(beanCall.getBasic().add(beanCall.getProm()));
				bean.setSmsFee(beanSMS.getBasic().add(beanSMS.getProm()));
				bean.setCallRc(beanCall.getCount());
				bean.setSmsRc(beanSMS.getCount());		
				bean.setDataFee(BigDecimal.ZERO);
				bean.setVasFee(BigDecimal.ZERO);
				
				
				return bean;
			}
				
				else {
				Query queryCall = getSession().createQuery(callSql.toString());

			//	queryCall.setString("isdn", isdn);
				queryCall.setString("msisdn", countryCode + isdn);
				queryCall.setDate("startTime", startDate);
				queryCall.setDate("endTime", endDate);

				Query querySMS = getSession().createQuery(smsSql.toString());
			//	querySMS.setString("isdn", isdn);
				querySMS.setString("msisdn", countryCode + isdn);
				querySMS.setDate("startTime", startDate);
				querySMS.setDate("endTime", endDate);
				
				//NamDH1 Begin
				Query qrData = getSession().createQuery(dataSql.toString());

				qrData.setString("msisdn", countryCode + isdn);
				qrData.setDate("startTime", startDate);
				qrData.setDate("endTime", endDate);
				
				Query qrVas = getSession().createQuery(vasSql.toString());

				qrVas.setString("msisdn", countryCode + isdn);
				qrVas.setDate("startTime", startDate);
				qrVas.setDate("endTime", endDate);
				 
				PostageInfoBean beanCall = (PostageInfoBean) queryCall.uniqueResult();
				PostageInfoBean beanSMS = (PostageInfoBean) querySMS.uniqueResult();
				PostageInfoBean beanData = (PostageInfoBean) qrData.uniqueResult();
				PostageInfoBean beanVas = (PostageInfoBean) qrVas.uniqueResult();
				PostageInfoBean bean = new PostageInfoBean();
				
				BigDecimal totalBasic = BigDecimal.ZERO;
				BigDecimal totalProm  = BigDecimal.ZERO;
				totalBasic = totalBasic.add(beanCall.getBasic()) ;
				totalBasic = totalBasic.add(beanSMS.getBasic());
				totalBasic = totalBasic.add(beanVas.getBasic());
				totalBasic = totalBasic.add(beanData.getBasic());
				bean.setBasic(totalBasic);
				
		
				totalProm = totalProm.add(beanCall.getProm()) ;
				totalProm = totalProm.add(beanSMS.getProm());
				totalProm = totalProm.add(beanVas.getProm());
				totalProm = totalProm.add(beanData.getProm());
				bean.setProm(totalProm);
				
				
			//	bean.setBasic(beanCall.getBasic().add(beanCall.getBasic()));
			//	bean.setProm(beanCall.getProm().add(beanCall.getProm()));

							
				bean.setCallFee(beanCall.getBasic().add(beanCall.getProm()));
				bean.setSmsFee(beanSMS.getBasic().add(beanSMS.getProm()));
				bean.setCallRc(beanCall.getCount());
				bean.setSmsRc(beanSMS.getCount());										
				bean.setDataFee(beanData.getBasic().add(beanData.getProm()));
				bean.setDataRc(beanData.getCount());				
				bean.setVasFee(beanVas.getBasic().add(beanVas.getProm()));
				bean.setVasRc(beanVas.getCount());
				
				
				return bean;				
			}
*/
                        
                        

	}
 }

