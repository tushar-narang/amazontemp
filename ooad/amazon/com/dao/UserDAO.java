package ooad.amazon.com.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import ooad.amazon.com.bean.Customer;
import ooad.amazon.com.bean.Order;
import ooad.amazon.com.bean.Product;
import ooad.amazon.com.bean.User;
import ooad.amazon.com.resources.CommonSessionFactory;

public class UserDAO {

	
	public static List<Customer> CheckLogin(String username, String password) {
		
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		try {
			System.out.println("select * from User u join fetch u.addrlist where emailid = "+"'"+username+"'"+" and password = "+"'"+password+"'");
			Query query = ses.createQuery("from User where emailid = "+"'"+username+"'"+" and password = "+"'"+password+"'");
			List<User> lusers  = (List<User>) query.list(); 
			//List<User> lusers  = ses.createNativeQuery("select * from User where emailid = "+"'"+username+"'"+" and password = "+"'"+password+"'").list();
			System.out.println("lusers to string is  :"+lusers.toString());
			
			int k = lusers.size();
			System.out.println("size of list lusers is :"+lusers.size());
			if(k >= 1) {
				System.out.println("returning "+lusers.get(0).getId());
				Query query1 = ses.createQuery("from Customer where id = "+lusers.get(0).getId());
				List<Customer> lcustomers  = (List<Customer>) query1.list(); 
				
				for (Customer c : lcustomers) {
					Hibernate.initialize(c.getAddrlist());
					Hibernate.initialize(c.getCardlist());
					Hibernate.initialize(c.getCartlist());
					Hibernate.initialize(c.getWishlist());
					Hibernate.initialize(c.getOrderlist());
					Hibernate.initialize(c.getProductlist());
					for(Product p : c.getProductlist()) {
						Hibernate.initialize(p.getProduct_images());
						Hibernate.initialize(p.getCategorylist());
					}
					Hibernate.initialize(c.getOrderlist());
					
					for(Order o: c.getOrderlist()) {
						Hibernate.initialize(o.getOrdereditemlist());
					}
				}
				
				System.out.println(lcustomers.get(0).toString());
				return lcustomers;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			ses.getTransaction().commit();
			ses.close();
		}
		return null;
	}
	
	
	public static boolean isbirthday(int id) {
		
		try {
			
			Session ses = CommonSessionFactory.sf.openSession();
			//System.out.println("select * from customer where emailid = "+"'"+username+"'"+" and password = "+"'"+password+"'");
			List<Customer> lcust  = ses.createNativeQuery("select * from customer where id = "+id).list();
			ses.close();
			//System.out.println("size of list lusers is :"+lusers.size());
		//	System.out.println("lcust date is "+Calendar.lcust.get(0).getDob());
			if(lcust.size() == 1) {
				return true;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	
	
	
	
	
	public static int addToCart(int userid, int prodid, int quantity) {
		Session ses = CommonSessionFactory.sf.openSession();
		Customer cust = (Customer) ses.get("Customer", userid);
		cust.getAddrlist();
		cust.getCardlist();
		
		System.out.println(cust.toString());
		return 0;
	}
	
}
