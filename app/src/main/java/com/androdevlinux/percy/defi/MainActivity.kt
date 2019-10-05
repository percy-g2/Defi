package com.androdevlinux.percy.defi

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
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
import java.util.*


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
        val cal = Calendar.getInstance()
        val myIntent = Intent(this, MainActivity::class.java)
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val pendingIntent = PendingIntent.getService(this, 0, myIntent, 0)

        // get current time
        val currentTime = Calendar.getInstance()

        // setup time for alarm
        val alarmTime = Calendar.getInstance()

        // set time-part of alarm
        alarmTime.set(Calendar.SECOND, 0)
        alarmTime.set(Calendar.MINUTE, 0)
        alarmTime.set(Calendar.HOUR, 3)
        alarmTime.set(Calendar.AM_PM, Calendar.PM)
        alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY)

        // check if it in the future
        if (currentTime.getTimeInMillis() < alarmTime.getTimeInMillis()) {
            // nothing to do - time of alarm in the future
        } else {
            var dayDiffBetweenClosestFriday =
                (7 + cal.get(Calendar.DAY_OF_WEEK) - cal.get(Calendar.DAY_OF_WEEK)) % 7

            if (dayDiffBetweenClosestFriday == 0) {
                // Today is Friday, but current time after 3pm, so schedule for the next Friday
                dayDiffBetweenClosestFriday = 7
            }

            alarmTime.add(Calendar.DAY_OF_MONTH, dayDiffBetweenClosestFriday)
        }

        // calculate interval (7 days) in ms
        val interval = 1000 * 60 * 60 * 24 * 7

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            alarmTime.getTimeInMillis(),
            interval.toLong(),
            pendingIntent
        )
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
                        myIntent )
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
