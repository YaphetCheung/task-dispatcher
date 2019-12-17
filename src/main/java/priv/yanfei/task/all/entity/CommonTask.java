package priv.yanfei.task.all.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import priv.yanfei.task.common.model.BaseModel;

/**
 * <p>
 * 通用任务表
 * </p>
 *
 * @author zyf
 * @since 2019-12-16
 */
public class CommonTask extends BaseModel {

    private static final long serialVersionUID = 1L;

    /**
     * 任务编号
     */
    @TableId
    private String taskNo;

    /**
     * 任务类型
     */
    private String taskType;

    /**
     * 任务状态
     */
    private String taskStatus;

    /**
     * 任务上下文
     */
    private String taskContext;

    /**
     * 执行次数
     */
    private Integer execTimes;

    /**
     * 上次执行时间
     */
    private LocalDateTime lastExecTime;

    /**
     * 下次执行时间
     */
    private LocalDateTime nextExecTime;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    private String memo;

    /**
     * 环境
     */
    private String env;

    /**
     * 上次执行主机
     */
    private String lastHost;

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskContext() {
        return taskContext;
    }

    public void setTaskContext(String taskContext) {
        this.taskContext = taskContext;
    }

    public Integer getExecTimes() {
        return execTimes;
    }

    public void setExecTimes(Integer execTimes) {
        this.execTimes = execTimes;
    }

    public LocalDateTime getLastExecTime() {
        return lastExecTime;
    }

    public void setLastExecTime(LocalDateTime lastExecTime) {
        this.lastExecTime = lastExecTime;
    }

    public LocalDateTime getNextExecTime() {
        return nextExecTime;
    }

    public void setNextExecTime(LocalDateTime nextExecTime) {
        this.nextExecTime = nextExecTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public String getLastHost() {
        return lastHost;
    }

    public void setLastHost(String lastHost) {
        this.lastHost = lastHost;
    }

    @Override
    public String toString() {
        return "CommonTask{" +
                "taskNo=" + taskNo +
                ", taskType=" + taskType +
                ", taskStatus=" + taskStatus +
                ", taskContext=" + taskContext +
                ", execTimes=" + execTimes +
                ", lastExecTime=" + lastExecTime +
                ", nextExecTime=" + nextExecTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", memo=" + memo +
                ", env=" + env +
                ", lastHost=" + lastHost +
                "}";
    }
}
