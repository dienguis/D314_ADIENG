package fr.servcesimmatriculation.Services;

import java.util.List;

import fr.servcesimmatriculation.model.DemandeImmatriculation;
import fr.servcesimmatriculation.model.Immatriculation;
import fr.servcesimmatriculation.model.StatutDemande;
import fr.servcesimmatriculation.model.TitulaireVehicule;

public interface AdministrationService {

	// Gestion des Titulaires de Véhicules

	public List<TitulaireVehicule> getAllTitulaire();

	public TitulaireVehicule getTitulaire(String numero);

	public TitulaireVehicule saveTitulaire(TitulaireVehicule titulaireVehicule);

	// Gestion des demandes d'immatriculation

	public List<DemandeImmatriculation> getAllDemandeImmatriculation();

	public DemandeImmatriculation saveDemandeImmatriculation(DemandeImmatriculation demandeImmatriculation);
	// Gestion des immatriculations

	public Immatriculation genererId(Immatriculation immatriculation);

	public List<Immatriculation> getAllImmatriculation();

	public Immatriculation getLastId();

	public Immatriculation getImmatriculationById(String numero);

	public List<Immatriculation> getAllImmatriculationNoPrint(StatutDemande statutDemande);

	// Get template path
	public String loadTemplate(String templateId);

}
