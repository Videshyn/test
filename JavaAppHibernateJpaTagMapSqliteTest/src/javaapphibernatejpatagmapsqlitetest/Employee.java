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

/**
 *
 * @author Artik
 */
@Entity
public class Employee implements Serializable{

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private long id = 1L;

       private String name;

       public Employee() {
       }

       public Employee(String fname) {
         name = fname;
       }

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
       @Override
	    public String toString() {
	        return "Employee{" +
	                "id=" + id +
	                ", name='" + name + '\'' +
	                '}';
	    }
    }
