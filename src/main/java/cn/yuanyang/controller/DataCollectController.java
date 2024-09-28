package cn.yuanyang.controller;

import cn.yuanyang.entity.CollectInfo;
import cn.yuanyang.service.DataCollectService;
import cn.yuanyang.service.Impl.DataCollectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/badmood")
public class DataCollectController {

    @Autowired
    private DataCollectService dataCollectService;

    @RequestMapping("addDataInfo")
    public void addDataCollectInfo(@RequestBody CollectInfo collectInfo){
        dataCollectService.addDataCollectInfo(collectInfo);
    }

    @RequestMapping("/export")
    public void exportCollectInfo(HttpServletResponse response) {
        dataCollectService.exportCollectInfo(response);
    }

}
