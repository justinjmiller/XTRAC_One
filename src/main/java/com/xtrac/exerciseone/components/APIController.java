package com.xtrac.exerciseone.components;

import com.xtrac.exerciseone.models.ApplicationInputModel;
import com.xtrac.exerciseone.models.ApplicationModel;
import com.xtrac.exerciseone.models.MessageModel;
import com.xtrac.exerciseone.models.UserModel;
import com.xtrac.exerciseone.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Date: 7/24/2018
 * Time: 8:40 PM
 */
@RestController
@RequestMapping("/api/users")
public class APIController
{
    private static final Logger logger = LoggerFactory.getLogger(APIController.class);

    UserService service = new UserService();

    @RequestMapping(value = {"","/"}, method = RequestMethod.POST)
    public ResponseEntity createUser(@Valid @RequestBody UserModel user, UriComponentsBuilder ucBuilder, Errors errors) {

        if ( user == null )
        {
            logger.info("Unable to create user record. User data missing");
            return new ResponseEntity(new MessageModel("Unable to create an account. User data missing."),HttpStatus.BAD_REQUEST);
        }

        if ( errors.hasErrors())
        {
            String error = errors.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));
            logger.info("Unable to create user record. User data invalid. " + error);
            return new ResponseEntity(new MessageModel("Unable to create an account. User data missing. " + error),HttpStatus.BAD_REQUEST);
        }

        logger.info("Create a user ('{}')", user.getEmail());

        if (service.userExists(user.getEmail())) {
            logger.info("Unable to create user record. A user with email {} already exist", user.getEmail());
            return new ResponseEntity(new MessageModel("Unable to create an account. A user with email " +
                    user.getEmail() + " already exist."),HttpStatus.CONFLICT);
        }

        try
        {
            service.saveUser(user);
        }
        catch (Exception ex)
        {
            logger.error("Error saving user - " + ex.getMessage(),ex);
            return new ResponseEntity(new MessageModel("Unable to create an account. An unexpected error occurred."),
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/users/{email}").buildAndExpand(user.getEmail()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{email}", method = RequestMethod.GET)
    public ResponseEntity getUser(@PathVariable String email)
    {
        logger.info("Finding user with email {}", email);
        UserModel user = service.findByEmail(email);
        if (user == null) {
            logger.info("User with email '{}' not found.", email);
            return new ResponseEntity(new MessageModel("User with email '" + email + "' not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserModel>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@PathVariable String email, @Valid @RequestBody UserModel user, Errors errors){
        logger.info("Updating user with email {}", email);

        if ( errors.hasErrors())
        {
            String error = errors.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(","));
            logger.info("Unable to update user record. User data invalid. " + error);
            return new ResponseEntity(new MessageModel("Unable to update account. User data missing. " + error),HttpStatus.BAD_REQUEST);
        }

        UserModel currentUser = service.findByEmail(email);

        if (currentUser == null) {
            logger.info("Unable to update. User with email '{}' not found.", email);
            return new ResponseEntity(new MessageModel("Unable to update. User with email '" + email + "' not found."),
                    HttpStatus.NOT_FOUND);
        }


        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setTelephone(user.getTelephone());

        try
        {
            service.saveUser(user);
        }
        catch (Exception ex)
        {
            logger.error("Error saving user - " + ex.getMessage(),ex);
            return new ResponseEntity(new MessageModel("Unable to create an account. An unexpected error occurred."),
                    HttpStatus.INTERNAL_SERVER_ERROR);

        }
        return new ResponseEntity<UserModel>(currentUser, HttpStatus.OK);
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable String email)
    {
        logger.info("Deleting User with email '{}'", email);

        if (!service.userExists(email)) {
            logger.info("Unable to delete. User with email '{}' not found.", email);
            return new ResponseEntity(new MessageModel("Unable to delete. User with email '" + email + "' not found."),
                    HttpStatus.NOT_FOUND);
        }
        service.deleteUserByEmail(email);
        return new ResponseEntity<UserModel>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{email}/applications", method = RequestMethod.POST)
    public ResponseEntity createApplication(@PathVariable String email, @Valid @RequestBody ApplicationInputModel application)
    {
        logger.info("Creating application named '{}' for user '{}'", application.getName(), email);

        if (!service.userExists(email)) {
            logger.info("Unable to create application. A User with email '{}' doesn't exist", email);
            return new ResponseEntity(new MessageModel("Unable to create application, user '" +
                    email + "' doesn't exist."),HttpStatus.BAD_REQUEST);
        }

        service.addUserApplication(email, application);

        return new ResponseEntity<String>(HttpStatus.CREATED);    }

    @RequestMapping(value = "/{email}/applications", method = RequestMethod.GET)
    public ResponseEntity getApplications(@PathVariable String email)
    {
        logger.info("Getting applications for user '{}'",  email);

        if (!service.userExists(email)) {
            logger.info("Unable to create application. A user with email '{}' doesn't exist", email);
            return new ResponseEntity(new MessageModel("Unable to create application, user '" +
                    email + "' doesn't exist."),HttpStatus.BAD_REQUEST);
        }

        List<ApplicationModel> apps = service.getUserApplications(email);
        if (apps.isEmpty()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(apps, HttpStatus.OK);
    }

}
