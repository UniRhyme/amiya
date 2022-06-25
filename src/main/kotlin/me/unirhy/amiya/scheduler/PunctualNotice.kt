package me.unirhy.amiya.scheduler

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.concurrent.Executors.newSingleThreadExecutor

class PunctualNotice(
    executor: CoroutineScope = CoroutineScope(newSingleThreadExecutor().asCoroutineDispatcher())
): CoroutineScope by executor {
    var action: () -> Unit = {}

    fun start() {
        launch {
            while (true) {
                // If the second is 0, then do the action
                if (Calendar.getInstance().get(Calendar.SECOND) == 0) {
                    action()
                }
                // Wait for the next second
                delay(1000)
            }
        }
    }
}