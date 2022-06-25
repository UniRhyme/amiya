package me.unirhy.amiya.handlers

import me.unirhy.amiya.PluginMain
import net.mamoe.mirai.event.AbstractEvent
import net.mamoe.mirai.utils.MiraiLogger

abstract class IHandler<T : AbstractEvent> {
    private var logger: MiraiLogger = PluginMain.logger

    abstract suspend fun handle(e: T)
}


fun <T : AbstractEvent> handler(block: suspend T.() -> Unit): IHandler<T> = object : IHandler<T>() {
    override suspend fun handle(e: T) {
        block(e)
    }
}