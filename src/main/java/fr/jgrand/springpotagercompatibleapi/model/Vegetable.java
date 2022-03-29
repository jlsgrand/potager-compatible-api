package fr.jgrand.springpotagercompatibleapi.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    private Set<Vegetable> friendVegetables = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "vegetable_enemies",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "enemy_id"))
    private Set<Vegetable> enemyVegetables = new HashSet<>();

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

    public Set<Vegetable> getFriendVegetables() {
        return friendVegetables;
    }

    public Set<Vegetable> getEnemyVegetables() {
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

    @JsonGetter("friendVegetableIds")
    public List<Long> getFriendVegetableIds() {
        return friendVegetables.stream().map(Vegetable::getId).collect(Collectors.toList());
    }

    @JsonGetter("enemyVegetableIds")
    public List<Long> getEnemyVegetablesIds() {
        return enemyVegetables.stream().map(Vegetable::getId).collect(Collectors.toList());
    }
}
