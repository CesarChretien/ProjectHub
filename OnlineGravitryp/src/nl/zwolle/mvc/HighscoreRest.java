package nl.zwolle.mvc;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Highscores/")
public class HighscoreRest {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Highscore> highscores() {
		List<Highscore> hsl = HighscoreDao.all();
		Collections.sort(hsl, new Highscore());
		return hsl;
	}
	
	@RequestMapping(value="Verstuur", method=RequestMethod.POST)
	public boolean add(Highscore score, Model model, HttpSession session) {
		if(!((boolean) session.getAttribute("sent"))) {
			HighscoreDao.add(score);
			session.setAttribute("sent", true);
			return true;
		}
		else {
			return false;
		}
	}
}