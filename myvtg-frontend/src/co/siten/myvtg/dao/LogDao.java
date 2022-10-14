package co.siten.myvtg.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.bean.DataByTimeBean;
import co.siten.myvtg.model.myvtg.Log;

@Repository("LogDao")
public interface LogDao extends JpaRepository<Log, String> {
	@Query("select new co.siten.myvtg.bean.DataByTimeBean(trunc(s.insertDate), COUNT(s.id)) from Log s  where s.insertDate>=?1 GROUP BY trunc(s.insertDate) ORDER BY trunc(s.insertDate)")
	public List<DataByTimeBean> getDataByTimeBean(Date previousMonth);

}
