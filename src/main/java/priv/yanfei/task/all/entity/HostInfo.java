package priv.yanfei.task.all.entity;

import java.time.LocalDateTime;
import priv.yanfei.task.common.model.BaseModel;

/**
 * <p>
 * 主机信息表
 * </p>
 *
 * @author zyf
 * @since 2019-12-16
 */
public class HostInfo extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 主机ip
     */
    private String hostIp;

    /**
     * 主机名称
     */
    private String hostName;

    /**
     * 上次启动时间
     */
    private LocalDateTime lastBoot;

    /**
     * 上次心跳时间
     */
    private LocalDateTime lastHeart;

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }
    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
    public LocalDateTime getLastBoot() {
        return lastBoot;
    }

    public void setLastBoot(LocalDateTime lastBoot) {
        this.lastBoot = lastBoot;
    }
    public LocalDateTime getLastHeart() {
        return lastHeart;
    }

    public void setLastHeart(LocalDateTime lastHeart) {
        this.lastHeart = lastHeart;
    }

    @Override
    public String toString() {
        return "HostInfo{" +
            "hostIp=" + hostIp +
            ", hostName=" + hostName +
            ", lastBoot=" + lastBoot +
            ", lastHeart=" + lastHeart +
        "}";
    }
}
