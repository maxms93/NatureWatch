/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Person;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

/**
 *
 * @author Samet C
 */
@Stateless
public class PersonFacade extends AbstractFacade<Person>{
    @PersistenceContext(unitName = "NatureWatchPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    public PersonFacade() {
        super(Person.class);
    }
    
    
    @Override
    public List<Person> findAll() {
        return super.findAll();
    }
    
    public Person findPerson(int id) {
        TypedQuery<Person> query = em
                .createNamedQuery("Person.getPersonById", Person.class)
                .setParameter("ID", id);
        return query.getSingleResult();
    }
    
    /*public int getAllPerson() {
        Query query = em
                .createNamedQuery("Person.getAllPerson");
        return ((Long)query.getSingleResult()).intValue();
    }*/
    /*
    public Kfz findByBrandAndType(String brand, String type) {
        TypedQuery<Kfz> query = em
                .createNamedQuery("Kfz.findByBrandAndType", Kfz.class)
                .setParameter("BRAND", brand)
                .setParameter("TYPE", type);
        return query.getSingleResult();
    }*/
}
