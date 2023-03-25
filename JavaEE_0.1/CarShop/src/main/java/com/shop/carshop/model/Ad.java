package com.shop.carshop.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ads")
public class Ad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @OneToOne(mappedBy = "ad")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.REMOVE, CascadeType.REFRESH})
    private Car car;

    @Column(name = "date_of_last_update")
    private String dateOfLastUpdate;

    @Column(name = "date_of_creation")
    private String dateOfCreation;

}
