class Cron(private val arguments: Array<String>) {

    private fun getTimeFromArgumentArgument(): String {
        return if (arguments.size < 2) {
            TimeUtils.getTime()
        } else {
            arguments[1]
        }
    }

    fun parseLine(line: String): String {
        val timeArgument = getTimeFromArgumentArgument()

        val minArgument = timeArgument.split(":")[1]
        val hourArgument = timeArgument.split(":")[0]

        val minParameter = line.split(" ")[0]
        val hourParameter = line.split(" ")[1]

        val cronBinTask = line.split(" ")[2]

        evaluateParameters(minParameter, hourParameter, minArgument, hourArgument)

        return evaluateAsterisk(hourArgument, hourParameter, minArgument, minParameter) + " - " + cronBinTask
    }

    private fun evaluateAsterisk(hour: String, hourParameter: String, min: String, minParameter: String): String {
        if (hourParameter == "*" && minParameter == "*") {
            return "${hour}:$min today"
        } else if (hourParameter == "*" && minParameter != "*") {
            return TimeUtils.evaluateHourHourlyRepeating(
                hour.toInt(),
                min.toInt(),
                minParameter.toInt()
            ) + ":$minParameter" + TimeUtils.evaluateIsTodayHourlyRepeating(
                hour.toInt(),
                min.toInt(),
                minParameter.toInt()
            )
        } else if (hourParameter != "*" && minParameter == "*") {
            return "${hourParameter}:00" + TimeUtils.evaluateIsTodayOnSixty(hour.toInt(), hourParameter.toInt())
        } else {
            return "${hourParameter}:$minParameter" + TimeUtils.evaluateIsTodayWithHoursAndMin(
                hour.toInt(),
                hourParameter.toInt(),
                min.toInt(),
                minParameter.toInt()
            )
        }
    }

    private fun evaluateParameters(
        minParameter: String,
        hourParameter: String,
        minArgument: String,
        hourArgument: String
    ) {
        if (isMinuteParameterCorrect(minParameter)) {
            println(CronError.MinConfig)
        }

        if (isHourParameterCorrect(hourParameter)) {
            println(CronError.HourConfig)
        }

        if (!(minArgument.toInt() >= 0 || minArgument.toInt() < 60)) {
            println(CronError.HourArgument)
        }

        if (!(hourArgument.toInt() >= 0 || hourArgument.toInt() < 24)) {
            println(CronError.MinArgument)
        }
    }

    private fun isHourParameterCorrect(hourConfig: String): Boolean {
        return !(hourConfig == "*" || hourConfig.toInt() >= 0 || hourConfig.toInt() < 24)
    }

    private fun isMinuteParameterCorrect(minConfig: String): Boolean {
        return !(minConfig == "*" || minConfig.toInt() >= 0 || minConfig.toInt() < 60)
    }

    enum class CronError {
        HourArgument,
        MinArgument,
        HourConfig,
        MinConfig
    }
}