package com.vesko.secondproject.view.jobs
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.vesko.secondproject.R
import com.vesko.secondproject.model.JobPojo
import com.vesko.secondproject.viewmodel.JobsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobsFragment : Fragment(), JobsAdapter.ClickEventHandler {

    private val jobsViewModel: JobsViewModel by viewModels()
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
            adapter = JobsAdapter( this@JobsFragment)
            jobsAdapter = adapter as JobsAdapter
        }

        searchButton.setOnClickListener {


            val location = locationEditText.text.toString()
            val description = descriptionEditText.text.toString()

            if(location.isNotEmpty() && description.isNotEmpty()) {
                hideKeyboard()
                progressBar.visibility = View.VISIBLE
                searchTextView.visibility = View.GONE
                jobsViewModel.fetchJobs(location, description)
            }
            else
                Toast.makeText(requireContext(),"Please enter location and description", Toast.LENGTH_SHORT).show()
        }


        jobsViewModel.myJobs.observe(viewLifecycleOwner, { jobs ->
            searchTextView.visibility = View.GONE
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            if(jobs.isNotEmpty())
                jobsAdapter.setJobList(jobs as ArrayList<JobPojo>)
            else
                Toast.makeText(requireContext(),"Try another search details", Toast.LENGTH_SHORT).show()
        })
    }

    private fun hideKeyboard(){
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }


    override fun clickView(jobId: String) {
        findNavController().navigate(
            R.id.action_jobFragment_to_jobDetailFragment,
            bundleOf("job_id" to jobId)
        )
    }
}