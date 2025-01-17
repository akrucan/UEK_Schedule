package com.github.pwoicik.uekschedule.feature_schedule.presentation.screens.main.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.github.pwoicik.uekschedule.feature_schedule.presentation.components.NavigationBar
import com.github.pwoicik.uekschedule.feature_schedule.presentation.screens.main.MainScreenDestination
import com.ramcosta.composedestinations.spec.DestinationSpec

@Composable
fun MainScreenBottomBar(
    currentDestination: DestinationSpec<*>?,
    onDestinationClick: (MainScreenDestination) -> Unit
) {
    NavigationBar {
        MainScreenDestination.values().forEach { destination ->
            val label = stringResource(destination.label)
            NavigationBarItem(
                selected = currentDestination?.route == destination.direction.route,
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = label
                    )
                },
                label = {
                    Text(text = label)
                },
                onClick = { onDestinationClick(destination) }
            )
        }
    }
}
