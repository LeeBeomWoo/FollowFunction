package bodygate.bcns.bodygation.dummy

import java.util.*

class PlayViewModel(val user: PlayDataItem): java.util.Observer {
    override fun update(p0: Observable?, p1: Any?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    init {
        user.addObserver(this)
    }
    var url:String = ""
    var progress:Int = 0
    var alpha:Int = 0
    val datalist: MutableList<PlayDataItem> = ArrayList()
    var data:PlayDataItem? = null
    fun setData(page:String, pro:Int, al:Int){
        url = page
        progress =pro
        alpha = al
        data = user
        datalist.add(user)
    }
    fun getData():MutableList<PlayDataItem>{
        return datalist
    }
}