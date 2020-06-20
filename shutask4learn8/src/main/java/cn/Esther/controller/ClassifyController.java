package cn.Esther.controller;

import cn.Esther.pojo.Classify;
import cn.Esther.service.ClassifyService;
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
public class ClassifyController {
    //自动注入Service
    @Autowired
    private ClassifyService classifyService;

    Logger logger = Logger.getLogger(String.valueOf(ClassifyController.class));

    //    新增分类
    @ResponseBody
    @RequestMapping(value = "/a/u/classify", method =  RequestMethod.POST)
    public Map<String, Object> addClassify(Classify classify){
        logger.info("Add classify param is: "+ classify);
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Long time = System.currentTimeMillis();
            classify.setCreateAt(time);
            classify.setUpdateAt(time);
            classify.setCreateBy(time);
            classify.setUpdateBy(time);
            classifyService.insert(classify);

            resultMap.put("code", 0);
            resultMap.put("message", "success");
            resultMap.put("data", classify.getId());
        }catch (Exception e){
            resultMap.put("code", -1);
            resultMap.put("message", "error");
            e.printStackTrace();
            logger.info("Add classify error param is: " + classify);
        }
        return resultMap;
    }
    //    编辑分类
    @ResponseBody
    @RequestMapping(value = "/a/u/classify/{id}", method =  RequestMethod.PUT)
    // url传参 @PathVariable Long id
    public Map<String, Object> updateClassify(Classify classify, @PathVariable Long id){
        logger.info("update classify param is: "+ classify + "id  is" + id);
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Long time = System.currentTimeMillis();
            //  1. 首先查询以前的数据，判断有没有
            Classify updateClassify = classifyService.selectByPrimaryKey(id);

            logger.info("update Classify is :" + classify);
            if (null == updateClassify){
                resultMap.put("code", -100);
                resultMap.put("message", "修改内容不存在！");
            }
            // 2.更新内容
            updateClassify.setName(classify.getName());
            updateClassify.setPid(classify.getPid());
            updateClassify.setUpdateAt(time);
            updateClassify.setUpdateBy(time);
//            classifyService.insert(classify);

            resultMap.put("code", 0);
            resultMap.put("message", "success");
            resultMap.put("data", classify.getId());
        }catch (Exception e){
            resultMap.put("code", -1);
            resultMap.put("message", "error");
            e.printStackTrace();
            logger.info("Update classify error param is: " + classify + "id  is" + id);
        }
        return resultMap;
    }

    //    查询分类-id
    @ResponseBody
    @RequestMapping(value = "/a/u/classify/{id}", method =  RequestMethod.GET)
    // url传参 @PathVariable Long id
    public Map<String, Object > getClassify(@PathVariable Long id){
        logger.info("get classify param is: "+ "id  is" + id);
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Long time = System.currentTimeMillis();
            //  1. 首先查询以前的数据，判断有没有
            Classify classify = classifyService.selectByPrimaryKey(id);
            logger.info("Get Content is :" + classify);
            if (null == classify){
                resultMap.put("code", -100);
                resultMap.put("message", "获取内容不存在！");
            }

            resultMap.put("code", 0);
            resultMap.put("message", "success");
            resultMap.put("data", classify);

        }catch (Exception e){
            resultMap.put("code", -1);
            resultMap.put("message", "error");
            e.printStackTrace();
            logger.info("Get classify error param is: " + "id  is" + id);
        }
        return resultMap;
    }

    //    查询分类-list
    @ResponseBody
    @RequestMapping(value = "/a/u/classify/list", method =  RequestMethod.GET)
    // url传参 @PathVariable Long id
    public Map<String, Object > getClassifyList(){
        logger.info("get content list");
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Long time = System.currentTimeMillis();
            //  1. 首先查询以前的数据，判断有没有
            List<Classify> classifyList = classifyService.selectByList();
            logger.info("Get classify List :" + classifyList);
            if (null == classifyList){
                resultMap.put("code", -100);
                resultMap.put("message", "获取内容不存在！");
            }

            resultMap.put("code", 0);
            resultMap.put("message", "success");
            resultMap.put("data", classifyList);

        }catch (Exception e){
            resultMap.put("code", -1);
            resultMap.put("message", "error");
            e.printStackTrace();
            logger.info("Get classify List error ");
        }
        return resultMap;
    }

}
