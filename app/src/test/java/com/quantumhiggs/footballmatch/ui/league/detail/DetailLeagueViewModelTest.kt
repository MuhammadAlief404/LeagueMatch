package com.quantumhiggs.footballmatch.ui.league.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import com.quantumhiggs.footballmatch.model.Leagues
import com.quantumhiggs.footballmatch.repository.FootballRepository
import com.quantumhiggs.footballmatch.repository.FootballRepositoryCallback
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DetailLeagueViewModelTest {
    private lateinit var viewModel: DetailLeagueViewModel

    @Mock
    private lateinit var footballResponse: Leagues

    @Mock
    private lateinit var footballRepository: FootballRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    var id = ""


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailLeagueViewModel(footballRepository)
        viewModel.setLeagueID(id)
    }

    @Test
    fun get_detail_league() {

        id = "4346"

        viewModel.getDetailLeague(id)

        argumentCaptor<FootballRepositoryCallback<Leagues?>>().apply {
            verify(footballRepository).getDetailLeague(eq(id), capture())
            firstValue.onDataLoaded(footballResponse)
        }
        verify(viewModel.setDetailLeague().value)
    }
}