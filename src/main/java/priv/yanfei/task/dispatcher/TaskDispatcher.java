package priv.yanfei.task.dispatcher;

import cn.hutool.core.collection.CollUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.*;

/**
 * 任务调度器
 */
@SuppressWarnings("all")
public class TaskDispatcher<T extends AbstractTask> implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskDispatcher.class);


    /**
     * 运行标志
     */
    private boolean runningFlag = true;

    /**
     * 调度器名称
     */
    private String dispatcherName;

    /**
     * 任务执行器
     */
    private AbstractTaskExecutor<T> taskExecutor;

    /**
     * 任务加载器
     */
    private AbstractTaskLoader<T> taskLoader;

    public TaskDispatcher(String dispatcherName, AbstractTaskExecutor taskExecutor, AbstractTaskLoader taskLoader) {
        this.dispatcherName = dispatcherName;
        this.taskExecutor = taskExecutor;
        this.taskLoader = taskLoader;
    }


    /**
     * 核心线程数
     */
    private int corePoolSize = Runtime.getRuntime().availableProcessors() * 2;

    /**
     * 最大线程数
     */
    private int maximumPoolSize = Runtime.getRuntime().availableProcessors() * 4;

    /**
     * 空闲线程回收时间(只回收corePoolSize以外的线程)
     */
    private long keepAliveTime = 60 * 5;

    /**
     * 阻塞队列的大小
     */
    private int queueSize = 1000;

    /**
     * 无任务睡眠时间
     */
    private long noTaskSleepMills = 100;

    /**
     * 阻塞队列满睡眠时间
     */
    private long queueFullSleepMills = 1000;

    /**
     * 饥饿度控制
     */
    private int hungrySize = 10;


    /**
     * 线程池
     */
    private ThreadPoolExecutor threadPoolExecutor;

    /**
     * 空闲线程回收时间单位
     */
    private TimeUnit unit = TimeUnit.SECONDS;

    /**
     * 任务队列
     */
    private BlockingQueue<Runnable> workQueue;

    /**
     * 线程工厂(新线程的产生方式)
     */
    private ThreadFactory threadFactory = Executors.defaultThreadFactory();

    /**
     * 拒绝策略
     */
    private RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();

    @Override
    public void run() {

        //1. 初始化队列
        workQueue = new ArrayBlockingQueue<>(queueSize);

        //2. 初始化线程池
        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);

        while (runningFlag) {
            try {
                if (workQueue.size() < hungrySize) {

                    List<T> todoTaskList = taskLoader.loadTodoTasks(queueSize - workQueue.size());

                    if (CollUtil.isEmpty(todoTaskList)) {
                        Thread.sleep(noTaskSleepMills);
                        continue;
                    }

                    todoTaskList.forEach(one -> {
                        threadPoolExecutor.execute(() -> {
                            taskExecutor.execute(one);
                        });
                    });

                    if (workQueue.size() < queueSize * 0.6) {
                        Thread.sleep(noTaskSleepMills);
                    }
                } else {
                    Thread.sleep(queueFullSleepMills);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public void setQueueSize(int queueSize) {
        this.queueSize = queueSize;
    }

    public void setRunningFlag(boolean runningFlag) {
        this.runningFlag = runningFlag;
    }

    public void setDispatcherName(String dispatcherName) {
        this.dispatcherName = dispatcherName;
    }

    public void setNoTaskSleepMills(long noTaskSleepMills) {
        this.noTaskSleepMills = noTaskSleepMills;
    }

    public void setQueueFullSleepMills(long queueFullSleepMills) {
        this.queueFullSleepMills = queueFullSleepMills;
    }

    public void setHungrySize(int hungrySize) {
        this.hungrySize = hungrySize;
    }
}
