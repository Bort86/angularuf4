package proven.friends.lib;

import javax.servlet.http.HttpServletRequest;
import proven.friends.model.Friend;

public class FriendForm {

    /**
     * gets and validates data sent by client.
     * @param request http request object to get data from
     * @return friend object with data sent from client or null in case of error.
     */
    public static Friend getData(HttpServletRequest request) {
        Friend friend = null;
        try {
            String phone = request.getParameter("phone");
            String name = request.getParameter("name");
            String surnames = request.getParameter("surnames");
            String sage = request.getParameter("age");
            String bornDate = request.getParameter("bornDate");
            String email = request.getParameter("email");
            if ((phone == null) || (name == null) || (sage==null)) {
                friend = null;
            } else {
                int age = Integer.parseInt(sage);
                friend = new Friend(phone, name, surnames, age, bornDate, email);                
            }            
        } catch (NumberFormatException e) {
            friend = null;
        }
        return friend;
    }
    
}
