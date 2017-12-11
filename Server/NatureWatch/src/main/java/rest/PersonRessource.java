/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import entity.Person;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import session.PersonFacade;

/**
 *
 * @author Samet C
 */
@Stateless
@Path("service")
public class PersonRessource {
    
    @Inject
    private PersonFacade personFacade;

    @GET
    @Path("/getPersonBy")
    @Produces(MediaType.APPLICATION_XML)
    public Person getAllPerson() {
        Person p = new Person();
        p.setId(1);
        p.setFullName("Sa");
        p.setAge(20);
        return p;
        //return personFacade.findAll();
    }
    
    @Path("/getPersonByIdXML/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Person getPersonByIdXML(@PathParam("id")int id)
    {
        return personFacade.findPerson(id);
    }

    /*
    //Should simulate a database
    private static Map<Integer, Person> persons = new HashMap<Integer, Person>();
    
    //Insert test data in simulated database
    static{
        for(int i =0; i<10;i++)
        {
            Person person = new Person();
            int id = i+1;
            person.setId(id);
            person.setFullName("My Wonderfull Person" + id);
            person.setAge(new Random().nextInt(40)+1);
            persons.put(id, person);
        }
    }
    
    //Method wich should return a single person object in XML format
    @GET
    @Path("/getPersonByIdXML/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Person getPersonByIdXML(@PathParam("id")int id)
    {
        return persons.get(id);
    }
    
    //Method wich should return a single person object in JSON format
     @GET
    @Path("/getPersonByJSON/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Person getPersonByJSON(@PathParam("id")int id)
    {
        return persons.get(id);
    }
    
    //Method wich should return a list of all person objects in XML format
    @GET
    @Path("/getAllPersonInXML")
    @Produces(MediaType.APPLICATION_XML)
    public List<Person> getAllPersonInXML(){
        return new ArrayList<Person>(persons.values());
    }*/
}
