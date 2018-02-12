import com.beust.klaxon.Klaxon
import khttp.get
import responseModels.ListFilesResponse
import responseModels.PaginatedResponse

val API_TOKEN = ""
val FILE_LIST_API = "https://slack.com/api/files.list"

fun main(args: Array<String>) {
    // read parameters from console

    // process and invoke the requested operation
    val slackFiles = listSlackFiles()

}

/**
 * @param page
 */
fun listSlackFiles(page: Int = 1, count: Int = 100): ListFilesResponse? {

    // Fetch pagination information
    val paginationInfo = makePaginatedRequest(FILE_LIST_API)
    val pages = paginationInfo?.pages ?: 0

    // Fetch all items and concatenate to single list
    // return
    return null
}

fun makePaginatedRequest(endPoint: String, page: Int = 1, count: Int = 100): PaginatedResponse? {
    // TODO isolate remote call in another function.
    val serverResponse = get(
            endPoint,
            params = mapOf(
                    "token" to API_TOKEN, // TODO improve this global usage... to not use it.
                    "pretty" to "1",
                    "page" to page.toString(),
                    "count" to count.toString())
    ).text
    return Klaxon().parse<PaginatedResponse>(serverResponse)
}