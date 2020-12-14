package com.example.SpringSecurityHW8.utils;

import com.example.SpringSecurityHW8.entities.*;
import com.example.SpringSecurityHW8.repositories.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class SampleData {

    private UserRepository userRepository;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    private OrderRepository orderRepository;
    private RoleRepository roleRepository;

    public SampleData(
            UserRepository userRepository,
            ProductRepository productRepository,
            OrderRepository orderRepository,
            CategoryRepository categoryRepository,
            RoleRepository roleRepository
    ) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.categoryRepository = categoryRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {

        Category category1 = new Category("redFruit", "Red fruit");
        Category category2 = new Category("yellowFruit", "Yellow Fruit");
        Category category3 = new Category("other", "Other");

        Product product1 = new Product("Orange", "Brand Orange", 50., "orange.jpg", category1);
        Product product2 = new Product("Lemon", "Brand Lemon", 70., "lemon.jpg", category2);
        Product product3 = new Product("Lime", "Brand Lime", 20., "lime.jpg", category3);
        Product product4 = new Product("Mango", "Brand Mango", 110., "mango.jpg", category1);
        Product product5 = new Product("Apple", "Brand Apple", 95., "apple.jpg", category1);
        Product product6 = new Product("Pineapple", "Brand Pineapple", 76., "pineapple.jpg", category2);
        Product product7 = new Product("Avocado", "Brand Avocado", 123., "avocado.jpg", category3);
        Product product8 = new Product("Strawberry", "Brand Strawberry", 125., "strawberry.jpg", category3);

        Role role1 = new Role("ROLE_USER");
        Role role2 = new Role("ROLE_ADMIN");
        Role role3 = new Role("ROLE_SUPER_ADMIN");
        Role role4 = new Role("ROLE_MANAGER");

        User user1 = new User("Alex", "test@test1.com", "79000000001", new Date(), "Russia, SPb, Leninskiy street 10-10", "something");
        user1.setUsername("alex");

        user1.setPassword("$2y$12$kppL/79H63sx3NoXlZhY/uDW2EiB18ByX8YeENyFwyxAnHjrCT4pK"); // 11
        user1.getRoles().add(role1);
        user1.getRoles().add(role2);
        boolean add = user1.getRoles().add(role3);

        User user2 = new User("Alena", "test@test2.com", "79000000002", new Date(), "Russia, Msk, Leninskiy street 01-01", "something else");
        user2.setUsername("alena");
        user2.setPassword("$2y$12$kppL/79H63sx3NoXlZhY/uDW2EiB18ByX8YeENyFwyxAnHjrCT4pK");
        user2.getRoles().add(role1);


        User user3 = new User("Vasya", "vasya@test2.com", "79030000034", new Date(), "Russia, Msk,Tveskya,1/12", "tsar");
        user3.setUsername("vasya");
        user3.setPassword("{bcrypt}$2y$12$kppL/79H63sx3NoXlZhY/uDW2EiB18ByX8YeENyFwyxAnHjrCT4pK");
        user3.getRoles().add(role1);
        user3.getRoles().add(role4);


        User user4 = new User("Ivan", "vanyat@test2.com", "79030000035", new Date(), "Russia, Msk,Flotskya,10/122", "tsar");
        user4.setUsername("ivvan");
        user4.setPassword("$2y$12$kppL/79H63sx3NoXlZhY/uDW2EiB18ByX8YeENyFwyxAnHjrCT4pK");
        user4.getRoles().add(role1);
        user4.getRoles().add(role4);

        User user5 = new User("John", "yankyt@test2.com", "+191830000002", new Date(), "USA, Miami,Florida,Biscane bld", "");
        user5.setUsername("john");
        user5.setPassword("$2y$12$kppL/79H63sx3NoXlZhY/uDW2EiB18ByX8YeENyFwyxAnHjrCT4pK");
        user5.getRoles().add(role1);

        User user6 = new User("Billy", "okt@test2.com", "+19183000020", new Date(), "USA, Miami,Florida,Biscane bld", "");
        user6.setUsername("billy");
        user6.setPassword("$2y$12$kppL/79H63sx3NoXlZhY/uDW2EiB18ByX8YeENyFwyxAnHjrCT4pK");
        user6.getRoles().add(role1);

        User user7 = new User();
        user7.setUsername("somebody");
        user7.setName("somebody");
        user7.getRoles().add(role1);


        Order order1 = new Order();
        order1.setTotalPrice(product1.getPrice());
        order1.setUser(user1);

        order1.setCode("0001");

        Order order2 = new Order();
        order2.setTotalPrice(product2.getPrice());
        order2.setUser(user2);

        order2.setCode("0002");

        OrderEntry orderEntry1 = new OrderEntry(product1, order1);
        OrderEntry orderEntry2 = new OrderEntry(product2, order1);
        OrderEntry orderEntry3 = new OrderEntry(product3, order1);
        OrderEntry orderEntry4 = new OrderEntry(product4, order2);
        OrderEntry orderEntry5 = new OrderEntry(product5, order2);

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
        productRepository.save(product6);
        productRepository.save(product7);
        productRepository.save(product8);

        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);
        roleRepository.save(role4);


        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);
        userRepository.save(user7);

        orderRepository.save(order1);
        orderRepository.save(order2);
    }
}