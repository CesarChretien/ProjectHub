package nl.zwolle.mvc;

import java.util.Comparator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Highscore implements Comparator<Highscore>{

	private Long id;
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	private String name;
	private int score;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	@Override
	public String toString(){
		return "Naam: " + this.getName() + ", score: " + this.getScore();
	}
	
	@Override
	public int compare(Highscore h1, Highscore h2) {
		return h2.getScore() - h1.getScore();
	}
}
