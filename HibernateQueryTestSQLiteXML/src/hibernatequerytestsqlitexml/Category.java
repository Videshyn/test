
package hibernatequerytestsqlitexml;

import java.util.Set;
// Тэг one-to-many - помечает главную таблицу
// класс обертка для главной таблицы содержит коллекцию подчиненных 
// inverse="true" cascade="all" - расшифровывает этот тэг,
// inverse="true" означает что этот класс отвечает за генерацию текста запроса,
// именно этот класс является владельцем отношения
// только команда --category.setProducts(products);-- будет создавать тексты запросов
// и применять их в правельной последовательности
// cascade="all" - важная команда при приминении DELETE, при этой команде при удалении категории,
// удалятся все товары в категории

public class Category {

	private long id;
	private String name;

	private Set<Product> products; // коллекция - перечень товаров, которые относятся к категории
        // эта коллекция заполняется не явно(автоматически с помощью хибернейта) 
        // для каждого экземпляра(object) класса Category.
        //Set преставитель из разных Collection из-за того что он может представлять таблицу 1 в 1
        //для List необходимо вводить дополнительное поле - нумератор в таблицу БД, с неприрывной индексацией

	public Category() {
	}
	
	public Category(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
