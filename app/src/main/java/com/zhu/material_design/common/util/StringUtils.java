package com.zhu.material_design.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: StringUtils
 * @description: 字符串工具类，提供一些字符串相关的便捷方法
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class StringUtils {

    /**
     * @MethodName: StringUtils
     * @description: 将构造方法私有化，通过JNI方式实例化此类
     * @author:  Mr.Lee
     */
    private StringUtils() {
        throw new AssertionError();
    }

    /**
     * @MethodName: isBlank
     * @description: 检测字符串是否是空串或者null
     * @author:  Mr.Lee
     * @param str 待检测字符串
     * @return boolean true str为空串或null; false：str不为null且不为空串
     */
    public static boolean isBlank(String str) {
        return (str == null || str.trim().length() == 0);
    }

    /**
     * @MethodName: 检测CharSequence对象是否为null或长度是否为0
     * @description: isEmpty
     * @author:  Mr.Lee
     * @param str
     * @return 如果字符串为null或者长度为0则返回true,否则返回false
     */
    public static boolean isEmpty(CharSequence str) {
        return (str == null || str.length() == 0);
    }

    /**
     * @MethodName: length
     * @description: 返回一个字符序列(字符串)的长度
     * @author:  Mr.Lee
     * @param str 字符序列(字符串)
     * @return int 长度值
     */
    public static int length(CharSequence str) {
        return str == null ? 0 : str.length();
    }

    /**
     * @MethodName: nullStrToEmpty
     * @description: 将一个值为null的对象的值转换成空字符串
     * @author:  Mr.Lee
     * @param str str
     * @return String 如果str==null 则返回"",否则返回字符串本身
     */
    public static String nullStrToEmpty(Object str) {
        return (str == null? "": (str instanceof String ? (String) str : str.toString()));
    }

    /**
     * @MethodName: capitalizeFirstLetter
     * @description: 将英文字符串的首字母大写
     * @author:  Mr.Lee
     * @param str 待处理字符串
     * @return 返回首字母大写的字符串
     */
    public static String capitalizeFirstLetter(String str) {
        if (isEmpty(str)) {
            return str;
        }
        char c = str.charAt(0);
        return (!Character.isLetter(c) || Character.isUpperCase(c))
               ? str : new StringBuilder(str.length()).append(
                       Character.toUpperCase(c)).append(str.substring(1))
                                                .toString();
    }

    /**
     * @MethodName: utf8Encode
     * @description: 对字符串进行UTF-8编码
     * @author:  Mr.Lee
     * @param str 字符串
     * @return 返回一个utf8的字符串
     */
    public static String utf8Encode(String str) {
        if (!isEmpty(str) && str.getBytes().length != str.length()) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("UnsupportedEncodingException occurred. ", e);
            }
        }
        return str;
    }

    /**
     * @MethodName: getHrefInnerHtml
     * @description:
     * @author:  Mr.Lee
     * @param href 字符串
     * @return 返回一个html
     */
    public static String getHrefInnerHtml(String href) {
        if (isEmpty(href)) {
            return "";
        }
        String hrefReg = ".*<[\\s]*a[\\s]*.*>(.+?)<[\\s]*/a[\\s]*>.*";
        Pattern hrefPattern = Pattern.compile(hrefReg,
                Pattern.CASE_INSENSITIVE);
        Matcher hrefMatcher = hrefPattern.matcher(href);
        if (hrefMatcher.matches()) {
            return hrefMatcher.group(1);
        }
        return href;
    }

    /**
     * @MethodName: htmlEscapeCharsToString
     * @description:
     * @author:  Mr.Lee
     * @param source 字符串
     * @return 返回htmL到字符串
     */
    public static String htmlEscapeCharsToString(String source) {
        return StringUtils.isEmpty(source)
               ? source
               : source.replaceAll("&lt;", "<")
                       .replaceAll("&gt;", ">")
                       .replaceAll("&amp;", "&")
                       .replaceAll("&quot;", "\"");
    }

    /**
     * @MethodName: fullWidthToHalfWidth
     * @description:
     * @author:  Mr.Lee
     * @param s str
     * @return String
     */
    public static String fullWidthToHalfWidth(String s) {

        if (isEmpty(s)) {
            return s;
        }

        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == 12288) {
                source[i] = ' ';
                // } else if (source[i] == 12290) {
                // source[i] = '.';
            }
            else if (source[i] >= 65281 && source[i] <= 65374) {
                source[i] = (char) (source[i] - 65248);
            }
            else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }

    /**
     * @MethodName: halfWidthToFullWidth
     * @description:
     * @author:  Mr.Lee
     * @param s 字符串
     * @return 返回的数值
     */
    public static String halfWidthToFullWidth(String s) {

        if (isEmpty(s)) {
            return s;
        }

        char[] source = s.toCharArray();
        for (int i = 0; i < source.length; i++) {
            if (source[i] == ' ') {
                source[i] = (char) 12288;
                // } else if (source[i] == '.') {
                // source[i] = (char)12290;
            }
            else if (source[i] >= 33 && source[i] <= 126) {
                source[i] = (char) (source[i] + 65248);
            }
            else {
                source[i] = source[i];
            }
        }
        return new String(source);
    }

    /**
     * @MethodName: replaceBlanktihuan
     * @description: 替换字符串中的空白
     * @author:  Mr.Lee
     * @param str 资源
     * @return 特殊字符串切换
     */
    public static String replaceBlanktihuan(String str) {

        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * @MethodName: isEmpty
     * @description: 判断给定的字符串是否为null或者是空的
     * @author:  Mr.Lee
     * @param string 给定的字符串
     */
    public static boolean isEmpty(String string) {
        return string == null || "".equals(string.trim());
    }

    /**
     * @MethodName: isNotEmpty
     * @description: 判断给定的字符串是否不为null且不为空
     * @author:  Mr.Lee
     * @param string 给定的字符串
     */
    public static boolean isNotEmpty(String string) {
        return !isEmpty(string);
    }

    /**
     * @MethodName: isEmpty
     * @description: 判断给定的字符串数组中的所有字符串是否都为null或者是空的
     * @author:  Mr.Lee
     * @param strings 给定的字符串
     */
    public static boolean isEmpty(String... strings) {
        boolean result = true;
        for (String string : strings) {
            if (isNotEmpty(string)) {
                result = false;
                break;
            }
        }
        return result;
    }
    
    /**
     * @MethodName: isNotEmpty
     * @description: 判断给定的字符串数组中是否全部都不为null且不为空
     * @author:  Mr.Lee
     * @param strings 给定的字符串数组
     * @return 是否全部都不为null且不为空
     */
    public static boolean isNotEmpty(String... strings) {
        boolean result = true;
        for (String string : strings) {
            if (isEmpty(string)) {
                result = false;
                break;
            }
        }
        return result;
    }
     
    /**
     * @MethodName: filterEmpty
     * @description: 如果字符串是null或者空就返回""
     * @author:  Mr.Lee
     * @param string
     * @return 如果字符串是null或者空就返回""
     */
    public static String filterEmpty(String string) {
        return StringUtils.isNotEmpty(string) ? string : "";
    }
     
    /**
     * @MethodName: replace
     * @description: 在给定的字符串中，用新的字符替换所有旧的字符
     * @author:  Mr.Lee
     * @param string 给定的字符串
     * @param oldchar 旧的字符
     * @param newchar 新的字符
     * @return 替换后的字符串
     */
    public static String replace(String string, char oldchar, char newchar) {
        char chars[] = string.toCharArray();
        for (int w = 0; w < chars.length; w++) {
            if (chars[w] == oldchar) {
                chars[w] = newchar;
                break;
            }
        }
        return new String(chars);
    }
     
    /**
     * @MethodName: split
     * @description: 把给定的字符串用给定的字符分割
     * @author:  Mr.Lee
     * @param string 给定的字符串
     * @param ch 给定的字符
     * @return 分割后的字符串数组
     */
    public static String[] split(String string, char ch) {
        ArrayList<String> stringList = new ArrayList<String>();
        char chars[] = string.toCharArray();
        int nextStart = 0;
        for (int w = 0; w < chars.length; w++) {
            if (ch == chars[w]) {
                stringList.add(new String(chars, nextStart, w - nextStart));
                nextStart = w + 1;
                if (nextStart ==
                        chars.length) {    //当最后一位是分割符的话，就再添加一个空的字符串到分割数组中去
                    stringList.add("");
                }
            }
        }
        if (nextStart <
                chars.length) {    //如果最后一位不是分隔符的话，就将最后一个分割符到最后一个字符中间的左右字符串作为一个字符串添加到分割数组中去
            stringList.add(new String(chars, nextStart,
                    chars.length - 1 - nextStart + 1));
        }
        return stringList.toArray(new String[stringList.size()]);
    }
     
    /**
     * @MethodName: countLength
     * @description: 计算给定的字符串的长度，计算规则是：一个汉字的长度为2，一个字符的长度为1
     * @author:  Mr.Lee
     * @param string 给定的字符串
     * @return 长度
     */
    public static int countLength(String string) {
        int length = 0;
        char[] chars = string.toCharArray();
        for (int w = 0; w < string.length(); w++) {
            char ch = chars[w];
            if (ch >= '\u0391' && ch <= '\uFFE5') {
                length++;
                length++;
            }
            else {
                length++;
            }
        }
        return length;
    }

    /**
     * @MethodName: getChars
     * @description: 获取字符数组中的指定位置的字符
     * @author:  Mr.Lee
     * @param chars 字符数组 
     * @param startIndex 起始位置
     * @return 返回截取的字符数组
     */
    private static char[] getChars(char[] chars, int startIndex) {
        int endIndex = startIndex + 1;
        //如果第一个是数字
        if (Character.isDigit(chars[startIndex])) {
            //如果下一个是数字
            while (endIndex < chars.length &&
                    Character.isDigit(chars[endIndex])) {
                endIndex++;
            }
        }
        char[] resultChars = new char[endIndex - startIndex];
        System.arraycopy(chars, startIndex, resultChars, 0, resultChars.length);
        return resultChars;
    }

    /**
     * @MethodName: isAllDigital
     * @description: 检测字符数组元素是否全是数字
     * @author:  Mr.Lee
     * @param chars 字符数组
     * @return 如果字符数组中元素值全是数字则返回ture,否则返回false
     */
    public static boolean isAllDigital(char[] chars) {
        boolean result = true;
        for (int w = 0; w < chars.length; w++) {
            if (!Character.isDigit(chars[w])) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * @MethodName: removeChar
     * @description: 删除给定字符串中所有的旧的字符
     * @author:  Mr.Lee
     * @param string 源字符串
     * @param ch 要删除的字符
     * @return 删除后的字符串
     */
    public static String removeChar(String string, char ch) {
        StringBuffer sb = new StringBuffer();
        for (char cha : string.toCharArray()) {
            if (cha != '-') {
                sb.append(cha);
            }
        }
        return sb.toString();
    }

    /**
     * @MethodName: removeChar
     * @description: 删除给定字符串中给定位置处的字符
     * @author:  Mr.Lee
     * @param string 给定字符串
     * @param index 给定位置
     */
    public static String removeChar(String string, int index) {
        String result = null;
        char[] chars = string.toCharArray();
        if (index == 0) {
            result = new String(chars, 1, chars.length - 1);
        }
        else if (index == chars.length - 1) {
            result = new String(chars, 0, chars.length - 1);
        }
        else {
            result = new String(chars, 0, index) +
                    new String(chars, index + 1, chars.length - index);
            ;
        }
        return result;
    }

    /**
     * @MethodName: removeChar
     * @description: 删除给定字符串中给定位置处的字符
     * @author:  Mr.Lee
     * @param string 给定字符串
     * @param index 给定位置
     * @param ch 如果同给定位置处的字符相同，则将给定位置处的字符删除
     */
    public static String removeChar(String string, int index, char ch) {
        String result = null;
        char[] chars = string.toCharArray();
        if (chars.length > 0 && chars[index] == ch) {
            if (index == 0) {
                result = new String(chars, 1, chars.length - 1);
            }
            else if (index == chars.length - 1) {
                result = new String(chars, 0, chars.length - 1);
            }
            else {
                result = new String(chars, 0, index) +
                        new String(chars, index + 1, chars.length - index);
                ;
            }
        }
        else {
            result = string;
        }
        return result;
    }

    /**
     * @MethodName: filterBlank
     * @description: 对给定的字符串进行空白过滤
     * @author:  Mr.Lee
     * @param string 给定的字符串
     * @return 如果给定的字符串是一个空白字符串，那么返回null；否则返回本身。
     */
    public static String filterBlank(String string) {
        if ("".equals(string)) {
            return null;
        }
        else {
            return string;
        }
    }

    /**
     * @MethodName: toLowerCase
     * @description: 将给定字符串中给定的区域的字符转换成小写
     * @author:  Mr.Lee
     * @param str 给定字符串中
     * @param beginIndex 开始索引（包括）
     * @param endIndex 结束索引（不包括）
     * @return 新的字符串
     */
    public static String toLowerCase(String str, int beginIndex, int endIndex) {
        return str.replaceFirst(str.substring(beginIndex, endIndex),
                str.substring(beginIndex, endIndex)
                   .toLowerCase(Locale.getDefault()));
    }

    /**
     * @MethodName: toUpperCase
     * @description: 将给定字符串中给定的区域的字符转换成大写
     * @author:  Mr.Lee
     * @param str 给定字符串中
     * @param beginIndex 开始索引（包括）
     * @param endIndex 结束索引（不包括）
     * @return 新的字符串
     */
    public static String toUpperCase(String str, int beginIndex, int endIndex) {
        return str.replaceFirst(str.substring(beginIndex, endIndex),
                str.substring(beginIndex, endIndex)
                   .toUpperCase(Locale.getDefault()));
    }

    /**
     * @MethodName: firstLetterToLowerCase
     * @description: 将给定字符串的首字母转为小写
     * @author:  Mr.Lee
     * @param str
     * @return
     *
     */
    public static String firstLetterToLowerCase(String str) {
        return toLowerCase(str, 0, 1);
    }

    /**
     * @MethodName: firstLetterToUpperCase
     * @description: 将给定字符串的首字母转为大写
     * @author:  Mr.Lee
     * @param str 给定字符串
     * @return 新的字符串
     */
    public static String firstLetterToUpperCase(String str) {
        return toUpperCase(str, 0, 1);
    }

    /**
     * @MethodName:
     * @description: 将给定的字符串MD5加密
     * @author:  Mr.Lee
     * @param string 给定的字符串
     * @return MD5加密后生成的字符串
     */
    public static String MD5(String string) {
        String result = null;
        try {
            char[] charArray = string.toCharArray();
            byte[] byteArray = new byte[charArray.length];
            for (int i = 0; i < charArray.length; i++) {
                byteArray[i] = (byte) charArray[i];
            }

            StringBuffer hexValue = new StringBuffer();
            byte[] md5Bytes = MessageDigest.getInstance("MD5")
                                           .digest(byteArray);
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }

            result = hexValue.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @MethodName: startsWithIgnoreCase
     * @description: 判断给定的字符串是否以一个特定的字符串开头，忽略大小写
     * @author:  Mr.Lee
     * @param sourceString 给定的字符串
     * @param newString 一个特定的字符串
     */
    public static boolean startsWithIgnoreCase(String sourceString, String newString) {
        int newLength = newString.length();
        int sourceLength = sourceString.length();
        if (newLength == sourceLength) {
            return newString.equalsIgnoreCase(sourceString);
        }
        else if (newLength < sourceLength) {
            char[] newChars = new char[newLength];
            sourceString.getChars(0, newLength, newChars, 0);
            return newString.equalsIgnoreCase(String.valueOf(newChars));
        }
        else {
            return false;
        }
    }

    /**
     * @MethodName: endsWithIgnoreCase
     * @description: 判断给定的字符串是否以一个特定的字符串结尾，忽略大小写
     * @author:  Mr.Lee
     * @param sourceString 给定的字符串
     * @param newString 一个特定的字符串
     */
    public static boolean endsWithIgnoreCase(String sourceString, String newString) {
        int newLength = newString.length();
        int sourceLength = sourceString.length();
        if (newLength == sourceLength) {
            return newString.equalsIgnoreCase(sourceString);
        }
        else if (newLength < sourceLength) {
            char[] newChars = new char[newLength];
            sourceString.getChars(sourceLength - newLength, sourceLength,
                    newChars, 0);
            return newString.equalsIgnoreCase(String.valueOf(newChars));
        }
        else {
            return false;
        }
    }

    /**
     * @MethodName: checkLength
     * @description: 检查字符串长度，如果字符串的长度超过maxLength，就截取前maxLength个字符串并在末尾拼上appendString
     * @author:  Mr.Lee
     * @param string
     * @param maxLength
     * @param appendString
     * @return
     */
    public static String checkLength(String string, int maxLength, String appendString) {
        if (string.length() > maxLength) {
            string = string.substring(0, maxLength);
            if (appendString != null) {
                string += appendString;
            }
        }
        return string;
    }

    /**
     * @MethodName: checkLength
     * @description: 检查字符串长度，如果字符串的长度超过maxLength，就截取前maxLength个字符串并在末尾拼上…
     * @author:  Mr.Lee
     * @param string
     * @param maxLength
     * @return
     */
    public static String checkLength(String string, int maxLength) {
        return checkLength(string, maxLength, "…");
    }
}

