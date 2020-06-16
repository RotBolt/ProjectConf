package io.rot.labs.projectconf.di.component

import dagger.Component
import io.rot.labs.projectconf.di.ActivityScope
import io.rot.labs.projectconf.di.module.ActivityModule
import io.rot.labs.projectconf.ui.allTech.AllTechActivity
import io.rot.labs.projectconf.ui.archive.ArchiveActivity
import io.rot.labs.projectconf.ui.eventDetails.EventDetailsActivity
import io.rot.labs.projectconf.ui.eventsList.EventsListActivity
import io.rot.labs.projectconf.ui.main.MainActivity
import io.rot.labs.projectconf.ui.search.SearchActivity
import io.rot.labs.projectconf.ui.settings.SettingsActivity

@ActivityScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(activity: MainActivity)

    fun inject(activity: ArchiveActivity)

    fun inject(activity: SettingsActivity)

    fun inject(activity: SearchActivity)

    fun inject(activity: EventsListActivity)

    fun inject(activity: EventDetailsActivity)

    fun inject(activity: AllTechActivity)
}
