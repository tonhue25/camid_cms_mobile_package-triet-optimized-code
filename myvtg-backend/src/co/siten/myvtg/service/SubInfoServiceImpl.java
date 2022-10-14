package co.siten.myvtg.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;

import co.siten.myvtg.bean.AllCustSubForSelfcareBean;
import co.siten.myvtg.bean.ResponseSuccessBean;
import co.siten.myvtg.bean.SubMainInfoBean;
import co.siten.myvtg.exception.SitenException;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.Constants;

@org.springframework.stereotype.Service("SubInfoService")

@PropertySource(value = { "classpath:provisioning.properties" })
public class SubInfoServiceImpl implements SubInfoService {
	@Autowired
	AsyncTaskService asyncTaskService;

	@Autowired
	CmpreService cmpreService;

	@Autowired
	MyvtgService myvtgService;

	@Autowired
	CmposService cmposService;

	@Override
	public void wsGetSubMainInfo(String isdn, ResponseSuccessBean bean) throws Exception {
		
                SubMainInfoBean dataSub = cmpreService.getCustSubInfo(isdn);
		if (dataSub == null) {
			dataSub = cmposService.getCustSubInfo(isdn);
		}
                
            //    System.out.println("namdh1: ========namdh1===========>" + dataSub.getSubId());
		bean.setWsResponse(dataSub);

            
		SubMainInfoBean data = myvtgService.getSubMainInfo(isdn);
		if (data == null) {
			String custName;
			Date birthday;
			String gender;
			Long subId;
			String productCode;
			int subType;

			// try to query from pre-paid's database
			AllCustSubForSelfcareBean preSubMb = cmpreService.getCustSubForSelfcare(isdn);

			if (preSubMb == null) {
				// try to query from pos-paid's database
				co.siten.myvtg.model.cmpos.AllTelServiceSubSelfcare posSubMb = cmposService
						.getTelServiceSubSelfcare(isdn);
				if (posSubMb == null) {

					throw new SitenException("cmposService.AllTelServiceSubSelfcare Not found with" + isdn);
				} else {

					custName = posSubMb.getCustName();
					birthday = posSubMb.getBirthDate();
					gender = posSubMb.getGender();
					subId = posSubMb.getSubId();
					productCode = posSubMb.getProductCode();
					subType = Constants.SUBTYPE_TRASAU;
					// data = cmposService.getCustSubInfo(isdn);
				}
			} else {
				// Lấy custname
				custName = preSubMb.getCustName();
				birthday = preSubMb.getBirthDate();
				gender = preSubMb.getGender();
				subId = preSubMb.getSubId();
				subType = Constants.SUBTYPE_TRATRUOC;
				productCode = preSubMb.getProductCode();
				// data = cmpreService.getCustSubInfo(isdn);
			}

			// insert new sub into SUB table
			asyncTaskService.syncNewSub(isdn, subId, custName, birthday, productCode, gender, subType);
			// asyncTaskService.syncSubscriberState(isdn, subId, subType,
			// false);

		} else {
			// ST_04

			if (data.getLastSyncTime() != null
					&& CommonUtil.subtractDays(CommonUtil.getCurrentTime(), data.getLastSyncTime()) <= 10) {

			} else {
				// try to get lastest info
				if (data.getSubType() == Constants.SUBTYPE_TRATRUOC) {
					AllCustSubForSelfcareBean preSubMb = cmpreService.getCustSubForSelfcare(isdn);

					asyncTaskService.updateSub(isdn, preSubMb.getSubId(), preSubMb.getCustName(),
							preSubMb.getBirthDate(), preSubMb.getProductCode(), preSubMb.getGender());
				} else if (data.getSubType() == Constants.SUBTYPE_TRASAU) {
					co.siten.myvtg.model.cmpos.AllTelServiceSubSelfcare posSubMb = cmposService
							.getTelServiceSubSelfcare(isdn);
					asyncTaskService.updateSub(isdn, posSubMb.getSubId(), posSubMb.getCustName(),
							posSubMb.getBirthDate(), posSubMb.getProductCode(), posSubMb.getGender());
				}

				// asyncTaskService.syncSubscriberState(isdn, data.getSubId(),
				// data.getSubType(), true);
			}
		}
                
            
		// bean.setWsResponse(data);
	}
}
