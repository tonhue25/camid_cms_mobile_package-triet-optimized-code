/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dao;

import co.siten.myvtg.model.myvtg.Job;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author duyth
 */
@Repository("JobDao")
@Qualifier("myvtgtx")
public class JobDao extends AbstractMyvtgDao<Object> {

    private static final Logger logger = Logger.getLogger(JobDao.class);

    public Job findById(long id) {
        try {
            return (Job) get(Job.class, id);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(JobDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
