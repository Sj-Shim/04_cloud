package com.nh.cloud.carryout.monitoring;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cloudCarryOutMonitoring")
public class CloudCarryOutMonitoring {

	  Logger logger = LoggerFactory.getLogger(getClass());
	  
	  @Autowired
	  CloudCarryOutMorningListener cloudCarryOutMorningListener;
	  
	  @PostConstruct
	  public void start() {
		  
		  cloudCarryOutMorningListener.start();
	      
		  logger.debug("@@@ CloudCarryOutMorningListener @@@ ");
	  }
}
