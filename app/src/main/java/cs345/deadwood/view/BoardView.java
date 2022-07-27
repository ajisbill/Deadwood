package cs345.deadwood.view;

import cs345.deadwood.controller.GameController;
import cs345.deadwood.model.*;
import cs345.deadwood.model.Button;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BoardView implements MouseListener {

    private final GameController controller;
    private final GameEngine model;
    private final GameLog gameLog;
    private JFrame frame;
    private JPanel gameLogPanel;
    private JTextArea gameLogText;
    private final int VERTICAL_PADDING = 5;
    private final int HORIZONTAL_PADDING = 5;

    public BoardView(GameEngine model, GameController controller) {
        this.model = model;
        this.controller = controller;
        this.gameLog = GameLog.getInstance();
        gameLog.registerObservers(this);
    }

    public void init() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1500, 930));
        // board img is 1200 x 900. The control panel is 300 x 900, so we want the frame to be 1500 x 900
        // The top bar on the frame is about 30 pixels in height. To account for that, we increase frame height by 30, so 930.

        // Set layout to null, so we can place widgets based on x-y coordinates.
        frame.setLayout(null);

        // SetSceneView trainStation = new SetSceneView(frame);
        // trainStation.drawSet();
        for (ISet set: model.getSets()) {
            if (set instanceof ISetScene) {
                SetSceneView setView = new SetSceneView(frame, (ISetScene) set, controller, model);
                setView.drawSet();
            } else if ("Trailer".equals(set.getName())) {
                SetTrailerView setView = new SetTrailerView(frame, set, controller);
                setView.drawSet();
            } else if ("Office".equals(set.getName())) {
                SetCastingOfficeView setView = new SetCastingOfficeView(frame, set, controller);
                setView.drawSet();
            } else {
                throw new RuntimeException("Found unexpected set name");
            }
        }




        URL boardImg = getClass().getClassLoader().getResource("img/board.png");
        JLabel board = new JLabel(new ImageIcon(boardImg.getPath()));
        board.setLocation(0, 0);
        board.setSize(1200, 900);
        frame.add(board);

        JPanel controlPanel = createControlPanel();
        controlPanel.setLocation(1200, 0);
        controlPanel.setSize(300, 900);
        frame.add(controlPanel);

        frame.addMouseListener(this);

        frame.pack();
        frame.setVisible(true);
    }

    public void logUpdated(){
        String message = gameLog.getMessage();
        System.out.println(message);
        gameLogText.append(message + "\n");
        //gameLogText.setText(message + "\n" + gameLogText.getText());
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(300, 900));
        // Set height same as the board image. board image dimensions are 1200 x 900

        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.PAGE_AXIS));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0)); // Add padding around edges

        JLabel team = new JLabel("Team Name");
        team.setFont(new Font("TimesRoman", Font.BOLD, 20));
        controlPanel.add(team);
        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding

        controlPanel.add(new JSeparator());
        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding

        JLabel playerInfoLabel = new JLabel("Players");
        playerInfoLabel.setFont(new Font("TimesRoman", Font.BOLD, 18));
        controlPanel.add(playerInfoLabel);
        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding

        // Show players
//        controlPanel.add(showPlayerInfo(1, "Train Station", 2, 3, "dice_b1.png"));
//        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding
//        controlPanel.add(showPlayerInfo(2, "Trailer", 2, 3, "dice_r1.png"));
//        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding
//        controlPanel.add(showPlayerInfo(3, "Trailer", 2, 3, "dice_p1.png"));
//        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding
//        controlPanel.add(showPlayerInfo(4, "Trailer", 2, 3, "dice_y1.png"));
//        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding
//        controlPanel.add(showPlayerInfo(5, "Trailer", 2, 3, "dice_w1.png"));
//        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding

        // add players with PlayerView class
//        Player p1 = new Player(1, "Trailer", 0, 0,0,10);
//        Player p2 = new Player(2, "Trailer", 0, 0,0,10);
//        PlayerView p1View = new PlayerView(p1, "dice_b2.png");
//        PlayerView p2View = new PlayerView(p2, "dice_c2.png");
//        controlPanel.add(p1View);
//        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding
//        controlPanel.add(p2View);
//        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding

        List<Player> playerList= model.getPlayers();
        //List<String> diceImages = Arrays.asList("dice_b1.png", "dice_c1.png", "dice_r1.png", "dice_p1.png", "dice_y1.png", "dice_w1.png");

        for(int i = 0; i< playerList.size();i++){
            playerList.get(i).setModel(model);
            PlayerView pView = new PlayerView(playerList.get(i));
            //System.out.println("Player" + String.valueOf(i+1) + playerList.get(i).isActive());
            controlPanel.add(pView);
            controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding
        }




        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding

        controlPanel.add(new JSeparator());
        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding

        controlPanel.add(getMovePanel());
        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding

        controlPanel.add(new JSeparator());
        controlPanel.add(Box.createRigidArea(new Dimension(0,VERTICAL_PADDING))); // Add padding

        JPanel gameLogPanel = miscInteraction();
        controlPanel.add(gameLogPanel);
        this.gameLogPanel = gameLogPanel;


        return controlPanel;
    }

    private JPanel showPlayerInfo(int i, String area, int cash, int credit, String dice) {

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300 - HORIZONTAL_PADDING*2, 50));
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));

        panel.add(new JLabel("Player " + i + ": "));
        panel.add(Box.createRigidArea(new Dimension(HORIZONTAL_PADDING,0))); // Add padding

        JLabel playerDice= new JLabel(new ImageIcon(getClass().getClassLoader().getResource("img/" + dice).getPath()));
        panel.add(playerDice);
        panel.add(Box.createRigidArea(new Dimension(HORIZONTAL_PADDING,0))); // Add padding

        JLabel playerLocation = new JLabel(area);
        panel.add(playerLocation);
        panel.add(Box.createRigidArea(new Dimension(HORIZONTAL_PADDING,0))); // Add padding

        JLabel money = new JLabel("$" + cash + " C" + credit); // 2 dollars and 3 credits.
        panel.add(money);
        panel.add(Box.createRigidArea(new Dimension(HORIZONTAL_PADDING,0))); // Add padding

        return panel;
    }

    private JPanel getMovePanel() {
        JPanel movePanel = new JPanel();
        movePanel.setPreferredSize(new Dimension(300 - HORIZONTAL_PADDING*2, 200));
        JLabel panelTitle = new JLabel("Move Options");
        panelTitle.setFont(new Font("TimesRoman", Font.BOLD, 22));
        movePanel.add(panelTitle);

        JPanel buttonGrid = new JPanel();
        int x = 2;
        int y = 3;
        buttonGrid.setLayout(new GridLayout(x, y));
        List<String> buttonNames = Arrays.asList("Move", "Take Role", "Act", "Rehearse", "Upgrade", "End Turn");
        for (int i = 0; i < x * y; i++) {
            ButtonView buttonView = new ButtonView(new Button(buttonNames.get(i)),buttonNames.get(i), this.controller);
            buttonView.setPreferredSize(new Dimension(140, 60));
            movePanel.add(buttonView);
        }
        movePanel.add(buttonGrid);

//        JTextArea comment = new JTextArea("Player interaction space. E.g., Ask what the player wants to do, show valid moves");
//        comment.setLineWrap(true);
//        comment.setPreferredSize(movePanel.getPreferredSize());
//        movePanel.add(comment);

        return movePanel;
    }


    private JPanel miscInteraction() {
        // free space to use for comments or any game related stuff. E.g., show rolling die or show game log.

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(300 - HORIZONTAL_PADDING*2, 200));

        JLabel panelTitle = new JLabel("Game Log");
        panelTitle.setFont(new Font("TimesRoman", Font.BOLD, 18));
        panel.add(panelTitle);



        gameLogText = new JTextArea();
        gameLogText.setLineWrap(true);
        //panel.add(gameLogText);

        JScrollPane sp = new JScrollPane(gameLogText);
        sp.setAutoscrolls(true);
        sp.setPreferredSize(panel.getPreferredSize());

        panel.add(sp);
        return panel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // The top bar of the frame is about 30 pixels in height. So to get the x,y values on the board, subtract 30 from the y value.
        System.out.println("Mouse clicked at X = " + e.getX() + ", Y = " + (e.getY() - 30));
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
