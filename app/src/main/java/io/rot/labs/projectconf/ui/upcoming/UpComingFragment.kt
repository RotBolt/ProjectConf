package io.rot.labs.projectconf.ui.upcoming

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import io.rot.labs.projectconf.R
import io.rot.labs.projectconf.di.component.FragmentComponent
import io.rot.labs.projectconf.ui.base.BaseFragment
import io.rot.labs.projectconf.ui.events.EventAdapter
import io.rot.labs.projectconf.ui.upcoming.banner.BannerViewModel
import io.rot.labs.projectconf.ui.upcoming.banner.TechBannerAdapter
import io.rot.labs.projectconf.ui.upcoming.banner.ZoomOutPageTransformer
import io.rot.labs.projectconf.utils.display.ScreenResourcesHelper
import kotlinx.android.synthetic.main.fragment_upcoming.*
import kotlinx.android.synthetic.main.layout_distinct_languages.*
import javax.inject.Inject

class UpComingFragment : BaseFragment<UpComingViewModel>() {


    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var gridLayoutManager: GridLayoutManager

    @Inject
    lateinit var eventAdapter: EventAdapter

    @Inject
    lateinit var techBannerAdapter: TechBannerAdapter

    @Inject
    lateinit var zoomOutPageTransformer: ZoomOutPageTransformer

    @Inject
    lateinit var bannerViewModel: BannerViewModel

    @Inject
    lateinit var screenUtils: ScreenResourcesHelper

    companion object {
        const val TAG = "UpComingFragment"
        fun newInstance(): UpComingFragment {
            val args = Bundle()
            val fragment = UpComingFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun provideLayoutId(): Int = R.layout.fragment_upcoming

    override fun injectDependencies(buildComponent: FragmentComponent) {
        buildComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {

        setUpBanner()

        setUpDistinctLanguages()

        setupRecyclerView()
    }

    override fun setupObservables() {
        super.setupObservables()
        viewModel.upcomingEvents.observe(this, Observer {
            eventAdapter.updateData(it)
        })

        bannerViewModel.bannerPosition.observe(this, Observer {
            techBannerPager.setCurrentItem(it, true)
        })

        viewModel.progress.observe(this, Observer {
            if (it) {
                upComingEventsContainer.isVisible = false
                shimmerUpComing.apply {
                    isVisible = true
                    startShimmer()
                }
                aviLoader.apply {
                    isVisible = true
                    smoothToShow()
                }
            } else {
                upComingEventsContainer.isVisible = true
                shimmerUpComing.apply {
                    isVisible = false
                    stopShimmer()
                }
                aviLoader.apply {
                    isVisible = false
                    smoothToHide()
                }
            }
        })
    }


    fun setupRecyclerView() {
        rvEvents.apply {
            adapter = eventAdapter
            layoutManager = if (screenUtils.isPortrait()) {
                linearLayoutManager
            } else {

                gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (eventAdapter.getItemViewType(position)) {
                            EventAdapter.HEADER -> 2
                            else -> 1
                        }
                    }
                }
                gridLayoutManager
            }
        }
    }

    private fun setUpBanner() {
        val slidingDots = Array(TechBannerAdapter.NUM_BANNERS) { ImageView(context) }

        for (i in slidingDots.indices) {
            slidingDots[i].setImageDrawable(
                ContextCompat.getDrawable(
                    context!!,
                    R.drawable.non_active_indicator
                )
            )
            val params =
                LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            params.setMargins(8, 0, 8, 0)
            sliderDotsContainer.addView(slidingDots[i], params)
        }

        slidingDots[bannerViewModel.iterator % TechBannerAdapter.NUM_BANNERS].setImageDrawable(
            ContextCompat.getDrawable(
                context!!,
                R.drawable.active_indicator
            )
        )

        techBannerPager.apply {
            adapter = techBannerAdapter
            setPageTransformer(zoomOutPageTransformer)

            currentItem = bannerViewModel.iterator

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    bannerViewModel.iterator = position

                    for (i in slidingDots.indices) {
                        slidingDots[i].setImageDrawable(
                            ContextCompat.getDrawable(
                                context!!,
                                R.drawable.non_active_indicator
                            )
                        )
                    }
                    slidingDots[position % TechBannerAdapter.NUM_BANNERS].setImageDrawable(
                        ContextCompat.getDrawable(
                            context!!,
                            R.drawable.active_indicator
                        )
                    )
                }
            })
        }
    }

    private fun setUpDistinctLanguages() {

        ivPython.setOnClickListener {

        }

        ivGolang.setOnClickListener {

        }

        ivRust.setOnClickListener {

        }

        ivGraphql.setOnClickListener {

        }

        ivShowAll.setOnClickListener {

        }

    }

    override fun onResume() {
        super.onResume()
        bannerViewModel.startAutoIterator()
    }

    override fun onStop() {
        super.onStop()
        bannerViewModel.cancelAutoIterator()
    }
}