package laoshi

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CategoryController {
    @GetMapping("/category/{name}")
    fun getCategoryBodyByName(@PathVariable name: String): String {
        // TODO("Implement")
        println(name)
        return "我，你，他，她"
    }

    @GetMapping("/all_categories")
    fun getAllCategories() : String {
        return ObjectMapper().writeValueAsString(
                listOf("Jobs", "Fruits", "Pronouns", "Places", "Actions", "Jobs", "Fruits", "Pronouns", "Places", "Actions", "Jobs", "Fruits", "Pronouns", "Places", "Actions", "Jobs", "Fruits", "Pronouns", "Places", "Actions", "Jobs", "Fruits", "Pronouns", "Places", "Actions"))
    }
}