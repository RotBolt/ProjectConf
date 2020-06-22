package io.rot.labs.projectconf.di.module

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import io.rot.labs.projectconf.data.local.prefs.UserTopicPreferences
import io.rot.labs.projectconf.data.repository.BookmarksRepository
import io.rot.labs.projectconf.data.repository.EventsRepository
import io.rot.labs.projectconf.ui.alerts.alertsView.AlertsAdapter
import io.rot.labs.projectconf.ui.alerts.alertsView.AlertsViewModel
import io.rot.labs.projectconf.ui.alerts.userTopicsChooser.AlertTopicChooserAdapter
import io.rot.labs.projectconf.ui.bookmarks.BookmarksViewModel
import io.rot.labs.projectconf.ui.eventsItem.EventsItemAdapter
import io.rot.labs.projectconf.ui.main.MainSharedViewModel
import io.rot.labs.projectconf.ui.twitter.TwitterViewModel
import io.rot.labs.projectconf.ui.upcoming.UpComingViewModel
import io.rot.labs.projectconf.ui.upcoming.banner.BannerViewModel
import io.rot.labs.projectconf.ui.upcoming.banner.TechBannerAdapter
import io.rot.labs.projectconf.utils.ViewModelProviderFactory
import io.rot.labs.projectconf.utils.display.ZoomOutPageTransformer
import io.rot.labs.projectconf.utils.network.NetworkDBHelper
import io.rot.labs.projectconf.utils.rx.SchedulerProvider

@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
    fun provideLinearLayoutManager() = LinearLayoutManager(fragment.context)

    @Provides
    fun provideGridLayoutManager(): GridLayoutManager = GridLayoutManager(fragment.context, 2)

    @Provides
    fun provideEventAdapter(): EventsItemAdapter =
        EventsItemAdapter(fragment.lifecycle, ArrayList())

    @Provides
    fun provideTechBannerAdapter(): TechBannerAdapter =
        TechBannerAdapter(fragment.requireActivity())

    @Provides
    fun provideAlertAdapter() =
        AlertsAdapter(
            fragment.lifecycle,
            arrayListOf()
        )

    @Provides
    fun provideAlertTopicChooserAdapter(): AlertTopicChooserAdapter = AlertTopicChooserAdapter(
        fragment.lifecycle,
        arrayListOf(),
        mutableSetOf()
    )

    @Provides
    fun provideZoomOutTransformer(): ZoomOutPageTransformer =
        ZoomOutPageTransformer()

    @Provides
    fun provideBannerViewModel(): BannerViewModel =
        ViewModelProvider(fragment).get(BannerViewModel::class.java)

    @Provides
    fun provideUpComingViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkDBHelper: NetworkDBHelper,
        eventsRepository: EventsRepository
    ): UpComingViewModel {
        return ViewModelProvider(fragment, ViewModelProviderFactory(UpComingViewModel::class) {
            UpComingViewModel(
                schedulerProvider,
                compositeDisposable,
                networkDBHelper,
                eventsRepository
            )
        }).get(UpComingViewModel::class.java)
    }

    @Provides
    fun provideTwitterViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkDBHelper: NetworkDBHelper
    ): TwitterViewModel {
        return ViewModelProvider(fragment, ViewModelProviderFactory(TwitterViewModel::class) {
            TwitterViewModel(schedulerProvider, compositeDisposable, networkDBHelper)
        }).get(TwitterViewModel::class.java)
    }

    @Provides
    fun provideAlertsViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkDBHelper: NetworkDBHelper,
        userTopicPreferences: UserTopicPreferences
    ): AlertsViewModel {
        return ViewModelProvider(
            fragment.requireActivity(),
            ViewModelProviderFactory(AlertsViewModel::class) {
                AlertsViewModel(
                    schedulerProvider,
                    compositeDisposable,
                    networkDBHelper,
                    userTopicPreferences
                )
            }).get(AlertsViewModel::class.java)
    }

    @Provides
    fun provideBookmarksViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkDBHelper: NetworkDBHelper,
        bookmarksRepository: BookmarksRepository
    ): BookmarksViewModel {
        return ViewModelProvider(fragment, ViewModelProviderFactory(BookmarksViewModel::class) {
            BookmarksViewModel(schedulerProvider, compositeDisposable, networkDBHelper, bookmarksRepository)
        }).get(BookmarksViewModel::class.java)
    }

    @Provides
    fun provideMainSharedViewModel(
        schedulerProvider: SchedulerProvider,
        compositeDisposable: CompositeDisposable,
        networkDBHelper: NetworkDBHelper
    ): MainSharedViewModel {
        return ViewModelProvider(
            fragment.requireActivity(),
            ViewModelProviderFactory(MainSharedViewModel::class) {
                MainSharedViewModel(schedulerProvider, compositeDisposable, networkDBHelper)
            }).get(MainSharedViewModel::class.java)
    }
}
