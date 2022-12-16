package aoc2022.day15.part2

import kotlin.math.absoluteValue

const val maxSize = 4000000

data class Point(val x: Int, val y: Int)
data class SensorBeacon(val sensor: Point, val beacon: Point) {
    private val viewingDistance = (sensor.x - beacon.x).absoluteValue + (sensor.y - beacon.y).absoluteValue

    fun getCoveredPointsOnLine(line: Int): Interval? {
        val viewingDistance = viewingDistance
        val distanceToLine = (sensor.y - line).absoluteValue
        val maxSpotsToCover = viewingDistance - (sensor.y - line).absoluteValue
        if (maxSpotsToCover < 1) return null
        val from = maxOf(sensor.x - (viewingDistance - distanceToLine), -1)
        val to = minOf(sensor.x + (viewingDistance - distanceToLine), maxSize + 1)
        return Interval(minOf(from, maxSize + 1), maxOf(to, -1))
    }
}

data class Interval(var start: Int, var end: Int) {
    fun intersects(interval: Interval): Boolean {
        return interval.start <= this.end + 1 && this.start <= interval.end + 1
    }
}
data class CoveredIntervals(val intervals: MutableList<Interval> = mutableListOf()) {
    fun addIntervalAndCheckOverlap(interval: Interval) {
        val intersectingInterval = this.intervals.find { it.intersects(interval) }
        if(intersectingInterval != null) {
            intersectingInterval.start = minOf(interval.start, intersectingInterval.start)
            intersectingInterval.end = maxOf(interval.end, intersectingInterval.end)
            spliceIntervals(intersectingInterval)
            return
        }
        this.intervals.add(interval)
    }

    private fun spliceIntervals(newInterval: Interval) {
        val intersectingInterval = this.intervals.find { it.intersects(newInterval) && it != newInterval }
        if(intersectingInterval != null) {
            intersectingInterval.start = minOf(newInterval.start, intersectingInterval.start)
            intersectingInterval.end = maxOf(newInterval.end, intersectingInterval.end)
            this.intervals.remove(newInterval)
            return spliceIntervals(intersectingInterval)
        }
    }

    fun isComplete(): Boolean {
        return this.intervals.size == 1 && this.intervals[0].start == -1 && this.intervals[0].end == maxSize + 1
    }
}

fun getAvailablePointsOnLine(sensorBeacons: List<SensorBeacon>, y: Int) {
    val intervals = CoveredIntervals()
    sensorBeacons.forEach {
        val coveredPoints = it.getCoveredPointsOnLine(y) ?: return@forEach
        intervals.addIntervalAndCheckOverlap(coveredPoints)
        if(intervals.isComplete()) {
            return
        }
    }
    println(
        (intervals.intervals[0].end + 1).toLong() * 4000000L + y
    )
}

fun main() {
    val sensorBeacons = input.split("\n").map {
        it.replace("Sensor at x=", "")
            .replace(" y=", "")
            .replace(" closest beacon is at x=", "")
            .replace(" y=", "")
            .split(":")
            .map {
                it.split(",")
            }
    }.map {
        SensorBeacon(
            Point(it[0][0].toInt(), it[0][1].toInt()),
            Point(it[1][0].toInt(), it[1][1].toInt()),
        )
    }
    IntRange(0, maxSize).forEach {
        getAvailablePointsOnLine(sensorBeacons, it)
    }
}

val inputTest = """Sensor at x=2, y=18: closest beacon is at x=-2, y=15
Sensor at x=9, y=16: closest beacon is at x=10, y=16
Sensor at x=13, y=2: closest beacon is at x=15, y=3
Sensor at x=12, y=14: closest beacon is at x=10, y=16
Sensor at x=10, y=20: closest beacon is at x=10, y=16
Sensor at x=14, y=17: closest beacon is at x=10, y=16
Sensor at x=8, y=7: closest beacon is at x=2, y=10
Sensor at x=2, y=0: closest beacon is at x=2, y=10
Sensor at x=0, y=11: closest beacon is at x=2, y=10
Sensor at x=20, y=14: closest beacon is at x=25, y=17
Sensor at x=17, y=20: closest beacon is at x=21, y=22
Sensor at x=16, y=7: closest beacon is at x=15, y=3
Sensor at x=14, y=3: closest beacon is at x=15, y=3
Sensor at x=20, y=1: closest beacon is at x=15, y=3"""

val input = """Sensor at x=2391367, y=3787759: closest beacon is at x=2345659, y=4354867
Sensor at x=1826659, y=2843839: closest beacon is at x=1654342, y=3193298
Sensor at x=980874, y=2369046: closest beacon is at x=31358, y=2000000
Sensor at x=2916267, y=2516612: closest beacon is at x=3064453, y=2107409
Sensor at x=3304786, y=844925: closest beacon is at x=3064453, y=2107409
Sensor at x=45969, y=76553: closest beacon is at x=31358, y=2000000
Sensor at x=2647492, y=1985479: closest beacon is at x=2483905, y=2123337
Sensor at x=15629, y=2015720: closest beacon is at x=31358, y=2000000
Sensor at x=3793239, y=3203486: closest beacon is at x=3528871, y=3361675
Sensor at x=3998240, y=15268: closest beacon is at x=4731853, y=1213406
Sensor at x=3475687, y=3738894: closest beacon is at x=3528871, y=3361675
Sensor at x=3993022, y=3910207: closest beacon is at x=3528871, y=3361675
Sensor at x=258318, y=2150378: closest beacon is at x=31358, y=2000000
Sensor at x=1615638, y=1108834: closest beacon is at x=2483905, y=2123337
Sensor at x=1183930, y=3997648: closest beacon is at x=1654342, y=3193298
Sensor at x=404933, y=3377916: closest beacon is at x=1654342, y=3193298
Sensor at x=3829801, y=2534117: closest beacon is at x=3528871, y=3361675
Sensor at x=2360813, y=2494240: closest beacon is at x=2483905, y=2123337
Sensor at x=2286195, y=3134541: closest beacon is at x=1654342, y=3193298
Sensor at x=15626, y=1984269: closest beacon is at x=31358, y=2000000
Sensor at x=3009341, y=3849969: closest beacon is at x=3528871, y=3361675
Sensor at x=1926292, y=193430: closest beacon is at x=1884716, y=-881769
Sensor at x=3028318, y=3091480: closest beacon is at x=3528871, y=3361675"""