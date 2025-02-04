package com.factoriaF5.cukies.model;

import jakarta.persistence.*;
import lombok.*;

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
    private String imageUrl;
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

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    public Product(String name, double price, String imageUrl, String description, boolean featured) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.description = description;
        this.featured = featured;
    }

    public Product(String name, double price, String description, String imageUrl, boolean featured, Category category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.featured = featured;
        this.category = category;
    }
}