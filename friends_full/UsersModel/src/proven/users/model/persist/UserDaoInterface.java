package proven.users.model.persist;

import java.util.List;
import proven.users.model.User;

/**
 * Interface to validate Users
 * @author Proven
 */
public interface UserDaoInterface {
    
    /**
     * Validates User 
     * @param User to validate
     * @return true if user / password is correct false if not
     */
    public boolean validate(User u);
    
    /**
     * retrieves all data
     * @return all data
     */
    List<User> findAll();
    
    /**
     * retrieves a User by name
     * @return User / null if not exists
     */
    User find(User user);

    /**
     * adds a new element to the data repository
     * @param entity to be inserted
     * @return number of entries affected by the operation
     */
    int insert(User entity);
    
    /**
     * removes the given entity from the data repository
     * @param entity to be removed
     * @return number of entries affected by the operation
     */
    int remove(User entity);

    /**
     * modifies oldEntity to newEntity values
     * @param oldEntity entity to be updated
     * @param newEntity new values for the given entity
     * @return number of entries affected by the operation
     */
    int update(User oldEntity, User newEntity);
    
}
