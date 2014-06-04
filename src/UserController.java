/**
 *
 * @author frmendes
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.regex.Pattern;

public class UserController implements Serializable {
    private UserDatabase database;
    private User currentUser;
    private boolean adminLogged;


    // Name regex - names can't contain more than one space and must not contain any numbers
    private static final String NAMEREGEX = "^[\\p{L}]+\\s?[\\p{L}]+$";
    // Email regex
    private static final String EMAILREGEX = "\\A[\\w\\-.]+@[a-z\\-\\d]+(\\.[a-z]+)*\\.[a-z]+\\z";

    /**
     * Empty constructor
     */

    public UserController() {
        this.database = new UserDatabase();
        this.currentUser = new User();
        this.adminLogged = false;
    }

    public UserController(User u, UserDatabase db) {
        this.currentUser = u.clone();
        this.database = db.clone();
        this.adminLogged = false;
    }

    public UserController(UserController uc) {
        this.currentUser = uc.getCurrentUser();
        this.database = uc.getDatabase();
        this.adminLogged = uc.isAdminLogin();
    }

    public User getCurrentUser() {
        if(this.adminLogged)
            return null;
        
        return this.currentUser.clone();
    }

    private UserDatabase getDatabase() {
        return this.database.clone();
    }

    public boolean isAdminLogin() {
        return this.adminLogged;
    }
    
    public void logoutAdmin() {
        this.adminLogged = false;
    }

    public boolean validateEmailUniqueness(String email) {
        return this.database.findByEmail(email) == null;
    }

    public boolean validAdminUniqueness(String email) {
        return this.database.findAdmin(email) == null;
    }

    public void registerUser(String name, String email, String password, UserInfo info) {
        this.database.save( new User(name, password, email, info) );
    }
    
    public void registerAdmin(String name, String password, String email) {
        this.database.addAdmin( new AdminUser(name, password, email) );
    }

    public boolean existsUserWithEmail(String email) {
        boolean existsUserEmail = !this.validateEmailUniqueness(email);
        boolean existsAdminEmail = !this.validAdminUniqueness(email);
        return (existsAdminEmail || existsUserEmail);
    }
    
    public boolean validUserEmail(String email) {
        return this.validateEmailUniqueness(email) && ! UserController.isAdminEmail(email);
    }
    
    public boolean validAdminEmail(String email) {
        return this.validAdminUniqueness(email) && UserController.isAdminEmail(email);
    }
    

    public ArrayList<User> nameSearch(String name) {
        return this.database.searchName(name);
    }

    public ArrayList<User> emailSearch(String email) {
        ArrayList<User> al = new ArrayList<User>();
        al.add( this.database.findByEmail(email) );
        return al;
    }

    private boolean normalUserLogin(String email, String password) {
        User u = this.database.findByEmail(email);
        boolean match = false;

        if ( u.matchPassword(password) ) {
            this.currentUser = u;
            match = true;
        }

        return match;
    }
    
    private boolean adminUserLogin(String email, String password) {
        AdminUser au = this.database.findAdmin(email);
        boolean match = false;

        if ( au.matchPassword(password) ) {
            this.currentUser = null;
            this.adminLogged = true;
            match = true;
        }

        return match;
    }

    public boolean loginUser(String email, String password) {
        if ( UserController.isAdminEmail(email) )
            return adminUserLogin(email, password);
        else
            return normalUserLogin(email, password);
    }

    public void sendFriendRequest(User u) {
        if (! this.currentUser.hasSentRequest(u) ) {
            this.currentUser.sendFriendRequest( u.getId() );
            u.receiveFriendRequest( this.currentUser.getId() );
            this.database.save(u);
            this.database.save(this.currentUser);
        }
    }

    public void sendFriendRequest(int id) {
        sendFriendRequest( this.database.findById(id) );
    }

    public void acceptFriendRequest(int id) {
        acceptFriendRequest( this.database.findById(id) );
    }

    public void acceptFriendRequest(User u) {
        if ( this.currentUser.hasReceivedRequest(u) ) {
            this.currentUser.acceptFriendRequest(u);
            u.confirmFriendRequest(this.currentUser);
            this.database.save(u);
            this.database.save(this.currentUser);
        }
    }

    public void rejectFriendRequest(int id) {
        rejectFriendRequest( this.database.findById(id) );
    }

    public void rejectFriendRequest(User u) {
        if ( this.currentUser.hasReceivedRequest(u) ) {
            this.currentUser.rejectFriendRequest(u);
            u.removeSentRequest(this.currentUser);
            this.database.save(u);
            this.database.save(this.currentUser);
        }
    }

    public void deleteFriend(int id) {
        deleteFriend( this.database.findById(id) );
    }

    public void deleteFriend(User u) {
        if ( this.currentUser.hasFriend(u) ) {
            this.currentUser.deleteFriend(u);
            u.deleteFriend(this.currentUser);
            this.database.save(u);
            this.database.save(this.currentUser);
        }
    }

    public boolean hasFriendRequests() {
        return this.currentUser.hasFriendRequest();
    }

    public ArrayList<User> getFriendRequests() {
        ArrayList<User> list = new ArrayList<User>();

        for (int i : this.currentUser.getRequests() )
            list.add( this.database.findById(i) );

        return list;
    }

    public ArrayList<User> getFriendList() {
        ArrayList<User> list = new ArrayList<User>();

        for (int i : this.currentUser.getFriends() )
            list.add( this.database.findById(i) );

        return list;
    }
    
    public ArrayList<User> getFriendList(int id) {
        User u = this.database.findById(id);
        ArrayList<User> list = new ArrayList<User>();

        for (int i : u.getFriends() )
            list.add( this.database.findById(i) );

        return list;
    }
    
    private void deleteUserFromFriends(int id) {
        ArrayList<User> friends = this.getFriendList(id);
        for (User u : friends) {
            u.deleteFriend(id);
            if (u.hasReceivedRequest(id) )
                u.removeSentRequest(id);
            
            this.database.save(u);
        }
    }

    public void addActivity(Activity act){
        currentUser.addActivity(act);
        database.save(currentUser);
    }

    public void removeActivity(Activity act){
        currentUser.removeActivity(act);
        database.save(currentUser);
    }

    public ArrayList<Activity> getMostRecentActivities(){
        return currentUser.getMostRecentActivities();
    }

    public String currentUserProfile() {
        return this.currentUser.toString();
    }

    public String showStatsOverview(){
        return this.currentUser.showStatsOverview();
    }

    public String showAnnualStats(int year) throws StatsNotAvailable{
        return this.currentUser.showAnnualStats(year);
    }

    public String showMonthlyStats(int year, int month) throws StatsNotAvailable{
        return this.currentUser.showMonthlyStats(year, month);
    }
    
    public void writeToFile(String fich) throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(fich) );
        oos.writeObject(this.database);
        oos.flush(); oos.close();
    }
    
    public void readFromFile(String fich) throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream( new FileInputStream(fich) );
        UserDatabase restored = (UserDatabase) ois.readObject();
        ois.close();
        this.database = restored;
    }

    public void deleteUser(int id) {
        deleteUserFromFriends(id);
        this.database.delete(id);
    }
    
    public void deleteUser(String email) {
        deleteUserFromFriends( this.database.findByEmail(email).getId() );
        this.database.delete(email);
    }
    
    public void deleteUser(User u) {
        deleteUserFromFriends( u.getId() );
        this.database.delete(u);
    }
    
    public void updateUser(String name, String email, String pw, UserInfo info) {
        UserInfo ui = UserInfo.generateValidInfo(this.currentUser.getInfo(), info);
        if (name.length() == 0)
            name = this.currentUser.getName();
        if (email.length() == 0)
            email = this.currentUser.getEmail();

        this.deleteUser( this.currentUser.getId() );
        this.currentUser.updateSettings(name, email, pw, ui);
        this.database.save(currentUser);
        
    }   

    @Override
    public UserController clone() {
        return new UserController(this);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if( o == null || this.getClass() != o.getClass() ) return false;

        UserController uc = (UserController) o;

       return this.database.equals( uc.getDatabase() ) && this.currentUser == uc.getCurrentUser();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Current user:\n");
        str.append(this.currentUser);
        str.append("User Database");
        str.append(this.database);
        return str.toString();
    }

      /** Tests if string matches email format
     */
    public static boolean validEmailFormat(String email) {
        return UserController.validateRegex(UserController.EMAILREGEX, email);
    }

    /** Tests if string matches name format
     */
    public static boolean validNameFormat(String name) {
        return UserController.validateRegex(UserController.NAMEREGEX, name);
    }

    private static boolean validateRegex(String regex, String str) {
        return Pattern.compile(regex).matcher(str).matches();
    }

    public static boolean isAdminEmail(String email) {
        return email.contains("@fitnessum.com");
    }

}
