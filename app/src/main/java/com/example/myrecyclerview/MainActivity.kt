package com.example.myrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var rvHeroes: RecyclerView
    private var list:ArrayList<Hero> = arrayListOf()
    private var title: String = "Error Bosss"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBarTitle(title)

        rvHeroes = findViewById(R.id.rv_hero)
        rvHeroes.setHasFixedSize(true)
        list.addAll(HeroesData.listData)
        showRecyclerList()
    }

    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null){
            (supportActionBar as ActionBar).title = title
        }
    }

    private fun showRecyclerList() {
        rvHeroes.layoutManager = LinearLayoutManager(this)
        val listHeroAdapter = ListHeroAdapter(list)
        rvHeroes.adapter = listHeroAdapter
        listHeroAdapter.setOnItemClickCallback(object : ListHeroAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Hero) {
                showSelectedHero(data) }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode){
            R.id.action_list -> {
                title ="Mode List"
                showRecyclerList()
            }
            R.id.action_grid -> {
                title ="Mode Grid"
                showRecycleGrid()
            }
            R.id.action_cardview -> {
                title ="Mode CardView"
                showRecyclerCardView()
            }

        }
    }
    private fun showRecycleGrid(){
        rvHeroes.layoutManager = GridLayoutManager(this, 2)
        val gridHeroAdapter = GridHeroAdapter(list)
        rvHeroes.adapter = gridHeroAdapter
            gridHeroAdapter.setOnItemClickCallBack(object: GridHeroAdapter.onItemClickCallBack {
                override fun onItemClicked(data: Hero) {
                    showSelectedHero(data)
                }
            })
    }
    private fun showRecyclerCardView(){
        rvHeroes.layoutManager= LinearLayoutManager(this)
        val cardViewHeroAdapter = CardViewHeroAdapter(list)
        rvHeroes.adapter = cardViewHeroAdapter
    }
    private fun showSelectedHero(hero: Hero){
        Toast.makeText(this, "Kamu memilih" + hero.name,Toast.LENGTH_SHORT).show()
    }

}