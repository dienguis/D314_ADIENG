package fr.servcesimmatriculation.Controller;

import java.net.URI;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fr.servcesimmatriculation.Services.AdministrationService;
import fr.servcesimmatriculation.Services.EmailService;
import fr.servcesimmatriculation.model.DemandeImmatriculation;
import fr.servcesimmatriculation.model.Immatriculation;

@CrossOrigin(origins = "${url}")
@RestController
@RequestMapping(value = "/api/genererId")
public class ImmatriculationController {
	@Autowired
	AdministrationService administrationService;
	@Autowired
	private EmailService serviceEmail;

	@GetMapping(value = "/allimmatriculation")
	public List<Immatriculation> getAllImmatriculation() {

		return administrationService.getAllImmatriculation();
	}

	@PostMapping(value = "/generer")
	public ResponseEntity<Void> genererId(@RequestBody DemandeImmatriculation demandeImmatriculation) {
		int number = 1;

		Immatriculation lastid = administrationService.getLastId();
		// Le mot est consituté des deux lettres
		String first_Mot = "";
		String second_Mot = "";
		String idvehicule = "";
		String chiffre = "";
		first_Mot = "AA";
		second_Mot = "AA";
		char firstlettre;
		char secondlettre;

		if (lastid == null) {
			chiffre = String.format("%03d", number);
			idvehicule = first_Mot + chiffre + second_Mot;
		} else {
			idvehicule = lastid.getNumeroimmatriculation();
			number = Integer.parseInt(idvehicule.substring(2, 5));
			// if (number < 10)
			chiffre = String.format("%03d", number);
			// if ((number > 9) && (number < 100))
			// chiffre = String.format("%02d", number);
			first_Mot = idvehicule.substring(0, 2);
			second_Mot = idvehicule.substring(5, 7);

			if (idvehicule != "ZZ999ZZ") {
				if ((number == 999) && (second_Mot.equals("ZZ"))) {
					// Traitement de l'incrémentation apres le numero ..999ZZ
					if (idvehicule.substring(1, 2).equals("Z")) {
						firstlettre = (char) (idvehicule.charAt(0) + 1);
						secondlettre = 'A';
						first_Mot = String.valueOf(firstlettre) + String.valueOf(secondlettre);
						second_Mot = "AA";
						chiffre = String.format("%03d", 1);
						idvehicule = first_Mot + chiffre + second_Mot;

					} else {
						firstlettre = idvehicule.charAt(0);
						secondlettre = (char) (idvehicule.charAt(1) + 1);
						System.out.print("secondlettre  " + secondlettre);
						second_Mot = "AA";
						chiffre = String.format("%03d", 1);
						first_Mot = String.valueOf(firstlettre) + String.valueOf(secondlettre);
						idvehicule = first_Mot + chiffre + second_Mot;

					}

				} else {
					if (number < 999) {
						number = number + 1;
						// if (number < 10)
						chiffre = String.format("%03d", number);
						// if ((number > 9) && (number < 100))
						// chiffre = String.format("%02d", number);
						idvehicule = first_Mot + chiffre + second_Mot;
					} else {
						secondlettre = idvehicule.charAt(6);
						firstlettre = idvehicule.charAt(5);
						if (secondlettre == 'Z') {
							secondlettre = 'A';
							chiffre = String.format("%03d", 1);
							firstlettre = (char) (idvehicule.charAt(5) + 1);
							second_Mot = String.valueOf(firstlettre) + String.valueOf(secondlettre);
							idvehicule = first_Mot + chiffre + second_Mot;

						} else {
							secondlettre = (char) (idvehicule.charAt(5) + 1);
							chiffre = String.format("%03d", 1);
							second_Mot = String.valueOf(firstlettre) + String.valueOf(secondlettre);
							idvehicule = first_Mot + chiffre + second_Mot;

						}
					}

				}

			}

		}
		Immatriculation immatriculation = new Immatriculation();
		immatriculation.setTitulaireVehicule(demandeImmatriculation.getTitulaireVehicule());
		immatriculation.setNumeroimmatriculation(idvehicule);
		immatriculation.setDatefirstimmatriculation(new Date());
		immatriculation.setDemandeImmatriculation(demandeImmatriculation);
		Immatriculation immatriculationCreate = administrationService.genererId(immatriculation);
		serviceEmail.sendNewIdGenerationEmail(immatriculationCreate,
				administrationService.loadTemplate("docx/certificat_temporaire.docx"), "temporaire");
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{numeroimmatriculation}")
				.buildAndExpand(immatriculationCreate.getNumeroimmatriculation()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
