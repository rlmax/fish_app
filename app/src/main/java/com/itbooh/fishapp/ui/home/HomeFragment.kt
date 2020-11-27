package com.itbooh.fishapp.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itbooh.fishapp.R
import com.itbooh.fishapp.data.model.Fish
import com.itbooh.fishapp.ui.details.DetailsActivity
import com.itbooh.fishapp.ui.main.MainActivity
import com.itbooh.fishapp.utils.AdmobAdaptiveBannerAdController

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter : HomeListAdapter
    lateinit var ACTIVITY: MainActivity
    private lateinit var adbanner : AdmobAdaptiveBannerAdController

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true);
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.text_home)
        val progressBar_home = root.findViewById<LinearLayout>(R.id.progressBar_home)
        recyclerView.layoutManager = GridLayoutManager(this.context,2)
         adapter = HomeListAdapter(homeViewModel.appDatabase.fishDao().selectAllFish())
        if(homeViewModel.appDatabase.fishDao().selectAllFish().size > 0) {
            progressBar_home.visibility = View.GONE
            recyclerView.adapter = adapter

        }else{
            homeViewModel.homeData.observe(viewLifecycleOwner, Observer {
                progressBar_home.visibility = View.GONE
                recyclerView.adapter = adapter
                adapter.replaceItems(homeViewModel.appDatabase.fishDao().selectAllFish())
            })
        }
        adapter.setItemClickListener(object : HomeListAdapter.ItemClickListener {
            override fun onItemClick(view: View, position: Int, items: Fish) {

                val intent = Intent (this@HomeFragment.context, DetailsActivity::class.java)
                intent.putExtra("fishId",items.id)
                intent.putExtra("fishName",items.story_title)
                startActivity(intent)

            }

        })
        adbanner = AdmobAdaptiveBannerAdController()
         adbanner.initializeAdBanner(ACTIVITY,R.id.adViewContainer,R.string.banner_id_home,root,true)
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val search = menu.findItem(R.id.action_search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        val searchData: MutableList<Fish> = homeViewModel.appDatabase.fishDao().selectAllFish()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { Log.d("fff", it) }
                if (newText != null) {
                    if (newText.length > 0) {
                        val searchItems: MutableList<Fish> =
                            homeViewModel.appDatabase.fishDao().loadSearch(newText)
                        adapter.replaceItems(searchItems)
                    }else{
                        adapter.replaceItems(homeViewModel.appDatabase.fishDao().selectAllFish())
                    }
                }
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        ACTIVITY = context as MainActivity
    }


}
