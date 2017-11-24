import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import org.apache.commons.cli.DefaultParser
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
    val cmd = DefaultParser().parse(o, args)
    val mapper = ObjectMapper(YAMLFactory())
    val config = mapper.readValue(File(cmd.getOptionValue("c", "config.yaml")), Map::class.java)
    val iterations = cmd.getOptionValue("n", "100").toInt()
    val rand = Random(cmd.getOptionValue("s", Random().nextLong().toString()).toLong())
    File(cmd.getOptionValue("o", "result.txt")).writer().use { w ->
        val categories = config["categories"] as Map<String, List<String>>
        val sequences = config["sequences"] as Map<String, List<String>>
        val sentences = config["sentences"] as Map<String, List<String>>
        for (i in 0..iterations) {
            val rule = sentences.values.toList()[rand.nextInt(sentences.size)]
            for (token in rule) {
                w.write(getWord(token, categories, sequences, rand))
            }
        }
    }
}

fun getWord(token: String, categories: Map<String, List<String>>, rules: Map<String, List<String>>, r: Random): String {
    val category = categories[token]
    val sequence = rules[token]
    return when {
        category != null -> (getWord(getAnyFromCategory(token, categories, r), categories, rules, r))
        sequence != null -> sequence.map { getWord(it, categories, rules, r) }.joinToString(separator = "")
        else -> token
    }
}

fun getAnyFromCategory(token: String, categories: Map<String, List<String>>, r: Random): String {
    val result = ArrayList<String>()
    flatten(token, result, categories)
    return result.get(r.nextInt(result.size))
}

fun flatten(token: String, result: MutableList<String>, categories: Map<String, List<String>>) {
    val category = categories[token]
    if (category == null) result.add(token)
    else category.forEach { flatten(it, result, categories) }
}