package io.rot.labs.projectconf.ui.eventsItem

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.Observer
import io.rot.labs.projectconf.data.model.EventItem
import io.rot.labs.projectconf.di.component.ViewHolderComponent
import io.rot.labs.projectconf.ui.base.BaseItemViewHolder
import io.rot.labs.projectconf.utils.display.ImageUtils.getTopicDrawableResId
import io.rot.labs.projectconf.utils.display.ImageUtils.loadImageDrawable
import kotlinx.android.synthetic.main.item_event.view.eventCardContainer
import kotlinx.android.synthetic.main.item_event.view.ivTopic
import kotlinx.android.synthetic.main.item_event.view.tvCityCountry
import kotlinx.android.synthetic.main.item_event.view.tvConfName
import kotlinx.android.synthetic.main.item_event.view.tvStartDate
import kotlinx.android.synthetic.main.item_event.view.tvTopicTitle
import kotlinx.android.synthetic.main.item_event_header.view.tvEventPeriod

class EventsItemViewHolder(
    @LayoutRes layoutId: Int,
    parent: ViewGroup
) : BaseItemViewHolder<EventItem, EventsItemViewModel>(layoutId, parent) {

    override fun injectDependencies(buildComponent: ViewHolderComponent) {
        buildComponent.inject(this)
    }

    override fun setupView(view: View) {

        itemView.eventCardContainer?.setOnClickListener {
            // go to events details
        }
    }

    override fun setUpObservables() {
        super.setUpObservables()

        viewModel.eventPeriod.observe(this, Observer {
            it?.let {
                itemView.tvEventPeriod.text = it
            }
        })

        viewModel.topic.observe(this, Observer {
            it?.let {
                itemView.tvTopicTitle.text = it
                loadImageDrawable(itemView.context, getTopicDrawableResId(it), itemView.ivTopic)
            }
        })

        viewModel.place.observe(this, Observer {
            it?.let {
                itemView.tvCityCountry.text = it
            }
        })

        viewModel.name.observe(this, Observer {
            it?.let {
                itemView.tvConfName.text = it
            }
        })

        viewModel.day.observe(this, Observer {
            it?.let {
                itemView.tvStartDate.text = it
            }
        })
    }
}
