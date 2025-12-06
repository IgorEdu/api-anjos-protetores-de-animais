package br.com.anjos_protetores_de_animais.api_controle_adocoes.controller.pvt;

import br.com.anjos_protetores_de_animais.api_controle_adocoes.domain.dto.AdoptionRequestDto;
import br.com.anjos_protetores_de_animais.api_controle_adocoes.service.AdoptionRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("adoptionRequestController")
@RequestMapping(value = "/api/pvt/adoptionRequests")
public class AdoptionRequestController extends BaseController {
    private final AdoptionRequestService adoptionRequestService;

    public AdoptionRequestController(final AdoptionRequestService adoptionRequestService) {
        this.adoptionRequestService = adoptionRequestService;
    }

    @GetMapping
    public ResponseEntity<List<AdoptionRequestDto>> findAllRequestAdoption() {
        final List<AdoptionRequestDto> adoptionRequests = this.adoptionRequestService.findAllRequestAdoption();

        return ResponseEntity.ok(adoptionRequests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findAllAdoptionRequestsByAnimal(@PathVariable final UUID id) {
        return this.adoptionRequestService.findAllAdoptionRequestsByAnimal(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> acceptAdoptionRequest(@PathVariable final UUID id) {
        return this.adoptionRequestService.acceptRequestAdoption(id);
    }
    
    @PutMapping("/{id}/revoke")
    public ResponseEntity<?> revokeAdoptionRequest(@PathVariable final UUID id) {
        return this.adoptionRequestService.revokeRequestAdoption(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdoptionRequest(@PathVariable final UUID id) {
        return this.adoptionRequestService.deleteRequestAdoption(id);
    }
    @GetMapping("/adopter/{id}")
    public ResponseEntity<List<AdoptionRequestDto>> findAllAdoptionRequestsByAdopter(@PathVariable final UUID id) {
        final List<AdoptionRequestDto> requests = this.adoptionRequestService.findAllAdoptionRequestsByAdopter(id);
        return ResponseEntity.ok(requests);
    }
}