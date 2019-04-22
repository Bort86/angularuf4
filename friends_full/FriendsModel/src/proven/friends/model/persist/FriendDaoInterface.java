package proven.friends.model.persist;

import java.util.List;
import proven.friends.model.Friend;

/**
 *
 * @author Jose Moreno
 */
public interface FriendDaoInterface {

    /**
     * searches entity in the data
     * @param entity to search
     * @return entity found
     */
    Friend find(Friend entity);

    /**
     * retrieves all data
     * @return all data
     */
    List<Friend> findAll();

    /**
     * adds a new element to the data repository
     * @param entity to be inserted
     * @return number of entries affected by the operation
     */
    int insert(Friend entity);

    /**
     * removes the given entity from the data repository
     * @param entity to be removed
     * @return number of entries affected by the operation
     */
    int delete(Friend entity);

    /**
     * modifies oldEntity to newEntity values
     * @param oldEntity entity to be updated
     * @param newEntity new values for the given entity
     * @return number of entries affected by the operation
     */
    int update(Friend oldEntity, Friend newEntity);

    /**
     * finds friends with the given name
     * @param name to search
     * @return a list of friend with the given name
     */
    public List<Friend> findByName(String name);
    
}