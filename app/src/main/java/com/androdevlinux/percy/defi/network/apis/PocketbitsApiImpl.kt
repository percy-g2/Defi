package com.androdevlinux.percy.defi.network.apis

import com.androdevlinux.percy.defi.NativeUtils
import com.androidevlinux.percy.UTXO.data.models.pocketbits.PocketBitsBean
import com.androidevlinux.percy.UTXO.data.network.apis.AbstractBaseApi
import com.androidevlinux.percy.UTXO.data.network.apis.PocketbitsAPI

import io.reactivex.Observable

/**
 * Created by percy on 20/1/18.
 */

class PocketbitsApiImpl private constructor() : AbstractBaseApi<PocketbitsAPI>() {
    private val pocketbitsAPI: PocketbitsAPI
    val pocketbitsTicker: Observable<PocketBitsBean>
        get() = pocketbitsAPI.pocketbitsTicker

    init {
        setBaseUrl(NativeUtils.pocketbitsBaseUrl)
        pocketbitsAPI = getClient(PocketbitsAPI::class.java)

    }

    companion object {

        private var pocketbitsApiManager: PocketbitsApiImpl? = null

        val instance: PocketbitsApiImpl
            get() {
                if (pocketbitsApiManager == null)
                    pocketbitsApiManager = PocketbitsApiImpl()
                return pocketbitsApiManager!!
            }
    }
}