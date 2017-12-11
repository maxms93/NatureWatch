/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Person;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;


/**
 *
 * @author Samet C
 */
@Startup
@Singleton
public class InitBean {
    
    @Inject 
    PersonFacade personFacade;

    public InitBean() {
    }
    
    @PostConstruct
    private void init() {
        personFacade.create(new Person("Samet", 22));
        personFacade.create(new Person("Robin",21));
        personFacade.create(new Person("Marco", 21));
    }
    
}