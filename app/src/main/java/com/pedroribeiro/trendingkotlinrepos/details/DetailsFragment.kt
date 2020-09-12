package com.pedroribeiro.trendingkotlinrepos.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.pedroribeiro.trendingkotlinrepos.R
import com.pedroribeiro.trendingkotlinrepos.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : BaseFragment() {

    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModel()

    private val repo by lazy {
        args.repositoryModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setupViewModel()
    }

    private fun setupViewModel() {
        with(viewModel) {
            navigation.observe(
                this@DetailsFragment,
                {
                    onNavigation(it)
                }
            )
        }
    }

    private fun onNavigation(navigation: DetailsViewModel.Navigation?) {
        when (navigation) {
            DetailsViewModel.Navigation.Up -> onUp()
            null -> {
                //do nothing
            }
        }
    }

    private fun onUp() {
        navigateUp()
    }

    private fun setupToolbar() {
        with(toolbar_details) {
            title = repo.name
            setNavigationOnClickListener {
                viewModel.onUpClick()
            }
        }
    }
}