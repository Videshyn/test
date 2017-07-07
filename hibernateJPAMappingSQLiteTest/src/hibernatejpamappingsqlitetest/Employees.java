
package hibernatejpamappingsqlitetest;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity; //@Entity
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.Id; //@Id
import javax.persistence.Table; 
import javax.persistence.UniqueConstraint; 

@Entity
@Table(name = "Employees")
public class Employees implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // unique = true - заставляет принимать только уникальные поля
    // nullable - не нулевое поле
    @Column(name = "EmployeesId", unique = true, nullable = false)
    private long employeesId; 
 
    private String employees_Name; 
 
    private Integer idCode; 
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
