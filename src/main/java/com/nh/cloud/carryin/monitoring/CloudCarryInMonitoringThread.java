package com.nh.cloud.carryin.monitoring;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.nh.cloud.carryin.service.FileCarryInService;

@Service("cloudCarryInMonitoringThread")
public class CloudCarryInMonitoringThread 
{
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    FileCarryInService  fileCarryInService;
	
	@Async("carryInThread")
    public void process(HashMap<String,Object> map) {
		try 
		{
			logger.debug("CloudCarryInMonitoringThread");
			
			fileCarryInService.start(map);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("CloudCarryInMonitoringThread Recive Msg take Err: " + e.toString());
		}
    }
}
