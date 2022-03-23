package fr.jgrand.springpotagercompatibleapi.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Vegetable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vegetable_generator")
    @SequenceGenerator(name = "vegetable_generator", sequenceName = "seq_vegetable", allocationSize = 1)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Month startingSowingCover;

    @Enumerated(EnumType.STRING)
    private Month endingSowingCover;

    @Enumerated(EnumType.STRING)
    private Month startingSowingGround;

    @Enumerated(EnumType.STRING)
    private Month endingSowingGround;

    @Enumerated(EnumType.STRING)
    private Month startingHarvest;

    @Enumerated(EnumType.STRING)
    private Month endingHarvest;

    @ManyToMany
    @JoinTable(
            name = "vegetable_friends",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private List<Vegetable> friendVegetables = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "vegetable_enemies",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "enemy_id"))
    private List<Vegetable> enemyVegetables = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Month getStartingSowingCover() {
        return startingSowingCover;
    }

    public Month getEndingSowingCover() {
        return endingSowingCover;
    }

    public Month getStartingSowingGround() {
        return startingSowingGround;
    }

    public Month getEndingSowingGround() {
        return endingSowingGround;
    }

    public Month getStartingHarvest() {
        return startingHarvest;
    }

    public Month getEndingHarvest() {
        return endingHarvest;
    }

    public List<Vegetable> getFriendVegetables() {
        return friendVegetables;
    }

    public List<Vegetable> getEnemyVegetables() {
        return enemyVegetables;
    }

    @JsonGetter("friendVegetables")
    public List<String> getFriendVegetablesString() {
        return friendVegetables.stream().map(Vegetable::getName).collect(Collectors.toList());
    }

    @JsonGetter("enemyVegetables")
    public List<String> getEnemyVegetablesString() {
        return enemyVegetables.stream().map(Vegetable::getName).collect(Collectors.toList());
    }
}
