data class SlackFile(val name: String, val size: Long) {
    fun toKilobytes(): Long {
        return size / 1024
    }

    fun toMegabytes(): Long {
        return toKilobytes() / 1024
    }
}