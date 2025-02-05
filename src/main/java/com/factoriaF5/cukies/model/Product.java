package com.factoriaF5.cukies.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;
    private double price;
    private String description;
    private boolean featured;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "product_customer",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Customer> customers;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private Image image;

    public Product(String name, double price, boolean featured) {
        this.name = name;
        this.price = price;
        this.featured = featured;
    }

    public Product(String name, double price, String description, boolean featured, Image image) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.featured = featured;
        this.image = image;
    }

    public Product(String name, double price, String description, boolean featured, Category category, Image image) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.featured = featured;
        this.category = category;
        this.image = image;
    }
}