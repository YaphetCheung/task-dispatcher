package priv.yanfei.task.all.service.impl;

import priv.yanfei.task.all.entity.HostInfo;
import priv.yanfei.task.all.mapper.HostInfoMapper;
import priv.yanfei.task.all.service.IHostInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 主机信息表 服务实现类
 * </p>
 *
 * @author zyf
 * @since 2019-12-16
 */
@Service
public class HostInfoServiceImpl extends ServiceImpl<HostInfoMapper, HostInfo> implements IHostInfoService {

}
