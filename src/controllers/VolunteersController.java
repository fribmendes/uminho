/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import data.DataException;
import data.RepositoryFactory;
import data.VolunteersRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import models.Activity;
import models.Contact;
import models.Volunteer;

/**
 *
 * @author mendes
 */
public class VolunteersController extends AbstractController<Volunteer> {
    VolunteersRepository repo;
    
    VolunteersController() {
        this.repo = RepositoryFactory.getVolunteersRepository();
    }
    
    @Override
    public Volunteer newInstance(final Map<String, Object> params) throws DataException {
        return new Volunteer(
                (String)params.get("name"),
                (String)params.get("address"),
                (String)params.get("nif"),
                (String)params.get("nib"),
                (Activity)ControllerFactory.getActivityController().findBy( new HashMap<String, Object>() {{
                    put("name", params.get("activity"));}}),
                (Set<Contact>)params.get("contacts"),
                (String)params.get("birthDate"),
                (String)params.get("education"),
                (String)params.get("nationality"),
                (String)params.get("citizenship"),
                (String)params.get("maritalStatus"),
                (String)params.get("observations"),
                (String)params.get("file"),
                (Integer)params.get("donorId")
        );
    }
    
    protected VolunteersRepository getRepository() {
        return RepositoryFactory.getVolunteersRepository();
    }
}
