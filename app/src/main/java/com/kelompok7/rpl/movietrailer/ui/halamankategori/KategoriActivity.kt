package com.kelompok7.rpl.movietrailer.ui.halamankategori

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kelompok7.rpl.movietrailer.R
import com.kelompok7.rpl.movietrailer.adapter.HalamanKategoriAdapter
import com.kelompok7.rpl.movietrailer.adapter.SearchAdapter
import com.kelompok7.rpl.movietrailer.databinding.ActivityKategoriBinding
import com.kelompok7.rpl.movietrailer.model.Movies
import com.kelompok7.rpl.movietrailer.viewmodel.ViewModelFilm

class KategoriActivity : AppCompatActivity() {

    private lateinit var activityKategoriBinding: ActivityKategoriBinding
    private lateinit var halamanKategoriViewModel: ViewModelFilm
    private lateinit var halamanKategoriAdapter: HalamanKategoriAdapter
    private lateinit var searchAdapter: SearchAdapter

    companion object {
        const val EXTRA_DATA_ACTION = "extra_data_action"
        const val EXTRA_DATA_ADVENTURE = "extra_data_adventure"
        const val EXTRA_DATA_COMEDY = "extra_data_comedy"
        const val EXTRA_DATA_HORROR = "extra_data_horror"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityKategoriBinding = ActivityKategoriBinding.inflate(layoutInflater)
        setContentView(activityKategoriBinding.root)

        halamanKategoriViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ViewModelFilm::class.java]
        halamanKategoriAdapter = HalamanKategoriAdapter()
        searchAdapter = SearchAdapter()

        setList()
        setListSearch()

        activityKategoriBinding.searchHalamanKategori.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                activityKategoriBinding.rvKategori.visibility = View.GONE
                activityKategoriBinding.rvSearch.visibility = View.VISIBLE

                searchAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                activityKategoriBinding.rvKategori.visibility = View.GONE
                activityKategoriBinding.rvSearch.visibility = View.VISIBLE

                searchAdapter.filter.filter(newText)
                return false
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.item_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.item_tentang -> {
                val mDialogView = LayoutInflater.from(this).inflate(R.layout.view_alert_dialog, null)
                val mBuilder = AlertDialog.Builder(this)
                    .setView(mDialogView)
                    .setPositiveButton(getString(R.string.close)) { dialog, _, -> dialog.cancel() }

                val alertDialog = mBuilder.create()
                alertDialog.show()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setListSearch() {
        halamanKategoriViewModel.dataSearch().observe(this, { data ->
            if (data != null) {
                searchAdapter.dataMovie.addAll(data)
            }

            with(activityKategoriBinding.rvSearch) {
                layoutManager = LinearLayoutManager(this@KategoriActivity)
                adapter = searchAdapter
            }
        })
    }

    private fun setList() {
        val dataAction = intent.getStringExtra(EXTRA_DATA_ACTION)
        val dataAdventure = intent.getStringExtra(EXTRA_DATA_ADVENTURE)
        val dataAComedy = intent.getStringExtra(EXTRA_DATA_COMEDY)
        val dataHorror = intent.getStringExtra(EXTRA_DATA_HORROR)

        if (dataAction != null) {
            supportActionBar?.title = dataAction

            halamanKategoriViewModel.dataListAction().observe(this, { listAction ->
                if (listAction != null) {
                    halamanKategoriAdapter.setData(listAction as ArrayList<Movies>)
                }
            })
        } else if (dataAdventure != null) {
            supportActionBar?.title = dataAdventure

            halamanKategoriViewModel.dataListAdventure().observe(this, { listAdventure ->
                if (listAdventure != null) {
                    halamanKategoriAdapter.setData(listAdventure as ArrayList<Movies>)
                }
            })
        } else if (dataAComedy != null) {
            supportActionBar?.title = dataAComedy

            halamanKategoriViewModel.dataListComedy().observe(this, { listComedy ->
                if (listComedy != null) {
                    halamanKategoriAdapter.setData(listComedy as ArrayList<Movies>)
                }
            })
        } else if (dataHorror != null) {
            supportActionBar?.title = dataHorror

            halamanKategoriViewModel.dataListHorror().observe(this, { listHorror ->
                if (listHorror != null) {
                    halamanKategoriAdapter.setData(listHorror as ArrayList<Movies>)
                }
            })
        }

        with(activityKategoriBinding.rvKategori) {
            layoutManager = GridLayoutManager(this@KategoriActivity, 2)
            adapter = halamanKategoriAdapter
        }
    }
}