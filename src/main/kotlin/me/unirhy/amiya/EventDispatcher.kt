package me.unirhy.amiya

import kotlinx.coroutines.runBlocking
import me.unirhy.amiya.handlers.IHandler
import net.mamoe.mirai.event.AbstractEvent

class EventDispatcher {
    private val listeners = mutableMapOf<String, MutableList<IHandler<out AbstractEvent>>>()

    fun <T : AbstractEvent> addListener(event : String, listener: IHandler<T>) {
        listeners[event] = listeners[event]?.apply {
            add(listener)
        } ?: mutableListOf(listener)
    }

    fun <T : AbstractEvent> removeListener(event: String, listener: IHandler<T>) {
        listeners[event] = listeners[event]?.apply {
            remove(listener)
        } ?: mutableListOf()
    }

    fun <T : AbstractEvent> dispatch(event: T) {
        listeners[event.javaClass.name]?.forEach {
            runBlocking {
                (it as IHandler<T>).handle(event)
            }
        }
    }
}
