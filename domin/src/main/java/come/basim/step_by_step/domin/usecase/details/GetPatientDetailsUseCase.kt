package come.basim.step_by_step.domin.usecase.details

import come.basim.step_by_step.domin.model.patient.PatientResponseModel
import come.basim.step_by_step.domin.repository.PatientRepository
import javax.inject.Inject

class GetPatientDetailsUseCase @Inject constructor(private val patientRepository: PatientRepository) {
    suspend operator fun invoke(id:String):PatientResponseModel{
        return patientRepository.getPatientDetails(id)
    }
}