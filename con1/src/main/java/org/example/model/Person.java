package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = false)
@Entity
@Table(name = "people")
public class Person implements Serializable, Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private int value;

    @ToString.Exclude
    @OneToMany(mappedBy = "owner")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Card> card;
}
