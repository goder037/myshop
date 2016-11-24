package com.rocket.myshop.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/users")
public class UserController {

	@Resource
	ReloadableResourceBundleMessageSource messageSource;
	
    @RequestMapping(value = "/{username}", method = GET)
    @ResponseBody
    public User getUser(@PathVariable String username) throws HttpRequestMethodNotSupportedException {
        //simulate Manager/DAO call:
    	//int i = 1/0;
        return findUser(username);
    }

    /**
     * Simulates a Manager or DAO lookup for a stored user account.
     *
     * @param username the username for the user to lookup.  Supports only 'jsmith' and 'djones' for testing.
     * @return the associated user
     * @throws Exception 
     */
    private User findUser(String username) throws HttpRequestMethodNotSupportedException {
        if (!StringUtils.hasText(username)) {
            //throw new IllegalArgumentException("Username is required.");
            throw new IllegalArgumentException("Username is required.");
        }

        //simulate a successful lookup for 2 users:
        if ("jsmith".equals(username)) {
            return new User("Jane Smith", username);
        }
        if ("djones".equals(username)) {
            return new User("Don Jones", username);
        }

        //any other lookup throws an exception (not found):
        throw new HttpRequestMethodNotSupportedException("Unable to find user with username '" + username + "'");
    }
    
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public Object addUser(@Valid User user, Errors errors){
    	if(errors.hasErrors()){
    		return errors.getAllErrors();
    	}
    	return user;
    }


}
