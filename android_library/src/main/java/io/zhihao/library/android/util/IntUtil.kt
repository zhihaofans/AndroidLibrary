package io.zhihao.library.android.util

/**
 * 在此写用途

 * @author: zhihaofans

 * @date: 2019-05-22 21:49

 */
class IntUtil {
    companion object {
        fun isEvenNumber(int: Int): Boolean {
            // 是否为偶数
            return int % 1 == 0
        }

        fun isOddNumber(int: Int): Boolean {
            // 是否为奇数
            return int % 1 != 0
        }
    }
}