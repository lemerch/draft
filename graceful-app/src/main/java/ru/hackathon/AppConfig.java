package ru.hackathon;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Objects;

@Configuration
public class AppConfig {

    private Integer coreSize;
    private Integer maxSize;
    private Integer timeOut;

    public AppConfig(
            @Value("${server.core-pool-size}") Integer coreSize,
            @Value("${server.max-pool-size}") Integer maxSize,
            @Value("${server.termination-timeout-seconds}") Integer timeOut
    ) {
        this.coreSize = coreSize;
        this.timeOut = timeOut;
        this.maxSize = maxSize;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(coreSize);
        taskExecutor.setMaxPoolSize(maxSize);
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        if (!Objects.isNull(timeOut)) taskExecutor.setAwaitTerminationSeconds(timeOut);
        taskExecutor.initialize();
        return taskExecutor;
    }
}
