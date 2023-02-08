package com.globallogic.amcr.utils;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public final class FormatUtil {
    private FormatUtil() {
        throw new UnsupportedOperationException("FormatUtil cannot be instantiated this way and its methods should be called statically");
    }

    public static String bytesToReadable(long bytes) {
        long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        if (absB < 1024) {
            return bytes + " B";
        }
        long value = absB;
        CharacterIterator ci = new StringCharacterIterator("kMGTPE");
        for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
            value >>= 10;
            ci.next();
        }
        value *= Long.signum(bytes);
        return String.format("%.1f %ciB", value / 1024.0, ci.current());
    }

}
