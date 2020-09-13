package com.pedroribeiro.trendingkotlinrepos.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.pedroribeiro.domain.repositories.TrendingRepoRepository
import com.pedroribeiro.domain.models.RepositoryDomainModel
import com.pedroribeiro.trendingkotlinrepos.utils.LifecycleOwnerUtils
import com.pedroribeiro.trendingkotlinrepos.mappers.RepositoryModelMapper
import com.pedroribeiro.trendingkotlinrepos.models.RepositoryUiModel
import com.pedroribeiro.trendingkotlinrepos.schedulers.TrampolineSchedulerProviderImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.junit.Rule

class HomeViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel
    private val repoRepository = mockk<TrendingRepoRepository>(relaxed = true)
    private val repoModelMapper = mockk<RepositoryModelMapper>(relaxed = true)
    private val lifecycleOwner = mockk<LifecycleOwner>(relaxed = true)

    @Before
    fun setup() {
        viewModel = HomeViewModel(
            repoRepository,
            repoModelMapper,
            TrampolineSchedulerProviderImpl()
        )
        LifecycleOwnerUtils.setupLifecycleOwner(lifecycleOwner)
    }

    @Test
    fun `GIVEN user opened app WHEN home fragment is attached THEN get trending kotlin repositories successfully`() {
        val observer = mockk<Observer<List<RepositoryUiModel>>>(relaxed = true)
        val mockedTrendingRepoDomainModel =
            mockk<List<RepositoryDomainModel>>(relaxed = true)
        viewModel.repositories.observe(lifecycleOwner, observer)
        every { repoRepository.getTrendingRepos() } returns Single.just(
            mockedTrendingRepoDomainModel
        )
        val uiModel = repoModelMapper.mapToUi(mockedTrendingRepoDomainModel)

        viewModel.getTrendingRepos()

        verify {
            observer.onChanged(uiModel)
        }
    }

    @Test
    fun `GIVEN user opened app WHEN home fragment is attached THEN get trending kotlin repositories with an error`() {
        val observer = mockk<Observer<Unit>>(relaxed = true)
        viewModel.error.observe(lifecycleOwner, observer)
        every { repoRepository.getTrendingRepos() } returns Single.error(Throwable())

        viewModel.getTrendingRepos()

        verify {
            observer.onChanged(Unit)
        }
    }

    @Test
    fun `GIVEN user sees a list of repositories WHEN user clicks on of the repositories THEN navigate to repository details`() {
        val observer = mockk<Observer<HomeViewModel.Navigation>>(relaxed = true)
        val mockedRepo = mockk<RepositoryUiModel>(relaxed = true)
        viewModel.navigation.observe(lifecycleOwner, observer)

        viewModel.onRepositoryClick(mockedRepo)

        verify {
            observer.onChanged(HomeViewModel.Navigation.ToRepoDetails(mockedRepo))
        }
    }
}