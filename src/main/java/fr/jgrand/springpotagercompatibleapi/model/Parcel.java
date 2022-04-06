package fr.jgrand.springpotagercompatibleapi.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parcel_generator")
    @SequenceGenerator(name = "parcel_generator", sequenceName = "seq_parcel", allocationSize = 1)
    private Long id;

    private Integer width;

    private Integer height;

    @ManyToMany
    @JoinTable(
            name = "parcel_vegetables",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "vegetable_id"))
    private List<Vegetable> vegetableList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    @JsonIgnore
    public List<Vegetable> getVegetableList() {
        return vegetableList;
    }

    @JsonGetter("vegetableIds")
    public List<Long> getVegetablesIds() {
        return vegetableList.stream().map(Vegetable::getId).collect(Collectors.toList());
    }

    @JsonSetter("vegetableIds")
    public void setVegetableIds(List<Long> vegetableIds) {
        this.vegetableList = vegetableIds.stream().map(Vegetable::new).collect(Collectors.toList());
    }
}
