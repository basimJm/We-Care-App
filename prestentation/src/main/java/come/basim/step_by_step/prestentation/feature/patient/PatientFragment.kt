package come.basim.step_by_step.prestentation.feature.patient

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import come.basim.step_by_step.prestentation.R
import come.basim.step_by_step.prestentation.databinding.FragmentPatientBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PatientFragment : Fragment() {

    lateinit var binding: FragmentPatientBinding
    private val viewModel: PatientViewModel by viewModels()

    private lateinit var adapter: PatientAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPatientBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initAdapter()
        initObserver()
        initListener()

    }

    private fun initAdapter() {
        adapter = PatientAdapter(::deletePatient,
        ::onClick)
        binding.recycleView.adapter = adapter
    }

    private fun initListener() {

        binding.fabAdd.setOnClickListener {
            findNavController().navigate(R.id.addPatientFragment)
        }


        binding.swipeRefresh.setOnRefreshListener {
            viewModel.getPatient()
            lifecycleScope.launch {
                delay(3000)

            }
            binding.swipeRefresh.isRefreshing = false
        }




    }

    private fun initObserver() {

        lifecycleScope.launch {
            viewModel.patientStateFlow.collect() { respone ->
                if (respone.isNotEmpty())
                    adapter.submitList(respone)
            }
        }
        lifecycleScope.launch {
            viewModel.patientLoadingStateFlow.collect() { show ->
                binding.progressLoading.isVisible = show
            }
        }

        lifecycleScope.launch {
            viewModel.patientErorrStateFlow.collect() { e ->
                Log.d("TAGOO", "Erorr :$e")
            }
        }

        lifecycleScope.launch {
            viewModel.deletePatientLifeData.observe(viewLifecycleOwner) { response ->
                if (response != null) {
                    Toast.makeText(requireContext(), "patient deleted", Toast.LENGTH_LONG).show()
                }

            }
        }
    }


    fun deletePatient(id: String) {

        AlertDialog.Builder(requireContext())
            .setTitle("Delete Patient!!")
            .setMessage("Are You Sure To Delete")
            .setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton("yes") { dialog, which ->
                viewModel.deletePatient(id)
                dialog.dismiss()
            }.show()
    }

    fun onClick(id: String) {
        findNavController().navigate(R.id.detailsFragment, bundleOf("id" to id))
    }
}


