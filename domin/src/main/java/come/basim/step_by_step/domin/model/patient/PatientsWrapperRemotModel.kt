package come.basim.step_by_step.domin.model.patient

data class PatientsWrapperRemotModel (
    val status: Int,

    val message: String,

    val data : List<PatientResponseModel>

        )


