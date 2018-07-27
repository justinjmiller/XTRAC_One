package com.xtrac.exerciseone.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: jumiller
 * Date: 7/24/2018
 * Time: 8:45 PM
 */
public class UserModel
{

    @Email(message = "Invalid email")
    private String email;
    @NotNull(message = "First Name must be provided")
    private String firstName;
    @NotNull(message = "Last Name must be provided")
    private String lastName;
    @Pattern(regexp="(^$|[0-9]{10})")
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
