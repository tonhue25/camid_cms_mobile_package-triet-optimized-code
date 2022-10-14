package co.siten.myvtg.dao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author thomc
 *
 */

@Qualifier("producttx")
@Repository("ProductDao")
public class ProductDao extends AbstractProductDao< Object> {

}
