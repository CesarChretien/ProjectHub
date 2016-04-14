package nl.zwolle.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/Game")
public class SimpleController {

	@RequestMapping(method=RequestMethod.GET)
	public void form() {
	}

}
