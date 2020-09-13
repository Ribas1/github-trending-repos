package com.pedroribeiro.trendingkotlinrepos.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.pedroribeiro.trendingkotlinrepos.R
import com.pedroribeiro.trendingkotlinrepos.common.BaseFragment
import com.pedroribeiro.trendingkotlinrepos.common.show
import com.pedroribeiro.trendingkotlinrepos.models.RepositoryUiModel
import com.pedroribeiro.trendingkotlinrepos.ui.VerticalItemSpaceDecorator
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()
    private val trendingRepositoriesAdapter: TrendingRepositoriesAdapter by lazy {
        TrendingRepositoriesAdapter { repo: RepositoryUiModel ->
            viewModel.onRepositoryClick(repo)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        setupViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupViewModel() {
        with(viewModel) {
            getTrendingRepos()

            repositories.observe(
                this@HomeFragment,
                {
                    onRepositories(it)
                }
            )
            navigation.observe(
                this@HomeFragment,
                {
                    onNavigation(it)
                }
            )
            error.observe(
                this@HomeFragment,
                { error ->
                    onError(error)
                }
            )
            loading.observe(
                this@HomeFragment,
                { isLoading ->
                    onLoading(isLoading)
                }
            )
        }
    }

    private fun onLoading(isLoading: Boolean) {
        pb_repos.show(isLoading)
    }

    private fun onNavigation(navigation: HomeViewModel.Navigation?) {
        when (navigation) {
            is HomeViewModel.Navigation.ToRepoDetails -> onNavigateToRepoDetails(navigation.repo)
            null -> {
                //do nothing
            }
        }
    }

    private fun onNavigateToRepoDetails(repo: RepositoryUiModel) {
        val navDirections = HomeFragmentDirections.actionFromHomeToDetails(repo)
        navigateTo(navDirections)
    }

    private fun onRepositories(repos: List<RepositoryUiModel>) {
        trendingRepositoriesAdapter.setData(repos)
    }

    private fun onError(error: HomeViewModel.Error) {
        when (error) {
            HomeViewModel.Error.EmptyDatabase -> showSnackBar(getString(R.string.error_empty_database))
            HomeViewModel.Error.Generic -> showSnackBar(getString(R.string.error_generic))
        }
    }

    private fun showSnackBar(string: String) {
        Snackbar.make(
            requireView(),
            string,
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(R.string.retry) {
                viewModel.getTrendingRepos()
            }
            .show()
    }

    private fun setupRecyclerView() {
        with(rv_repositories) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = trendingRepositoriesAdapter
            addItemDecoration(VerticalItemSpaceDecorator(resources.getDimensionPixelSize(R.dimen.item_spacing)))
        }
    }
}