package cn.Esther.controller;

import cn.Esther.pojo.User;
import cn.Esther.pojo.Works;
import cn.Esther.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    Logger logger = Logger.getLogger(String.valueOf(UserController.class));

    //   普通用户注册
    @ResponseBody
    @RequestMapping(value = "/a/u/user", method =  RequestMethod.POST)
    public Map<String, Object> addUser(User user){

        logger.info("Add user param is: "+ user +"user.name: " +user.getUserName() +user.getUserId()+user.getPassword());
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Long time = System.currentTimeMillis();

            user.setCreateAt(time);
            user.setRole(2);//normal user
            userService.insert(user);

            resultMap.put("code", 0);
            resultMap.put("message", "success");
            resultMap.put("data", user.getUserId());

        }catch (Exception e){
            resultMap.put("code", -1);
            resultMap.put("message", "error");
            e.printStackTrace();
            logger.info("Add user error param is: " + user +"user.name: " +user.getUserId()+user.getUserName() +user.getPassword());
        }
        return resultMap;
    }

    //    查询用户-id
    @ResponseBody
    @RequestMapping(value = "/a/u/user/{user_name}", method =  RequestMethod.GET)
    // url传参 @PathVariable Long id
    public Map<String, Object > getUserid(@PathVariable String user_name){
        logger.info("get work param is: "+ "id  is" + user_name);
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Long time = System.currentTimeMillis();
            //  1. 首先查询以前的数据，判断有没有
            User user =userService.selectByPrimaryKey(user_name);
            logger.info("Get Content is :" + user);
            if (null == user){
                resultMap.put("code", -100);
                resultMap.put("message", "获取内容不存在！");
            }

            resultMap.put("code", 0);
            resultMap.put("message", "success");
            resultMap.put("data", user);

        }catch (Exception e){
            resultMap.put("code", -1);
            resultMap.put("message", "error");
            e.printStackTrace();
            logger.info("Get work error param is: " + "id  is" + user_name);
        }
        return resultMap;
    }

    //    用户登录-id
    @ResponseBody
    @RequestMapping(value = "/a/u/user", method =  RequestMethod.GET)
    public Map<String, Object > getUser(User user){
        logger.info("login user param is: "+ "user_name  is" + user.getUserName());
        Map<String, Object> resultMap = new HashMap<>();
        try {
//            Long time = System.currentTimeMillis();
            //  1. 首先查询以前的数据，判断有没有
            User checkUser =userService.selectByPrimaryKey(user.getUserName());
            logger.info("Get user is :" + user);
            if (null == checkUser){
                resultMap.put("code", -100);
                resultMap.put("message", "用户不存在！");
            }


            if(user.getPassword().equals(checkUser.getPassword())){
                resultMap.put("code", 0);
                resultMap.put("message", "login successfully! ");
                resultMap.put("data",user.getUserName());
            }else{
                resultMap.put("code", -200);
                resultMap.put("message", "用户密码不正确！");
            }



        }catch (Exception e){
            resultMap.put("code", -1);
            resultMap.put("message", "login error");
            e.printStackTrace();
            logger.info("Get user error param is: " + "id  is" + user.getUserId()+ user.getUserName());
        }
        return resultMap;
    }

    //    管理人员查看用户列表
    @ResponseBody
    @RequestMapping(value = "/a/u/user/list", method =  RequestMethod.GET)
    public Map<String, Object > getUserList(User user){
        logger.info("get user list");
        Map<String, Object> resultMap = new HashMap<>();
        try {
//            Long time = System.currentTimeMillis();
            //  1. 首先查询以前的数据，判断有没有
            User checkUser =userService.selectByPrimaryKey(user.getUserName());
            List<User> userList = userService.selectByList();
            logger.info("Get user list by :" + user);
            if (null == checkUser){
                resultMap.put("code", -100);
                resultMap.put("message", "用户不存在！");
            }


            if(user.getPassword().equals(checkUser.getPassword()) && checkUser.getRole() == 1){
                resultMap.put("code", 0);
                resultMap.put("message", "login successfully! ");
                resultMap.put("data",userList);
            }else if(checkUser.getRole() != 1){
                resultMap.put("code", -20);
                resultMap.put("message", "用户没有权限！");
            }else{
                resultMap.put("code", -200);
                resultMap.put("message", "用户密码不正确！");
            }

        }catch (Exception e){
            resultMap.put("code", -1);
            resultMap.put("message", "login error");
            e.printStackTrace();
            logger.info("Get user error param is: " + "id  is" + user.getUserId()+ user.getUserName());
        }
        return resultMap;
    }
}
