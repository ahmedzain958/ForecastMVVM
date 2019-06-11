package com.zain.forecastmvvm.ui.weather.current

import androidx.lifecycle.ViewModel;

class CurrentWeatherViewModel : ViewModel() {
    // TODO: Implement the ViewModel
}

fun main(args: Array<String>) {
    val p1 = Point(3, -8)
    val p2 = Point(2, 9)
    val p3 = Point(2, 9)

    var sum = Point()
    sum = p1 + p2 * p3

    println("sum = (${sum.x}, ${sum.y})")
}

class Point(val x: Int = 0, val y: Int = 10) {

    // overloading plus function
    operator fun plus(p: Point): Point {
        return Point(x + p.x, y + p.y)
    }

    operator fun times(p: Point): Point {
        return Point(x * p.x, y * p.y)
    }
}
