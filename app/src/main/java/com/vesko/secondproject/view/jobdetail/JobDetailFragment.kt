package com.vesko.secondproject.view.jobdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.vesko.secondproject.R
import com.vesko.secondproject.viewmodel.JobViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobDetailFragment : Fragment() {
    private val jobViewModel : JobViewModel by viewModels()
    private var selectedJobId = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            selectedJobId = it.getString("job_id","")
            jobViewModel.start(selectedJobId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_job_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val companyTextView = view.findViewById<TextView>(R.id.company_name_text_view)
        val titleJobTextView = view.findViewById<TextView>(R.id.title_job_text_view)
        val descriptionTextView = view.findViewById<TextView>(R.id.description_job_text_view)

        val companyLogo = view.findViewById<ImageView>(R.id.company_logo_image)

        jobViewModel.job.observe(viewLifecycleOwner, { job ->
            companyTextView.text = job.company
            titleJobTextView.text = job.title
            val fixedDescription = HtmlCompat.fromHtml(job.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
            descriptionTextView.text = fixedDescription

            Glide.with(companyLogo.context)
                .load(job.company_logo)
                .apply(RequestOptions().circleCrop())
                .into(companyLogo)
        })
    }
}