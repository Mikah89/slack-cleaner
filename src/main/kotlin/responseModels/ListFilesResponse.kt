package responseModels

class ListFilesResponse(
        ok: Boolean,
        count: Int,
        total: Int,
        page: Int,
        pages: Int,
        val files: List<SlackFile>)
    : PaginatedResponse(ok, count, total, page, pages)