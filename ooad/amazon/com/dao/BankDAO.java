package ooad.amazon.com.dao;

import org.hibernate.Session;

import ooad.amazon.com.bean.Bank;
import ooad.amazon.com.bean.User;
import ooad.amazon.com.resources.CommonSessionFactory;

public class BankDAO {

	public static void addMoney(int userid, int amount) {
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		
		User buyer = (User)ses.load(User.class, userid);
		Bank buyeracc = buyer.getBank();
		buyeracc.setAmount(buyeracc.getAmount() + amount);
		
		ses.getTransaction().commit();
		ses.close();
	}
}
