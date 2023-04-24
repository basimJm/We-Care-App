package come.basim.step_by_step.data.repo

import come.basim.step_by_step.data.dataSource.PatientDataSource
import come.basim.step_by_step.domin.model.add.AddPatientRequest
import come.basim.step_by_step.domin.model.add.AddPatientResponse
import come.basim.step_by_step.domin.model.delete.DeletPateintResponse
import come.basim.step_by_step.domin.model.patient.PatientResponseModel
import come.basim.step_by_step.domin.repository.PatientRepository
import javax.inject.Inject

class PatientRepositoryImpl @Inject constructor(private val patientDataSource: PatientDataSource) :
    PatientRepository {


    override suspend fun getPatients(): List<PatientResponseModel> {
        return patientDataSource.getPatients().data
    }

    override suspend fun deletPatients(id:String): DeletPateintResponse {
        return patientDataSource.deletePatient(id)
    }

    override suspend fun getPatientDetails(id: String): PatientResponseModel {
        return patientDataSource.getPatientDetails(id)
    }

    override suspend fun addPatient(bodyAddingPatientsModel: AddPatientRequest): AddPatientResponse {
        return patientDataSource.addPatient(bodyAddingPatientsModel)
    }


}
