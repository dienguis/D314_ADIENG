package fr.servcesimmatriculation.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import fr.servcesimmatriculation.Services.AdministrationService;
import fr.servcesimmatriculation.Services.EmailService;
import fr.servcesimmatriculation.model.DemandeImmatriculation;
import fr.servcesimmatriculation.model.Immatriculation;
import fr.servcesimmatriculation.model.StatutDemande;

@CrossOrigin(origins = "${url}")
@RestController
@RequestMapping(value = "/api")
public class ImpressionController {
	@Autowired
	private EmailService serviceEmail;
	@Autowired
	AdministrationService administrationService;
	@Value("${url2}")
	private String lien;

	@RequestMapping("/imprimer")

	public String SendImpression(@RequestBody Immatriculation immatriculation) {
		try {
			sendForPrint(immatriculation);
			Map<String, String> params = new HashMap<String, String>();
			params.put("demandeid", "" + immatriculation.getDemandeImmatriculation().getIddemande());
			RestTemplate restTemplate = new RestTemplate();
			DemandeImmatriculation demandeImmatriculation = immatriculation.getDemandeImmatriculation();
			demandeImmatriculation.setStatutDemande(StatutDemande.Imprimé);
			restTemplate.put(lien + "/api/immatriculation/{demandeid}", demandeImmatriculation, params);

			return "Demande Impression Envoyée!";
		} catch (Exception ex) {
			return "Erreur dans la demande d'impression: " + ex;
		}

	}

	private void sendForPrint(Immatriculation immatriculation) throws Exception {

		serviceEmail.sendNewIdGenerationEmail(immatriculation,
				administrationService.loadTemplate("docx/certificat_immatriculation.docx"), "final");
	}
}
