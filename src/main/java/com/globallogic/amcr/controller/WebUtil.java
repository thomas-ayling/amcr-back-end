package com.globallogic.amcr.controller;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

public final class WebUtil {
    private WebUtil () {
        throw new UnsupportedOperationException("WebUtil cannot be instantiated this way and its methods should be called statically");
    }

    public static URI generateUri (String path, UUID id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path(path).buildAndExpand(id).toUri();
    }
}
