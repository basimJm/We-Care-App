package come.basim.step_by_step.domin.usecase.add

import come.basim.step_by_step.domin.model.add.AddPatientRequest
import come.basim.step_by_step.domin.model.add.AddPatientResponse
import come.basim.step_by_step.domin.repository.PatientRepository
import javax.inject.Inject

class AddPatientUseCase @Inject constructor(private val patientRepository: PatientRepository) {

suspend operator fun invoke(bodyAddingPatientsModel:AddPatientRequest) :AddPatientResponse{
    return patientRepository.addPatient(bodyAddingPatientsModel)

    }
}