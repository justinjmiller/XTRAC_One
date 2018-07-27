package com.xtrac.exerciseone.services;

import com.xtrac.exerciseone.models.ApplicationInputModel;
import com.xtrac.exerciseone.models.ApplicationModel;
import com.xtrac.exerciseone.models.UserApplicationModel;
import com.xtrac.exerciseone.models.UserModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jumiller
 * Date: 7/24/2018
 * Time: 8:47 PM
 */
public class UserService
{
    private static HashMap<String, UserApplicationModel> users = new HashMap<>();

    public void saveUser(UserModel user)
    {

        users.put(user.getEmail(), new UserApplicationModel(
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getTelephone()));
    }

    public UserModel findByEmail(String email)
    {
        return users.get(email);
    }

    public boolean userExists(String email)
    {
        return users.containsKey(email);
    }

    public void updateUser(UserModel user)
    {
        users.put(user.getEmail(), new UserApplicationModel(
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getTelephone()));
    }

    public void deleteUserByEmail(String email)
    {
        users.remove(email);
    }

    public List<ApplicationModel> getUserApplications(String email)
    {
        return new ArrayList<ApplicationModel>(users.get(email).getApps().values());
    }

    public void addUserApplication(String email, ApplicationInputModel application)
    {
        ApplicationModel app = new ApplicationModel();
        //todo: genereate key and code
        app.setName(application.getName());
        app.setDescription(application.getDescription());
        users.get(email).getApps().put(application.getName(),app);
    }
}
