package laoshi

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
@EnableAutoConfiguration
open class LaoShiApplication

fun main(args: Array<String>) {
    SpringApplication.run(LaoShiApplication::class.java, *args)
}

