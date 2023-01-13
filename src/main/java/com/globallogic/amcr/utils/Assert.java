package com.globallogic.amcr.utils;

import com.globallogic.amcr.exception.NotFoundException;

@SuppressWarnings("FinalStaticMethod")
public class Assert {
    private Assert() {
        throw new UnsupportedOperationException("Assert class cannot be used dynamically and should only be referenced statically. Methods include assertNull(object, message)");
    }
    public final static <T> T assertNull(T object, String message) {
        if (object == null) {
            throw new NotFoundException(message);
        }
        return object;
    }
}

