package ooad.amazon.com.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;

import ooad.amazon.com.bean.Advertisment;
import ooad.amazon.com.bean.Category;
import ooad.amazon.com.bean.Product;
import ooad.amazon.com.bean.ProductImages;
import ooad.amazon.com.bean.Seller;
import ooad.amazon.com.bean.User;
import ooad.amazon.com.resources.CommonSessionFactory;

public class ProductDAO {

	
	public static List<Product> getAllProducts() {
		Session ses = CommonSessionFactory.sf.openSession();
		
		List<Product> prodlist = ses.createNativeQuery("select * from product",Product.class).list();
		for(Product p: prodlist)
		{Hibernate.initialize(p.getProduct_images());
		Hibernate.initialize(p.getCategorylist());
		}
		ses.close();
		
		return prodlist;
	}
	
	public static List<Product> getProductsbyCategory(String categoryname) {
		Session ses = CommonSessionFactory.sf.openSession();
		
		List<Product> prodlist = ses.createNativeQuery("select * from Product where id in (select Product_id from product_categorylist where categoryname = "+"'"+categoryname+"'"+" )", Product.class).list();
		for(Product p: prodlist)
		{Hibernate.initialize(p.getProduct_images());
		Hibernate.initialize(p.getCategorylist());
		System.out.println(p.toString());
		}
		ses.close();
		
		return prodlist;
	}
	
	
	public static List<Product> getProductsbyId(int id) {
		Session ses = CommonSessionFactory.sf.openSession();
		
		List<Product> prodlist = ses.createNativeQuery("select * from Product where id ="+ id + ";", Product.class).list();
		for(Product p: prodlist)
		{Hibernate.initialize(p.getProduct_images());
		Hibernate.initialize(p.getCategorylist());
		System.out.println(p.toString());
		}
		ses.close();
		
		return prodlist;
	}
	
	
	public static int addproduct(int sellerid, Product prod1, List<Category> categories, List<ProductImages> images) {
		
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		
//		for(Category cat: categories) {
//			ses.save(cat);
//		}
		System.out.println(sellerid + " ");
		User seller = (User)ses.get(User.class, sellerid);
		System.out.println(seller.toString());
		List<Product> prods = seller.getProductlist();
		prod1.setSeller(sellerid);
		
		ses.save(prod1);
		prods.add(prod1);
		ses.save(seller);
		seller.setProductlist(prods);
		for(ProductImages img: images) {
			ses.save(img);
		}
		
		Integer id = (Integer) ses.save(prod1);
		
		ses.getTransaction().commit();
		ses.close();
		return id;
	}
	
	
}
