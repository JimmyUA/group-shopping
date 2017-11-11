package com.sergey.prykhodko.util;

import com.sergey.prykhodko.model.user.User;

public class UserSearchCriteria {
    private Integer idUser;
    private String login;
    private String name;
    private String lastName;
    private String email;
    private String mobile;


    public UserSearchCriteria() {
    }

    public boolean isEmpty() {
        return (idUser == null && email == null && name == null && lastName == null
        && login == null && mobile == null);
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean accept(User user) {
        return acceptId(user.getIdUser())
                && acceptLogin(user.getLogin())
                && acceptEmail(user.getEmail())
                && acceptName(user.getName())
                && acceptLastName(user.getLastName())
                && acceptMobile(user.getMobile());
    }

    private boolean acceptMobile(String mobile) {
        return this.mobile == null || this.mobile.equals(mobile);
    }

    private boolean acceptId(Integer id) {
        return this.idUser == null || this.idUser.equals(id);
    }

    private boolean acceptLogin(String login) {
        return this.login == null || this.login.equals(login);
    }

    private boolean acceptEmail(String email) {
        return this.email == null || this.email.equals(email);
    }

    private boolean acceptName(String firstName) {
        return this.name == null || this.name.equals(firstName);
    }

    private boolean acceptLastName(String lastName) {
        return this.lastName == null || this.lastName.equals(lastName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserSearchCriteria that = (UserSearchCriteria) o;

        if (!idUser.equals(that.idUser)) return false;
        if (!login.equals(that.login)) return false;
        if (!name.equals(that.name)) return false;
        if (!lastName.equals(that.lastName)) return false;
        if (!email.equals(that.email)) return false;
        return mobile.equals(that.mobile);
    }

    @Override
    public int hashCode() {
        int result = idUser.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + mobile.hashCode();
        return result;
    }
}
