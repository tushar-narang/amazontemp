package ooad.amazon.com.bean;
import javax.persistence.Embeddable;



@Embeddable
public class Category{
	
	public Category() {
		super();
	}
	public Category(String categoryname, int levelid) {
		super();
		this.categoryname = categoryname;
		this.levelid = levelid;
	}
	private String categoryname;
	private int levelid ;
	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	public int getLevelid() {
		return levelid;
	}
	public void setLevelid(int levelid) {
		this.levelid = levelid;
	}
	
	
	
}
