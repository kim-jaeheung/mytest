package or.kosta.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {
	
	@RequestMapping("/")
	public String myMain() {
		
		
		return "index";
	}

	
	@RequestMapping("/myindex")
	public String myDefaultView() {
		
		return "index";
	}
	
	@RequestMapping("/mytest")
	public String myDefaultView1() {
		
		return "index";
	}
	
	@RequestMapping("/memberlist")
	public String memberlist() {
		return "memberlist";
	}

	
}
