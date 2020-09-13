package com.pedroribeiro.trendingkotlinrepos.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pedroribeiro.trendingkotlinrepos.R
import com.pedroribeiro.trendingkotlinrepos.common.BaseFragment
import com.pedroribeiro.trendingkotlinrepos.ui.HorizontalItemSpaceDecorator
import kotlinx.android.synthetic.main.fragment_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : BaseFragment() {

    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: DetailsViewModel by viewModel()

    private val buildersAdapter: ContributorsAdapter by lazy {
        ContributorsAdapter(repo.builtBy)
    }

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
        setupRecyclerView()
        setupView()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setupViewModel()
    }

    private fun setupRecyclerView() {
        with(rv_details_builders) {
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            adapter = buildersAdapter
            addItemDecoration(HorizontalItemSpaceDecorator(resources.getDimensionPixelSize(R.dimen.item_outer_margin)))
        }
    }

    private fun setupView() {
        tv_details_title.text = repo.name
        tv_details_description.text = repo.description
        tv_details_forks.text = getString(R.string.details_forks, repo.forks)
        tv_details_stars.text =
            getString(R.string.details_stars, repo.currentPeriodStars, repo.stars)
        Glide.with(requireContext())
            .load(repo.avatar)
            .circleCrop()
            .into(iv_details_avatar)
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