package fr.servcesimmatriculation.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.servcesimmatriculation.model.Immatriculation;
import fr.servcesimmatriculation.model.StatutDemande;

@Repository
public interface ImmatriculationRepository extends JpaRepository<Immatriculation, String> {
	Immatriculation findTopByOrderByNumeroimmatriculationDesc();

	Immatriculation findByNumeroimmatriculation(String numero);

	List<Immatriculation> findByDemandeImmatriculation_StatutDemande(StatutDemande statutDemande);

}
