package com.shop.carshop.model;

import lombok.*;
import org.json.simple.JSONObject;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "phones")
public class Phone {
    public Phone(Phone phone, User user){
        this.id = phone.getId();
        this.number = phone.getNumber();
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @NonNull
    private User user;

    @Column(name = "number")
    @NonNull
    private String number;

    @Override
    public String toString() {
        JSONObject object = new JSONObject();
        object.put("number", number);
        return String.valueOf(object);
    }
}
