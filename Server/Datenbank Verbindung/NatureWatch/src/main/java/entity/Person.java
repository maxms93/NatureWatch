/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Samet C
 */
@Entity
@XmlRootElement(name = "person")
//@XmlType(propOrder={"id", "fullName", "age"})
@NamedQueries({
    @NamedQuery(name = "Person.getPersonById", query="Select s from Person s where s.id = ?1")
})
@Table(name = "PERSON")
public class Person implements Serializable {

    @Column(name = "AGE")
    private Integer age;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "FULLNAME")
    private String fullName;
    
    public Person()
    {
        
    }
    
    public Person(String name, int age)
    {
        this.fullName = name;
        this.age = age;
    }

    @XmlElement
    public int getId() {
        return id;
    }

    /*public void setId(int id) {
        this.id = id;
    }*/
    @XmlElement
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    
    
}
