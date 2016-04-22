package nl.zwolle.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SimpleController {

	@RequestMapping(value="/Game", method=RequestMethod.GET)
	public void form(HttpServletRequest request) {	
		System.out.println("Cookie: " + request.getSession().toString());
	}

	@RequestMapping(value="/Highscores", method=RequestMethod.POST) 
	public void setScore(int score, Model model, HttpServletRequest request) {
		System.out.println("Ik kom hierlangs.");
		model.addAttribute("score", score);
	}
}