package com.antifraud.aegis.common.object;

import lombok.Data;
import org.apache.commons.lang3.tuple.MutablePair;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : niu liguo
 * @date : 2022/5/28 22:34
 */
@Data
public class MutableDictData implements Serializable {

    /**
     * business line
     * todo: bizType -> bizLine
     */
    private final String bizType;

    /**
     * event name
     */
    private final String cmd;

    private final Map<String,  DictDataItem> dataItemMap = new ConcurrentHashMap<>();

    public MutableDictData(String bizType, String cmd) {
        this.bizType = bizType;
        this.cmd = cmd;
    }

    public MutableDictData(String bizType, String cmd, Map<String, Object> data) {
        this.bizType = bizType;
        this.cmd = cmd;

        if (data != null && !data.isEmpty()) {
            for (Map.Entry<String, Object> entry : data.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (value == null) {
                    continue;
                }
                if (value instanceof Long) {
                    this.putData(key, (Long) value);
                } else if (value instanceof Integer) {
                    this.putData(key, ((Integer) value).longValue());
                } else if (value instanceof BigDecimal) {
                    this.putData(key, (BigDecimal) value);
                } else if (value instanceof Float) {
                    this.putData(key, new BigDecimal((Float) value));
                } else if (value instanceof Double) {
                    this.putData(key, new BigDecimal((Double) value));
                } else if (value instanceof Boolean) {
                    this.putData(key, (Boolean) value);
                } else if (value instanceof String) {
                    this.putData(key, (String) value);
                } else if (value instanceof List) {
                    this.putData(key, (List) value);
                } else if (value instanceof Date) {
                    this.putData(key, (Date) value);
                } else {
                    throw new IllegalArgumentException(
                            String.format("build MutableDictData error||bizType=%s||eventName=%s||value=%s||type=%s",
                                    bizType, cmd, value, value.getClass()));
                }
            }
        }
    }

    public boolean putData(String key, Boolean value) {
        if (value == null) {
            return false;
        }
        dataItemMap.put(key, new DictBoolean(key, value));
        return true;
    }

    public boolean putData(String key, Long value) {
        if (value == null) {
            return false;
        }
        dataItemMap.put(key, new DictLong(key, value));
        return true;
    }

    public boolean putData(String key, BigDecimal value) {
        if (value == null) {
            return false;
        }
        dataItemMap.put(key, new DictDecimal(key, value));
        return true;
    }

    public boolean putData(String key, String value) {
        if (value == null) {
            return false;
        }
        dataItemMap.put(key, new DictString(key, value));
        return true;
    }

    public boolean putData(String key, Date value) {
        if (value == null) {
            return false;
        }
        dataItemMap.put(key, new DictDate(key, value));
        return true;
    }

    public boolean putData(String key, List<String> values) {
        if (values == null) {
            return false;
        }
        dataItemMap.put(key, new DictList(key, values));
        return true;
    }

    public Boolean getBoolean(String key) {
        DictDataItem item = dataItemMap.get(key);
        if (item != null && item instanceof DictBoolean) {
            return ((DictBoolean) item).getRight();
        }
        return null;
    }

    public Long getLong(String key) {
        DictDataItem item = dataItemMap.get(key);
        if (item != null && item instanceof DictLong) {
            return ((DictLong) item).getRight();
        }
        return null;
    }

    public BigDecimal getDecimal(String key) {
        DictDataItem item = dataItemMap.get(key);
        if (item != null && item instanceof DictDecimal) {
            return ((DictDecimal) item).getRight();
        }
        return null;
    }

    public String getString(String key) {
        DictDataItem item = dataItemMap.get(key);
        if (item != null && item instanceof DictString) {
            return ((DictString) item).getRight();
        }
        return null;
    }

    public Date getDate(String key) {
        DictDataItem item = dataItemMap.get(key);
        if (item != null && item instanceof DictDate) {
            return ((DictDate) item).getRight();
        }
        return null;
    }


    public List<String> getList(String key) {

        DictDataItem item = dataItemMap.get(key);
        if (item != null && item instanceof DictList) {
            return ((DictList) item).getRight();
        }
        return null;
    }

    public Object get(String key) {
        DictDataItem item = dataItemMap.get(key);
        if (item != null) {
            return item.getValue();
        }
        return null;
    }

    public static class DictBoolean extends MutablePair<String, Boolean> implements DictDataItem {
        private static final long serialVersionUID = -3166938731786534682L;

        public DictBoolean() {
        }

        public DictBoolean(String left, Boolean right) {
            super(left, right);
        }

        @Override
        public String toString() {
            return String.valueOf(super.getRight());
        }
    }

    /**
     * 小数类型的数据字典项
     */
    public static class DictDecimal extends MutablePair<String, BigDecimal> implements DictDataItem {
        private static final long serialVersionUID = 5247362004743960684L;

        public DictDecimal() {
        }

        public DictDecimal(String left, BigDecimal right) {
            super(left, right);
        }

        @Override
        public String toString() {
            return String.valueOf(super.getRight());
        }
    }

    /**
     * 整数类型的数据字典项
     */
    public static class DictLong extends MutablePair<String, Long> implements DictDataItem {
        private static final long serialVersionUID = 5243998253691229673L;

        public DictLong() {
        }

        public DictLong(String left, Long right) {
            super(left, right);
        }

        @Override
        public String toString() {
            return String.valueOf(super.getRight());
        }
    }

    /**
     * 字符串类型的数据字典项
     */
    public static class DictString extends MutablePair<String, String> implements DictDataItem {
        private static final long serialVersionUID = -986632178514862640L;

        public DictString() {
        }

        public DictString(String left, String right) {
            super(left, right);
        }

        @Override
        public String toString() {
            return String.valueOf(super.getRight());
        }
    }

    /**
     * 字符串类型的数据字典项
     */
    public static class DictDate extends MutablePair<String, Date> implements DictDataItem {
        private static final long serialVersionUID = -986632178514862640L;

        public DictDate() {
        }

        public DictDate(String left, Date right) {
            super(left, right);
        }

        @Override
        public String toString() {
            return String.valueOf(super.getRight().getTime());
        }
    }

    /**
     * 字符串列表的数据字典项
     */
    public static class DictList extends MutablePair<String, List<String>> implements DictDataItem {
        private static final long serialVersionUID = 2807460752972296464L;

        public DictList() {
        }

        public DictList(String left, List<String> right) {
            super(left, right);
        }

        @Override
        public String toString() {
            return String.valueOf(super.getRight());
        }
    }
}
