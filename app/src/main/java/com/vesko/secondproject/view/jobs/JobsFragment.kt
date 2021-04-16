package com.vesko.secondproject.view.jobs


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.vesko.secondproject.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobsFragment : Fragment(), JobsAdapter.ClickEventHandler {

    private lateinit var jobsAdapter: JobsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_jobs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        val locationEditText = view.findViewById<TextInputEditText>(R.id.locationEditText)
        val descriptionEditText = view.findViewById<TextInputEditText>(R.id.descriptionEditText)

        val searchButton = view.findViewById<MaterialButton>(R.id.search_Button)
        val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)
        val searchTextView = view.findViewById<TextView>(R.id.search_text_view)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = JobsAdapter(this@JobsFragment)
            jobsAdapter = adapter as JobsAdapter
        }

        searchButton.setOnClickListener {
        }

    }


    override fun clickView(jobId: String) {
        findNavController().navigate(
            R.id.action_jobFragment_to_jobDetailFragment,
            bundleOf("job_id" to jobId)
        )
    }
}