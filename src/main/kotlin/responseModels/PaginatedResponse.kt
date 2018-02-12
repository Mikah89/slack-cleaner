package responseModels

open class PaginatedResponse(ok: Boolean, val count: Int, val total: Int, val page: Int, val pages: Int): Response(ok)