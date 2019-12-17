package priv.yanfei.task.dispatcher.common.service;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import priv.yanfei.task.dispatcher.common.CommonTask;

import java.io.FileNotFoundException;

public abstract class AbstractCommonTaskService {

    @Autowired
    protected CommonTaskFactory commonTaskFactory;

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCommonTaskService.class);

    public boolean serve(CommonTask commonTask) {

        LOGGER.info(StrUtil.format("[{}]taskNo={},taskType={}", AbstractCommonTaskService.class.getSimpleName(), commonTask.getTaskNo(), commonTask.getTaskType()));

        long startTime = System.currentTimeMillis();

        try {
            return doServe(commonTask);
        } finally {
            long endTime = System.currentTimeMillis();
            LOGGER.info("[{}]taskNo={},elipse={}ms", AbstractCommonTaskService.class.getSimpleName(), commonTask.getTaskNo(), endTime - startTime);
        }

    }

    protected abstract boolean doServe(CommonTask commonTask);
}
