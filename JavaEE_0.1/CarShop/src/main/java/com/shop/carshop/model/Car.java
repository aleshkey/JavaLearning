package com.shop.carshop.model;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "cars")
public class Car implements Serializable, Model {

    public Car(Car car, Ad ad, User user){
        this.mark = car.getMark();
        this.condition = car.getCondition();
        this.images = car.getImages();
        this.model = car.getModel();
        this.yearOfRelease = car.getYearOfRelease();
        this.ad = ad;
        this.user = user;
    }

    @Id
    @OneToOne(cascade = jakarta.persistence.CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ad_id", referencedColumnName = "id")
    private Ad ad;

    @Column(name = "year_of_release")
    @NonNull
    private int yearOfRelease;

    @Column(name = "mark")
    @NonNull
    private String mark;

    @Column(name = "model")
    @NonNull
    private String model;

    @Column(name = "condition")
    @NonNull
    private Condition condition;

    @OneToOne(cascade = jakarta.persistence.CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @NonNull
    private User user;

    @OneToMany(mappedBy = "car", fetch = FetchType.EAGER)
    @Cascade({CascadeType.PERSIST, CascadeType.SAVE_UPDATE})
    private List<Image> images;

    @Override
    public String toString() {
        return "Car{" +
                "ad_id=" + ad.getId() +
                ", yearOfRelease=" + yearOfRelease +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                ", condition=" + condition +
                ", user=" + user.getId() +
                ", images=" + images +
                '}';
    }

    @Override
    public int getId() {
        return ad.getId();
    }
}

