package com.georlegacy.general.likom.util;

import com.georlegacy.general.likom.Likom;
import com.georlegacy.general.likom.objects.LikomSave;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class SaveEncoder {

    public static void save(LikomSave save) {
        System.out.println("saving");
        URL url = Likom.class.getProtectionDomain().getCodeSource().getLocation();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(new File(url.toURI()).getParentFile() + File.separator + "save.lk")))) {
            File file = new File(new File(url.toURI()).getParentFile() + File.separator + "save.lk");
            if (file.exists())
                file.delete();
            file.createNewFile();
            oos.writeObject(save);
        } catch (IOException | URISyntaxException ex) {
            ex.printStackTrace();
        }

    }

    public static LikomSave decode() {
        URL url = Likom.class.getProtectionDomain().getCodeSource().getLocation();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(new File(url.toURI()).getParentFile() + File.separator + "save.lk")))) {
            return (LikomSave) ois.readObject();
        } catch (FileNotFoundException ex) {
            return new LikomSave();
        } catch (IOException | URISyntaxException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
