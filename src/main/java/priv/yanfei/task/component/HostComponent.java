package priv.yanfei.task.component;


import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import priv.yanfei.task.all.entity.HostInfo;
import priv.yanfei.task.all.mapper.HostInfoMapper;
import priv.yanfei.task.common.util.SystemDateUtil;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class HostComponent implements InitializingBean {

    @Autowired
    private HostInfoMapper hostInfoMapper;

    private HostInfo hostInfo;

    public void sendHeartBeat() {
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        hostInfo.setLastHeart(SystemDateUtil.getSystemDate());
        hostInfo.setHostName(Optional.ofNullable(addr).map(InetAddress::getHostName).orElse(null));
        hostInfoMapper.updateHeart(hostInfo);
    }

    public HostInfo getHostInfo() {
        return this.hostInfo;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        InetAddress addr = InetAddress.getLocalHost();

        HostInfo hostInfo = hostInfoMapper.selectByHostIp(addr.getHostAddress());

        if (ObjectUtil.isNull(hostInfo)) {
            hostInfo = new HostInfo();
            hostInfo.setHostIp(addr.getHostAddress());
            hostInfo.setHostName(addr.getHostName());
            hostInfo.setLastBoot(SystemDateUtil.getSystemDate());
            hostInfo.setLastHeart(SystemDateUtil.getSystemDate());
            hostInfoMapper.insert(hostInfo);
        } else {
            hostInfo.setLastBoot(SystemDateUtil.getSystemDate());
            hostInfo.setLastHeart(SystemDateUtil.getSystemDate());
            hostInfo.setHostName(addr.getHostName());
            hostInfoMapper.updateByHostIp(hostInfo);
        }

        this.hostInfo = hostInfo;

    }
}
