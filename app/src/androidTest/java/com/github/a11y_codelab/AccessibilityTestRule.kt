package com.github.a11y_codelab

import androidx.test.espresso.accessibility.AccessibilityChecks
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckResultUtils
import org.hamcrest.CoreMatchers
import org.junit.rules.ExternalResource

class AccessibilityTestRule : ExternalResource() {
    override fun before() {
        AccessibilityChecks
            .enable()
            .setRunChecksFromRootView(true)
            .setThrowExceptionForErrors(true)
            .setSuppressingResultMatcher(
                AccessibilityCheckResultUtils.matchesViews(
                    CoreMatchers.anyOf(
                        withId(R.id.user)
                    )
                )
            )
    }
} 