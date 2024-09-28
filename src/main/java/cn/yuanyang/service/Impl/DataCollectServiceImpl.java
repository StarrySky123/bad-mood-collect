package cn.yuanyang.service.Impl;

import cn.yuanyang.constant.CommonConstant;
import cn.yuanyang.entity.CollectInfo;
import cn.yuanyang.mapper.CollectInfoMapper;
import cn.yuanyang.service.DataCollectService;
import cn.yuanyang.util.DateUtil;
import cn.yuanyang.util.ExcelUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class DataCollectServiceImpl implements DataCollectService {

    @Autowired
    private CollectInfoMapper collectInfoMapper;

    @Override
    public void addDataCollectInfo(CollectInfo collectInfo) {
        collectInfo.setRecordDate(new Date());
        try {
            collectInfoMapper.insert(collectInfo);
        }catch (Exception e){
            log.error("插入坏情绪收集信息失败");
            e.printStackTrace();
        }
    }

    @Override
    public void exportCollectInfo(HttpServletResponse response) {
        QueryWrapper<CollectInfo> collectInfoWrapper = new QueryWrapper<>();
        collectInfoWrapper.lambda().eq(CollectInfo::getRecordDate, DateUtil.formatDate(new Date(),DateUtil.YEAR_MON_DAY_FORMAT1));
        List<CollectInfo> collectInfos = collectInfoMapper.selectList(collectInfoWrapper);
//        log.info("collectInfos={}", Arrays.toString(collectInfos.toArray()));
        try {
            String fileName = "信息表_" + DateUtil.formatDate(new Date(),DateUtil.YEAR_MON_DAY_FORMAT2);
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName + ".xls", "utf-8"));
            HSSFWorkbook wb = ExcelUtil.exportMoodCollectInfo(collectInfos, CommonConstant.moodInfoCellNames);
            ServletOutputStream out = response.getOutputStream();
            wb.write(out);
            out.close();
        }catch (Exception e){
            log.error("导出坏情绪搜集EXCEL表格异常");
            e.printStackTrace();
        }
    }
}
