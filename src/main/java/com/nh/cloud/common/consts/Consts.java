package com.nh.cloud.common.consts;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;

public class Consts {
	
	/** Carry Out 처리  */
	public static LinkedList<String>  carryOutName     = new LinkedList<String>();
	
	/** Carry In  처리  */
	public static LinkedList<String>  carryInName      = new LinkedList<String>();
	
	/** Carry In  처리  */
	public static LinkedList<HashMap<String,Object>>  carryInMap = new LinkedList<HashMap<String,Object>>();
	
	// 클라우드 URL 
	public static  String Cloud_URL = "http://localhost:8080/";
	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//
	// 시스템명
	public static  String      systemName    = "AZU";
	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//
	// ############# < Carry In > ###########//
	// 파일 반입 대상 디렉토리  < APP -> 클라우드 >
	public static  String   CarryInPath;
	// ############# < Carry Out > ###########//
	// 파일 반출 대상 디렉토리 < 외부망 -> 클라우드 >
	public static  String   CarryOutPath;
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//
	public static  String      attributeName = "FILENAME";
	public static  String      attributeEmpno= "EMPNO";
	
	public static  final String Log_Error     = "ERROR";
	public static  final String Log_Exception = "ERROR";
	public static  final String Log_Trace     = "ERROR";
	
	
	public static synchronized void setCarryInMap(HashMap<String,Object> name) 
	{
		carryInMap.add(name);
	}
	
	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//
	public static synchronized HashMap<String,Object> getCarryInMap() 
	{
		HashMap<String,Object> name = null;
		
		if(carryInMap.size() > 0){
			name = carryInMap.getFirst(); 
			carryInMap.remove(0);
		}
		
		return name;
	}
	
	public static synchronized void setCarryInName(String name) 
	{
		carryInName.add(name);
	}
	
	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//
	public static synchronized String getCarryInName() 
	{
		String name = null;
		
		if(carryInName.size() > 0){
			name = carryInName.getFirst(); 
			carryInName.remove(0);
		}
		
		return name;
	}
	
	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//
	public static synchronized void setCarryOutName(String name) 
	{
		carryOutName.add(name);
	}

	//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//
	public static synchronized String getCarryOutName() 
	{
		String name = null;
		
		if(carryOutName.size() > 0){
			name = carryOutName.getFirst(); 
			carryOutName.remove(0);
		}
		
		return name;
	}
	
	 /* 현재 서버의 IP 주소를 가져옵니다.
	 * 
	 * @return IP 주소
	 */
	 
	private static String getLocalServerIp()
	{
		String  ip = "";
		try
		{
		    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();)
		    {
		        NetworkInterface intf = en.nextElement();
		       
		        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();)
		        {
		            InetAddress inetAddress = enumIpAddr.nextElement();
		            
		            ip = ip +"@"+inetAddress;
		        }
		    }
		}
		catch (SocketException ex) {}
		
		return ip;
	}
}
