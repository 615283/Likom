package com.georlegacy.general.likom;

import com.georlegacy.general.likom.modules.ModuleListeners;
import com.georlegacy.general.likom.modules.base.ModuleHandler;
import com.georlegacy.general.likom.util.SaveEncoder;
import javafx.scene.text.Font;
import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.payload.InstagramLoginResult;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URISyntaxException;

public class GUI extends JFrame {

    JPanel main;

    public GUI() throws URISyntaxException {
        ModuleListeners.save = SaveEncoder.decode();
        this.setResizable(false);
        this.setSize(new Dimension(800, 600));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setTitle("Likom - Instagram Comment and Like Utility");
        try {
            this.setIconImage(ImageIO.read(Likom.class.getClassLoader().getResourceAsStream("icon.png")));
//            this.setFont(FontUtil.getFont(App.class.getClassLoader().getResourceAsStream("./myriadfont.ttf"), 24));
            this.setFont(new JLabel().getFont().deriveFont(24f));
        } catch (IOException e) {
            e.printStackTrace();
        }
        main = new JPanel();
        main.setPreferredSize(this.getSize());
        main.setBorder(BorderFactory.createEmptyBorder(50, 75, 50, 75));
        main.setBackground(new Color(20, 139, 251));
        this.add(main);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SaveEncoder.save(ModuleListeners.save);
            }
        });
    }

    public void displayOnly(String text, Color color) {
        main.removeAll();
        JPanel panel = new JPanel();
        panel.setBackground(this.getBackground());

        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setForeground(color);
        label.setFont(this.getFont());
        label.setPreferredSize(new Dimension(600, 400));

        panel.add(label);
//        main.setFont(FontUtil.getFont(App.class.getClassLoader().getResourceAsStream("./myriadfont.ttf"), 52));
        this.setFont(new JLabel().getFont().deriveFont(52f));
        main.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void displayLogon() {
        Border padding = BorderFactory.createEmptyBorder(15, 25, 30, 25);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 15));
        panel.setBackground(new Color(119, 196, 251));
        panel.setPreferredSize(new Dimension(320, 300));
        panel.setBorder(padding);

        JLabel header = new JLabel("Sign in to Instagram below", SwingConstants.CENTER);
        header.setFont(this.getFont());
        header.setBorder(BorderFactory.createEmptyBorder(10, 0, 15, 0));

        JTextField username = new JTextField();
        username.setText(ModuleListeners.save.getUsername());
        username.setPreferredSize(new Dimension(175, 30));
        username.setFont(this.getFont().deriveFont(16f));

        JTextField password = new JTextField();
        password.setText("Password");
        password.setPreferredSize(new Dimension(175, 30));
        password.setFont(this.getFont().deriveFont(16f));

        JButton login = new JButton("Sign in");
        login.setFocusPainted(false);
        login.setForeground(Color.BLACK);
        login.setFont(this.getFont().deriveFont(24f));
        login.setPreferredSize(new Dimension(100, 40));
        login.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String user = username.getText();
                final String pw = password.getText();

                ModuleListeners.save.setUsername(user);

                JOptionPane.showMessageDialog(null, "Please be patient, logging in may take some time...", "Status", JOptionPane.INFORMATION_MESSAGE);
                Likom.getInstance().setInstagram(Instagram4j.builder().username(user).password(pw).build());
                Likom.getInstance().getInstagram().setup();
                InstagramLoginResult result;
                try {
                    result = Likom.getInstance().getInstagram().login();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                if (result.getStatus().equalsIgnoreCase("fail")) {
                    System.out.println("fail");
                    username.setText("Username");
                    password.setText("Password");
                    JOptionPane.showMessageDialog(null, result.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } else
                    displayMain();
            }
        });

        panel.add(header);
        panel.add(username);
        panel.add(password);
        panel.add(login);

        main.add(panel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    public void displayMain() {
        this.remove(main);
        this.setSize(new Dimension(300, 500));

        JPanel modules = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 32));
        modules.setPreferredSize(new Dimension(300, 500));
        modules.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));
        modules.setBackground(new Color(20, 139, 251));

        ModuleHandler handler = new ModuleHandler();

        JButton system = createModuleButton("System");
        system.addActionListener(handler.getByName(system.getText()));

        JButton hashtags = createModuleButton("HashTags");
        hashtags.addActionListener(handler.getByName(hashtags.getText()));

        JButton interval = createModuleButton("Interval");
        interval.addActionListener(handler.getByName(interval.getText()));

        JButton blacklistusers = createModuleButton("BlacklistUsers");
        blacklistusers.addActionListener(handler.getByName(blacklistusers.getText()));

        JButton comments = createModuleButton("Comments");
        comments.addActionListener(handler.getByName(comments.getText()));

        modules.add(system);
        modules.add(hashtags);
        modules.add(interval);
        modules.add(blacklistusers);
        modules.add(comments);

        this.add(modules);
        this.setVisible(true);
    }

    private JButton createModuleButton(String name) {
        JButton button = new JButton();
        button.setText(name);
        button.setBackground(Color.WHITE);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setFont(this.getFont().deriveFont(18f));
        button.setPreferredSize(new Dimension(200, 30));

        return button;
    }

}
