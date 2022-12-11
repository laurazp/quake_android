import java.util.*

class EarthquakeDetail {

    private var title: String
    private var place: String?
    private var time: Date
    private var tsunami: Int
    private var coords: ArrayList<Int>
    private var depth: Float
    private var magnitude: Double?

    constructor(
        title: String,
        place: String?,
        time: Date,
        tsunami: Int,
        coords: ArrayList<Int>,
        depth: Float,
        magnitude: Double?
    ) {
        this.title = title
        this.place = place
        this.time = time
        this.tsunami = tsunami
        this.coords = coords
        this.depth = depth
        this.magnitude = magnitude
    }


}