package cn.yuanyang.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("bad_mood_collect_info")
public class CollectInfo {

    private String phone;

    @TableField("msg_ref")
    private String msgRef;

    @TableField("msg_info")
    private String msgInfo;

    @TableField("record_date")
    private Date recordDate;

}