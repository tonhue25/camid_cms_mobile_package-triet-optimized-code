/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.siten.myvtg.configuration;

import co.siten.myvtg.exception.SimpleAsyncUncaughtExceptionHandler;
import co.siten.myvtg.service.AsyncLogService;
import co.siten.myvtg.service.AsyncTaskService;
import java.util.concurrent.Executor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 *
 * @author LuatNC
 */
@Configuration
@EnableAsync
@ComponentScan(basePackages = "co.siten.myvtg.service")
public class AsynConfiguration implements AsyncConfigurer {

        @Bean
        public AsyncTaskService asyncTaskService() {
                return new AsyncTaskService();
        }
        
        @Bean
        public AsyncLogService asyncLogService() {
                return new AsyncLogService();
        }

        @Override
        public Executor getAsyncExecutor() {
                ThreadPoolTaskExecutor executorPool = new ThreadPoolTaskExecutor();
                executorPool.setCorePoolSize(50);
                executorPool.setMaxPoolSize(100);
                executorPool.initialize();

                return executorPool;
        }

        @Override
        public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
                return new SimpleAsyncUncaughtExceptionHandler();
        }
}
