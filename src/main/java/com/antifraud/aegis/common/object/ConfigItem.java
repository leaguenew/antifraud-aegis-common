package com.antifraud.aegis.common.object;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : niu liguo
 * @date : 2022/5/28 22:59
 * todo: rename to ConfigBase
 * @desc: 配置积累
 */
@Data
public class ConfigItem implements Serializable {

    private String id;

    private Date createTime;

    private Date updateTime;
}
