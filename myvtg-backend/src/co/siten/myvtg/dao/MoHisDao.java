package co.siten.myvtg.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import co.siten.myvtg.model.myvtg.MoHis;

/**
 * 
 * @author thomc
 *
 */

@Repository("MoHisDao")
@Qualifier("myvtgtx")
public class MoHisDao extends AbstractMyvtgDao< MoHis> {

}
