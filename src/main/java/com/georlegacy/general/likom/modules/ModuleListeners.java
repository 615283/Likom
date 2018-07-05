package com.georlegacy.general.likom.modules;

import com.georlegacy.general.likom.App;
import com.georlegacy.general.likom.objects.LikomSave;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class containing GUIs and handlers for all modules.
 *
 * @author 615283
 */
public class ModuleListeners {

    public static LikomSave save;

    public static class System implements ActionListener {
        private boolean isOpen;
        private boolean isStarted;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isOpen) {
                JOptionPane.showMessageDialog(null, "That module is already open.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JFrame frame = new JFrame();
//            frame.setFont(FontUtil.getFont(App.class.getClassLoader().getResourceAsStream("./myriadfont.ttf"), 18));
            frame.setFont(new JLabel().getFont().deriveFont(18f));
            frame.setSize(new Dimension(200, 300));
            frame.setUndecorated(true);
            frame.setResizable(false);
            frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 30));
            panel.setBackground(new Color(20, 139, 251));

            JLabel sts = new JLabel();
            sts.setPreferredSize(new Dimension(70, 40));
            sts.setBorder(BorderFactory.createLineBorder(isStarted ? Color.GREEN : Color.RED, 20));

            JButton startStop = new JButton();
            startStop.setFont(frame.getFont().deriveFont(18f));
            startStop.setPreferredSize(new Dimension(80, 40));
            startStop.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
            startStop.setText(isStarted ? "Stop" : "Start");
            startStop.setFocusPainted(false);
            startStop.setForeground(Color.BLACK);
            startStop.setBackground(Color.WHITE);
            startStop.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (startStop.getText().equals("Start")) {
                        if (save.getHashtags().size() == 0) {
                            JOptionPane.showMessageDialog(frame, "You haven't set any Hashtags to search for posts in.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (save.getComments().size() == 0) {
                            JOptionPane.showMessageDialog(frame, "You haven't set any comments to comment on posts with.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        isStarted = true;
                        sts.setBorder(BorderFactory.createLineBorder(Color.GREEN, 20));
                        startStop.setText("Stop");
                        return;
                    }
                    if (startStop.getText().equals("Stop")) {

                        isStarted = false;
                        sts.setBorder(BorderFactory.createLineBorder(Color.RED, 20));
                        startStop.setText("Start");
                        return;
                    }
                }
            });

            panel.add(startStop);
            panel.add(sts);

            frame.add(panel);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    isOpen = false;
                }
            });

            frame.setVisible(true);
            isOpen = true;
        }
    }

    public static class HashTags implements ActionListener {
        private boolean isOpen;
        private JList tagList;
        private JButton ar;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isOpen) {
                JOptionPane.showMessageDialog(null, "That module is already open.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JFrame frame = new JFrame();
            frame.setFont(new JLabel().getFont().deriveFont(18f));
            frame.setFont(frame.getFont().deriveFont(18f));
            frame.setSize(new Dimension(200, 300));
            frame.setUndecorated(true);
            frame.setResizable(false);
            frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

            List<String> tags = save.getHashtags();

            JTextField enter = new JTextField();
            enter.setPreferredSize(new Dimension(170, 30));

            ar = new JButton();
            ar.setPreferredSize(new Dimension(150, 40));
            ar.setText("Add");
            ar.setForeground(Color.BLACK);
            ar.setBackground(Color.WHITE);
            ar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (ar.getText().equals("Remove")) {
                        tags.remove(tagList.getSelectedValue());
                        tagList.setListData(tags.toArray());
                        save.setHashtags(tags);
                        ar.setText("Add");
                        return;
                    }
                    if (ar.getText().equals("Add")) {
                        if (tags.contains(enter.getText())) {
                            JOptionPane.showMessageDialog(frame, "That Hashtag is already registered.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (enter.getText().contains(" ")) {
                            JOptionPane.showMessageDialog(frame, "Hashtags cannot contain spaces.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        tags.add(enter.getText());
                        enter.setText("");
                        tagList.setListData(tags.toArray());
                        save.setHashtags(tags);
                        return;
                    }
                }
            });

            tagList = new JList();
            tagList.setListData(tags.toArray());
            save.setHashtags(tags);
            tagList.setPreferredSize(new Dimension(150, 150));
            tagList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {

                    ar.setText("Remove");
                }
            });

            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 15));
            panel.setBackground(new Color(20, 139, 251));

            panel.add(tagList);
            panel.add(enter);
            panel.add(ar);

            frame.add(panel);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    isOpen = false;
                }
            });

            frame.setVisible(true);
            isOpen = true;
        }
    }

    public static class Interval implements ActionListener {
        private boolean isOpen;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isOpen) {
                JOptionPane.showMessageDialog(null, "That module is already open.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JFrame frame = new JFrame();
//            frame.setFont(FontUtil.getFont(App.class.getClassLoader().getResourceAsStream("./myriadfont.ttf"), 18));
            frame.setFont(new JLabel().getFont().deriveFont(18f));
            frame.setSize(new Dimension(200, 300));
            frame.setUndecorated(true);
            frame.setResizable(false);
            frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 30));
            panel.setBorder(BorderFactory.createEmptyBorder(30, 3, 20, 3));
            panel.setBackground(new Color(20, 139, 251));

            JButton lowerDown = new JButton();
            lowerDown.setFont(frame.getFont().deriveFont(18f));
            lowerDown.setText("▼");
            lowerDown.setPreferredSize(new Dimension(30, 20));
            lowerDown.setBackground(Color.WHITE);
            lowerDown.setFocusPainted(false);
            lowerDown.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

            JLabel lowerLabel = new JLabel();
            lowerLabel.setText("Minimum");
            lowerLabel.setForeground(Color.WHITE);
            lowerLabel.setFont(frame.getFont().deriveFont(17f));
            lowerLabel.setPreferredSize(new Dimension(80, 40));

            JButton lowerUp = new JButton();
            lowerUp.setFont(frame.getFont().deriveFont(18f));
            lowerUp.setText("▲");
            lowerUp.setPreferredSize(new Dimension(30, 20));
            lowerUp.setBackground(Color.WHITE);
            lowerUp.setFocusPainted(false);
            lowerUp.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

            JButton upperDown = new JButton();
            upperDown.setFont(frame.getFont().deriveFont(18f));
            upperDown.setText("▼");
            upperDown.setPreferredSize(new Dimension(30, 20));
            upperDown.setBackground(Color.WHITE);
            upperDown.setFocusPainted(false);
            upperDown.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

            JLabel upperLabel = new JLabel();
            upperLabel.setText("Maximum");
            upperLabel.setForeground(Color.WHITE);
            upperLabel.setFont(frame.getFont().deriveFont(17f));
            upperLabel.setPreferredSize(new Dimension(80, 40));

            JButton upperUp = new JButton();
            upperUp.setFont(frame.getFont().deriveFont(18f));
            upperUp.setText("▲");
            upperUp.setPreferredSize(new Dimension(30, 20));
            upperUp.setBackground(Color.WHITE);
            upperUp.setFocusPainted(false);
            upperUp.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

            panel.add(lowerDown);
            panel.add(lowerLabel);
            panel.add(lowerUp);

            panel.add(upperDown);
            panel.add(upperLabel);
            panel.add(upperUp);

            frame.add(panel);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    isOpen = false;
                }
            });

            frame.setVisible(true);
        }
    }

    public static class BlacklistUsers implements ActionListener {
        private boolean isOpen;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isOpen) {
                JOptionPane.showMessageDialog(null, "That module is already open.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JFrame frame = new JFrame();
//            frame.setFont(FontUtil.getFont(App.class.getClassLoader().getResourceAsStream("./myriadfont.ttf"), 18));
            frame.setFont(new JLabel().getFont().deriveFont(18f));
            frame.setSize(new Dimension(200, 300));
            frame.setUndecorated(true);
            frame.setResizable(false);
            frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
            panel.setBackground(new Color(20, 139, 251));


            frame.add(panel);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    isOpen = false;
                }
            });

            frame.setVisible(true);
            isOpen = true;
        }
    }

    public static class Comments implements ActionListener {
        private boolean isOpen;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isOpen) {
                JOptionPane.showMessageDialog(null, "That module is already open.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JFrame frame = new JFrame();
//            frame.setFont(FontUtil.getFont(App.class.getClassLoader().getResourceAsStream("./myriadfont.ttf"), 18));
            frame.setFont(new JLabel().getFont().deriveFont(18f));
            frame.setSize(new Dimension(200, 300));
            frame.setUndecorated(true);
            frame.setResizable(false);
            frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 30));
            panel.setBackground(new Color(20, 139, 251));

            JButton open = new JButton();
            open.setForeground(Color.BLACK);
            open.setBackground(Color.WHITE);
            open.setFocusPainted(false);
            open.setText("Open File");
            open.setPreferredSize(new Dimension(80, 50));
            open.setFont(frame.getFont().deriveFont(14f));
            open.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
            open.addActionListener(e12 -> {
                File commentsFile;
                try {
                    commentsFile = new File(new File(App.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile() + File.separator + "comments.txt");
                } catch (URISyntaxException ex) {
                    ex.printStackTrace();
                    return;
                }
                if (!commentsFile.exists()) {
                    try {
                        commentsFile.createNewFile();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        return;
                    }
                }
                try {
                    Desktop.getDesktop().edit(commentsFile);
                } catch (UnsupportedOperationException ex) {
                    JOptionPane.showMessageDialog(frame, "Editing the comments file is unsupported, try opening manually.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (SecurityException ex) {
                    JOptionPane.showMessageDialog(frame, "The program does not have permission to open the comments file, try opening manually.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                } catch (IOException ex) {
                    ex.printStackTrace();
                    return;
                }
            });

            JButton load = new JButton();
            load.setForeground(Color.BLACK);
            load.setBackground(Color.WHITE);
            load.setFocusPainted(false);
            load.setText("Reload");
            load.setPreferredSize(new Dimension(80, 50));
            load.setFont(frame.getFont().deriveFont(20f));
            load.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
            load.addActionListener(e1 -> {
                File commentsFile;
                try {
                    commentsFile = new File(new File(App.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile() + File.separator + "comments.txt");
                } catch (URISyntaxException ex) {
                    ex.printStackTrace();
                    return;
                }
                try {
                    if (!commentsFile.exists())
                        commentsFile.createNewFile();
                    List<String> comments = new ArrayList<>();
                    Scanner scanner = new Scanner(commentsFile);
                    while (scanner.hasNext())
                        comments.add(scanner.next());
                    save.setComments(comments);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            panel.add(open);
            panel.add(load);

            frame.add(panel);

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    isOpen = false;
                }
            });

            frame.setVisible(true);
            isOpen = true;
        }
    }

}
