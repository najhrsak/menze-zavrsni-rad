package hr.tvz.android_menza

import com.google.gson.annotations.SerializedName

data class UniqueUsernameResponse(@SerializedName("isUnique") val isUnique: Boolean, val user: StudentUser)
