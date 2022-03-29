package fr.jgrand.springpotagercompatibleapi.controller;

import fr.jgrand.springpotagercompatibleapi.model.Vegetable;
import fr.jgrand.springpotagercompatibleapi.repository.VegetableRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return vegetableRepository.findAllFriendVegetables(id);
    }

    @GetMapping("/{id}/enemies")
    public List<Vegetable> getVegetableEnemies(@PathVariable Long id) {
        return vegetableRepository.findAllEnemyVegetables(id);
    }
}
