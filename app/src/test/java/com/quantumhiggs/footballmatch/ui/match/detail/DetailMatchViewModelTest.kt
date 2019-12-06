package com.quantumhiggs.footballmatch.ui.match.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import com.nhaarman.mockito_kotlin.verify
import com.quantumhiggs.footballmatch.model.Sports
import com.quantumhiggs.footballmatch.model.Teams
import com.quantumhiggs.footballmatch.repository.FootballRepository
import com.quantumhiggs.footballmatch.repository.FootballRepositoryCallback
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DetailMatchViewModelTest {

    private lateinit var viewModel: DetailMatchViewModel

    @Mock
    private lateinit var footballResponse: Sports

    @Mock
    private lateinit var teamResponse: Teams

    @Mock
    private lateinit var footballRepository: FootballRepository

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    var id = ""


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailMatchViewModel(footballRepository)
        viewModel.setMatchID(id)
    }

    @Test
    fun get_detail_match() {

        id = "4346"

        viewModel.getDetailMatch(id)

        argumentCaptor<FootballRepositoryCallback<Sports?>>().apply {
            verify(footballRepository).getDetailMatch(eq(id), capture())
            firstValue.onDataLoaded(footballResponse)
        }
    }

    @Test
    fun get_detail_home() {
        id = "2112"

        viewModel.getDetailHome(id)

        argumentCaptor<FootballRepositoryCallback<Teams?>>().apply {
            verify(footballRepository).getDetailHome(eq(id), capture())
            firstValue.onDataLoaded(teamResponse)
        }
    }

    @Test
    fun get_detail_away() {
        id = "2112"

        viewModel.getAwayDetail(id)

        argumentCaptor<FootballRepositoryCallback<Teams?>>().apply {
            verify(footballRepository).getAwayDetail(eq(id), capture())
            firstValue.onDataLoaded(teamResponse)
        }
    }
}