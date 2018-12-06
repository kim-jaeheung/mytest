package finalproj.mvc.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ComponyController {
	
	@RequestMapping(value="/")
	public String home() {
		System.out.println("main");
		
		
		return "my_main";
	}
}
