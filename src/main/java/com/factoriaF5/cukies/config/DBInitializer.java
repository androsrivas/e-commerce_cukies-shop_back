package com.factoriaF5.cukies.config;

import com.factoriaF5.cukies.model.Category;
import com.factoriaF5.cukies.model.Customer;
import com.factoriaF5.cukies.model.Product;
import com.factoriaF5.cukies.repository.CategoryRepository;
import com.factoriaF5.cukies.repository.CustomerRepository;
import com.factoriaF5.cukies.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DBInitializer {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;


    public DBInitializer(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
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
                "Set de bolígrafos con dibujos adorables de gatos, perfecto para los amantes del kawaii.",
                "https://example.com/kawaii-cat-pen-set.jpg",
                true,
                stationery
        );
        productRepository.save(product1);

        Product product2 = new Product(
                "Bunny Sticky Note",
                6.50,
                "Notas adhesivas con forma de conejito, para añadir un toque dulce a tus tareas diárias.",
                "https://example.com/bunny-sticky-note.jpg",
                false,
                stationery
        );
        productRepository.save(product2);

        Product product3 = new Product(
                "Panda Pencil Case",
                12.99,
                "Estuche de lápices con diseño de panda, ideal para mantener tus herramientas de escritorio organizadas y con estilo.",
                "https://example.com/panda-pencil-case.jpg",
                true,
                stationery
        );
        productRepository.save(product3);

        Product product4 = new Product(
                "Sakura Blossom Necklace",
                18.00,
                "Collar delicado con un colgante en forma de flor de sakura, perfecto para añadir un toque floral a tu look.",
                "https://example.com/sakura-blossom-necklace.jpg",
                true,
                accessories
        );
        productRepository.save(product4);

        Product product5 = new Product(
                "Kawaii Cat Earrings",
                10.50,
                "Pendientes pequeños y adorables con forma de gato, para aquellos que quieren llevar lo kawaii en sus orejas.",
                "https://example.com/kawaii-cat-earrings.jpg",
                false,
                accessories
        );
        productRepository.save(product5);

        Product product6 = new Product(
                "Unicorn Hair Clip",
                7.25,
                "Pinza para el pelo con diseño de unicornio, para añadir un toque mágico y colorido a tu peinado.",
                "https://example.com/unicorn-hair-clip.jpg",
                true,
                accessories
        );
        productRepository.save(product6);

        Product product7 = new Product(
                "Mochi Plush Bear",
                22.00,
                "Manta de peluche suave y resistente, con diseño de oso, ideal para abrazar o decorar tu habitación.",
                "https://example.com/mochi-plush-bear.jpg",
                true,
                plush_toys
        );
        productRepository.save(product7);

        Product product8 = new Product(
                "Kawaii Alpaca Plush",
                19.50,
                "Un peluche de alpaca con una cara sonriente encantadora, perfecta para regalar o decorar.",
                "https://example.com/kawaii-alpaca-plush.jpg",
                false,
                plush_toys
        );
        productRepository.save(product8);

        Product product9 = new Product(
                "Kitsune Plush Toy",
                25.99,
                "Peluche suave y acogedor en forma de kitsune (zorrito), una mascota dulce y encantadora para cualquier coleccionista.",
                "https://example.com/kitsune-plush-fox.jpg",
                true,
                plush_toys
        );
        productRepository.save(product9);

        Product product10 = new Product(
                "Kawaii Cat Hoodie",
                32.00,
                "Sudadera con capucha de estilo kawaii con una impresión de gato adorable. Perfecta para esos días fríos.",
                "https://example.com/kawaii-cat-hoodie.jpg",
                true,
                clothes
        );
        productRepository.save(product10);

        Product product11 = new Product(
                "Sakura Kimono Robe",
                45.00,
                "Kimono tradicional japonés con diseño floral de sakura, ideal para un look elegante y relajado.",
                "https://example.com/sakura-kimono-robe.jpg",
                false,
                clothes
        );
        productRepository.save(product11);

        Product product12 = new Product(
                "Bunny Pajama Set",
                28.50,
                "Pijama con diseño de conejito, súper cómodo y ideal para descansar con estilo kawaii.",
                "https://example.com/bunny-pajama-set.jpg",
                true,
                clothes
        );
        productRepository.save(product12);

        Product product13 = new Product(
                "Mini Cat USB Charger",
                12.99,
                "Cargador USB para dispositivos con diseño de gato, compacto y práctico para llevar a cualquier lugar.",
                "https://example.com/mini-cat-usb-charger.jpg",
                true,
                lifestyle_gadgets
        );
        productRepository.save(product13);

        Product product14 = new Product(
                "Kawaii Cat Alarm Clock",
                20.99,
                "Reloj despertador con diseño de gato, para aquellos que quieren comenzar el día con una sonrisa.",
                "https://example.com/kawaii-cat-alarm-clock.jpg",
                false,
                lifestyle_gadgets
        );
        productRepository.save(product14);

        Product product15 = new Product(
                "Panda Power Bank",
                18.50,
                "Power bank portátil con diseño de panda, para mantener tus dispositivos cargados y con un toque kawaii.",
                "https://example.com/panda-power-bank.jpg",
                true,
                lifestyle_gadgets
        );
        productRepository.save(product15);
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
