package com.shop.carshop.models;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
public class User {
    private int ID;

    private String name;

    private String email;
}
