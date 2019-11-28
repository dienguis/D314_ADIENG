package fr.servcesimmatriculation.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class DemandeImmatriculation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long iddemande;
	@Temporal(TemporalType.DATE)
	private Date datedemande;
	@Temporal(TemporalType.DATE)
	private Date dateachat;
	private String marque;
	private String type;
	private String denomination;
	private StatutDemande statutDemande;
	private Couleurs couleurs;
	@ManyToOne
	@JoinColumn(name = "numeroidentification", referencedColumnName = "numeroidentification")
	private TitulaireVehicule titulaireVehicule;
	private Integer nbrecotitulaire;
	private String cotitulaire;
	private String numeroidentificationvehicule;

	public DemandeImmatriculation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DemandeImmatriculation(Date datedemande, Date dateachat, String marque, String type, String denomination,
			StatutDemande statutDemande, Couleurs couleurs, TitulaireVehicule titulaireVehicule,
			Integer nbrecotitulaire, String cotitulaire, String numeroidentificationvehicule) {
		super();
		this.datedemande = datedemande;
		this.dateachat = dateachat;
		this.marque = marque;
		this.type = type;
		this.denomination = denomination;
		this.statutDemande = statutDemande;
		this.couleurs = couleurs;
		this.titulaireVehicule = titulaireVehicule;
		this.nbrecotitulaire = nbrecotitulaire;
		this.cotitulaire = cotitulaire;
		this.numeroidentificationvehicule = numeroidentificationvehicule;
	}

	public Long getIddemande() {
		return iddemande;
	}

	public void setIddemande(Long iddemande) {
		this.iddemande = iddemande;
	}

	public Date getDatedemande() {
		return datedemande;
	}

	public void setDatedemande(Date datedemande) {
		this.datedemande = datedemande;
	}

	public Date getDateachat() {
		return dateachat;
	}

	public void setDateachat(Date dateachat) {
		this.dateachat = dateachat;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDenomination() {
		return denomination;
	}

	public void setDenomination(String denomination) {
		this.denomination = denomination;
	}

	public StatutDemande getStatutDemande() {
		return statutDemande;
	}

	public void setStatutDemande(StatutDemande statutDemande) {
		this.statutDemande = statutDemande;
	}

	public Couleurs getCouleurs() {
		return couleurs;
	}

	public void setCouleurs(Couleurs couleurs) {
		this.couleurs = couleurs;
	}

	public TitulaireVehicule getTitulaireVehicule() {
		return titulaireVehicule;
	}

	public void setTitulaireVehicule(TitulaireVehicule titulaireVehicule) {
		this.titulaireVehicule = titulaireVehicule;
	}

	public Integer getNbrecotitulaire() {
		return nbrecotitulaire;
	}

	public void setNbrecotitulaire(Integer nbrecotitulaire) {
		this.nbrecotitulaire = nbrecotitulaire;
	}

	public String getCotitulaire() {
		return cotitulaire;
	}

	public void setCotitulaire(String cotitulaire) {
		this.cotitulaire = cotitulaire;
	}

	public String getNumeroidentificationvehicule() {
		return numeroidentificationvehicule;
	}

	public void setNumeroidentificationvehicule(String numeroidentificationvehicule) {
		this.numeroidentificationvehicule = numeroidentificationvehicule;
	}

}
