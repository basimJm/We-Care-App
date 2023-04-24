package come.basim.step_by_step.prestentation.feature.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import come.basim.step_by_step.domin.model.add.AddPatientRequest
import come.basim.step_by_step.prestentation.databinding.FragmentAddPatientBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddPatientFragment : Fragment() {
    lateinit var binding: FragmentAddPatientBinding
    private val viewModel: AddPatientViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPatientBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initObserve()
        initListener()
    }

    private fun initListener() {
        bodyAddpatient()
        binding.buttonAdd.setOnClickListener {
            if (infoValidation())

                viewModel.addPatient(bodyAddpatient())
        }

    }

    private fun bodyAddpatient(): AddPatientRequest {

        return AddPatientRequest(
            binding.editTextFullName.text.toString(),
            binding.editTextFullEmail.text.toString(),
            binding.editTextFullAddress.text.toString(),
            binding.editTextFullBirthdate.text.toString(),
            binding.editTextFullGender.text.toString(),
            binding.editTextFullMobile.text.toString()
        )

    }

    private fun infoValidation(): Boolean {

        if (binding.editTextFullName.length() == 0) {
            binding.textFullName.error = "(\"This field is required\")"
            return false
        } else {
            binding.textFullName.error = null
        }

        if (binding.editTextFullEmail.length() == 0) {
            binding.textFullEmail.error = "(\"This field is required\")"
            return false
        } else {
            binding.textFullEmail.error = null
        }
        if (binding.editTextFullAddress.length() == 0) {
            binding.textFullAddress.error = "(\"This field is required\")"
            return false
        } else {
            binding.textFullAddress.error = null
        }

        if (binding.editTextFullBirthdate.length() == 0) {
            binding.textFullBirthDate.error = "(\"This field is required\")"
            return false
        } else {
            binding.textFullBirthDate.error = null
        }

        if (binding.editTextFullGender.length() == 0) {
            binding.textFullGender.error = "(\"This field is required\")"
            return false
        } else {
            binding.textFullGender.error = null
        }


        if (binding.editTextFullMobile.length() == 0) {
            binding.textFullMobile.error = "(\"This field is required\")"
            return false
        } else {
            binding.textFullMobile.error = null
        }
        return true
    }

    private fun initObserve() {
        lifecycleScope.launch {
            viewModel.addPatientStateFlow.collect() { response ->
                if (response != null) {
                    Toast.makeText(requireContext(), "Patient Added", Toast.LENGTH_SHORT).show()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.addPatientLoadingStateFlow.collect() { show ->

                binding.progressCircular.isVisible = show

            }
        }

        lifecycleScope.launch {
            viewModel.addPatientStateFlow.collect() { response ->
                if (response != null) {
                    Toast.makeText(
                        requireContext(), "Erorr :Patient Did Not Added",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}