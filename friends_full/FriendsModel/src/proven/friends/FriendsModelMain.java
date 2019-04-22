package proven.friends;

import proven.friends.model.FriendModel;
import proven.friends.model.Friend;

/**
 * <strong>FriendsModelMain.java</strong>
 * Application to manage friends and categories. uses the
 * {@link proven.friends.controllers.FriendController} class.
 *
 * @author Jose Moreno
 */
public class FriendsModelMain {

    public static void main(String[] args) {
        FriendModel model = new FriendModel();

        model.add(new Friend("111", "Susan", "Sarandom", 21, "1986-04-27", "email@y.com" ));        
        System.out.println(model.toString());
    }
}