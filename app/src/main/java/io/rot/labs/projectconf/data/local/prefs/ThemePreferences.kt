package io.rot.labs.projectconf.data.local.prefs

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ThemePreferences @Inject constructor(val prefs: SharedPreferences) {

    companion object {
        const val KEY_USER_THEME = "key_user_theme"
    }

    fun save(themeMode: Int) {
        prefs.edit().putInt(KEY_USER_THEME, themeMode).apply()
    }

    fun getThemeMode(): Int {
        return prefs.getInt(KEY_USER_THEME, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
}
