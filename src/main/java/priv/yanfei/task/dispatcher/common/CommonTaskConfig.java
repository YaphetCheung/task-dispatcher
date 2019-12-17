package priv.yanfei.task.dispatcher.common;

import cn.hutool.core.collection.CollUtil;
import priv.yanfei.task.common.enums.CommonTaskTypeEnum;

import java.util.Map;

public class CommonTaskConfig {


    //1.可判断是否到达最大得试次数
    //2.可获取下次执行时间
    private static final Map<CommonTaskTypeEnum, int[]> nextTimeGap = CollUtil.newHashMap();

    static {
        //单位：秒
        nextTimeGap.put(CommonTaskTypeEnum.DOWN_LOAD_PIC, new int[]{30, 60, 180, 360, 60 * 60, 60 * 60 * 2, 60 * 60 * 24});

    }

    /**
     * 查询下次任务执行的间隔时间
     * @param commonTaskTypeEnum
     * @param execTimes
     * @return
     */
    public static Integer getNextTime(CommonTaskTypeEnum commonTaskTypeEnum, Integer execTimes) {
        if (nextTimeGap.containsKey(commonTaskTypeEnum)) {
            int[] timeArray = nextTimeGap.get(commonTaskTypeEnum);
            //如果执行次数超出数组每次增加一小时,否则取数组里面的时间
            return execTimes > timeArray.length - 1 ? 60 * 60 : timeArray[execTimes];
        } else {
            return 5;
        }
    }

    /**
     * 查询任务的最大执行次数
     * @param commonTaskTypeEnum
     * @return
     */
    public static Integer getMaxRetryCount(CommonTaskTypeEnum commonTaskTypeEnum) {
        if (nextTimeGap.containsKey(commonTaskTypeEnum)) {
            int[] timeArray = nextTimeGap.get(commonTaskTypeEnum);
            return timeArray.length;
        } else {
            return 10;
        }
    }


}
