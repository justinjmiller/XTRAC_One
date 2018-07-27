package com.xtrac.exerciseone.models;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: jumiller
 * Date: 7/26/2018
 * Time: 9:53 PM
 */
public class UserApplicationModel extends UserModel
{
    private HashMap<String, ApplicationModel> apps = new HashMap<>();

    public UserApplicationModel(String email, String firstName, String lastName, String telephone)
    {
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
        setTelephone(telephone);
    }

    public HashMap<String, ApplicationModel> getApps()
    {
        return apps;
    }

    public void setApps(HashMap<String, ApplicationModel> apps)
    {
        this.apps = apps;
    }
}
