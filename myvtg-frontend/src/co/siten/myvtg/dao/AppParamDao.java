package co.siten.myvtg.dao;

import co.siten.myvtg.model.myvtg.AppParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("AppParamDao")
public interface AppParamDao extends JpaRepository<AppParam, Long> {
    @Query("select s from AppParam s where s.name = ?1")
    public List<AppParam> findByName(String name);
}
