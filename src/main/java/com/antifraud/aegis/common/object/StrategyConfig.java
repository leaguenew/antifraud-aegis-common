package com.antifraud.aegis.common.object;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author : niu liguo
 * @date : 2022/5/28 22:57
 */
@Data
public class StrategyConfig extends ConfigItem implements Serializable {

    private Long StrategyId;

    //todo: name -> strategyName
    private String name;

    private String description;

    private String bizType;

    private String cmd;

    /**
     * 配置状态：关闭、观察、开启
     */
    private String configStatus;

    /**
     * 策略阈值
     */
    private Integer strategyThreshold;

    /**
     * 特征配置
     */
    private List<StrategyFeatureConfig> features;

    /**
     * 特征执行方式
     */
    private String featurePlan;

    /**
     * 后续动作
     */
//    private List<StrategyPunishConfig> actionConfigs;
}
