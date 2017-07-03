
package sqlhibernatetest;
// Имя класса должно совпадать с именем табице в базе, это значительно упрощает работу

public class Employee {

    // поля должны быть в нижнем регистре и буква в букву совпадать с названиями полей в БД
       private long id = 1L;
       private String name;

       //Обязательно нужен конструктор без параметров и обязательно явный
       public Employee() {
       }
       //Конструктор с параметрами создается для удобства программиста
       public Employee(String fname) {
         name = fname;
       }
       //обязательно создать все геттеры и сеттеры для каждого поля задействуемого в БД
       public long getId() {
         return id;
       }

       public void setId(Long id) {
         this.id = id;
       }

       public String getName() {
         return name;
       }

       public void setName(String name) {
         this.name = name;
       }
    }
