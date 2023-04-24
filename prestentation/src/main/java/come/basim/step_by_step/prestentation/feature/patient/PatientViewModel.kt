package come.basim.step_by_step.prestentation.feature.patient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import come.basim.step_by_step.domin.model.delete.DeletPateintResponse
import come.basim.step_by_step.domin.model.patient.PatientResponseModel
import come.basim.step_by_step.domin.usecase.delete.DeletePatientsUseCase
import come.basim.step_by_step.domin.usecase.patient.GetPatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PatientViewModel @Inject constructor(private val getPatientUseCase: GetPatientUseCase
, private val deletePatientsUseCase: DeletePatientsUseCase) :
    ViewModel() {

    private val _patientStateFlow: MutableStateFlow<List<PatientResponseModel>> =
        MutableStateFlow(emptyList())

    val patientStateFlow: StateFlow<List<PatientResponseModel>> = _patientStateFlow


    //loading
    private val _patientLoadinStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val patientLoadingStateFlow = _patientLoadinStateFlow.asStateFlow()

    //erorr
    private val _patientErorrStateFlow: MutableStateFlow<Exception?> = MutableStateFlow(null)
    val patientErorrStateFlow = _patientErorrStateFlow.asStateFlow()

    //delete
    private val _deletePatientLifeData : MutableLiveData<DeletPateintResponse> = MutableLiveData(null)

    val deletePatientLifeData :LiveData<DeletPateintResponse> = _deletePatientLifeData


    init {
        getPatient()
    }

    fun getPatient() {
        viewModelScope.launch {
            _patientLoadinStateFlow.emit(true)
            try {
                _patientStateFlow.emit(getPatientUseCase())
            } catch (e: Exception) {
                _patientErorrStateFlow.emit(e)

            }

            _patientLoadinStateFlow.emit(false)
        }
    }
    fun deletePatient(id:String){

        viewModelScope.launch {
            _patientLoadinStateFlow.emit(true)
            try {
                _deletePatientLifeData.postValue(deletePatientsUseCase(id))
            }
            catch (e:Exception){
                _patientErorrStateFlow.emit(e)
            }
            _patientLoadinStateFlow.emit(false)
        }


    }
}