package come.basim.step_by_step.prestentation.feature.patient

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import come.basim.step_by_step.domin.model.patient.PatientResponseModel
import come.basim.step_by_step.prestentation.databinding.RowPatientsBinding


class PatientAdapter(
    private val onDeletePatient: (id: String) -> Unit,
    private val onClick: (id: String) -> Unit
) :
    ListAdapter<PatientResponseModel, PatientAdapter.PatientViewHolder>(
        PatientResponseModelDiffCallback
    ) {

    var lastSelected = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientViewHolder {
        val binding = RowPatientsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PatientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PatientViewHolder, position: Int) {
        val model = getItem(position)
         holder.bind(model, position)
    }


    inner class PatientViewHolder(private val binding: RowPatientsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: PatientResponseModel, position: Int) {
            binding.model = model

            binding.cardView.setOnClickListener {
                if (position != lastSelected) {

                    if (lastSelected != -1) {
                        getItem(lastSelected).selected = false
                        notifyItemChanged(lastSelected)
                    }

                    lastSelected = position
                    getItem(position).selected = true
                    notifyItemChanged(position)

                }
                onClick(model.id)
            }


            binding.imageDelete.setOnClickListener {

                onDeletePatient(model.id)
            }
        }

    }

    object PatientResponseModelDiffCallback : DiffUtil.ItemCallback<PatientResponseModel>() {
        override fun areItemsTheSame(
            oldItem: PatientResponseModel,
            newItem: PatientResponseModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: PatientResponseModel,
            newItem: PatientResponseModel
        ): Boolean {
            return oldItem == newItem
        }
    }
}