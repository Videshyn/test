
package hibernatequerytestsqlitejpa;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "CATEGORY")
// тэг @OneToMany - помечает главную таблицу, располагается обязательно рядом с get-ром коллекции,
// связь @OneToMany - это связь между полем в подчиненном классе и коллекцией в главном классе
public class Category {

	private long id; //поле этой таблицы
	private String name;//поле этой таблицы

	private Set<Product> products; //НЕТ в таблице

	public Category() {
	}
	
	public Category(String name) {
		this.name = name;
	}

        @Id
        @Column(name = "CATEGORY_ID")
        @GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
        
        @Column(name = "NAME") // тэг не обязателен, поскольку имя поля в ДЖАВА и имя столбца в БД одинаковые 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(mappedBy = "category", // category - имя поля в классе в подчиненном классе(Product)
                cascade = CascadeType.ALL)
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}