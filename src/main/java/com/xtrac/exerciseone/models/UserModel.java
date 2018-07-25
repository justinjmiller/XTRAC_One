package com.xtrac.exerciseone.models;

/**
 * Created with IntelliJ IDEA.
 * User: jumiller
 * Date: 7/24/2018
 * Time: 8:45 PM
 */
public class UserModel
{
    private String email;
    private String firstName;
    private String lastName;
    private String telephone;

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getTelephone()
    {
        return telephone;
    }

    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }
}
