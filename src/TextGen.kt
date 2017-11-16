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
    val config = mapper.readValue(File(cmd.getOptionValue("c")), Map::class.java)
    val iterations = cmd.getOptionValue("n").toInt()
    val rand = Random(cmd.getOptionValue("s").toLong())
    File(cmd.getOptionValue("o")).writer().use { w ->
        val categories = config["categories"] as Map<String, List<String>>
        val rules = config["rules"] as List<List<String>>
        for (i in 0..iterations) {
            val rule = rules.get(rand.nextInt(rules.size))
            for (token in rule) {
                val category = categories[token]
                if (category != null)
                    w.write(category[rand.nextInt(category.size)])
                else
                    w.write(token)
            }
            w.write("。")
        }
    }
}

