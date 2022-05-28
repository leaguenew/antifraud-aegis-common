package com.antifraud.aegis.common.object;

import lombok.Data;

import java.io.Serializable;


/**
 * @author : niu liguo
 * @date : 2022/5/28 22:17
 */
@Data
public class StrategyFeatureConfig implements Serializable {

    private int index;

    private String operator;

    private String leftValue;

    private String rightValue;


}
