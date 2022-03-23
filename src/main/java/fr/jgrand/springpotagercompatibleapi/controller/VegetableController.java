package fr.jgrand.springpotagercompatibleapi.controller;

import fr.jgrand.springpotagercompatibleapi.model.Vegetable;
import fr.jgrand.springpotagercompatibleapi.repository.VegetableRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/vegetables")
@CrossOrigin("*")
public class VegetableController {

    private VegetableRepository vegetableRepository;

    public VegetableController(VegetableRepository vegetableRepository) {
        this.vegetableRepository = vegetableRepository;
    }

    @GetMapping
    public List<Vegetable> getAllVegetables() {
        return vegetableRepository.findAll();
    }
}
