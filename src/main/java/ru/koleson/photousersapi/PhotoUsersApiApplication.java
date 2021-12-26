package ru.koleson.photousersapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PhotoUsersApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhotoUsersApiApplication.class, args);
    }

}
