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
import fr.servcesimmatriculation.model.TitulaireVehicule;

@CrossOrigin(origins = "${url}")
@RestController
@RequestMapping(value = "/api/vehicules")
public class TitulaireVehiculeController {
	@Autowired
	AdministrationService administrationService;

	@PostMapping(value = "/create")
	public ResponseEntity<Void> createTitulaire(@RequestBody TitulaireVehicule titulaireVehicule) {
		TitulaireVehicule titulaireVehiculeCreate = administrationService.saveTitulaire(titulaireVehicule);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{numeroidentification}")
				.buildAndExpand(titulaireVehiculeCreate.getNumeroidentification()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping(value = "/alltitulaire")
	public List<TitulaireVehicule> getAllTitulaire() {
		return administrationService.getAllTitulaire();
	}

	@PutMapping(value = "/{titulaireId}")
	public ResponseEntity<TitulaireVehicule> updateTitulaireVehicule(@PathVariable String titulaireId,
			@RequestBody TitulaireVehicule titulaireVehicule) {
		TitulaireVehicule titulaireVehiculeUpdated = administrationService.saveTitulaire(titulaireVehicule);
		return new ResponseEntity<TitulaireVehicule>(titulaireVehicule, HttpStatus.OK);
	}

}
