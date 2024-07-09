package com.nh.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication

public class CloudApplication extends SpringBootServletInitializer
{
  public static void main(String[] args)
  {
    SpringApplication.run(CloudApplication.class, args);
  }
}
