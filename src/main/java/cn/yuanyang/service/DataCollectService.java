package cn.yuanyang.service;

import cn.yuanyang.entity.CollectInfo;

import javax.servlet.http.HttpServletResponse;

public interface DataCollectService {

    void addDataCollectInfo(CollectInfo collectInfo);

    void exportCollectInfo(HttpServletResponse response);
}
