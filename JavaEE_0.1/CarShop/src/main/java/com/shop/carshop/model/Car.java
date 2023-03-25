package com.shop.carshop.model;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "ad_id", referencedColumnName = "id")
    private Ad ad;

    @Column(name = "year_of_release")
    private int yearOfRelease;

    @Column(name = "mark")
    private String mark;

    @Column(name = "model")
    private String model;

    @Column(name = "condition")
    private Condition condition;

    @OneToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "car")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, CascadeType.REMOVE, CascadeType.REFRESH})
    private List<Image> images;
}

