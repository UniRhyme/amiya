package me.unirhy.amiya.handlers

import me.unirhy.amiya.PluginMain
import net.mamoe.mirai.event.AbstractEvent
import net.mamoe.mirai.utils.MiraiLogger

abstract class IHandler(e: AbstractEvent) {
    var event: AbstractEvent
    private var logger: MiraiLogger
    init {
        event = e
        logger = PluginMain.logger
    }

    abstract suspend fun handle()
}