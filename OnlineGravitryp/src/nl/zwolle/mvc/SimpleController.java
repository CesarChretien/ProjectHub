package nl.zwolle.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SimpleController {

	@RequestMapping(value="/Game", method=RequestMethod.GET)
	public void form(Model model) {	}

	@RequestMapping(value="/Highscores", method=RequestMethod.POST) 
	public void redirect() {}
}
