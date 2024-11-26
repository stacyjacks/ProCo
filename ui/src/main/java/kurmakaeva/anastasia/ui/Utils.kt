package kurmakaeva.anastasia.ui

fun goalString(current: Float): Int {
    return when {
        current == 0.0f || current.isNaN() -> R.string.empty
        current < 0.5f -> R.string.progressCheerFirst
        current >= 0.5f && current < 0.75f -> R.string.progressCheerSecond
        current >= 0.75f && current < 1.0f -> R.string.progressCheerThird
        else -> R.string.progressCheerFinish
    }
}