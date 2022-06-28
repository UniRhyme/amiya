package me.unirhy.amiya.handlers

import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.content
import java.util.Calendar

val nickMap = mapOf(
    401395069L to "瑞秋",
    1928925202L to "小黑",
    1193778291L to "flash",
)

val getNick: (Long) -> String = {
    nickMap[it] ?: "博士"
}

val morningKeywords = listOf("早上好", "早安", "早上坏", "早哇", "早呀", "早喵", "早喔")
val morningGreetBack: (Long) -> String = {
    listOf(
        "早呀, ${getNick(it)}",
        "早上好, ${getNick(it)}",
        "早哇, ${getNick(it)}",
        "早呀, ${getNick(it)}",
        "早早早, ${getNick(it)}",
        "早安哟, ${getNick(it)}",
    ).random()
}

val noonKeywords = listOf("中午好", "午好")
val noonGreetBack: (Long) -> String = {
    listOf(
        "中午好, ${getNick(it)}",
        "午好, ${getNick(it)}",
    ).random()
}

val eveningKeywords = listOf("晚上好", "晚好")
val eveningGreetBack: (Long) -> String = {
    listOf(
        "晚上好, ${getNick(it)}",
        "晚好, ${getNick(it)}",
    ).random()
}

val nightKeywords = listOf("晚安", "睡啦")
val nightGreetBack: (Long) -> String = {
    listOf(
        "晚安哟, ${getNick(it)}",
        "好梦呀, ${getNick(it)}",
    ).random()
}

val greeterHandler = handler<GroupMessageEvent> {
    if (message.contentToString().length >= 8) return@handler
    when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
        in 6..10 -> {
            if (morningKeywords.any { it in message.contentToString() } || message.contentToString() == "早") {
                group.sendMessage(morningGreetBack(sender.id))
            }
        }
        in 11..13 -> {
            if (noonKeywords.any { it in message.contentToString() }) {
                group.sendMessage(noonGreetBack(sender.id))
            }
        }
        in 17..23 -> {
            if (eveningKeywords.any { it in message.contentToString() }) {
                group.sendMessage(eveningGreetBack(sender.id))
                return@handler
            }
            if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) >= 20 &&
                nightKeywords.any { it in message.contentToString() }) {
                group.sendMessage(nightGreetBack(sender.id))
            }
        }
    }
}
