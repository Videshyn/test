
package hibernatequerytestsqlitejpa;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ORDERS")
public class Order {
	private int id;
	private String customerName;
	private Date purchaseDate;
	private float amount;
	private Product product;

        @Id
        @Column(name = "ORDER_ID")
        @GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

        @Column(name = "CUSTOMER_NAME")
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

        @Column(name = "PURCHASE_DATE")
        @Temporal(TemporalType.DATE)// используем стандартный преобразователь ДЖАВА(для SQLite и Oracle не подходит)
        //Требуется свой класс парсер, будет реализован в дальнейшем, при работе с Oracle
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
        @Column(name = "amount")
	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

        @ManyToOne
        @JoinColumn(name = "PRODUCT_ID")
        // должно быть OneToOne исходя из структуры таблицы, но если использовать его, то нарушается описание
        // Product для OneToMany, поэтому повторяем описание из Product
        // внести товар через Order нельзя, но компиляция будет пройдена, Хибернейт будет мэпить Product корректно 
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
