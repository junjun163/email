package com.qf.springboot_email;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.qf.serviceimpl")
@DubboComponentScan("com.qf.serviceimpl")
public class SpringbootEmailApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootEmailApplication.class, args);
	}

}
