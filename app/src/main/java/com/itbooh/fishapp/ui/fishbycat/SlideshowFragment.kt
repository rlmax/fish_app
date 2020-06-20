package com.itbooh.fishapp.ui.fishbycat

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.itbooh.fishapp.R
import com.itbooh.fishapp.data.model.Fish
import com.itbooh.fishapp.ui.details.DetailsActivity
import com.itbooh.fishapp.ui.home.HomeListAdapter
import com.itbooh.fishapp.utils.AdmobAdaptiveBannerAdController

class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel
    private lateinit var adapter : HomeListAdapter
    private lateinit var adbanner : AdmobAdaptiveBannerAdController
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true);
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        if(getArguments() != null) {
            val catData :MutableList<Fish>  =  slideshowViewModel.appDatabase.fishDao()
                .loadCatId(arguments?.get("catId").toString())
            if(catData.size >0 ){
                textView.visibility = View.GONE
                val recyclerView = root.findViewById<RecyclerView>(R.id.text_home)
                recyclerView.layoutManager = GridLayoutManager(this.context, 2)

                 adapter = HomeListAdapter(
                    slideshowViewModel.appDatabase.fishDao()
                        .loadCatId(arguments?.get("catId").toString())
                )
                recyclerView.adapter = adapter
                adapter.setItemClickListener(object : HomeListAdapter.ItemClickListener {
                    override fun onItemClick(view: View, position: Int, items: Fish) {

                        val intent = Intent (this@SlideshowFragment.context, DetailsActivity::class.java)
                        intent.putExtra("fishId",items.id)
                        intent.putExtra("fishName",items.story_title)
                        startActivity(intent)

                    }

                })
            }else{
                textView.visibility = View.VISIBLE
                textView.text = "No data Found !"
            }

        }else{
            textView.visibility = View.VISIBLE
            textView.text = "No data Found !"
        }
        adbanner = AdmobAdaptiveBannerAdController()
        activity?.let { adbanner.initializeAdBanner(it,R.id.adViewContainer,R.string.banner_details,root,true) }
        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val search = menu.findItem(R.id.action_search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    if (newText.length > 0) {
                        val searchItems: MutableList<Fish> =
                            slideshowViewModel.appDatabase.fishDao().loadCatIdSearch(arguments?.get("catId").toString(),newText)
                        adapter.replaceItems(searchItems)
                    }else{
                        adapter.replaceItems(slideshowViewModel.appDatabase.fishDao().loadCatId(arguments?.get("catId").toString()))
                    }
                }
                return true
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }
}
