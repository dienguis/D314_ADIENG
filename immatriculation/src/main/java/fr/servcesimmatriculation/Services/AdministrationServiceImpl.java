package fr.servcesimmatriculation.Services;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.servcesimmatriculation.DAO.DemandeImmatriRepository;
import fr.servcesimmatriculation.DAO.ImmatriculationRepository;
import fr.servcesimmatriculation.DAO.TitulaireVehiculeRepository;
import fr.servcesimmatriculation.model.DemandeImmatriculation;
import fr.servcesimmatriculation.model.Immatriculation;
import fr.servcesimmatriculation.model.StatutDemande;
import fr.servcesimmatriculation.model.TitulaireVehicule;

@Service
@Transactional(rollbackOn = { Exception.class })
public class AdministrationServiceImpl implements AdministrationService {
	@Autowired
	TitulaireVehiculeRepository titulaireVehiculeRepository;
	@Autowired
	DemandeImmatriRepository demandeImantriculationRepository;
	@Autowired
	ImmatriculationRepository immatriculationRepository;

	// Gestion des Titulaires de Véhicules

	@Override
	public List<TitulaireVehicule> getAllTitulaire() {
		return titulaireVehiculeRepository.findAll();
	}

	@Override
	public TitulaireVehicule saveTitulaire(TitulaireVehicule titulaireVehicule) {
		return titulaireVehiculeRepository.save(titulaireVehicule);
	}

	@Override
	public TitulaireVehicule getTitulaire(String numero) {
		return titulaireVehiculeRepository.findByNumeroidentification(numero);
	}

	// Gestion des demandes d'immatriculation

	@Override
	public List<DemandeImmatriculation> getAllDemandeImmatriculation() {
		return demandeImantriculationRepository.findAll();
	}

	@Override
	public DemandeImmatriculation saveDemandeImmatriculation(DemandeImmatriculation demandeImmatriculation) {
		return demandeImantriculationRepository.save(demandeImmatriculation);
	}

	// Gestion des immatriculations

	@Override
	public Immatriculation genererId(Immatriculation immatriculation) {
		return immatriculationRepository.save(immatriculation);
	}

	@Override
	public List<Immatriculation> getAllImmatriculation() {
		return immatriculationRepository.findAll();
	}

	@Override
	public Immatriculation getLastId() {
		return immatriculationRepository.findTopByOrderByNumeroimmatriculationDesc();
	}

	@Override
	public List<Immatriculation> getAllImmatriculationNoPrint(StatutDemande statutDemande) {
		return immatriculationRepository.findByDemandeImmatriculation_StatutDemande(statutDemande);
	}

	@Override
	public Immatriculation getImmatriculationById(String numero) {
		return immatriculationRepository.findByNumeroimmatriculation(numero);
	}

	// Fonction qui retourne le chemin d'access de templates

	@Override
	public String loadTemplate(String templateId) {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("templates/" + templateId).getFile());
		String configPath = "";
		try {
			configPath = URLDecoder.decode("" + file.getParent(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return configPath;
	}
}
