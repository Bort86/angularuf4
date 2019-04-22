package proven.friends.servlets;

import java.io.IOException;
import java.util.List;

import proven.friends.lib.FriendForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import proven.friends.model.Friend;
import proven.friends.model.FriendModel;

/**
 * Servlet to resolve requests in Friends application
 * @author ProvenSoft
 */
public class FriendServletJson extends HttpServlet {

    private FriendModel model;

    @Override
    public void init() throws ServletException {
        this.model = new FriendModel();
    }

    /**
     * Handles the HTTP GET method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP POST method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP PUT method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP DELETE method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * ProcessRequest processes all http queries
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "list_all": //list all friends
                    listAllFriends(request, response);
                    break;
                case "search_by_phone": //list friend by phone
                    searchByPhone(request, response);
                    break;
                case "search_by_name": //list friend by phone
                    searchByName(request, response);
                    break;
                case "add": //add
                    //if (secured(request)) {
                        addNewFriend(request, response);
                    //}
                    break;
                case "modify": //modify
                    //if (secured(request)) {
                        modifyFriend(request, response);
                    //}
                    break;
                case "remove": //delete
                    //if (secured(request)) {
                        removeFriend(request, response);
                    //}
                    break;
                default: //unknown option.
                    redirectError(request, response, "bad_parameter");
                    break;
            }
        } else { // parameter action needed
            redirectError(request, response, "no_action");
        }
    }

    /**
     * serves a list of all friends
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    public void listAllFriends(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Friend> entityList = (List<Friend>) model.findAll();
        FriendRequestResult result = new FriendRequestResult(entityList, 1);
        request.setAttribute("result", result);
        RequestDispatcher rd = getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/json-result.jsp");
        rd.forward(request, response);
    }

    /**
     * Adds new Friend to data source of Model
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void addNewFriend(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int resultCode = -1;
        Friend friend = FriendForm.getData(request);
        if (friend != null) {
            resultCode = model.add(friend);
        } else {
            resultCode = -1;
        }
        FriendRequestResult result = new FriendRequestResult(friend, resultCode);
        request.setAttribute("result", result);
        RequestDispatcher rd = getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/json-result.jsp");
        rd.forward(request, response);

    }

    /**
     * Search a Friend by phone in data source of Model
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void searchByPhone(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String phone = request.getParameter("phone");
        FriendRequestResult result;
        if (phone != null) {
            Friend friend = new Friend(phone);
            Friend found = model.find(friend);
            if (found != null) {
                result = new FriendRequestResult(found, 1);
            } else {
                result = new FriendRequestResult(null, -2);
            }
        } else {
            result = new FriendRequestResult(null, -1);
        }
        request.setAttribute("result", result);
        RequestDispatcher rd = getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/json-result.jsp");
        rd.forward(request, response);
    }

    /**
     * Search a List of Friends by name in data source of Model
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void searchByName(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        FriendRequestResult result;
        if (name != null) {
            List<Friend> resultList = model.findFriendsByName(name);
            if (resultList != null) {
                result = new FriendRequestResult(resultList, 1);
            } else {
                result = new FriendRequestResult(null, -2);
            }
        } else {
            result = new FriendRequestResult(null, -1);
        }
        request.setAttribute("result", result);
        RequestDispatcher rd = getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/json-result.jsp");
        rd.forward(request, response);
    }

    /**
     * Modify Friend to data source of Model
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void modifyFriend(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Friend friend = FriendForm.getData(request);
        String oldPhone = request.getParameter("oldphone");
        int resultCode = -1;
        if (friend != null && oldPhone != null) {
            Friend oldFriend = new Friend(oldPhone);
            resultCode = model.modify(oldFriend, friend);
        } else {
            resultCode = -1;
        }
        FriendRequestResult result = new FriendRequestResult(friend, resultCode);
        request.setAttribute("result", result);
        RequestDispatcher rd = getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/json-result.jsp");
        rd.forward(request, response);

    }

    /**
     * Remove a Friend from data source of Model
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void removeFriend(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String phone = request.getParameter("phone");
        Friend friend = null;
        int resultCode = -1;
        if (phone != null) {
            friend = new Friend(phone);
            resultCode = model.remove(friend);

        }
        FriendRequestResult result = new FriendRequestResult(friend, resultCode);
        request.setAttribute("result", result);
        RequestDispatcher rd = getServletContext()
            .getRequestDispatcher("/WEB-INF/jsp/json-result.jsp");
        rd.forward(request, response);
    }

    /**
     * serves Bad Request errors with HTTP Status 400
     * @param request
     * @param response
     * @param error
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    public void redirectError(HttpServletRequest request, HttpServletResponse response, String error)
            throws ServletException, IOException {
        String errorMsg = null;
        switch (error) {
            case "bad_parameter": // bad parameter action
                errorMsg = "Invalid action parameter";
                break;
            default: // need parameter action
                errorMsg = "Parameter action needed";
        }
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, errorMsg);
    }

    /**
     * Validates role and session of authenticated User
     * @param request
     * @return true if user has session authenticated , false otherwise
     */
    private boolean secured(HttpServletRequest request) {
        boolean logged = false;
        HttpSession session = request.getSession(false);
        if (session != null) {
            String user = (String) session.getAttribute("username");
            String role = (String) session.getAttribute("userrole");
            if (role != null && user != null) {
                if (role.equals("admin")) {
                    logged = true;
                }
            }            
        } else {
            logged = false;
        }
        return logged;
    }

}
