package com.kirilpetriv.artwork

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kirilpetriv.artwork.util.getFormattedDescription
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ArtworkExtInstrumentationTest {

    @Test
    fun getFormattedDescription_passes_with_correct_expectation() {
        val rawString = "<p>The front of ... facing right.<br>On the ... between them.</p>\n"
        val expectedString = "The front of ... facing right.\nOn the ... between them.\n"
        assertEquals(
            expectedString,
            getFormattedDescription(rawString).text
        )
    }
}
