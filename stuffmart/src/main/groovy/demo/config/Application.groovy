package demo.config

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.ServletComponentScan

@SpringBootApplication
@ServletComponentScan(basePackages=['demo.filter'])
class Application {

    static void main(String[] args) {
        SpringApplication.run(Application.class, args)
    }

}