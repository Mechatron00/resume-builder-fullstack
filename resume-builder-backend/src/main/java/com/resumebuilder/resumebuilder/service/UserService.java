package com.resumebuilder.resumebuilder.service;

import com.resumebuilder.resumebuilder.model.User;
import com.resumebuilder.resumebuilder.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService
{

    @Autowired
    private UserRepo userRepo;

    //login
    public ResponseEntity<?> getUserByEmailAndPassword(String userEmail, String confirmPassword)
    {
        Optional<User> exist = userRepo.findByUserEmailAndConfirmPassword(userEmail, confirmPassword);
        if(exist.isPresent())
        {
            User user = exist.get();
            String resumeId = user.getResumeId();
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else return  ResponseEntity.badRequest().body("User not found");
    }

    //signup
    public ResponseEntity<?> createUser(User user)
    {
        if (user!= null)
        {
            List<User> userList = userRepo.findAll();
            Stream<User> userStream = userList.stream();
           boolean isPresent = userStream.anyMatch(newUser -> newUser.getUserEmail().equals(user.getUserEmail()));
           if (isPresent)
           {
               return new ResponseEntity<>("Email alrady used",HttpStatus.NOT_ACCEPTABLE);
           }
           else if((user.getPassword()).equals(user.getConfirmPassword()))
            {
                User newUser = userRepo.save(user);

//                String userid = newUser.getId();
                return new ResponseEntity<>(newUser ,HttpStatus.OK);
            }
            else return ResponseEntity.badRequest().body("Password does not match");
        }
        else return ResponseEntity.badRequest().body("failed to create user");
    }

    public ResponseEntity<?> setResumeId(String id, String resumeId)
    {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent())
        {
            User getUser = user.get();
            getUser.setResumeId(resumeId);
            userRepo.save(getUser);
            return new ResponseEntity<>(getUser, HttpStatus.OK);
        }
        else return ResponseEntity.badRequest().body("Failed");
    }
}
