package come.basim.step_by_step.prestentation.feature.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import come.basim.step_by_step.domin.model.patient.PatientResponseModel
import come.basim.step_by_step.domin.usecase.details.GetPatientDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val getPatientDetailsUseCase: GetPatientDetailsUseCase
, state :SavedStateHandle) :
    ViewModel() {

    private val _patienDetailstStateFlow: MutableStateFlow<PatientResponseModel?> =
        MutableStateFlow(null)

    val patienDetailstStateFlow = _patienDetailstStateFlow.asStateFlow()


    //loading
    private val _patientDetailLoadinStateFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val patientDetailLoadingStateFlow = _patientDetailLoadinStateFlow.asStateFlow()

    //erorr
    private val _patientDetailErorrStateFlow: MutableStateFlow<Exception?> = MutableStateFlow(null)
    val patientDetailErorrStateFlow = _patientDetailErorrStateFlow.asStateFlow()


    var savedStateHandle =state
    init {
        getPatientDetails()
    }

    fun getPatientDetails() {
        val id =savedStateHandle.get<String>("id") ?: "-1"

        viewModelScope.launch {
            _patientDetailLoadinStateFlow.emit(true)
            try {
                _patienDetailstStateFlow.emit(getPatientDetailsUseCase(id))
            } catch (e: Exception) {
                _patientDetailErorrStateFlow.emit(e)
            }
            _patientDetailLoadinStateFlow.emit(false)
        }
    }
}