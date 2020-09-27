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

    @RequestMapping(value = "/login.json" , method = RequestMethod.POST)
    public boolean ntdevUser(@RequestBody User reqUserInfo) {

        User ntUser = userRepository.findByUserName(reqUserInfo.getUserName());

        boolean res = false;
        if(!(ntUser == null) && ntUser.getPassword().equals(reqUserInfo.getPassword())) {

            res = true;
        }

        return res;
    }

}
