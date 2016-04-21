package nl.zwolle.mvc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Highscores/")
public class HighscoreRest {

	@RequestMapping(method=RequestMethod.GET)
	public List<Highscore> highscores() {
		return HighscoreDao.all();
	}
	
	@RequestMapping(value="Verstuur", method=RequestMethod.POST)
	public void add(Highscore score, HttpServletRequest request) {
		System.out.println("En hier kom ik ook langs!");
		HighscoreDao.add(score);
		request.getSession(false).invalidate();
	}
}
