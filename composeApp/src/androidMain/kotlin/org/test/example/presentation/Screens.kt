package org.test.example.presentation

import com.slack.circuit.runtime.screen.Screen
import dev.icerock.moko.parcelize.Parcelable
import kotlinx.parcelize.Parcelize

actual object Screens {
    @Parcelize
    actual data object HomScreen : Screen, Parcelable
}