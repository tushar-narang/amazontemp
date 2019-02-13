package ooad.amazon.com.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
public class Product {
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String productname;
	private int price;
	private int discountedprice;
	private int quantityleft;
	private String description;
	private float starratings;
	private int seller;
	
	@OneToMany
	@JoinColumn(name="prodId")
	List<ProductImages> product_images = new ArrayList<ProductImages>();
	


	public Product() {
		super();
	}

	public Product(String productname, int price, int discountedprice, int quantityleft, String description,
			float starratings) {
		super();
		this.productname = productname;
		this.price = price;
		this.discountedprice = discountedprice;
		this.quantityleft = quantityleft;
		this.description = description;
		this.starratings = starratings;
	}

	@ElementCollection
    @GenericGenerator(name="categoryGenerator",strategy="sequence")
    @CollectionId(
        columns=@Column(name="catid"),
        type=@Type(type="long"),
        generator="categoryGenerator"
    )
    private List<Category> categorylist = new ArrayList<Category>();

	
	public List<ProductImages> getProduct_images() {
		return product_images;
	}

	public void setProduct_images(List<ProductImages> product_images) {
		this.product_images = product_images;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscountedprice() {
		return discountedprice;
	}

	public void setDiscountedprice(int discountedprice) {
		this.discountedprice = discountedprice;
	}

	public int getQuantityleft() {
		return quantityleft;
	}

	public void setQuantityleft(int quantityleft) {
		this.quantityleft = quantityleft;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getStarratings() {
		return starratings;
	}

	public void setStarratings(float starratings) {
		this.starratings = starratings;
	}
	
	
	public List<Category> getCategorylist() {
		return categorylist;
	}

	public void setCategorylist(List<Category> categorylist) {
		this.categorylist = categorylist;
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	@Override
	public String toString() {
		return "Product [id=" + id + ", productname=" + productname + ", price=" + price + ", discountedprice="
				+ discountedprice + ", quantityleft=" + quantityleft + ", description=" + description + ", starratings="
				+ starratings + ", seller=" + seller + ", product_images=" + product_images + ", categorylist="
				+ categorylist + "]";
	}

	public int getSeller() {
		return seller;
	}

	public void setSeller(int seller) {
		this.seller = seller;
	}
	

	
	
	
	
	
}
