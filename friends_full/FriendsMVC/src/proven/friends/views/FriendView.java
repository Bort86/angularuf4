package proven.friends.views;

import java.util.List;
import java.util.Scanner;
import proven.friends.controllers.FriendController;
import proven.friends.model.Friend;
import proven.friends.model.FriendModel;

/**
 * FriendView class
 * View of friend manager application
 * uses the {@link proven.friends.views.Menu} class.
 * uses the {@link proven.friends.controllers.FriendController} class.
 * uses the {@link proven.friends.model.FriendModel} class.
 * @author ProvenSoft
 */
public class FriendView {

    /**
     * controller for friend management
     */
    private final FriendController control;
    /**
     * model for friend management
     */
    private final FriendModel model;
    /**
     * menu for friend management
     */
    private final FriendMenu menu;

    /**
     * contructor for friend view
     * @param control controller
     * @param model model
     */
    public FriendView(FriendController control, FriendModel model) {
        this.control = control;
        this.model = model;
        this.menu = new FriendMenu();
    }

    /**
     * prompts a message to the user and asks for an answer
     * @param message to show
     * @return user answer
     */
    public String showInputDialog(String message) {
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        return sc.next();
    }

    /**
     * Displays a message.
     * @param message to display
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
    
    /**
     * Shows the view and performs user interaction.
     */
    public void display() {
        do {
            menu.show();
            String action = menu.getSelectedOptionActionCommand();
            processAction(action);                 
        } while (true);
    }   

    /**
     * control method for actions in the view
     * @param action to execute
     */
    private void processAction(String action) {
        switch (action) {
            case "search_friend_by_phone":
                control.searchFriendByPhone();
                break;
            case "search_friends_by_name":
                control.searchFriendsByName();
                break;
            case "friend_form_add":
                showFriendForm(null, "add");
                break;
            case "friend_form_modify":
                control.modifyFriendForm();
                break;
            case "friend_form_remove":
                control.removeFriendForm();
                break;
            case "list_all_friends":
                control.listAllFriends();
                break;
            default:
                control.processRequest(action);      
                break;
        }
    }

    /**
     * prints a table with the given data
     * @param data to print
     */
    public void showFriendTable(List<Friend> data) {
        for (Friend elem: data) {
            System.out.println(elem.toString());
        }
        System.out.format("%d elements found.\n", data.size());
    }

    /**
     * Displays a form with data of friend f.
     * @param input friend to show
     * @param type the type of the form: add, modify, remove, display, ...
     */
    public void showFriendForm(Friend input, String type) {
        Friend f = null;
        System.out.println("Type of form: "+type);
        if (input != null) {
            System.out.println(input.toString());            
        }
        if ((type.equals("add")) || (type.equals("modify"))) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Input phone: ");
            String phone = scan.next();
            System.out.print("Input name: ");
            String name = scan.next();
            System.out.print("Input age: ");
            int age = scan.nextInt();
            f = new Friend(phone, name, age);
        }
        switch (type) {
            case "remove":
                control.removeFriend(input);
                break;
            case "modify":
                control.modifyFriend(input, f);
                break;
            case "add":
                control.addFriend(f);
                break;   
            default:
                break;
        }
        
    }
 
} //end of class