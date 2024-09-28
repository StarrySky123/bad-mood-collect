package cn.yuanyang.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommonUtils {
    private static final String NULL = "null";

    public CommonUtils() {
    }

    public static boolean isBlank(Object obj) {
        if (null != obj && !"null".equals(obj) && !"".equals(obj)) {
            if (obj instanceof String) {
                if ("".equals(obj.toString().trim())) {
                    return true;
                }
            } else if (obj instanceof List) {
                if (((List) obj).size() == 0) {
                    return true;
                }
            } else if (obj instanceof Map) {
                if (((Map) obj).size() == 0) {
                    return true;
                }
            } else if (obj instanceof Set) {
                if (((Set) obj).size() == 0) {
                    return true;
                }
            } else if (obj instanceof Object[]) {
                if (((Object[]) ((Object[]) obj)).length == 0) {
                    return true;
                }
            } else if (obj instanceof int[]) {
                if (((int[]) ((int[]) obj)).length == 0) {
                    return true;
                }
            } else if (obj instanceof long[] && ((long[]) ((long[]) obj)).length == 0) {
                return true;
            }

            return false;
        } else {
            return true;
        }
    }

    public static boolean isNotBlank(Object obj) {
        return !isBlank(obj);
    }

}
