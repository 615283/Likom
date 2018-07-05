package com.georlegacy.general.likom.tasks;

import com.georlegacy.general.likom.Likom;
import com.georlegacy.general.likom.modules.ModuleListeners;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class PostInteractTask implements Runnable {
    private volatile boolean stopped;

    @Override
    public void run() {
        Random random = new Random();
        String tagToSearch = ModuleListeners.save.getHashtags().get(random.nextInt(ModuleListeners.save.getHashtags().size()));

        Likom.getInstance().getScheduler().scheduleWithFixedDelay(this, 1l,
                ThreadLocalRandom.current().nextLong(
                        ModuleListeners.save.getIntervalLowerBoundMillis(),
                        ModuleListeners.save.getIntervalUpperBoundMillis() + 1), TimeUnit.MILLISECONDS);
    }

}
