package com.shop.carshop.model;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.json.simple.JSONObject;

import jakarta.persistence.*;

import java.io.Serializable;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "phones")
public class Phone implements Serializable, Model{
    public Phone(Phone phone, User user){
        this.id = phone.getId();
        this.number = phone.getNumber();
        this.user = user;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
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
