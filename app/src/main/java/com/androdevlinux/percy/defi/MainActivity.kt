package com.androdevlinux.percy.defi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.androdevlinux.percy.defi.network.ApiManager
import com.androidevlinux.percy.UTXO.data.models.pocketbits.PocketBitsBean
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var strRuppeSymbol = "\u20B9 "
    private var pocketBitsBeanObservable: Observable<PocketBitsBean>? = null
    private var disposables: CompositeDisposable? = null
    private var apiManager: ApiManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        apiManager = ApiManager.instance

        getPocketData()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Fuck Ethereum!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            getPocketData()
        }

    }

    private fun getPocketData() {
        pocketBitsBeanObservable = apiManager!!.pocketbitsTicker
        disposables = CompositeDisposable()
        disposables!!.add(pocketBitsBeanObservable!!.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<PocketBitsBean>() {

                override fun onNext(value: PocketBitsBean) {
                    Constants.btc_price = strRuppeSymbol + value.altcoins!![0].altBuyPrice.toString()
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {
                    val notificationHelper = NotificationHelper(this@MainActivity)
                    notificationHelper.createNotification( "Defi 2.0", "You should buy some sats/btc this week : - " +  Constants.btc_price ,
                        Intent(Intent.ACTION_VIEW, Uri.parse("https://pocketbits.in/")) )
                }
            }))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
