/*
 *  Created by Rohan Maity on 11/1/21 3:19 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 11/1/21 3:12 PM
 */

package io.rot.labs.projectconf.data.local.db

import io.reactivex.Completable
import io.reactivex.Single
import io.rot.labs.projectconf.data.local.db.entity.EventEntity
import io.rot.labs.projectconf.utils.testHelper.AndroidTestHelper
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.util.Date
import javax.inject.Singleton

@Singleton
class FakeEventsDatabaseService(database: ConfDatabase) : EventsDatabaseService(database) {

    var toThrowConnectException = false
    var toThrowTimeOutException = false
    var toGiveEmptyList = false

    companion object {
        private const val COULD_NOT_CONNECT = "Could not connect"

        private const val TIMEOUT = "timeout"
    }

    override fun insertEvents(list: List<EventEntity>): Completable {
        return Completable.complete()
    }

    override fun getUpComingEventsFromCurrentYear(): Single<List<EventEntity>> {

        return Single.just(AndroidTestHelper.fakeEventEntityList).flatMap {
            when {
                toThrowConnectException -> {
                    throw ConnectException(COULD_NOT_CONNECT)
                }
                toThrowTimeOutException -> {
                    throw SocketTimeoutException(TIMEOUT)
                }
                else -> {
                    Single.just(it)
                }
            }
        }
    }

    override fun getEventsForYear(year: Int): Single<List<EventEntity>> {
        return Single.just(AndroidTestHelper.fakeEventEntityList).flatMap {
            when {
                toThrowConnectException -> {
                    throw ConnectException(COULD_NOT_CONNECT)
                }
                toThrowTimeOutException -> {
                    throw SocketTimeoutException(TIMEOUT)
                }
                else -> {
                    Single.just(it)
                }
            }
        }
    }

    override fun getEventsForYearAndTech(
        topics: List<String>,
        year: Int
    ): Single<List<EventEntity>> {

        if (toGiveEmptyList) {
            return Single.just(emptyList())
        }

        return Single.just(AndroidTestHelper.fakeEventEntityList).flatMap {
            when {
                toThrowConnectException -> {
                    throw ConnectException(COULD_NOT_CONNECT)
                }
                toThrowTimeOutException -> {
                    throw SocketTimeoutException(TIMEOUT)
                }
                else -> {
                    Single.just(it)
                }
            }
        }
    }

    override fun getUpComingEventsForTech(topics: List<String>): Single<List<EventEntity>> {

        if (toGiveEmptyList) {
            return Single.just(emptyList())
        }

        return Single.just(AndroidTestHelper.fakeEventEntityList).flatMap {
            when {
                toThrowConnectException -> {
                    throw ConnectException(COULD_NOT_CONNECT)
                }
                toThrowTimeOutException -> {
                    throw SocketTimeoutException(TIMEOUT)
                }
                else -> {
                    Single.just(it)
                }
            }
        }
    }

    override fun getUpComingEventsForCurrentMonth(): Single<List<EventEntity>> {
        return Single.just(AndroidTestHelper.fakeEventEntityList).flatMap {
            when {
                toThrowConnectException -> {
                    throw ConnectException(COULD_NOT_CONNECT)
                }
                toThrowTimeOutException -> {
                    throw SocketTimeoutException(TIMEOUT)
                }
                else -> {
                    Single.just(it)
                }
            }
        }
    }

    override fun getEventDetails(
        name: String,
        startDate: Date,
        topic: String
    ): Single<EventEntity> {

        val eventEntity = when (name) {
            "PragmaConf" -> AndroidTestHelper.fakeEventEntityList[0]
            "KotlinersConf" -> AndroidTestHelper.fakeEventEntityList[1]
            "Kubecon" -> AndroidTestHelper.fakeEventEntityList[2]
            "Cloudnative" -> AndroidTestHelper.fakeEventEntityList[3]
            "Rustlang" -> AndroidTestHelper.fakeEventEntityList[4]
            else -> null
        }

        return Single.just(eventEntity)
    }

    override fun getEventsByQuery(
        nameQuery: String,
        yearList: List<Int>
    ): Single<List<EventEntity>> {

        val eventEntityList = when (nameQuery) {
            "Pragma" -> listOf(AndroidTestHelper.fakeEventEntityList[0])
            "Kotlin" -> listOf(AndroidTestHelper.fakeEventEntityList[1])
            "Kube" -> listOf(AndroidTestHelper.fakeEventEntityList[2])
            "Cloud" -> listOf(AndroidTestHelper.fakeEventEntityList[3])
            "Rust" -> listOf(AndroidTestHelper.fakeEventEntityList[4])
            else -> emptyList()
        }

        return Single.just(eventEntityList)
    }

    override fun getUpComingEventsForUserTechCurrentMonth(topics: List<String>): Single<List<EventEntity>> {
        return Single.just(AndroidTestHelper.fakeEventEntityList)
    }
}
