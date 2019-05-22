package io.zhihao.library.android.util

/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-05-16 22:38

 */
class StringUtil {
    companion object {
        fun isChinese(string: String) = this.isChinese(this.getChar(string))

        fun isChinese(char: Char): Boolean {
            val ub = Character.UnicodeBlock.of(char)
            return (ub === Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                    || ub === Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                    || ub === Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                    || ub === Character.UnicodeBlock.GENERAL_PUNCTUATION
                    || ub === Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                    || ub === Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS)
        }

        fun getChar(string: String): Char = string.single()
    }
}