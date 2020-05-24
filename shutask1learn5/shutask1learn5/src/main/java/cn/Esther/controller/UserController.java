package cn.Esther.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import cn.Esther.pojo.User;

import cn.Esther.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private UserService userService;

//    新增用户
//    @param id 用户id
//    @param user 用户信息
//    @return 结果
    @RequestMapping(value = "/a/user", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addUser(User user){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", 0);
        try {
            Integer id = userService.addUser(user);
            resultMap.put("data", id);
        }catch (Exception e){
            e.getMessage();
            logger.error(e.getMessage());
            resultMap.put("status", -1);
        }
        return resultMap;
    }

//    删除用户
//    @param id 用户id
//    @param user 用户信息
//    @return 结果
    @RequestMapping(value = "/a/user/{id}", method=RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> deleteUser(@PathVariable Long id){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", 0);
        try {
            Integer result = userService.deleteUser(id);
            resultMap.put("data", result);
        }catch (Exception e){
            e.getMessage();
            logger.error(e.getMessage());
            resultMap.put("status", -1);
        }
        return resultMap;
    }



//    更改用户
//    @param id 用户id
//    @param user 用户信息
//    @return 结果
    @RequestMapping(value = "/a/user/{id}", method=RequestMethod.PUT)
    @ResponseBody
    public Map<String, Object> updateUser(@PathVariable Long id, User user){
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", 0);
        try {
            Integer result = userService.updateUser(user);
            resultMap.put("data", result);
        }catch (Exception e){
            e.getMessage();
            logger.error(e.getMessage());
            resultMap.put("status", -1);
        }
        return resultMap;
    }



    //    查询用户详情
//    @param id 用户id
//    @return 结果
    @RequestMapping(value = "/a/user/{id}", method=RequestMethod.GET)
    @ResponseBody
    public User getUser(@PathVariable Long id){
        logger.info("你好世界");
        System.out.println("________________");
        User user = userService.selectObject(id);

        //不是userService.selectObject(id)获取user，而是用假数据
//        Long time = System.currentTimeMillis();
//        User user1 = new User();
//        user1.setName("小");
//        user1.setQq("1234556");
//        user1.setType(1);
//        user1.setTime(time);
//        user1.setLink("bbb");
//        user1.setCreateAt(time);
//        user1.setCreateBy(time);
//        user1.setUpdateAt(time);
//        user1.setUpdateBy(time);


        logger.info("user is "+user);
        return user;
    }


    //    查询用户列表
//    @return 结果
    @RequestMapping(value = "/a/user/list", method=RequestMethod.GET)
//    @RequestMapping(value = "/a/user/list/{id}", method=RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getUserList(){
//    public Map<String, Object> getUserList(@PathVariable Long id,
//                                           String name,
//                                           @RequestParam("test") String dog){

//        logger.info("id is:" + id +"name is :"+ name + " dog is" + dog);


        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", 0);
        try {
            List<User> userList = userService.selectList();//改成.selectList(name)就可以用name查询
            resultMap.put("data", userList);
        }catch (Exception e){
            e.getMessage();
            logger.error(e.getMessage());
            resultMap.put("status", -1);
        }
        return resultMap;
    }

    @RequestMapping(value="/a/test", method= RequestMethod.GET) // method不写也行
    public String test(){
        //move to user, option+Enter
        User user = new User();

        System.out.println("te");
        return "test";
    }
}
