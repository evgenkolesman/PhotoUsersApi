package ru.koleson.photousersapi.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;

@RestController
@RequestMapping("/users")
public class UserController {

//    @GetMapping("/status/check")
//    public Image status() throws IOException {
//        return ImageIO.read(new URL("https://ya.cc/t/a6Ih_UVx37iLP5"));
//    }

    @GetMapping("/status/check")
    public String status() {
        return "Api is working";
    }
}
