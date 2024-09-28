package cn.yuanyang.util;

import cn.yuanyang.entity.CollectInfo;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    public static HSSFSheet createSheet(HSSFWorkbook wb, String sheetName) {
        HSSFSheet sheet = wb.createSheet(sheetName);
        sheet.setAutobreaks(true);
        sheet.setDefaultColumnWidth(20);
        return sheet;
    }

    public static HSSFCellStyle createCellStyle(HSSFWorkbook wb) {
        // 字体
        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 13);
        font.setFontName("微软雅黑");
        // 单元格内容样式
        HSSFCellStyle normalStyle = wb.createCellStyle();
        normalStyle.setBorderBottom(BorderStyle.THIN); // 下边框
        normalStyle.setBorderLeft(BorderStyle.THIN);// 左边框
        normalStyle.setBorderTop(BorderStyle.THIN);// 上边框
        normalStyle.setBorderRight(BorderStyle.THIN);// 右边框
        normalStyle.setFont(font);
        return normalStyle;
    }

    //表头设置
    public static void setHeadCellNames(HSSFRow firstRow, HSSFCellStyle normalStyle, String[] headCellNames) {
        // 添加表头内容
        for (int i = 0; i < headCellNames.length; i++) {
            HSSFCell headCell = firstRow.createCell(i);
            headCell.setCellValue(headCellNames[i]);
            headCell.setCellStyle(normalStyle);
        }
    }

    //数据设置
    public static void setCellValue(HSSFRow hssfRow, HSSFCellStyle normalStyle, List<String> cellValues) {
        // 添加数据内容
        for (int i = 0; i < cellValues.size(); i++) {
            // 创建单元格，并设置值
            HSSFCell cell = hssfRow.createCell(i);
            cell.setCellValue(cellValues.get(i));
            cell.setCellStyle(normalStyle);
        }
    }

    //合并单元格
    public static void merge(HSSFWorkbook wb, HSSFSheet sheet, int starRow, int endRow, int starColumn, int endColumn) {
        CellRangeAddress cra = new CellRangeAddress(starRow, endRow, starColumn, endColumn);

        sheet.addMergedRegion(cra);

        RegionUtil.setBorderBottom(BorderStyle.THIN, cra, sheet); // 下边框

        RegionUtil.setBorderLeft(BorderStyle.THIN, cra, sheet); // 左边框

        RegionUtil.setBorderRight(BorderStyle.THIN, cra, sheet); // 右边框

        RegionUtil.setBorderTop(BorderStyle.THIN, cra, sheet); // 上边框

    }

    public static HSSFWorkbook exportMoodCollectInfo(List<CollectInfo> collectInfos, String[] headCellNames) {
        HSSFWorkbook wb = new HSSFWorkbook();
        //页签
        String sheetName = "用户信息";
        HSSFSheet sheet = ExcelUtil.createSheet(wb, sheetName);
        //格式
        HSSFCellStyle normalStyle = ExcelUtil.createCellStyle(wb);

        //创建表头
        HSSFRow firstRow = sheet.createRow(0);

        ExcelUtil.setHeadCellNames(firstRow, normalStyle, headCellNames);

        if (CommonUtils.isNotBlank(collectInfos)) {
            //行数
            int rowNum = 1;
            for (CollectInfo info : collectInfos) {
                HSSFRow hssfRow = sheet.createRow(rowNum);
                List<String> data = new ArrayList<>();
                data.add(info.getPhone());
                data.add(info.getMsgRef());
                data.add(info.getMsgInfo());
                data.add(DateUtil.formatDate(info.getRecordDate(),DateUtil.YEAR_MON_DAY_FORMAT1));
                ExcelUtil.setCellValue(hssfRow, normalStyle, data);
                rowNum++;
            }
        }
        return wb;
    }

}
