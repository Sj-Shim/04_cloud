package com.nh.cloud.carryout.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nh.cloud.common.consts.Consts;

@Service("carryOutReceiveService")
public class CarryOutReceiveService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	// Outnet Server로부터 반출된 파일 Receive
	// CloudReceiveController.outnetUpload에서 호출 된다.
	public void receiveOutnetFile(String fileName) 
	{	
		logger.debug("@@@ Cloud receive CarryOut File @@@ "+fileName);
		
		// 수신 파일 저장 메모리
		Consts.setCarryOutName(fileName);
	}
}
