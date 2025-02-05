package com.factoriaF5.cukies.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "image_name")
    private String name;

    @Column(name = "image_url")
    private String url;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Image(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public Image(String name, String url, Product product) {
        this.name = name;
        this.url = url;
        this.product = product;
    }

}
