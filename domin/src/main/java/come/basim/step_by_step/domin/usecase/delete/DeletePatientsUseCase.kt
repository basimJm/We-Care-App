package come.basim.step_by_step.domin.usecase.delete

import come.basim.step_by_step.domin.model.delete.DeletPateintResponse
import come.basim.step_by_step.domin.repository.PatientRepository
import javax.inject.Inject

class DeletePatientsUseCase @Inject constructor(private val patientRepository: PatientRepository) {

    suspend operator fun invoke(id : String):DeletPateintResponse{
        return patientRepository.deletPatients(id)
    }
}