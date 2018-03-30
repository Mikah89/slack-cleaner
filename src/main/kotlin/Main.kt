import com.beust.klaxon.Klaxon
import khttp.get
import models.SlackFile
import responseModels.ListFilesResponse
import java.io.File

val API_TOKEN_FILE = "conf/apiKey"
val FILE_LIST_API = "https://slack.com/api/files.list"

fun main(args: Array<String>) {
    // read API TOKEN from file
    val api_token = readFile(API_TOKEN_FILE)

    // process and invoke the requested operation
    if(args.isNotEmpty()) {
        when (args[0]) {
            "usedspace" -> {
                val slackFiles = listSlackFiles(api_token)
                println("You are using " + calculateTotalSizeInMb(slackFiles) + " MBytes.")
            }

            "deleteold" -> {
                // TODO to be done
            }
        }
    }


}

fun readFile(path: String): String {
    val fileContent = File(path).readLines()
    // file should only have one line with the key
    return fileContent[0]
}

fun calculateTotalSizeInMb(files: List<SlackFile>): Long {
    return files.sumByDouble { f -> f.toMegabytes().toDouble() }.toLong()
}

/**
 * @param page
 */
fun listSlackFiles(apiToken: String, page: Int = 1, count: Int = 100): List<SlackFile> {

    // Fetch pagination information
    val paginationInfo = makePaginatedRequest<ListFilesResponse>(FILE_LIST_API, apiToken, page, count)
    val pages = paginationInfo?.paging?.pages ?: 0

    val result = mutableListOf<SlackFile>()
    IntRange(1, pages).map { idx ->
        makePaginatedRequest<ListFilesResponse>(FILE_LIST_API, apiToken, idx)?.files?.map { f -> result.add(f) }
    }

    return result
}

inline fun <reified T> makePaginatedRequest(endPoint: String, apiToken: String, page: Int = 1, count: Int = 100): T? {
    val serverResponse = get(
            endPoint,
            params = mapOf(
                    "token" to apiToken,
                    "pretty" to "1",
                    "page" to page.toString(),
                    "count" to count.toString())
    ).text

    return Klaxon().parse<T>(serverResponse)
}