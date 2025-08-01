package com.ecommerce;

import com.ecommerce.config.DotenvLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EcommerceApplicationTests {

    @BeforeAll
    public static void setup() {
        DotenvLoader.loadEnv();  //Test başlamadan .env yükleniyor
    }

    @Test
    void contextLoads() {
        // Uygulama context sorunsuz başlatılabiliyor mu testi
    }
}
