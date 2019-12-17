package priv.yanfei.task.dispatcher.common.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import priv.yanfei.task.common.enums.CommonTaskTypeEnum;
import priv.yanfei.task.dispatcher.common.CommonTask;
import priv.yanfei.task.dispatcher.common.model.DownPicTaskContextModel;
import priv.yanfei.task.exception.BizException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@Component
public class DownPicTaskService extends AbstractCommonTaskService implements InitializingBean {
    @Override
    protected boolean doServe(CommonTask commonTask) {

        DownPicTaskContextModel downPicTaskContextModel = JSONUtil.toBean(commonTask.getTaskContext(), DownPicTaskContextModel.class);

        FileOutputStream os = null;
        InputStream is = null;
        try {
            // 构造URL
            URL url = new URL(downPicTaskContextModel.getPicUrl());
            // 打开连接
            URLConnection con = url.openConnection();
            // 输入流
            is = con.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            String filename = StrUtil.format("/data/pic/{}", downPicTaskContextModel.getPicTitle());  //下载路径及下载图片名称
            File file = new File(filename);
            os = new FileOutputStream(file, true);
            // 开始读取
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new BizException("file not found", e);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new BizException("malformed url", e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BizException("io exception", e);
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new BizException("io exception(close io)", e);
            }
        }

        return true;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        commonTaskFactory.registerService(CommonTaskTypeEnum.DOWN_LOAD_PIC, this);
    }

    public static void main(String[] args) {
        DownPicTaskContextModel downPicTaskContextModel = new DownPicTaskContextModel();
        downPicTaskContextModel.setPicUrl("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3445812423,773927785&fm=26&gp=0.jpg");
        downPicTaskContextModel.setPicPackTitle("sougou");
        downPicTaskContextModel.setPicTitle("dd.jpg");
        downPicTaskContextModel.setPicType("zg");
        System.out.println(JSONUtil.toJsonStr(downPicTaskContextModel));
    }
}
