import com.beust.klaxon.Klaxon
import khttp.get

val API_TOKEN = ""
val FILE_LIST_API = "https://slack.com/api/files.list"

fun main(args: Array<String>) {
    // read parameters from console

    // process and invoke the requested operation
    listSlackFiles()
}

fun listSlackFiles() {
    println(get(FILE_LIST_API, params = mapOf("token" to API_TOKEN, "pretty" to "1")).text)
}