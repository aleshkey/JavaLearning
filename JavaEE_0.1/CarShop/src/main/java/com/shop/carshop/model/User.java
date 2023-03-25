package com.shop.carshop.model;


import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(mappedBy = "user")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.REFRESH,CascadeType.REMOVE})
    private Car car;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Phone> phones;
}
