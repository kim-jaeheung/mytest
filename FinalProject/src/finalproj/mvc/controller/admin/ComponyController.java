package finalproj.mvc.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ComponyController {
	
	@RequestMapping(value="/")
	public String home() {
		
		
		return "my_main";
	}
}
