package com.ntd.controller;

import com.ntd.dao.UserDevPartRelRepository;
import com.ntd.dao.UserRepository;
import com.ntd.entity.UserDevPartRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class RestTestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDevPartRelRepository userDevPartRelRepository;

    @RequestMapping(value = "/")
    public String testFn() {

        return "hello welcome";
    }

    @RequestMapping(value = "/user")
    public String userRepository() {
        //System.out.println(ntDevRepo.findAll().size());
        return "result value:"+userRepository.findAll().size() + "  uesrRepository connect success";
    }

    @RequestMapping(value = "/relation")
    public String relation() {
        //System.out.println(ntDevRepo.findAll().size());

        List<UserDevPartRelation> userDevPartRelations = userDevPartRelRepository.findAll();

        return
                "result entity address:" + userDevPartRelations.toString()
                + "result value:" + userDevPartRelations.size()
                        + "  userDevPartRelRepository connect success";
    }

}
