package com.shop.carshop.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Ad")
public class Ad{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToOne(mappedBy = "ad")
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.REMOVE, CascadeType.REFRESH})
    private Car car;

    @Column(name = "date_of_last_update")
    private String dateOfLastUpdate;

    @Column(name = "date_of_creation")
    private String dateOfCreation;

}
