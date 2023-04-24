package come.basim.step_by_step.domin.model.patient

import com.google.gson.annotations.SerializedName

data class PatientResponseModel (
    val condition: String,

    @SerializedName("_id")
    val id: String,

    @SerializedName("name")
    val namePatients: String,

    val address: String,

    val mobile: String,

    val email: String,

    val birthdate: String,

    val gender: String,

    val photo: String,

    var selected : Boolean = false


    )
{

    fun getPatientInfo():String{
        return "Lives on $address , Email $email ,  Born on $birthdate"
    }
}


