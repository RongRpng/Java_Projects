package cn.Esther.controller;

import cn.Esther.pojo.Content;
import cn.Esther.pojo.Works;
import cn.Esther.service.ContentService;
import cn.Esther.service.WorksService;
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
public class WorksController {
    @Autowired
    private WorksService worksService;

    Logger logger = Logger.getLogger(String.valueOf(WorksController.class));

    //    新增作品
    @ResponseBody
    @RequestMapping(value = "/a/u/work", method =  RequestMethod.POST)
    public Map<String, Object> addWork(Works work){

        logger.info("Add work param is: "+ work);
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Long time = System.currentTimeMillis();
            // only need to add create time and update time
            work.setCreateAt(time);
            work.setCreateBy(time);
            work.setUpdateAt(time);
            work.setUpdateBy(time);
            worksService.insert(work);

            resultMap.put("code", 0);
            resultMap.put("message", "success");
            resultMap.put("data", work.getId());
        }catch (Exception e){
            resultMap.put("code", -1);
            resultMap.put("message", "error");
            e.printStackTrace();
            logger.info("Add work error param is: " + work);
        }
        return resultMap;
    }
    //    编辑作品
    @ResponseBody
    @RequestMapping(value = "/a/u/work/{id}", method =  RequestMethod.PUT)
    // url传参 @PathVariable Long id
    public Map<String, Object> updateWork(Works work, @PathVariable Long id){
        logger.info("update work param is: "+ work + "id  is" + id);
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Long time = System.currentTimeMillis();
            //  1. 首先查询以前的数据，判断有没有
            Works updateWork = worksService.selectByPrimaryKey(id);

            logger.info("update work is :" + updateWork);
            if (null == updateWork){
                resultMap.put("code", -100);
                resultMap.put("message", "修改内容不存在！");
            }
            // 2.更新内容
//            updateWork.setId(0L);
            updateWork.setName("Masterpiece 1");
            updateWork.setIntro(work.getIntro());
            updateWork.setCover(work.getCover());
            updateWork.setVedio(work.getVedio());
            updateWork.setImg(work.getImg());
            updateWork.setContent(work.getContent());
            updateWork.setUpdateAt(time);
            updateWork.setUpdateBy(time);
//            worksService.insert(work);


            resultMap.put("code", 0);
            resultMap.put("message", "success");
            resultMap.put("data", work.getId());
        }catch (Exception e){
            resultMap.put("code", -1);
            resultMap.put("message", "error");
            e.printStackTrace();
            logger.info("Update work error param is: " + work + "id  is" + id);
        }
        return resultMap;
    }

    //    查询作品-id
    @ResponseBody
    @RequestMapping(value = "/a/u/work/{id}", method =  RequestMethod.GET)
    // url传参 @PathVariable Long id
    public Map<String, Object > getWork(@PathVariable Long id){
        logger.info("get work param is: "+ "id  is" + id);
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Long time = System.currentTimeMillis();
            //  1. 首先查询以前的数据，判断有没有
            Works work =worksService.selectByPrimaryKey(id);
            logger.info("Get Content is :" + work);
            if (null == work){
                resultMap.put("code", -100);
                resultMap.put("message", "获取内容不存在！");
            }

            resultMap.put("code", 0);
            resultMap.put("message", "success");
            resultMap.put("data", work);

        }catch (Exception e){
            resultMap.put("code", -1);
            resultMap.put("message", "error");
            e.printStackTrace();
            logger.info("Get work error param is: " + "id  is" + id);
        }
        return resultMap;
    }

    //    查询作品列表-list
    @ResponseBody
    @RequestMapping(value = "/a/u/work/list", method =  RequestMethod.GET)
    // url传参 @PathVariable Long id
    public Map<String, Object > getWorkList(){
        logger.info("get content list");
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Long time = System.currentTimeMillis();
            //  1. 首先查询以前的数据，判断有没有
            List<Works> workList = worksService.selectByList();
            logger.info("Get Work List :" + workList);
            if (null == workList){
                resultMap.put("code", -100);
                resultMap.put("message", "获取内容不存在！");
            }

            resultMap.put("code", 0);
            resultMap.put("message", "success");
            resultMap.put("data", workList);

        }catch (Exception e){
            resultMap.put("code", -1);
            resultMap.put("message", "error");
            e.printStackTrace();
            logger.info("Get work list error ");
        }
        return resultMap;
    }
}
