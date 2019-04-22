package proven.users.model.persist;

import java.util.ArrayList;
import java.util.List;
import proven.users.model.User;

/**
 * DAO users
 * @author ProvenSoft
 */
public class UserArrayDao implements UserDaoInterface {

    private static UserArrayDao instance;    
    private final List<User> data;
    
    private  UserArrayDao()  {
        data = new ArrayList<>(); 
        loadTestData();
    }   

    public static UserArrayDao getInstance(){
      if (instance == null) {
           instance = new UserArrayDao();
      }
      return instance;
    }    
    
    @Override
    public boolean validate(User user) {
       boolean exists = false;
       if (user != null) {
           String username = user.getUsername();
           String password = user.getPassword();
           if ( (username != null) && (password != null)) {
                for (User object: data) {
                    if(object.getUsername().equals(username) 
                    && object.getPassword().equals(password)) {
                        exists = true;
                    }                
                }               
           } else {
               exists = false;
           }          
       } else {
           exists = false;
       }      
        return exists;
    }
    
    @Override
    public User find(User user) {
        User u  = null;
        boolean exists  = false;
        for(int i=0; i < data.size() && !exists; i++) { 
            User object = data.get(i);
            if(object.getUsername().equals(user.getUsername())){
                u = object;
                exists  = true;
            }
        }
        return u;
    }
    
    @Override
    public List<User> findAll() {
        return (List<User>) data;
    }

    @Override
    public int insert(User entity) {
        int rowsAffected;
        boolean alreadyExists = data.contains(entity);
        if (alreadyExists) {
            rowsAffected = 0;
        }
        else {
            boolean success = data.add(entity);
            if (success) rowsAffected = 1;
            else rowsAffected = 0;
        }
        return rowsAffected;    
    }

    @Override
    public int remove(User entity) {
        int rowsAffected;
        if (data.contains(entity)) {
            data.remove(entity);
            rowsAffected = 1;
        }
        else {
            rowsAffected = 0;
        }
        return rowsAffected;    
    }

    @Override
    public int update(User oldEntity, User newEntity) {
        int rowsAffected;
        int index = data.indexOf(oldEntity);
        if (index >= 0) {
            data.set(index, newEntity);
            rowsAffected = 1;
        }
        else {
            rowsAffected = 0;
        }
        return rowsAffected;   
    }
    
    private void loadTestData() {
        data.add(new User("peter", "12345","user"));
        data.add(new User("admin", "12345","admin"));
        data.add(new User("jonh", "12345","user"));
    }
    
}
