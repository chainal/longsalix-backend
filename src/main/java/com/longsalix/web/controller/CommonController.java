package com.longsalix.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.longsalix.web.dao.UserRepository;
import com.longsalix.web.domain.User;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        List<User> existUser = userRepository.findUserByName(user.getName());
        if (existUser.isEmpty()) {
            DateTime now = new DateTime();
            user.setId(null);
            user.setCreate_time(now.toString("yyyy/MM/dd HH:mm:ss"));
            userRepository.save(user);
            return "SUCCESS";
        } else {
            return "EXISTED";
        }
    }

    @GetMapping("/user/list")
    @ResponseBody
    public String listUser(@RequestParam(value = "p", required = false, defaultValue = "1") int pageIndex) {
        int pageSize = 10;
        pageIndex = pageIndex - 1;
        Page<User> users = userRepository.findAll(PageRequest.of(pageIndex, pageSize));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total", "" + users.getNumberOfElements() + "/" + users.getTotalElements());
        jsonObject.put("page", "" + (pageIndex + 1) + "/" + users.getTotalPages());
        jsonObject.put("data", users.getContent());
        return jsonObject.toJSONString();
    }

    @PostMapping("/user/delete")
    @ResponseBody
    public String deleteUser(@RequestBody String body) {
        try {
            User user = JSON.parseObject(body, User.class);
            userRepository.deleteById(user.getId());
            return "SUCCESS";
        } catch (Exception e) {
            logger.error("", e);
            return "FAILED";
        }
    }
}
