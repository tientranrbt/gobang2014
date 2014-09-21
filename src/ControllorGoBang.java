
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class ControllorGoBang {
    ViewGoBang viewgb;
    ModelGoBang modelgb;
    public ControllorGoBang(ViewGoBang viewgb, ModelGoBang modelgb){
        this.viewgb = viewgb;
        this.modelgb = modelgb;
        viewgb.addStartPanelActionListener(new StartPanelController());
        viewgb.addCellListener(new CellControllerForCells());
        
    }

   
    
    
    
    public class StartPanelController implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            //ACTIONLISTENER FOR START PANEL
            if (e.getSource() == viewgb.getAiButton()){
                //CONTROLL THE GUI
                viewgb.remove(viewgb.getStartPanel());
                viewgb.add(viewgb.getAiOptionPanel());
                viewgb.getAiOptionPanel().setVisible(true);
                viewgb.setSize(new Dimension(300,300)); 
                //
                modelgb.setModeModel(1);
                modelgb.setTurnOf('x');
                viewgb.getStatusTextField().setText("Turn of player");
            }
            else if (e.getSource() == viewgb.getPvpButton()){
                //CONTROLL THE GUI
                viewgb.remove(viewgb.getStartPanel());
                viewgb.add(viewgb.getPvpOptionPanel());
                viewgb.getPvpOptionPanel().setVisible(true);
                viewgb.setSize(new Dimension(300,200));
                
                //
                modelgb.setModeModel(2);
                viewgb.getStatusTextField().setText(modelgb.getP1Turn());
                modelgb.setTurnOf('x');
            }
            else if (e.getSource() == viewgb.getExitInStartPanelButton()){
                System.exit(0);
            }
            //ACTIONLISTENER FOR AI PANEL
            if (e.getSource() == viewgb.getIcon1ForAiButton() && 
                                (viewgb.getEasyChoice().isSelected() || 
                                 viewgb.getMediumChoice().isSelected() || 
                                 viewgb.getHardChoice().isSelected())){
                //CONTROLL THE GUI
                viewgb.remove(viewgb.getAiOptionPanel());
                viewgb.add(viewgb.getMainframePanel());
                viewgb.getMainframePanel().setVisible(true);
                viewgb.setSize(new Dimension(500,600));
                
                
                
                //
                modelgb.setPlayerAIStyle(1);
                if (viewgb.getEasyChoice().isSelected()){
                modelgb.setLevelModel(1);
            }
            else if (viewgb.getMediumChoice().isSelected()){
                modelgb.setLevelModel(2);
            }
            else{
                modelgb.setLevelModel(3);
            }
                
                //
            }
            else if (e.getSource() == viewgb.getIcon2ForAiButton() && 
                                (viewgb.getEasyChoice().isSelected() || 
                                 viewgb.getMediumChoice().isSelected() || 
                                 viewgb.getHardChoice().isSelected())){
                //CONTROLL THE GUI
                viewgb.remove(viewgb.getAiOptionPanel());
                viewgb.add(viewgb.getMainframePanel());
                viewgb.getMainframePanel().setVisible(true);
                viewgb.setSize(new Dimension(500,600));
                

                //
                modelgb.setPlayerAIStyle(2);
            if (viewgb.getEasyChoice().isSelected()){
                modelgb.setLevelModel(1);
            }
            else if (viewgb.getMediumChoice().isSelected()){
                modelgb.setLevelModel(2);
            }
            else if (viewgb.getHardChoice().isSelected()){
                modelgb.setLevelModel(3);
            }

        }
            
            //ACTIONLISTENER FOR PVP PANEL
            if (e.getSource() == viewgb.getPlayer1IconForPvP_1()){
                modelgb.setPlayer1Style(1);
            }
            else if (e.getSource() == viewgb.getPlayer1IconForPvP_2()){
                modelgb.setPlayer1Style(2);
            }
            if (e.getSource() == viewgb.getPlayer2IconForPvP_1()){
                modelgb.setPlayer2Style(3);
            }
            else if (e.getSource() == viewgb.getPlayer2IconForPvP_2()){
                modelgb.setPlayer2Style(4);
            }
            if (e.getSource() == viewgb.getStartPvPButton() && modelgb.getPlayer1style() != 0 && modelgb.getPlayer2Style()!= 0){
                //CONTROLL THE GUI
                viewgb.remove(viewgb.getPvpOptionPanel());
                viewgb.add(viewgb.getMainframePanel());
                viewgb.getMainframePanel().setVisible(true);
                viewgb.setSize(new Dimension(500,600));
            }
        
            //ACTIONLISTENER FOR MAIMFRAME
            if (e.getSource() == viewgb.getNewGameButton()){
                //CONTROLL GUI
                viewgb.remove(viewgb.getMainframePanel());
                viewgb.add(viewgb.getStartPanel());
                viewgb.getStartPanel().setVisible(true);
                viewgb.setSize(new Dimension(300,300));
                //
                modelgb.setModeModel(0);
                modelgb.setTurnOf(' ');
                modelgb.setP1Count(0);
                modelgb.setP2Count(0);
                modelgb.setPlayer1Style(0);
                modelgb.setPlayer2Style(0);
                modelgb.setPlayerAIStyle(0);
                for (int i = 0; i < viewgb.getSizeGB(); i++){
                    for (int j = 0; j < viewgb.getSizeGB(); j++){
                        viewgb.cells[i][j].setStone('_');
                        viewgb.cells[i][j].setStyle(0);
                    }
                }
                modelgb.setLevelModel(0);
                modelgb.setModeModel(0);
                
                
                
            }
            else if (e.getSource() == viewgb.getExitInMainFrameButton()){
                System.exit(0);
            }
    }
    } 
   
    //MOUSELISTENER FOR CELL
    public class CellControllerForCells implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent me) {
            //For AI
            if (modelgb.getModeModel() == 1){
                for (int i = 0; i < viewgb.getSizeGB(); i++){
                for (int j = 0; j < viewgb.getSizeGB(); j++){
                    if (me.getSource() == viewgb.cells[i][j]){
                        if (viewgb.cells[i][j].getStone() == '_' && modelgb.getTurnOf() == 'x'){
                            viewgb.cells[i][j].setStone('x');
                            viewgb.cells[i][j].setStyle(modelgb.getPlayerAIStyle());
                            if (modelgb.winGame('x')){
                                viewgb.getStatusTextField().setText("You won!");
                                modelgb.setTurnOf(' ');
                            }
                            else if (modelgb.fullBoard()){
                                viewgb.getStatusTextField().setText("Full board!");
                            }
                            else {
                                modelgb.setTurnOf('o');
                            }
                        }
                        
                    }
                }
            }
            if (modelgb.getTurnOf() == 'o'){
                viewgb.getStatusTextField().setText("Turn of computer");
                
                for (int i = 0; i < viewgb.getSizeGB(); i++){
                    for (int j = 0; j < viewgb.getSizeGB(); j++){
                        if (viewgb.cells[i][j].getStone() == '_'){
                            viewgb.cells[i][j].setStyle(4);
                        }
                    }
                }
                
                
                if (modelgb.getLevelModel() == 1){
                    if (modelgb.attack4Stones() == 0){
                        if (modelgb.defense4Stones() == 0){
                            if (modelgb.attack3Stones() == 0){
                                if (modelgb.defense3Stones() == 0){
                                    if (modelgb.attack2Stones() == 0){
                                        if (modelgb.attack1Stone() == 0){
                                            modelgb.attack0Stone();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else if (modelgb.getLevelModel() == 2){
                    if (modelgb.attack4Stones() == 0){
                        if (modelgb.defense4Stones() == 0){
                            if (modelgb.attack3Stones() == 0){
                                if (modelgb.defense2ways() == 0){
                                    if (modelgb.defense3Stones() == 0){
                                        if (modelgb.attack2Stones() == 0){
                                            if (modelgb.attack1Stone() == 0){
                                                modelgb.attack0Stone();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                else if (modelgb.getLevelModel() == 3){
                    if (modelgb.attack4Stones() == 0){
                        if (modelgb.defense4Stones() == 0){
                            if (modelgb.attack2ways() == 0){
                                if (modelgb.attack3Stones() == 0){
                                    if (modelgb.defense2ways() == 0){
                                        if (modelgb.defense3Stones() == 0){
                                            if (modelgb.attack2Stones() == 0){
                                                if (modelgb.attack1Stone() == 0){
                                                    modelgb.attack0Stone();
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }


                if (modelgb.winGame('o')){
                    viewgb.getStatusTextField().setText("Gameover!");
                    modelgb.setTurnOf(' ');
                }
                else if (modelgb.fullBoard()){
                    viewgb.getStatusTextField().setText("Full board!");
                }
                else {
                    modelgb.setTurnOf('x');
                    viewgb.getStatusTextField().setText("Turn of player");
                }
                
                
            }
            }
            //For PvP
            else if (modelgb.getModeModel() == 2){
                for (int i = 0; i < viewgb.getSizeGB(); i++){
                for (int j = 0; j < viewgb.getSizeGB(); j++){
                    if (me.getSource() == viewgb.cells[i][j]){
                        if(viewgb.cells[i][j].getStone() == '_' && modelgb.getTurnOf() != ' '){
                            viewgb.cells[i][j].setStone(modelgb.getTurnOf());
                            if (viewgb.cells[i][j].getStone() == 'x') {
                                
                                    if (modelgb.getPlayer1style() == 1){
                                        viewgb.cells[i][j].setStyle(1);
                                    }
                                    else {
                                        viewgb.cells[i][j].setStyle(2);
                                    }
                                }
                                else {
                                    if (modelgb.getPlayer2Style() == 3){
                                        viewgb.cells[i][j].setStyle(3);
                                    }
                                    else {
                                        viewgb.cells[i][j].setStyle(4);
                                    }
                            }
                            if (modelgb.winGame(modelgb.getTurnOf())){
                                if (modelgb.getTurnOf() == 'x'){
                                    viewgb.getStatusTextField().setText(modelgb.getP1Win());
                                }
                                else {
                                    viewgb.getStatusTextField().setText(modelgb.getP2Win());
                                }
                                modelgb.setTurnOf(' ');
                            }
                            else if (modelgb.fullBoard()){
                                viewgb.getStatusTextField().setText("Full board!");
                            }
                            else{
                                if (viewgb.cells[i][j].getStone() == 'x') {
                                    modelgb.setTurnOf('o');
                                    viewgb.getStatusTextField().setText(modelgb.getP2Turn());
                                }
                                else {
                                    modelgb.setTurnOf('x');
                                    viewgb.getStatusTextField().setText(modelgb.getP1Turn());
                                }   
            }
        }
                    }
                }
            }
            }
        }

        @Override
        public void mousePressed(MouseEvent me) {
        }

        @Override
        public void mouseReleased(MouseEvent me) {
        }

        @Override
        public void mouseEntered(MouseEvent me) {
        }

        @Override
        public void mouseExited(MouseEvent me) {
        }
        
    }
    
    
}
    
    

    
