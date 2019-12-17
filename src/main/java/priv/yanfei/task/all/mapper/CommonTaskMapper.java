package priv.yanfei.task.all.mapper;

import org.apache.ibatis.annotations.Param;
import priv.yanfei.task.all.entity.CommonTask;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 通用任务表 Mapper 接口
 * </p>
 *
 * @author zyf
 * @since 2019-12-16
 */
public interface CommonTaskMapper extends BaseMapper<CommonTask> {


    Integer updateHangTasksByIp(@Param("lastHost") String lastHost, @Param("nextExecTime") LocalDateTime nextExecTime);

    Integer markTaskSucc(@Param("taskNo") String taskNo);

    Integer markTaskFail(@Param("taskNo") String taskNo, @Param("msg") String msg);

    Integer markTaskExcp(@Param("taskNo") String taskNo, @Param("nextExecTime") LocalDateTime nextExecTime, @Param("msg") String msg);

    List<CommonTask> selectInitTasks(@Param("env") String env, @Param("nowTime") LocalDateTime nowTime);

    Integer updateTaskExec(@Param("taskNos") List<String> taskNos,@Param("hostIp") String hostIp);
}
