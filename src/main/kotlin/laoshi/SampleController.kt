package laoshi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController {
    @GetMapping("/category/{name}")
    fun getCategoryBodyByName(@PathVariable name: String): String {
        // TODO("Implement")
        println(name)
        return "$name:[我，你，他，她]"
    }
}