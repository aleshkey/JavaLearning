package com.shop.carshop.model;

import lombok.*;
import org.json.simple.JSONObject;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phones")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User user;

    @Column(name = "number")
    private String number;

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("number", number);
        return String.valueOf(object);
    }
}
