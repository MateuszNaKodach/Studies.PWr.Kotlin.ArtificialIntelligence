import kotlin.system.measureTimeMillis

/**
 * Method to calculate time of process and print it
 * @param function function witch execution time we count
 */
fun measure(function: () -> Unit) {
    val millis = measureTimeMillis { function() }
    println("Millis: $millis")
}
