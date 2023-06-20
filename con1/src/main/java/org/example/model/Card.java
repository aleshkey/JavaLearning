package org.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "card")
public class Card implements Serializable, Model {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner", referencedColumnName = "id")
    private Person owner;

    @Column(name = "number")
    private String number;

    @Override
    public int getId() {
        return owner.getId();
    }
}
