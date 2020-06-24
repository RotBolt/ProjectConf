package io.rot.labs.projectconf.ui.alerts.alertsNotification

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import io.rot.labs.projectconf.R
import io.rot.labs.projectconf.data.local.db.entity.EventEntity
import io.rot.labs.projectconf.di.component.ActivityComponent
import io.rot.labs.projectconf.ui.base.BaseActivity
import io.rot.labs.projectconf.ui.eventsItem.EventsItemAdapter
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_alerts_view.matToolBarAlerts
import kotlinx.android.synthetic.main.activity_alerts_view.rvAlertEvents

class AlertsNotificationActivity : BaseActivity<AlertsNotificationViewModel>() {

    companion object {
        const val ALERTS_LIST = "alerts_list"
    }

    @Inject
    lateinit var eventsItemAdapter: EventsItemAdapter

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var gridLayoutManager: GridLayoutManager

    override fun injectDependencies(buildComponent: ActivityComponent) {
        buildComponent.inject(this)
    }

    override fun provideLayoutId(): Int = R.layout.activity_alerts_view

    @Suppress("UNCHECKED_CAST")
    override fun setupView(savedInstanceState: Bundle?) {

        setSupportActionBar(matToolBarAlerts)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        setupRecyclerView()

        val alertsList = intent.getParcelableArrayListExtra<EventEntity>(ALERTS_LIST)
        viewModel.getEvents(alertsList!!)
    }

    override fun setupObservables() {
        super.setupObservables()
        viewModel.events.observe(this, Observer {
            eventsItemAdapter.updateData(it)
        })
    }

    private fun setupRecyclerView() {
        rvAlertEvents.apply {
            adapter = eventsItemAdapter

            layoutManager = if (screenUtils.isPortrait()) {
                linearLayoutManager
            } else {
                gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (eventsItemAdapter.getItemViewType(position)) {
                            EventsItemAdapter.HEADER -> 2
                            else -> 1
                        }
                    }
                }
                gridLayoutManager
            }
        }
    }
}
