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

        if (messageChain.content.startsWith("Cup")) {
            val args = messageChain.contentToString().split(" ")
            try {
                if (args.size == 3 && args[2].toInt() - args[1].toInt() >= 0) {
                    val diff = args[2].toInt() - args[1].toInt()
                    val underbust = args[1].toInt()
                    val cup = when (diff / 2) {
                        5 -> "AA"
                        6 -> "A"
                        7 -> "B"
                        8 -> "C"
                        9 -> "D"
                        10 -> "E"
                        11 -> "F"
                        12 -> "G"
                        13 -> "H"
                        14 -> "I"
                        15 -> "J"
                        16 -> "K"
                        17 -> "L"
                        18 -> "M"
                        19 -> "N"
                        20 -> "O"
                        21 -> "P"
                        22 -> "R"
                        23 -> "S"
                        24, 25 -> "T"
                        else -> "/"
                    }
                    val size = underbust - (underbust % 5)
                    var msg = "你的罩杯是$size$cup"
                    if (cup == "/") {
                        msg = if (diff / 2 <= 5) {
                            "小妹妹你还不需要穿内衣哟~"
                        } else {
                            "对不起, 您的尺寸太大了, 我们无法计算您的罩杯"
                        }
                    }
                    e.group.sendMessage(msg)
                } else {
                    e.group.sendMessage("Oops, wrong arguments!\nUsage: Cup <under-bust> <over-bust>\nUnder-bust: rf下侧的胸围\nOver-bust: 过rt的胸围")
                }
            } catch (e1: Exception) {
                e.group.sendMessage("参数不符")
            }
        }
    }
}