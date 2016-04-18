package nl.rjekker.demo;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Servlet implementation class HogerLagerServlet
 */

//@WebServlet("/index.html")

@Controller
public class HogerLagerServlet {
	
	public static final String GAMESTATE = "gamestate";
	public static final String ERROR = "error";
	public static final String GAME_JSP = "/WEB-INF/game.jsp";
	public static final String SUCCESS_JSP = "/WEB-INF/success.jsp";
	
	public GameState gs = null;
	
	/*private GameState getGameState(HttpServletRequest r){
		return (GameState)r.getSession().getAttribute(GAMESTATE);
	}
	
	private void makeGameState(HttpServletRequest r){
		r.getSession().setAttribute(GAMESTATE, new GameState());
	}
	
	/** 
	 * Returns null if no guess in req. params; -1 if not a valid guess
	 * If everything ok: return guess */
	/*private Integer getGuess(HttpServletRequest req){
		String input = req.getParameter("guess");
		if(input == null || input.isEmpty()){
			return null;
		}
		try{
			int result = Integer.valueOf(input);
			if(result < 0 || result > 100){
				return -1;
			}
			return result;
		}
		catch(NumberFormatException e){
			return -1;
		}
	}*/
	
	@RequestMapping("/try")
	protected @ResponseBody String tryGuess (@RequestParam int guess) {
		System.out.println("Hoi.");
		if(gs == null) {
			System.out.println("Hallo.");
			gs = new GameState();
		} 
		else if(gs.isSecret(guess)) {
			System.out.println("Ik kom hier voorbij.");
			gs = null;
			return "Correct!";
		}
		return (gs.getSecret() > guess) ? "Hoger!" : "Lager!";
	}
	
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(getGameState(request) == null){
			makeGameState(request);
		}
				
		request.getRequestDispatcher(GAME_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GameState gs = getGameState(request);
		if(gs == null){
			// no gamestate??
			// return same content as GET
			doGet(request, response);
			return;
		}

		String error = null;
		Integer guess = getGuess(request);
		if(guess == null){
			error = "Please enter your guess";	
		} else if(guess < 0){
			error = "That's not a valid guess!";
		} else if(gs.isSecret(guess)){
			request.setAttribute(GAMESTATE, gs);
			request.getSession().invalidate();
			request.getRequestDispatcher(SUCCESS_JSP).forward(request, response);
			return;
		} else {
			String hint = gs.getSecret() > guess ? "HOGER" : "LAGER";
			request.setAttribute("hint", hint);
		}

		request.setAttribute("error", error);
		request.getRequestDispatcher(GAME_JSP).forward(request, response);
	}*/
	
	

}
