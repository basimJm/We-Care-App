package come.basim.step_by_step.prestentation.feature.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import come.basim.step_by_step.prestentation.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment: Fragment() {

    lateinit var binding:FragmentDetailsBinding

    private val viewModel:DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding =FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initObserv()
    }

    private fun initObserv() {
        lifecycleScope.launch{
            viewModel.patienDetailstStateFlow.collect(){response->
                if (response!=null){
                    Toast.makeText(requireContext(), "details came", Toast.LENGTH_LONG).show()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.patientDetailLoadingStateFlow.collect(){show->

                binding.progressCircular.isVisible=show
            }
        }
        lifecycleScope.launch {
            viewModel.patientDetailErorrStateFlow.collect(){e->
                if (e!=null){
                    Toast.makeText(requireContext(), "ERORR", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun String.addPoint(): String {
        return "$this."
    }


    fun AppCompatTextView.addPoint() {
        this.text = text.toString().addPoint()
    }
}