package me.unirhy.amiya

import me.unirhy.amiya.handlers.cupSizeHandler
import me.unirhy.amiya.handlers.greeterHandler
import me.unirhy.amiya.handlers.parenthesesFunHandler
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.utils.info

// 请让机器人回复的所有内容都符合阿米娅的说话风格

object PluginMain : KotlinPlugin(
    JvmPluginDescription(
        id = "me.unirhy.amiya",
        name = "Amiya",
        version = "0.1.0"
    ) {
        author("Rabbit0w0 <rabbit0w0@outlook.com>")
    }
) {

    private val dispatcher: EventDispatcher = EventDispatcher()

    override fun onEnable() {
        logger.info { "阿米娅冲鸭" }

        // dispatcher.addListener(GroupMessageEvent::class.java.name, cupSizeHandler)

        val eventChannel = GlobalEventChannel.parentScope(this)
        eventChannel.subscribeAlways<GroupMessageEvent> {
            // dispatcher.dispatch(it)
            cupSizeHandler.handle(it)
            parenthesesFunHandler.handle(it)
            greeterHandler.handle(it)
        }
    }
}
