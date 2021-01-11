/*
 *  Created by Rohan Maity on 11/1/21 3:19 PM
 *  Copyright (c) 2021 . All rights reserved.
 *  Last modified 11/1/21 3:12 PM
 */

package io.rot.labs.projectconf.data.repository

import io.reactivex.Single
import io.rot.labs.projectconf.data.local.db.ConfDatabase
import io.rot.labs.projectconf.data.local.db.entity.BookmarkedEvent
import io.rot.labs.projectconf.utils.testHelper.AndroidTestHelper
import javax.inject.Inject

class FakeBookmarksRepository @Inject constructor(database: ConfDatabase) :
    BookmarksRepository(database) {

    override fun getCFPReminderEvents(): Single<List<BookmarkedEvent>> {
        return Single.just(
            listOf(
                BookmarkedEvent(
                    AndroidTestHelper.fakeEventEntityList[3],
                    true,
                    123,
                    AndroidTestHelper.cfpDateFuture
                )
            )
        )
    }
}
