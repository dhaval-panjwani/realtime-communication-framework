package rtcf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	@GetMapping("/greetingInrtcf")
	public @ResponseBody String greetingWishes() {
		System.out.println("Inside rest greeting");
		return "Hello world";
	}

}
