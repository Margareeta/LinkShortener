package org.daisy.service;

import org.daisy.dto.LinkEntityDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface LinkShortenerService {
    void redirect(String shortLink, HttpServletResponse response) throws IOException;
    String createShortLink(String fullLink);
    void deleteLink(String shortLink);
    void incrementCounter(String shortLink);
    LinkEntityDto getAnalytics(String shortLink);
}
