package fr.servcesimmatriculation.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.servcesimmatriculation.model.DemandeImmatriculation;

@Repository
public interface DemandeImmatriRepository extends JpaRepository<DemandeImmatriculation, Long> {

}
