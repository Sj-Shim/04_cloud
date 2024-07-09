package com.nh.cloud.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@EnableAsync
@Configuration
public class ThreadConfig
{
  @Bean(name={"carryOutThread"})
  public TaskExecutor carryoutexecutor()
  {
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    executor.setCorePoolSize(10);
	    executor.setMaxPoolSize(100);
	    executor.setQueueCapacity(20);
	    executor.setThreadNamePrefix("CarryOutThreadPool_");
	    executor.initialize();
	    return executor;
  }

  @Bean(name={"carryInThread"})
  public TaskExecutor carryinexecutor()
  {
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    executor.setCorePoolSize(10);
	    executor.setMaxPoolSize(100);
	    executor.setQueueCapacity(20);
	    executor.setThreadNamePrefix("CarryInThreadPool_");
	    executor.initialize();
	    return executor;
  }
}
