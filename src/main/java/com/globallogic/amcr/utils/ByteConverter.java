package com.globallogic.amcr.utils;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

@SuppressWarnings({"FinalStaticMethod", "SpellCheckingInspection"})
public class ByteConverter {
    private ByteConverter() {
        throw new UnsupportedOperationException("ByteConverter cannot be used dynamically and should only be referenced statically");
    }
    public final static String bytesToReadable(long bytes) {
        long absB = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
        if (absB < 1024) {
            return bytes + " B";
        }
        long value = absB;
        CharacterIterator ci = new StringCharacterIterator("KMGTPE");
        for (int i = 40; i >= 0 && absB > 0xfffccccccccccccL >> i; i -= 10) {
            value >>= 10;
            ci.next();
        }
        value *= Long.signum(bytes);
        return String.format("%.1f %ciB", value / 1024.0, ci.current());
    }
}