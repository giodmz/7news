package notice.controller;

import notice.dto.NewsDTO;
import notice.service.NewsService;
import notice.service.PublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    private final PublisherService publisherService;

    public NewsController(NewsService newsService, PublisherService publisherService) {
        this.newsService = newsService;
        this.publisherService = publisherService;
    }

    @PostMapping
    public ResponseEntity<Void> postNews(@RequestBody NewsDTO obj) {
        newsService.messageDTO(obj);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{news}")
                .buildAndExpand(obj.getUuid())
                .toUri();

        publisherService.sendMessage("", "news.sent", obj);

        return ResponseEntity.created(uri).build();
    }




}
