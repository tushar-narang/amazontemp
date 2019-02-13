package ooad.amazon.com.resources;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CommonSessionFactory {
	public static SessionFactory sf = new Configuration().configure().buildSessionFactory();	
}
