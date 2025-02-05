package com.factoriaF5.cukies.config;

import com.factoriaF5.cukies.model.Category;
import com.factoriaF5.cukies.model.Customer;
import com.factoriaF5.cukies.model.Image;
import com.factoriaF5.cukies.model.Product;
import com.factoriaF5.cukies.repository.CategoryRepository;
import com.factoriaF5.cukies.repository.CustomerRepository;
import com.factoriaF5.cukies.repository.ImageRepository;
import com.factoriaF5.cukies.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBInitializer {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final ImageRepository imageRepository;


    public DBInitializer(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            CustomerRepository customerRepository,
            ImageRepository imageRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.imageRepository = imageRepository;
    }

    @PostConstruct
    public void initializer() {
        initCategories();
        initProducts();
        initCustomers();
    }

    public void initCategories() {
        Category category1 = new Category("stationery");
        categoryRepository.save(category1);

        Category category2 = new Category("accessories");
        categoryRepository.save(category2);

        Category category3 = new Category("plush toys");
        categoryRepository.save(category3);

        Category category4 = new Category("home");
        categoryRepository.save(category4);

        Category category5 = new Category("clothes");
        categoryRepository.save(category5);

        Category category6 = new Category("beauty & skincare");
        categoryRepository.save(category6);

        Category category7 = new Category("fidget toys");
        categoryRepository.save(category7);

        Category category8 = new Category("tech accessories");
        categoryRepository.save(category8);

        Category category9 = new Category("anime merchandise");
        categoryRepository.save(category9);

        Category category10 = new Category("lifestyle & gadgets");
        categoryRepository.save(category10);
    }

    public void initProducts() {
        Category stationery = categoryRepository.getById(1);
        Category accessories = categoryRepository.getReferenceById(2);
        Category plush_toys = categoryRepository.getById(3);
        Category clothes = categoryRepository.getById(4);
        Category lifestyle_gadgets = categoryRepository.getById(10);

        Product product1 = new Product(
                "Kawaii Cat Pen Set",
                15.99,
                true
        );
        product1.setCategory(stationery);
        productRepository.save(product1);
        Image image1 = new Image("Image 1", "https://picsum.photos/200/300?random=1", product1);
        imageRepository.save(image1);

        Product product2 = new Product(
                "Bunny Sticky Note",
                6.50,
                false
        );
        product2.setCategory(stationery);
        productRepository.save(product2);
        Image image2 = new Image("Image 3", "https://picsum.photos/200/300?random=3", product2);
        imageRepository.save(image2);

//        Product product3 = new Product(
//                "Panda Pencil Case",
//                12.99,
//                "Estuche de lápices con diseño de panda, ideal para mantener tus herramientas de escritorio organizadas y con estilo.",
//                true,
//                stationery,
//                List.of(image1, image2)
//        );
//        productRepository.save(product3);
//
//        Product product4 = new Product(
//                "Sakura Blossom Necklace",
//                18.00,
//                "Collar delicado con un colgante en forma de flor de sakura, perfecto para añadir un toque floral a tu look.",
//                true,
//                accessories,
//                List.of(image1, image2)
//        );
//        productRepository.save(product4);
//
//        Product product5 = new Product(
//                "Kawaii Cat Earrings",
//                10.50,
//                "Pendientes pequeños y adorables con forma de gato, para aquellos que quieren llevar lo kawaii en sus orejas.",
//                false,
//                accessories,
//                List.of(image1, image2)
//        );
//        productRepository.save(product5);
   }

    public void initCustomers() {
        Customer customer1 = new Customer(
                "sakuraLover",
                "sakura.lover@example.com",
                "password123"
        );
        customerRepository.save(customer1);

        Customer customer2 = new Customer(
                "kawaiiFan88",
                "kawaii.fan88@example.com",
                "supersecret"
        );
        customerRepository.save(customer2);

        Customer customer3 = new Customer(
                "pandaAdmirer",
                "panda.admirer@example.com",
                "pandapower"
        );
        customerRepository.save(customer3);

        Customer customer4 = new Customer(
                "bunnyGirl99",
                "bunny.girl99@example.com",
                "fluffylove"
        );
        customerRepository.save(customer4);

        Customer customer5 = new Customer(
                "momoChan",
                "momo.chan@example.com",
                "sweetmomo"
        );
        customerRepository.save(customer5);

    }

}
