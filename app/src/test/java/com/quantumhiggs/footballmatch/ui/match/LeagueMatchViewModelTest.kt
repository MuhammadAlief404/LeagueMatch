package com.quantumhiggs.footballmatch.ui.match

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import com.quantumhiggs.footballmatch.model.Sports
import com.quantumhiggs.footballmatch.repository.FootballRepository
import com.quantumhiggs.footballmatch.repository.FootballRepositoryCallback
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class LeagueMatchViewModelTest {
    private lateinit var viewModel: LeagueMatchViewModel

    @Mock
    private lateinit var footballResponse: Sports

    @Mock
    private lateinit var footballRepository: FootballRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    var id = ""


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = LeagueMatchViewModel(footballRepository)
        viewModel.setLeagueID(id)
    }

    @Test
    fun get_list_league_previous() {

        id = "4346"

        viewModel.getListPrevMatch(id)

        argumentCaptor<FootballRepositoryCallback<Sports?>>().apply {
            verify(footballRepository).getListPrevMatch(eq(id), capture())
            firstValue.onDataLoaded(footballResponse)
        }
    }

    @Test
    fun get_list_league_next() {
        id = "4346"

        viewModel.getListNextMatch(id)

        argumentCaptor<FootballRepositoryCallback<Sports?>>().apply {
            verify(footballRepository).getListNextMatch(eq(id), capture())
            firstValue.onDataLoaded(footballResponse)
        }
    }

}