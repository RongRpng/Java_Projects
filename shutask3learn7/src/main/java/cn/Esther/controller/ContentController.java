package cn.Esther.controller;

import cn.Esther.pojo.Content;
import cn.Esther.service.ContentService;
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
public class ContentController {
    @Autowired
    private ContentService contentService;

    Logger logger = Logger.getLogger(String.valueOf(ContentController.class));

//    新增小室介绍
    @ResponseBody
    @RequestMapping(value = "/a/u/content", method =  RequestMethod.POST)
    public Map<String, Object> addContent(Content content){
        logger.info("Add content param is: "+ content);
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Long time = System.currentTimeMillis();
            content.setCreateAt(time);
            content.setUpdateAt(time);
            content.setCreateBy(time);
            content.setUpdateBy(time);
            contentService.insert(content);

            resultMap.put("code", 0);
            resultMap.put("message", "success");
            resultMap.put("data", content.getId());
        }catch (Exception e){
            resultMap.put("code", -1);
            resultMap.put("message", "error");
            e.printStackTrace();
            logger.info("Add content error param is: " + content);
        }
        return resultMap;
    }
    //    编辑小室介绍
    @ResponseBody
    @RequestMapping(value = "/a/u/content/{id}", method =  RequestMethod.PUT)
    // url传参 @PathVariable Long id
    public Map<String, Object> updateContent(Content content, @PathVariable Long id){
        logger.info("update content param is: "+ content + "id  is" + id);
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Long time = System.currentTimeMillis();
           //  1. 首先查询以前的数据，判断有没有
            Content updateContent = contentService.selectByPrimaryKey(id);
            logger.info("update Content is :" + updateContent);
            if (null == updateContent){
                resultMap.put("code", -100);
                resultMap.put("message", "修改内容不存在！");
            }
            // 2.更新内容
            updateContent.setImg(content.getImg()); //ctr+b jump back
            updateContent.setType(content.getType());
            updateContent.setUrl(content.getUrl());
            updateContent.setContent(content.getContent());
            updateContent.setUpdateAt(time);
            updateContent.setUpdateBy(time);
//            contentService.insert(content);

            resultMap.put("code", 0);
            resultMap.put("message", "success");
            resultMap.put("data", content.getId());
        }catch (Exception e){
            resultMap.put("code", -1);
            resultMap.put("message", "error");
            e.printStackTrace();
            logger.info("Update content error param is: " + content + "id  is" + id);
        }
        return resultMap;
    }

    //    查询小室介绍-id
    @ResponseBody
    @RequestMapping(value = "/a/u/content/{id}", method =  RequestMethod.GET)
    // url传参 @PathVariable Long id
    public Map<String, Object > getContent(@PathVariable Long id){
        logger.info("get content param is: "+ "id  is" + id);
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Long time = System.currentTimeMillis();
            //  1. 首先查询以前的数据，判断有没有
            Content content = contentService.selectByPrimaryKey(id);
            logger.info("Get Content is :" + content);
            if (null == content){
                resultMap.put("code", -100);
                resultMap.put("message", "获取内容不存在！");
            }

            resultMap.put("code", 0);
            resultMap.put("message", "success");
            resultMap.put("data", content);

        }catch (Exception e){
            resultMap.put("code", -1);
            resultMap.put("message", "error");
            e.printStackTrace();
            logger.info("Get content error param is: " + "id  is" + id);
        }
        return resultMap;
    }

    //    查询小室介绍-list
    @ResponseBody
    @RequestMapping(value = "/a/u/content/list", method =  RequestMethod.GET)
    // url传参 @PathVariable Long id
    public Map<String, Object > getContentList(){
        logger.info("get content list");
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Long time = System.currentTimeMillis();
            //  1. 首先查询以前的数据，判断有没有
            List<Content> contentList = contentService.selectByList();
            logger.info("Get Content List :" + contentList);
            if (null == contentList){
                resultMap.put("code", -100);
                resultMap.put("message", "获取内容不存在！");
            }

            resultMap.put("code", 0);
            resultMap.put("message", "success");
            resultMap.put("data", contentList);

        }catch (Exception e){
            resultMap.put("code", -1);
            resultMap.put("message", "error");
            e.printStackTrace();
            logger.info("Get content List error ");
        }
        return resultMap;
    }

}
