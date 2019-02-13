package ooad.amazon.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;

import ooad.amazon.com.bean.Advertisment;
import ooad.amazon.com.resources.CommonSessionFactory;

public class AdvertisementDAO {

	
	public static List<Advertisment> getAllAdvertisments() {
		Session ses = CommonSessionFactory.sf.openSession();
		List<Advertisment> adlist = ses.createNativeQuery("select * from advertisment",Advertisment.class).list();
		ses.close();
		return adlist;
		
	}
	
	
	
}
