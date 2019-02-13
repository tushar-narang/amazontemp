package ooad.amazon.com.bean;
import javax.persistence.Embeddable;



@Embeddable
public class Card{
	
	private String cardno;
	
	public Card(String cardno, boolean is_debit, int cvv, int expmonth, int expyear, String cardholdername) {
		super();
		this.cardno = cardno;
		this.is_debit = is_debit;
		this.cvv = cvv;
		this.expmonth = expmonth;
		this.expyear = expyear;
		this.cardholdername = cardholdername;
	}
	
	
	public Card() {
		super();
	}
	private boolean is_debit ;
	private int cvv;
	private int expmonth;
	private int expyear;
	private String cardholdername;
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public boolean isIs_debit() {
		return is_debit;
	}
	public void setIs_debit(boolean is_debit) {
		this.is_debit = is_debit;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	public int getExpmonth() {
		return expmonth;
	}
	public void setExpmonth(int expmonth) {
		this.expmonth = expmonth;
	}
	public int getExpyear() {
		return expyear;
	}
	public void setExpyear(int expyear) {
		this.expyear = expyear;
	}
	public String getCardholdername() {
		return cardholdername;
	}
	public void setCardholdername(String cardholdername) {
		this.cardholdername = cardholdername;
	}
	

}
