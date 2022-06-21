package me.unirhy.amiya.handlers

import net.mamoe.mirai.event.AbstractEvent

abstract class IHandler(e: AbstractEvent) {
    var event: AbstractEvent
    init {
        event = e
    }

    abstract fun handle()
}