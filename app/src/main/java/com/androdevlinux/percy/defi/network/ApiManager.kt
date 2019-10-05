package  com.androdevlinux.percy.defi.network

import com.androdevlinux.percy.defi.network.apis.PocketbitsApiImpl
import com.androidevlinux.percy.UTXO.data.models.pocketbits.PocketBitsBean
import io.reactivex.Observable

class ApiManager private constructor() {


    private val pocketbitsApiImpl: PocketbitsApiImpl = PocketbitsApiImpl.instance

    val pocketbitsTicker: Observable<PocketBitsBean>
        get() = pocketbitsApiImpl.pocketbitsTicker

    companion object {

        private var apiManager: ApiManager? = null

        val instance: ApiManager
            get() {
                if (apiManager == null) {
                    apiManager = ApiManager()
                }
                return apiManager!!
            }
    }
}
