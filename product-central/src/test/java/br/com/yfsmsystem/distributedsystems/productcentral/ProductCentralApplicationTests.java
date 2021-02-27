package br.com.yfsmsystem.distributedsystems.productcentral;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductCentralApplicationTests {

    @Test
    public void applicationContextLoadedTest(){
    }

    @Test
    public void applicationStartTest() {
        //you can add your mocks as per your required dependencies and requirements
        ProductCentralApplication.main(new String[] {});
    }
}
