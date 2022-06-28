package me.unirhy.amiya.handlers

import net.mamoe.mirai.event.events.GroupMessageEvent
import java.util.Stack

val brackets = mapOf(
    // ASCII
    "(" to ")",
    "[" to "]",
    "{" to "}",
    "<" to ">",

    // Unicode
    "（" to "）",
    "【" to "】",
    "《" to "》",
    "〈" to "〉",
    "〔" to "〕",
    "〖" to "〗",
    "〘" to "〙",
    "〚" to "〛",
    "｛" to "｝",
    "｟" to "｠",
    "｢" to "｣",
    "『" to "』",
)

val parenthesesFunHandler = handler<GroupMessageEvent> {
    val content = message.contentToString()

    val bracketStack = Stack<String>()
    val bracketBackList = brackets.values.toList()
    val bracketFrontList = brackets.keys.toList()
    var msg = "none"

    for (c in content) {
        if (bracketFrontList.contains(c.toString())) {
            bracketStack.push(c.toString())
        } else if (bracketBackList.contains(c.toString())) {
            if (bracketStack.isEmpty()) {
                msg = "让我看看是不是博士又没有把括号补齐....嗷, 补不齐!"
                break
            }
            val front = bracketStack.pop()
            if (brackets[front] != c.toString()) {
                msg = "让我看看是不是博士又没有把括号补齐....嗷, 补不齐!"
                break
            }
        }
    }

    if (!bracketStack.isEmpty()) {
        var trailingComplete = StringBuffer()

        while (!bracketStack.isEmpty()) {
            trailingComplete.append(brackets[bracketStack.pop()])
        }

        msg = "$trailingComplete 让我看看是不是博士又没有把括号补齐?"
    }

    if (msg != "none") {
        group.sendMessage(msg)
    }
}