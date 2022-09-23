import java.text.SimpleDateFormat
import java.util.*

class TimeUtils {

    companion object {

        fun evaluateIsTodayOnSixty(hour: Int, hourParameter: Int): String {
            return if (hour > hourParameter) " tomorrow" else " today"
        }

        fun evaluateHourHourlyRepeating(hour: Int, min: Int, minParameter: Int): String {
            return if (hour == 23 && min > minParameter) {
                "00"
            } else if (min > minParameter) {
                (hour + 1).toString()
            } else {
                hour.toString()
            }
        }

        fun evaluateIsTodayHourlyRepeating(hour: Int, min: Int, minParameter: Int): String {
            return if (hour == 23 && min > minParameter) " tomorrow" else " today"
        }

        fun evaluateIsTodayWithHoursAndMin(hour: Int, hourParameter: Int, min: Int, minParameter: Int): String {
            return if (hour > hourParameter) {
                " tomorrow"
            } else if (hour < hourParameter) {
                " today"
            } else {
                if (min > minParameter) " tomorrow" else " today"
            }
        }

        fun getTime(): String {
            return SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        }
    }
}