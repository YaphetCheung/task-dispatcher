package priv.yanfei.task.dispatcher.common;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import priv.yanfei.task.all.entity.HostInfo;
import priv.yanfei.task.all.mapper.CommonTaskMapper;
import priv.yanfei.task.common.util.SystemDateUtil;
import priv.yanfei.task.component.HostComponent;
import priv.yanfei.task.dispatcher.AbstractTaskDispatcherStarter;
import priv.yanfei.task.dispatcher.AbstractTaskExecutor;
import priv.yanfei.task.dispatcher.AbstractTaskLoader;


@Component
public class CommonTaskDispatcherStarter extends AbstractTaskDispatcherStarter<CommonTask> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonTaskDispatcherStarter.class);

    @Autowired
    private CommonTaskExecutor commonTaskExecutor;

    @Autowired
    private CommonTaskLoader commonTaskLoader;

    @Autowired
    private CommonTaskMapper commonTaskMapper;

    @Autowired
    private HostComponent hostComponent;

    @Value("${dispatcher.common.corePoolSize}")
    private int corePoolSize;

    @Value("${dispatcher.common.hungrySize}")
    private int hungrySize;

    @Value("${dispatcher.common.keepAliveTime}")
    private long keepAliveTime;

    @Value("${dispatcher.common.maximumPoolSize}")
    private int maximumPoolSize;

    @Value("${dispatcher.common.noTaskSleepMills}")
    private long noTaskSleepMills;

    @Value("${dispatcher.common.queueFullSleepMills}")
    private long queueFullSleepMills;

    @Value("${dispatcher.common.queueSize}")
    private int queueSize;


    @Override
    protected int getQueueSize() {
        return queueSize;
    }

    @Override
    public long getQueueFullSleepMills() {
        return queueFullSleepMills;
    }

    @Override
    protected long getNoTaskSleepMills() {
        return noTaskSleepMills;
    }

    @Override
    protected int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    @Override
    protected long getKeepAliveTime() {
        return keepAliveTime;
    }


    @Override
    protected int getHungrySize() {
        return hungrySize;
    }

    @Override
    protected int getCorePoolSize() {
        return corePoolSize;
    }

    @Override
    protected void recover() {

        HostInfo hostInfo = hostComponent.getHostInfo();

        Integer update = commonTaskMapper.updateHangTasksByIp(hostInfo.getHostIp(), SystemDateUtil.getSystemDate());

        LOGGER.info(StrUtil.format("[{}]Recover success, {} hang tasks are recovered...", getDispatcherName(), update));
    }

    @Override
    protected String getDispatcherName() {
        return "CommonTaskDispatcher";
    }

    @Override
    protected AbstractTaskLoader getTaskLoader() {
        return commonTaskLoader;
    }

    @Override
    protected AbstractTaskExecutor getTaskExecutor() {
        return commonTaskExecutor;
    }
}
