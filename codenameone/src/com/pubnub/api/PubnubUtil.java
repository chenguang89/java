package com.pubnub.api;

import com.codename1.io.Util;
import com.codename1.util.StringUtil;
import org.json.*;

/**
 * PubnubUtil class provides utility methods like urlEncode etc
 * 
 * @author Pubnub
 *
 */
public class PubnubUtil extends PubnubUtilCore {

    private static String replace(String str, String pattern, String replace) {
        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer();

        while ((e = str.indexOf(pattern, s)) >= 0) {
            result.append(str.substring(s, e));
            result.append(replace);
            s = e + pattern.length();
        }
        result.append(str.substring(s));
        return result.toString();
    }

    /**
     * Returns encoded String
     *
     * @param sUrl
     *            , input string
     * @return , encoded string
     */
    public static String pamEncode(String sUrl) {
        /* !'()*~ */

        String encoded = urlEncode(sUrl);
        if (encoded != null) {
            encoded = replace(encoded, "*", "%2A");
            encoded = replace(encoded, "!", "%21");
            encoded = replace(encoded, "'", "%27");
            encoded = replace(encoded, "(", "%28");
            encoded = replace(encoded, ")", "%29");
            encoded = replace(encoded, "[", "%5B");
            encoded = replace(encoded, "]", "%5D");
            encoded = replace(encoded, "~", "%7E");
        }
        return encoded;
    }

    /**
     * Returns encoded String
     *
     * @param sUrl
     *            , input string
     * @return , encoded string
     */
    public static String urlEncode(String sUrl) {
        return Util.encodeUrl(sUrl);
    }

    public static String stringReplaceAll(String s, String a, String b) {
        return StringUtil.replaceAll(s, a, b);
    }

    public static String escapeJava(String s) {
        s = PubnubUtil.stringReplaceAll(s, "\"", "\\\\\"");
        return s;
    }

    /**
     * Convert input String to JSONObject, JSONArray, or String
     *
     * @param str
     *            JSON data in string format
     *
     * @return JSONArray or JSONObject or String
     */
    static Object stringToJSON(String str) {
        try {
            return new JSONArray(str);
        } catch (JSONException e) {
        }
        try {
            return new JSONObject(str);
        } catch (JSONException ex) {
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception ex) {
        }
        try {
            return Double.parseDouble(str);
        } catch (Exception ex) {
        }
        return str;
    }
}
