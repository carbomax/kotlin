import kotlinx.coroutines.*
import java.io.File

fun main(args: Array<String>) = runBlocking {

    val file1 = "/home/luis/Escritorio/work/config/business_target_by_day_old"
    val file2 = "/home/luis/Escritorio/work/config/business_target_by_day"
    val hasSameOrder: Boolean? = null
    val linesRead = readFilesLines(file1, file2)

    val lines1 = linesRead["lines1"]
    val lines2 = linesRead["lines2"]

        checkSize(lines1, lines2)

    when (hasSameOrder) {
        true -> withSameOrder(lines1!!, lines2!!)
        false -> withoutOrder(lines1!!, lines2!!)
        else -> {
            withBothOrderTypes(lines1, lines2)
        }
    }

}

private fun withBothOrderTypes(
    lines1: List<String>?,
    lines2: List<String>?
) {
    withSameOrder(lines1!!, lines2!!)
    withoutOrder(lines1, lines2)
}

private fun checkSize(lines1: List<String>?, lines2: List<String>?) {
    println("*******Checking size **********")
    val size1 = lines1?.size
    val size2 = lines2?.size
    if (size1 == size2) {
        println("Lines1: $size1, lines2 $size2")
    } else {
        throw RuntimeException("Invalid size Lines1 => $size1, lines2 => $size2")
    }
    println("******* Checking size finished successfully **********")
}

private fun withoutOrder(
    lines1: List<String>,
    lines2: List<String>
) {
    println("******* Checking without order **********")
    lines1.forEach { line1 ->
        if (lines2.firstOrNull { line1 == it } == null) {
            throw RuntimeException("Error with line $line1")
        }
    }
    println("******* Checking without order finished successfully **********")
}

private fun withSameOrder(
    lines1: List<String>,
    lines2: List<String>
) {
    println("******* Checking with same order **********")
    (0..lines1.size).forEach { index ->
        val lines1Item = lines1.getOrNull(index)
        val lines2Item = lines2.getOrNull(index)
        if (lines1Item != lines2Item) {
            throw RuntimeException("The item does not match  lines1 => $lines1Item, lines2 => $lines2Item")
        }
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
