package com.example.quake.Mappers

import com.example.quake.API.Models.Feature
import com.example.quake.EarthquakeModel
import com.example.quake.Formatters.GetCustomTextFormatter
import com.example.quake.Formatters.GetDateFormatter
import com.example.quake.Formatters.GetFormattedCoordsFormatter
import com.google.android.gms.maps.model.LatLng
import java.lang.Long

class FeatureToEarthquakeModelMapper {

    private val getCustomTextFormatter = GetCustomTextFormatter()
    private val getFormattedCoordsFormatter = GetFormattedCoordsFormatter()
    private val getDateFormatter = GetDateFormatter()
    //private val getTsunamiValueFormatter = GetTsunamiValueFormatter()
    //private let unitsUseCase = UnitsUseCase()

    fun map(feature: Feature): EarthquakeModel {
        val earthquakeModel = EarthquakeModel(
            fullTitle = feature.properties.title.toString(),
            simplifiedTitle = getCustomTextFormatter.getSimplifiedTitle(feature.properties.title, feature.properties.place),
            place = feature.properties.place.toString(),
            formattedCoords = getFormattedCoordsFormatter.getFormattedCoords(feature.geometry.coordinates),
            originalCoords = getFormattedCoordsFormatter.getFormattedLatLngCoords(feature.geometry.coordinates),
            //TODO: Terminar si sirve !!!
            depth = "",
            date = "",
            originalDate = 0L,
            tsunami = "",
            magnitude = ""
        )
        return earthquakeModel
    }

    /*func map(from feature: Feature) -> EarthquakeModel {
        EarthquakeModel(fullTitle: feature.properties.title ?? "Unknown",
                        simplifiedTitle: getSimplifiedTitleFormatter.getSimplifiedTitle(titleWithoutFormat: feature.properties.title ?? "Unknown", place: feature.properties.place ?? "Unknown"),
                        place: feature.properties.place ?? "Unknown",
                        formattedCoords: getFormattedCoordsFormatter.getFormattedCoords(actualCoords: feature.geometry.coordinates),
                        originalCoords: feature.geometry.coordinates,
                        depth: "\(depthInSelectedUnits(feature: feature))",
                        date: getDateFormatter.formatDate(dateToFormat: feature.properties.time ?? 0000),
                        originalDate: getDateFormatter.formatIntToDate(dateToFormat: feature.properties.time ?? 0),
                        tsunami: getTsunamiValueFormatter.getTsunamiValue(tsunami: feature.properties.tsunami ?? 0),
                        magnitude: String(format: "%.1f", feature.properties.mag ?? 0))
    }

    func depthInSelectedUnits(feature: Feature) -> Measurement<UnitLength> {
        let initialDepthInKms = Double(feature.geometry.coordinates[2]).roundToDecimal(2)
        var finalDepth = Measurement(value: 0.0, unit: UnitLength.kilometers)
        if (unitsUseCase.getSelectedUnit() == "kilometers") {
            finalDepth = Measurement(value: initialDepthInKms, unit: UnitLength.kilometers)
        } else {
            finalDepth = Measurement(value: (initialDepthInKms * 0.62137), unit: UnitLength.miles)
        }
        return finalDepth
    }

    func depthInSelectedUnitsFromFloat(depth: Float) -> Measurement<UnitLength> {
        let initialDepthInKms = Double(depth)
        var finalDepth = Measurement(value: 0.0, unit: UnitLength.kilometers)
        if (unitsUseCase.getSelectedUnit() == "kilometers") {
            finalDepth = Measurement(value: initialDepthInKms, unit: UnitLength.kilometers)
        } else {
            finalDepth = Measurement(value: (initialDepthInKms * 0.62137), unit: UnitLength.miles)
        }
        return finalDepth
    }
}

extension Double {
    func roundToDecimal(_ fractionDigits: Int) -> Double {
        let multiplier = pow(10, Double(fractionDigits))
        return Darwin.round(self * multiplier) / multiplier
    }
     */
}