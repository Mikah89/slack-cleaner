package responseModels

import models.SlackFile

class ListFilesResponse(val ok: Boolean, val files: List<SlackFile>, val paging: PaginatedResponse)