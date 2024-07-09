package com.nh.cloud.carryin.monitoring;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nh.cloud.common.consts.Consts;
import com.nh.cloud.common.log.SendLogService;

@Service("cloudCarryInMorningListener")
public class CloudCarryInMorningListener {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	public ExecutorService rcvExecutor;
	
	@Autowired
	CloudCarryInMonitoringThread cloudCarryInMonitoringThread;
	
	@Autowired
	SendLogService         sendLogService;

	public CloudCarryInMorningListener() {
	   	rcvExecutor = Executors.newSingleThreadExecutor();
	}
	
	public void start() 
	{
		rcvExecutor.execute(new Thread() 
		{
			public void run() 
			{
				while (true) 
				{
					try {Thread.sleep(1000);} catch (InterruptedException e) 
					{	e.printStackTrace(); }
					
					HashMap<String,Object> map = null;

					try 
					{	
						if(Consts.carryInMap != null) 
						{
							if(Consts.carryInMap.size() != 0) 
							{
								map = Consts.getCarryInMap();
							   	
								if( map == null) continue;
							   	
								cloudCarryInMonitoringThread.process(map);
							}
						} 
					 }  catch (Exception e) {
						 logger.debug(sendLogService.printStackTrace(e)); 
					 }
			    }
			}
		});
	}
}