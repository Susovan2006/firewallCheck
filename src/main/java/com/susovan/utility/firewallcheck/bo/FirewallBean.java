package com.susovan.utility.firewallcheck.bo;

public class FirewallBean {
	
	String destinationHostName;
	String destinationIPAddress;
	int destinationPort;
	String outputResult;
	
	
	public String getDestinationHostName() {
		return destinationHostName;
	}
	public void setDestinationHostName(String destinationHostName) {
		this.destinationHostName = destinationHostName;
	}
	public String getDestinationIPAddress() {
		return destinationIPAddress;
	}
	public void setDestinationIPAddress(String destinationIPAddress) {
		this.destinationIPAddress = destinationIPAddress;
	}
	public int getDestinationPort() {
		return destinationPort;
	}
	public void setDestinationPort(int destinationPort) {
		this.destinationPort = destinationPort;
	}
	public String getOutputResult() {
		return outputResult;
	}
	public void setOutputResult(String outputResult) {
		this.outputResult = outputResult;
	}
	@Override
	public String toString() {
		return "FirewallBean [destinationHostName=" + destinationHostName + ", destinationIPAddress="
				+ destinationIPAddress + ", destinationPort=" + destinationPort + ", outputResult=" + outputResult + "]";
	}
	
	

}
