package priv.yanfei.task.all.mapper;

import org.apache.ibatis.annotations.Param;
import priv.yanfei.task.all.entity.HostInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 主机信息表 Mapper 接口
 * </p>
 *
 * @author zyf
 * @since 2019-12-16
 */
public interface HostInfoMapper extends BaseMapper<HostInfo> {

    Integer updateHeart(HostInfo hostInfo);


    HostInfo selectByHostIp(@Param("hostIp") String hostIp);


    Integer updateByHostIp(HostInfo hostInfo);
}
