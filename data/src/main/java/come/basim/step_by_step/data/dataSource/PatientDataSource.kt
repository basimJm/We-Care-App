package come.basim.step_by_step.data.dataSource

import come.basim.step_by_step.domin.model.add.AddPatientRequest
import come.basim.step_by_step.domin.model.add.AddPatientResponse
import come.basim.step_by_step.domin.model.delete.DeletPateintResponse
import come.basim.step_by_step.domin.model.patient.PatientResponseModel
import come.basim.step_by_step.domin.model.patient.PatientsWrapperRemotModel
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PatientDataSource {

    @GET("patients")
    suspend fun getPatients(): PatientsWrapperRemotModel


    @GET("patients/{id}")
    suspend fun getPatientDetails(@Path("id") id: String): PatientResponseModel

    @DELETE("patients/{id}")
    suspend fun deletePatient(@Path("id") id: String): DeletPateintResponse


   @POST("patients/{id}")
   suspend fun addPatient(@Body bodyAddingPatientsModel:AddPatientRequest):AddPatientResponse
}