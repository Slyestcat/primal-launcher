import net.http.HttpDownload;
import net.sql.forumPosts;
import net.sql.playersOnline;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;


public class Launcher {

    private JFrame primalLauncher;

    private String launcherName = "Primal Launcher";
    public static String welcomeText = "Welcome to Primal Launcher!";
    public static String websiteURL = "https://primal.ps/";
    public static String forumURL = "https://primal.ps/forum";
    public static String voteURL = "https://primal.ps/vote";
    public static String hiscoresURL = "https://primal.ps/hiscores";
    public static String storeURL = "https://primal.ps/store";
    public static String discordURL = "https://discord.gg/4jTb6Y3";
    public static String youtubeURL = "https://youtube.com/ProjectPrimal";

    private String clientVersion = "CLIENT VERSION: ";

    public static String jarName = "primal.jar";
    public static String gameDownloadUrl = "http://157.245.143.62/dl/primal.jar";
    public static String homeDir = System.getProperty("user.home");
    public static String saveDir = homeDir + File.separator + ".primal";
    public static String gameSaveLocation = saveDir + File.separator + jarName;

    public static void main(String args[]) {
        EventQueue.invokeLater(() -> {
            try {
                Launcher window = new Launcher();
                window.primalLauncher.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    public class MoveListener implements MouseListener, MouseMotionListener {

        private Point pressedPoint;
        private Rectangle frameBounds;

        @Override
        public void mouseClicked(MouseEvent event) {
        }

        @Override
        public void mousePressed(MouseEvent event) {
            this.frameBounds = primalLauncher.getBounds();
            this.pressedPoint = event.getPoint();
        }

        @Override
        public void mouseReleased(MouseEvent event) {
            moveJFrame(event);
        }

        @Override
        public void mouseEntered(MouseEvent event) {
        }

        @Override
        public void mouseExited(MouseEvent event) {
        }

        @Override
        public void mouseDragged(MouseEvent event) {
            moveJFrame(event);
        }

        @Override
        public void mouseMoved(MouseEvent event) {
        }

        private void moveJFrame(MouseEvent event) {
            Point endPoint = event.getPoint();

            int xDiff = endPoint.x - pressedPoint.x;
            int yDiff = endPoint.y - pressedPoint.y;
            frameBounds.x += xDiff;
            frameBounds.y += yDiff;
            primalLauncher.setBounds(frameBounds);
        }

    }

    public Launcher() throws SQLException, IOException {
        initialize();
    }

    public void initialize() throws SQLException, IOException {

        URL primalDownload_URL = new URL(gameDownloadUrl);
        Download primalJar = new Download(primalDownload_URL);
        HttpURLConnection httpConnection = (HttpURLConnection) primalDownload_URL.openConnection();
        httpConnection.setRequestMethod("HEAD");
        httpConnection.connect();
        long lastModifiedWeb = httpConnection.getLastModified();
        httpConnection.disconnect();
        File jarFile = new File(gameSaveLocation);
        boolean exists = jarFile.exists();
        long lastModifiedLocal = jarFile.lastModified();
        File saveDirectory = new File (saveDir);
        if (!saveDirectory.exists()){
            saveDirectory.mkdirs();
        }

        primalLauncher = new JFrame();
        primalLauncher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        primalLauncher.setUndecorated(true);
        primalLauncher.getContentPane().setForeground(SystemColor.menu);
        primalLauncher.setBackground(new Color(255, 255, 255));
        primalLauncher.setVisible(true);
        primalLauncher.setIconImage(Toolkit.getDefaultToolkit().getImage(Launcher.class.getResource("/img/nav-panel/logo-icon.png")));
        primalLauncher.getContentPane().setName(launcherName);
        primalLauncher.setResizable(false);
        primalLauncher.setBounds(0, 0, 840, 560);
        primalLauncher.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        primalLauncher.setLocationRelativeTo(null);
        primalLauncher.getContentPane().setLayout(new BoxLayout(primalLauncher.getContentPane(), BoxLayout.X_AXIS));
        MoveListener listener = new MoveListener();
        primalLauncher.addMouseListener(listener);
        primalLauncher.addMouseMotionListener(listener);

        JPanel main = new JPanel();
        main.setBorder(null);
        main.setForeground(SystemColor.menu);
        primalLauncher.getContentPane().add(main);
        main.setLayout(null);

        JLabel fadeIcon = new JLabel("");
        fadeIcon.setIcon(new ImageIcon(Launcher.class.getResource("/img/news/news-overlay-fade.png")));
        fadeIcon.setBounds(141, 235, 445, 44);
        main.add(fadeIcon);

        JLabel fadeIcon2 = new JLabel("");
        fadeIcon2.setIcon(new ImageIcon(Launcher.class.getResource("/img/news/news-overlay-fade.png")));
        fadeIcon2.setBounds(141, 391, 445, 44);
        main.add(fadeIcon2);

        JLabel CloseButton = new JLabel("");
        CloseButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        CloseButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/top-panel/Close-window.png")));
        CloseButton.setBounds(816, 8, 15, 15);
        main.add(CloseButton);

        JLabel MinButton = new JLabel("");
        MinButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        MinButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/top-panel/minimize-window.png")));
        MinButton.setBounds(793, 8, 15, 15);
        main.add(MinButton);

        JLabel IconButton = new JLabel("");
        IconButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        IconButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/nav-panel/logo-icon.png")));
        IconButton.setBounds(31, 58, 59, 67);
        main.add(IconButton);

        JLabel ForumButton = new JLabel("");
        ForumButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ForumButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/nav-panel/Forums.png")));
        ForumButton.setBounds(39, 156, 45, 61);
        main.add(ForumButton);

        JLabel VoteButton = new JLabel("");
        VoteButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        VoteButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/nav-panel/Vote.png")));
        VoteButton.setBounds(39, 236, 45, 61);
        main.add(VoteButton);

        JLabel HiscoresButton = new JLabel("");
        HiscoresButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        HiscoresButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/nav-panel/hiscores.png")));
        HiscoresButton.setBounds(38, 317, 47, 61);
        main.add(HiscoresButton);

        JLabel StoreButton = new JLabel("");
        StoreButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        StoreButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/nav-panel/store.png")));
        StoreButton.setBounds(39, 399, 45, 61);
        main.add(StoreButton);

        JLabel DiscordButton = new JLabel("");
        DiscordButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        DiscordButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/nav-panel/discord.png")));
        DiscordButton.setBounds(27, 520, 18, 21);
        main.add(DiscordButton);

        JLabel YoutubeButton = new JLabel("");
        YoutubeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        YoutubeButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/nav-panel/youtube.png")));
        YoutubeButton.setBounds(73, 522, 22, 17);
        main.add(YoutubeButton);

        JLabel ProgressBar = new JLabel("");
        ProgressBar.setIcon(new ImageIcon(Launcher.class.getResource("/img/download-bar/download-progress-bar-full.png")));
        ProgressBar.setBounds(146, 472, 434, 20);
        //main.add(ProgressBar);

        //Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("img/OpenSans-Regular.ttf")).deriveFont(12f);
        //GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //ge.registerFont(customFont);

        JLabel titleLeft = new JLabel(welcomeText);
        titleLeft.setBounds(27, 9, 175, 12);
        titleLeft.setForeground(new Color(109, 154, 185));
        main.add(titleLeft);

        JLabel playerIcon = new JLabel("");
        playerIcon.setIcon(new ImageIcon(Launcher.class.getResource("/img/top-panel/user-icon.png")));
        playerIcon.setBounds(621, 11, 9, 10);
        main.add(playerIcon);


        JLabel downloadPercent = new JLabel("");
        downloadPercent.setForeground(new Color(109, 154, 185));
        main.add(downloadPercent);

        JLabel downloadInfo = new JLabel("");
        if (exists & lastModifiedWeb < lastModifiedLocal) {
            downloadInfo.setText("Primal is up to date!");
        } else {
            downloadInfo.setText("Please update Primal!");
        }
        downloadInfo.setBounds(141, 505, 208, 14);
        downloadInfo.setForeground(new Color(228, 191, 111));
        main.add(downloadInfo);

        String[] optionsToChoose = {"LIVE", "Beta", "Development"};
        JComboBox<String> jComboBox = new JComboBox<>(optionsToChoose);
        jComboBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jComboBox.setBounds(141, 525, 115, 20);
        jComboBox.setForeground(new Color(228, 191, 111));
        jComboBox.setBackground(new Color(52, 84, 111));
        jComboBox.setFocusable(false);
        //main.add(jComboBox);

        JLabel PlayButton = new JLabel("");
        PlayButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        if (exists & lastModifiedWeb < lastModifiedLocal) {
            PlayButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/download-bar/Play-BTN.png")));
        } else {
            PlayButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/download-bar/Play-BTN-update.png")));
        }
        PlayButton.setBounds(607, 466, 213, 60);
        main.add(PlayButton);

        JLabel playerOnline = new JLabel(playersOnline.getOnline() + " Other Players Online");
        playerOnline.setBounds(634, 9, 175, 12);
        playerOnline.setForeground(new Color(109, 154, 185));
        main.add(playerOnline);

        String fileURL = "http://157.245.143.62/dl/LIVE_version.txt";
        String homeDir = System.getProperty("user.home");
        String saveDir = homeDir + File.separator + ".primal";
        String txtName = "LIVE_version.txt";


        File directory = new File(saveDir);
        if (!directory.exists()) {
            directory.mkdir();
        }

        try {
            HttpDownload.downloadFile(fileURL, saveDir);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        File file = new File(saveDir + File.separator + txtName);

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String st = null;
        while (true) {
            try {
                if (!((st = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(st);


            JLabel clientVersion = new JLabel("CLIENT VERSION: " + st);
            clientVersion.setBounds(607, 535, 208, 14);
            clientVersion.setForeground(new Color(155, 151, 144, 119));
            clientVersion.setHorizontalAlignment(SwingConstants.CENTER);
            main.add(clientVersion);
        }

        JProgressBar dynamicProgressBar = new JProgressBar(0,100);
        dynamicProgressBar.setBounds(146, 472, 434, 20);
        dynamicProgressBar.setForeground(new Color(52, 84, 111));
        dynamicProgressBar.setBorderPainted(false);
        dynamicProgressBar.setBackground(new Color(5,13,19));
        if (exists & lastModifiedWeb < lastModifiedLocal) {
            dynamicProgressBar.setValue(100);
        } else {
            dynamicProgressBar.setValue(0);
        }
        main.add(dynamicProgressBar);

        ResultSet rs = forumPosts.getAnnouncements();
        int count = 0;
        while (rs.next()) {
            ++count;
            String title = rs.getString("title").toUpperCase(Locale.ROOT);
            Long postDateLong = rs.getLong("start_date");
            int threadID = rs.getInt("tid");
            String postText = rs.getString("post");
            String strippedText = postText.replaceAll("<!--.*?-->", "").replaceAll("<[^>]+>", "");
            String cutText = strippedText.substring(0, 250);
            LocalDate ld = Instant.ofEpochMilli(postDateLong * 1000).atZone(ZoneId.systemDefault()).toLocalDate();
            if (count == 1) {
                JLabel threadTitle1 = new JLabel(title);
                threadTitle1.setBounds(160, 155, 262, 14);
                threadTitle1.setForeground(new Color(228, 191, 111));
                threadTitle1.setFont(new Font("Georgia", Font.BOLD, 12));
                main.add(threadTitle1);
                JLabel postDate = new JLabel(String.valueOf(ld));
                postDate.setBounds(160, 169, 262, 14);
                postDate.setForeground(new Color(109, 154, 185));
                main.add(postDate);
                JLabel readPost1 = new JLabel("");
                readPost1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                readPost1.setIcon(new ImageIcon(Launcher.class.getResource("/img/news/read-btn.png")));
                readPost1.setBounds(470, 158, 96, 20);
                main.add(readPost1);
                JLabel postTextData = new JLabel("<html><p>" + cutText + "...</p></html>");
                postTextData.setBounds(160, 205, 395, 60);
                postTextData.setForeground(new Color(228, 191, 111));
                main.add(postTextData);
                readPost1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        openURL.open(forumURL + "/index.php?app=forums&module=forums&controller=topic&id=" + threadID);
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        readPost1.setIcon(new ImageIcon(Launcher.class.getResource("/img/news/read-btn-hover.png")));
                    }

                    public void mouseExited(MouseEvent e) {
                        readPost1.setIcon(new ImageIcon(Launcher.class.getResource("/img/news/read-btn.png")));
                    }
                });

            } else if (count == 2) {
                JLabel threadTitle1 = new JLabel(title);
                threadTitle1.setBounds(160, 311, 262, 14);
                threadTitle1.setForeground(new Color(228, 191, 111));
                threadTitle1.setFont(new Font("Georgia", Font.BOLD, 12));
                main.add(threadTitle1);
                JLabel postDate = new JLabel(String.valueOf(ld));
                postDate.setBounds(160, 325, 262, 14);
                postDate.setForeground(new Color(109, 154, 185));
                main.add(postDate);
                JLabel readPost1 = new JLabel("");
                readPost1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                readPost1.setIcon(new ImageIcon(Launcher.class.getResource("/img/news/read-btn.png")));
                readPost1.setBounds(470, 314, 96, 20);
                main.add(readPost1);
                JLabel postTextData = new JLabel("<html>" + cutText + "...</html>");
                postTextData.setBounds(160, 361, 395, 60);
                postTextData.setForeground(new Color(228, 191, 111));
                main.add(postTextData);
                readPost1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        openURL.open(forumURL + "/index.php?app=forums&module=forums&controller=topic&id=" + threadID);
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        readPost1.setIcon(new ImageIcon(Launcher.class.getResource("/img/news/read-btn-hover.png")));
                    }

                    public void mouseExited(MouseEvent e) {
                        readPost1.setIcon(new ImageIcon(Launcher.class.getResource("/img/news/read-btn.png")));
                    }
                });

            } else {
                return;
            }
        }
        rs.close();

        rs = forumPosts.getPosts();
        int count2 = 0;
        while (rs.next()) {
            ++count2;
            String title = rs.getString("title");
            String posterName = rs.getString("starter_name");
            int threadID = rs.getInt("tid");
            if (count2 == 1) {
                JLabel threadTitle1 = new JLabel(title);
                threadTitle1.setBounds(622, 160, 150, 14);
                threadTitle1.setForeground(new Color(109, 154, 185));
                threadTitle1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                main.add(threadTitle1);
                JLabel postDate = new JLabel("Posted by: " + posterName);
                postDate.setBounds(622, 175, 150, 14);
                postDate.setForeground(new Color(52, 84, 111));
                main.add(postDate);
                threadTitle1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        openURL.open(forumURL + "/index.php?app=forums&module=forums&controller=topic&id=" + threadID);
                    }
                });
            } else if (count2 == 2) {
                JLabel threadTitle1 = new JLabel(title);
                threadTitle1.setBounds(622, 218, 150, 14);
                threadTitle1.setForeground(new Color(109, 154, 185));
                threadTitle1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                main.add(threadTitle1);
                JLabel postDate = new JLabel("Posted by: " + posterName);
                postDate.setBounds(622, 232, 150, 14);
                postDate.setForeground(new Color(52, 84, 111));
                main.add(postDate);
                threadTitle1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        openURL.open(forumURL + "/index.php?app=forums&module=forums&controller=topic&id=" + threadID);
                    }
                });
            } else if (count2 == 3) {
                JLabel threadTitle1 = new JLabel(title);
                threadTitle1.setBounds(622, 276, 150, 14);
                threadTitle1.setForeground(new Color(109, 154, 185));
                threadTitle1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                main.add(threadTitle1);
                JLabel postDate = new JLabel("Posted by: " + posterName);
                postDate.setBounds(622, 291, 150, 14);
                postDate.setForeground(new Color(52, 84, 111));
                main.add(postDate);
                threadTitle1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        openURL.open(forumURL + "/index.php?app=forums&module=forums&controller=topic&id=" + threadID);
                    }
                });
            } else if (count2 == 4) {
                JLabel threadTitle1 = new JLabel(title);
                threadTitle1.setBounds(622, 335, 150, 14);
                threadTitle1.setForeground(new Color(109, 154, 185));
                threadTitle1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                main.add(threadTitle1);
                JLabel postDate = new JLabel("Posted by: " + posterName);
                postDate.setBounds(622, 348, 150, 14);
                postDate.setForeground(new Color(52, 84, 111));
                main.add(postDate);
                threadTitle1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        openURL.open(forumURL + "/index.php?app=forums&module=forums&controller=topic&id=" + threadID);
                    }
                });
            } else if (count2 == 5) {
                JLabel threadTitle1 = new JLabel(title);
                threadTitle1.setBounds(622, 393, 150, 14);
                threadTitle1.setForeground(new Color(109, 154, 185));
                threadTitle1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                main.add(threadTitle1);
                JLabel postDate = new JLabel("Posted by: " + posterName);
                postDate.setBounds(622, 406, 150, 14);
                postDate.setForeground(new Color(52, 84, 111));
                main.add(postDate);
                threadTitle1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        openURL.open(forumURL + "/forum/index.php?app=forums&module=forums&controller=topic&id=" + threadID);
                    }
                });
            } else {
                return;
            }
        }
        rs.close();

        JLabel Background = new JLabel("");
        Background.setIcon(new ImageIcon(Launcher.class.getResource("/img/background-with-assets.png")));
        Background.setBounds(0, 0, 840, 560);
        main.add(Background);

        IconButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                openURL.open(websiteURL);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                IconButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/nav-panel/logo-icon.png")));
            }

            public void mouseExited(MouseEvent e) {
                IconButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/nav-panel/logo-icon.png")));
            }
        });
        ForumButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                openURL.open(forumURL);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ForumButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/nav-panel/Forums-hover.png")));
            }

            public void mouseExited(MouseEvent e) {
                ForumButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/nav-panel/Forums.png")));
            }
        });
        VoteButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                openURL.open(voteURL);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                VoteButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/nav-panel/Vote-hover.png")));
            }

            public void mouseExited(MouseEvent e) {
                VoteButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/nav-panel/Vote.png")));
            }
        });
        HiscoresButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                openURL.open(hiscoresURL);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                HiscoresButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/nav-panel/hiscores-hover.png")));
            }

            public void mouseExited(MouseEvent e) {
                HiscoresButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/nav-panel/hiscores.png")));
            }
        });
        StoreButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                openURL.open(storeURL);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                StoreButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/nav-panel/store-hover.png")));
            }

            public void mouseExited(MouseEvent e) {
                StoreButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/nav-panel/store.png")));
            }
        });
        DiscordButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                openURL.open(discordURL);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                DiscordButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/nav-panel/discord-hover.png")));
            }

            public void mouseExited(MouseEvent e) {
                DiscordButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/nav-panel/discord.png")));
            }
        });
        YoutubeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                openURL.open(youtubeURL);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                YoutubeButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/nav-panel/youtube-hover.png")));
            }

            public void mouseExited(MouseEvent e) {
                YoutubeButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/nav-panel/youtube.png")));
            }
        });

        PlayButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!saveDirectory.exists()){
                    saveDirectory.mkdirs();
                }
                long lastModifiedWeb = httpConnection.getLastModified();
                httpConnection.disconnect();
                File jarFile = new File(gameSaveLocation);
                boolean exists = jarFile.exists();
                long lastModifiedLocal = jarFile.lastModified();
                if (exists & lastModifiedWeb < lastModifiedLocal) {
                    System.out.println("Game file exists and is most up to date");
                    try {
                        Desktop.getDesktop().open(new File(gameSaveLocation));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    /*try {
                        runJar.run(gameSaveLocation);

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }                     */
                } else {
                    System.out.println("Game file is missing or need to be updated");
                    primalJar.start();
                    int percentComplete;
                    downloadPercent.setText("Updating...");
                    downloadPercent.setBounds(525, 505, 208, 14);
                    downloadPercent.update(downloadPercent.getGraphics());
                    while (primalJar.getProgress() < 100.0f) {
                        percentComplete = Math.round(primalJar.getProgress());
                        System.out.println(percentComplete);
                        dynamicProgressBar.setValue(percentComplete);
                        dynamicProgressBar.update(dynamicProgressBar.getGraphics());

                    }

                    if (primalJar.getStatus() == Download.COMPLETE) {
                        downloadPercent.setText("Updated!");
                        downloadPercent.setBounds(530, 505, 208, 14);
                        downloadPercent.update(downloadPercent.getGraphics());
                        dynamicProgressBar.setValue(100);
                        dynamicProgressBar.update(dynamicProgressBar.getGraphics());
                    }
                }
            }

            public void mouseEntered(MouseEvent e) {
                if (exists & lastModifiedWeb < lastModifiedLocal) {
                    PlayButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/download-bar/Play-BTN-hover.png")));
                } else {
                    PlayButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/download-bar/Play-BTN-update-hover.png")));
                }
                if (primalJar.getStatus() == Download.COMPLETE) {
                    PlayButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/download-bar/Play-BTN-hover.png")));
                }

            }

            public void mouseExited(MouseEvent e) {
                if (exists & lastModifiedWeb < lastModifiedLocal) {
                    PlayButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/download-bar/Play-BTN.png")));
                } else {
                    PlayButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/download-bar/Play-BTN-update.png")));
                }
                if (primalJar.getStatus() == Download.COMPLETE) {
                    PlayButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/download-bar/Play-BTN-hover.png")));
                }

            }
        });

        CloseButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                System.exit(0);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                CloseButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/top-panel/Close-window-hover.png")));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                CloseButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/top-panel/Close-window.png")));
            }
        });
        MinButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent arg0) {
                primalLauncher.setState(primalLauncher.ICONIFIED);
            }

            public void mouseEntered(MouseEvent e) {
                MinButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/top-panel/minimize-window-hover.png")));
            }

            public void mouseExited(MouseEvent e) {
                MinButton.setIcon(new ImageIcon(Launcher.class.getResource("/img/top-panel/minimize-window.png")));
            }
        });
    }
}
