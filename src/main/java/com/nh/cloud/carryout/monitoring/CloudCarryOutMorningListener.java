package com.nh.cloud.carryout.monitoring;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nh.cloud.common.consts.Consts;




@Service("cloudCarryOutMorningListener")
public class CloudCarryOutMorningListener {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public ExecutorService rcvExecutor;
	
	@Autowired
	CloudCarryOutMonitoringThread cloudCarryOutMonitoringThread;

	public CloudCarryOutMorningListener() {
	   	rcvExecutor = Executors.newSingleThreadExecutor();
	}
	
	public void start() 
	{
//		rcvExecutor.execute(new Thread() 
//		{
//			public void run() 
//			{
//				while (true) 
//				{
//					try {Thread.sleep(5000);} catch (InterruptedException e) 
//					{	e.printStackTrace(); }
//						
//					logger.debug("@@@ Cloud Carry Out Morning Listener @@@ ");
//					
//		 			String name = null;
//		 			/*
//		 			String DATA_DIRECTORY = "D:\\99_NH\\000\\04\\CarryOut";
//		 			
//		 			File dir = new File(DATA_DIRECTORY);
//
//		 			String[] filenames = dir.list();
//		 			
//		 			for (String filename : filenames) {
//		 				
//		 				Consts.setCarryOutName(filename);
//		 				
//		 				try {Thread.sleep(1000);} catch (InterruptedException e) 
//						{	e.printStackTrace(); }
//		 			}
//		 			*/
//					try 
//					{	if(Consts.carryOutName != null) 
//						{
//							if(Consts.carryOutName.size() != 0) 
//							{
//								name = Consts.getCarryOutName();
//							   	
//								if( name == null) continue;
//							   	
//								cloudCarryOutMonitoringThread.process(name);
//							}
//						} 
//					 }  catch (Exception e) {
//						 
//						 logger.error("@@@ CloudCarryOutMorningListener EXCEPTION @@@ ",e);
//					 }
//			    }
//			}
//		});
	}
}