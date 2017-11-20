import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import org.apache.commons.cli.BasicParser
import org.apache.commons.cli.Options
import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val o = Options().apply {
        addOption("c", true, "configuration")
        addOption("o", true, "output file")
        addOption("n", true, "number of iteration")
        addOption("s", true, "seed")
    }
    val cmd = BasicParser().parse(o, args)
    val mapper = ObjectMapper(YAMLFactory())
    val config = mapper.readValue(File(cmd.getOptionValue("c", "config.yaml")), Map::class.java)
    val iterations = cmd.getOptionValue("n", "100").toInt()
    val rand = Random(cmd.getOptionValue("s", Random().nextLong().toString()).toLong())
    File(cmd.getOptionValue("o", "result.txt")).writer().use { w ->
        val categories = config["categories"] as Map<String, List<String>>
        val rules = config["rules"] as Map<String, List<String>>
        for (i in 0..iterations) {
            val rule = rules.entries.toList()[rand.nextInt(rules.size)]
            for (token in rule.value) {
                w.write(getWord(token, categories, rules, rand))
            }
        }
    }
}


fun getWord(token: String, categories: Map<String, List<String>>, rules: Map<String, List<String>>, r: Random): String {
    val category = categories[token]
    val rule = rules[token]
    return when {
        category != null -> getWord(category[r.nextInt(category.size)], categories, rules, r)
        rule != null -> rule.map { getWord(it, categories, rules, r) }.joinToString(separator = "")
        else -> token
    }
}


