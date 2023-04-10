package app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
    private int userId = 0;
    private String validateUserId = userId + String.valueOf(System.nanoTime());
    private String name = "";
    private String userName = "";
    private Boolean gender = null;
    private String email = "";
    private String password = "";
    private String avatar = "";
    private boolean status = false;
    private Date userUpdatedTime = new Date();
    private Date emailUpdatedTime = new Date();
    private Set<Role> role = new HashSet<>();

    public User() {
    }

    public User(int userId, String name, String userName, Boolean gender, String email, String password, boolean status, Set<Role> role) {
        this.userId = userId;
        this.validateUserId = userId + String.valueOf(System.nanoTime());
        this.name = name;
        this.userName = userName;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.status = status;
        this.role = role;
    }

    public User(int userId, String name, String userName, Boolean gender, String email, String password, String avatar, boolean status, Set<Role> role) {
        this.userId = userId;
        this.validateUserId = userId + " - " + System.nanoTime();
        this.name = name;
        this.userName = userName;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.status = status;
        this.role = role;
    }

    public User(int userId, String name, String userName, String email, String password, Set<Role> role) {
        this.userId = userId;
        this.validateUserId = userId + " - " + System.nanoTime();
        this.name = name;
        this.userName = userName;
        this.gender = null;
        this.email = email;
        this.password = password;
        this.avatar = "";
        this.status = false;
        this.role = role;
    }

    public String getValidateUserId() {
        return validateUserId;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean isGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getUserUpdatedTime() {
        return userUpdatedTime;
    }

    public void setUserUpdatedTime(Date userUpdatedTime) {
        this.userUpdatedTime = userUpdatedTime;
    }

    public Date getEmailUpdatedTime() {
        return emailUpdatedTime;
    }

    public void setEmailUpdatedTime(Date emailUpdatedTime) {
        this.emailUpdatedTime = emailUpdatedTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", validateUserId='" + validateUserId + '\'' +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", status=" + status +
                ", userUpdatedTime=" + userUpdatedTime +
                ", emailUpdatedTime=" + emailUpdatedTime +
                ", role=" + role +
                '}';
    }
}
