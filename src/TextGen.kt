import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import org.apache.commons.cli.GnuParser
import org.apache.commons.cli.Options
import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val o = Options().apply {
        addOption("c", "configuration")
        addOption("o", "output file")
        addOption("n", "number of iteration")
    }
    val cmd = GnuParser().parse(o, args)
    val mapper = ObjectMapper(YAMLFactory())
    val config = mapper.readValue(File(cmd.getOptionValue("c")), Map::class.java)
    File(cmd.getOptionValue("o")).writer().use { w ->
        val categories = config["categories"] as Map<String, List<String>>
        val rules = config["rules"] as List<String>
        for (rule in rules) {
            for (token in rules) {
                w.write(" ")
                val category = categories[token]
                if (category != null)
                    w.write(getWord(category))
                else
                    w.write(token)
            }
            w.write(". ")
        }
    }
}

fun getWord(words: List<String>): String {
    return words[Random().nextInt(words.size)]
}