package com.github.a11y_codelab

import androidx.test.espresso.accessibility.AccessibilityChecks
import org.junit.rules.ExternalResource

class AccessibilityTestRule : ExternalResource() {
    override fun before() {
        AccessibilityChecks
            .enable()
            .setRunChecksFromRootView(true)
            .setThrowExceptionForErrors(true)
    }
} 