package com.longsalix.web.controller;

import com.alibaba.fastjson.JSON;
import com.longsalix.web.dao.UserRepository;
import com.longsalix.web.domain.User;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommonController {

    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    private final UserRepository userRepository;

    public CommonController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/status")
    @ResponseBody
    public String status() {
        return "running";
    }

    @PostMapping("/user/add")
    @ResponseBody
    public String addUser(@RequestBody String body) {
        logger.info(body);
        User user = JSON.parseObject(body, User.class);
        logger.info("{}", user);
        DateTime now = new DateTime();
        user.setCreate_time(now.toString("yyyyMMdd HH:mm:ss"));
        user = userRepository.save(user);
        return JSON.toJSONString(user);
    }
}
