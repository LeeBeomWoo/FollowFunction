package com.example.followfunction.navigationitem

import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.followfunction.R
import com.example.followfunction.YoutubeResultListViewAdapter
import com.google.api.services.youtube.model.SearchResult
import kotlinx.coroutines.runBlocking


/**
 * Created by LeeBeomWoo on 2018-03-23.
 */

private const val ARG_PARAM1 = "img"
class YouTubeResult : androidx.fragment.app.Fragment() {
    // TODO: Rename and change types of parameters
    val TAG = "YouTubeResult"
    private var mParam1: String? = null
    private var mParam2: Int = 0
    var adapter: YoutubeResultListViewAdapter? = null
    private var mListener: OnYoutubeResultInteraction? = null
    lateinit var result_list: RecyclerView
    /** Global instance of the max number of videos we want returned (50 = upper limit per page).  */

    /** Global instance of Youtube object to make all API requests.  */
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i(TAG, "onCreate")
        super.onCreate(savedInstanceState)
        setRetainInstance(true)
        arguments?.let {
            mParam1 = it.getString(ARG_PARAM1)
            mParam2 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        Log.i(TAG, "onCreateView")
        val rootvie = inflater.inflate(R.layout.fragment_follow, container, false)
        result_list = rootvie.findViewById(R.id.result_list)
        return rootvie
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i(TAG, "onActivityCreated")
        // Set the adapter
        val pop_linearLayoutManager = LinearLayoutManager(context)
        pop_linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL)
        result_list.layoutManager = pop_linearLayoutManager
        adapter = YoutubeResultListViewAdapter(mListener!!.data, context!!){ s: String ->
            mListener!!.showVideo(s)}
        result_list.setAdapter(adapter)
        result_list.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (result_list.isActivated)
                    return
                val visibleItemCount = pop_linearLayoutManager.getChildCount()
                val totalItemCount = pop_linearLayoutManager.getItemCount()
                val pastVisibleItems = pop_linearLayoutManager.findFirstVisibleItemPosition()
                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                    //End of list
                    if(mListener!!.totalpage > 0){
                        runBlocking{mListener!!.getNetxtPage(mListener!!.sendquery!!, getString(R.string.API_key), 5,true)}
                        adapter!!.setLkItems(mListener!!.data)
                    }
                }
            }
        })
        Log.i(TAG, result_list.adapter!!.itemCount.toString())
        Log.i(TAG, "onActivityCreated_final")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnYoutubeResultInteraction) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onStart() {
        super.onStart()

    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnYoutubeResultInteraction {
        fun getpage():String
        var data: MutableList<SearchResult>
        val context:Context
        var visableFragment:String
        var totalpage:Int
        var sendquery:String?
        suspend fun getNetxtPage(q: String, api_Key: String, max_result: Int, more:Boolean)
        fun showVideo(s: String)
        var result_list: RecyclerView
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */


        fun newInstance(param1: String) =
            YouTubeResult().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}// Required empty public constructor
