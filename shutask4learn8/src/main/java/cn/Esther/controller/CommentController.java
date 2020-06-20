package cn.Esther.controller;

import cn.Esther.pojo.Comment;
import cn.Esther.pojo.Works;
import cn.Esther.service.CommentService;
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
public class CommentController {
    @Autowired
    private CommentService commentService;

    Logger logger = Logger.getLogger(String.valueOf(CommentController.class));

    //    新增评论
    @ResponseBody
    @RequestMapping(value = "/a/u/comment", method =  RequestMethod.POST)
    public Map<String, Object> addComment(Comment comment){

        logger.info("Add comment param is: "+ comment);
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Long time = System.currentTimeMillis();
            // only need to add create time and update time
            comment.setCreateAt(time);
            comment.setCreateBy(time);
            comment.setUpdateAt(time);
            comment.setUpdateBy(time);
            commentService.insert(comment);

            resultMap.put("code", 0);
            resultMap.put("message", "success");
            resultMap.put("data", comment.getId());
        }catch (Exception e){
            resultMap.put("code", -1);
            resultMap.put("message", "error");
            e.printStackTrace();
            logger.info("Add comment error param is: " + comment);
        }
        return resultMap;
    }


    //    查询评论列表-list
    @ResponseBody
    @RequestMapping(value = "/a/u/comment/list", method =  RequestMethod.GET)
    // url传参 @PathVariable Long id
    public Map<String, Object > getCommentList(){
        logger.info("get comment list");
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Long time = System.currentTimeMillis();
            //  1. 首先查询以前的数据，判断有没有
            List<Comment> commentList = commentService.selectByList();
            logger.info("Get Comment List :" + commentList);
            if (null == commentList){
                resultMap.put("code", -100);
                resultMap.put("message", "获取内容不存在！");
            }

            resultMap.put("code", 0);
            resultMap.put("message", "success");
            resultMap.put("data", commentList);

        }catch (Exception e){
            resultMap.put("code", -1);
            resultMap.put("message", "error");
            e.printStackTrace();
            logger.info("Get comment list error ");
        }
        return resultMap;
    }
}
