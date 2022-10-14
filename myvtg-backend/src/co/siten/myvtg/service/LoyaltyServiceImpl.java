package co.siten.myvtg.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;

import co.siten.myvtg.bean.AllCustSubForSelfcareBean;
import co.siten.myvtg.bean.ResponseSuccessBean;
import co.siten.myvtg.bean.SubMainInfoBean;
import co.siten.myvtg.bean.VCustomerBean;
import co.siten.myvtg.dao.CmposDao;
import co.siten.myvtg.dao.CmpreDao;
import co.siten.myvtg.dao.LoyaltyDao;
import co.siten.myvtg.dto.AccountRankDTO;
import co.siten.myvtg.dto.BaseResponsesDto;
import co.siten.myvtg.dto.InforAccountPoint;
import co.siten.myvtg.dto.InforOfRankDTO;
import co.siten.myvtg.dto.SubMbDto;
import co.siten.myvtg.exception.SitenException;
import co.siten.myvtg.model.myvtg.Webservice;
import co.siten.myvtg.util.CommonUtil;
import co.siten.myvtg.util.ConfigUtils;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.WebServiceUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.util.Base64;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;

@org.springframework.stereotype.Service("LoyaltyService")

@PropertySource(value = {"classpath:provisioning.properties"})
@Transactional(rollbackFor = Exception.class, value = "loyaltytransaction")
public class LoyaltyServiceImpl implements LoyaltyService {

    private static final Logger logger = Logger.getLogger(LoyaltyServiceImpl.class);
//
//    @Autowired
//    LoyaltyDao loyaltyDao;
    @Autowired
    CmpreDao cmpreDao;
    @Autowired
    MyvtgService myvtgService;
    @Autowired
    ConfigUtils configUtils;

    @Override
    public String generateQrCodeByIsdn(String isdn) {
        try {
            if (DataUtil.isNullOrEmpty(isdn)) {
                return null;
            }
            isdn = DataUtil.fomatIsdn(isdn);
            InforOfRankDTO infoBean = new InforOfRankDTO();
            SubMbDto vCus = cmpreDao.getCustByIsdn(isdn);
            logger.info("Start call API getAccountRankInfo ");
            Webservice ws = myvtgService.getWS(Constants.WEBSERVICE_LOYALTY_RANK);
            if (!DataUtil.isNullObject(ws)) {
                logger.info("Requesst send getAccountRankInfo");
                String url = ws.getUrl() + isdn;
                BaseResponsesDto response = WebServiceUtil.callApiGet(url);
                if (response.getStatusCode() == HttpURLConnection.HTTP_OK) {
                    JSONObject jsonObj = new JSONObject(response.getMessageCode());
                    if ("000".equals(jsonObj.getString("code")) && response.getMessageCode().contains("accountRankDTO")) {
                        infoBean = CommonUtil.convertJsonStringToObject(jsonObj.toString(), InforOfRankDTO.class);
                    }
                }
            }
            if (!DataUtil.isNullObject(vCus)) {
                return generateQrCode(vCus, infoBean.getAccountRankDTO());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generateQrCode(SubMbDto vCus, AccountRankDTO infoBean) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("Name: ").append(vCus.getCustName()).append("\n");
            String rank = DataUtil.isNullOrEmpty(infoBean.getRankName()) ? "" : infoBean.getRankName();
            sb.append("Rank: " + rank + "\n");
            sb.append("Contact: " + vCus.getEmail() + "\n");
            String expiredDate = DataUtil.isNullOrEmpty(infoBean.getEndDate()) ? "" : infoBean.getEndDate();
            sb.append("Expiry date: " + expiredDate + "\n");
            sb.append(configUtils.getMessage("URL_QRCODE", "https://vip.metfone.com.kh/"));
            System.out.println("sb:\n" + sb.toString());
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(sb.toString(), BarcodeFormat.QR_CODE, Constants.WIDTH_QR_CODE,
                    Constants.HEIGHT_QR_CODE);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
            System.out.println("========>" + Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray()));
            return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
