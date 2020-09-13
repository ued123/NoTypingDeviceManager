package com.ntd.controller;

import com.ntd.dao.UserRepository;
import com.ntd.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping("/user.json")
    public User ntdevUser(@RequestParam(name = "username") String userName) {

        User ntUser = userRepository.findByUserName(userName);

        return ntUser;
    }

}
