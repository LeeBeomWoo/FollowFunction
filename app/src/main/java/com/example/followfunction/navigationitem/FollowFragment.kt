package com.example.followfunction.navigationitem

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.followfunction.R
import com.example.followfunction.R.*
import kotlinx.android.synthetic.main.select_view.*
import kotlinx.coroutines.runBlocking


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FollowFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [FollowFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val ARG_PARAM1 = "img"
class FollowFragment : Fragment(), View.OnClickListener {
    private lateinit var search_Btn: Button

    val TAG = "FollowFragment"
    lateinit var mParam1: String
    @SuppressLint("RestrictedApi")
    override fun onClick(p0: View?) {
        when (p0!!.id){
            R.id.btn_youtube_search -> consume {
                Log.i(TAG, "search_Btn")
                onButtonPressed(txtB_youtube_search.text.toString())
                Log.i(TAG, txtB_youtube_search.text.toString())
                mListener!!.sendsection = 1
            }
            R.id.btn_youtube_url -> consume {
                mListener!!.showVideo(txtB_youtube_url.text.toString())
                mListener!!.sendsection = 2
                Log.i(TAG, "play_Btn")
            }
            R.id.btn_vimeo_search -> consume {
                Log.i(TAG, "search_Btn")
                onButtonPressed(txtB_vimeo_search.text.toString())
                Log.i(TAG, txtB_vimeo_search.text.toString())
                mListener!!.sendsection = 1
            }
            R.id.btn_vimeo_url -> consume {
                mListener!!.showVideo(txtB_vimeo_url.text.toString())
                mListener!!.sendsection = 2
                Log.i(TAG, "play_Btn")
            }
        }
    }
    inline fun consume(f: () -> Unit): Boolean {
        f()
        return true
    }

    private var mListener: OnFollowInteraction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootview = inflater.inflate(layout.select_view, container, false)

        return rootview
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        search_Btn.setOnClickListener(this)
        mListener!!.visableFragment = TAG

    }

    fun onButtonPressed(uri: String)= runBlocking{
        if (mListener != null) {
            mListener!!.getDatas("snippet", uri, getString(string.API_key), 5, true)}
        Log.i("test", "네번째")
        mListener!!.OnFollowInteraction(uri, 0)
        Log.i("test", "여섯번째")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFollowInteraction) {
            mListener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
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
    interface OnFollowInteraction {
        // TODO: Update argument type and name
        fun OnFollowInteraction(q: String, s:Int)
        var visableFragment:String
        suspend fun getDatas(part: String, q: String, api_Key: String, max_result: Int, more:Boolean)
        fun showVideo(s: String)
        var sendsection:Int
        var sendtype:Int
    }
    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FollowFragment.
         */
        // TODO: Rename and change types and number of parameters

        fun newInstance() =
                FollowFragment().apply {
                    arguments = Bundle().apply {                                      }
                }
    }
}// Required empty public constructor
