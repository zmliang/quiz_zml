package com.self.java.quiz.netty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Value;

public class QuartzManager {
    private static final Logger logger = LogManager.getLogger(QuartzManager.class.getName());
    private Scheduler scheduler;

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Value("${scheduler.open}")
    private boolean open;

    /**
     * @Description:关闭所有定时任务
     */
    public void shutdownJobs() {
        try {
            logger.info("scheduler.isShutdown()--->" + scheduler.isShutdown());
            if (!scheduler.isShutdown()) {
                scheduler.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isOpen() {
        return open;
    }
}
