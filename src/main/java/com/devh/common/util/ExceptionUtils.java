package com.devh.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <pre>
 * Description :
 *     Exception 관련 유틸
 * ===============================================
 * Member fields :
 *
 * ===============================================
 *
 * Author : HeonSeung Kim
 * Date   : 2021. 4. 10.
 * </pre>
 */
public class ExceptionUtils {
    /*
     * <pre>
     * Description :
     *     익셉션의 스택트레이스를 문자열로 반환
     * ===============================
     * Parameters :
     *     Throwable cause
     * Returns :
     *     String
     * Throws :
     *
     * ===============================
     *
     * Author : HeonSeung Kim
     * Date   : 2021. 5. 17.
     * </pre>
     */
    public static String stackTraceToString(Throwable cause) {
        final StringWriter sw = new StringWriter();
        cause.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}
