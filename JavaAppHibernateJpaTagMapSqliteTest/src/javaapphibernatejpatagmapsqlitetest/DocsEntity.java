/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapphibernatejpatagmapsqlitetest;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Artik
 */
@Entity 
@Table(name = "DOCS") 
public class DocsEntity implements Serializable{
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "Id", unique = true, nullable = false) 
    private long id; 
  
    @Column(name = "Docs_Info", unique = false, nullable = false, length = 100) 
    private String docs_Info; 
    //
    /*
    Mapping one-to-many (master-detail).
    Главная (master) - Employees, подчиненная (detail) - DocsEntity
    в главной создается коллекция с элементами подчиненной таблицы, и указывается имя свойства в классе для подчиненной
    таблицы
    */
    @ManyToOne
    @JoinColumn(name = "EmployeesId")//имя столбца-родителя-мастера в этой таблице
    private Employees employees;
    public Employees getEmployees() {
        return employees;
    }
    public void setEmployees(Employees emp) {
        this.employees = emp;
    } 
    //
     //Constructors,  Accessors and mutators for all four fields 
    public DocsEntity() {
    }
   
    public DocsEntity(String docs_Info, Employees employees) {
        this.docs_Info = docs_Info;
        this.employees = employees;
    }
    //
    public long getId() {
        return id;
    }
    public void setId(long Id) {
        this.id = Id;
    }
    public String getDocs_Info() {
        return docs_Info;
    }
    public void setDocs_Info(String docs_Info) {
        this.docs_Info = docs_Info;
    }
    
    //
    @Override
    public String toString() {
        return "DocsEntity{"
                + "id=" + id
                + ", docs_Info='" + docs_Info + '\''
                //+ "employeesId=" + employeesId
                + "employeesId=" + employees.getEmployeesId()
                + '}';
    }
    //
}
