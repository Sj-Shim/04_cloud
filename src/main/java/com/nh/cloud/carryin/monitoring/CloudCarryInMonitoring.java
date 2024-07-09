package com.nh.cloud.carryin.monitoring;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cloudCarryInMonitoring")
public class CloudCarryInMonitoring {

	  Logger logger = LoggerFactory.getLogger(getClass());
	  
	  @Autowired
	  CloudCarryInMorningListener cloudCarryInMorningListener;
	  
	  @PostConstruct
	  public void start() {
		  
		  cloudCarryInMorningListener.start();
	  }
}
