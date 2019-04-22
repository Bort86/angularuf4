package proven.friends.model.persist;

import java.util.ArrayList;
import java.util.List;

import proven.friends.model.Friend;

/**
 * <strong>FriendArrayDao.java</strong>
 * DAO class for Friend persistence in an array.
 * Implements singleton pattern.
 * @author ProvenSoft
 */
public class FriendArrayDao implements FriendDaoInterface {
    
    private static FriendArrayDao instance;
    private final List<Friend> data;
    
    private FriendArrayDao()  {
        data = new ArrayList<>(); 
        loadTestData();  //TODO: remove test data for production.
    }   

    public static FriendArrayDao getInstance(){
      if (instance == null) {
           instance = new FriendArrayDao();
      }
      return instance;
    }
    
    @Override
    public Friend find(Friend entity) {
        Friend entityFound;
        int index = data.indexOf(entity);
        if (index >= 0) {
            entityFound = data.get(index);
        }
        else {
            entityFound = null;
        }
        return entityFound;    
    }

    @Override
    public int insert(Friend entity) {
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
    public int update(Friend oldEntity, Friend newEntity) {
        //TODO avoid phone duplicates.
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

    @Override
    public int delete(Friend entity) {
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
    public List<Friend> findAll() {
        return (List<Friend>) data;
    }
        
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Friend f : data) {
            sb.append(f.toString());
        }
        return sb.toString();
    }
    
    public final void loadTestData() {
        data.add(new Friend("111", "Susan", "Sarandom", 33, "1986-01-01", "email1@y.com" ));
        data.add(new Friend("112", "Peter", "Parker", 33 , "1986-01-01", "email2@y.com" ));
        data.add(new Friend("113", "Son", "Goku", 34, "1985-01-01", "email3@y.com" ));
        data.add(new Friend("114", "Mr", "Popo", 33, "1986-01-01", "email4@y.com" ));
        data.add(new Friend("115", "Vegetta", "Vegetta", 33, "1986-01-01", "email5@y.com" ));
        for (int i = 0; i<100; i++){
            data.add(new Friend("111" + i, "Name" + i, "Surnames" + i, 33 + i, "1986-01-01", "email1" + i + "@y.com" ));
        }

    }

    @Override
    public List<Friend> findByName(String name) {
        List<Friend> found = new ArrayList<>();
        for (int i=0; i<data.size(); i++) {
            Friend f = data.get(i);
            if (f.getName().equals(name)) {
                found.add(f);
            }
        }
        return found;
    }
    
}