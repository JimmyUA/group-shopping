package com.sergey.prykhodko.model.user;

public class UserBuilder {

    private Integer idUser;
    private String login;
    private String password;
    private String name;
    private String lastName;
    private String email;
    private String mobile;

    public UserBuilder() {
    }

    public UserBuilder setIdUser(Integer idUser) {
        this.idUser = idUser;
        return this;
    }

    public UserBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder setMobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public User build(){
        User user = new User();
        user.setIdUser(idUser);
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setMobile(mobile);
        return user;
    }
}
