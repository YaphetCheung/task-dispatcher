package priv.yanfei.task.common.converter;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import priv.yanfei.task.dispatcher.common.CommonTask;

import java.util.List;
import java.util.stream.Collectors;

public class CommonTaskConverter {


    public static CommonTask do2bo(priv.yanfei.task.all.entity.CommonTask commonTask) {
        return BeanUtil.toBean(commonTask, CommonTask.class);
    }

    public static priv.yanfei.task.all.entity.CommonTask bo2do(CommonTask commonTask) {
        return BeanUtil.toBean(commonTask, priv.yanfei.task.all.entity.CommonTask.class);
    }

    public static List<CommonTask> doList2boList(List<priv.yanfei.task.all.entity.CommonTask> commonTaskList) {
        if (CollUtil.isEmpty(commonTaskList)) {
            return CollUtil.newArrayList();
        }
        return commonTaskList.stream().map(CommonTaskConverter::do2bo).collect(Collectors.toList());
    }

    public static List<priv.yanfei.task.all.entity.CommonTask> boList2doList(List<CommonTask> commonTaskList) {
        if (CollUtil.isEmpty(commonTaskList)) {
            return CollUtil.newArrayList();
        }
        return commonTaskList.stream().map(CommonTaskConverter::bo2do).collect(Collectors.toList());
    }

}
