package com.globallogic.amcr.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.UUID;

public class Utils {
    private Utils() {
        throw new UnsupportedOperationException("Utils class is static and should not be instantiated using the constructor.");
    }

    public static URI generateUri (String path, UUID id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path(path).buildAndExpand(id).toUri();
    }

    public static String bytesToReadable(long bytes) {
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
