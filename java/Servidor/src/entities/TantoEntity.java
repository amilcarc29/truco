package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tanto")
public class TantoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idTanto;
	
	private String tanto;

	public String getTanto() {
		return tanto;
	}

	public void setTanto(String tanto) {
		this.tanto = tanto;
	}
	
}	
