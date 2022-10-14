/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.service;

import co.siten.myvtg.bean.BaseResponseBean;
import co.siten.myvtg.bean.CamIdGame;
import co.siten.myvtg.bean.CamIdGameCategory;
import co.siten.myvtg.bean.RequestBean;
import co.siten.myvtg.dao.ApiGwDao;
import co.siten.myvtg.util.Constants;
import co.siten.myvtg.util.DataUtil;
import co.siten.myvtg.util.ResponseUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * PassiveNotifyServiceImpl
 *
 * @author partner7
 */
@Service("PassiveNotifyService")
@Transactional(rollbackFor = Exception.class, value = "myvtgtransaction")
@PropertySource(value = {"classpath:config.properties"})
public class PassiveNotifyServiceImpl implements PassiveNotifyService {

    @Autowired
    ApiGwDao dao;

    @Autowired
    ResponseUtil responseUtil;

    private static final Logger logger = Logger.getLogger(PassiveNotifyServiceImpl.class);
    private static final String DES_FAIL = "myMetfone.Ishare.des.fail.";

    @Override
    public BaseResponseBean getGames(RequestBean request, String language) {

        logger.info("Start business getGames of PassiveNotifyServiceImpl");
        List<CamIdGame> games = dao.getGames();
        BaseResponseBean bean = new BaseResponseBean();
        bean.setErrorCode(Constants.ERROR_SUCCESS);
        bean.setMessage("Success");
        bean.setWsResponse(games);
        bean.setUserMsg("Success");

        logger.info("End business getGames of PassiveNotifyServiceImpl");

        return bean;
    }

    @Override
    public BaseResponseBean getGameCategories(RequestBean request, String language) {
        logger.info("Start business getGameCategories of PassiveNotifyServiceImpl");
        BaseResponseBean bean = new BaseResponseBean();
        List<CamIdGameCategory> gameCates = dao.getGameCategories();
        for (CamIdGameCategory ele : gameCates) {
            List<CamIdGame> result = dao.getGamesByCateId(ele.getId());
            ele.setGames(result);
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("items", gameCates);
        data.put("total", 6);
        bean.setErrorCode(Constants.ERROR_SUCCESS);
        bean.setMessage("Success");
        bean.setWsResponse(data);
        bean.setUserMsg("Success");

        logger.info("End business getGames of PassiveNotifyServiceImpl");

        return bean;
    }
    
    @Override
    public BaseResponseBean getGameCategoriesForCMS(RequestBean request, String language) {
        logger.info("Start business getGameCategoriesForCMS of PassiveNotifyServiceImpl");
        BaseResponseBean bean = new BaseResponseBean();
        List<CamIdGameCategory> gameCates = dao.getGameCategories();
        bean.setErrorCode(Constants.ERROR_SUCCESS);
        bean.setMessage("Success");
        bean.setWsResponse(gameCates);
        bean.setUserMsg("Success");

        logger.info("End business getGameCategoriesForCMS of PassiveNotifyServiceImpl");

        return bean;
    }

    @Override
    public BaseResponseBean getGameById(RequestBean request, String language) {
        logger.info("### Start business getGameById of PassiveNotifyServiceImpl");

        if (DataUtil.isNullObject(request.getWsRequest().get("id")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("id").toString())) {
            logger.info("Error request : id is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.id.empty.", language);
        }
        String id = request.getWsRequest().get("id").toString();
        Long gameId = 0L;
        try {
            gameId = Long.parseLong(id);
        } catch (NumberFormatException ne) {
            logger.info("Error request : id is not a number ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.id.empty.", language);
        }

        BaseResponseBean bean = new BaseResponseBean();
        CamIdGame game = dao.getGameById(gameId);
        bean.setErrorCode(Constants.ERROR_SUCCESS);
        bean.setMessage("Success");
        bean.setWsResponse(game);
        bean.setUserMsg("Success");

        logger.info("End business getGameById of PassiveNotifyServiceImpl");

        return bean;
    }

    @Override
    public BaseResponseBean getGameCategoryById(RequestBean request, String language) {
        logger.info("### Start business getGameCategoryById of PassiveNotifyServiceImpl");

        if (DataUtil.isNullObject(request.getWsRequest().get("id")) || DataUtil.isNullOrEmpty(request.getWsRequest().get("id").toString())) {
            logger.info("Error request : id is null ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.id.empty.", language);
        }
        String id = request.getWsRequest().get("id").toString();
        Long gameCateId = 0L;
        try {
            gameCateId = Long.parseLong(id);
        } catch (NumberFormatException ne) {
            logger.info("Error request : id is not a number ");
            return responseUtil.responseBean(Constants.ERROR_PARAMETER_INVALID, DES_FAIL, "myMetfone.id.empty.", language);
        }

        BaseResponseBean bean = new BaseResponseBean();
        CamIdGameCategory gameCate = dao.getGameCategoryById(gameCateId);

        List<CamIdGame> result = dao.getGamesByCateId(gameCate.getId());
        gameCate.setGames(result);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("items", gameCate);
        data.put("total", 1);

        bean.setErrorCode(Constants.ERROR_SUCCESS);
        bean.setMessage("Success");
        bean.setWsResponse(data);
        bean.setUserMsg("Success");

        logger.info("End business getGameCategoryById of PassiveNotifyServiceImpl");

        return bean;
    }

}
