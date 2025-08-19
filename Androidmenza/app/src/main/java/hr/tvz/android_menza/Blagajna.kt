package hr.tvz.android_menza

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Blagajna(
    @SerializedName("id") var id: UUID,
    @SerializedName("username") var username: String,
    @SerializedName("password") var password: String
): Parcelable{

}
