package me.unirhy.amiya.handlers

import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.content

class GroupMessageHandler(e: GroupMessageEvent) : IHandler(e) {
    override suspend fun handle() {
        var e = event as GroupMessageEvent
        var messageChain = e.message
        if (messageChain.content.endsWith("(") || messageChain.content.endsWith("（")) {
            e.group.sendMessage("让我看看谁还没有把括号补齐?")
        }
    }
}