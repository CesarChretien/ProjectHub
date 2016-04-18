package nl.rjekker.demo;

import java.util.Date;
import java.util.Random;

public class GameState {
	private int secret;
	private int tries;
	private static Random rng = new Random(new Date().getTime());
	
	public int getSecret() {
		return secret;
	}
	public int getTries() {
		return tries;
	}
	
	public boolean isSecret(int guess){
		++this.tries;
		return guess == this.secret;
	}
	
	public GameState(){
		secret = rng.nextInt(100);
	}
}
