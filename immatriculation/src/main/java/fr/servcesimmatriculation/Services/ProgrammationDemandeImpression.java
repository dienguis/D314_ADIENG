package fr.servcesimmatriculation.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import fr.servcesimmatriculation.model.Immatriculation;
import fr.servcesimmatriculation.model.StatutDemande;

@Component
@EnableScheduling
public class ProgrammationDemandeImpression {
	@Autowired
	AdministrationService administrationService;
	// @Value permet d'injecter la variable url2 définie dans application.properties
	@Value("${url2}")
	private String lien;

// Programme une tache de demande d'impression de carte grise qui s'exécute toutes les 03 minutes
	@Scheduled(cron = "0 */3 * ? * *") // chaque 3 minute
	public void scheduleTaskUsingCronExpression() {
		List<Immatriculation> immatriculations = administrationService
				.getAllImmatriculationNoPrint(StatutDemande.Approuvé);
		RestTemplate restTemplate = new RestTemplate();
		for (Immatriculation immatriculation : immatriculations) {
			restTemplate.put(lien + "/api/imprimer", immatriculation);

		}

	}

}
