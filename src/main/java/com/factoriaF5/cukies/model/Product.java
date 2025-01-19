package com.factoriaF5.cukies.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;
    private double price;
    private String imageUrl;
    private boolean featured;

    public Product(String name, double price, String imageUrl, boolean featured) {
    }


}
