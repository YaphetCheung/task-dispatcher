package priv.yanfei.task.common.enums;

import java.util.HashMap;
import java.util.Map;

public enum CommonTaskTypeEnum implements IEnum {
    DOWN_LOAD_PIC("down_load_pic", "下载图片");

    private static final Map<String, CommonTaskTypeEnum> codeMap = new HashMap<String, CommonTaskTypeEnum>();

    static {
        for (CommonTaskTypeEnum item : CommonTaskTypeEnum.values()) {
            codeMap.put(item.getCode(), item);
        }
    }

    private String code;
    private String desc;

    private CommonTaskTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CommonTaskTypeEnum getByCode(String code) {
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
