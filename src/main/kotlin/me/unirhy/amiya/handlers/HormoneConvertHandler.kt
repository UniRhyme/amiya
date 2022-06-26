package me.unirhy.amiya.handlers

import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.content

// Testosterone ng/dL -> nmol/L: * 0.0347, nmol/L -> ng/dL: * 28.8
// Estradiol pg/mL -> pmol/L: * 3.67, pmol/L -> pg/mL: * 0.272

val hormoneConvertHandler = handler<GroupMessageEvent> {
    if (!message.content.startsWith("Hormone")) return@handler

}