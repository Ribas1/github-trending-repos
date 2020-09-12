package com.pedroribeiro.trendingkotlinrepos.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.pedroribeiro.domain.models.RepositoryDomainModel
import com.pedroribeiro.trendingkotlinrepos.R
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private val trendingRepositoriesAdapter: TrendingRepositoriesAdapter by lazy {
        TrendingRepositoriesAdapter()
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
        }
    }

    private fun onRepositories(repos: List<RepositoryDomainModel>) {
        trendingRepositoriesAdapter.setData(repos)
    }

    private fun setupRecyclerView() {
        with (rv_repositories) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = trendingRepositoriesAdapter
        }
    }
}