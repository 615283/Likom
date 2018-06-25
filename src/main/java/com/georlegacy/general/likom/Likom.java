package com.georlegacy.general.likom;

import com.georlegacy.general.likom.util.ChecksUtil;
import org.brunocvcunha.instagram4j.Instagram4j;

import java.awt.*;
import java.net.URISyntaxException;

public class Likom {

    private GUI gui;

    private Instagram4j instagram;

    private static Likom instance;

    public void setInstagram(Instagram4j instagram) {
        this.instagram = instagram;
    }

    public Instagram4j getInstagram() {
        return instagram;
    }

    public static Likom getInstance() {
        return instance;
    }

    public Likom() {
        instance = this;
        try {
            gui = new GUI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        if (!ChecksUtil.netIsAvailable()) {
            gui.displayOnly("<html><div style='text-align: center;'>INTERNET<br>CONNECTION<br>NOT<br>FOUND!</div></html>", Color.WHITE);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(1);
        }
        gui.displayLogon();
    }

}
