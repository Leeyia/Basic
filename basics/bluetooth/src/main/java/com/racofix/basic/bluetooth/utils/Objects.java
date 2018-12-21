package com.racofix.basic.bluetooth.utils;

import android.util.SparseArray;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

public class Objects {

    public static String toString(SparseArray<byte[]> array) {
        if (array == null) {
            return "null";
        } else if (array.size() == 0) {
            return "{}";
        } else {
            StringBuilder buffer = new StringBuilder();
            buffer.append('{');

            for (int i = 0; i < array.size(); ++i) {
                buffer.append(array.keyAt(i)).append("=").append(Arrays.toString((byte[]) array.valueAt(i)));
            }

            buffer.append('}');
            return buffer.toString();
        }
    }

    public static <T> String toString(Map<T, byte[]> map) {
        if (map == null) {
            return "null";
        } else if (map.isEmpty()) {
            return "{}";
        } else {
            StringBuilder buffer = new StringBuilder();
            buffer.append('{');
            Iterator it = map.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                Object key = entry.getKey();
                buffer.append(key).append("=").append(Arrays.toString((byte[]) map.get(key)));
                if (it.hasNext()) {
                    buffer.append(", ");
                }
            }

            buffer.append('}');
            return buffer.toString();
        }
    }
}
