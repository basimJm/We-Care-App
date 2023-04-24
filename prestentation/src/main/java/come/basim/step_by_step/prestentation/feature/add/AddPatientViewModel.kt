package come.basim.step_by_step.prestentation.feature.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import come.basim.step_by_step.domin.model.add.AddPatientRequest
import come.basim.step_by_step.domin.model.add.AddPatientResponse
import come.basim.step_by_step.domin.usecase.add.AddPatientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPatientViewModel @Inject constructor(private val addPatientUseCase: AddPatientUseCase) :ViewModel() {

    private val _addPatientStateFlow :MutableStateFlow<AddPatientResponse?> = MutableStateFlow(null)
    val addPatientStateFlow = _addPatientStateFlow.asStateFlow()

    //loading
    private val _addPatientLoadingStateFlow :MutableStateFlow<Boolean> = MutableStateFlow(false)
    val addPatientLoadingStateFlow = _addPatientLoadingStateFlow.asStateFlow()

    //erorr
    private val _addPatientErorrStateFlow :MutableStateFlow<Exception?> = MutableStateFlow(null)
    val addPatientErorrStateFlow = _addPatientErorrStateFlow.asStateFlow()



    fun addPatient(bodyAddingPatientsModel: AddPatientRequest){
   viewModelScope.launch {
       _addPatientLoadingStateFlow.emit(true)
       try {
           _addPatientStateFlow.emit(addPatientUseCase(bodyAddingPatientsModel))
       }catch (e:Exception){
           _addPatientErorrStateFlow.emit(e)
       }
       _addPatientLoadingStateFlow.emit(false)
   }
    }
}