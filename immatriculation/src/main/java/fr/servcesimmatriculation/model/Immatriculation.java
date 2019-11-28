package fr.servcesimmatriculation.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Immatriculation {
	@Id
	private String numeroimmatriculation;
	@Temporal(TemporalType.DATE)
	private Date datefirstimmatriculation;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "numeroidentification", referencedColumnName = "numeroidentification")
	private TitulaireVehicule titulaireVehicule;
	@OneToOne
	@JoinColumn(name = "iddemande", referencedColumnName = "iddemande")
	private DemandeImmatriculation demandeImmatriculation;

	public Immatriculation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Immatriculation(String numeroimmatriculation, Date datefirstimmatriculation,
			@NotNull TitulaireVehicule titulaireVehicule, DemandeImmatriculation demandeImmatriculation) {
		super();
		this.numeroimmatriculation = numeroimmatriculation;
		this.datefirstimmatriculation = datefirstimmatriculation;
		this.titulaireVehicule = titulaireVehicule;
		this.demandeImmatriculation = demandeImmatriculation;
	}

	public String getNumeroimmatriculation() {
		return numeroimmatriculation;
	}

	public void setNumeroimmatriculation(String numeroimmatriculation) {
		this.numeroimmatriculation = numeroimmatriculation;
	}

	public Date getDatefirstimmatriculation() {
		return datefirstimmatriculation;
	}

	public void setDatefirstimmatriculation(Date datefirstimmatriculation) {
		this.datefirstimmatriculation = datefirstimmatriculation;
	}

	public TitulaireVehicule getTitulaireVehicule() {
		return titulaireVehicule;
	}

	public void setTitulaireVehicule(TitulaireVehicule titulaireVehicule) {
		this.titulaireVehicule = titulaireVehicule;
	}

	public DemandeImmatriculation getDemandeImmatriculation() {
		return demandeImmatriculation;
	}

	public void setDemandeImmatriculation(DemandeImmatriculation demandeImmatriculation) {
		this.demandeImmatriculation = demandeImmatriculation;
	}

}
