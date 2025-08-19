package hr.tvz.android_menza

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Menza(@SerializedName("id") var id: UUID,
                 @SerializedName("naziv") var naziv: String,
                 @SerializedName("adresa") var adresa:String,
                 @SerializedName("info") var info:String,
                 @SerializedName("radnoVrijeme") var radnoVrijeme: String,
                 @SerializedName("meni") var meni:String,
                 @SerializedName("blagajne") var blagajne: kotlin.collections.List<Blagajna>): Parcelable {
}