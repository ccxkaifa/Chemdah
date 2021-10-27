package ink.ptms.chemdah.core.conversation.theme

import ink.ptms.chemdah.core.conversation.Session
import taboolib.library.configuration.ConfigurationSection
import taboolib.library.xseries.XSound
import taboolib.module.chat.colored

/**
 * Chemdah
 * ink.ptms.chemdah.core.conversation.ThemeChatSettings
 *
 * @author sky
 * @since 2021/2/12 2:08 上午
 */
class ThemeChatSettings(
    root: ConfigurationSection,
    val format: List<String> = root.getStringList("format").map { it.colored() },
    val selectChar: String = root.getString("select.char", "")!!,
    val selectOther: String = root.getString("select.other", "")!!,
    val selectColor: String = root.getString("select.color", "")!!.colored(),
    val talking: String = root.getString("talking", "")!!.colored(),
    val animation: Boolean = root.getBoolean("animation", true),
    val spaceLine: Int = root.getInt("space-line", 30),
    val useScroll: Boolean = root.getBoolean("use-scroll")
) : ThemeSettings(root) {

    val selectSound: XSound? = XSound.matchXSound(root.getString("select.sound.name").toString()).orElse(null)
    val selectSoundPitch = root.getDouble("select.sound.p").toFloat()
    val selectSoundVolume = root.getDouble("select.sound.v").toFloat()

    fun playSelectSound(session: Session) {
        selectSound?.play(session.player, selectSoundPitch, selectSoundVolume)
    }

    override fun toString(): String {
        return "ThemeChatSettings(" +
                "format=$format, " +
                "selectChar='$selectChar', " +
                "selectOther='$selectOther', " +
                "selectColor='$selectColor', " +
                "talking='$talking', " +
                "animation=$animation" +
                ")"
    }
}