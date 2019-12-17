package priv.yanfei.task.dispatcher;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.support.TransactionTemplate;
import priv.yanfei.task.exception.BaseException;

public abstract class AbstractTaskExecutor<T extends AbstractTask> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTaskExecutor.class);

    @Autowired
    private TransactionTemplate transactionTemplate;



    /**
     * 任务执行模板
     *
     * @param one
     */
    public void execute(T one) {
        try {
            LOGGER.info(StrUtil.format("[DispatcherExecutor]start execute {}", getName()));

            if (one.needTransaction()) {
                transactionTemplate.executeWithoutResult(status -> {
                    doExecute(one);
                });
            } else {
                doExecute(one);
            }

            LOGGER.info(StrUtil.format("[DispatcherExecutor]success execute {}", getName()));

        } catch (BaseException e) {
            String msg = StrUtil.format("{}@{}@{}", e.getErrCode(), e.getClass().getSimpleName(), e.getMessage());

            if (e.isCanRetry()) {
                markException(one, msg);
            } else {
                markFail(one, msg);
            }

        } catch (Throwable e) {
            LOGGER.error(StrUtil.format("[DispatcherExecutor]error execute {} in Unknown", getName()), e);


            this.markException(one, StrUtil.format("unknown@{}@{}", e.getClass().getSimpleName(), e.getMessage()));


        }
    }

    protected abstract String getName();

    /**
     * 标记失败
     *  重试到达上限标记失败
     *  出现不可重试异常标记失败
     * @param one
     * @param msg
     */
    protected abstract void markFail(T one, String msg);

    /**
     * 标记异常
     * @param one
     * @param msg
     */
    protected abstract void markException(T one, String msg);

    /**
     * 标记成功
     * @param one
     */
    protected abstract void markSuccess(T one);

    protected abstract void doExecute(T one);
}
