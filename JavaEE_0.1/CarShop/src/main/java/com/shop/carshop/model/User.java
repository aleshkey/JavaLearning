package com.shop.carshop.model;


import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "User")
public class User {
    @Id
    @OneToOne(mappedBy = "user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "owner")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Phone> phones;
}
