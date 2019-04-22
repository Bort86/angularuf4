package proven.friends.webservices;

import com.google.gson.Gson;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import proven.friends.model.Friend;
import proven.friends.model.FriendModel;
import proven.msg.Message;
import proven.msg.PropertiesLanguage;

/**
 * Service of friend application
 *
 * @author ProvenSoft
 */
@Path("/friend-service")
public class FriendService {

    /**
     * data model to provide data access.
     */
    private final FriendModel friendModel;

    /**
     * Constructor. It gets a reference to the model and saves it in the
     * application context to
     *
     * @param context the application context
     */
    public FriendService(@Context ServletContext context) {
        if (context.getAttribute("friendModel") != null) {
            friendModel = (FriendModel) context.getAttribute("friendModel");
        } else {
            friendModel = new FriendModel();
            context.setAttribute("friendModel", friendModel);
        }
    }

    /**
     * gets all friends
     *
     * @return list of friends in json
     */
    @GET
    @Path("/friends")
    @Produces(MediaType.APPLICATION_JSON)
    public String searchAll() {
        return new Gson().toJson(friendModel.findAll());
    }

    /**
     * Find friend by phone
     *
     * @param phone to search for
     * @return friend or code 0 Friend Not Found
     */
    @GET
    @Path("/friend/{phone}")
    @Produces(MediaType.APPLICATION_JSON)
    public String searchByPhone(@PathParam("phone") String phone) {
        Friend f = friendModel.find(new Friend(phone));
        if (f == null) {
            return new Gson().toJson(new Message(0,
                    PropertiesLanguage.returnValue(Locale.ENGLISH, "notfound")));
        } else {
            return new Gson().toJson(f);
        }
    }

    /**
     * Find friend by name
     *
     * @param name to search for
     * @return friend or code 0 Friend Not Found
     */
    @GET
    @Path("/friend/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String searchFriendByName(@PathParam("name") String name) {
        List<Friend> resultList = friendModel.findFriendsByName(name);
        if (resultList == null) {
            return new Gson().toJson(new Message(0,
                    PropertiesLanguage.returnValue(Locale.ENGLISH, "notfound")));
        } else {
            return new Gson().toJson(resultList);
        }
    }

    /**
     * Add a new friend
     *
     * @param phone
     * @param name
     * @param age
     * @return 1 ok , 0 fail
     */
    @POST
    @Path("/friend/add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String add(@FormParam("phone") String phone,
            @FormParam("name") String name,
            @FormParam("surnames") String surnames,
            @FormParam("age") int age,
            @FormParam("bornDate") String bornDate,
            @FormParam("email") String email) {
        Friend f = new Friend(phone, name, surnames, age, bornDate, email);
        if (friendModel.add(f) == 1) {
            return new Gson().toJson(new Message(1,
                    PropertiesLanguage.returnValue(Locale.ENGLISH, "ok")));
        } else {
            return new Gson().toJson(new Message(0,
                    PropertiesLanguage.returnValue(Locale.ENGLISH, "fail")));
        }
    }

    /**
     * Modify a friend
     *
     * @param oldPhone
     * @param phone
     * @param name
     * @param age
     * @return 1 ok , 0 fail
     */
    @POST
    @Path("/friend/{oldphone}/update")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public String modify(@PathParam("oldphone") String oldPhone,
            @FormParam("phone") String phone,
            @FormParam("name") String name,
            @FormParam("surnames") String surnames,
            @FormParam("age") int age,
            @FormParam("bornDate") String bornDate,
            @FormParam("email") String email) {
        if (friendModel.modify(
                new Friend(oldPhone, name, surnames, age, bornDate, email),
                new Friend(phone, name, surnames, age, bornDate, email)
        ) == 1) {
            return new Gson().toJson(new Message(1,
                    PropertiesLanguage.returnValue(Locale.ENGLISH, "ok")));
        } else {
            return new Gson().toJson(new Message(0,
                    PropertiesLanguage.returnValue(Locale.ENGLISH, "fail")));
        }
    }

    /**
     * Remove a friend
     *
     * @param phone of friend to remove
     * @return 1 ok , 0 fail
     *
     */
    @POST
    @Path("/friend/{phone}/delete")
    @Produces(MediaType.APPLICATION_JSON)
    public String remove(@PathParam("phone") String phone) {
        Friend f = new Friend(phone);
        if (friendModel.remove(f) == 1) {
            return new Gson().toJson(new Message(1,
                    PropertiesLanguage.returnValue(Locale.ENGLISH, "ok")));
        } else {
            return new Gson().toJson(new Message(0,
                    PropertiesLanguage.returnValue(Locale.ENGLISH, "fail")));
        }
    }

}
