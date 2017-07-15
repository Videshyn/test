/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapphibernatejpatagmapsqlitetest;

import java.io.Serializable;
import javax.persistence.Column; 
import javax.persistence.Entity; //@Entity
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id; //@Id
import javax.persistence.Table; 
import javax.persistence.UniqueConstraint; 
import java.util.List;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
/**
 *
 * @author Artik
 */
@Entity 
@Table(name = "Employees") 
/*
@Table(name = "Employees", uniqueConstraints = { 
        @UniqueConstraint(columnNames = "ID"), 
        @UniqueConstraint(columnNames = "EMAIL") }) 
*/
public class Employees implements Serializable{
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "EmployeesId", unique = true, nullable = false) 
    private long employeesId; 
 
    @Column(name = "Employees_Name", unique = false, nullable = false, length = 100) 
    private String employees_Name; 
 
    @Column(name = "IdCode", unique = false, nullable = true) 
    private Integer idCode; 
    //
/*
     Двунаправленный маппинг many-to-many

Если сущности типов Department и Employees будут иметь ссылки на коллекции друг друга, то отношения будут двунаправленными.
Для этого:
    создадим поле departments типа List<Department> в этом классе Employees
    добавим к этому полю departments аннотацию @ManyToMany
    добавим элемент аннотации mappedBy, который будет указывать на имя поля класса Department,
    которое в том класск имеет аннотации @ManyToMany и @JoinTable, т.е. departments
    */
    @ManyToMany(mappedBy = "employees")//employees - это поле в классе Department
    private List<Department> departments;

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
    
    /*
    mapping one-to-many (master-detail). главная (master) - Employees, подчиненная (detail) - DocsEntity
    в главной создается коллекция с элементами подчиненной таблицы, и указывается имя свойства в классе для подчиненной
    таблицы
    */
    @OneToMany(mappedBy = "employees")//employees - это свойство  в классе DocsEntity.
                                      //Свойство DocsEntity описано там при помощи тэгов @ManyToOne и @JoinColumn
    private List<DocsEntity> docs;
    public List<DocsEntity> getDocs() {
        return docs;
    }
    public void setDocs(List<DocsEntity> docs) {
        this.docs = docs;
    }
    //
    //Constructors,  Accessors and mutators for all four fields 
    public Employees() {
    }

    public Employees(String name, int idCode) {
        this.employees_Name = name;
        this.idCode = idCode;
    }
    public long getEmployeesId() {
        return employeesId;
    }
    public void setEmployeesId(long employeesId) {
        this.employeesId = employeesId;
    }
    public String getEmployees_Name() {
        return employees_Name;
    }
    public void setEmployees_Name(String employees_Name) {
        this.employees_Name = employees_Name;
    }
    public int getIdCode() {
        return idCode;
    }
    public void setIdCode(int idCode) {
        this.idCode = idCode;
    }   
    //
    @Override
    public String toString() {
        return "Employee{"
                + "id=" + employeesId
                + ", name='" + employees_Name + '\''
                + "idCode=" + idCode
                + '}';
    }
}
