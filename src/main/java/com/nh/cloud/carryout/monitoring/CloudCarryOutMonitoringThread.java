package com.nh.cloud.carryout.monitoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.nh.cloud.carryout.service.FileCarryOutService;


@Service("cloudCarryOutMonitoringThread")
public class CloudCarryOutMonitoringThread 
{
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    FileCarryOutService  fileCarryOutService;
	
	@Async("carryOutThread")
    public void process(String fileName) {
		
		try 
		{
			fileCarryOutService.start(fileName);
		} catch (Exception e) {
				e.printStackTrace();
				logger.error("CloudCarryOutMonitoringThread Recive Msg take Err: " + e.toString());
		}
    }
}
