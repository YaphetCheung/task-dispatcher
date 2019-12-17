package priv.yanfei.task.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import priv.yanfei.task.all.entity.CommonTask;
import priv.yanfei.task.all.mapper.CommonTaskMapper;
import priv.yanfei.task.common.util.SystemDateUtil;
import priv.yanfei.task.component.HostComponent;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {

    @Autowired
    private CommonTaskMapper commonTaskMapper;

    @Autowired
    private HostComponent hostComponent;

    @Test
    public void testSelectAll() {
        List<CommonTask> commonTasks = commonTaskMapper.selectList(null);
        Assert.assertNotEquals(0, commonTasks.size());
        System.out.println(commonTasks);
    }

    @Test
    public void testSelectOne() {
        CommonTask commonTask = commonTaskMapper.selectById("nono");
        Assert.assertNotNull(commonTask);
        System.out.println(commonTask);
    }

    @Test
    public void testQueryWrapper() {

        QueryWrapper wrapper = new QueryWrapper();


        List<CommonTask> commonTasks = commonTaskMapper.selectList(new QueryWrapper<CommonTask>().eq(true, "task_no", "no2"));

        Assert.assertEquals(1, commonTasks.size());

    }

    @Test
    public void testUpdateHangTasksByIp() {

        Integer integer = commonTaskMapper.updateHangTasksByIp("127.0.0.1", SystemDateUtil.getSystemDate());


    }
}
