package me.unirhy.amiya.handlers

import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.content
import kotlin.math.roundToInt

private val splitRegex = Regex("\\s+")

private const val description = "Oops, wrong arguments!\n" +
    "Usage: Cup <under-bust> <over-bust>\n" +
    "Under-bust: rf下侧的胸围\n" +
    "Over-bust: 过rt的胸围"

val cupSizeHandler = handler<GroupMessageEvent> {

    if (!message.content.startsWith("Cup ")) return@handler

    // drop first one, it's "Cup "
    val args = message.contentToString().split(splitRegex).drop(1)

    // ignore args after the first two
    if (args.size < 2) {
        group.sendMessage(description)
        return@handler
    }

    val (under, over) = args.take(2).map(String::toIntOrNull)

    under ?: group.sendMessage("请输入整数")
    under ?: return@handler

    over ?: group.sendMessage("请输入整数")
    over ?: return@handler

    val diff = over - under

    if (diff < 0) {
        group.sendMessage("过胸围不能小于下胸围")
        return@handler
    }

    val cup = calculateCup((diff / 2.0f).roundToInt())
    val size = if (under % 5 > 2) under + 5 - under % 5 else under - under % 5

    group.sendMessage(
        when (cup) {
            "/" ->
                if (diff / 2 <= 5) "小妹妹你还不需要穿内衣哟~"
                else "对不起, 您的尺寸太大了, 我们无法计算您的罩杯"

            else -> "姐姐你的罩杯是$size$cup"
        }
    )
}

private fun calculateCup(size: Int) = when (size) {
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