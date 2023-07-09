package br.com.projects.petbite.data

import android.os.Parcel
import android.os.Parcelable

class PetDTO(val creationDate: String?, val name: String?, val birthday: String?, val id: String?) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(creationDate)
        parcel.writeString(name)
        parcel.writeString(birthday)
        parcel.writeString(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PetDTO> {
        override fun createFromParcel(parcel: Parcel): PetDTO {
            return PetDTO(parcel)
        }

        override fun newArray(size: Int): Array<PetDTO?> {
            return arrayOfNulls(size)
        }
    }
}
