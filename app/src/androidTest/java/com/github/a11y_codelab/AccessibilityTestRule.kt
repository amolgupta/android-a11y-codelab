package com.github.a11y_codelab

import androidx.test.espresso.accessibility.AccessibilityChecks
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import com.google.android.apps.common.testing.accessibility.framework.AccessibilityCheckResultUtils.matchesViews
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.hamcrest.CoreMatchers.anyOf
import org.hamcrest.CoreMatchers.containsString
import org.junit.rules.ExternalResource

class AccessibilityTestRule : ExternalResource() {
    override fun before() {
        AccessibilityChecks
            .enable()
            .setRunChecksFromRootView(true)
            .setThrowExceptionForErrors(true)
            .setSuppressingResultMatcher(
                matchesViews(
                    anyOf(
                        withClassName(containsString(FloatingActionButton::class.simpleName))
                    )
                )
            )
    }
} 