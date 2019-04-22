package proven.friends.model;

import java.util.Objects;

/**
 * <strong>Friend.java</strong>
 * ADT for a friend.
 */
public class Friend {
    private String phone;
    private String name;
    private String surnames;
    private int age;
    private String bornDate;
    private String email;

    public Friend(String phone, String name, String surnames, int age, String bornDate, String email) {
        this.phone = phone;
        this.name = name;
        this.surnames = surnames;
        this.age = age;
        this.bornDate = bornDate;
        this.email = email;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public Friend() {
    }
    
    public Friend(String phone) {
        this.phone = phone;
    }  

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.phone);
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
                if (obj instanceof Friend) {
                    Friend other = (Friend) obj;
                    b = (this.phone.equals(other.phone));
                } else {
                    b = false;
                }
            }
        }
        return b;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Friend{");
        sb.append("phone="); sb.append(phone);
        sb.append(";name="); sb.append(name);
        sb.append(";surnames="); sb.append(surnames);
        sb.append(";age="); sb.append(age);
        sb.append(";bornDate="); sb.append(bornDate);
        sb.append(";email="); sb.append(email);
        sb.append("}");
        return sb.toString();
    }
    
}