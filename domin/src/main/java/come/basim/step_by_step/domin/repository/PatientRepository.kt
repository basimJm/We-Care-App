package come.basim.step_by_step.domin.repository

import come.basim.step_by_step.domin.model.add.AddPatientRequest
import come.basim.step_by_step.domin.model.add.AddPatientResponse
import come.basim.step_by_step.domin.model.delete.DeletPateintResponse
import come.basim.step_by_step.domin.model.patient.PatientResponseModel

interface PatientRepository {

    suspend fun getPatients(): List<PatientResponseModel>

    suspend fun deletPatients(id: String): DeletPateintResponse

    suspend fun getPatientDetails(id: String): PatientResponseModel

    suspend fun addPatient(bodyAddingPatientsModel: AddPatientRequest): AddPatientResponse
}