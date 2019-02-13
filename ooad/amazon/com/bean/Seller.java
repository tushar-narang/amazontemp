package ooad.amazon.com.bean;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
public class Seller extends User {
	
	private String companyname;
	private String gstin;
	private String pan;
	private String contact_no;
	private float rating;
	private int count_ratings;
	
	
	public int getCount_ratings() {
		return count_ratings;
	}
	public void setCount_ratings(int count_ratings) {
		this.count_ratings = count_ratings;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	@OneToMany
	@JoinColumn(name="seller_id")
    private List<Product> productlist = new ArrayList<Product>();
	
	public Seller() {
		super();
	}
	
	public Seller(String companyname, String gstin, String pan, String contact_no, float rating, int count_ratings) {
		super();
		this.companyname = companyname;
		this.gstin = gstin;
		this.pan = pan;
		this.contact_no = contact_no;
		this.rating = rating;
		this.count_ratings = count_ratings;
	}
	public List<Product> getProductlist() {
		return productlist;
	}
	public void setProductlist(List<Product> productlist) {
		this.productlist = productlist;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getContact_no() {
		return contact_no;
	}
	public void setContact_no(String contact_no) {
		this.contact_no = contact_no;
	}
	
	
	
	
	
	
	
}
