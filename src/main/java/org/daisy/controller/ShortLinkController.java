package org.daisy.controller;

import lombok.RequiredArgsConstructor;
import org.daisy.dto.LinkEntityDto;
import org.daisy.service.LinkShortenerService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.daisy.controller.Controllers.*;

@RestController
@RequiredArgsConstructor
public class ShortLinkController {
    private final LinkShortenerService service;

    @PostMapping(GET_SHORT_LINK)
    private String shortLink (@RequestBody String fullLink){
        return service.createShortLink(fullLink);
    }

    @GetMapping(REDIRECT)
    private void redirect(@PathVariable(name = "shortLink") String shortLink, HttpServletResponse response) throws IOException {
        service.redirect(shortLink, response);
    }

    @DeleteMapping(DELETE)
    private void delete(@PathVariable(name = "shortLink") String shortLink){
        service.deleteLink(shortLink);
    }

    @GetMapping(GET_ANALYTICS)
    private LinkEntityDto getLinkStatistic(@PathVariable(name = "shortLink") String shortLink){

        return service.getAnalytics(shortLink);
    }

}
