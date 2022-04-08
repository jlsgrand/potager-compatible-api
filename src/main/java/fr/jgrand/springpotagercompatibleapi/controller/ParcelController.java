package fr.jgrand.springpotagercompatibleapi.controller;

import fr.jgrand.springpotagercompatibleapi.exception.ParcelAlreadyExistingException;
import fr.jgrand.springpotagercompatibleapi.exception.ParcelDimensionExceededException;
import fr.jgrand.springpotagercompatibleapi.exception.ParcelNotFoundException;
import fr.jgrand.springpotagercompatibleapi.model.Parcel;
import fr.jgrand.springpotagercompatibleapi.repository.ParcelRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parcels")
@CrossOrigin("*")
public class ParcelController {

    private ParcelRepository parcelRepository;

    public ParcelController(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    @GetMapping
    public List<Parcel> getParcelList() {
        return parcelRepository.findAll();
    }

    @GetMapping("{id}")
    public ResponseEntity<Parcel> getParcel(@PathVariable Long id) {
        Optional<Parcel> parcel = parcelRepository.findById(id);
        return parcel.map(ResponseEntity::ok).orElseThrow(() -> new ParcelNotFoundException("La parcelle ID : {" + id + "} n'existe pas"));
    }

    @PostMapping
    public ResponseEntity<Parcel> createParcel(@RequestBody Parcel parcel) {
        if (parcel.getId() != null) {
            throw new ParcelAlreadyExistingException("La parcelle existe déjà, elle ne peut pas être créée à nouveau");
        }

        if (parcel.getVegetableList().size() > parcel.getHeight() * parcel.getWidth()) {
            throw new ParcelDimensionExceededException("La parcelle n'a pas les bonnes dimentions");
        }

        return ResponseEntity.ok(parcelRepository.save(parcel));
    }

    @PutMapping
    public ResponseEntity<Parcel> updateParcel(@RequestBody Parcel parcel) {

        if (parcel.getId() == null) {
            throw new ParcelNotFoundException("La parcelle n'a pas d'ID, elle ne peut pas être mise à jour");
        }

        if (!parcelRepository.existsById(parcel.getId())) {
            throw new ParcelNotFoundException("La parcelle n'existe pas, elle ne peut pas être mise à jour");
        }

        if (parcel.getVegetableList().size() > parcel.getHeight() * parcel.getWidth()) {
            throw new ParcelDimensionExceededException("La parcelle n'a pas les bonnes dimensions");
        }

        return ResponseEntity.ok(parcelRepository.save(parcel));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Parcel> deleteParcel(@PathVariable Long id) {
        parcelRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
