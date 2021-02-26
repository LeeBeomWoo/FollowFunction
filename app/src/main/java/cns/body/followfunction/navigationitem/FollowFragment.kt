package cns.body.followfunction.navigationitem

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.followfunction.R
import com.example.followfunction.R.layout
import com.example.followfunction.R.string
import kotlinx.android.synthetic.main.select_view.*
import kotlinx.coroutines.runBlocking
import kotlinx.android.synthetic.main.select_view.txtB_vimeo_url as txtB_vimeo_url1
import kotlinx.android.synthetic.main.select_view.txtB_youtube_search as txtB_youtube_search1
import kotlinx.android.synthetic.main.select_view.txtB_youtube_url as txtB_youtube_url1

class FollowFragment : Fragment(), View.OnClickListener {
    private val TAG = "FollowFragment"
    @Suppress("PLUGIN_WARNING")
    override fun onClick(p0: View?) {
        when (p0!!.id){
            R.id.btn_youtube_search -> consume {
                if (TextUtils.isEmpty(this.txtB_youtube_search1.text.toString())) {
                    BLayout_youtube_search.error = "유튜브에서 검색하고 싶은 단어를 입력 후 터치하여 주세요";
                    return;
                } else {
                    mListener!!.sendsection = 1
                    mListener!!.sendtype = 0
                    Log.i(TAG, "btn_youtube_search")
                    mListener!!.searchWord = txtB_youtube_search1.text.toString()
                    onButtonPressed(txtB_youtube_search1.text.toString())
                    Log.i(TAG, txtB_youtube_search1.text.toString())
                }
            }
            R.id.btn_youtube_url -> consume {
                if(TextUtils.isEmpty(txtB_youtube_url1.text.toString())){
                    BLayout_youtube_url.error = "유튜브의 공유하기에서 url을 복사하여 붙여넣어 주세요";
                    return;
                }else {
                    mListener!!.sendsection = 2
                    mListener!!.sendtype = 0
                    mListener!!.showVideo(txtB_youtube_url1.text.toString().substringAfterLast("/"))
                    Log.i(TAG, "btn_youtube_url")
                }
            }/*
            R.id.btn_vimeo_search -> consume {
                Log.i(TAG, "search_Btn")
                onButtonPressed(txtB_vimeo_search.text.toString())
                Log.i(TAG, txtB_vimeo_search.text.toString())
                mListener!!.sendsection = 1
                mListener!!.sendtype = 1f
            }*/
            R.id.btn_vimeo_url -> consume {
                if(TextUtils.isEmpty(txtB_vimeo_url1.text.toString())){
                    BLayout_vimeo_url.error = "비메오의 공유하기에서 url을 복사하여 붙여넣어 주세요";
                    return;
                }else {
                    mListener!!.sendsection = 2
                    mListener!!.sendtype = 1
                    mListener!!.showVideo(txtB_vimeo_url1.text.toString().substringAfterLast("/"))
                    Log.i(TAG, "btn_vimeo_url")
                }
            }
        }
    }
    private inline fun consume(f: () -> Unit): Boolean {
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
        return inflater.inflate(layout.select_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btn_youtube_search.setOnClickListener(this)
        btn_youtube_url.setOnClickListener(this)
        btn_vimeo_url.setOnClickListener(this)
       // btn_vimeo_search.setOnClickListener(this)
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
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
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
        var viideoId:String?
        var searchWord:String?
    }
    companion object {

        fun newInstance() =
                FollowFragment().apply {
                    arguments = Bundle().apply {                                      }
                }
    }
}// Required empty public constructor
