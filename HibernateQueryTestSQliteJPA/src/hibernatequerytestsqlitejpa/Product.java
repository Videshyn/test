
package hibernatequerytestsqlitejpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {
	private long id;//из таблицы
	private String name;//из таблицы
	private String description;//из таблицы
	private float price;//из таблицы
	
	private Category category; // НЕ из таблицы, для установки связи, 
        //указывается имя этого поля в главном классе(Category) в атрибуте mappedBy

	public Product() {
	}

	public Product(String name, String description, float price,
			Category category) {
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}

        @Id
        @Column(name = "PRODUCT_ID")
        @GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
        @Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
        @Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
        @Column(name = "PRICE")
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

        @ManyToOne
        @JoinColumn(name = "CATEGORY_ID")// здесь CATEGORY_ID - это значение Column в анотации Id
                                        //  при описании главного класса 
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}

