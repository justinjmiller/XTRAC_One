package com.xtrac.exerciseone.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: jumiller
 * Date: 7/24/2018
 * Time: 9:11 PM
 */
public class ApplicationInputModel
{
    @NotNull(message = "Application name is required")
    private String name;
    private String description;

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getName()
    {

        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
