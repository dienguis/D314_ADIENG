package fr.servcesimmatriculation.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.servcesimmatriculation.model.TitulaireVehicule;

@Repository
public interface TitulaireVehiculeRepository extends JpaRepository<TitulaireVehicule, String> {
	TitulaireVehicule findByNumeroidentification(String numero);
}
