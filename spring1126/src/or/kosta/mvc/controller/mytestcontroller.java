package or.kosta.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class mytestcontroller {

	@RequestMapping(value="/test", RequestMethod="GET")
	public String myView() {
		
		return "myview";
	}
}
