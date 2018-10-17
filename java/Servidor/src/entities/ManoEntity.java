package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Mano")
public class ManoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idMano;
	
	@OneToOne
	@JoinColumn(name = "idChico")
	private ChicoEntity chico;
	
	public ManoEntity() {}
	
	public ManoEntity(ChicoEntity chico) {
		super();
		this.chico = chico;
	}

	public Integer getIdMano() {
		return idMano;
	}

	public void setIdMano(Integer idMano) {
		this.idMano = idMano;
	}

	public ChicoEntity getChico() {
		return chico;
	}

	public void setChico(ChicoEntity chico) {
		this.chico = chico;
	}
	
	

}
