package proven.friends.controllers;

import java.util.List;
import proven.friends.model.Friend;
import proven.friends.model.FriendModel;
import proven.friends.views.FriendView;

/** 
 * <strong>FriendController.java</strong>
 * Class FriendController.
 * Friend controller.
 * uses the {@link proven.friends.model.FriendModel} class.
 * uses the {@link proven.friends.views.FriendView} class.
 * @author ProvenSoft
 */
public class FriendController {
    /**
     * the model for friend management
     */
    private final FriendModel model;
    /**
     * the view for friend management
     */
    private final FriendView view;

    /**
     * constructor of controller for friend management
     * @param model reference for data service
     */
    public FriendController(FriendModel model) {
        this.model = model;
        this.view = new FriendView(this,model);
        view.display();
    }
    
    /**
     * performs closing application tasks
     */
    public void exitApplication() {
        System.exit(0);
    }
    
    /**
     * Processes requests from the view.
     * @param action to execute.
     */
    public void processRequest(String action) {
        action = (action==null) ? "wrong_action" : action;
        switch (action) {
            case "exit": 
                exitApplication();
                break;
            case "wrong_action":
            default:
                view.showMessage("Wrong option");
                break;
        }
    }
    
    /**
     * lists all friends in the data source.
     */
    public void listAllFriends() {
        List<Friend> data = model.findAll();
        view.showFriendTable(data);
    }   
    
    /**
     * performs the search of a friend by phone.
     * Asks the user to input the phone of the friend to search.
     * If successfully entered, it asks the model to carry out the search,
     * and finally report the result to the user.
     */
    public void searchFriendByPhone() {
        String phone = view.showInputDialog("Input phone: ");
        if (phone != null) {
            Friend friend = new Friend(phone);
            Friend found = model.find(friend);
            if (found != null) {
                view.showFriendForm(found, "display");
            } else {
                view.showMessage("Friend not found");
            }
        }
    }    
 
    /**
     * performs the search of the friends with a given name
     * Asks the user to input the name of the friends to search.
     * If successfully entered, it asks the model to carry out the search,
     * and finally report the result to the user.
     */
    public void searchFriendsByName() {
        String name = view.showInputDialog("Input name: ");
        if (name != null) {
            List<Friend> data = model.findFriendsByName(name);
            view.showFriendTable(data);            
        }
    }  
    
    /**
     * adds a new friend to data source
     * @param friend to add
     */
    public void addFriend(Friend friend) {
        int result = model.add(friend);
        if (result > 0) {
            view.showMessage("Friend successfully added");
        } else  {
            view.showMessage("Friend has not been added");
        }
    } 

    /**
     * modifies a Friend in the data source
     * @param oldVersion old data
     * @param newVersion new data
     */
    public void modifyFriend(Friend oldVersion, Friend newVersion) {
        int result = model.modify(oldVersion, newVersion);
        if (result > 0) {
            view.showMessage("Friend successfully modified");
        } else  {
            view.showMessage("Friend has not been modified");
        }
    }      
     
    /**
     * removes a Friend from the data source
     * @param friend to be removed 
     */
    public void removeFriend(Friend friend) {
        int result = model.remove(friend);
        if (result > 0) {
            view.showMessage("Friend successfully removed");
        } else  {
            view.showMessage("Friend has not been removed");
        }
    }
    
    /**
     * provides a form to modify a friend.
     */
    public void modifyFriendForm() {
        String phone = view.showInputDialog("Input phone: ");
        if (phone != null) {
            Friend friend = new Friend(phone);
            Friend found = model.find(friend);
            if (found != null) {
                view.showFriendForm(found, "modify");
            } else {
                view.showMessage("Friend not found");
            }
        }
    }

    /**
     * provides a form to remove a friend.
     */
    public void removeFriendForm() {
        String phone = view.showInputDialog("Input phone: ");
        if (phone != null) {
            Friend friend = new Friend(phone);
            Friend found = model.find(friend);
            if (found != null) {
                view.showFriendForm(found, "remove");
            } else {
                view.showMessage("Friend not found");
            }
        }
    }  

}