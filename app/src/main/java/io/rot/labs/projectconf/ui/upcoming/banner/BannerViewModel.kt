package io.rot.labs.projectconf.ui.upcoming.banner

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*


/**
 *  BannerViewModel
 *  To make the autoscroll of viewPager LifeCycleAware
 *  Position will be emitted only when the Activity/Fragment is in Visible Lifecyle
 */
class BannerViewModel : ViewModel() {

    val bannerPosition = MutableLiveData<Int>()

    var iterator = 0

    private val timer = Timer()

    fun startAutoIterator() {
        timer.schedule(object : TimerTask() {
            override fun run() {
                bannerPosition.postValue(iterator++)
            }
        }, 3000, 3000)
    }

    override fun onCleared() {
        timer.cancel()
        super.onCleared()
    }
}