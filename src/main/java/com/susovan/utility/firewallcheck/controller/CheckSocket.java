package com.susovan.utility.firewallcheck.controller;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckSocket {
	
	
	
	public String getSourceDetails() throws UnknownHostException{
		InetAddress IP = InetAddress.getLocalHost();
		return IP.getHostAddress()+"/"+IP.getHostName();
	}
	
	
	@SuppressWarnings("finally")
	public String getFirewallCheckOutPut(String node, int port, int timeOutInSec) {
		int extiStatus = 1;
		Socket a = null;
		String reason = null;
		try {
			a= new Socket();
			a.setReuseAddress(true);
			SocketAddress sa = new InetSocketAddress(node, port);
			a.connect(sa, timeOutInSec * 1000);
		}catch( IOException ex) {
			if(ex.getMessage().equalsIgnoreCase("Connection Refused") || 
					ex.getMessage().contains(("Connection Refused"))) {
				reason =  "Port " + port + " on " + node + " is closed";
			}
			
			if(ex instanceof UnknownHostException) {
				reason =  " Node " + node + " is unresolved, validate the host name.";
			}
			
			if(ex instanceof SocketTimeoutException) {
				reason =  " Time out occured while trying to reach node " + node + " on Port" + port;
			}
		} catch(Exception ex) {
			reason = "Error Occured while checking Firewall, MSG:"+ex.getMessage();
		}finally{
			if (a!=null) {
				if(a.isConnected()) {
					System.out.println("Port " + port + " on " + node + " is reachable");
					reason = "Port " + port + " on " + node + " is reachable";
					extiStatus = 0;
				}else {
					System.out.println("Port " + port + " on " + node + " is not reachable. Reason ="+reason);
					reason = "Port " + port + " on " + node + " is not reachable. Reason ="+reason ;
				}
				
				try {
					a.close();
				}catch(IOException e) {
					reason = "Error Occured While checking firewall" + e.getMessage();
				}catch(Exception e) {
					reason = "Error Occured While checking firewall" + e.getMessage();
				}
			}
			
			return reason;
		}
	}

}
