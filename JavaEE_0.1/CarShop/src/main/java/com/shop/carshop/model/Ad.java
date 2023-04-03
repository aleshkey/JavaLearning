package com.shop.carshop.model;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "ads")
public class Ad implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @OneToOne(mappedBy = "ad")
    @Cascade({CascadeType.ALL})
    @NonNull
    private Car car;

    @Column(name = "date_of_last_update")
    private String dateOfLastUpdate;

    @Column(name = "date_of_creation")
    private String dateOfCreation;

}
