package nl.zwolle.mvc;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Highscores/")
public class HighscoreRest {
	
	private boolean death = false;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Highscore> highscores() {
		List<Highscore> hsl = HighscoreDao.all();
		Collections.sort(hsl, new Highscore());
		return hsl;
	}
	
	@RequestMapping(value = "Check", method=RequestMethod.GET)
	public void death() {
		death = true;
	}
	
	@RequestMapping(value="Verstuur", method=RequestMethod.POST)
	public boolean add(Highscore score) {
		if(death) {
			HighscoreDao.add(score);
			death = false;
			return true;
		}
		else {
			return false;
		}
	}
}