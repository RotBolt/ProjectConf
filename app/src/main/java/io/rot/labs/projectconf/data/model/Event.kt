package io.rot.labs.projectconf.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import java.util.Date
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "startDate")
    val startDate: Date,

    @ColumnInfo(name = "endDate")
    val endDate: Date,

    @ColumnInfo(name = "city")
    val city: String,

    @ColumnInfo(name = "country")
    val country: String,

    @ColumnInfo(name = "cfpUrl")
    val cfpUrl: String? = null,

    @ColumnInfo(name = "cfpEndDate")
    val cfpEndDate: Date? = null,

    @ColumnInfo(name = "twitter")
    val twitter: String? = null
) : Parcelable
