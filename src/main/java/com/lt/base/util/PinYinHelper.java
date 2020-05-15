package com.lt.base.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by GaoShan on 2017/7/7.
 *
 * @see 如果出现问题，可能是多音字问题
 */
public class PinYinHelper {

    /**
     * 将字符串中的中文转化为拼音,其他字符不变
     *
     * @param src
     * @return
     */
    public static String getPingYin(String src) {
        if (src != null) {
            StringBuilder resultStr = new StringBuilder();
            char[] tagCharArr = src.toCharArray();
            String[] temp;
            HanyuPinyinOutputFormat hanyuPinyinOutputFormat = getHanyuPinyinOutputFormat();
            hanyuPinyinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
            try {
                for (char c : tagCharArr) {
                    // 判断是否为汉字字符
                    if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                        temp = PinyinHelper.toHanyuPinyinStringArray(c, hanyuPinyinOutputFormat);
                        resultStr.append(temp[0]);
                    } else
                        resultStr.append(c);
                }
                return resultStr.toString();
            } catch (BadHanyuPinyinOutputFormatCombination e1) {
                e1.printStackTrace();
            }
            return resultStr.toString();
        }
        return "-1";
    }

    /**
     * 获取汉字串拼音首字母，英文字符不变
     *
     * @param src 汉字串
     * @return 汉语拼音首字母
     */
    public static String getFirstSpell(String src) {
        if (src != null) {
            HanyuPinyinOutputFormat hanyuPinyinOutputFormat = getHanyuPinyinOutputFormat();
            StringBuilder resultStr = new StringBuilder();
            char[] tagCharArr = src.toCharArray();
            String[] temp;

            try {
                for (char c : tagCharArr) {
                    if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                        temp = PinyinHelper.toHanyuPinyinStringArray(c, hanyuPinyinOutputFormat);
                        if (temp != null) {
                            resultStr.append(temp[0].charAt(0));
                        }
                    } else {
                        resultStr.append(c);
                    }
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
            return resultStr.toString().replaceAll("\\W", "").trim();
        }
        return "-1";

    }

    /**
     * 获取汉字串拼音，英文字符不变
     *
     * @param src 汉字串
     * @return 汉语拼音
     */
    public static String getFullSpell(String src) {
        if (src != null) {
            HanyuPinyinOutputFormat hanyuPinyinOutputFormat = getHanyuPinyinOutputFormat();
            StringBuilder resultStr = new StringBuilder();
            char[] tagCharArr = src.toCharArray();
            String[] temp;

            try {
                for (char c : tagCharArr) {
                    if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                        temp = PinyinHelper.toHanyuPinyinStringArray(c, hanyuPinyinOutputFormat);
                        if (temp != null) {
                            resultStr.append(temp[0]);
                        }
                    } else {
                        resultStr.append(c);
                    }
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
            return resultStr.toString();
        }
        return "-1";

    }

    private static HanyuPinyinOutputFormat getHanyuPinyinOutputFormat() {
        HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
        hanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        return hanyuPinyinOutputFormat;
    }


}
