package compose.project.chrysalis

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform