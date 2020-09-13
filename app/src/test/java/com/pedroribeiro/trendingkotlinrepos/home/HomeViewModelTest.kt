package com.pedroribeiro.trendingkotlinrepos.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.pedroribeiro.data.network.exceptions.EmptyDatabaseException
import com.pedroribeiro.domain.repositories.TrendingRepoRepository
import com.pedroribeiro.domain.models.RepositoryDomainModel
import com.pedroribeiro.domain.usecases.GetTrendingReposUseCase
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
    private val getTrendingReposUseCase = mockk<GetTrendingReposUseCase>(relaxed = true)
    private val repoModelMapper = mockk<RepositoryModelMapper>(relaxed = true)
    private val lifecycleOwner = mockk<LifecycleOwner>(relaxed = true)

    @Before
    fun setup() {
        viewModel = HomeViewModel(
            getTrendingReposUseCase,
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
        every { getTrendingReposUseCase.execute() } returns Single.just(
            mockedTrendingRepoDomainModel
        )
        val uiModel = repoModelMapper.mapToUi(mockedTrendingRepoDomainModel)

        viewModel.getTrendingRepos()

        verify {
            observer.onChanged(uiModel)
        }
    }

    @Test
    fun `GIVEN user opened app WHEN home fragment is attached THEN get trending kotlin repositories with a generic error`() {
        val observer = mockk<Observer<HomeViewModel.Error>>(relaxed = true)
        viewModel.error.observe(lifecycleOwner, observer)
        every { getTrendingReposUseCase.execute() } returns Single.error(Throwable())

        viewModel.getTrendingRepos()

        verify {
            observer.onChanged(HomeViewModel.Error.Generic)
        }
    }

    @Test
    fun `GIVEN user opened app WHEN home fragment is attached THEN get trending kotlin repositories from database and it's empty`() {
        val observer = mockk<Observer<HomeViewModel.Error>>(relaxed = true)
        viewModel.error.observe(lifecycleOwner, observer)
        every { getTrendingReposUseCase.execute() } returns Single.error(EmptyDatabaseException())

        viewModel.getTrendingRepos()

        verify {
            observer.onChanged(HomeViewModel.Error.EmptyDatabase)
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