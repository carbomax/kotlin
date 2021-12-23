import kotlinx.coroutines.*
import java.io.File

fun main(args: Array<String>) = runBlocking {

    val file1 = "file1"
    val file2 = "file2"
    val hasSameOrder: Boolean? = null
    val linesRead = readFilesLines(file1, file2)

    val lines1 = linesRead["lines1"]
    val lines2 = linesRead["lines2"]

    checkSize(lines1, lines2)

    when (hasSameOrder) {
        true -> withSameOrder(lines1!!, lines2!!)
        false -> withContains(lines1!!, lines2!!)
        else -> {
            withSameOrderAndContainsCriteria(lines1, lines2)
        }
    }

}

private fun withSameOrderAndContainsCriteria(
    lines1: List<String>?,
    lines2: List<String>?
) {
    withSameOrder(lines1!!, lines2!!)
    withContains(lines1, lines2)
}

private fun checkSize(lines1: List<String>?, lines2: List<String>?) {
    println("*******Checking size **********")
    val size1 = lines1?.size
    val size2 = lines2?.size
    if (size1 == size2) {
        println("lines size => [file1: $size1, file2: $size2]")
    } else {
        throw RuntimeException("Invalid line size:  [file1 => $size1, file2 => $size2]")
    }
    println("******* Checking size finished successfully **********")
}

private fun withContains(
    lines1: List<String>,
    lines2: List<String>
) {
    println("******* Checking with contains **********")
    println("Lines that file1 has and file2 does not has")
    withContainsBetweenFiles(lines1, lines2)

    println("********************************************")

    println("Lines that file2 has and file1 does not has")
    withContainsBetweenFiles(lines2, lines1)

    println("******* Checking with contains finished successfully **********")

}
private fun withContainsBetweenFiles(
    lines1: List<String>,
    lines2: List<String>
) {

    val differences = mutableListOf<String>()
    lines1.forEach { line1 ->
        if (lines2.firstOrNull { line1 == it } == null) {
            differences.add(line1)
        }
    }
    println("Differences => ${differences.size}.")
    if (differences.isNotEmpty()) {
        differences.forEach { println(it) }
    }
}

private fun withSameOrder(
    lines1: List<String>,
    lines2: List<String>
) {
    println("******* Checking with same order **********")
    val differences = mutableMapOf<String, String>()
    (0..lines1.size).forEach { index ->
        val lines1Item = lines1.getOrNull(index)
        val lines2Item = lines2.getOrNull(index)
        if (lines1Item != lines2Item) {
            differences["Line: ${index + 1}"] = "[ file1: $lines1Item ], [ file2: $lines2Item ]"
        }
    }
    println("Differences => ${differences.size}")
    differences.forEach { (t, u) ->
        println("$t $u")
    }
    println("******* Checking with same order finished successfully **********")
}

@OptIn(ExperimentalCoroutinesApi::class)
private suspend fun readFilesLines(path: String, path1: String): Map<String, List<String>>  =  coroutineScope {
    withContext(Dispatchers.IO.limitedParallelism(10)) {
        val lines1 = async {
            File(path).readLines()
        }
        val lines2 = async {
            File(path1).readLines()
        }
        mapOf("lines1" to lines1.await(), "lines2" to lines2.await())
    }
}
