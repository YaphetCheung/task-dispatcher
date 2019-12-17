package priv.yanfei.task.common.util;

import java.time.LocalDateTime;


/**
 * 系统统一用这个时间，方便测试
 */
public class SystemDateUtil {


    public static LocalDateTime getSystemDate() {
        return LocalDateTime.now().plusDays(3);
    }
}
