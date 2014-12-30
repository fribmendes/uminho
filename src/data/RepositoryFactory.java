/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author frmendes
 */
public class RepositoryFactory {
    
    private static VolunteersRepository usersRepository;
    
    // TODO create valid login credentials
    private static final String USERNAME = System.getenv("HBT_USR");
    private static final String PASSWORD = System.getenv("HBT_PW");
    private static final String URL = "jdbc:mysql://localhost/habitat";
    
    public RepositoryFactory() {
        usersRepository = new VolunteersRepository(getURL(), USERNAME, PASSWORD);
    }
    
    // TODO change this to generate valid mysql db url
    public static String getURL() {
        return URL;
    }
    
    public static VolunteersRepository getUsersRepository() {
        if (usersRepository == null)
            usersRepository = new VolunteersRepository(getURL(), USERNAME, PASSWORD);
        
        return usersRepository;
    }
    
}
