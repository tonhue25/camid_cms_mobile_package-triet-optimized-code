package co.siten.myvtg.dao;

import co.siten.myvtg.model.myvtg.Service;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("StaffDao")
@Qualifier("myvtgtx")
@PropertySource(value = {"classpath:database.properties"})
public class StaffDao extends AbstractMyvtgDao<Object>{

    private static final Logger logger = Logger.getLogger(ServiceDao.class.getName());

    public Map<String, Object> getListStaff(String search, Integer pageSize, Integer pageNum, String sortBy, String sortType, Long servicegroupId) {
        logger.info("Start getListStaff API off StaffDao");
        Map<String, Object> response = new HashMap<>();
        StringBuilder sql = new StringBuilder();
        sql.append(" select s from Staff");
        Query query = getSession().createQuery(sql.toString());
        List<Service> lstResult = query.list();
        response.put("services", lstResult);
        return response;
    }

}
