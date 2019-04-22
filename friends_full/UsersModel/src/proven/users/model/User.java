package proven.users.model;

import java.util.Objects;

/**
 * ADT User
 * @author ProvenSoft
 */
public class User {

    private String username;
    private String password;
    private String role;

    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = "user";
    }

    public User() {
    }
    
    public User(User other) {
        this.username = other.username;
        this.password = other.password;
        this.role = other.role;
    }

    public User(String username) {
        this.username = username;
    }
    
    
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.username);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean b = false;
        if (obj==null) { //null object
            b = false;
        } else {
            if (this==obj) {  //same object
                b = true;
            } else {
                if (obj instanceof User) {
                    User other = (User) obj;
                    b = (this.username.equals(other.username));
                } else {
                    b = false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{");
        sb.append("username="); sb.append(username);
        sb.append(";password="); sb.append(password);
        sb.append(";role="); sb.append(role);
        sb.append("}");
        return sb.toString();
    }

}
