package com.spring.cloud.task;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.task.listener.TaskExecutionListener;
import org.springframework.cloud.task.listener.annotation.AfterTask;
import org.springframework.cloud.task.listener.annotation.BeforeTask;
import org.springframework.cloud.task.listener.annotation.FailedTask;
import org.springframework.cloud.task.repository.TaskExecution;
import org.springframework.cloud.task.repository.support.SimpleTaskRepository;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableTask
public class SpringCloudTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudTaskApplication.class, args);
	}
	
	@Bean
	public GreetingTask greetingTask(){
		return new GreetingTask();
	}
	
	public static class GreetingTask implements CommandLineRunner {
		
		@Override
		public void run(String... arg0) throws Exception {
			System.out.println("Message : "+ arg0[1]);
		}

		@AfterTask
		public void onTaskEnd(TaskExecution arg0) {
			System.out.println("Task Completed Sucessfully");
		}

		@FailedTask
		public void onTaskFailed(TaskExecution arg0, Throwable arg1) {
			System.out.println("Task Failed");
		}

		@BeforeTask
		public void onTaskStartup(TaskExecution arg0) {
			System.out.println("Task started");
		}
		
	}
}
