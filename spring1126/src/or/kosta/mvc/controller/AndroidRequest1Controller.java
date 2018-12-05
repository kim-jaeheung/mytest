package or.kosta.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AndroidRequest1Controller {

	@GetMapping("/value1")
	@ResponseBody	// 별도의 view 지정할 필요없이  바로 바인딩 됨.
	public String ex1_hello(String msg, Model m) {
		String contents = msg+": Walk up!";
		//m.addAttribute("contents", contesnts);
		System.out.println("Android ::" + msg);
		//return "app1";
		return contents;
	}
	
	/*@GetMapping("/value2")
	@ResponseBody
	public Map<String, String> ex1_hello(){
		Map<String, String> map = new HashMap<>();
		map.put("1", "kosta188");
		map.put("2", "kosta189");
		map.put("3", "kosta190");
			
		return map;
	}*/
	
}
