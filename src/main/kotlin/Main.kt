import java.io.File

fun main(args: Array<String>) {

    var file: String = System.getProperty("user.dir") + File.separator + "config"

    if (args.isNotEmpty()) {
        for (fileName in args)
            if (fileName.contains(".txt"))
                file = System.getProperty("user.dir") + File.separator + fileName.substringBefore(".txt")
    }

    readFileLineByLineUsingForEachLine(
        args,
        file
    )
}

fun readFileLineByLineUsingForEachLine(args: Array<String>, fileName: String) = File(fileName).forEachLine {
    println(Cron(args).parseLine(it))
}