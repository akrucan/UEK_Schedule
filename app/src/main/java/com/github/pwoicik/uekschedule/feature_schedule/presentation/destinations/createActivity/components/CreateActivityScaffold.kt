package com.github.pwoicik.uekschedule.feature_schedule.presentation.destinations.createActivity.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.pwoicik.uekschedule.R
import com.github.pwoicik.uekschedule.feature_schedule.presentation.components.SnackbarWithError
import com.google.accompanist.insets.statusBarsPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateActivityScaffold(
    snackbarHostState: SnackbarHostState,
    onSaveChanges: () -> Unit,
    contentPadding: PaddingValues,
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        topBar = {
            Surface {
                Text(
                    text = stringResource(R.string.create_new_activity),
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier
                        .statusBarsPadding()
                        .padding(horizontal = 16.dp, vertical = 24.dp)
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onSaveChanges,
                modifier = Modifier.padding(contentPadding)
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = stringResource(R.string.save_activity)
                )
            }
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState) { snackbarData ->
                SnackbarWithError(
                    snackbarData = snackbarData
                )
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .padding(contentPadding)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
            ) {
                content()
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
