package catchdesign.co.nz.androidtest.model

import android.os.Parcel
import android.os.Parcelable

class DataObject(val id: Int,
                 val title: String,
                 val subtitle: String,
                 val content: String) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0!!.writeInt(id)
        p0!!.writeString(title)
        p0!!.writeString(subtitle)
        p0!!.writeString(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataObject> {
        override fun createFromParcel(parcel: Parcel): DataObject {
            return DataObject(parcel)
        }

        override fun newArray(size: Int): Array<DataObject?> {
            return arrayOfNulls(size)
        }
    }


}
