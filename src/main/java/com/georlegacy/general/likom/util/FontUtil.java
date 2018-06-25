package com.georlegacy.general.likom.util;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class FontUtil {

    public static Font getFont(InputStream is, int size) {
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
            font = null;
        } catch (IOException e) {
            e.printStackTrace();
            font = null;
        }
        return font.deriveFont(Font.PLAIN, size);
    }

}
