package com.nh.cloud.common.log;

import java.util.HashMap;

/**
 * 로그 이력 저장 Interface 
 * <pre>
 * <b>History:</b>
 *    작성자, 1.0, 2024.06.26 최초작성
 * </pre>
 *
 * @author  김성현
 * @version 1.0, 2024.06.26 소스 수정
 * @see     None
 */

public interface SendLogInterface {

	public void logWrite(HashMap<String,String>  logMap, String desc);
}
