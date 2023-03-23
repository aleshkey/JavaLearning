package com.shop.carshop.model;


import lombok.*;
import org.json.simple.JSONObject;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @Column(name = "url")
    private String url;

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("imageURL", url);

        return String.valueOf(object);
    }
}
