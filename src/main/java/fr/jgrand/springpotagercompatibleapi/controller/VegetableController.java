package fr.jgrand.springpotagercompatibleapi.controller;

import fr.jgrand.springpotagercompatibleapi.exception.VegetableAlreadyExisting;
import fr.jgrand.springpotagercompatibleapi.exception.VegetableNotFoundException;
import fr.jgrand.springpotagercompatibleapi.model.Vegetable;
import fr.jgrand.springpotagercompatibleapi.repository.VegetableRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vegetables")
@CrossOrigin("*")
public class VegetableController {

    private final VegetableRepository vegetableRepository;

    public VegetableController(VegetableRepository vegetableRepository) {
        this.vegetableRepository = vegetableRepository;
    }

    @GetMapping
    public List<Vegetable> getAllVegetables() {
        return vegetableRepository.findAll();
    }

    @GetMapping("/{id}/friends")
    public List<Vegetable> getVegetableFriends(@PathVariable Long id) {
        Optional<Vegetable> vegetable = vegetableRepository.findById(id);
        if (vegetable.isPresent()) {
            return vegetableRepository.findAllFriendVegetables(id);
        } else {
            throw new VegetableNotFoundException("Le légume ID : {" + id + "} n'existe pas, impossible de renvoyer la liste de légume compatibles");
        }
    }

    @GetMapping("/{id}/enemies")
    public List<Vegetable> getVegetableEnemies(@PathVariable Long id) {
        Optional<Vegetable> vegetable = vegetableRepository.findById(id);
        if (vegetable.isPresent()) {
            return vegetableRepository.findAllEnemyVegetables(id);
        } else {
            throw new VegetableNotFoundException("Le légume ID : {" + id + "} n'existe pas, impossible de renvoyer la liste de légume incompatibles");
        }
    }

    @PostMapping
    public ResponseEntity<Vegetable> createNewVegetable(@RequestBody Vegetable vegetable) {
        if (vegetable.getId() != null) {
            throw new VegetableAlreadyExisting("Le légume existe déjà, il ne peut pas être créé à nouveau");
        }
        return ResponseEntity.ok(vegetableRepository.save(vegetable));
    }

    @PutMapping
    public ResponseEntity<Vegetable> updateVegetable(@RequestBody Vegetable vegetable) {
        if (vegetable.getId() == null) {
            throw new VegetableNotFoundException("Le légume n'a pas d'ID, il ne peut pas être mis à jour");
        }
        if (!vegetableRepository.existsById(vegetable.getId())) {
            throw new VegetableNotFoundException("Le légume n'existe pas, il ne peut pas être mis à jour");
        }
        return ResponseEntity.ok(vegetableRepository.save(vegetable));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Vegetable> deleteVegetable(@PathVariable Long id) {
        // Dissociating relations before deletion
        Optional<Vegetable> vegetable = vegetableRepository.findById(id);
        if (vegetable.isPresent()) {
            vegetableRepository.deleteFriendsById(id);
            vegetableRepository.deleteEnemiesById(id);
            vegetableRepository.deleteById(id);
        }
        return ResponseEntity.noContent().build();
    }
}
