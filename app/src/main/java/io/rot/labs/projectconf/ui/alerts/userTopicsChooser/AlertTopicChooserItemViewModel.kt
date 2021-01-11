/*
 *  Created by Rohan Maity on 11/1/21 3:19 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 11/1/21 3:12 PM
 */

package io.rot.labs.projectconf.ui.alerts.userTopicsChooser

import androidx.lifecycle.Transformations
import io.reactivex.disposables.CompositeDisposable
import io.rot.labs.projectconf.ui.base.BaseItemViewModel
import io.rot.labs.projectconf.utils.network.NetworkDBHelper
import io.rot.labs.projectconf.utils.rx.SchedulerProvider
import javax.inject.Inject

class AlertTopicChooserItemViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkDBHelper: NetworkDBHelper
) : BaseItemViewModel<Pair<String, Boolean>>(
    schedulerProvider,
    compositeDisposable,
    networkDBHelper
) {
    override fun onCreate() {}

    val name = Transformations.map(data) {
        it.first
    }

    val isChosen = Transformations.map(data) {
        it.second
    }
}
