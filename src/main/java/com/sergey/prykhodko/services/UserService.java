package com.sergey.prykhodko.services;

import com.sergey.prykhodko.dao.UserDAO;
import com.sergey.prykhodko.dao.factory.FactoryDAO;
import com.sergey.prykhodko.dao.factory.FactoryType;
import com.sergey.prykhodko.model.user.User;

import java.util.List;

public class UserService {

    private UserDAO userDAO;

    private UserService(FactoryType factoryType) {
        userDAO = FactoryDAO.getFactory(factoryType).getUserDAO();
    }

    public static UserService getUserService(FactoryType factoryType){
        return new UserService(factoryType);
    }

    public void addUserToDB(User user) {
        userDAO.add(user);
    }

    public List<String> getAllLogins() {
        return userDAO.getAllLogins();
    }

    public String getPasswordByLogin(String login) {
        User user = FactoryDAO.getFactory(FactoryType.SPRING).getUserDAO().getByLogin(login);
        return user.getPassword();
    }

    public User getUserByLogin(String login) {
        return FactoryDAO.getFactory(FactoryType.SPRING).getUserDAO().getByLogin(login);
    }
}
