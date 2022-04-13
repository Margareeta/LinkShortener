package org.margareeta.linkshortener.baseservice.controller;

public class Controllers {
    public static final String BASE = "/api/v1";
    public static final String BY_SHORT_LINK = "/{shortLink}";
    public static final String REDIRECT = BASE + BY_SHORT_LINK;
    public static final String DELETE = BASE + BY_SHORT_LINK;
    public static final String GET_SHORT_LINK = BASE + "/short-link";
    public static final String GET_ANALYTICS = BASE + "/analytics" + BY_SHORT_LINK;

    private Controllers() {
    }
}
