package com.xtrac.exerciseone.models;

/**
 * Created with IntelliJ IDEA.
 * User: jumiller
 * Date: 7/24/2018
 * Time: 9:10 PM
 */
public class ApplicationModel extends ApplicationInputModel
{
    private String id;
    private String secret;
    private String email;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getSecret()
    {
        return secret;
    }

    public void setSecret(String secret)
    {
        this.secret = secret;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
