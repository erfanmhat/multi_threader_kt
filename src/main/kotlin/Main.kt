import kotlinx.coroutines.*

fun main(args: Array<String>) {
    println("start")
    val startIndex: Int
    val endIndex: Int
    try {
        startIndex = args[1].toInt()
        endIndex = args[2].toInt()
    } catch (e: Exception) {
        println("enter a valid range")
        return
    }
    val endedList = mutableListOf<Int>()
    for (index in startIndex..endIndex) {
        CoroutineScope(Dispatchers.Default).launch {
            println("$index started...")
            println("python ${args[0]} $index".runCommand())
            println("$index end.")
            endedList.add(index)
        }
    }
    println("run blocking")
    runBlocking {
        while (endIndex - startIndex != endedList.size) delay(1000)
        delay(5000)
    }
}