package priv.yanfei.task.dispatcher.common;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;
import priv.yanfei.task.all.entity.CommonTask;
import priv.yanfei.task.all.mapper.CommonTaskMapper;
import priv.yanfei.task.common.converter.CommonTaskConverter;
import priv.yanfei.task.common.util.SystemDateUtil;
import priv.yanfei.task.component.HostComponent;
import priv.yanfei.task.dispatcher.AbstractTaskLoader;
import priv.yanfei.task.exception.BizException;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommonTaskLoader extends AbstractTaskLoader {

    @Value("${spring.profiles.active}")
    private String env;

    @Autowired
    private CommonTaskMapper commonTaskMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private HostComponent hostComponent;

    /**
     * 加载初始状态的任务，满足多台布署
     * @param size
     * @return
     */
    @Override
    protected List loadTodoTasks(int size) {

        return transactionTemplate.execute(status -> {
            List<CommonTask> commonTasks = commonTaskMapper.selectInitTasks(env, SystemDateUtil.getSystemDate(), size);

            if (CollUtil.isEmpty(commonTasks)) {
                return CollUtil.newArrayList();
            }

            List<String> taskNos = commonTasks.stream().map(CommonTask::getTaskNo).collect(Collectors.toList());

            Integer update = commonTaskMapper.updateTaskExec(taskNos, hostComponent.getHostInfo().getHostIp());

            if (taskNos.size() != update) {
                throw new BizException(StrUtil.format("[{}]更新任务数跟期望数不一致，实际更新：{}，期望更新：{}，已回滚", CommonTaskLoader.class.getSimpleName(), update, taskNos.size()));
            } else {
                return CommonTaskConverter.doList2boList(commonTasks);
            }
        });
    }
}
