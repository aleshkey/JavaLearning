package com.shop.carshop.model;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Entity
@Table(name = "ads")
public class Ad implements Serializable, Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(mappedBy = "ad", fetch = FetchType.EAGER)
    @Cascade({CascadeType.ALL})
    @NonNull
    private Car car;

    @Column(name = "date_of_last_update")
    private Date dateOfLastUpdate;

    @Column(name = "date_of_creation")
    private Date dateOfCreation;

    @Version
    private Integer version;
}
