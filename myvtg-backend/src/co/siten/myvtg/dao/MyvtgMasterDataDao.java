package co.siten.myvtg.dao;

import co.siten.myvtg.bean.*;
import co.siten.myvtg.dto.*;
import co.siten.myvtg.model.myvtg.*;
import co.siten.myvtg.model.soapws.SoapWebServiceResponse;
import co.siten.myvtg.util.*;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.hibernate.type.DoubleType;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

/**
 * @author thomc
 */
@Repository("MyvtgMasterDataDao")
public class MyvtgMasterDataDao extends AbstractMyvtgDao<Object> {

    private static final Logger logger = Logger.getLogger(MyvtgMasterDataDao.class.getName());
    @Autowired
    CmpreDao cmpreDao;
    @Autowired
    CmposDao cmposDao;
    @Autowired
    SubDao subDao;
    @Autowired
    private Environment environment;
    @Autowired
    private FTPUploader fileUploader;

    @SuppressWarnings("unchecked")
    public List<HotNewsBean> getHotNewsByLanguage(String language, int limit) {
        StringBuilder sb = new StringBuilder(
                " SELECT new co.siten.myvtg.bean.HotNewsBean(s.id, s.adImgUrl, s.type)  FROM HotNew s WHERE s.effectTime  <= :sysdate and expiredTime >= :sysdate"
                        + " AND approved =1 AND language = :language AND status = 1 ORDER BY priority");
        Query query = getSession().createQuery(sb.toString());
        query.setString("language", language);
        query.setDate("sysdate", new Date());
        query.setMaxResults(limit);
        return query.list();
    }

    @Cacheable("jobs")
    @SuppressWarnings("unchecked")
    public List<DataBean> getJobsByLanguage(String language) {
        StringBuilder sb = new StringBuilder();
        if (Constants.LOCAL_LANGUAGE.equals(language)) {
            sb.append(" SELECT new co.siten.myvtg.bean.DataBean(s.id, CASE WHEN s.nameLc is null THEN s.name ELSE s.nameLc END) FROM Job s  Order by s.name ");
        } else {
            sb.append(" SELECT new co.siten.myvtg.bean.DataBean(s.id, CASE WHEN s.nameLc is null THEN s.name ELSE s.nameLc END) FROM Job s Order by s.name ");
        }
//		StringBuilder sb = new StringBuilder(
//				" SELECT new co.siten.myvtg.bean.DataBean(s.id, s.name) FROM Job s WHERE s.language = :language Order by s.name");
        Query query = getSession().createQuery(sb.toString());
//		query.setString("language", language);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<AccConfig> getAllAccConfig() {
        StringBuilder sb = new StringBuilder(" SELECT s  FROM AccConfig s");
        Query query = getSession().createQuery(sb.toString());
//        query.setCacheable(true);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<Hobby> getAllHobbies(String language) {
        StringBuilder sb = new StringBuilder();

        if (Constants.LOCAL_LANGUAGE.equals(language)) {
            sb.append(" SELECT new co.siten.myvtg.bean.HobbyBean(s.id, CASE WHEN s.nameLc is null THEN s.name ELSE s.nameLc END)  FROM Hobby s ");
        } else {
            sb.append(" SELECT new co.siten.myvtg.bean.HobbyBean(s.id, s.name)  FROM Hobby s");
        }

//		StringBuilder sb = new StringBuilder(
//				" SELECT new co.siten.myvtg.bean.HobbyBean(s.id, s.name)  FROM Hobby s WHERE s.language = :language Order by s.name ");
        Query query = getSession().createQuery(sb.toString());
        //	query.setString("language", language);
        return query.list();
    }

    public boolean updateSubInfo(String isdn, BigDecimal jobId, BigDecimal hobbyId, String email) {
        String sb = " UPDATE Sub SET jobId = :jobId, hobbyId = :hobbyId, email= :email where isdn = :isdn";
        Query query = getSession().createQuery(sb);
        query.setBigDecimal("jobId", jobId);
        query.setBigDecimal("hobbyId", hobbyId);
        query.setString("email", email);
        query.setString("isdn", isdn);
        int result = query.executeUpdate();
        return result > 0;
    }

    public boolean updateSubInfo(String isdn, BigDecimal jobId, BigDecimal hobbyId, String email, String avatar) {
        String sb = " UPDATE Sub SET jobId = :jobId, hobbyId = :hobbyId, email= :email, avatarUrl = :avatar   where isdn = :isdn";
        Query query = getSession().createQuery(sb);
        query.setBigDecimal("jobId", jobId);
        query.setBigDecimal("hobbyId", hobbyId);
        query.setString("email", email);
        query.setString("isdn", isdn);
        query.setString("avatar", avatar);
        int result = query.executeUpdate();
        return result > 0;
    }

    @SuppressWarnings("unchecked")
    public List<DataPricePlanConfig> getAllPricePlanConfig(int simType) {
        StringBuilder sb = new StringBuilder(
                " SELECT s  FROM DataPricePlanConfig s WHERE s.simType = :simType or s.simType =:simType3G4G");
        // + " and s.accCode in (:accCodeList)");
        Query query = getSession().createQuery(sb.toString());
        query.setInteger("simType", simType);
        query.setInteger("simType3G4G", Constants.SIM_TYPE_3_4G);
        // query.setParameterList("accCodeList", accountCodeList);
//        query.setCacheable(true);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public DataPricePlanConfig getPricePlanConfigByPricePlanId(int pricePlan, int simType) {
        StringBuilder sb = new StringBuilder(
                " SELECT s  FROM DataPricePlanConfig s WHERE s.pricePlan = :pricePlan and s.simType= :simType");
        Query query = getSession().createQuery(sb.toString());
        query.setInteger("pricePlan", pricePlan);
        query.setInteger("simType", simType);
//        query.setCacheable(true);
        List<DataPricePlanConfig> rList = query.list();
        if (!CommonUtil.isEmpty(rList)) {
            return rList.get(0);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<ServiceDetailBean> getServiceDetail(String language, String isdn, String serviceCode,
                                                    List<String> listRegisteredServiceCodes) {
        // if (isUnitelMarket()) {

        if (listRegisteredServiceCodes == null || listRegisteredServiceCodes.isEmpty()) {
            listRegisteredServiceCodes = new ArrayList<>();
        }

        /*
         * StringBuilder sb = new
         * StringBuilder(" SELECT new co.siten.myvtg.bean.ServiceDetailBean(s.name,"
         * + " s.code," + " s.fullDes," + " s.imgDesUrl," + " s.webLink," +
         * " s.price," +
         * " CASE WHEN s.actionType = null THEN 0 ELSE s.actionType END," +
         * " ss.name as name, " + " ss.code, " + " ss.shortDes," +
         * " ss.fullDes, " + " ss.iconUrl, " + " ss.price, " + " ss.unit, " +
         * " 0) " + " FROM co.siten.myvtg.model.myvtg.ServiceA s,SubServiceA ss "
         * + " WHERE (s.code = :code OR s.id = :code)" +
         * " AND s.language = :language" + " AND s.approved = 1" +
         * " AND s.status = 1" + " AND s.serviceType = 0" +
         * " AND s.id = ss.serviceId" + " AND ss.approved = 1" +
         * " AND ss.language = :language" + " AND ss.status   = 1");
         */
        String sb = " SELECT new co.siten.myvtg.bean.ServiceDetailBean(s.name," + " s.code, s.shortDes,"
                + " s.fullDes," + " s.imgDesUrl," + " s.webLink," + " s.price,"
                + " CASE WHEN s.actionType = null THEN 0 ELSE s.actionType END," + " ss.name as name, " + " ss.code, "
                + " ss.shortDes," + " ss.fullDes," + " ss.iconUrl, " + " ss.price, " + " ss.unit, " + " 0,s.validity, s.serviceType, s.showSubService) "
                + " FROM co.siten.myvtg.model.myvtg.ServiceA s,SubServiceA ss "
                + " WHERE (s.code = :code OR s.id = :code)" + " AND s.language = :language" + " AND s.approved = 1"
                + " AND s.status = 1" + " AND s.id = ss.serviceId" + " AND ss.approved = 1"
                + " AND ss.language = :language" + " AND ss.status   = 1";

        Query query = getSession().createQuery(sb);
        query.setString("language", language);
        query.setString("code", serviceCode);

        List<ServiceDetailBean> lst = query.list();
        List<ServiceDetailBean> lstTemp = new ArrayList<>();
        for (ServiceDetailBean b : lst) {

            if (isContainServiceCode(b.getSubCode(), listRegisteredServiceCodes)) {
                b.setState(Constants.SUBCRIBER_STATE_REGISTERED);
            } else {
                b.setState(Constants.SUBCRIBER_STATE_CANCEL);
            }
            if (!isExist(lstTemp, b.getSubCode())) {
                lstTemp.add(b);
            }
        }
        return lstTemp;

    }

    private boolean isExist(List<ServiceDetailBean> lstTemp, String code) {
        boolean result = false;
        for (ServiceDetailBean b : lstTemp) {
            if (b.getSubCode().equals(code)) {
                result = true;
                break;
            }
        }
        return result;
    }

    private Boolean isContainServiceCode(String subServiceCode, List<String> listRegisteredServiceCodes) {
        for (String s : listRegisteredServiceCodes) {
            if (s.equalsIgnoreCase(subServiceCode)) {
                return true;
            }
        }
        return false;

    }

    private Subscriber getSubscriberBy(String subServiceCode, List<Subscriber> lst) {
        for (Subscriber s : lst) {
            if (s.getId().getSubServiceCode().equalsIgnoreCase(subServiceCode)) {
                return s;
            }
        }
        return null;

    }

    public Webservice getWebserviceByName(String name) {
        StringBuilder sb = new StringBuilder("SELECT DISTINCT s from Webservice s WHERE s.wsName = :wsName");
        Query query = getSession().createQuery(sb.toString());
        query.setString("wsName", name);
        return (Webservice) query.uniqueResult();
    }

    public Webservice getWebserviceById(String id) {
        StringBuilder sb = new StringBuilder("SELECT DISTINCT s from Webservice s WHERE s.id = :id");
        Query query = getSession().createQuery(sb.toString());
        query.setString("id", id);
        return (Webservice) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<AreaBean> getProvinces() {
        StringBuilder sb = new StringBuilder(
                " SELECT new co.siten.myvtg.bean.AreaBean(s.province,s.name) FROM Area s WHERE s.province is not null AND s.district is null AND s.precinct is null Order by s.name");
        Query query = getSession().createQuery(sb.toString());
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<AreaBean> getProvinces(String language) {
        StringBuilder sb = new StringBuilder();

        if (Constants.LOCAL_LANGUAGE.equals(language)) {
            sb.append(" SELECT new co.siten.myvtg.bean.AreaBean(s.province, CASE WHEN s.nameLc is null THEN s.name ELSE s.nameLc END ) "
                    + " FROM Area s WHERE s.province is not null AND s.district is null AND s.precinct is null Order by s.nameLc");
        } else {
            sb.append(" SELECT new co.siten.myvtg.bean.AreaBean(s.province,s.name) FROM Area s WHERE s.province is not null AND s.district is null AND s.precinct is null Order by s.name");
        }
        Query query = getSession().createQuery(sb.toString());
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<AreaBean> getDistricts(String provinceId) {
        StringBuilder sb = new StringBuilder(
                " SELECT new co.siten.myvtg.bean.AreaBean(s.district,s.name) FROM Area s WHERE s.province is not null AND s.district is not null AND s.precinct is null ");
        if (provinceId != null) {
            sb.append(" AND s.province=:provinceId");
        }
        sb.append(" Order by s.name");
        Query query = getSession().createQuery(sb.toString());
        if (provinceId != null) {
            query.setString("provinceId", provinceId);
        }
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<AreaBean> getDistricts(String provinceId, String language) {

        StringBuilder sb = new StringBuilder();

        if (Constants.LOCAL_LANGUAGE.equals(language)) {
            sb.append(" SELECT new co.siten.myvtg.bean.AreaBean(s.district, CASE WHEN s.nameLc is null THEN s.name ELSE s.nameLc END ) FROM Area s WHERE s.province is not null AND s.district is not null AND s.precinct is null ");
        } else {
            sb.append(" SELECT new co.siten.myvtg.bean.AreaBean(s.district,s.name) FROM Area s WHERE s.province is not null AND s.district is not null AND s.precinct is null ");
        }

//		StringBuilder sb = new StringBuilder(
//				" SELECT new co.siten.myvtg.bean.AreaBean(s.district,s.name) FROM Area s WHERE s.province is not null AND s.district is not null AND s.precinct is null ");
        if (provinceId != null) {
            sb.append(" AND s.province=:provinceId");
        }
        if (Constants.LOCAL_LANGUAGE.equals(language)) {
            sb.append(" Order by s.nameLc");
        } else {
            sb.append(" Order by s.name");
        }
        // sb.append(" Order by s.name");

        Query query = getSession().createQuery(sb.toString());
        if (provinceId != null) {
            query.setString("provinceId", provinceId);
        }
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<AreaBean> getPrecincts(String provinceId, String districtId) {
        StringBuilder sb = new StringBuilder(
                " SELECT new co.siten.myvtg.bean.AreaBean(s.areaCode,s.name) FROM Area s WHERE s.precinct is not null ");
        if (provinceId != null) {
            sb.append(" AND s.province=:provinceId");
        }
        if (districtId != null) {
            sb.append(" AND s.district=:districtId");
        }
        sb.append(" Order by s.name");
        Query query = getSession().createQuery(sb.toString());
        if (provinceId != null) {
            query.setString("provinceId", provinceId);
        }
        if (districtId != null) {
            query.setString("districtId", districtId);
        }
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<StoresBean> getNearestStores(BigDecimal longitude, BigDecimal latitude, float maxDistance,
                                             Boolean isCheckDistrict) {
        StringBuilder sb = new StringBuilder();
        String distance = "0";
        if (longitude != null && latitude != null) {
            distance = " (NVL(6387.7, 0) * ACOS((sin(NVL(:latitude, 0) / 57.29577951) * SIN(NVL(s.latitude, 0) / 57.29577951)) + (COS(NVL(:latitude, 0) / 57.29577951) * COS(NVL(s.latitude, 0) / 57.29577951) * COS(NVL(s.longitude, 0) / 57.29577951 - NVL(:longitude, 0) / 57.29577951)))) ";
        }

        if (isCheckDistrict) {
            sb.append(" SELECT new co.siten.myvtg.bean.StoresBean(s.name,s.addr,s.openTime,"
                    + "p.name as provinceName,d.name as districtName,s.isdn," + distance + " as distance "
                    + ", s.latitude, s.longitude) " + " FROM Shop s,Area p,Area d "
                    + " WHERE (s.provinceId = p.province  AND p.district is null AND p.precinct is null) "
                    + " AND (s.provinceId=d.province AND s.districtId = d.district AND d.precinct is null) "
                    + " AND s.longitude is not null AND s.latitude is not null " + " AND " + distance
                    + " < :maxDistance " + " order by distance asc ");
        } else {
            sb.append(" SELECT new co.siten.myvtg.bean.StoresBean(s.name,s.addr,s.openTime,"
                    + "p.name as provinceName, p.name as districtName,s.isdn," + distance + " as distance "
                    + ", s.latitude, s.longitude) " + " FROM Shop s,Area p"
                    + " WHERE (s.provinceId = p.province  AND p.district is null AND p.precinct is null) "
                    + " AND s.longitude is not null AND s.latitude is not null " + " AND " + distance
                    + " < :maxDistance " + " order by distance asc ");
        }

        Query query = getSession().createQuery(sb.toString());
        query.setFloat("maxDistance", maxDistance);
        if ("0".equalsIgnoreCase(distance)) {
            query.setFetchSize(20);
        } else {
            query.setBigDecimal("longitude", longitude);
            query.setBigDecimal("latitude", latitude);
        }
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<StoresBean> findStoreByAddr(BigDecimal longitude, BigDecimal latitude, String provinceId,
                                            String districtId, Boolean isCheckDistrict) {
        StringBuilder sb = new StringBuilder();
        String distance = "0";
        if (longitude != null && latitude != null) {
            distance = " (NVL(6387.7, 0) * ACOS((sin(NVL(:latitude, 0) / 57.29577951) * SIN(NVL(s.latitude, 0) / 57.29577951)) + (COS(NVL(:latitude, 0) / 57.29577951) * COS(NVL(s.latitude, 0) / 57.29577951) * COS(NVL(s.longitude, 0) / 57.29577951 - NVL(:longitude, 0) / 57.29577951)))) ";
        }

        if (isCheckDistrict && provinceId != null && districtId != null) {
            sb.append(" SELECT new co.siten.myvtg.bean.StoresBean(s.name,s.addr,s.openTime,"
                    + "p.name as provinceName,d.name as districtName,s.isdn," + distance + " as distance "
                    + ", s.latitude, s.longitude) " + " FROM Shop s,Area p,Area d "
                    + " WHERE s.provinceId = :provinceId " + " AND s.districtId = :districtId "
                    + " AND (s.provinceId = p.province AND p.district is null AND p.precinct is null) "
                    + " AND (s.provinceId = d.province AND s.districtId = d.district AND d.precinct is null) "
                    + " AND s.longitude is not null AND s.latitude is not null " + " order by distance asc ");
        } else {
            sb.append(" SELECT new co.siten.myvtg.bean.StoresBean(s.name,s.addr,s.openTime,"
                    + "p.name as provinceName,p.name as districtName,s.isdn," + distance + " as distance "
                    + ", s.latitude, s.longitude) " + " FROM Shop s,Area p " + " WHERE s.provinceId = :provinceId "
                    + " AND s.longitude is not null AND s.latitude is not null "
                    + " AND (s.provinceId = p.province AND p.district is null AND p.precinct is null) "
                    + " order by distance asc ");
        }

        Query query = getSession().createQuery(sb.toString());
        query.setString("provinceId", provinceId);
        if (isCheckDistrict && provinceId != null && districtId != null) {
            query.setString("districtId", districtId);
        }

        if ("0".equalsIgnoreCase(distance)) {
            query.setFetchSize(20);
        } else {
            query.setBigDecimal("longitude", longitude);
            query.setBigDecimal("latitude", latitude);
        }
        return query.list();
    }

    public NewsDetailBean getNewsDetail(String newsId) {
        StringBuilder sb = new StringBuilder(
                " SELECT DISTINCT new co.siten.myvtg.bean.NewsDetailBean(s.id,s.name,s.imgDesUrl,s.content) "
                        + "FROM New s WHERE s.id=:newsId");
        Query query = getSession().createQuery(sb.toString());
        query.setString("newsId", newsId);
        return (NewsDetailBean) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<NewsBean> getNews(String language, Integer pageSize, Integer pageNum) {
        StringBuilder sb = new StringBuilder(
                " SELECT new co.siten.myvtg.bean.NewsBean(s.id,s.name,s.iconUrl,s.publishedTime) FROM New s "
                        + "WHERE s.language=:language AND s.approved = 1 AND s.effectiveTime <= sysdate AND s.expiredTime >= sysdate AND s.status = 1 "
                        + "ORDER BY s.publishedTime DESC");
        Query query = getSession().createQuery(sb.toString());
        query.setString("language", language);
        if (pageSize != 0 && pageNum != null) {
            query.setFirstResult(pageSize * (pageNum - 1));
            query.setMaxResults(pageSize);
        }
        return query.list();
    }

    public DataPackageInfoBean getDataPackageInfo(Sub sub, String packageCode, String lang, String serviceTypes,
                                                  List<String> listRegisteredServiceCodes) {
        // if (isUnitelMarket()) {
        StringBuilder sb = new StringBuilder(
                " SELECT DISTINCT new co.siten.myvtg.bean.DataPackageInfoBean(s.name," + " s.code, " + " s.fullDes,"
                        + " s.imgDesUrl," + " 0," + " s.actionType) " + " FROM co.siten.myvtg.model.myvtg.ServiceA s"
                        + " WHERE s.code = :packageCode" + " AND s.language= :language" + " AND s.serviceType IN "
                        + serviceTypes + " AND s.approved= 1" + " AND s.status = 1");

        Query query = getSession().createQuery(sb.toString());
        query.setString("packageCode", packageCode);
        query.setString("language", lang);

        DataPackageInfoBean bean = (DataPackageInfoBean) query.uniqueResult();
        if (bean == null) {
            return bean;
        }

        if (listRegisteredServiceCodes == null || listRegisteredServiceCodes.isEmpty()) {
            listRegisteredServiceCodes = new ArrayList<>();
        }

        if (CommonUtil.isEmpty(listRegisteredServiceCodes)) {
            bean.setRegState(Constants.SUBCRIBER_STATE_CANCEL);
        } else {
            if (isContainServiceCode(bean.getCode(), listRegisteredServiceCodes)) {
                bean.setRegState(Constants.SUBCRIBER_STATE_REGISTERED);
            } else {
                bean.setRegState(Constants.SUBCRIBER_STATE_CANCEL);
            }
        }
        return bean;

        // } else {
        // StringBuilder sb = new StringBuilder(
        // " SELECT DISTINCT new
        // co.siten.myvtg.bean.DataPackageInfoBean(s.name," + " s.code, " + "
        // s.fullDes,"
        // + " s.imgDesUrl," + " 0," + " s.actionType) " + " FROM
        // co.siten.myvtg.model.myvtg.ServiceA s"
        // + " WHERE s.code = :packageCode" + " AND s.language= :language" + "
        // AND s.serviceType IN "
        // + serviceTypes + " AND s.approved= 1" + " AND s.status = 1");
        //
        // StringBuilder sb1 = new StringBuilder("SELECT DISTINCT scr "
        // + " FROM co.siten.myvtg.model.myvtg.ServiceA s, SubServiceA ss,
        // Subscriber scr "
        // + " WHERE s.code = :packageCode" + " AND s.language= :language" + "
        // AND " + "s.serviceType IN "
        // + serviceTypes + " AND s.approved= 1" + " AND s.status = 1 " + " AND
        // s.id= ss.serviceId"
        // + " AND ss.approved = 1" + " AND ss.language = :language" + " AND
        // ss.status= 1"
        // + " AND ss.code = scr.id.subServiceCode" + " AND scr.id.isdn = :isdn"
        // + " AND (scr.state = 1 OR scr.state = 2 )");
        //
        // Query query = getSession().createQuery(sb.toString());
        // query.setString("packageCode", packageCode);
        // query.setString("language", lang);
        //
        // DataPackageInfoBean bean = (DataPackageInfoBean)
        // query.uniqueResult();
        //
        // Query query1 = getSession().createQuery(sb1.toString());
        // query1.setString("isdn", sub.getIsdn());
        // query1.setString("packageCode", packageCode);
        // query1.setString("language", lang);
        //
        // List<Subscriber> srcList = query1.list();
        // if (CommonUtil.isEmpty(srcList)) {
        // if (bean != null)
        // bean.setRegState(0);
        // } else {
        // if (bean != null)
        // bean.setRegState(srcList.get(0).getState());
        // }
        // return bean;
        // }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<AdvertBannerBean> getTopAdvertBanner(String language, int limit) {
        StringBuilder sb = new StringBuilder(
                " SELECT new co.siten.myvtg.bean.AdvertBannerBean(s.advImgUrl,s.sourceLink) "
                        + "FROM AdvertBanner s WHERE s.showPage=1 AND s.approved = 1 AND s.status = 1 "
                        + "ORDER BY s.lastUpdatedTime DESC ");
        Query query = getSession().createQuery(sb.toString());
        query.setFirstResult(0);
        query.setMaxResults(limit);
        return (ArrayList<AdvertBannerBean>) query.list();
    }

    @SuppressWarnings("unchecked")
    public ArrayList<ExperienceLinkBean> getTopExperienceLink(String language, int limit) {
        StringBuilder sb = new StringBuilder(
                " SELECT new co.siten.myvtg.bean.ExperienceLinkBean(s.name,s.shortDes,s.sourceLink,s.iconUrl) "
                        + "FROM ExperienceLink s WHERE s.language =:language AND s.approved = 1 AND s.status = 1 "
                        + "ORDER BY s.lastUpdatedTime DESC ");
        Query query = getSession().createQuery(sb.toString());
        query.setString("language", language);
        query.setFirstResult(0);
        query.setMaxResults(limit);
        return (ArrayList<ExperienceLinkBean>) query.list();
    }

    @SuppressWarnings("unchecked")
    public ArrayList<ApplicationBean> getAllApps(String language, Integer limit) {
        StringBuilder sb = new StringBuilder(
                " SELECT new co.siten.myvtg.bean.ApplicationBean(s.id,s.name,s.shortDes,s.fullDes,s.iconUrl,s.iosLink,s.androidLink) "
                        + "FROM Application s WHERE s.language =:language AND s.approved = 1 AND s.status = 1 "
                        + "ORDER BY s.lastUpdatedTime DESC ");
        Query query = getSession().createQuery(sb.toString());
        query.setString("language", language);
        query.setFirstResult(0);
        query.setMaxResults(limit);
        return (ArrayList<ApplicationBean>) query.list();
    }

    public WebserviceBean getWebserviceByServiceCodeAndActionType(String serviceCode, int actionType) {
        StringBuilder sb = new StringBuilder("SELECT a FROM Action a WHERE " + "a.id.subServiceCode = :serviceCode"
                + "	AND a.id.actionType  = :actionType" + "	AND a.status= 1 ");
        Action action = null;
        Webservice ws = null;
        Query query = getSession().createQuery(sb.toString());
        query.setString("serviceCode", serviceCode);
        query.setInteger("actionType", actionType);
        query.setFirstResult(0);
        query.setMaxResults(1);
        List<Action> l = query.list();
        if (!CommonUtil.isEmpty(l)) {
            action = l.get(0);
        }

        if (action != null) {
            StringBuilder sb1 = new StringBuilder("SELECT ws FROM  Webservice ws WHERE ws.id = :wsId");
            Query query1 = getSession().createQuery(sb1.toString());
            query1.setString("wsId", action.getWsId());
            query1.setFirstResult(0);
            query1.setMaxResults(1);
            List<Webservice> l1 = query1.list();

            if (l1 != null && l1.size() > 0) {
                ws = l1.get(0);
            }
        }

        if (ws != null) {
            return new WebserviceBean(ws, action);
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<ServiceBean> getAllDataPackages(String language, String isdn, String serviceTypes,
                                                String listRegisteredServiceCodes) {
        // if (isUnitelMarket()) {

        if (listRegisteredServiceCodes == null || listRegisteredServiceCodes.isEmpty()) {
            listRegisteredServiceCodes = "(' ')";
        }

        StringBuilder sb = new StringBuilder(
                " SELECT new co.siten.myvtg.bean.ServiceBean(s.name,s.code,s.shortDes,s.iconUrl, 0) "
                        + " FROM co.siten.myvtg.model.myvtg.ServiceA s" + " WHERE s.id IN "
                        + " (SELECT DISTINCT ss.serviceId " + " FROM SubServiceA ss " + " WHERE ss.code NOT IN "
                        + listRegisteredServiceCodes + " AND ss.language = :language " + " AND ss.status = 1 "
                        + " AND ss.approved = 1)" + " AND s.language =:language " + " AND s.serviceType IN "
                        + serviceTypes + " AND s.status = 1" + " AND s.approved = 1 " + " ORDER BY s.name");
        Query query = getSession().createQuery(sb.toString());
        query.setString("language", language);
        // query.setString("isdn", isdn);
        return query.list();

    }

    public List<ServiceBean> getAllProcessingDataPackages(String language, String isdn, String serviceTypes) {
        StringBuilder sb = new StringBuilder(
                " SELECT new co.siten.myvtg.bean.ServiceBean(s.name,s.code,s.shortDes,s.iconUrl) "
                        + " FROM co.siten.myvtg.model.myvtg.ServiceA s WHERE s.id IN "
                        + " (SELECT DISTINCT ss.serviceId " + " FROM SubServiceA ss" + " WHERE ss.code IN"
                        + " (SELECT sb.id.subServiceCode FROM Subscriber sb " + " WHERE sb.id.isdn = :isdn"
                        + " AND sb.state  = 2" + " AND sb.status = 1)" + " AND ss.language = :language"
                        + " AND ss.status = 1" + " AND ss.approved = 1)" + " AND s.language =:language"
                        + " AND s.serviceType IN " + serviceTypes + " AND s.status = 1" + " AND s.approved = 1"
                        + " ORDER BY s.name");
        Query query = getSession().createQuery(sb.toString());
        query.setString("language", language);
        query.setString("isdn", isdn);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<ServiceBean> getServicesByGroup(String language, String isdn, String serviceGroupCode,
                                                String listRegisteredServiceCodes, String validity) {

        if (listRegisteredServiceCodes == null || listRegisteredServiceCodes.isEmpty()) {
            listRegisteredServiceCodes = "(' ')";
        }
        StringBuilder sb = new StringBuilder(" SELECT new co.siten.myvtg.bean.ServiceBean(s.name," + " s.code,"
                + " s.shortDes," + " CASE WHEN s.actionType = null THEN 0 ELSE s.actionType END," + " s.iconUrl,"
                + " s.isMultPlan, 0, s.validity, s.unit,s.price)" + " FROM co.siten.myvtg.model.myvtg.ServiceA s,ServiceGroupA sg " + " WHERE s.id IN "
                + "(SELECT DISTINCT ss.serviceId " + " FROM SubServiceA ss " + " WHERE ss.code NOT IN"
                + listRegisteredServiceCodes + " AND ss.language = :language" + " AND ss.approved = 1) "
                + " AND s.language =:language " + " AND s.approved = 1 " + " AND s.status = 1" + " AND sg.status = 1 "
                + " AND sg.code =:serviceGroupId " + " AND sg.id = s.serviceGroupId " + " AND sg.language =:language"
                + " ORDER BY s.serviceOrder asc)");
        Query query = getSession().createQuery(sb.toString());
        query.setString("language", language);
        // query.setString("isdn", isdn);
        query.setString("serviceGroupId", serviceGroupCode);
        return query.list();

    }

    @SuppressWarnings("unchecked")
    public List<ServiceBean> getProcessingServicesByGroup(String language, String isdn, String serviceGroupCode) {
        StringBuilder sb = new StringBuilder(" SELECT new co.siten.myvtg.bean.ServiceBean(s.name," + " s.code,"
                + " s.shortDes," + " CASE WHEN s.actionType = null THEN 0 ELSE 1 END," + " s.iconUrl,"
                + " s.isMultPlan) " + " FROM co.siten.myvtg.model.myvtg.ServiceA s,ServiceGroupA sg" + " WHERE s.id IN "
                + "(SELECT DISTINCT ss.serviceId" + " FROM SubServiceA ss" + " WHERE ss.code IN"
                + " (SELECT sb.id.subServiceCode" + " FROM Subscriber sb " + " WHERE sb.id.isdn = :isdn"
                + " AND sb.state  = 2" + " AND sb.status = 1)" + " AND ss.language = :language"
                + " AND ss.approved = 1)" + " AND s.language =:language" + " AND s.approved = 1"
                + " AND sg.code =:serviceGroupId" + " AND sg.id = s.serviceGroupId" + " AND sg.language =:language"
                + " ORDER BY s.name)");
        Query query = getSession().createQuery(sb.toString());
        query.setString("language", language);
        query.setString("isdn", isdn);
        query.setString("serviceGroupId", serviceGroupCode);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<CareerBean> getCareers(String language) {

        StringBuilder sb = new StringBuilder();

        if (Constants.LOCAL_LANGUAGE.equals(language)) {
            sb.append(" SELECT new co.siten.myvtg.bean.CareerBean(s.id, CASE WHEN s.nameLc is null THEN s.name ELSE s.nameLc END) FROM Job s");
        } else {
            sb.append(" SELECT new co.siten.myvtg.bean.CareerBean(s.id,s.name ) FROM Job s ");
        }

//		StringBuilder sb = new StringBuilder(
//				" SELECT new co.siten.myvtg.bean.CareerBean(s.id,s.name) FROM Job s WHERE s.language=:language");
        Query query = getSession().createQuery(sb.toString());
//		query.setString("language", language);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<PromotionBean> getNewPromotions(String language, Integer pageSize, Integer pageNum) {
        StringBuilder sb = new StringBuilder(
                " SELECT new co.siten.myvtg.bean.PromotionBean(s.name,s.id,s.shortDes,s.iconUrl,s.publishedTime,s.actionType) FROM Promotion s "
                        + "WHERE s.language=:language AND s.approved = 1 AND s.effectiveTime <= sysdate AND s.expiredTime >=sysdate AND s.status = 1 "
                        + "ORDER BY s.publishedTime DESC");
        Query query = getSession().createQuery(sb.toString());
        query.setString("language", language);
        if (pageSize != 0 && pageNum != null) {
            query.setFirstResult(pageSize * (pageNum - 1));
            query.setMaxResults(pageSize);
        }
        return query.list();
    }

    public PromotionDetailBean getPromotionInfo(String language, String packageCode) {
        StringBuilder sb = new StringBuilder(
                " SELECT DISTINCT new co.siten.myvtg.bean.PromotionDetailBean(s.name,s.id,s.fullDes,s.imgDesUrl,s.publishedTime,s.actionType) "
                        + "FROM Promotion s WHERE s.language=:language AND id =:packageCode");
        Query query = getSession().createQuery(sb.toString());
        query.setString("language", language);
        query.setString("packageCode", packageCode);
        return (PromotionDetailBean) query.uniqueResult();
    }

    public String getAppParam(String name) {
        StringBuilder sb = new StringBuilder("SELECT p FROM co.siten.myvtg.model.myvtg.AppParam p WHERE p.name=:name");
        Query query = getSession().createQuery(sb.toString());
        query.setString("name", name);

        try {
            List<AppParam> appList = query.list();
            if (!CommonUtil.isEmpty(appList)) {
                return appList.get(0).getValue();
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    public AppParam getAppParamObject(String name) {
        StringBuilder sb = new StringBuilder("SELECT p FROM co.siten.myvtg.model.myvtg.AppParam p WHERE p.name=:name");
        Query query = getSession().createQuery(sb.toString());
        query.setString("name", name);

        try {
            List<AppParam> appList = query.list();
            if (!CommonUtil.isEmpty(appList)) {
                return appList.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    public String getAppParamByVersion(String name, String version) {
        StringBuilder sb = new StringBuilder(
                "SELECT p FROM co.siten.myvtg.model.myvtg.AppParam p WHERE p.name=:name and p.appVersion= :appVersion");
        Query query = getSession().createQuery(sb.toString());
        query.setString("name", name);
        query.setString("appVersion", version);

        try {
            List<AppParam> appList = query.list();
            if (!CommonUtil.isEmpty(appList)) {
                return appList.get(0).getValue();
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    public SubPrivilegeInfoBean getSubPrivilegeInfo(String isdn, Long subId) {
        StringBuilder sb = new StringBuilder(
                "SELECT DISTINCT new co.siten.myvtg.bean.SubPrivilegeInfoBean(s.isVip,s.markRate,s.markExchange) "
                        + "FROM SubCycleRe s WHERE s.subId=:subId AND s.status=1");
        Query query = getSession().createQuery(sb.toString());
        query.setBigDecimal("subId", new BigDecimal(subId));
        return (SubPrivilegeInfoBean) query.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public List<ServiceBean> getCurrentUsedAllServices(String language, String isdn,
                                                       String listRegisteredServiceCodes) {
        if (listRegisteredServiceCodes == null || listRegisteredServiceCodes.isEmpty()) {
            listRegisteredServiceCodes = "(' ')";
        }

        StringBuilder sb = new StringBuilder(
                " SELECT new co.siten.myvtg.bean.ServiceBean(s.name,s.code,s.shortDes,s.iconUrl,'Validity: ' ||s.validity, "
                        + "CASE WHEN ss.name LIKE '%AUTO %' AND ss.name NOT LIKE '%NON-AUTO %' THEN '1' ELSE '0' END) "
                        + "FROM co.siten.myvtg.model.myvtg.ServiceA s,SubServiceA ss WHERE ss.code IN "
                        + listRegisteredServiceCodes
                        + " AND ss.language = :language AND s.language =:language AND ss.status= 1 AND s.status=1 AND s.id=ss.serviceId "
                        + "ORDER BY s.name,s.code,s.shortDes,s.iconUrl");
        Query query = getSession().createQuery(sb.toString());
        query.setString("language", language);
        // query.setString("isdn", isdn);
        return query.list();
    }

    public List<ServiceAutoRenewBean> getCurrentUserAutoRenewAllServices(String language,
                                                                         String listRegisteredServiceCodes) {
        if (listRegisteredServiceCodes == null || listRegisteredServiceCodes.isEmpty()) {
            listRegisteredServiceCodes = "(' ')";
        }
        /*StringBuilder sb = new StringBuilder(
                " SELECT DISTINCT new co.siten.myvtg.bean.ServiceAutoRenewBean(s.name,s.code,s.shortDes,s.iconUrl, s.validity, s.unit, s.price, "
                        + "CASE WHEN ss.autoRenew = 1 THEN '1' ELSE ( CASE WHEN ss.autoRenew = 0 and "
                        + "(SELECT sum (ss1.autoRenew) from co.siten.myvtg.model.myvtg.SubServiceA ss1 where ss1.serviceId = ss.serviceId GROUP BY ss1.serviceId) > 0 "
                        + "THEN '0' ELSE NULL END) END, ss.code,"
                        + "(SELECT ss1.code FROM co.siten.myvtg.model.myvtg.SubServiceA ss1 where ss1.serviceId = ss.serviceId and ss1.autoRenew = 0 and s.showSubService = 0 "
                        + "and (SELECT sum (ss2.autoRenew) from co.siten.myvtg.model.myvtg.SubServiceA ss2 where ss2.serviceId = ss.serviceId GROUP BY ss2.serviceId) > 0 and rownum = 1),"
                        + "(SELECT ss1.code FROM co.siten.myvtg.model.myvtg.SubServiceA ss1 where ss1.serviceId = ss.serviceId and ss1.autoRenew = 1 and s.showSubService = 0 and rownum = 1)) "
                        + "FROM co.siten.myvtg.model.myvtg.ServiceA s,SubServiceA ss WHERE ss.code IN "
                        + listRegisteredServiceCodes
                        + " AND ss.language = :language AND s.language =:language AND ss.status= 1 AND s.status=1 AND s.id=ss.serviceId "
                        + "ORDER BY s.name,s.code,s.shortDes,s.iconUrl");*/
        StringBuilder sb = new StringBuilder(
                " SELECT DISTINCT new co.siten.myvtg.bean.ServiceAutoRenewBean(s.name,s.code,s.shortDes,s.iconUrl, s.validity, s.unit, s.price, "
                        + "CASE WHEN ss.name LIKE 'NON-AUTO%' THEN '0' ELSE ( CASE WHEN ss.name LIKE 'AUTO%' THEN '1' ELSE NULL END) END, ss.code,"
                        + "(SELECT ss1.code FROM SubServiceA ss1 where ss1.serviceId = ss.serviceId and ss1.name LIKE 'NON-AUTO%' and rownum = 1),"
                        + "(SELECT ss1.code FROM SubServiceA ss1 where ss1.serviceId = ss.serviceId and ss1.name LIKE 'AUTO%' and rownum = 1)) "
                        + "FROM co.siten.myvtg.model.myvtg.ServiceA s,SubServiceA ss WHERE ss.code IN "
                        + listRegisteredServiceCodes
                        + " AND ss.language = :language AND s.language =:language AND ss.status= 1 AND s.status=1 AND s.id=ss.serviceId AND s.price is not null AND s.price != 0 "
                        + " ORDER BY s.name,s.code,s.shortDes,s.iconUrl");
        Query query = getSession().createQuery(sb.toString());
        query.setString("language", language);
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<GiftBean> getGiftsByProvince(Integer provinceId) {
        StringBuilder sb = new StringBuilder(
                "SELECT new co.siten.myvtg.bean.GiftBean(s.name,s.code,s.mark,'') " + "FROM Gift s WHERE s.status = 1");
        Query query = getSession().createQuery(sb.toString());
        return query.list();
    }

    public Integer doExchangeGift(BigDecimal subId, String isdn, String subType, String giftCode, Integer provinceId,
                                  Integer districtId, Integer precinctId, String addr, Date time, BigDecimal markExchange) {
        Gift gift = getGiftByCode(giftCode);
        // check mark_exchange
        SubCycleRe scr = getSubCycleReBySubId(subId);
        if (scr == null) {
            return 3;
        }
        BigDecimal curMarkExchange = scr.getMarkExchange();
        if (curMarkExchange.compareTo(markExchange) >= 0) {
            scr.setMarkExchange(curMarkExchange.subtract(markExchange));
            persist(scr);
            // save log gift_audit
            GiftAudit ga = new GiftAudit();
            // check exist gift by code
            if (gift == null) {
                return 1;
            }
            ga.setGiftId(gift.getGiftId());
            ga.setSubId(subId);
            ga.setSubType(subType);
            ga.setMbId(null);
            ga.setCreateDate(new Date());
            ga.setEffectDate(time);
            ga.setEventId(null);
            ga.setStatus("1");
            ga.setReasonNotGift(null);
            ga.setReasonId(null);
            ga.setUserCreate(Constants.MYVTG);
            ga.setUserUpdate(Constants.MYVTG);
            ga.setStaffPresent(Constants.MYVTG);
            ga.setClassId(null);
            ga.setNote(null);
            ga.setScrId(null);
            ga.setGiftType(null);
            persist(ga);
        } else {
            return 2;
        }

        return 0;
    }

    public Gift getGiftByCode(String giftCode) {
        StringBuilder sb = new StringBuilder(
                "SELECT DISTINCT g from co.siten.myvtg.model.myvtg.Gift g Where code=:code");
        Query query = getSession().createQuery(sb.toString());
        query.setString("code", giftCode);
        return (Gift) query.uniqueResult();
    }

    public SubCycleRe getSubCycleReBySubId(BigDecimal subId) {
        StringBuilder sb = new StringBuilder("Select DISTINCT s FROM SubCycleRe s where s.subId =:subId");
        Query query = getSession().createQuery(sb.toString());
        query.setBigDecimal("subId", subId);
        return (SubCycleRe) query.uniqueResult();
    }

    @Deprecated
    public SubCycleRe getSubCycleReByIsdn(String isdn) {
        StringBuilder sb = new StringBuilder("Select DISTINCT s FROM SubCycleRe s where s.subId =:isdn");
        Query query = getSession().createQuery(sb.toString());
        query.setString("isdn", isdn);
        return (SubCycleRe) query.uniqueResult();
    }

    public FunctionPageServiceBean getServiceFuntionPage(String language, String serviceCode, String functionPageCode) {
        StringBuilder sb = new StringBuilder("Select new co.siten.myvtg.bean.FunctionPageServiceBean(s.content) "
                + "FROM FunctionPage s WHERE s.serviceCode=:serviceCode AND s.code=:functionPageCode "
                + "AND s.language=:language AND s.approved=1 AND s.status=1");
        Query query = getSession().createQuery(sb.toString());
        query.setString("language", language);
        query.setString("serviceCode", serviceCode);
        query.setString("functionPageCode", functionPageCode);
        return (FunctionPageServiceBean) query.uniqueResult();
    }

    public List<Subscriber> getSubscriberListByIsdn(String isdn) {
        List<Subscriber> result;

        StringBuilder sb = new StringBuilder("SELECT scr FROM co.siten.myvtg.model.myvtg.Subscriber scr "
                + " WHERE scr.id.isdn = :isdn " + " AND scr.status = 1 ");

        Query query = getSession().createQuery(sb.toString());
        query.setString("isdn", isdn);

        result = query.list();

        return result;
    }

    public Subscriber getSubscriberById(Long subId, String subServiceCode) {

        try {
            StringBuilder sb = new StringBuilder("SELECT scr FROM co.siten.myvtg.model.myvtg.Subscriber scr "
                    + " WHERE scr.id.subServiceCode = :subServiceCode AND scr.subId = :subId ");

            Query query = getSession().createQuery(sb.toString());
            query.setLong("subId", subId);
            query.setString("subServiceCode", subServiceCode);

            return (Subscriber) query.uniqueResult();
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    public Boolean insertBatchSubscriber(String isdn, Long subId,
                                         List<SubscriberSyncInfoBean> subscriberSyncInfoBeans) {
        Session session = getSession();
        for (int index = 0; index < subscriberSyncInfoBeans.size(); index++) {
            SubscriberSyncInfoBean item = subscriberSyncInfoBeans.get(index);
            Subscriber scr = new Subscriber();
            SubscriberPK id = new SubscriberPK();
            id.setIsdn(isdn);
            id.setSubServiceCode(item.getSubServiceCode());

            scr.setId(id);
            scr.setSubId(subId);
            scr.setRegisterTime(item.getRegisterTime());
            scr.setState(item.getState());
            scr.setStatus(1);
            scr.setCreatedBy("SYNC PROCESS - 01");
            scr.setLastUpdatedTime(new Date());

            session.persist(scr);
            if (index % 50 == 0) {
                session.flush();
                session.clear();
            }
        }

        session.flush();
        session.clear();

        return true;
    }

    public Boolean insertBatchSubscriber(List<Subscriber> newItems) {
        Session session = getSession();
        for (int index = 0; index < newItems.size(); index++) {
            session.persist(newItems.get(index));
            if (index % 100 == 0) {
                session.flush();
                session.clear();
            }
        }

        session.flush();
        session.clear();

        return true;
    }

    public Boolean updateBatchSubscriber(List<Subscriber> subscribers) {
        Session session = getSession();
        for (int index = 0; index < subscribers.size(); index++) {
            Subscriber scr = subscribers.get(index);
            session.update(scr);
            if (index % 100 == 0) {
                session.flush();
                session.clear();
            }
        }

        session.flush();
        session.clear();

        return true;
    }

    public AreaLongLatBean findProvinceById(Integer provinceId) {
        try {
            StringBuilder sb = new StringBuilder(
                    " SELECT new co.siten.myvtg.bean.AreaLongLatBean(a.longitude, a.latitude) FROM Area a WHERE a.province = :province AND a.district is null AND a.precinct is null ");
            Query query = getSession().createQuery(sb.toString());
            query.setInteger("province", provinceId);

            return (AreaLongLatBean) query.uniqueResult();
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    // private Boolean isUnitelMarket() {
    // String marketName =
    // environment.getProperty(Constants.MARKET_NAME_CONFIG);
    // return marketName != null &&
    // marketName.equalsIgnoreCase(Constants.MARKET_NAME_UNITEL);
    // }
    public List<SubServiceA> getRegisteredPackageData(List<String> relProCodes, String language) {
        try {
            if (CommonUtil.isEmpty(relProCodes)) {
                return null;
            }
            StringBuilder sb = new StringBuilder("SELECT ss FROM co.siten.myvtg.model.myvtg.ServiceA s, SubServiceA ss "
                    + " WHERE ss.serviceId= s.id AND s.serviceType !=0 and ss.code in :relProCodes and ss.language= :language and s.language = :language and ss.status = 1 and s.status = 1");

            Query query = getSession().createQuery(sb.toString());
            query.setString("language", language);
            query.setParameterList("relProCodes", relProCodes);

            return (List<SubServiceA>) query.list();
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    public List<AccConfig> getAllAccConfigByLanguage(String language) {
        StringBuilder sb = new StringBuilder(" SELECT s  FROM AccConfig s WHERE s.language = :language");
        Query query = getSession().createQuery(sb.toString());
        query.setString("language", language);
//        query.setCacheable(true);
        return query.list();
    }

    /*
     Function Notification
     */
    public DeviceToken getDeviceTokenByIsdn(String isdn) {
        try {
            String sb = "SELECT dt FROM co.siten.myvtg.model.myvtg.DeviceToken dt WHERE dt.isdn = :isdn ";
            Query query = getSession().createQuery(sb);
            query.setString("isdn", isdn);

            return (DeviceToken) query.uniqueResult();
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    public Notification findNotificationById(Long id) {
        try {
            StringBuilder sb = new StringBuilder(" SELECT a from Notification a WHERE a.id= :id");
            Query query = getSession().createQuery(sb.toString());
            query.setLong("id", id);

            return (Notification) query.uniqueResult();
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<AccountNotification> getListAccountNotifyByIsdnAndNoti(String isdn, Long notificationId) {
        try {
            String sb = " SELECT a from co.siten.myvtg.model.myvtg.AccountNotification a "
                    + " WHERE a.isdn= :isdn and a.notificationId = :notificationId";
            Query query = getSession().createQuery(sb);
            query.setString("isdn", isdn);
            query.setLong("notificationId", notificationId);
            return query.list();
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<NotificationBean> getListNotificationByIsdn(String isdn, Integer pageSize, Integer pageNum) {
        try {
            String sb = " SELECT new co.siten.myvtg.bean.NotificationBean(a.id, n.message, n.id,n.title, n.serviceCode,"
                    + " 													n.message, n.startTime, n.endTime, a.isRead,"
                    + " 													n.icon, n.image, n.notificationType, "
                    + "														a.insertTime, a.params) "
                    + " FROM co.siten.myvtg.model.myvtg.Notification n, "
                    + "		 co.siten.myvtg.model.myvtg.AccountNotification a "
                    + " WHERE n.id = a.notificationId AND n.isSave = 1 "
                    + "		  AND a.isdn = :isdn  AND n.endTime >=sysdate "
                    + " ORDER BY a.insertTime desc";
            Query query = getSession().createQuery(sb);
            query.setString("isdn", isdn);
            if (pageSize != 0 && pageNum != null) {
                query.setFirstResult(pageSize * (pageNum - 1));
                query.setMaxResults(pageSize);
            }
            return query.list();
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    /**
     * randomOTP
     *
     * @return
     * @throws Exception
     * @author daibq
     */
    @SuppressWarnings("unchecked")
    public Integer randomOTP() throws Exception {
        logger.info("begin method randomPin of MyvtgMasterDataDao");
        try {
            StringBuilder sql = new StringBuilder();
            Query query = null;
            sql.append("select PCK_RANDOM_OTP.random_otp from dual ");
            query = getSession().createSQLQuery(sql.toString());
            logger.info("end method randomPin of MyvtgMasterDataDao");
            if (!DataUtil.isNullOrEmpty(query.list())) {
                return Integer.valueOf(query.list().get(0).toString());
            }
        } catch (HibernateException ex) {
            throw ex;
        }
        return null;
    }

    /**
     * updateSocialNetwork
     *
     * @param isdn
     * @param socialNetwork
     * @return
     * @author daibq
     */
    @SuppressWarnings("unchecked")
    public boolean updateSocialNetwork(String isdn, String socialNetwork) {
        String sb = " UPDATE Sub SET socialNetwork= :socialNetwork  where isdn = :isdn";
        Query query = getSession().createQuery(sb);
        query.setString("socialNetwork", socialNetwork);
        query.setString("isdn", isdn);
        int result = query.executeUpdate();
        return result > 0;
    }

    /**
     * updateSocialNetwork
     *
     * @param isdn
     * @param comman
     * @return
     * @throws java.lang.Exception
     * @author daibq
     */
    @SuppressWarnings("unchecked")
    public List<Object> getHistoryTopup(String isdn, String comman) throws Exception {
        List<Object> list = new ArrayList<>();
        String sb = "SELECT MSISDN isdn  FROM TRANSACTION_LOG WHERE REFILL_ISDN =:isdn and COMMAND = :comman and ERROR_CODE = 0 ORDER BY REQUEST_TIME desc";
        Query query = getSession().createSQLQuery(sb);
//                .addScalar("isdn", StringType.INSTANCE)
//                .setResultTransformer(Transformers.aliasToBean(IsdnInfoBean.class));
        query.setString("comman", comman);
        query.setString("isdn", isdn.trim());
        query.setMaxResults(5);
        //String.format("%%%s%%", isdn.trim())
        List listResult = query.list();
        if (!DataUtil.isNullOrEmpty(listResult)) {
            for (int i = 0; i < listResult.size(); i++) {
                IsdnInfoBean bean = new IsdnInfoBean();
                bean.setIsdn(listResult.get(i).toString());
                list.add(bean);
            }

        }
        return list;
    }

    /**
     * @param isdn
     * @param command
     * @return
     * @throws java.lang.Exception
     * @author yaangvu
     */
    @SuppressWarnings("unchecked")
    public Map<String, List<Object>> getHistoryTopUpNew(String isdn, String command) throws ParseException {

        List<Object> list = new ArrayList<>();

        String sb = "SELECT TRANSACTION_LOG.MSISDN isdn, CHARGE_HIS.REFILL_AMOUNT amount, CHARGE_HIS.REFILL_DATE refill_date "
                + "FROM TRANSACTION_LOG JOIN CHARGE_HIS ON CHARGE_HIS.TRANSACTION_ID = TRANSACTION_LOG.ID "
                + "WHERE CHARGE_HIS.REFILL_ISDN = :isdn and TRANSACTION_LOG.COMMAND = :command and TRANSACTION_LOG.ERROR_CODE = 0 AND CHARGE_HIS.RESULT_CODE = 0 "
                + "ORDER BY TRANSACTION_LOG.REQUEST_TIME desc ";
        Query query = getSession().createSQLQuery(sb)
                .addScalar("isdn", StringType.INSTANCE)
                .addScalar("amount", DoubleType.INSTANCE)
                .addScalar("refill_date", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(HistoryTopUpNewDto.class));
        query.setString("command", command);
        query.setString("isdn", isdn.trim());
        query.setMaxResults(500);
        List<Object> queryResult = query.list();

        Date last7days = DateUtils.addDays(new Date(), -7);
        Date last14days = DateUtils.addDays(new Date(), -14);
        Date last30days = DateUtils.addDays(new Date(), -30);

        Map<String, List<Object>> result = new HashMap<>();
        List<Object> his7days = new ArrayList<>();
        List<Object> his14days = new ArrayList<>();
        List<Object> his30days = new ArrayList<>();

        for (Object object : queryResult) {
            HistoryTopUpNewDto historyTopUpNewDto = (HistoryTopUpNewDto) object;
//            System.out.println(historyTopUpNewDto.getRefill_date());
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.0");
            Date historyDate = date.parse(historyTopUpNewDto.getRefill_date());

            if (historyDate.after(last7days)) {
                his7days.add(historyTopUpNewDto);
            } else if (historyDate.after(last14days)) {
                his14days.add(historyTopUpNewDto);
            } else if (historyDate.after(last30days)) {
                his30days.add(historyTopUpNewDto);
            }
        }

        result.put("7days", his7days);
        result.put("14days", his14days);
        result.put("30days", his30days);
        return result;
    }

    /**
     * checkLoginAfterUpdateVersion
     *
     * @param isdn
     * @param status
     * @param version
     * @return
     * @throws java.lang.Exception
     * @author daibq
     */
    @SuppressWarnings("unchecked")
    public List<InfoUpdateVsApp> getLoginAfterUpdateVersion(String isdn, Long status, String version) throws Exception {
        List<Object> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT i FROM InfoUpdateVsApp i WHERE i.isdn = :isdn ");
        if (!DataUtil.isNullObject(status)) {
            sql.append("AND i.status =:status ");
        }
        if (!DataUtil.isNullOrEmpty(version)) {
            sql.append("AND i.version =:version ");
        }
        Query query = getSession().createQuery(sql.toString());
        if (!DataUtil.isNullObject(status)) {
            query.setParameter("status", status);
        }
        if (!DataUtil.isNullOrEmpty(version)) {
            query.setParameter("version", version);
        }
        query.setParameter("isdn", isdn);
        return query.list();
    }

    /**
     * getAppParamByTypAndName type = 1 hoat dong; 0 khong hoat dong
     *
     * @param name
     * @return
     * @author
     */
    public String getAppParamByTypAndName(String name) {
        StringBuilder sb = new StringBuilder("SELECT p FROM co.siten.myvtg.model.myvtg.AppParam p WHERE p.name=:name and p.type = 1 ");
        Query query = getSession().createQuery(sb.toString());
        query.setString("name", name);

        try {
            List<AppParam> appList = query.list();
            if (!CommonUtil.isEmpty(appList)) {
                return appList.get(0).getValue();
            } else {
                return null;
            }
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    public Long getTotalRecordNewsCovid() {
        String sql = "SELECT count(*) FROM NewsCovid19Entity WHERE status <> 2";
        Query query = getSession().createQuery(sql);
        try {
            List<Long> result = query.list();
            if (!DataUtil.isNullOrEmpty(result)) {
                return result.get(0);
            }
            return 0L;
        } catch (Exception e) {
            logger.error("Cannot count total news about covid-19", e);
            return 0L;
        }
    }

    //phuonghc mua o day
    public List<NewsCovidDto> getNewsCovid19List(Long from, Long to) {
        String sql = "SELECT * FROM ("
                + "SELECT news_covid_id newsCovidId, title, message, TO_CHAR(create_date, 'dd/mm/yyyy hh24:mi:ss') createDate, notification_image notificationImage, topic, notification_type notificationType, link, status, description, time, RANK() OVER( ORDER BY create_date DESC) myrank FROM NEWS_COVIDS "
                + "WHERE status in (1,0)) t WHERE myrank BETWEEN :from AND :to";
        try {
            Query query;
            query = getSession().createSQLQuery(sql)
                    .addScalar("newsCovidId", StringType.INSTANCE)
                    .addScalar("title", StringType.INSTANCE)
                    .addScalar("message", StringType.INSTANCE)
                    .addScalar("createDate", StringType.INSTANCE)
                    .addScalar("time", IntegerType.INSTANCE)
                    .addScalar("link", StringType.INSTANCE)
                    .addScalar("notificationImage", StringType.INSTANCE)
                    .addScalar("topic", StringType.INSTANCE)
                    .addScalar("notificationType", StringType.INSTANCE)
                    .addScalar("status", StringType.INSTANCE)
                    .addScalar("description", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(NewsCovidDto.class));
            query.setLong("from", from);
            query.setLong("to", to);

            List<NewsCovidDto> result = query.list();
            if (!DataUtil.isNullObject(result)) {
                return result;
            }
            return null;
        } catch (Exception e) {
            logger.error("Cannot read news about covid-19", e);
            return null;
        }
    }

    public NotificationResult addNewsCovid19(NewsCovid19Entity entity) {
        NotificationResult result = new NotificationResult();
        Session session = getSession();
        try {
            Long id = getSequence("NEWS_COVIDS_SEQ");
            Long status = 0L;
            try {
                status = Long.parseLong(entity.getStatus());
            } catch (NumberFormatException e) {
                logger.info("Status is not a number", e);
            }
            String sql = "INSERT INTO NEWS_COVIDS (NEWS_COVID_ID, TITLE, MESSAGE, LINK, CREATE_DATE, NOTIFICATION_IMAGE, TOPIC, NOTIFICATION_TYPE, STATUS, DESCRIPTION, TIME) "
                    + "VALUES (?,?,?,?,sysdate,?,?,?,?,?,?)";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, id);
            query.setParameter(1, entity.getTitle());
            query.setParameter(2, entity.getMessage());
            query.setParameter(3, entity.getLink());
            query.setParameter(4, entity.getNotificationImage());
            query.setParameter(5, entity.getTopic());
            query.setParameter(6, entity.getNotificationType());
            query.setParameter(7, status);
            query.setParameter(8, entity.getDescription());
            query.setParameter(9, entity.getTime());

            int valueSuccess = query.executeUpdate();
            session.flush();
            result.setResult(valueSuccess);
            result.setNotificationId(id);
            return result;
        } catch (HibernateException e) {
            logger.error("Cannot read news about covid-19", e);
            result.setResult(0);
            return result;
        } catch (Exception e) {
            logger.error("Cannot read news about covid-19", e);
            result.setResult(0);
            return result;
        }
    }

    public int updateNewsCovid19(NewsCovid19Entity entity) {
        Session session = getSession();
        try {
            Long status = 0L;
            try {
                status = Long.parseLong(entity.getStatus());
            } catch (NumberFormatException e) {
                logger.info("Status is not a number", e);
            }
            String sql = "UPDATE NEWS_COVIDS SET TITLE =:title, MESSAGE=:message, "
                    + "LINK=:link,TIME=:time, "
                    + "NOTIFICATION_IMAGE=:notificationImage, "
                    + "TOPIC=:topic, NOTIFICATION_TYPE=:notificationType, "
                    + "STATUS=:status, DESCRIPTION=:des "
                    + "WHERE NEWS_COVID_ID=:id";
            Query query = session.createSQLQuery(sql);
            query.setParameter("title", entity.getTitle());
            query.setParameter("message", entity.getMessage());
            query.setParameter("link", entity.getLink());
            query.setParameter("time", entity.getTime());
            query.setParameter("notificationImage", entity.getNotificationImage());
            query.setParameter("topic", entity.getTopic());
            query.setParameter("notificationType", entity.getNotificationType());
            query.setParameter("status", status);
            query.setParameter("des", entity.getDescription());
            query.setParameter("id", entity.getNewsCovidId());

            int result = query.executeUpdate();
            session.flush();
            return result;
        } catch (HibernateException e) {
            logger.error("Cannot read news about covid-19", e);
            return 0;
        } catch (Exception e) {
            logger.error("Cannot read news about covid-19", e);
            return 0;
        }
    }

    public int deleteNewsCovid19(NewsCovid19Entity entity) {
        Session session = getSession();
        try {
            String sql = "UPDATE NEWS_COVIDS SET STATUS=:status, DESCRIPTION=:des "
                    + "WHERE NEWS_COVID_ID=:id";
            Query query = session.createSQLQuery(sql);
            query.setParameter("status", entity.getStatus());
            query.setParameter("des", entity.getDescription());
            query.setParameter("id", entity.getNewsCovidId());

            int result = query.executeUpdate();
            session.flush();
            return result;
        } catch (HibernateException e) {
            logger.error("Cannot read news about covid-19", e);
            return 0;
        } catch (Exception e) {
            logger.error("Cannot read news about covid-19", e);
            return 0;
        }
    }

    public NewsCovidDto getDetailNewsCovid19(NewsCovid19Entity entity) {
        String sql = "SELECT * FROM ("
                + "SELECT news_covid_id newsCovidId, title, message, TO_CHAR(create_date, 'dd/mm/yyyy hh24:mi:ss') createDate, notification_image notificationImage, topic, notification_type notificationType, link, status, description, time, RANK() OVER( ORDER BY create_date DESC) myrank FROM NEWS_COVIDS "
                + "WHERE status in (1,0)) t WHERE newsCovidId=:id";
        try {
            Query query;
            query = getSession().createSQLQuery(sql)
                    .addScalar("newsCovidId", StringType.INSTANCE)
                    .addScalar("title", StringType.INSTANCE)
                    .addScalar("message", StringType.INSTANCE)
                    .addScalar("createDate", StringType.INSTANCE)
                    .addScalar("time", IntegerType.INSTANCE)
                    .addScalar("link", StringType.INSTANCE)
                    .addScalar("notificationImage", StringType.INSTANCE)
                    .addScalar("topic", StringType.INSTANCE)
                    .addScalar("notificationType", StringType.INSTANCE)
                    .addScalar("status", StringType.INSTANCE)
                    .addScalar("description", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(NewsCovidDto.class));
            query.setString("id", entity.getNewsCovidId());

            List<NewsCovidDto> result = query.list();
            if (!DataUtil.isNullObject(result)) {
                return result.get(0);
            }
            return null;
        } catch (Exception e) {
            logger.error("Cannot read news about covid-19", e);
            return null;
        }
    }

    public List<CodeFiredbaseConsole> getListCodeFiredBaseConsole() {
        List<CodeFiredbaseConsole> result = new ArrayList();
        String hql = "SELECT c from CodeFiredbaseConsole c order by ID asc";
        try {
            Query query = getSession().createQuery(hql);
            result = query.list();
            if (result.isEmpty()) {
                return new ArrayList();
            }
            return result;
        } catch (Exception e) {
            logger.info(String.format("### Error occured when read list of Code Firedbase Console: %s", e.getMessage()));
            return new ArrayList();
        }

    }

    private Long getSequence(String sequenceName) {
        try {
            String sql = "select " + sequenceName + ".NEXTVAL from dual";
            Query query = getSession().createSQLQuery(sql);
            List lst = query.list();
            if (lst.isEmpty()) {
                return null;
            }
            return Long.parseLong(lst.get(0).toString());
        } catch (NumberFormatException e) {
            logger.info(String.format("### Cannot find SEQ for %s", sequenceName), e);
            return null;
        }
    }

    private ObjectDTO initObjectParamCore() {
        ObjectDTO object = new ObjectDTO();
        object.setWsCmEndpoint("http://10.79.120.21:8582/CM_WS/CmWS?wsdl");
        object.setWsCmToken("c1u1o1n1g143045ef95bb959ab2448f9072c086c90d01a4");
        object.setLocale("en_US");
        return object;
    }

    private String prepareXmlForProvinceRequest(String paramRequests) {
        String request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://ws.cm.bccs.viettel.com/\">"
                + "<soapenv:Header/>"
                + "<soapenv:Body>"
                + "<ws:getListProvince>"
                + paramRequests
                + "</ws:getListProvince>"
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";
        return request;
    }

    private String prepareXmlForDistrictRequest(String paramRequests) {
        String request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://ws.cm.bccs.viettel.com/\">"
                + "<soapenv:Header/>"
                + "<soapenv:Body>"
                + "<ws:getListDistrict>"
                + paramRequests
                + "</ws:getListDistrict>"
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";
        return request;
    }

    private String prepareXmlForPrecinctRequest(String paramRequests) {
        String request = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://ws.cm.bccs.viettel.com/\">"
                + "<soapenv:Header/>"
                + "<soapenv:Body>"
                + "<ws:getListPrecinct>"
                + paramRequests
                + "</ws:getListPrecinct>"
                + "</soapenv:Body>"
                + "</soapenv:Envelope>";
        return request;
    }

    public List<ObjectDTO> getListProvince() {
        ObjectDTO object = initObjectParamCore();
        ProvinceDTO province = new ProvinceDTO();
        province.setObject(object);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(12000).build();
        HttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
        HttpPost post = new HttpPost(province.getObject().getWsCmEndpoint());
        try {
            JSONObject json = new JSONObject();
            json.put("token", province.getObject().getWsCmToken());
            json.put("locale", province.getObject().getLocale());
            String xml = prepareXmlForProvinceRequest(XML.toString(json));
            logger.info("Xml province send request: " + xml);
            StringEntity input = new StringEntity(xml, "UTF-8");
            input.setContentType("text/xml");
            post.setEntity(input);
            HttpResponse response = client.execute(post);
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode != 200) {
                logger.info("Cannot call to Webservice " + province.getObject().getWsCmEndpoint());
                return null;
            }
            String contentReturn = EntityUtils.toString(response.getEntity());
            return getInformationForProvinceResponseWS(contentReturn);
        } catch (Exception ex) {
            logger.error("Cannot call to Webservice " + province.getObject().getWsCmEndpoint(), ex);
            return null;
        }
    }

    public List<ObjectDTO> getListDistrict(String provinceCode) {
        ObjectDTO object = initObjectParamCore();
        DistrictDTO district = new DistrictDTO();
        object.setProvinceCode(provinceCode);
        district.setObject(object);

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(12000).build();
        HttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
        HttpPost post = new HttpPost(district.getObject().getWsCmEndpoint());
        try {
            JSONObject json = new JSONObject();
            json.put("token", district.getObject().getWsCmToken());
            json.put("locale", district.getObject().getLocale());
            json.put("provinceCode", district.getObject().getProvinceCode());
            String xml = prepareXmlForDistrictRequest(XML.toString(json));
            logger.info("Xml district send request: " + xml);
            StringEntity input = new StringEntity(xml, "UTF-8");
            input.setContentType("text/xml");
            post.setEntity(input);
            HttpResponse response = client.execute(post);
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode != 200) {
                logger.info("Cannot call to Webservice " + district.getObject().getWsCmEndpoint());
                return null;
            }
            String contentReturn = EntityUtils.toString(response.getEntity());
            return getInformationForDistrictResponseWS(contentReturn);
        } catch (Exception ex) {
            logger.error("Cannot call to Webservice " + district.getObject().getWsCmEndpoint(), ex);
            return null;
        }
    }

    public List<ObjectDTO> getListPrecinct(String provinceCode, String districtCode) {
        ObjectDTO object = initObjectParamCore();
        PrecinctDTO precinct = new PrecinctDTO();
        object.setProvinceCode(provinceCode);
        object.setDistrictCode(districtCode);
        precinct.setObject(object);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(12000).build();
        HttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
        HttpPost post = new HttpPost(precinct.getObject().getWsCmEndpoint());
        try {
            JSONObject json = new JSONObject();
            json.put("token", precinct.getObject().getWsCmToken());
            json.put("locale", precinct.getObject().getLocale());
            json.put("provinceCode", precinct.getObject().getProvinceCode());
            json.put("districtCode", precinct.getObject().getDistrictCode());
            String xml = prepareXmlForPrecinctRequest(XML.toString(json));
            logger.info("Xml district send request: " + xml);
            StringEntity input = new StringEntity(xml, "UTF-8");
            input.setContentType("text/xml");
            post.setEntity(input);
            HttpResponse response = client.execute(post);
            int responseCode = response.getStatusLine().getStatusCode();
            if (responseCode != 200) {
                logger.info("Cannot call to Webservice " + precinct.getObject().getWsCmEndpoint());
                return null;
            }
            String contentReturn = EntityUtils.toString(response.getEntity());
            return getInformationForPrecinctResponseWS(contentReturn);
        } catch (Exception ex) {
            logger.error("Cannot call to Webservice " + precinct.getObject().getWsCmEndpoint(), ex);
            return null;
        }
    }

    private List<ObjectDTO> getInformationForProvinceResponseWS(String contentReturn) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        SoapWebServiceResponse soapWebServiceResponse = new SoapWebServiceResponse(contentReturn);
        List<ObjectDTO> provinceDTOS = soapWebServiceResponse.getContentObject("province", "provinceCode", "provinceName");
        return provinceDTOS;
    }

    private List<ObjectDTO> getInformationForDistrictResponseWS(String contentReturn) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        SoapWebServiceResponse soapWebServiceResponse = new SoapWebServiceResponse(contentReturn);
        List<ObjectDTO> districtDTOS = soapWebServiceResponse.getContentObject("district", "districtCode", "districtName");
        return districtDTOS;
    }

    private List<ObjectDTO> getInformationForPrecinctResponseWS(String contentReturn) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        SoapWebServiceResponse soapWebServiceResponse = new SoapWebServiceResponse(contentReturn);
        List<ObjectDTO> precinctDTOS = soapWebServiceResponse.getContentObject("precinct", "precinctCode", "precinctName");
        return precinctDTOS;
    }

    // get province by province code
    public String getProvinceByCode(String code) {
        List<ObjectDTO> list = this.getListProvince();
        for (ObjectDTO o : list) {
            if (o.getCode().equalsIgnoreCase(code)) {
                return o.getName();
            }
        }
        return "";
    }

    public String getDistrictByCode(String provinceCode, String districtCode) {
        List<ObjectDTO> list = this.getListDistrict(provinceCode);
        for (ObjectDTO o : list) {
            if (o.getCode().equalsIgnoreCase(districtCode)) {
                return o.getName();
            }
        }
        return "";
    }

    public String getCommuneByCode(String provinceCode, String districtCode, String communeCode) {
        List<ObjectDTO> list = this.getListPrecinct(provinceCode, districtCode);
        for (ObjectDTO o : list) {
            if (o.getCode().equalsIgnoreCase(communeCode)) {
                return o.getName();
            }
        }
        return "";
    }

    @Override
    public Long insert(Object entity) {
        return super.insert(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String save(Object entity) {
        return super.save(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void persist(Object entity) {
        super.persist(entity); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Object entity) {
        super.update(entity); //To change body of generated methods, choose Tools | Templates.
    }

    public NotificationResult addPaymentHistory(PaymentHistory entity) {
        NotificationResult result = new NotificationResult();
        Session session = getSession();
        try {
            Long id = getSequence("PAYMENT_HISTORY_SEQ");

            String sql = "INSERT INTO PAYMENT_HISTORY (ID, AMOUNT, CURRENT_BALANCE, PAYMENT_DATE, TID, BANK_CODE, PAYMENT_ACCOUNT, FTTH_TYPE, ID_BILL, FTTH_ACCOUNT, STATUS, CREATE_DATE, FTTH_NAME, CONTRACT_ID) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            Query query = session.createSQLQuery(sql);
            query.setParameter(0, id);
            query.setParameter(1, entity.getAmount());
            query.setParameter(2, entity.getCurrentBalance());
            query.setParameter(3, entity.getPaymentDate());
            query.setParameter(4, entity.getTid());
            query.setParameter(5, entity.getBankCode());
            query.setParameter(6, entity.getPaymentAccount());
            query.setParameter(7, entity.getFtthType());
            query.setParameter(8, entity.getIdBill());
            query.setParameter(9, entity.getFtthAccount());
            query.setParameter(10, entity.getStatus());
            query.setParameter(11, entity.getCreateDate());
            query.setParameter(12, entity.getFtthName());
            query.setParameter(13, entity.getContractIdInfo());

            int valueSuccess = query.executeUpdate();
            session.flush();
            result.setResult(valueSuccess);
            result.setNotificationId(id);
            return result;
        } catch (HibernateException e) {
            logger.error("Cannot read news about covid-19", e);
            result.setResult(0);
            return result;
        } catch (Exception e) {
            logger.error("Cannot read news about covid-19", e);
            result.setResult(0);
            return result;
        }
    }

    public Long getTotalRecordPaymentHistory(String isdn, String fromDate, String toDate) {
        String sql = "SELECT count(*) as count FROM PAYMENT_HISTORY ph WHERE ph.PAYMENT_ACCOUNT = :isdn AND ph.STATUS=0 "
                + "AND TO_TIMESTAMP(PAYMENT_DATE) >= TO_TIMESTAMP(:fromDate, 'DD-MM-YYYY HH24:MI:SS')"
                + "AND TO_TIMESTAMP(PAYMENT_DATE) <= TO_TIMESTAMP(:toDate, 'DD-MM-YYYY HH24:MI:SS')";
        ;

        try {
            Query query;
            query = getSession().createSQLQuery(sql)
                    .addScalar("count", StringType.INSTANCE);
            query.setParameter("isdn", isdn);
            query.setString("fromDate", fromDate);
            query.setString("toDate", toDate);
            List<String> result = query.list();
            if (!DataUtil.isNullOrEmpty(result)) {
                return Long.valueOf(result.get(0));
            }
            return 0L;
        } catch (Exception e) {
            logger.error("Cannot count total payment history", e);
            return 0L;
        }
    }

    public List<PaymentHistoryDTO> getPaymentHistoryListById(String isdn, Integer pageSize, Integer pageNum, String fromDate, String toDate) {
        String sql = "SELECT PAYMENT_ACCOUNT paymentAccount, FTTH_ACCOUNT ftthAccount, amount, FTTH_NAME ftthName,CONTRACT_ID contractIdInfo, TO_CHAR(PAYMENT_DATE, 'dd/mm/yyyy hh24:mi:ss') paymentDate "
                + "FROM PAYMENT_HISTORY ph WHERE ph.PAYMENT_ACCOUNT = :isdn AND ph.STATUS=0 "
                + "AND TO_TIMESTAMP(PAYMENT_DATE) >= TO_TIMESTAMP(:fromDate, 'DD-MM-YYYY HH24:MI:SS')"
                + "AND TO_TIMESTAMP(PAYMENT_DATE) <= TO_TIMESTAMP(:toDate, 'DD-MM-YYYY HH24:MI:SS')"
                + "ORDER BY PAYMENT_DATE DESC";
        try {
            Query query;
            query = getSession().createSQLQuery(sql)
                    .addScalar("paymentAccount", StringType.INSTANCE)
                    .addScalar("ftthAccount", StringType.INSTANCE)
                    .addScalar("amount", DoubleType.INSTANCE)
                    .addScalar("ftthName", StringType.INSTANCE)
                    .addScalar("contractIdInfo", IntegerType.INSTANCE)
                    .addScalar("paymentDate", StringType.INSTANCE)
                    .setResultTransformer(Transformers.aliasToBean(PaymentHistoryDTO.class));
            query.setString("isdn", isdn);
            query.setString("fromDate", fromDate);
            query.setString("toDate", toDate);
            if (pageSize != 0 && pageNum != null) {
                query.setFirstResult(pageSize * (pageNum - 1));
                query.setMaxResults(pageSize);
            }
            List<PaymentHistoryDTO> result = query.list();
            if (!DataUtil.isNullObject(result)) {
                return result;
            }
            return null;
        } catch (Exception e) {
            logger.error("Cannot get payment history", e);
            return null;
        }
    }

    public Object findPaymentHistoryByTid(Long tid) {
        StringBuilder sb = new StringBuilder("SELECT * FROM PAYMENT_HISTORY ph "
                + " WHERE ph.TID = :tid ");
        try {
            Query query;
            query = getSession().createSQLQuery(sb.toString());
            query.setLong("tid", tid);

            List<Object> result = query.list();
            if (!DataUtil.isNullObject(result) && !DataUtil.isNullOrEmpty(result)) {
                return result;
            }
            return null;
        } catch (Exception e) {
            logger.error("error", e);
            return null;
        }
    }

    public int updatePaymentHistory(PaymentHistory entity) {
        Session session = getSession();
        try {
            Long status = 0L;
            try {
                status = entity.getStatus();
            } catch (NumberFormatException e) {
                logger.info("Status is not a number", e);
            }
            String sql = "UPDATE PAYMENT_HISTORY SET STATUS=:status "
                    + "WHERE PAYMENT_HISTORY.TID=:tid";
            Query query = session.createSQLQuery(sql);
            query.setLong("status", status);
            query.setLong("tid", entity.getTid());

            int result = query.executeUpdate();
            session.flush();
            return result;
        } catch (HibernateException e) {
            logger.error("Cannot update payment history", e);
            return 0;
        } catch (Exception e) {
            logger.error("Cannot update payment history", e);
            return 0;
        }
    }

    public Users getUserByPhone(String phone, String password) {
        Session session = getSession();
        try {
            logger.info("get user by phone");
            String sql = "select id,login,FIRST_NAME firstName,LAST_NAME lastName,EMAIL email from USERS where LOGIN =:phone and PASSWORD_HASH =:pass and ACTIVATED = 1";
            SQLQuery query = session.createSQLQuery(sql);
            query.setString("phone", phone);
            query.setString("pass", password);
            query.addScalar("id", new LongType())
                    .addScalar("login", new StringType())
                    .addScalar("firstName", new StringType())
                    .addScalar("lastName", new StringType())
                    .addScalar("email", new StringType())
                    .setResultTransformer(Transformers.aliasToBean(Users.class));
            List<Users> users = query.list();
            if (users == null || users.isEmpty()) {
                logger.error("users is empty");
                return null;
            }
            return users.get(0);
        } catch (Exception e) {
            logger.error("Cannot get user with exception: ", e);
        }
        return null;
    }

    public List<UserAuthority> getUserAuthor(Long userId) {
        Session session = getSession();
        try {
            logger.info("get user author by user id");
            String sql = "select USER_ID userId, AUTHORITY_NAME authorityName from USERS_AUTHORITY where USER_ID =:userId";
            SQLQuery query = session.createSQLQuery(sql);
            query.setLong("userId", userId);
            query.addScalar("userId", new LongType())
                    .addScalar("authorityName", new StringType())
                    .setResultTransformer(Transformers.aliasToBean(UserAuthority.class));
            return query.list();
        } catch (Exception e) {
            logger.error("Cannot get user with exception: ", e);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Object> getHistoryTopUpNewByDate(String isdn, String command, String fromDate, String toDate, int total, Integer pageSize, Integer pageNum) throws ParseException {

        List<Object> list = new ArrayList<>();

        String sb = "SELECT TRANSACTION_LOG.MSISDN isdn, CHARGE_HIS.REFILL_AMOUNT amount, TO_CHAR(CHARGE_HIS.REFILL_DATE, 'DD-MM-YYYY HH24:MI:SS') refill_date "
                + "FROM TRANSACTION_LOG JOIN CHARGE_HIS ON CHARGE_HIS.TRANSACTION_ID = TRANSACTION_LOG.ID "
                + "WHERE CHARGE_HIS.REFILL_ISDN = :isdn and TRANSACTION_LOG.COMMAND = :command and TRANSACTION_LOG.ERROR_CODE = 0 AND CHARGE_HIS.RESULT_CODE = 0 "
                + "AND TO_TIMESTAMP(CHARGE_HIS.REFILL_DATE) >= TO_TIMESTAMP(:fromDate, 'DD-MM-YYYY HH24:MI:SS') "
                + "AND TO_TIMESTAMP(CHARGE_HIS.REFILL_DATE) <= TO_TIMESTAMP(:toDate, 'DD-MM-YYYY HH24:MI:SS') "
                + "ORDER BY CHARGE_HIS.REFILL_DATE desc ";
        Query query = getSession().createSQLQuery(sb)
                .addScalar("isdn", StringType.INSTANCE)
                .addScalar("amount", DoubleType.INSTANCE)
                .addScalar("refill_date", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(HistoryTopUpNewDto.class));
        query.setString("command", command);
        query.setString("isdn", isdn.trim());
        query.setString("fromDate", fromDate);
        query.setString("toDate", toDate);
        if (pageSize != 0 && pageNum != null) {
            query.setFirstResult(pageSize * (pageNum - 1));
            query.setMaxResults(pageSize);
        }
        List<Object> queryResult = query.list();

        return queryResult;
    }

    public int getTotalHistoryTopUp(String isdn, String command, String fromDate, String toDate) {
        String sql = "SELECT count(*) as count "
                + "FROM TRANSACTION_LOG JOIN CHARGE_HIS ON CHARGE_HIS.TRANSACTION_ID = TRANSACTION_LOG.ID "
                + "WHERE CHARGE_HIS.REFILL_ISDN = :isdn and TRANSACTION_LOG.COMMAND = :command and TRANSACTION_LOG.ERROR_CODE = 0 AND CHARGE_HIS.RESULT_CODE = 0 "
                + "AND TO_TIMESTAMP(CHARGE_HIS.REFILL_DATE) >= TO_TIMESTAMP(:fromDate, 'DD-MM-YYYY HH24:MI:SS') "
                + "AND TO_TIMESTAMP(CHARGE_HIS.REFILL_DATE) <= TO_TIMESTAMP(:toDate, 'DD-MM-YYYY HH24:MI:SS') ";

        try {
            Query query;
            query = getSession().createSQLQuery(sql)
                    .addScalar("count", StringType.INSTANCE);
            query.setString("command", command);
            query.setParameter("isdn", isdn);
            query.setString("fromDate", fromDate);
            query.setString("toDate", toDate);
            List<String> result = query.list();
            if (!DataUtil.isNullOrEmpty(result)) {
                return Integer.valueOf(result.get(0));
            }
            return 0;
        } catch (Exception e) {
            logger.error("Cannot count total history TopUp", e);
            return 0;
        }
    }
    public List<Object> getHistoryTopUpLucky(String isdn, String command, String fromDate, String toDate, int total, Integer pageSize, Integer pageNum, String status, String userId) throws ParseException {

        List<Object> list = new ArrayList<>();

        List<String> listAccount = new ArrayList<>();
        listAccount.add(isdn);
        listAccount.add(userId);

        String sb = "SELECT TRANSACTION_LOG.MSISDN isdn, CHARGE_HIS.REFILL_AMOUNT amount, TO_CHAR(CHARGE_HIS.REFILL_DATE, 'DD-MM-YYYY HH24:MI:SS') refill_date, " +
                " CHARGE_HIS.PIN_NUMBER pinCode"
                + " FROM TRANSACTION_LOG JOIN CHARGE_HIS ON CHARGE_HIS.TRANSACTION_ID = TRANSACTION_LOG.ID "
                + "WHERE nvl(TO_CHAR(user_id),charge_his.refill_isdn) in (:listAccount) and TRANSACTION_LOG.COMMAND = :command and TRANSACTION_LOG.ERROR_CODE = 0 AND CHARGE_HIS.RESULT_CODE = 0 "
                + "AND TO_TIMESTAMP(CHARGE_HIS.REFILL_DATE) >= TO_TIMESTAMP(:fromDate, 'DD-MM-YYYY HH24:MI:SS') "
                + "AND TO_TIMESTAMP(CHARGE_HIS.REFILL_DATE) <= TO_TIMESTAMP(:toDate, 'DD-MM-YYYY HH24:MI:SS') "
                + "ORDER BY CHARGE_HIS.REFILL_DATE desc ";
        Query query = getSession().createSQLQuery(sb)
                .addScalar("isdn", StringType.INSTANCE)
                .addScalar("amount", DoubleType.INSTANCE)
                .addScalar("refill_date", StringType.INSTANCE)
                .addScalar("pinCode", StringType.INSTANCE)
                .setResultTransformer(Transformers.aliasToBean(HistoryTopUpNewDto.class));
        query.setString("command", command);
        query.setParameterList("listAccount", listAccount);
        query.setString("fromDate", fromDate);
        query.setString("toDate", toDate);
        if (pageSize != 0 && pageNum != null) {
            query.setFirstResult(pageSize * (pageNum - 1));
            query.setMaxResults(pageSize);
        }
        List<Object> queryResult = query.list();

        return queryResult;
    }

    public int getTotalHistoryTopUpLucky(String isdn, String command, String fromDate, String toDate, String userId) {
        List<String> listAccount = new ArrayList<>();
        listAccount.add(isdn);
        listAccount.add(userId);
        String sql = "SELECT count(*) as count "
                + " FROM TRANSACTION_LOG JOIN CHARGE_HIS ON CHARGE_HIS.TRANSACTION_ID = TRANSACTION_LOG.ID "
                + "WHERE nvl(TO_CHAR(user_id),charge_his.refill_isdn) in (:listAccount) and TRANSACTION_LOG.COMMAND = :command and TRANSACTION_LOG.ERROR_CODE = 0 AND CHARGE_HIS.RESULT_CODE = 0 "
                + "AND TO_TIMESTAMP(CHARGE_HIS.REFILL_DATE) >= TO_TIMESTAMP(:fromDate, 'DD-MM-YYYY HH24:MI:SS') "
                + "AND TO_TIMESTAMP(CHARGE_HIS.REFILL_DATE) <= TO_TIMESTAMP(:toDate, 'DD-MM-YYYY HH24:MI:SS') ";

        try {
            Query query;
            query = getSession().createSQLQuery(sql)
                    .addScalar("count", StringType.INSTANCE);
            query.setString("command", command);
            query.setParameterList("listAccount", listAccount);
            query.setString("fromDate", fromDate);
            query.setString("toDate", toDate);
            List<String> result = query.list();
            if (!DataUtil.isNullOrEmpty(result)) {
                return Integer.valueOf(result.get(0));
            }
            return 0;
        } catch (Exception e) {
            logger.error("Cannot count total history TopUp lucky", e);
            return 0;
        }
    }
}
