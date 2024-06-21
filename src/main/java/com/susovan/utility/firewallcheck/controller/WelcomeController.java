package com.susovan.utility.firewallcheck.controller;

import java.net.SocketException;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.susovan.utility.firewallcheck.bo.FirewallBean;

import ch.qos.logback.core.util.StringUtil;
import io.micrometer.common.util.StringUtils;

@Controller
public class WelcomeController {
	
	
	@Autowired
	CheckSocket checkSocket;
	
	@GetMapping("/")
	public String main(Model model) throws SocketException, UnknownHostException {
		FirewallBean firewallBean = new FirewallBean();
		model.addAttribute("firewallBean", firewallBean);
		model.addAttribute("message", checkSocket.getSourceDetails());
		return "welcome";
	}
	
	
	@PostMapping("/getDetails")
	public String getDetails(@ModelAttribute ("firewallBean") FirewallBean firewallBean,
			              Model model) throws UnknownHostException{
		String node = firewallBean.getDestinationHostName();
		String ip = firewallBean.getDestinationIPAddress();
		Integer port = firewallBean.getDestinationPort();
		String validationMessage;
		
		try {
		    if(StringUtils.isEmpty(node) || (port !=null && port <=0)) {
		    	validationMessage = "Destination Host Name & Destination Port is a required Field";
		    	firewallBean.setOutputResult("Validation Error :"+validationMessage);
		    }else {
		    	firewallBean.setOutputResult(checkSocket.getFirewallCheckOutPut(node, port, 6));
		    }
		}catch (Exception e) {
			e.printStackTrace();
			firewallBean.setOutputResult("Processing Error :"+e.getMessage());
		}
		
		model.addAttribute("firewallBean",firewallBean);
		model.addAttribute("message",checkSocket.getSourceDetails());
		
		return "welcome";
		
	}
	

}
