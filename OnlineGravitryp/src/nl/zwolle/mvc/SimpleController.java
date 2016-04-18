package nl.zwolle.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import objects.Point;
import objects.Ship;
import objects.Sprite;

@Controller
@RequestMapping("/Game")
public class SimpleController {

	@RequestMapping(method=RequestMethod.GET)
	public void form(Model model) {
		//creates the ship
		Ship ship = new Ship(new Point(300, 300), Sprite.TRIANGLE, 64, 32, new Point(1, 0), new Point(0, 0));
		model.addAttribute("ship", ship);
	}

}
