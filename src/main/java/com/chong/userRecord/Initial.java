package com.chong.userRecord;

import com.chong.userRecord.domain.Content;
import com.chong.userRecord.domain.ContentType;
import com.chong.userRecord.domain.User;
import com.chong.userRecord.service.ContentService;
import com.chong.userRecord.service.UserService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Log
public class Initial implements ApplicationRunner {

    @Autowired
    UserService userService;

    @Autowired
    ContentService contentService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 10; i++) {
            userService.save(User.dummyOf(String.valueOf(i))).block();
        }

        for (int i = 1; i <= 10; i++) {
            contentService.add(Content.dummyOf(String.valueOf(i), ContentType.BOOK)).block();
            contentService.add(Content.dummyOf(String.valueOf(i*10), ContentType.VIDEO)).block();
            contentService.add(Content.dummyOf(String.valueOf(i*20), ContentType.WEBTOON)).block();
        }

        log.info("Add Dummy Data");
    }
}
