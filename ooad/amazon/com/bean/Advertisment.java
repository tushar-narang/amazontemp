package ooad.amazon.com.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Advertisment {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String url;
	
	private String categorytoshow;
	
	public String getCategorytoshow() {
		return categorytoshow;
	}
	public void setCategorytoshow(String categorytoshow) {
		this.categorytoshow = categorytoshow;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
