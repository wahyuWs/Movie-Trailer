package com.kelompok7.rpl.movietrailer.ui.halamanutama

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kelompok7.rpl.movietrailer.R
import com.kelompok7.rpl.movietrailer.adapter.*
import com.kelompok7.rpl.movietrailer.databinding.ActivityMainBinding
import com.kelompok7.rpl.movietrailer.model.Movies
import com.kelompok7.rpl.movietrailer.ui.halamanfavorite.FavoriteActivity
import com.kelompok7.rpl.movietrailer.ui.halamankategori.KategoriActivity
import com.kelompok7.rpl.movietrailer.viewmodel.ViewModelFilm
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var halamanUtamaActionAdapter: HalamanUtamaActionAdapter
    private lateinit var halamanUtamaAdventureAdapter: HalamanUtamaAdventureAdapter
    private lateinit var halamanUtamaViewModel: ViewModelFilm
    private lateinit var halamanUtamaComedyAdapter: HalamanUtamaComedyAdapter
    private lateinit var halamanUtamaHorrorAdapter: HalamanUtamaHorrorAdapter
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var slideAdapter: SliderAdapter
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)


        halamanUtamaViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[ViewModelFilm::class.java]
        halamanUtamaActionAdapter = HalamanUtamaActionAdapter()
        halamanUtamaAdventureAdapter = HalamanUtamaAdventureAdapter()
        halamanUtamaComedyAdapter = HalamanUtamaComedyAdapter()
        halamanUtamaHorrorAdapter = HalamanUtamaHorrorAdapter()
        searchAdapter = SearchAdapter()
        slideAdapter = SliderAdapter()

        setBanners()
        setListAction()
        setListAdventure()
        setListComedy()
        setListHorror()
        setListSearch()

        activityMainBinding.iconAction.setOnClickListener(this)
        activityMainBinding.iconAdventure.setOnClickListener(this)
        activityMainBinding.iconComedy.setOnClickListener(this)
        activityMainBinding.iconHoror.setOnClickListener(this)
        activityMainBinding.fabFavorite.setOnClickListener(this)

        activityMainBinding.searchHalamanUtama.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                activityMainBinding.layoutScrollview.visibility = View.GONE
                activityMainBinding.rvSearch.visibility = View.VISIBLE
                searchAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                activityMainBinding.layoutScrollview.visibility = View.GONE
                activityMainBinding.rvSearch.visibility = View.VISIBLE
                searchAdapter.filter.filter(newText)
                return false
            }

        })
    }

    private fun setBanners() {
        val dataBanner = ArrayList<Int>()
        dataBanner.add(R.drawable.ff9)
        dataBanner.add(R.drawable.avengers_endgame_2019)
        dataBanner.add(R.drawable.captain_marvel_2019)

        slideAdapter.setData(dataBanner)

        with(activityMainBinding.bannerPoster) {
            setSliderAdapter(slideAdapter)
            setIndicatorAnimation(IndicatorAnimationType.WORM)
            setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION)
            startAutoCycle()
        }
    }

    private fun setListSearch() {
        halamanUtamaViewModel.dataSearch().observe(this, { data ->
            if (data != null) {
                searchAdapter.dataMovie.addAll(data)
            }
        })

        with(activityMainBinding.rvSearch) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = searchAdapter
        }
    }

    private fun setListAction() {
        halamanUtamaViewModel.dataListAction().observe(this, { lisAction ->
            if (lisAction != null) {
                halamanUtamaActionAdapter.setData(lisAction as ArrayList<Movies>)
            }
        })

        with(activityMainBinding.rvAction) {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = halamanUtamaActionAdapter
        }
    }

    private fun setListAdventure() {
        halamanUtamaViewModel.dataListAdventure().observe(this, { lisAdventure ->
            if (lisAdventure != null) {
                halamanUtamaAdventureAdapter.setData(lisAdventure as ArrayList<Movies>)
            }
        })

        with(activityMainBinding.rvAdventure) {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = halamanUtamaAdventureAdapter
        }
    }

    private fun setListComedy() {
        halamanUtamaViewModel.dataListComedy().observe(this, { listComedy ->
            if (listComedy != null) {
                halamanUtamaComedyAdapter.setData(listComedy as ArrayList<Movies>)
            }
        })

        with(activityMainBinding.rvComedy) {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = halamanUtamaComedyAdapter
        }
    }

    private fun setListHorror() {
        halamanUtamaViewModel.dataListHorror().observe(this, { listHorror ->
            if (listHorror != null) {
                halamanUtamaHorrorAdapter.setData(listHorror as ArrayList<Movies>)
            }
        })

        with(activityMainBinding.rvHoror) {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = halamanUtamaHorrorAdapter
        }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.icon_action -> {
                val intent = Intent(this, KategoriActivity::class.java)
                intent.putExtra(KategoriActivity.EXTRA_DATA_ACTION, "Action")
                startActivity(intent)
            }
            R.id.icon_adventure -> {
                val intent = Intent(this, KategoriActivity::class.java)
                intent.putExtra(KategoriActivity.EXTRA_DATA_ADVENTURE, "Adventure")
                startActivity(intent)
            }
            R.id.icon_comedy -> {
                val intent = Intent(this, KategoriActivity::class.java)
                intent.putExtra(KategoriActivity.EXTRA_DATA_COMEDY, "Comedy")
                startActivity(intent)
            }
            R.id.icon_horor -> {
                val intent = Intent(this, KategoriActivity::class.java)
                intent.putExtra(KategoriActivity.EXTRA_DATA_HORROR, "Horror")
                startActivity(intent)
            }
            R.id.fab_favorite -> {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onBackPressed() {
        activityMainBinding.rvSearch.visibility = View.GONE
        activityMainBinding.layoutScrollview.visibility = View.VISIBLE

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }
}