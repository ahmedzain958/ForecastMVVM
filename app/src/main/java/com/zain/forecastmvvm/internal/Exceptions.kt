package com.zain.forecastmvvm.internal

import java.io.IOException


class NoConnectivityException: IOException()
class LocationPermissionNotGrantedException: Exception()
class DateNotFoundException: Exception()