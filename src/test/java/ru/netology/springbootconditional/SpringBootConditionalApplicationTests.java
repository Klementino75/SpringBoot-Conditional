package ru.netology.springbootconditional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DisplayName("Checking logic dev/prod")
@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootConditionalApplicationTests {
    @Autowired
    private TestRestTemplate restTemplate;
    @Container
    private final static GenericContainer<?> containerDev = new GenericContainer<>("devapp:latest")
            .withExposedPorts(8080);
    @Container
    private final static GenericContainer<?> containerProd = new GenericContainer<>("prodapp:latest")
            .withExposedPorts(8081);

    @DisplayName("Test dev")
    @Test
    void contextLoadsDev() {
        final String expected = "Current profile is dev.";
        ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:" +
                containerDev.getMappedPort(8080) + "/profile", String.class);

//        System.out.println(result.getBody());
        Assertions.assertEquals(expected, result.getBody());
        System.out.println("Test Ok!\n->" + expected + " = " + result.getBody());
    }

    @DisplayName("Test prod")
    @Test
    void contextLoadsProd() {
        final String expected = "Current profile is production.";
        ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:" +
                containerProd.getMappedPort(8081) + "/profile", String.class);

//        System.out.println(result.getBody());
        Assertions.assertEquals(expected, result.getBody());
        System.out.println("Test Ok!\n->" + expected + " = " + result.getBody());
    }
}