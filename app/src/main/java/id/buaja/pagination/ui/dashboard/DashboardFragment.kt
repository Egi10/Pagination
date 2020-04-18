package id.buaja.pagination.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.buaja.pagination.R
import id.buaja.pagination.network.ApiService
import id.buaja.pagination.network.model.ResultsItem
import id.buaja.pagination.util.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var dashboardAdapter: DashboardAdapter
    private var list: MutableList<ResultsItem> = mutableListOf()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
                ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {

        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefresh.post {
            getMovie(1)
        }

        swipeRefresh.setOnRefreshListener {
            list.clear()
            getMovie(1)
        }

        dashboardAdapter = DashboardAdapter(list) {

        }
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = dashboardAdapter
        recyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(layoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                Log.d("swiperefreshloadmore", page.toString())
                getMovie(page)
            }
        })
    }

    private fun getMovie(page: Int) {
        swipeRefresh.isRefreshing = true
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ApiService::class.java)
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    service.getMovie(
                        apiKey = "86d0e4ed0cc15d8f65e2ccdf5250f4bd",
                        language = "en-US",
                        page = page
                    )
                }

                if (response.isSuccessful) {
                    swipeRefresh.isRefreshing = false
                    response.body()?.results?.let {
                        list.addAll(it)
                    }
                    dashboardAdapter.notifyDataSetChanged()
                } else {
                    swipeRefresh.isRefreshing = false
                    Log.d("Error", response.message())
                }
            } catch (e: Exception) {
                swipeRefresh.isRefreshing = false
                Log.d("Error", e.message.toString())
            }
        }
    }
}
