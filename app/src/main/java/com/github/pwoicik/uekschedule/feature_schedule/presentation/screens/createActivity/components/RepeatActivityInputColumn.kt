package com.github.pwoicik.uekschedule.feature_schedule.presentation.screens.createActivity.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.github.pwoicik.uekschedule.R
import com.github.pwoicik.uekschedule.feature_schedule.presentation.components.Switch
import com.github.pwoicik.uekschedule.feature_schedule.presentation.screens.createActivity.CreateActivityState
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

@Composable
fun RepeatActivityInputColumn(
    state: CreateActivityState,
    onRepeatActivityChange: (Boolean) -> Unit,
    onStartDateSelect: (LocalDate) -> Unit,
    onSelectDayToRepeat: (DayOfWeek) -> Unit,
    onUnselectDayToRepeat: (DayOfWeek) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .animateContentSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.repeat_activity),
                fontWeight = FontWeight.Medium
            )
            Switch(
                checked = state.repeatActivity,
                onCheckedChange = onRepeatActivityChange
            )
        }

        Crossfade(state.repeatActivity) {
            when (it) {
                true -> {
                    DaysOfWeekSelectionRow(
                        selectedDays = state.repeatOnDaysOfWeek,
                        onSelectDayToRepeat = onSelectDayToRepeat,
                        onUnselectDayToRepeat = onUnselectDayToRepeat
                    )
                }
                false -> {
                    DateInputField(
                        date = state.startDate,
                        onDateSelect = onStartDateSelect,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
fun DaysOfWeekSelectionRow(
    selectedDays: Set<DayOfWeek>,
    onSelectDayToRepeat: (DayOfWeek) -> Unit,
    onUnselectDayToRepeat: (DayOfWeek) -> Unit
) {
    val daysOfWeek by remember {
        derivedStateOf {
            DayOfWeek.values().associate {
                it to it.getDisplayName(
                    TextStyle.SHORT_STANDALONE,
                    Locale.getDefault()
                )
            }
        }
    }

    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .run {
                if (selectedDays.isEmpty())
                    border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.error,
                        shape = RoundedCornerShape(10.dp)
                    )
                else this
            }
    ) {
        FlowRow(
            mainAxisSize = SizeMode.Expand,
            mainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly,
            mainAxisSpacing = 6.dp,
            crossAxisSpacing = 4.dp,
            modifier = Modifier
                .selectableGroup()
                .padding(8.dp)
        ) {
            for ((dayOfWeek, displayName) in daysOfWeek) {
                val isSelected = dayOfWeek in selectedDays
                val color = animateColorAsState(
                    if (isSelected) {
                        MaterialTheme.colorScheme.secondary
                    } else {
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f)
                    }
                )

                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp, vertical = 8.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .border(
                            width = if (isSelected) 2.dp else 1.dp,
                            color = color.value,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .selectable(
                            selected = isSelected,
                            role = Role.Checkbox
                        ) {
                            if (isSelected) {
                                onUnselectDayToRepeat(dayOfWeek)
                            } else {
                                onSelectDayToRepeat(dayOfWeek)
                            }
                        }
                ) {
                    Text(
                        text = displayName,
                        modifier = Modifier
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }
}
