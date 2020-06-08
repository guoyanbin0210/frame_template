package com.lt.base.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Description:字典
 * Create Time: 2019-05-07 02:40
 */
@Component
@Setter
@Getter
public class BaseDirModel extends BaseModel {
    /**
     * 名称
     */
    private String bd_name;
    /**
     * 说明
     */
    private String bd_des;
    /**
     * 数据
     */
    private String bd_data;
    /**
     * 其他
     */
    private String bd_other;
    /**
     * 类目 见 DirEnum
     */
    private String bd_type;


    private String p_id;
}
