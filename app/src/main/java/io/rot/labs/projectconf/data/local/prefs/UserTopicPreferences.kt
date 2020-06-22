package io.rot.labs.projectconf.data.local.prefs

import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserTopicPreferences @Inject constructor(private val prefs: SharedPreferences) {

    companion object {
        const val KEY_USER_TOPICS = "key_user_topics"
    }

    fun edit(userTopicPrefs: List<String>) {

        val serialized = userTopicPrefs.joinToString()
        prefs.edit().putString(KEY_USER_TOPICS, serialized).apply()
    }

    fun getUserTopics(): List<String>? {
        val userTopicsString = prefs.getString(KEY_USER_TOPICS, "")
        if (userTopicsString.isNullOrEmpty()) {
            return null
        }
        return userTopicsString.split(", ")
    }
}
