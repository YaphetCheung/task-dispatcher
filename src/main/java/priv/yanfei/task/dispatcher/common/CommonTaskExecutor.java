package priv.yanfei.task.dispatcher.common;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import priv.yanfei.task.all.mapper.CommonTaskMapper;
import priv.yanfei.task.common.enums.CommonTaskTypeEnum;
import priv.yanfei.task.common.util.SystemDateUtil;
import priv.yanfei.task.dispatcher.AbstractTaskExecutor;
import priv.yanfei.task.dispatcher.common.service.AbstractCommonTaskService;
import priv.yanfei.task.dispatcher.common.service.CommonTaskFactory;
import priv.yanfei.task.exception.BizException;

import java.time.LocalDateTime;

@Component
public class CommonTaskExecutor extends AbstractTaskExecutor<CommonTask> {

    @Autowired
    private CommonTaskMapper commonTaskMapper;

    @Override
    protected String getName() {
        return "commonTaskExecutor";
    }

    @Autowired
    private CommonTaskFactory commonTaskFactory;

    /**
     * 标记任务失败
     *
     * @param one
     * @param msg
     */
    @Override
    protected void markFail(CommonTask one, String msg) {
        commonTaskMapper.markTaskFail(one.getTaskNo(), msg);
    }

    /**
     * 任务执行异常
     *
     * @param one
     * @param msg
     */
    @Override
    protected void markException(CommonTask one, String msg) {
        Integer execTimes = one.getExecTimes();
        if (execTimes > CommonTaskConfig.getMaxRetryCount(CommonTaskTypeEnum.getByCode(one.getTaskType()))) {
            markFail(one, msg);
            return;
        }
        Integer offsetSeconds = CommonTaskConfig.getNextTime(CommonTaskTypeEnum.getByCode(one.getTaskType()), one.getExecTimes());
        LocalDateTime nextExecTime = SystemDateUtil.getSystemDate().plusSeconds(offsetSeconds);
        commonTaskMapper.markTaskExcp(one.getTaskNo(), nextExecTime, msg);
    }

    /**
     * 标记任务成功
     *
     * @param one
     */
    @Override
    protected void markSuccess(CommonTask one) {
        commonTaskMapper.markTaskSucc(one.getTaskNo());

    }

    @Override
    protected void doExecute(CommonTask one) {

        AbstractCommonTaskService process = commonTaskFactory.getProcess(CommonTaskTypeEnum.getByCode(one.getTaskType()));

        // 重试需要抛出异常，默认可重试
        if (ObjectUtil.isNull(process)) {
            throw new BizException("task：" + one.getTaskType() + "未注册");
        }

        boolean serve = process.serve(one);

        if (serve) {
            this.markSuccess(one);
        } else {
            this.markFail(one, one.getMemo());
        }

    }


}
