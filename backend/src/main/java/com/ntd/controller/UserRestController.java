package com.ntd.controller;

import com.ntd.dao.UserRepository;
import com.ntd.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserRestController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/user.json" , method = RequestMethod.GET)
    public User ntdevUser(@RequestParam(name = "username") String userName) {

        User ntUser = userRepository.findByUserName(userName);

        return ntUser;
    }

}
