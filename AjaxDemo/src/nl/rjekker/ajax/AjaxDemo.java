package nl.rjekker.ajax;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxDemo {

	@RequestMapping(value="/plus")
	public @ResponseBody Integer plus(@RequestParam int a, @RequestParam int b, HttpSession session){
		List<Integer> history = (List<Integer>)session.getAttribute("history");
		if(history == null){
			history = new ArrayList<>();
			session.setAttribute("history", history);
		}
		int result = a + b;
		history.add(result);
		return result;
	}
	
	@RequestMapping(value="/history")
	public @ResponseBody List<Integer> history(HttpSession session){
		return (List<Integer>)session.getAttribute("history");
	}
}
