package priv.yanfei.task.common.model;

import cn.hutool.core.builder.EqualsBuilder;
import cn.hutool.core.builder.HashCodeBuilder;
import cn.hutool.json.JSONUtil;

import java.io.Serializable;

public abstract class BaseModel implements Cloneable, Serializable {

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, true);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(obj, this, true);
    }

    @Override
    public String toString() {
        return JSONUtil.toJsonStr(this);
    }
}
