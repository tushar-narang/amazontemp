package ooad.amazon.com.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import ooad.amazon.com.bean.Bank;
import ooad.amazon.com.bean.Order;
import ooad.amazon.com.bean.OrderedItem;
import ooad.amazon.com.bean.Product;
import ooad.amazon.com.bean.User;
import ooad.amazon.com.resources.CommonSessionFactory;

public class OrderDAO {

	public static String saveOrder(int custid, List<Integer> prodids, List<Integer> quantities) {
		
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		
		User buyer = (User)ses.load(User.class, custid);
		Bank buyeracc = buyer.getBank();
		
		List<Integer> sellerids = new ArrayList<>();
		for(int prodid: prodids) {
			Product product = (Product)ses.load(Product.class, prodid);
			sellerids.add(product.getSeller());
		}
		
		int totalAmt = 0;
		for(int i=0;i<sellerids.size();i++) {
			
			Product product = (Product)ses.load(Product.class, prodids.get(i));
			totalAmt += (product.getDiscountedprice() * quantities.get(i));
		}
		if(totalAmt > buyeracc.getAmount() ) {
			ses.getTransaction().commit();
			ses.close();
			return "Insufficient Balance";
		}
		else {
			List<OrderedItem> itemlist = new ArrayList<>();
			for(int i=0;i<sellerids.size();i++) {
				User seller = (User)ses.load(User.class, sellerids.get(i));
				Bank selleracc = seller.getBank();
			
				Product product = (Product)ses.load(Product.class, prodids.get(i));
			
				System.out.println(buyeracc.toString() + " " + selleracc.toString() + " "  + product.toString());
		
				
					selleracc.setAmzamount(selleracc.getAmzamount() + product.getDiscountedprice()* quantities.get(i));
					buyeracc.setAmount(buyeracc.getAmount() - product.getDiscountedprice()* quantities.get(i));
					
					OrderedItem item = new OrderedItem(product.getId(),quantities.get(i), product.getPrice(), product.getDiscountedprice(), "PENDING");
					itemlist.add(item);
					
					
					
			}
			Order order = new Order(new Date(), totalAmt );
			order.setBuyerid(custid);
			order.setOrdereditemlist(itemlist);
			ses.save(order);
			
			List<Order> orderlist = buyer.getOrderlist();
			orderlist.add(order);
			buyer.setOrderlist(orderlist);
		
			ses.getTransaction().commit();
			ses.close();
			return "Success";
		}
			
		
		
	}
	
	public static void confirmOrder(int sellerid, int orderid) {
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		
		Order order = (Order)ses.load(Order.class, orderid);
		List<OrderedItem> items = order.getOrdereditemlist();
		
		//System.out.println("transfer from" + order.getBuyerid());
		//int buyer = order.get
		int amt = 0;
		int quan = 0;
		for(OrderedItem item: items) {
			System.out.println(item.toString());
			System.out.println("amount: " + item.getUnitdiscountedamount());
			amt = item.getUnitdiscountedamount();
			quan = item.getQuantity();
			item.setStatus("Shipped");
		}
		System.out.println("to:" + sellerid);
		
		User seller = (User)ses.load(User.class, sellerid);
		Bank selleracc = seller.getBank();
		selleracc.setAmzamount(selleracc.getAmzamount() - (amt*quan) );
		selleracc.setAmount(selleracc.getAmount() + (amt*quan) );
		
		
		ses.getTransaction().commit();
		ses.close();
		
		
	}
	
	public static List<Order> getOrdersofSeller(int sellerid) {
		Session ses = CommonSessionFactory.sf.openSession();
		ses.beginTransaction();
		List<Order> returnitems = new ArrayList<>();
		
		try {
			//System.out.println("select * from customerorder where emailid = "+"'"+username+"'"+" and password = "+"'"+password+"'");
			Query query = ses.createQuery("from customerorder");
			List<Order> allorders  = (List<Order>) query.list(); 
			for(Order order: allorders) {
				Hibernate.initialize(order.getOrdereditemlist());	
			}
			
			for(Order order: allorders) {
				for(OrderedItem item: order.getOrdereditemlist()) {
					System.out.println("---" + item.toString());
					Product prod = (Product)ses.load(Product.class, item.getProductid());
					System.out.println("prod--" + prod.toString());
					if(prod.getSeller() == sellerid) {
						returnitems.add(order);
					}
				}
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		ses.getTransaction().commit();
		ses.close();
		
		return returnitems;
	}
	public static void main(String[] args) {
		confirmOrder(10, 13);
	}
}
