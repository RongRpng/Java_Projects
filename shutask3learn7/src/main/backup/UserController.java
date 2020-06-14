package cn.Esther.backup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

        logger.info("Add user param is: "+ user);
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Long time = System.currentTimeMillis();

            user.setCreateAt(time);
            user.setRole(2);//normal user
            userService.insert(user);

            resultMap.put("code", 0);
            resultMap.put("message", "success");
            resultMap.put("data", user.getId());
        }catch (Exception e){
            resultMap.put("code", -1);
            resultMap.put("message", "error");
            e.printStackTrace();
            logger.info("Add user error param is: " + user);
        }
        return resultMap;
    }

    //    用户登录-id
    @ResponseBody
    @RequestMapping(value = "/a/u/user", method =  RequestMethod.GET)
    public Map<String, Object > getUser(User user){
        logger.info("login user param is: "+ "user_name  is" + user.getName());
        Map<String, Object> resultMap = new HashMap<>();
        try {
//            Long time = System.currentTimeMillis();
            //  1. 首先查询以前的数据，判断有没有
            User checkUser =userService.selectByPrimaryKey(user.getName());
            logger.info("Get user is :" + user);
            if (null == checkUser){
                resultMap.put("code", -100);
                resultMap.put("message", "用户不存在！");
            }


            if(user.getPassword().equals(checkUser.getPassword())){
                resultMap.put("code", 0);
                resultMap.put("message", "login successfully! ");
                resultMap.put("data",user.getName());
            }else{
                resultMap.put("code", -200);
                resultMap.put("message", "用户密码不正确！");
            }



        }catch (Exception e){
            resultMap.put("code", -1);
            resultMap.put("message", "login error");
            e.printStackTrace();
            logger.info("Get user error param is: " + "id  is" + user.getId()+ user.getName());
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
            User checkUser =userService.selectByPrimaryKey(user.getName());
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
            logger.info("Get user error param is: " + "id  is" + user.getId()+ user.getName());
        }
        return resultMap;
    }
}
