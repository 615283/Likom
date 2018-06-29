package com.georlegacy.general.likom.modules;

import com.georlegacy.general.likom.App;
import com.georlegacy.general.likom.util.FontUtil;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class ModuleListeners {

    public static class System implements ActionListener {
        private boolean isOpen;

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isOpen) {
                JOptionPane.showMessageDialog(null, "That module is already open.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JFrame frame = new JFrame();
            frame.setFont(FontUtil.getFont(App.class.getClassLoader().getResourceAsStream("myriadfont.ttf"), 18));
            frame.setSize(new Dimension(200, 300));
            frame.setLocation(540, 540);
            frame.setUndecorated(true);
            frame.setResizable(false);
            frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

            JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 30));
            panel.setBackground(new Color(20, 139, 251));

            JLabel sts = new JLabel();
            sts.setPreferredSize(new Dimension(70, 40));
            sts.setBorder(BorderFactory.createLineBorder(Color.RED, 20));

            JButton startStop = new JButton();
            startStop.setFont(frame.getFont().deriveFont(18f));
            startStop.setPreferredSize(new Dimension(70, 40));
            startStop.setText("Start");
            startStop.setFocusPainted(false);
            startStop.setForeground(Color.BLACK);
            startStop.setBackground(Color.WHITE);
            startStop.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (startStop.getText().equals("Start")) {

                        sts.setBorder(BorderFactory.createLineBorder(Color.GREEN, 20));
                        startStop.setText("Stop");
                        return;
                    }
                    if (startStop.getText().equals("Stop")) {

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
            frame.setFont(FontUtil.getFont(App.class.getClassLoader().getResourceAsStream("myriadfont.ttf"), 18));
            frame.setSize(new Dimension(200, 300));
            frame.setLocation(540, 540);
            frame.setUndecorated(true);
            frame.setResizable(false);
            frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);

            List<String> tags = new ArrayList<String>();

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
                        ar.setText("Add");
                        return;
                    }
                    if (ar.getText().equals("Add")) {
                        if (tags.contains(enter.getText())) {
                            JOptionPane.showMessageDialog(null, "That Hashtag is already registered.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        tags.add(enter.getText());
                        enter.setText("");
                        tagList.setListData(tags.toArray());
                        return;
                    }
                }
            });

            tagList = new JList();
            tagList.setListData(tags.toArray());
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
            frame.setFont(FontUtil.getFont(App.class.getClassLoader().getResourceAsStream("myriadfont.ttf"), 18));
            frame.setSize(new Dimension(200, 300));
            frame.setLocation(540, 540);
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
            frame.setFont(FontUtil.getFont(App.class.getClassLoader().getResourceAsStream("myriadfont.ttf"), 18));
            frame.setSize(new Dimension(200, 300));
            frame.setLocation(540, 540);
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
            frame.setFont(FontUtil.getFont(App.class.getClassLoader().getResourceAsStream("myriadfont.ttf"), 18));
            frame.setSize(new Dimension(200, 300));
            frame.setLocation(540, 540);
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

}
