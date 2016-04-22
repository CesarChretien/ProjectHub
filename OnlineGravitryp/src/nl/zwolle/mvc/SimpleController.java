package nl.zwolle.mvc;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SimpleController {

	@RequestMapping(value="/Game", method=RequestMethod.GET)
	public void checkCookie(HttpSession session) {
		System.out.println("Hallo!");
		session.setAttribute("sent", false);
	}

	@RequestMapping(value="/Highscores", method=RequestMethod.POST) 
	public void setScore(int score, Model model, HttpSession session) {
		model.addAttribute("score", score);
		if (session.getAttribute("sent") == null) {
			session.setAttribute("sent", false);
		}
	}
}