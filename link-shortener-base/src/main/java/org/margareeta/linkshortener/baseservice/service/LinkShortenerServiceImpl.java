package org.margareeta.linkshortener.baseservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.margareeta.linkshortener.baseservice.converter.LinkEntityDtoConverter;
import org.margareeta.linkshortener.baseservice.dto.LinkEntityDto;
import org.margareeta.linkshortener.baseservice.exception.ExpiredLinkException;
import org.margareeta.linkshortener.baseservice.exception.LinkNotFoundException;
import org.margareeta.linkshortener.baseservice.exception.NotWorkingLinkException;
import org.margareeta.linkshortener.baseservice.model.LinkEntity;
import org.margareeta.linkshortener.baseservice.repository.LinkShortenerRepositiory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@RequiredArgsConstructor
@Service
@Slf4j
public class LinkShortenerServiceImpl implements LinkShortenerService {
    private final LinkShortenerRepositiory repository;
    private final LinkEntityDtoConverter converter;
    @Value("${expirationTimeMinutes}")
    private Long expirationTerm;

    @Override
    public void redirect(String shortLink, HttpServletResponse response) throws IOException {
        LinkEntity linkEntity = repository.findById(shortLink).orElseThrow(LinkNotFoundException::new);
        checkExpiration(linkEntity);
        response.sendRedirect(linkEntity.getFullLink());
        incrementCounter(shortLink);
    }

    @Override
    public String createShortLink(String fullLink) {
        checkLink(fullLink);
        String shortLink = generateShortLink(fullLink);
        if (!repository.existsById(shortLink)) {
            repository.save(
                    LinkEntity.builder()
                            .shortLink(shortLink)
                            .fullLink(fullLink)
                            .createdAt(new Date())
                            .counter(new AtomicLong(0))
                            .build()
            );
        }
        return shortLink;
    }

    @Override
    public void deleteLink(String shortLink) {
        if (!repository.existsById(shortLink)){
            throw new LinkNotFoundException();
        }
        repository.deleteById(shortLink);
    }

    @Override
    public void incrementCounter(String shortLink) {
        LinkEntity linkEntity = repository.findById(shortLink).orElseThrow(LinkNotFoundException::new);
        linkEntity.setCounter(new AtomicLong(linkEntity.getCounter().incrementAndGet()));
        linkEntity.setCreatedAt(linkEntity.getCreatedAt());
        repository.save(linkEntity);
    }

    @Override
    public LinkEntityDto getAnalytics(String shortLink) {
        return converter.toDto(repository.findById(shortLink).orElseThrow(LinkNotFoundException::new));
    }

    private String cutLink(String fullLink) {
        String[] splitLink = fullLink.split("\\.");
        String target = "";

        if (splitLink[0].contains("www")) {
            target = splitLink[1];
        } else {
            if (splitLink[0].contains("https")) {
                target = splitLink[0].substring(8);
            } else {
                target = splitLink[0].substring(7);
            }
        }
        return target;
    }

    private String generateShortLink(String fullLink) {
        String shorterLink = cutLink(fullLink);
        String halfLink = "";
        if (shorterLink.length() < 4) {
            halfLink = shorterLink.substring(0, 2) + ".";
            return halfLink + shorterLink.substring(shorterLink.length() - 1) + fullLink.hashCode();
        }
        return shorterLink.substring(0, 2) + "." + shorterLink.substring(shorterLink.length() - 2) + fullLink.hashCode();
    }

    private void checkLink(String fullLink) {
        try {
            new RestTemplate().exchange(fullLink, HttpMethod.GET, null, String.class);
        } catch (RestClientException e) {
            log.error("The link does not work: {}", fullLink);
            throw new NotWorkingLinkException();
        }
    }
    private void checkExpiration(LinkEntity linkEntity) {
        long currentTimeMinutes = new Date().getTime() / 1000 / 60;
        long creationTimeMinutes = linkEntity.getCreatedAt().getTime() / 1000 / 60;
        if ((currentTimeMinutes - creationTimeMinutes) >= expirationTerm) {
            repository.deleteById(linkEntity.getShortLink());
            log.error("The link has expired: {}", linkEntity.getShortLink());
            throw new ExpiredLinkException();
        }
    }
}
