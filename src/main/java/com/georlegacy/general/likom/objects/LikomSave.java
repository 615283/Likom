package com.georlegacy.general.likom.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The utility class to encode and decode saved 'instances' of the program.
 *
 * @author 615283
 * @version ${project.version}
 */
public class LikomSave implements Serializable {

    private List<String> hashtags;

    private long intervalLowerBoundMillis, intervalUpperBoundMillis;

    private String username;

    /**
     * Contructor
     */
    public LikomSave() {
        this.hashtags = new ArrayList<String>();
        this.username = "Username";
    }

    /**
     * Gets the list of Hashtags.
     * @return {@link List} of Hashtags
     */
    public List<String> getHashtags() {
        return hashtags;
    }

    /**
     * Gets the lower interval bound.
     * @return {@link long} of milliseconds
     */
    public long getIntervalLowerBoundMillis() {
        return intervalLowerBoundMillis;
    }

    /**
     * Gets the upper interval bound.
     * @return {@link long} of milliseconds
     */
    public long getIntervalUpperBoundMillis() {
        return intervalUpperBoundMillis;
    }

    //TODO docs
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the lower interval bound.
     * @param intervalLowerBoundMillis New value in milliseconds for bound
     */
    public void setIntervalLowerBoundMillis(long intervalLowerBoundMillis) {
        this.intervalLowerBoundMillis = intervalLowerBoundMillis;
    }

    /**
     * Sets the upper interval bound.
     * @param intervalUpperBoundMillis New value in milliseconds for bound
     */
    public void setIntervalUpperBoundMillis(long intervalUpperBoundMillis) {
        this.intervalUpperBoundMillis = intervalUpperBoundMillis;
    }

    /**
     * Sets the Hashtag list.
     * @param hashtags New {@link List} of Hashtags
     */
    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

}
