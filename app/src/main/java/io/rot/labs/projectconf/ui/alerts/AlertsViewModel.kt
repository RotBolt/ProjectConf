package io.rot.labs.projectconf.ui.alerts

import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.rot.labs.projectconf.data.local.prefs.UserTopicPreferences
import io.rot.labs.projectconf.ui.base.BaseViewModel
import io.rot.labs.projectconf.utils.network.NetworkDBHelper
import io.rot.labs.projectconf.utils.rx.SchedulerProvider

class AlertsViewModel(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkDBHelper: NetworkDBHelper,
    private val userTopicPreferences: UserTopicPreferences
) : BaseViewModel(schedulerProvider, compositeDisposable, networkDBHelper) {

    val userTopics = MutableLiveData<List<String>>()

    override fun onCreate() {
        getTopicPreferences()
    }

    fun getTopicPreferences() {
        if (userTopics.value?.isNotEmpty() == true) {
            return
        }
        val topics = userTopicPreferences.getUserTopics()?.sorted() ?: emptyList()
        userTopics.postValue(topics)
    }
}
