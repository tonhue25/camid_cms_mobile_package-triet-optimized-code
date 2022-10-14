package co.siten.myvtg.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.bean.DataByStringBean;
import co.siten.myvtg.bean.DataByTimeBean;
import co.siten.myvtg.model.myvtg.Sub;

@Repository("SubDao")
public interface SubDao extends JpaRepository<Sub, String> {

	public Long countByStatus(Integer status);

	public Long countByStatusAndRegisterTime(Integer status, Date registerTime);


	public Long countByStatusAndLastActiveTime(Integer status, Date lastActiveTime);


    //SubList
	@Query("select new co.siten.myvtg.bean.DataByTimeBean(trunc(s.registerTime), COUNT(s.id)) from Sub s  where s.registerTime>=?1 GROUP BY trunc(s.registerTime) ORDER BY trunc(s.registerTime)")
	public List<DataByTimeBean> getDataByTimeBean(Date previousMonth);

    //OSList
	@Query("select new co.siten.myvtg.bean.DataByStringBean(s.osType, COUNT(s.id)) from Sub s GROUP BY s.osType ORDER BY s.osType")
	public List<DataByStringBean> getDataByStringBean();

}
