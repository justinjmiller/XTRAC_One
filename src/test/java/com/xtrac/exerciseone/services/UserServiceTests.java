package com.xtrac.exerciseone.services;

import com.xtrac.exerciseone.models.UserModel;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: jumiller
 * Date: 7/26/2018
 * Time: 9:55 PM
 */
public class UserServiceTests
{
    private static UserService users = new UserService();

    @org.junit.BeforeClass
    public static void setupUsers()
    {
        UserModel model = new UserModel();
        model.setEmail("justin@test.com");
        model.setFirstName("Justin");
        model.setLastName("Miller");
        model.setTelephone("8015551212");
        users.saveUser(model);
    }

    @Test
    public void testFindByEmail() throws Exception
    {
        Assert.assertNotNull(users.findByEmail("justin@test.com"));
        Assert.assertNull(users.findByEmail("test@test.com"));
    }

    //todo: tests for adding / getting applications
}
