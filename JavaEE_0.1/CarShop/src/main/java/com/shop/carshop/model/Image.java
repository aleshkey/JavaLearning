package com.shop.carshop.model;


import lombok.*;
import org.json.simple.JSONObject;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "images")
public class Image {
    public Image(Image image, Car car){
        this.id = image.getId();
        this.url = image.getUrl();
        this.car = car;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "car_id", referencedColumnName = "ad_id")
    @NonNull
    private Car car;

    @Column(name = "url")
    @NonNull
    private String url;

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("url", url);
        return object.toString();
    }
}
