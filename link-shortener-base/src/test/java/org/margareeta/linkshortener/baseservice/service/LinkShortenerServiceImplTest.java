package org.margareeta.linkshortener.baseservice.service;

import lombok.SneakyThrows;
import org.margareeta.linkshortener.baseservice.dto.LinkEntityDto;
import org.margareeta.linkshortener.baseservice.exception.NotWorkingLinkException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.margareeta.linkshortener.common.model.LinkEntity;
import org.margareeta.linkshortener.common.repository.LinkShortenerRepositiory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

class LinkShortenerServiceImplTest extends BaseIntegrationTest {
    private final LinkShortenerService service;
    private final LinkShortenerRepositiory repository;
    private static final String fullLink = "https://github.com/Margareeta";
    private static final String brokenLink = "https://github.com/Margareeta/NordCest";

    @Autowired
    public LinkShortenerServiceImplTest(LinkShortenerService service, LinkShortenerRepositiory repository) {
        this.service = service;
        this.repository = repository;
    }

    @AfterEach
    void clearTable() {
        repository.deleteAll();
    }

    @Test
    void shouldCreateAndDeleteShortLink() {
        String actual = service.createShortLink(fullLink);
        String expected = "gi.ub" + fullLink.hashCode();
        Assertions.assertEquals(expected, actual);

        service.deleteLink(actual);
        Assertions.assertFalse(repository.existsById(actual));
    }

    @SneakyThrows
    @Test
    void shouldRedirect() {
        LinkEntity linkEntity = createLinkEntity();
        HttpServletResponse response = new MockHttpServletResponse();

        service.redirect(linkEntity.getShortLink(), response);

        Assertions.assertEquals(302, response.getStatus());
    }

    @Test
    void incrementCounter() {
        LinkEntity linkEntity = createLinkEntity();

        service.incrementCounter(linkEntity.getShortLink());
        long actual = repository.findById(linkEntity.getShortLink()).get().getCounter().longValue();

        Assertions.assertEquals(1, actual);
    }

    @SneakyThrows
    @Test
    void shouldGetAnalytics() {
        LinkEntity linkEntity = createLinkEntity();

        Assertions.assertEquals(0, linkEntity.getCounter().longValue());

        for (int i = 0; i < 5; i++) {
            service.redirect(linkEntity.getShortLink(), new MockHttpServletResponse());
        }
        LinkEntityDto linkEntityDto = service.getAnalytics(linkEntity.getShortLink());

        Assertions.assertEquals(5, linkEntityDto.getCounter());
    }

    @Test
    void shouldThrowExceptionWithBrockenLink(){
        Assertions.assertThrows(NotWorkingLinkException.class,() -> service.createShortLink(brokenLink));
    }

    private LinkEntity createLinkEntity() {
        return LinkEntity.builder()
                .shortLink(service.createShortLink(fullLink))
                .fullLink(fullLink)
                .createdAt(new Date())
                .counter(new AtomicLong(0))
                .build();
    }
}