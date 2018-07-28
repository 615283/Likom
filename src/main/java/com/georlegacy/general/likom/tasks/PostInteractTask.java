package com.georlegacy.general.likom.tasks;

import com.georlegacy.general.likom.Likom;
import com.georlegacy.general.likom.modules.ModuleListeners;
import org.brunocvcunha.instagram4j.requests.InstagramTagFeedRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class PostInteractTask implements Runnable {
    public volatile boolean stopped;

    @Override
    public void run() {
        if (stopped) return;

        Random random = new Random();
        String tagToSearch = ModuleListeners.save.getHashtags().get(random.nextInt(ModuleListeners.save.getHashtags().size()));

        InstagramFeedResult feedResult;
        try {
            feedResult = Likom.getInstance().getInstagram().sendRequest(new InstagramTagFeedRequest(tagToSearch));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("An error occurred whilst searching the feed.");
        }

        int i = 0;
        for (InstagramFeedItem item : feedResult.getItems()) {
            if (i>1) break;

            System.out.println(item.getCaption());
            System.out.println(item.getLike_count());

            i++;
        }

//        Likom.getInstance().getScheduler().scheduleWithFixedDelay(this, 1l,
//                ThreadLocalRandom.current().nextLong(
//                        ModuleListeners.save.getIntervalLowerBoundMillis(),
//                        ModuleListeners.save.getIntervalUpperBoundMillis() + 1), TimeUnit.MILLISECONDS);
    }

}
