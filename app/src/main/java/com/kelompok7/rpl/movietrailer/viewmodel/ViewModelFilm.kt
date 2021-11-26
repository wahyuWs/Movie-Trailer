package com.kelompok7.rpl.movietrailer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kelompok7.rpl.movietrailer.helper.DataDummy
import com.kelompok7.rpl.movietrailer.model.Movies

class ViewModelFilm: ViewModel() {
    private val dataAction = MutableLiveData<List<Movies>>()
    private val dataAdventure = MutableLiveData<List<Movies>>()
    private val dataComedy = MutableLiveData<List<Movies>>()
    private val dataHorror = MutableLiveData<List<Movies>>()
    private val _dataSearch = MutableLiveData<List<Movies>>()

    fun dataListAction(): LiveData<List<Movies>> = dataAction
    fun dataListAdventure(): LiveData<List<Movies>> = dataAdventure
    fun dataListComedy(): LiveData<List<Movies>> = dataComedy
    fun dataListHorror(): LiveData<List<Movies>> = dataHorror
    fun dataSearch(): LiveData<List<Movies>> = _dataSearch

    init {
        dataAction.value = DataDummy.dataAction()
        dataAdventure.value = DataDummy.dataAdventure()
        dataComedy.value = DataDummy.dataComedy()
        dataHorror.value = DataDummy.dataHoror()
        _dataSearch.value = DataDummy.dataSearch()
    }
}