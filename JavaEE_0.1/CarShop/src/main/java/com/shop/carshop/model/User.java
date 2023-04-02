package com.shop.carshop.model;


import lombok.*;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(mappedBy = "user")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Car car;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "email")
    @NonNull
    private String email;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @Cascade({ org.hibernate.annotations.CascadeType.PERSIST})
    private List<Phone> phones;
}
