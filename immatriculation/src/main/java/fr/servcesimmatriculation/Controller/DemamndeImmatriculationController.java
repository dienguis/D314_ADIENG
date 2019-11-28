package fr.servcesimmatriculation.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import fr.servcesimmatriculation.Services.AdministrationService;
import fr.servcesimmatriculation.model.DemandeImmatriculation;

@CrossOrigin(origins = "${url}")
@RestController
@RequestMapping(value = "/api/immatriculation")
public class DemamndeImmatriculationController {
	@Autowired
	AdministrationService administrationService;

// Web Services POST GET  sur l'antité DemandeImmatriculation pour création et rechercher
	@PostMapping(value = "/create")
	public ResponseEntity<Void> createDemande(@RequestBody DemandeImmatriculation demandeImmatriculation) {
		DemandeImmatriculation demandeCreate = administrationService.saveDemandeImmatriculation(demandeImmatriculation);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{iddemande}")
				.buildAndExpand(demandeCreate.getIddemande()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping(value = "/alldemande")
	public List<DemandeImmatriculation> getAllDemande() {
		return administrationService.getAllDemandeImmatriculation();
	}

	@PutMapping(value = "/{demandeid}")
	public ResponseEntity<DemandeImmatriculation> updateDemande(@PathVariable String demandeid,
			@RequestBody DemandeImmatriculation demandeImmatriculation) {
		DemandeImmatriculation demandeUpdated = administrationService
				.saveDemandeImmatriculation(demandeImmatriculation);
		return new ResponseEntity<DemandeImmatriculation>(demandeImmatriculation, HttpStatus.OK);
	}

}
