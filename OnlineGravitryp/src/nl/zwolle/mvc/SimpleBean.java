package nl.zwolle.mvc;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class SimpleBean {

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
	
	@NotEmpty(message=" is leeg")
	private String woord;

	public String getWoord() {
		return woord;
	}
	public void setWoord(String woord) {
		this.woord = woord;
	}
}