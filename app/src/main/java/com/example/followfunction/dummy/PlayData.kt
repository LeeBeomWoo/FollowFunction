package bodygate.bcns.bodygation.dummy

import java.util.*

class PlayDataItem(val url: String, val progress: Int, val alpha: Int): Observable() {
    /// The first name of the user
    var page_url: String = ""
        set(value) {
            field = value
            setChangedAndNotify("url")
        }

    /// The age of the user
    var webprogress: Int = 0
        set(value) {
            field = value
            setChangedAndNotify("progress")
        }
    var al: Int = 0
        set(value) {
            field = value
            setChangedAndNotify("alpha")
        }
    private fun setChangedAndNotify(field: Any)
    {
        setChanged()
        notifyObservers(field)
    }
    }