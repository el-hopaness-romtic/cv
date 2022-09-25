import kotlin.math.pow


data class Point(val x: Double = .0, val y: Double = .0) {
    fun distance(other: Point) = ((x - other.x).pow(2) + (y - other.y).pow(2)).pow(0.5)
}
