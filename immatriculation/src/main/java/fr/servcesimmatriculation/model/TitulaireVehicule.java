package fr.servcesimmatriculation.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "numerotelephone") })
@Entity
public class TitulaireVehicule {
	@Id
	private String numeroidentification;
	private String nom;
	private String prenom;
	@Temporal(TemporalType.DATE)
	private Date datenaissance;
	private String commune;
	private String departement;
	private String pays;
	private Nature nature;
	private String email;
	private String numerotelephone;
	private String addresse;

	public TitulaireVehicule() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TitulaireVehicule(String numeroidentification, String nom, String prenom, Date datenaissance, String commune,
			String departement, String pays, Nature nature, String email, String numerotelephone, String addresse) {
		super();
		this.numeroidentification = numeroidentification;
		this.nom = nom;
		this.prenom = prenom;
		this.datenaissance = datenaissance;
		this.commune = commune;
		this.departement = departement;
		this.pays = pays;
		this.nature = nature;
		this.email = email;
		this.numerotelephone = numerotelephone;
		this.addresse = addresse;
	}

	public String getNumeroidentification() {
		return numeroidentification;
	}

	public void setNumeroidentification(String numeroidentification) {
		this.numeroidentification = numeroidentification;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Date getDatenaissance() {
		return datenaissance;
	}

	public void setDatenaissance(Date datenaissance) {
		this.datenaissance = datenaissance;
	}

	public String getCommune() {
		return commune;
	}

	public void setCommune(String commune) {
		this.commune = commune;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public Nature getNature() {
		return nature;
	}

	public void setNature(Nature nature) {
		this.nature = nature;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumerotelephone() {
		return numerotelephone;
	}

	public void setNumerotelephone(String numerotelephone) {
		this.numerotelephone = numerotelephone;
	}

	public String getAddresse() {
		return addresse;
	}

	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}

}
