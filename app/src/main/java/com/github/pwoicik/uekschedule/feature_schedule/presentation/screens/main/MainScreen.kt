package com.github.pwoicik.uekschedule.feature_schedule.presentation.screens.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.navigation.compose.currentBackStackEntryAsState
import com.github.pwoicik.uekschedule.feature_schedule.presentation.screens.NavGraphs
import com.github.pwoicik.uekschedule.feature_schedule.presentation.screens.allGroups.AllGroupsScreen
import com.github.pwoicik.uekschedule.feature_schedule.presentation.screens.destinations.AllGroupsScreenDestination
import com.github.pwoicik.uekschedule.feature_schedule.presentation.screens.destinations.SavedGroupsScreenDestination
import com.github.pwoicik.uekschedule.feature_schedule.presentation.screens.destinations.ScheduleScreenDestination
import com.github.pwoicik.uekschedule.feature_schedule.presentation.screens.savedGroups.SavedGroupsScreen
import com.github.pwoicik.uekschedule.feature_schedule.presentation.screens.schedule.ScheduleScreen
import com.github.pwoicik.uekschedule.feature_schedule.presentation.screens.main.components.MainScreenScaffold
import com.github.pwoicik.uekschedule.feature_schedule.presentation.screens.navDestination
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.navigation.DestinationsNavController
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.navigateTo
import timber.log.Timber

@OptIn(ExperimentalAnimationApi::class)
@Destination(start = true)
@Composable
fun MainScreen(
    parentNavigator: DestinationsNavigator
) {
    val navController = rememberAnimatedNavController()
    val currentDestination = navController.currentBackStackEntryAsState()
        .value?.navDestination

    SideEffect {
        Timber.tag("mainScreen navGraph destination")
            .d(currentDestination?.route.toString())
    }

    MainScreenScaffold(
        currentDestination = currentDestination,
        onDestinationClick = { destination ->
            navController.popBackStack(NavGraphs.mainScreen.startRoute.route, inclusive = false)
            navController.navigateTo(destination.direction) {
                launchSingleTop = true
            }
        }
    ) {
        DestinationsNavHost(
            navGraph = NavGraphs.mainScreen,
            navController = navController
        ) {
            composable(ScheduleScreenDestination) {
                ScheduleScreen(parentNavigator = parentNavigator)
            }
            composable(SavedGroupsScreenDestination) {
                SavedGroupsScreen(
                    parentNavigator = parentNavigator,
                    navigator = DestinationsNavController(
                        navController = navController,
                        navBackStackEntry = navBackStackEntry
                    )
                )
            }
            composable(AllGroupsScreenDestination) {
                AllGroupsScreen(parentNavigator = parentNavigator)
            }
        }
    }
}
