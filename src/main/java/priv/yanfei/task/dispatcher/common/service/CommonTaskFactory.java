package priv.yanfei.task.dispatcher.common.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import priv.yanfei.task.common.enums.CommonTaskTypeEnum;

import java.util.Map;

@Component
public class CommonTaskFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonTaskFactory.class);

    private final Map<CommonTaskTypeEnum, AbstractCommonTaskService> processers = CollUtil.newHashMap();


    public AbstractCommonTaskService getProcess(CommonTaskTypeEnum commonTaskTypeEnum) {
        return processers.get(commonTaskTypeEnum);
    }


    /**
     * 注册任务
     * @param commonTaskTypeEnum
     * @param commonTaskService
     */
    public void registerService(CommonTaskTypeEnum commonTaskTypeEnum, AbstractCommonTaskService commonTaskService) {
        processers.put(commonTaskTypeEnum, commonTaskService);

        LOGGER.info(StrUtil.format("[{}] 注册服务:{}成功", CommonTaskFactory.class.getSimpleName(), commonTaskTypeEnum.getCode()));
    }
}
