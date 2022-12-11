import android.telecom.Call

interface APIService {
    @GET
    fun getCharacterByName(@Url url:String): Call<EarthquakeResponse>
}