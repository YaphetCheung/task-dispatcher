/**
 * abssqr.com Inc.
 * Copyright (c) 2017-2017 All Rights Reserved.
 */
package priv.yanfei.task.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import priv.yanfei.task.component.HostComponent;

@Component
public class HostHeartJob {

    @Autowired
    private HostComponent hostComponent;

    @Scheduled(cron = "0 */5 * * * *")
    public void execute() {
        hostComponent.sendHeartBeat();
    }
}