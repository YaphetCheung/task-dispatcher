package priv.yanfei.task.all.service.impl;

import priv.yanfei.task.all.entity.CommonTask;
import priv.yanfei.task.all.mapper.CommonTaskMapper;
import priv.yanfei.task.all.service.ICommonTaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 通用任务表 服务实现类
 * </p>
 *
 * @author zyf
 * @since 2019-12-16
 */
@Service
public class CommonTaskServiceImpl extends ServiceImpl<CommonTaskMapper, CommonTask> implements ICommonTaskService {

}
