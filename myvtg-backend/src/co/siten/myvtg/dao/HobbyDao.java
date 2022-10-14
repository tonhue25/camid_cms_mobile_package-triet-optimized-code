/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.dao;

import co.siten.myvtg.model.myvtg.Hobby;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author duyth
 */
@Repository("HobbyDao")
@Qualifier("myvtgtx")
public class HobbyDao extends AbstractMyvtgDao<Object> {

    private static final Logger logger = Logger.getLogger(HobbyDao.class);

    public Hobby findById(long id) {
        try {
            return (Hobby) get(Hobby.class, id);
        } catch (Exception ex) {
            logger.error(ex);
        }

        return null;
    }
}
