package me.unirhy.amiya

import me.unirhy.amiya.handlers.GroupMessageHandler
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.BotInvitedJoinGroupRequestEvent
import net.mamoe.mirai.event.events.FriendMessageEvent
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.events.NewFriendRequestEvent
import net.mamoe.mirai.message.data.Image
import net.mamoe.mirai.message.data.Image.Key.queryUrl
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.utils.MiraiLogger
import net.mamoe.mirai.utils.info

/**
 * 使用 kotlin 版请把
 * `src/main/resources/META-INF.services/net.mamoe.mirai.console.plugin.jvm.JvmPlugin`
 * 文件内容改成 `org.example.mirai.plugin.me.unirhy.amiya.PluginMain` 也就是当前主类全类名
 *
 * 使用 kotlin 可以把 java 源集删除不会对项目有影响
 *
 * 在 `settings.gradle.kts` 里改构建的插件名称、依赖库和插件版本
 *
 * 在该示例下的 [JvmPluginDescription] 修改插件名称，id和版本，etc
 *
 * 可以使用 `src/test/kotlin/RunMirai.kt` 在 ide 里直接调试，
 * 不用复制到 mirai-console-loader 或其他启动器中调试
 */

object PluginMain : KotlinPlugin(
    JvmPluginDescription(
        id = "me.unirhy.amiya",
        name = "Amiya",
        version = "0.1.0"
    ) {
        author("Rabbit0w0 <rabbit0w0@outlook.com>")
        // author 和 info 可以删除.
    }
) {

    override fun onEnable() {
        logger.info { "阿米娅冲鸭" }
        //配置文件目录 "${dataFolder.absolutePath}/"

        val eventChannel = GlobalEventChannel.parentScope(this)
        eventChannel.subscribeAlways<GroupMessageEvent>{
            GroupMessageHandler(it).handle()
        }
//        eventChannel.subscribeAlways<FriendMessageEvent>{
//            //好友信息
//            sender.sendMessage("hi")
//        }
//        eventChannel.subscribeAlways<NewFriendRequestEvent>{
//            //自动同意好友申请
//            accept()
//        }
//        eventChannel.subscribeAlways<BotInvitedJoinGroupRequestEvent>{
//            //自动同意加群申请
//            accept()
//        }
    }
}
