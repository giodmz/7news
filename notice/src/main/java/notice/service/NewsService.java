package notice.service;

import notice.dto.NewsDTO;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

    public String messageDTO(NewsDTO obj) {
        return obj.toString();
    }


}
