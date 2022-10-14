package co.siten.myvtg.service;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import co.siten.myvtg.dao.ProductDao;

@org.springframework.stereotype.Service("ProductService")
@Transactional(rollbackFor = Exception.class, value = "producttransaction")
public class ProductService {

	@Autowired
	ProductDao productDao;

	public Session getSession() {
		return productDao.getSession();
	}

}
