package cn.yuanyang.util;

import lombok.extern.slf4j.Slf4j;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Slf4j
public class DateUtil {

    /** 锁对象 */
//    private static final Object lockObj = new Object();

    /** 存放不同的日期模板格式的sdf的Map */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();
    //private static ThreadLocal<SimpleDateFormat> local = new ThreadLocal<SimpleDateFormat>();

    public static final String YEAR_MON_DAY_FORMAT1 = "yyyy-MM-dd";

    public static final String YEAR_MON_DAY_FORMAT2 = "yyyyMMdd";
    /**
     * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
     */
    private static SimpleDateFormat getSdf(final String pattern) {

        ThreadLocal<SimpleDateFormat> sdf = sdfMap.get(pattern);

        // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
        if (sdf == null) {
            sdf = sdfMap.get(pattern);
            if (sdf == null) {
                // 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
                // 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new SimpleDateFormat
                sdf = new ThreadLocal<SimpleDateFormat>() {
                    @Override
                    protected SimpleDateFormat initialValue() {
                        return new SimpleDateFormat(pattern);
                    }
                };
                sdfMap.put(pattern, sdf);
            }
            /*synchronized (lockObj) {

            }*/
        }
        return sdf.get();
    }

    public static String formatDate(Date datestr, String pattern) {
        if (datestr != null) {
            try {
                return getSdf(pattern).format(datestr);
            } catch (Exception e) {
                log.error("日期格式解析错误",e);
                return null;
            }
        } else {
            return null;
        }
    }

    public static String formatLongDate(Long time, String pattern) {
        if (time != null) {
            try {
                return getSdf(pattern).format(time);
            } catch (Exception e) {
                log.error("日期格式解析错误",e);
                return null;
            }
        } else {
            return null;
        }
    }

    public static Date parseDate(String datestr,String pattern) {
        if (datestr != null) {
            try {
                return getSdf(pattern).parse(datestr);
            } catch (Exception e) {
                log.error("日期格式解析错误",e);
                return null;
            }
        } else {
            return null;
        }
    }

    public static void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            log.error("sleep error:{}",e);
        }
    }
}
