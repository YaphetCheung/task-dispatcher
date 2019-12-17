package priv.yanfei.task.dispatcher;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;


public abstract class AbstractTaskDispatcherStarter<T extends AbstractTask> implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTaskDispatcherStarter.class);

    @Autowired
    private BeanFactory beanFactory;

    @Override
    public void afterPropertiesSet() {

        LOGGER.info(StrUtil.format("[{}]Task dispatcher initializer begin...", getDispatcherName()));

        //1. 先做启动前恢复
        doRecover();

        //2. 启动任务调度器
        doStart();
        LOGGER.info(StrUtil.format("[{}]Task dispatcher initializer end...", getDispatcherName()));


    }

    /**
     * 启动调度器
     */
    private void doStart() {
        TaskDispatcher<T> taskDispatcher = new TaskDispatcher(getDispatcherName(), getTaskExecutor(), getTaskLoader());

        taskDispatcher.setCorePoolSize(getCorePoolSize());
        taskDispatcher.setDispatcherName(getDispatcherName());
        taskDispatcher.setHungrySize(getHungrySize());
        taskDispatcher.setKeepAliveTime(getKeepAliveTime());
        taskDispatcher.setMaximumPoolSize(getMaximumPoolSize());
        taskDispatcher.setNoTaskSleepMills(getNoTaskSleepMills());
        taskDispatcher.setQueueFullSleepMills(getQueueFullSleepMills());
        taskDispatcher.setQueueSize(getQueueSize());

        registerBean(getDispatcherName(), taskDispatcher);
        new Thread(taskDispatcher, getDispatcherName()).start();
    }

    private void registerBean(String dispatcherName, TaskDispatcher<T> taskDispatcher) {
        ConfigurableBeanFactory configurableBeanFactory = (ConfigurableBeanFactory) beanFactory;
        configurableBeanFactory.registerSingleton(dispatcherName, dispatcherName);
    }

    protected abstract int getQueueSize();

    protected abstract long getQueueFullSleepMills();

    protected abstract long getNoTaskSleepMills();

    protected abstract int getMaximumPoolSize();

    protected abstract long getKeepAliveTime();

    protected abstract int getHungrySize();

    protected abstract int getCorePoolSize();

    /**
     * 启动前恢复挂起的任务
     */
    private void doRecover() {

        LOGGER.info(StrUtil.format("[{}]Task recover hang tasks...", getDispatcherName()));
        try {
            recover();
            LOGGER.info(StrUtil.format("[{}]Task recover success...", getDispatcherName()));
        } catch (Throwable e) {
            LOGGER.error(StrUtil.format("[{}]Task recover failed, just skip now, waiting for hand...", getDispatcherName()));
        }

    }

    protected abstract void recover();


    /**
     * 获取调度器名称
     * @return
     */
    protected abstract String getDispatcherName();


    /**
     * 获取任务加载器
     * @return
     */
    protected abstract AbstractTaskLoader getTaskLoader();


    /**
     * 获取任务执行器
     * @return
     */
    protected abstract AbstractTaskExecutor getTaskExecutor();


}
