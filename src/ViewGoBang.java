
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ViewGoBang extends JFrame{
    private int sizeGB = 10;
    
    //Start panel
    private JPanel startPanel = new JPanel(new GridLayout(3,1));
    private JLabel titleLabel = new JLabel("GOBANG GAME");
    private JButton aiButton = new JButton("AI");
    private JPanel modePanel = new JPanel(new GridLayout(1,3));
    private JButton pvpButton = new JButton("PvP");
    private String introduceText = new String("How to Play: The game is played in turns. \n"
            + "A stone must be put on the intersection of the lines.\n "
            + "You must try to prevent your opponent from achieving 5 aligned stones first\n "
            + "by putting your stone in between his/hers \n "
            + "no matter whether it is horizontally, vertically or diagonally \n"
            + "place your Go stones using the left click of your computer mouse");
    private JTextArea introduce = new JTextArea(introduceText);
    private JScrollPane scroll = new JScrollPane(introduce);
    private JButton exitInStartPanelButton = new JButton("Exit");
    
    //Ai option panel
    private JPanel aiOptionPanel = new JPanel(new GridLayout(3,1));
    private JLabel aiOptionLabel = new JLabel("AI mode option");
    private JPanel levelPanel = new JPanel(new GridLayout(1,3));
    private ButtonGroup levelGroup = new ButtonGroup();
    private JRadioButton easyChoice = new JRadioButton("Easy", true);
    private JRadioButton mediumChoice = new JRadioButton("Medium");
    private JRadioButton hardChoice = new JRadioButton("Hard");
    private JPanel aiIconChooserPanel = new JPanel(new GridLayout(1,2));
    private JButton icon1ForAiButton = new JButton("Icon 1");
    private JButton icon2ForAiButton = new JButton("Icon 2");
    
    //PvP option panel
    private JPanel pvpOptionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JLabel pvpOptionLabel = new JLabel("PvP mode option");
    private JPanel playerPanel = new JPanel(new GridLayout(2,3));
    private JLabel player1Label = new JLabel("Player 1");
    //private ButtonGroup player1group = new ButtonGroup();
    private JButton player1IconForPvP_1 = new JButton();
    private JButton player1IconForPvP_2 = new JButton();
    private JLabel player2Label = new JLabel("Player 2");
    //private ButtonGroup player2group = new ButtonGroup();
    private JButton player2IconForPvP_1 = new JButton();
    private JButton player2IconForPvP_2 = new JButton();
    private JButton startPvPButton = new JButton("Start PvP");
    
    //Battle field panel
    private JPanel battleFieldPanel = new JPanel(new GridLayout(sizeGB,sizeGB));
    private JPanel mainFramPanel = new JPanel(new BorderLayout());
    private JLabel battlefieldTitle = new JLabel("Gobang");
    private JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    private JButton newGameButton = new JButton("New game");
    private JTextField statusTextField = new JTextField(20);
    private JButton exitInMainFramButton = new JButton("Exit");
    
    //CELL
    
    Cell cells[][] = new Cell[sizeGB][sizeGB];
    
    //Decoration
    Font fontForTitle = new Font ("Tahoma", Font.BOLD, 20);
    Font fontNormal = new Font ("Tahoma", Font.TRUETYPE_FONT, 14);
    
    ImageIcon image1 = new ImageIcon("Stone1.png");
    Image i1 = image1.getImage();
    ImageIcon image2 = new ImageIcon("Stone2.png");
    Image i2 = image2.getImage();
    ImageIcon image3 = new ImageIcon("Stone3.png");
    Image i3 = image3.getImage();
    ImageIcon image4 = new ImageIcon("Stone4.png");
    Image i4 = image4.getImage();
    ImageIcon imageCell = new ImageIcon("Cell.png");
    Image iCell = imageCell.getImage();
    
    //Server-Client GUI components
    private JPanel serverClientPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JPanel serverChooserPanel = new JPanel(new GridLayout(1,2));
    private JButton serverIcon1 = new JButton();
    private JButton serverIcon2 = new JButton();
    private JPanel clientChooserPaner = new JPanel(new GridLayout(1,2));
    private JButton clientIcon1 = new JButton();
    private JButton clientIcon2 = new JButton();
    private JButton startServerClient = new JButton("Start game");
    
    public ViewGoBang(){
        
        //DEFINE START PANEL
        startPanel.add(titleLabel);
        startPanel.add(scroll);
        startPanel.add(modePanel);
        modePanel.add(aiButton);
        modePanel.add(pvpButton);
        modePanel.add(exitInStartPanelButton);
        introduce.setEditable(false);
        aiButton.setBackground(Color.GREEN);
        pvpButton.setBackground(Color.GREEN);
        exitInStartPanelButton.setBackground(Color.GREEN);
        titleLabel.setForeground(Color.GREEN);
        titleLabel.setFont(fontForTitle);
        startPanel.setSize(new Dimension(300,300));
        startPanel.setBackground(Color.BLACK);
        startPanel.setVisible(true);
        
        //DEFINE AI OPTION
        icon1ForAiButton.setIcon(image1);
        icon2ForAiButton.setIcon(image2);
        aiOptionLabel.setFont(fontForTitle);
        levelGroup.add(easyChoice);
        levelGroup.add(mediumChoice);
        levelGroup.add(hardChoice);
        aiIconChooserPanel.add(icon1ForAiButton);
        aiIconChooserPanel.add(icon2ForAiButton);
        aiOptionPanel.add(aiOptionLabel);
        levelPanel.add(easyChoice);
        levelPanel.add(mediumChoice);
        levelPanel.add(hardChoice);
        aiOptionPanel.add(levelPanel);
        aiOptionPanel.add(aiIconChooserPanel);
        aiOptionPanel.setSize(new Dimension(300,300));
        aiOptionPanel.setBackground(Color.BLACK);
        aiOptionLabel.setForeground(Color.GREEN);
        easyChoice.setForeground(Color.WHITE);
        mediumChoice.setForeground(Color.WHITE);
        hardChoice.setForeground(Color.WHITE);
        icon1ForAiButton.setBackground(Color.GREEN);
        icon2ForAiButton.setBackground(Color.GREEN);
        levelPanel.setBackground(Color.BLACK);
        easyChoice.setBackground(Color.BLACK);
        mediumChoice.setBackground(Color.BLACK);
        hardChoice.setBackground(Color.BLACK);
        aiOptionPanel.setVisible(false);
        
        
        //DEFINE PvP OPTION
        
        //player1group.add(player1IconForPvP_1);
        //player1group.add(player1IconForPvP_2);
        //player2group.add(player1IconForPvP_1);
        //player2group.add(player1IconForPvP_2);
        playerPanel.add(player1Label);
        playerPanel.add(player1IconForPvP_1);
        playerPanel.add(player1IconForPvP_2);
        playerPanel.add(player2Label);
        playerPanel.add(player2IconForPvP_1);
        playerPanel.add(player2IconForPvP_2);
        pvpOptionPanel.add(pvpOptionLabel);
        pvpOptionPanel.add(playerPanel);
        pvpOptionPanel.add(startPvPButton);
        pvpOptionPanel.setSize(new Dimension(300,200));
        player1IconForPvP_1.setIcon(image1);
        player1IconForPvP_2.setIcon(image2);
        player2IconForPvP_1.setIcon(image3);
        player2IconForPvP_2.setIcon(image4);
        pvpOptionLabel.setFont(fontForTitle);
        startPvPButton.setBackground(Color.GREEN);
        pvpOptionLabel.setForeground(Color.GREEN);
        pvpOptionPanel.setBackground(Color.BLACK);
        player1Label.setBackground(Color.BLACK);
        player1Label.setForeground(Color.GREEN);
        player2Label.setBackground(Color.BLACK);
        player2Label.setForeground(Color.GREEN);
        playerPanel.setBackground(Color.BLACK);
        player1IconForPvP_1.setBackground(Color.GREEN);
        player1IconForPvP_2.setBackground(Color.GREEN);
        player2IconForPvP_1.setBackground(Color.GREEN);
        player2IconForPvP_2.setBackground(Color.GREEN);
        pvpOptionPanel.setVisible(true);
        
        //DEFINE MAIN FRAME
        northPanel.add(battlefieldTitle);
        northPanel.add(statusTextField);
        northPanel.add(newGameButton);
        northPanel.add(exitInMainFramButton);
        mainFramPanel.add(northPanel, BorderLayout.NORTH);
        for (int i = 0; i < sizeGB;i++){
            for (int j = 0; j < sizeGB; j++){
                battleFieldPanel.add(cells[i][j] = new Cell());
            }
        }
        mainFramPanel.add(battleFieldPanel, BorderLayout.CENTER);
        battleFieldPanel.setSize(new Dimension(500,500));
        mainFramPanel.setSize(new Dimension(500,600));
        //mainFramPanel.setVisible(true);
        northPanel.setBackground(Color.BLACK);
        statusTextField.setEditable(false);
        statusTextField.setBackground(Color.BLACK);
        statusTextField.setForeground(Color.GREEN);
        newGameButton.setBackground(Color.GREEN);
        exitInMainFramButton.setBackground(Color.GREEN);
        battlefieldTitle.setFont(fontForTitle);
        battlefieldTitle.setForeground(Color.GREEN);
        mainFramPanel.setVisible(false);
        
        
        //Server-client
        startServerClient.setBackground(Color.GREEN);
        serverChooserPanel.add(startServerClient);
        serverIcon1.setBackground(Color.BLACK);
        serverIcon2.setBackground(Color.BLACK);
        clientIcon1.setBackground(Color.BLACK);
        clientIcon2.setBackground(Color.BLACK);
        serverIcon1.setIcon(image1);
        serverIcon2.setIcon(image2);
        clientIcon1.setIcon(image3);
        clientIcon2.setIcon(image4);
        serverChooserPanel.add(serverIcon1);
        serverChooserPanel.add(serverIcon2);
        clientChooserPaner.add(clientIcon1);
        clientChooserPaner.add(clientIcon2);
        serverClientPanel.setSize(new Dimension(200,80));
        serverClientPanel.setVisible(true);
        


        
        
        
        //TEST
        add(startPanel);
        //add(aiOptionPanel);
        //add(pvpOptionPanel);
        //add(mainFramPanel);
        
        //Default
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setSize(500,600);//mainFramPanel
        //this.setSize(300,200);//PvP
        //this.setSize(300,300);//AI
        this.setSize(300,300);//Start
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("Gobang");


    }
    
    class Cell extends JPanel{
        // x or o - empty: _
        private char stone = '_';
        public void setStone(char stone){
            this.stone = stone;
            repaint();
        }
        public char getStone(){
            return stone;
        }
        //1-2-3-4 for image icon
        public int style = 0;
        public int getStyle(){
            return style;
        }
        public void setStyle(int style){
            this.style = style;
            repaint();
        }
        public Cell(){
            
            setVisible(true);
            setPreferredSize(new Dimension(50,50));
            setMinimumSize(new Dimension(50,50));
            setMaximumSize(new Dimension(50,50));
            //setBorder(new LineBorder(Color.YELLOW, 1));

        }
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            g.drawImage(iCell, 0, 0, this);
            g.setColor(Color.WHITE);
            g.drawLine(getWidth()/2, 0, getWidth()/2, getHeight());
            g.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);
            if (stone == 'x'){
                if(style == 1){
                    g.drawImage(i1, 5, 5, this);
                }
                else if (style == 2){
                    g.drawImage(i2, 5, 5, this);
                }
            }
            else if (stone == 'o'){
                if(style == 3){
                    g.drawImage(i3, 5, 5, this);
                }
                else if(style == 4){
                    g.drawImage(i4, 5, 5, this);
                }
            }
            
        }
    }
    
    //GET COMPONENTS
    public int getSizeGB(){
        return sizeGB;
    }
    public void setSizeGB(int size){
        this.sizeGB = size;
    }
    
    //START PANEL
    
    public JButton getAiButton(){
        return aiButton;
    }
    public JButton getPvpButton(){
        return pvpButton;
    }
    public JPanel getStartPanel(){
        return startPanel;
    }
    public JButton getExitInStartPanelButton(){
        return exitInStartPanelButton;
    }
    
    //AI PANEL
    public JPanel getAiOptionPanel(){
        return aiOptionPanel;
    }
    public JRadioButton getEasyChoice(){
        return easyChoice;
    }
    public JRadioButton getMediumChoice(){
        return mediumChoice;
    }
    public JRadioButton getHardChoice(){
        return hardChoice;
    }
    public JButton getIcon1ForAiButton(){
        return icon1ForAiButton;
    }
    public JButton getIcon2ForAiButton(){
        return icon2ForAiButton;
    }
    
    //PVP PANEL
    public JPanel getPvpOptionPanel(){
        return pvpOptionPanel;
    }
    public JButton getPlayer1IconForPvP_1(){
        return player1IconForPvP_1;
    }
    public JButton getPlayer1IconForPvP_2(){
        return player1IconForPvP_2;
    }
    public JButton getPlayer2IconForPvP_1(){
        return player2IconForPvP_1;
    }
    public JButton getPlayer2IconForPvP_2(){
        return player2IconForPvP_2;
    }
    public JButton getStartPvPButton(){
        return startPvPButton;
    }
    
    //MAINFRAME
    public JPanel getMainframePanel(){
        return mainFramPanel;
    }
    public JButton getNewGameButton(){
        return newGameButton;
    }
    public JTextField getStatusTextField(){
        return statusTextField;
    }
    public JButton getExitInMainFrameButton(){
        return exitInMainFramButton;
    }
    
    //Server-Client
    public JPanel getServerClientPanel(){
        return serverClientPanel;
    }
    public JPanel getServerChooserPanel(){
        return serverChooserPanel;
    }
    public JPanel getClientChooserPanel(){
        return clientChooserPaner;
    }
    public JButton getServerIcon1Button(){
        return serverIcon1;
    }
    public JButton getServerIcon2Button(){
        return serverIcon2;
    }
    public JButton getClientIcon1Button(){
        return clientIcon1;
    }
    public JButton getClientIcon2Button(){
        return clientIcon2;
    }
    public JButton getStartServerClientButton(){
        return startServerClient;
    }

    
    
    //ADD ACTIONLISTENER TO COMPONENTS
    public void addStartPanelActionListener(ActionListener startListener){
        aiButton.addActionListener(startListener);
        pvpButton.addActionListener(startListener);
        exitInStartPanelButton.addActionListener(startListener);
        
        easyChoice.addActionListener(startListener);
        mediumChoice.addActionListener(startListener);
        hardChoice.addActionListener(startListener);
        icon1ForAiButton.addActionListener(startListener);
        icon2ForAiButton.addActionListener(startListener);
        
        player1IconForPvP_1.addActionListener(startListener);
        player1IconForPvP_2.addActionListener(startListener);
        player2IconForPvP_1.addActionListener(startListener);
        player2IconForPvP_2.addActionListener(startListener);
        startPvPButton.addActionListener(startListener);
        
        newGameButton.addActionListener(startListener);
        exitInMainFramButton.addActionListener(startListener);
        statusTextField.addActionListener(startListener);
        
        serverIcon1.addActionListener(startListener);
        serverIcon2.addActionListener(startListener);
        clientIcon1.addActionListener(startListener);
        clientIcon2.addActionListener(startListener);
        startServerClient.addActionListener(startListener);
    }
//    public void addAIOptionPanelListener(ActionListener aiListener){
//        easyChoice.addActionListener(aiListener);
//        mediumChoice.addActionListener(aiListener);
//        hardChoice.addActionListener(aiListener);
//        icon1ForAiButton.addActionListener(aiListener);
//        icon2ForAiButton.addActionListener(aiListener);
//    }
//    public void addPvpOptionPanelListener(ActionListener pvpListener){
//        player1IconForPvP_1.addActionListener(pvpListener);
//        player1IconForPvP_2.addActionListener(pvpListener);
//        player2IconForPvP_1.addActionListener(pvpListener);
//        player2IconForPvP_2.addActionListener(pvpListener);
//    }
//    public void addMainframeListener(ActionListener mainframeListener){
//        newGameButton.addActionListener(mainframeListener);
//        statusTextField.addActionListener(mainframeListener);
//        
//    }
    public void addCellListener(MouseListener cellListener){
        for (int i = 0; i < sizeGB; i++){
            for (int j = 0; j < sizeGB; j++){
                cells[i][j].addMouseListener(cellListener);
            }
    }
}
    public void removeCellListener(MouseListener cellListener){
        for (int i = 0; i < sizeGB; i++){
            for (int j = 0; j < sizeGB; j++){
                cells[i][j].removeMouseListener(cellListener);
            }
        }
    }
    
    
}