package com.example.firstcontroller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.example.firstcontroller.Errors.*;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class loginController {
    private List<User> Users = new ArrayList<>();
    @RequestMapping(value = "create-account", method = {RequestMethod.POST})
    public basicResponse createAccount(String name, String pass,String pass2) {
        basicResponse basicResponse = new basicResponse();
        if(name!=null && !name.isEmpty()){
            if(pass!=null && !pass.isEmpty()){
                if(pass.equals(pass2)){
                if(this.isStrongPassword(pass)){
                    if(!this.nameExists(name)){
                        this.Users.add(new User(name, pass));
                        basicResponse.setSuccess(true);}
                    else {basicResponse.setErrorCode(USER_NAME_EXISTS);}}
                      else {basicResponse.setErrorCode(ERROR_CODE_WEEK_PASSWORD);}}
                       else  {basicResponse.setErrorCode(ERROR_CODE_PASSWORDS_NOT_EQUALS);}}
                        else {basicResponse.setErrorCode(ERROR_CODE_MISSING_PASSWORD);}}
        else basicResponse.setErrorCode(ERROR_CODE_MISSING_USER_NAME);
        return basicResponse;

    }
     private boolean isStrongPassword(String pass){
       boolean isStrong = false;
       if(pass.length()>=6)isStrong=true;
       return isStrong;
    }

    private boolean nameExists(String name){
        boolean isExists = false;
        for(User user: this.Users){
            if(user.getUserName().equals(name)){
                isExists=true;
                break;
            }
        }
        return isExists;
    }
    @RequestMapping(value = "get-all-users")
    public List<User> getAllUsers(){
        return Users;
    }

    @RequestMapping(value = "login" , method = {RequestMethod.POST})
    public Object login (String name, String pass){
        basicResponse basicResponse = new basicResponse();
        if(name!=null && !name.isEmpty()){
            if(pass!=null && !pass.isEmpty()){
                User user = getUser(name,pass);
                if(user!=null){basicResponse.setSuccess(true); }
                else{basicResponse.setErrorCode(LOGIN_IS_WRONG);}
            }
            else {basicResponse.setErrorCode(ERROR_CODE_MISSING_PASSWORD);}}
        else basicResponse.setErrorCode(ERROR_CODE_MISSING_USER_NAME);
        return basicResponse;
    }

    public User getUser (String name, String pass){
        User user = null;
        for(User currentUser: this.Users){
            if(currentUser.isSameUser(name,pass)){
                user = currentUser;
                break;
            }
        }
        return user;
    }
    @RequestMapping(value = "add-note", method = {RequestMethod.POST})
     public basicResponse addNote (String name, String text){
        basicResponse basicResponse = new basicResponse();
        User user = findUser(name);
        if(user!=null)  {
            if(user.addNote(text)) {
                user.addNote(text);
                basicResponse.setSuccess(true);
            }else{basicResponse.setErrorCode(THERE_IS_SUCH_NOTE);}
        } else basicResponse.setErrorCode(DONT_FIND_USER);
        return basicResponse;
    }
    public User findUser (String name){
        User user = null;
        for(User currentUser: this.Users){
            if(currentUser.getUserName().equals(name)){
                user = currentUser;
                break;
            }
        }
        return user;
    }

}
