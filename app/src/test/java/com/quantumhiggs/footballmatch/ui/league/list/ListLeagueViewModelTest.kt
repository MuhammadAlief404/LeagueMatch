package com.quantumhiggs.footballmatch.ui.league.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.verify
import com.quantumhiggs.footballmatch.model.Leagues
import com.quantumhiggs.footballmatch.repository.FootballRepository
import com.quantumhiggs.footballmatch.repository.FootballRepositoryCallback
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class ListLeagueViewModelTest {

    private lateinit var viewModel: ListLeagueViewModel

    @Mock
    private lateinit var footballResponse: Leagues

    @Mock
    private lateinit var footballRepository: FootballRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = ListLeagueViewModel(footballRepository)
    }

    @Test
    fun testGetListLeague() {
        argumentCaptor<FootballRepositoryCallback<Leagues?>>().apply {
            verify(footballRepository).getListLeague(capture())
            firstValue.onDataLoaded(footballResponse)
        }
    }
}