package come.basim.step_by_step.domin.usecase.patient

import come.basim.step_by_step.domin.model.patient.PatientResponseModel
import come.basim.step_by_step.domin.repository.PatientRepository
import javax.inject.Inject

class GetPatientUseCase @Inject constructor(private val patientRepository: PatientRepository) {

    suspend operator fun invoke():List<PatientResponseModel>{
        return patientRepository.getPatients()
    }
}