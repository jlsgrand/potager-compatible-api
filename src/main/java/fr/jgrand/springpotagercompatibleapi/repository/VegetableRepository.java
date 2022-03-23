package fr.jgrand.springpotagercompatibleapi.repository;

import fr.jgrand.springpotagercompatibleapi.model.Vegetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VegetableRepository extends JpaRepository<Vegetable, Long> {
}
