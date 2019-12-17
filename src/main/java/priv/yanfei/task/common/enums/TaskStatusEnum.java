/**
 * abssqr.com Inc.
 * Copyright (c) 2017-2017 All Rights Reserved.
 */
package priv.yanfei.task.common.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 任务状态枚举类
 *
 * @author zhenxuan.luo
 * @version $Id: AssetStatusEnum.java, v 0.1 2017-06-07 下午18:38 zhenxuan.luo Exp $ 
 */
public enum TaskStatusEnum implements IEnum {
    INIT("init", "初始"),

    EXECUTING("exec", "执行中"),

    SUCCESS("succ", "成功"),

    FAILURE("fail", "失败");

    private static final Map<String, TaskStatusEnum> codeMap = new HashMap<String, TaskStatusEnum>();

    static {
        for (TaskStatusEnum item : TaskStatusEnum.values()) {
            codeMap.put(item.getCode(), item);
        }
    }

    private String code;
    private String desc;

    private TaskStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static TaskStatusEnum getByCode(String code) {
        return codeMap.get(code);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}