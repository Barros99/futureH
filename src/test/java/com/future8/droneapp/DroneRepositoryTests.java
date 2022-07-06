// package com.future8.droneapp;

// import com.future8.droneapp.model.Drone;
// import com.future8.droneapp.repository.DroneRepository;
// import org.junit.Test;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.DisplayName;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.testcontainers.containers.MySQLContainer;
// import org.testcontainers.junit.jupiter.Container;
// import org.testcontainers.junit.jupiter.Testcontainers;

// @Testcontainers
// public class DroneRepositoryTests {
//   @Autowired private DroneRepository droneRepository;

//   // @Container
//   // private static MySQLContainer mySqlDB =
//   //     new MySQLContainer("mysql:5.5").withUsername("root").withPassword("root");

//   // @DynamicPropertySource
//   // static void databaseProperties(DynamicPropertyRegistry registry) {
//   //   registry.add("spring.datasource.url", mySqlDB::getJdbcUrl);
//   //   registry.add("spring.datasource.username", mySqlDB::getUsername);
//   //   registry.add("spring.datasource.password", mySqlDB::getPassword);
//   //   registry.add("flyway.user", () -> "root");
//   //   registry.add("flyway.password", mySqlDB::getPassword);
//   // }
//   @Container private static final MySQLContainer MY_SQL_CONTAINER = new MySQLContainer();
//   // @Container
//   // public static MySQLContainer<?> mySqlDB =
//   //     new MySQLContainer<>("mysql:5.5")
//   //         .withDatabaseName("future8")
//   //         .withUsername("root")
//   //         .withPassword("root")
//   //         .withExposedPorts(8080)
//   //         .waitingFor(Wait.forListeningPort());

//   // @DynamicPropertySource
//   // public static void properties(DynamicPropertyRegistry registry) {
//   //   registry.add("spring.datasource.url", mySqlDB::getJdbcUrl);
//   //   registry.add("spring.datasource.username", mySqlDB::getUsername);
//   //   registry.add("spring.datasource.password", mySqlDB::getPassword);
//   // }

//   @AfterEach
//   void cleanUp() {
//     this.droneRepository.deleteAll();
//   }

//   Drone droneMock = new Drone(1231321, 5656565);

//   @DisplayName("1 - Deve cadastrar um novo produto com sucesso.")
//   @Test
//   public void DeveCadastrarNovoproduto() {
//     Drone produto = droneRepository.save(new Drone(15151, 46545));
//     Assertions.assertNotNull(produto);
//   }
// }
