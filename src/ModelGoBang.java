
public class ModelGoBang {
    ViewGoBang viewgb;
    private int size;
    public ModelGoBang(ViewGoBang viewgb){
        this.viewgb = viewgb;
        
    
}
    
    //GENERAL
    //private char [][] stones = new char[viewgb.getSizeGB()][viewgb.getSizeGB()];
    public void resetStones(){
        for (int i = 0; i < viewgb.getSizeGB(); i++){
            for (int j = 0; j < viewgb.getSizeGB(); j++){
                viewgb.cells[i][j].setStone('_');
            }
        }
    }
    
    private int modeModel = 0; //1 for AI, 2 for PvP
    public int getModeModel(){
        return modeModel;
    }
    public void setModeModel(int mode){
        modeModel = mode;
    }
    
    private int levelModel = 0; //1 for easy, 2 for medium, 3 for hard
    public int getLevelModel(){
        return levelModel;
    }
    public void setLevelModel(int level){
        levelModel = level;
    }
    
    private char turnOf = ' ';
    public char getTurnOf(){
        return turnOf;
    }
    public void setTurnOf(char turnOf){
        this.turnOf = turnOf;
    }
    
    
    //COMPUTER - AI
    private char computerStone = 'o';
    public char getComputerStone(){
        return computerStone;
    }
    
    
    
    
    //PLAYER - AI
    private char playerAIStone = 'x';
    public char getPlayerAIStone(){
        return playerAIStone;
    }
    private int playAIStyle = 0;
    public int getPlayerAIStyle(){
        return playAIStyle;
    }
    public void setPlayerAIStyle(int style){
        playAIStyle = style;
    }
    
    //PLAYER 1
    private char player1Stone = 'x';
    public char getPlayer1Stone(){
        return getPlayer1Stone();
    }
    private int player1style = 0;
    public int getPlayer1style(){
        return player1style;
    }
    public void setPlayer1Style(int style){
        this.player1style = style;
    }
    private String p1Turn = new String ("Player 1's turn");
    public String getP1Turn(){
        return p1Turn;
    }
    private String p1Win = new String("Player 1 won!");
    public String getP1Win(){
        return p1Win;
    }
    private int p1Count = 0;
    public int getP1Count(){
        return p1Count;
    }
    public void setP1Count(int count){
        p1Count = count;
    }
    public void increaseP1Count(){
        p1Count++;
    }
    
    //PLAYER 2
    private char player2Stone = 'o';
    public char getPlayer2Stone(){
        return player2Stone;
    }
    private int player2Style = 0;
    public int getPlayer2Style(){
        return player2Style;
    }
    public void setPlayer2Style(int style){
        this.player2Style = style;
    }
     private String p2Turn = new String ("Player 2's turn");
    public String getP2Turn(){
        return p2Turn;
    }
    private String p2Win = new String("Player 2 won!");
    public String getP2Win(){
        return p2Win;
    }
    private int p2Count = 0;
    public int getP2Count(){
        return p2Count;
    }
    public void setP2Count(int count){
        p2Count = count;
    }
    public void increaseP2Count(){
        p2Count++;
    }
    
    //PvP methods
    public boolean fullBoard(){
        for(int i = 0; i < viewgb.getSizeGB(); i++){
            for (int j = 0; j < viewgb.getSizeGB(); j++){
                if (viewgb.cells[i][j].getStone() == '_'){
                    return false;
                }
            }
        }
        return true;
    }
    public boolean winGame(char who){
        for(int i = 0; i < viewgb.getSizeGB(); i++){
            for (int j = 0; j < viewgb.getSizeGB(); j++){
                //HORIZON
                if(j + 4 < viewgb.getSizeGB()){
                    if (viewgb.cells[i][j].getStone() == who &&
                        viewgb.cells[i][j+1].getStone() == who &&
                        viewgb.cells[i][j+2].getStone() == who &&
                        viewgb.cells[i][j+3].getStone() == who &&
                        viewgb.cells[i][j+4].getStone() == who){
                        return true;
                    }
                }
                //VERTICAL
                if(i + 4 < viewgb.getSizeGB()){
                    if (viewgb.cells[i][j].getStone() == who &&
                        viewgb.cells[i+1][j].getStone() == who &&
                        viewgb.cells[i+2][j].getStone() == who &&
                        viewgb.cells[i+3][j].getStone() == who &&
                        viewgb.cells[i+4][j].getStone() == who){
                        return true;
                    }
                }
                //DIAGONAL TOP LEFT - BOTTOM RIGHT
                if(i + 4 < viewgb.getSizeGB() && j + 4 < viewgb.getSizeGB()){
                    if (viewgb.cells[i][j].getStone() == who &&
                        viewgb.cells[i+1][j+1].getStone() == who &&
                        viewgb.cells[i+2][j+2].getStone() == who &&
                        viewgb.cells[i+3][j+3].getStone() == who &&
                        viewgb.cells[i+4][j+4].getStone() == who){
                        return true;
                    }
                }
                //DIAGONAL TOP RIGHT - BOTTOM LEFT
                if(i + 4 < viewgb.getSizeGB() && j - 4 >= 0){
                    if (viewgb.cells[i][j].getStone() == who &&
                        viewgb.cells[i+1][j-1].getStone() == who &&
                        viewgb.cells[i+2][j-2].getStone() == who &&
                        viewgb.cells[i+3][j-3].getStone() == who &&
                        viewgb.cells[i+4][j-4].getStone() == who){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    //AI methods

    
    //ATTACK
    public int attack4Stones(){
        int attack = 0;
        String horizon, vertical, diatr_bl, diatl_br;
        for (int i = 0; i < viewgb.getSizeGB(); i++){
            for (int j = 0; j < viewgb.getSizeGB(); j++){
                
                //Horizon
                if (attack == 0 && j + 4 < viewgb.getSizeGB() ){
                    horizon = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i][j+1].getStone())
                            + Character.toString(viewgb.cells[i][j+2].getStone())
                            + Character.toString(viewgb.cells[i][j+3].getStone())
                            + Character.toString(viewgb.cells[i][j+4].getStone());
                    switch (horizon){
                        case "_oooo":
                            viewgb.cells[i][j].setStone('o');
                            attack = 1;
                            break;
                        case "oooo_":
                            viewgb.cells[i][j+4].setStone('o');
                            attack = 1;
                            break;
                        case "o_ooo":
                            viewgb.cells[i][j+1].setStone('o');
                            attack = 1;
                            break;
                        case "ooo_o":
                            viewgb.cells[i][j+3].setStone('o');
                            attack = 1;
                            break;
                        case "oo_oo":
                            viewgb.cells[i][j+2].setStone('o');
                            attack = 1;
                            break;
                        
                    }
                    
                }
                
                //Vertical
                if (attack == 0 && i + 4 < viewgb.getSizeGB() ){
                    vertical = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j].getStone())
                            + Character.toString(viewgb.cells[i+2][j].getStone())
                            + Character.toString(viewgb.cells[i+3][j].getStone())
                            + Character.toString(viewgb.cells[i+4][j].getStone());
                    switch (vertical){
                        case "_oooo":
                            viewgb.cells[i][j].setStone('o');
                            attack = 1;
                            break;
                        case "oooo_":
                            viewgb.cells[i+4][j].setStone('o');
                            attack = 1;
                            break;
                        case "o_ooo":
                            viewgb.cells[i+1][j].setStone('o');
                            attack = 1;
                            break;
                        case "ooo_o":
                            viewgb.cells[i+3][j].setStone('o');
                            attack = 1;
                            break;
                        case "oo_oo":
                            viewgb.cells[i+2][j].setStone('o');
                            attack = 1;
                            break;
                        
                    }
                    
                }
                //step 2 diagonal top left - bottom right
                if (attack == 0 && j + 4 < viewgb.getSizeGB() && i + 4 < viewgb.getSizeGB() ){
                    diatl_br = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j+1].getStone())
                            + Character.toString(viewgb.cells[i+2][j+2].getStone())
                            + Character.toString(viewgb.cells[i+3][j+3].getStone())
                            + Character.toString(viewgb.cells[i+4][j+4].getStone());
                    switch (diatl_br){
                        case "_oooo":
                            viewgb.cells[i][j].setStone('o');
                            attack = 1;
                            break;
                        case "oooo_":
                            viewgb.cells[i+4][j+4].setStone('o');
                            attack = 1;
                            break;
                        case "o_ooo":
                            viewgb.cells[i+1][j+1].setStone('o');
                            attack = 1;
                            break;
                        case "ooo_o":
                            viewgb.cells[i+3][j+3].setStone('o');
                            attack = 1;
                            break;
                        case "oo_oo":
                            viewgb.cells[i+2][j+2].setStone('o');
                            attack = 1;
                            break;
                        
                    }
                    
                }
                //step 2 diagonal top right - bottom left
                if (attack == 0 && i + 4 < viewgb.getSizeGB() && j - 4 >= 0 ){
                    diatr_bl = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j-1].getStone())
                            + Character.toString(viewgb.cells[i+2][j-2].getStone())
                            + Character.toString(viewgb.cells[i+3][j-3].getStone())
                            + Character.toString(viewgb.cells[i+4][j-4].getStone());
                    switch (diatr_bl){
                        case "_oooo":
                            viewgb.cells[i][j].setStone('o');
                            attack = 1;
                            break;
                        case "oooo_":
                            viewgb.cells[i+4][j-4].setStone('o');
                            attack = 1;
                            break;
                        case "o_ooo":
                            viewgb.cells[i+1][j-1].setStone('o');
                            attack = 1;
                            break;
                        case "ooo_o":
                            viewgb.cells[i+3][j-3].setStone('o');
                            attack = 1;
                            break;
                        case "oo_oo":
                            viewgb.cells[i+2][j-2].setStone('o');
                            attack = 1;
                            break;
                    }
                }
            }
        }
        return attack;
    }
    public int attack3Stones(){
        int attack = 0;
        String horizon, vertical, diatr_bl, diatl_br;
        for (int i = 0; i < viewgb.getSizeGB(); i++){
            for (int j = 0; j < viewgb.getSizeGB(); j++){
                //step 3 horizone
                if (attack == 0 && j + 4 < viewgb.getSizeGB() ){
                    horizon = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i][j+1].getStone())
                            + Character.toString(viewgb.cells[i][j+2].getStone())
                            + Character.toString(viewgb.cells[i][j+3].getStone())
                            + Character.toString(viewgb.cells[i][j+4].getStone());
                    switch (horizon){
                        case "_ooo_":
                            viewgb.cells[i][j].setStone('o');
                            attack = 1;
                            break;
                        case "o_oo_":
                            viewgb.cells[i][j+1].setStone('o');
                            attack = 1;
                            break;
                        case "_o_oo":
                            viewgb.cells[i][j+2].setStone('o');
                            attack = 1;
                            break;
                        case "oo_o_":
                            viewgb.cells[i][j+2].setStone('o');
                            attack = 1;
                            break;
                        case "_oo_o":
                            viewgb.cells[i][j+3].setStone('o');
                            attack = 1;
                            break;
                        
                    }
                    
                }
                //step 3 vertical
                if(attack == 0 && i + 4 < viewgb.getSizeGB() ){
                    vertical = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j].getStone())
                            + Character.toString(viewgb.cells[i+2][j].getStone())
                            + Character.toString(viewgb.cells[i+3][j].getStone())
                            + Character.toString(viewgb.cells[i+4][j].getStone());
                    switch (vertical){
                        case "_ooo_":
                            viewgb.cells[i][j].setStone('o');
                            attack = 1;
                            break;
                        case "o_oo_":
                            viewgb.cells[i+1][j].setStone('o');
                            attack = 1;
                            break;
                        case "_o_oo":
                            viewgb.cells[i+2][j].setStone('o');
                            attack = 1;
                            break;
                        case "oo_o_":
                            viewgb.cells[i+2][j].setStone('o');
                            attack = 1;
                            break;
                        case "_oo_o":
                            viewgb.cells[i+3][j].setStone('o');
                            attack = 1;
                            break;
                        
                    }
                }
                //step 3 diagonal top left - bottom right
                if(attack == 0 && i + 4 < viewgb.getSizeGB() && j + 4 < viewgb.getSizeGB()){
                    diatl_br = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j+1].getStone())
                            + Character.toString(viewgb.cells[i+2][j+2].getStone())
                            + Character.toString(viewgb.cells[i+3][j+3].getStone())
                            + Character.toString(viewgb.cells[i+4][j+4].getStone());
                    switch (diatl_br){
                        case "_ooo_":
                            viewgb.cells[i][j].setStone('o');
                            attack = 1;
                            break;
                        case "o_oo_":
                            viewgb.cells[i+1][j+1].setStone('o');
                            attack = 1;
                            break;
                        case "_o_oo":
                            viewgb.cells[i+2][j+2].setStone('o');
                            attack = 1;
                            break;
                        case "oo_o_":
                            viewgb.cells[i+2][j+2].setStone('o');
                            attack = 1;
                            break;
                        case "_oo_o":
                            viewgb.cells[i+3][j+3].setStone('o');
                            attack = 1;
                            break;
                        
                    }
                }
                //step 3 diagonal top right - bottom left
                if(attack == 0 && i + 4 < viewgb.getSizeGB() && j - 4 >=0 ){
                    diatr_bl = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j-1].getStone())
                            + Character.toString(viewgb.cells[i+2][j-2].getStone())
                            + Character.toString(viewgb.cells[i+3][j-3].getStone())
                            + Character.toString(viewgb.cells[i+4][j-4].getStone());
                    switch (diatr_bl){
                        case "_ooo_":
                            viewgb.cells[i][j].setStone('o');
                            attack = 1;
                            break;
                        case "o_oo_":
                            viewgb.cells[i+1][j-1].setStone('o');
                            attack = 1;
                            break;
                        case "_o_oo":
                            viewgb.cells[i+2][j-2].setStone('o');
                            attack = 1;
                            break;
                        case "oo_o_":
                            viewgb.cells[i+2][j-2].setStone('o');
                            attack = 1;
                            break;
                        case "_oo_o":
                            viewgb.cells[i+3][j-3].setStone('o');
                            attack = 1;
                            break;                      
                    }
                }
            }
        }
        return attack;
    }
    public int attack2Stones(){
        int attack = 0;
        String horizon, vertical, diatr_bl, diatl_br;
        for (int i = 0; i < viewgb.getSizeGB(); i++){
            for (int j = 0; j < viewgb.getSizeGB(); j++){
                //step 4 horizone
                if (attack == 0 && j + 4 < viewgb.getSizeGB() ){
                    horizon = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i][j+1].getStone())
                            + Character.toString(viewgb.cells[i][j+2].getStone())
                            + Character.toString(viewgb.cells[i][j+3].getStone())
                            + Character.toString(viewgb.cells[i][j+4].getStone());
                    if(horizon.equals("_o_o_")){
                        viewgb.cells[i][j+2].setStone('o');
                        attack = 1;
                        break;
                    }
                }
                
                //step 4 vertical
                if (attack == 0 && i + 4 < viewgb.getSizeGB() ){
                    vertical = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j].getStone())
                            + Character.toString(viewgb.cells[i+2][j].getStone())
                            + Character.toString(viewgb.cells[i+3][j].getStone())
                            + Character.toString(viewgb.cells[i+4][j].getStone());
                    if(vertical.equals("_o_o_")){
                        viewgb.cells[i+2][j].setStone('o');
                        attack = 1;
                        break;
                    }
                    
                }
                //step 4 diagonal top left - bottom right
                if (attack == 0 && j + 4 < viewgb.getSizeGB() && i + 4 < viewgb.getSizeGB() ){
                    diatl_br = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j+1].getStone())
                            + Character.toString(viewgb.cells[i+2][j+2].getStone())
                            + Character.toString(viewgb.cells[i+3][j+3].getStone())
                            + Character.toString(viewgb.cells[i+4][j+4].getStone());
                    if(diatl_br.equals("_o_o_")){
                        viewgb.cells[i+2][j+2].setStone('o');
                        attack = 1;
                    }
                    
                    
                }
                //step 4 diagonal top right - bottom left
                if (attack == 0 && i + 4 < viewgb.getSizeGB() && j - 4 >= 0 ){
                    diatr_bl = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j-1].getStone())
                            + Character.toString(viewgb.cells[i+2][j-2].getStone())
                            + Character.toString(viewgb.cells[i+3][j-3].getStone())
                            + Character.toString(viewgb.cells[i+4][j-4].getStone());
                    if(diatr_bl.equals("_o_o_")){
                        viewgb.cells[i+2][j-2].setStone('o');
                        attack = 1;
                    }
                    
                }
                //step 5 horizone
                if (attack == 0 && j + 3 < viewgb.getSizeGB() ){
                    horizon = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i][j+1].getStone())
                            + Character.toString(viewgb.cells[i][j+2].getStone())
                            + Character.toString(viewgb.cells[i][j+3].getStone());
                    if(horizon.equals("_oo_")){
                        viewgb.cells[i][j].setStone('o');
                        attack = 1;
                        break;
                    }
                }
                
                //step 5 vertical
                if (attack == 0 && i + 3 < viewgb.getSizeGB() ){
                    vertical = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j].getStone())
                            + Character.toString(viewgb.cells[i+2][j].getStone())
                            + Character.toString(viewgb.cells[i+3][j].getStone());
                    if(vertical.equals("_oo_")){
                        viewgb.cells[i][j].setStone('o');
                        attack = 1;
                        break;
                    }
                    
                }
                //step 5 diagonal top left - bottom right
                if (attack == 0 && j + 3 < viewgb.getSizeGB() && i + 3 < viewgb.getSizeGB() ){
                    diatl_br = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j+1].getStone())
                            + Character.toString(viewgb.cells[i+2][j+2].getStone())
                            + Character.toString(viewgb.cells[i+3][j+3].getStone());
                    if(diatl_br.equals("_oo_")){
                        viewgb.cells[i][j].setStone('o');
                        attack = 1;
                    }
                    
                    
                }
                //step 5 diagonal top right - bottom left
                if (attack == 0 && i + 3 < viewgb.getSizeGB() && j - 3 >= 0 ){
                    diatr_bl = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j-1].getStone())
                            + Character.toString(viewgb.cells[i+2][j-2].getStone())
                            + Character.toString(viewgb.cells[i+3][j-3].getStone());
                    if(diatr_bl.equals("_oo_")){
                        viewgb.cells[i][j].setStone('o');
                        attack = 1;
                    }  
                }
            }
        }
        return attack;
    }
    public int attack1Stone(){
        String horizon, vertical, diatr_bl, diatl_br;
        int attack = 0;
        for (int i = 0; i < viewgb.getSizeGB(); i++){
            for (int j = 0; j < viewgb.getSizeGB(); j++){
                //step 6 horizone
                if (attack == 0 && j + 2 < viewgb.getSizeGB() ){
                    horizon = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i][j+1].getStone())
                            + Character.toString(viewgb.cells[i][j+2].getStone());
                    if(horizon.equals("_o_")){
                        viewgb.cells[i][j].setStone('o');
                        attack = 1;
                        break;
                    }
                }
                
                //step 6 vertical
                if (attack == 0 && i + 2 < viewgb.getSizeGB() ){
                    vertical = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j].getStone())
                            + Character.toString(viewgb.cells[i+2][j].getStone());
                    if(vertical.equals("_o_")){
                        viewgb.cells[i][j].setStone('o');
                        attack = 1;
                        break;
                    }
                    
                }
                //step 6 diagonal top left - bottom right
                if (attack == 0 && j + 2 < viewgb.getSizeGB() && i + 2 < viewgb.getSizeGB() ){
                    diatl_br = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j+1].getStone())
                            + Character.toString(viewgb.cells[i+2][j+2].getStone());
                    if(diatl_br.equals("_o_")){
                        viewgb.cells[i][j].setStone('o');
                        attack = 1;
                    }
                    
                    
                }
                //step 6 diagonal top right - bottom left
                if (attack == 0 && i + 2 < viewgb.getSizeGB() && j - 2 >= 0 ){
                    diatr_bl = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j-1].getStone())
                            + Character.toString(viewgb.cells[i+2][j-2].getStone());
                    if(diatr_bl.equals("_o_")){
                        viewgb.cells[i][j].setStone('o');
                        attack = 1;
                    }  
                }
                //
                if (attack == 0 && i + 1 < viewgb.getSizeGB() && j - 1 >= 0 ){
                    horizon = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j-1].getStone());
                    if(horizon.equals("_o_")){
                        viewgb.cells[i][j].setStone('o');
                        attack = 1;
                        break;
                    }
                }
                if (attack == 0 && i + 1 < viewgb.getSizeGB() && j + 1 < viewgb.getSizeGB() ){
                    horizon = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i][j+1].getStone())
                            + Character.toString(viewgb.cells[i+1][j+1].getStone());
                    if(horizon.equals("_o_")){
                        viewgb.cells[i][j].setStone('o');
                        attack = 1;
                        break;
                    }
                }
                if (attack == 0 && i + 1 < viewgb.getSizeGB() && j - 1 >= 0 ){
                    horizon = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i][j-1].getStone())
                            + Character.toString(viewgb.cells[i+1][j-1].getStone());
                    if(horizon.equals("_o_")){
                        viewgb.cells[i][j].setStone('o');
                        attack = 1;
                        break;
                    }
                }
                if (attack == 0 && i + 1 < viewgb.getSizeGB() && j +1 < viewgb.getSizeGB() ){
                    horizon = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j+1].getStone());
                    if(horizon.equals("_o_")){
                        viewgb.cells[i][j].setStone('o');
                        attack = 1;
                        break;
                    }
                }
            }
        }
        return attack;
    }
    public int attack0Stone(){
        int attack = 0;
        String horizon, vertical, diatr_bl, diatl_br;
        for (int i = 0; i < viewgb.getSizeGB(); i++){
            for (int j = 0; j < viewgb.getSizeGB(); j++){
                //step 6 horizone
                if (attack == 0 && j + 2 < viewgb.getSizeGB() ){
                    horizon = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i][j+1].getStone())
                            + Character.toString(viewgb.cells[i][j+2].getStone());
                    if(horizon.equals("_x_")){
                        viewgb.cells[i][j].setStone('o');
                        attack = 1;
                        break;
                    }
                }
                
                //step 6 vertical
                if (attack == 0 && i + 2 < viewgb.getSizeGB() ){
                    vertical = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j].getStone())
                            + Character.toString(viewgb.cells[i+2][j].getStone());
                    if(vertical.equals("_x_")){
                        viewgb.cells[i][j].setStone('o');
                        attack = 1;
                        break;
                    }
                    
                }
                //step 6 diagonal top left - bottom right
                if (attack == 0 && j + 2 < viewgb.getSizeGB() && i + 2 < viewgb.getSizeGB() ){
                    diatl_br = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j+1].getStone())
                            + Character.toString(viewgb.cells[i+2][j+2].getStone());
                    if(diatl_br.equals("_x_")){
                        viewgb.cells[i][j].setStone('o');
                        attack = 1;
                    }
                    
                    
                }
                //step 6 diagonal top right - bottom left
                if (attack == 0 && i + 2 < viewgb.getSizeGB() && j - 2 >= 0 ){
                    diatr_bl = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j-1].getStone())
                            + Character.toString(viewgb.cells[i+2][j-2].getStone());
                    if(diatr_bl.equals("_x_")){
                        viewgb.cells[i][j].setStone('o');
                        attack = 1;
                    }  
                }
                //
                if (attack == 0 && i + 1 < viewgb.getSizeGB() && j - 1 >= 0 ){
                    horizon = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j-1].getStone());
                    if(horizon.equals("_x_")){
                        viewgb.cells[i][j].setStone('o');
                        attack = 1;
                        break;
                    }
                }
                if (attack == 0 && i + 1 < viewgb.getSizeGB() && j + 1 < viewgb.getSizeGB() ){
                    horizon = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i][j+1].getStone())
                            + Character.toString(viewgb.cells[i+1][j+1].getStone());
                    if(horizon.equals("_x_")){
                        viewgb.cells[i][j].setStone('o');
                        attack = 1;
                        break;
                    }
                }
                if (attack == 0 && i + 1 < viewgb.getSizeGB() && j - 1 >= 0 ){
                    horizon = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i][j-1].getStone())
                            + Character.toString(viewgb.cells[i+1][j-1].getStone());
                    if(horizon.equals("_x_")){
                        viewgb.cells[i][j].setStone('o');
                        attack = 1;
                        break;
                    }
                }
                if (attack == 0 && i + 1 < viewgb.getSizeGB() && j +1 < viewgb.getSizeGB() ){
                    horizon = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j+1].getStone());
                    if(horizon.equals("_x_")){
                        viewgb.cells[i][j].setStone('o');
                        attack = 1;
                        break;
                    }
                }
            }
        }
        return attack;
    }
//    public int specialAttack(){
//        int attack = 0;
//        for (int i = 0; i < viewgb.getSizeGB(); i++){
//            for (int j = 0; j < viewgb.getSizeGB(); j++){
//                
//            }
//        }
//        return attack;
//    }
    public int attack2ways(){
        int attack = 0;
        for (int i = 0; i < viewgb.getSizeGB(); i++){
            for (int j = 0; j < viewgb.getSizeGB(); j++){
                if ( attack == 0 && i + 3 < viewgb.getSizeGB() && j - 3 >=0){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j].getStone())
                            + Character.toString(viewgb.cells[i+2][j].getStone())
                            + Character.toString(viewgb.cells[i+3][j].getStone())
                            + Character.toString(viewgb.cells[i+3][j-1].getStone())
                            + Character.toString(viewgb.cells[i+3][j-2].getStone())
                            + Character.toString(viewgb.cells[i+3][j-3].getStone());
                    switch (ex){
                        case "_oo_oo_":
                            viewgb.cells[i+3][j].setStone('o');
                            attack = 1;
                            break;
                        case "ooo_ooo":
                            viewgb.cells[i+3][j].setStone('o');
                            attack = 1;
                            break;
                    }
                }
                //Type 2a
                if ( attack == 0 && i + 3 < viewgb.getSizeGB() && j + 3 < viewgb.getSizeGB()){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i][j+1].getStone())
                            + Character.toString(viewgb.cells[i][j+2].getStone())
                            + Character.toString(viewgb.cells[i][j+3].getStone())
                            + Character.toString(viewgb.cells[i+1][j+3].getStone())
                            + Character.toString(viewgb.cells[i+2][j+3].getStone())
                            + Character.toString(viewgb.cells[i+3][j+3].getStone());
                    switch (ex){
                        case "_oo_oo_":
                            viewgb.cells[i][j+3].setStone('o');
                            attack = 1;
                            break;
                        case "ooo_ooo":
                            viewgb.cells[i][j+3].setStone('o');
                            attack = 1;
                            break;
                    }
                }
                //Type 3a
                if ( attack == 0 && i + 3 < viewgb.getSizeGB() && j - 3 >=0){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i][j-1].getStone())
                            + Character.toString(viewgb.cells[i][j-2].getStone())
                            + Character.toString(viewgb.cells[i][j-3].getStone())
                            + Character.toString(viewgb.cells[i+1][j-3].getStone())
                            + Character.toString(viewgb.cells[i+2][j-3].getStone())
                            + Character.toString(viewgb.cells[i+3][j-3].getStone());
                    switch (ex){
                        case "_oo_oo_":
                            viewgb.cells[i][j-3].setStone('o');
                            attack = 1;
                            break;
                        case "ooo_ooo":
                            viewgb.cells[i][j-3].setStone('o');
                            attack = 1;
                            break;
                    }
                }
                //Type 4a
                if ( attack == 0 && i + 3 < viewgb.getSizeGB() && j + 3 < viewgb.getSizeGB()){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j].getStone())
                            + Character.toString(viewgb.cells[i+2][j].getStone())
                            + Character.toString(viewgb.cells[i+3][j].getStone())
                            + Character.toString(viewgb.cells[i+3][j+1].getStone())
                            + Character.toString(viewgb.cells[i+3][j+2].getStone())
                            + Character.toString(viewgb.cells[i+3][j+3].getStone());
                    switch (ex){
                        case "_oo_oo_":
                            viewgb.cells[i+3][j].setStone('o');
                            attack = 1;
                            break;
                        case "ooo_ooo":
                            viewgb.cells[i+3][j].setStone('o');
                            attack = 1;
                            break;
                    }
                }
                //Type 1a
                if ( attack == 0 && i + 6 < viewgb.getSizeGB() && j - 3 >=0){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j-1].getStone())
                            + Character.toString(viewgb.cells[i+2][j-2].getStone())
                            + Character.toString(viewgb.cells[i+3][j-3].getStone())
                            + Character.toString(viewgb.cells[i+4][j-2].getStone())
                            + Character.toString(viewgb.cells[i+5][j-1].getStone())
                            + Character.toString(viewgb.cells[i+6][j].getStone());
                    switch (ex){
                        case "_oo_oo_":
                            viewgb.cells[i+3][j+3].setStone('o');
                            attack = 1;
                            break;
                        case "ooo_ooo":
                            viewgb.cells[i+3][j+3].setStone('o');
                            attack = 1;
                            break;
                    }
                }
                //Type 2a
                if ( attack == 0 && i + 3 < viewgb.getSizeGB() && j + 6 < viewgb.getSizeGB()){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j+1].getStone())
                            + Character.toString(viewgb.cells[i+2][j+2].getStone())
                            + Character.toString(viewgb.cells[i+3][j+3].getStone())
                            + Character.toString(viewgb.cells[i+2][j+4].getStone())
                            + Character.toString(viewgb.cells[i+1][j+5].getStone())
                            + Character.toString(viewgb.cells[i][j+6].getStone());
                    switch (ex){
                        case "_oo_oo_":
                            viewgb.cells[i][j+3].setStone('o');
                            attack = 1;
                            break;
                        case "ooo_ooo":
                            viewgb.cells[i][j+3].setStone('o');
                            attack = 1;
                            break;
                    }
                }
                //Type 3a
                if ( attack == 0 && i + 6 < viewgb.getSizeGB() && j + 3 < viewgb.getSizeGB()){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j+1].getStone())
                            + Character.toString(viewgb.cells[i+2][j+2].getStone())
                            + Character.toString(viewgb.cells[i+3][j+3].getStone())
                            + Character.toString(viewgb.cells[i+4][j+2].getStone())
                            + Character.toString(viewgb.cells[i+5][j+1].getStone())
                            + Character.toString(viewgb.cells[i+6][j].getStone());
                    switch (ex){
                        case "_oo_oo_":
                            viewgb.cells[i+3][j+3].setStone('o');
                            attack = 1;
                            break;
                        case "ooo_ooo":
                            viewgb.cells[i+3][j+3].setStone('o');
                            attack = 1;
                            break;
                    }
                }
                //Type 4a
                if ( attack == 0 && i - 3 >= 0 && j + 6 < viewgb.getSizeGB()){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i-1][j+1].getStone())
                            + Character.toString(viewgb.cells[i-2][j+2].getStone())
                            + Character.toString(viewgb.cells[i-3][j+3].getStone())
                            + Character.toString(viewgb.cells[i-2][j+4].getStone())
                            + Character.toString(viewgb.cells[i-1][j+5].getStone())
                            + Character.toString(viewgb.cells[i][j+6].getStone());
                    switch (ex){
                        case "_oo_oo_":
                            viewgb.cells[i-3][j+3].setStone('o');
                            attack = 1;
                            break;
                        case "ooo_ooo":
                            viewgb.cells[i-3][j+3].setStone('o');
                            attack = 1;
                            break;
                    }
                }
                if ( attack == 0 && i + 3 < viewgb.getSizeGB() && j - 3 >=0){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j-1].getStone())
                            + Character.toString(viewgb.cells[i+2][j-2].getStone())
                            + Character.toString(viewgb.cells[i+3][j-3].getStone())
                            + Character.toString(viewgb.cells[i+3][j-2].getStone())
                            + Character.toString(viewgb.cells[i+3][j-1].getStone())
                            + Character.toString(viewgb.cells[i+3][j].getStone());
                    switch (ex){
                        case "_oo_oo_":
                            viewgb.cells[i+3][j-3].setStone('o');
                            attack = 1;
                            break;
                        case "ooo_ooo":
                            viewgb.cells[i+3][j-3].setStone('o');
                            attack = 1;
                            break;
                    }
                }
                //Type 2a
                if ( attack == 0 && i + 3 < viewgb.getSizeGB() && j + 3 < viewgb.getSizeGB()){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j+1].getStone())
                            + Character.toString(viewgb.cells[i+2][j+2].getStone())
                            + Character.toString(viewgb.cells[i+3][j+3].getStone())
                            + Character.toString(viewgb.cells[i+2][j+3].getStone())
                            + Character.toString(viewgb.cells[i+1][j+3].getStone())
                            + Character.toString(viewgb.cells[i][j+3].getStone());
                    switch (ex){
                        case "_oo_oo_":
                            viewgb.cells[i+3][j+3].setStone('o');
                            attack = 1;
                            break;
                        case "ooo_ooo":
                            viewgb.cells[i+3][j+3].setStone('o');
                            attack = 1;
                            break;
                    }
                }
                //Type 3a
                if ( attack == 0 && i + 3 < viewgb.getSizeGB() && j + 3 < viewgb.getSizeGB()){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i][j+1].getStone())
                            + Character.toString(viewgb.cells[i][j+2].getStone())
                            + Character.toString(viewgb.cells[i][j+3].getStone())
                            + Character.toString(viewgb.cells[i+1][j+2].getStone())
                            + Character.toString(viewgb.cells[i+2][j+1].getStone())
                            + Character.toString(viewgb.cells[i+3][j].getStone());
                    switch (ex){
                        case "_oo_oo_":
                            viewgb.cells[i][j+3].setStone('o');
                            attack = 1;
                            break;
                        case "ooo_ooo":
                            viewgb.cells[i][j+3].setStone('o');
                            attack = 1;
                            break;
                    }
                }
                //Type 4a
                if ( attack == 0 && i - 3 >= 0 && j + 3 < viewgb.getSizeGB()){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i-1][j].getStone())
                            + Character.toString(viewgb.cells[i-2][j].getStone())
                            + Character.toString(viewgb.cells[i-3][j].getStone())
                            + Character.toString(viewgb.cells[i-2][j+1].getStone())
                            + Character.toString(viewgb.cells[i-1][j+2].getStone())
                            + Character.toString(viewgb.cells[i][j+3].getStone());
                    switch (ex){
                        case "_oo_oo_":
                            viewgb.cells[i-3][j].setStone('o');
                            attack = 1;
                            break;
                        case "ooo_ooo":
                            viewgb.cells[i-3][j].setStone('o');
                            attack = 1;
                            break;
                    }
                }
                
                //
                //Type 1b
                if ( attack == 0 && i + 3 < viewgb.getSizeGB() && j + 3 < viewgb.getSizeGB()){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j+1].getStone())
                            + Character.toString(viewgb.cells[i+2][j+2].getStone())
                            + Character.toString(viewgb.cells[i+3][j+3].getStone())
                            + Character.toString(viewgb.cells[i+3][j+2].getStone())
                            + Character.toString(viewgb.cells[i+3][j+1].getStone())
                            + Character.toString(viewgb.cells[i+3][j].getStone());
                    switch (ex){
                        case "_oo_oo_":
                            viewgb.cells[i+3][j+3].setStone('o');
                            attack = 1;
                            break;
                        case "ooo_ooo":
                            viewgb.cells[i+3][j+3].setStone('o');
                            attack = 1;
                            break;
                    }
                }
                //Type 2b
                if ( attack == 0 && i + 3 < viewgb.getSizeGB() && j + 3 < viewgb.getSizeGB()){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j].getStone())
                            + Character.toString(viewgb.cells[i+2][j].getStone())
                            + Character.toString(viewgb.cells[i+3][j].getStone())
                            + Character.toString(viewgb.cells[i+2][j+1].getStone())
                            + Character.toString(viewgb.cells[i+1][j+2].getStone())
                            + Character.toString(viewgb.cells[i][j+3].getStone());
                    switch (ex){
                        case "_oo_oo_":
                            viewgb.cells[i+3][j].setStone('o');
                            attack = 1;
                            break;
                        case "ooo_ooo":
                            viewgb.cells[i+3][j].setStone('o');
                            attack = 1;
                            break;
                    }
                }
                //Type 3b
                if ( attack == 0 && i + 3 < viewgb.getSizeGB() && j - 3 >= 0){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i][j-1].getStone())
                            + Character.toString(viewgb.cells[i][j-2].getStone())
                            + Character.toString(viewgb.cells[i][j-3].getStone())
                            + Character.toString(viewgb.cells[i+1][j-2].getStone())
                            + Character.toString(viewgb.cells[i+2][j-1].getStone())
                            + Character.toString(viewgb.cells[i+3][j].getStone());
                    switch (ex){
                        case "_oo_oo_":
                            viewgb.cells[i][j-3].setStone('o');
                            attack = 1;
                            break;
                        case "ooo_ooo":
                            viewgb.cells[i][j-3].setStone('o');
                            attack = 1;
                            break;
                    }
                }
                //Type 4b
                if ( attack == 0 && i - 3 >= 0 && j + 3 < viewgb.getSizeGB()){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i-1][j+1].getStone())
                            + Character.toString(viewgb.cells[i-2][j+2].getStone())
                            + Character.toString(viewgb.cells[i-3][j+3].getStone())
                            + Character.toString(viewgb.cells[i-2][j+3].getStone())
                            + Character.toString(viewgb.cells[i-1][j+3].getStone())
                            + Character.toString(viewgb.cells[i][j+3].getStone());
                    switch (ex){
                        case "_oo_oo_":
                            viewgb.cells[i-3][j+3].setStone('o');
                            attack = 1;
                            break;
                        case "ooo_ooo":
                            viewgb.cells[i-3][j+3].setStone('o');
                            attack = 1;
                            break;
                    }
                }
            }
        }
        return attack;
    }
    //DEFENSE
    public int defense4Stones(){
        int defense = 0;
        String horizon, vertical, diatr_bl, diatl_br;
        for (int i = 0; i < viewgb.getSizeGB(); i++){
            for (int j = 0; j < viewgb.getSizeGB(); j++){
                //step 2 horizone
                if (defense == 0 && j + 4 < viewgb.getSizeGB() ){
                    horizon = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i][j+1].getStone())
                            + Character.toString(viewgb.cells[i][j+2].getStone())
                            + Character.toString(viewgb.cells[i][j+3].getStone())
                            + Character.toString(viewgb.cells[i][j+4].getStone());
                    switch (horizon){
                        case "x_xxx":
                            viewgb.cells[i][j+1].setStone('o');
                            defense = 1;
                            break;
                        case "xxx_x":
                            viewgb.cells[i][j+3].setStone('o');
                            defense = 1;
                            break;
                        case "xxxx_":
                            viewgb.cells[i][j+4].setStone('o');
                            defense = 1;
                            break;
                        case "_xxxx":
                            viewgb.cells[i][j].setStone('o');
                            defense = 1;
                            break;
                        case "xx_xx":
                            viewgb.cells[i][j+2].setStone('o');
                            defense = 1;
                            break;
                        
                    }
                    
                }
                
                //step 2 vertical
                if (defense == 0 && i + 4 < viewgb.getSizeGB() ){
                    vertical = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j].getStone())
                            + Character.toString(viewgb.cells[i+2][j].getStone())
                            + Character.toString(viewgb.cells[i+3][j].getStone())
                            + Character.toString(viewgb.cells[i+4][j].getStone());
                    switch (vertical){
                        case "xxxx_":
                            viewgb.cells[i+4][j].setStone('o');
                            defense = 1;
                            break;
                        case "_xxxx":
                            viewgb.cells[i][j].setStone('o');
                            defense = 1;
                            break;
                        case "x_xxx":
                            viewgb.cells[i+1][j].setStone('o');
                            defense = 1;
                            break;
                        case "xxx_x":
                            viewgb.cells[i+3][j].setStone('o');
                            defense = 1;
                            break;
                        case "xx_xx":
                            viewgb.cells[i+2][j].setStone('o');
                            defense = 1;
                            break;
                        
                    }
                    
                }
                //step 2 diagonal top left - bottom right
                if (defense == 0 && j + 4 < viewgb.getSizeGB() && i + 4 < viewgb.getSizeGB() ){
                    diatl_br = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j+1].getStone())
                            + Character.toString(viewgb.cells[i+2][j+2].getStone())
                            + Character.toString(viewgb.cells[i+3][j+3].getStone())
                            + Character.toString(viewgb.cells[i+4][j+4].getStone());
                    switch (diatl_br){
                        case "xxxx_":
                            viewgb.cells[i+4][j+4].setStone('o');
                            defense = 1;
                            break;
                        case "_xxxx":
                            viewgb.cells[i][j].setStone('o');
                            defense = 1;
                            break;
                        case "x_xxx":
                            viewgb.cells[i+1][j+1].setStone('o');
                            defense = 1;
                            break;
                        case "xxx_x":
                            viewgb.cells[i+3][j+3].setStone('o');
                            defense = 1;
                            break;
                        case "xx_xx":
                            viewgb.cells[i+2][j+2].setStone('o');
                            defense = 1;
                            break;
                        
                    }
                    
                }
                //step 2 diagonal top right - bottom left
                if (defense == 0 && i + 4 < viewgb.getSizeGB() && j - 4 >= 0 ){
                    diatr_bl = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j-1].getStone())
                            + Character.toString(viewgb.cells[i+2][j-2].getStone())
                            + Character.toString(viewgb.cells[i+3][j-3].getStone())
                            + Character.toString(viewgb.cells[i+4][j-4].getStone());
                    switch (diatr_bl){
                        case "xxxx_":
                            viewgb.cells[i+4][j-4].setStone('o');
                            defense = 1;
                            break;
                        case "_xxxx":
                            viewgb.cells[i][j].setStone('o');
                            defense = 1;
                            break;
                        case "x_xxx":
                            viewgb.cells[i+1][j-1].setStone('o');
                            defense = 1;
                            break;
                        case "xxx_x":
                            viewgb.cells[i+3][j-3].setStone('o');
                            defense = 1;
                            break;
                        case "xx_xx":
                            viewgb.cells[i+2][j-2].setStone('o');
                            defense = 1;
                            break;
                        
                    }
                    
                }
            }
        }
        return defense;
    }
    public int defense3Stones(){
        int defense = 0;
        String horizon, vertical, diatr_bl, diatl_br;
        for (int i = 0; i < viewgb.getSizeGB(); i++){
            for (int j = 0; j < viewgb.getSizeGB(); j++){
                //step 3 horizone
                if (defense == 0 && j + 4 < viewgb.getSizeGB() ){
                    horizon = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i][j+1].getStone())
                            + Character.toString(viewgb.cells[i][j+2].getStone())
                            + Character.toString(viewgb.cells[i][j+3].getStone())
                            + Character.toString(viewgb.cells[i][j+4].getStone());
                    switch (horizon){
                        case "_xxx_":
                            viewgb.cells[i][j].setStone('o');
                            defense = 1;
                            break;
                        case "x_xx_":
                            viewgb.cells[i][j+1].setStone('o');
                            defense = 1;
                            break;
                        case "_x_xx":
                            viewgb.cells[i][j+2].setStone('o');
                            defense = 1;
                            break;
                        case "xx_x_":
                            viewgb.cells[i][j+2].setStone('o');
                            defense = 1;
                            break;
                        case "_xx_x":
                            viewgb.cells[i][j+3].setStone('o');
                            defense = 1;
                            break;
                        
                    }
                    
                }
                //step 3 vertical
                if(defense == 0 && i + 4 < viewgb.getSizeGB() ){
                    vertical = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j].getStone())
                            + Character.toString(viewgb.cells[i+2][j].getStone())
                            + Character.toString(viewgb.cells[i+3][j].getStone())
                            + Character.toString(viewgb.cells[i+4][j].getStone());
                    switch (vertical){
                        case "_xxx_":
                            viewgb.cells[i][j].setStone('o');
                            defense = 1;
                            break;
                        case "x_xx_":
                            viewgb.cells[i+1][j].setStone('o');
                            defense = 1;
                            break;
                        case "_x_xx":
                            viewgb.cells[i+2][j].setStone('o');
                            defense = 1;
                            break;
                        case "xx_x_":
                            viewgb.cells[i+2][j].setStone('o');
                            defense = 1;
                            break;
                        case "_xx_x":
                            viewgb.cells[i+3][j].setStone('o');
                            defense = 1;
                            break;
                        
                    }
                }
                //step 3 diagonal top left - bottom right
                if(defense == 0 && i + 4 < viewgb.getSizeGB() && j + 4 < viewgb.getSizeGB()){
                    diatl_br = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j+1].getStone())
                            + Character.toString(viewgb.cells[i+2][j+2].getStone())
                            + Character.toString(viewgb.cells[i+3][j+3].getStone())
                            + Character.toString(viewgb.cells[i+4][j+4].getStone());
                    switch (diatl_br){
                        case "_xxx_":
                            viewgb.cells[i][j].setStone('o');
                            defense = 1;
                            break;
                        case "x_xx_":
                            viewgb.cells[i+1][j+1].setStone('o');
                            defense = 1;
                            break;
                        case "_x_xx":
                            viewgb.cells[i+2][j+2].setStone('o');
                            defense = 1;
                            break;
                        case "xx_x_":
                            viewgb.cells[i+2][j+2].setStone('o');
                            defense = 1;
                            break;
                        case "_xx_x":
                            viewgb.cells[i+3][j+3].setStone('o');
                            defense = 1;
                            break;
                        
                    }
                }
                //step 3 diagonal top right - bottom left
                if(defense == 0 && i + 4 < viewgb.getSizeGB() && j - 4 >=0 ){
                    diatr_bl = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j-1].getStone())
                            + Character.toString(viewgb.cells[i+2][j-2].getStone())
                            + Character.toString(viewgb.cells[i+3][j-3].getStone())
                            + Character.toString(viewgb.cells[i+4][j-4].getStone());
                    switch (diatr_bl){
                        case "_xxx_":
                            viewgb.cells[i][j].setStone('o');
                            defense = 1;
                            break;
                        case "x_xx_":
                            viewgb.cells[i+1][j-1].setStone('o');
                            defense = 1;
                            break;
                        case "_x_xx":
                            viewgb.cells[i+2][j-2].setStone('o');
                            defense = 1;
                            break;
                        case "xx_x_":
                            viewgb.cells[i+2][j-2].setStone('o');
                            defense = 1;
                            break;
                        case "_xx_x":
                            viewgb.cells[i+3][j-3].setStone('o');
                            defense = 1;
                            break;                      
                    }
                }
            }
        }
        return defense;
    }
    public int defense2ways(){
        int defense = 0;
        for (int i = 0; i < viewgb.getSizeGB(); i++){
            for (int j = 0; j < viewgb.getSizeGB(); j++){
                //Type 1a
                if ( defense == 0 && i + 6 < viewgb.getSizeGB() && j - 3 >=0){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j-1].getStone())
                            + Character.toString(viewgb.cells[i+2][j-2].getStone())
                            + Character.toString(viewgb.cells[i+3][j-3].getStone())
                            + Character.toString(viewgb.cells[i+4][j-2].getStone())
                            + Character.toString(viewgb.cells[i+5][j-1].getStone())
                            + Character.toString(viewgb.cells[i+6][j].getStone());
                    switch (ex){
                        case "_xx_xx_":
                            viewgb.cells[i+3][j+3].setStone('o');
                            defense = 1;
                            break;
                        case "xxx_xxx":
                            viewgb.cells[i+3][j+3].setStone('o');
                            defense = 1;
                            break;
                    }
                }
                //Type 2a
                if ( defense == 0 && i + 3 < viewgb.getSizeGB() && j + 6 < viewgb.getSizeGB()){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j+1].getStone())
                            + Character.toString(viewgb.cells[i+2][j+2].getStone())
                            + Character.toString(viewgb.cells[i+3][j+3].getStone())
                            + Character.toString(viewgb.cells[i+2][j+4].getStone())
                            + Character.toString(viewgb.cells[i+1][j+5].getStone())
                            + Character.toString(viewgb.cells[i][j+6].getStone());
                    switch (ex){
                        case "_xx_xx_":
                            viewgb.cells[i][j+3].setStone('o');
                            defense = 1;
                            break;
                        case "xxx_xxx":
                            viewgb.cells[i][j+3].setStone('o');
                            defense = 1;
                            break;
                    }
                }
                //Type 3a
                if ( defense == 0 && i + 6 < viewgb.getSizeGB() && j + 3 < viewgb.getSizeGB()){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j+1].getStone())
                            + Character.toString(viewgb.cells[i+2][j+2].getStone())
                            + Character.toString(viewgb.cells[i+3][j+3].getStone())
                            + Character.toString(viewgb.cells[i+4][j+2].getStone())
                            + Character.toString(viewgb.cells[i+5][j+1].getStone())
                            + Character.toString(viewgb.cells[i+6][j].getStone());
                    switch (ex){
                        case "_xx_xx_":
                            viewgb.cells[i+3][j+3].setStone('o');
                            defense = 1;
                            break;
                        case "xxx_xxx":
                            viewgb.cells[i+3][j+3].setStone('o');
                            defense = 1;
                            break;
                    }
                }
                //Type 4a
                if ( defense == 0 && i - 3 >= 0 && j + 6 < viewgb.getSizeGB()){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i-1][j+1].getStone())
                            + Character.toString(viewgb.cells[i-2][j+2].getStone())
                            + Character.toString(viewgb.cells[i-3][j+3].getStone())
                            + Character.toString(viewgb.cells[i-2][j+4].getStone())
                            + Character.toString(viewgb.cells[i-1][j+5].getStone())
                            + Character.toString(viewgb.cells[i][j+6].getStone());
                    switch (ex){
                        case "_xx_xx_":
                            viewgb.cells[i-3][j+3].setStone('o');
                            defense = 1;
                            break;
                        case "xxx_xxx":
                            viewgb.cells[i-3][j+3].setStone('o');
                            defense = 1;
                            break;
                    }
                }
                //Type 1a
                if ( defense == 0 && i + 3 < viewgb.getSizeGB() && j - 3 >=0){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j-1].getStone())
                            + Character.toString(viewgb.cells[i+2][j-2].getStone())
                            + Character.toString(viewgb.cells[i+3][j-3].getStone())
                            + Character.toString(viewgb.cells[i+3][j-2].getStone())
                            + Character.toString(viewgb.cells[i+3][j-1].getStone())
                            + Character.toString(viewgb.cells[i+3][j].getStone());
                    switch (ex){
                        case "_xx_xx_":
                            viewgb.cells[i+3][j-3].setStone('o');
                            defense = 1;
                            break;
                        case "xxx_xxx":
                            viewgb.cells[i+3][j-3].setStone('o');
                            defense = 1;
                            break;
                    }
                }
                //Type 2a
                if ( defense == 0 && i + 3 < viewgb.getSizeGB() && j + 3 < viewgb.getSizeGB()){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j+1].getStone())
                            + Character.toString(viewgb.cells[i+2][j+2].getStone())
                            + Character.toString(viewgb.cells[i+3][j+3].getStone())
                            + Character.toString(viewgb.cells[i+2][j+3].getStone())
                            + Character.toString(viewgb.cells[i+1][j+3].getStone())
                            + Character.toString(viewgb.cells[i][j+3].getStone());
                    switch (ex){
                        case "_xx_xx_":
                            viewgb.cells[i+3][j+3].setStone('o');
                            defense = 1;
                            break;
                        case "xxx_xxx":
                            viewgb.cells[i+3][j+3].setStone('o');
                            defense = 1;
                            break;
                    }
                }
                //Type 3a
                if ( defense == 0 && i + 3 < viewgb.getSizeGB() && j + 3 < viewgb.getSizeGB()){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i][j+1].getStone())
                            + Character.toString(viewgb.cells[i][j+2].getStone())
                            + Character.toString(viewgb.cells[i][j+3].getStone())
                            + Character.toString(viewgb.cells[i+1][j+2].getStone())
                            + Character.toString(viewgb.cells[i+2][j+1].getStone())
                            + Character.toString(viewgb.cells[i+3][j].getStone());
                    switch (ex){
                        case "_xx_xx_":
                            viewgb.cells[i][j+3].setStone('o');
                            defense = 1;
                            break;
                        case "xxx_xxx":
                            viewgb.cells[i][j+3].setStone('o');
                            defense = 1;
                            break;
                    }
                }
                //Type 4a
                if ( defense == 0 && i - 3 >= 0 && j + 3 < viewgb.getSizeGB()){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i-1][j].getStone())
                            + Character.toString(viewgb.cells[i-2][j].getStone())
                            + Character.toString(viewgb.cells[i-3][j].getStone())
                            + Character.toString(viewgb.cells[i-2][j+1].getStone())
                            + Character.toString(viewgb.cells[i-1][j+2].getStone())
                            + Character.toString(viewgb.cells[i][j+3].getStone());
                    switch (ex){
                        case "_xx_xx_":
                            viewgb.cells[i-3][j].setStone('o');
                            defense = 1;
                            break;
                        case "xxx_xxx":
                            viewgb.cells[i-3][j].setStone('o');
                            defense = 1;
                            break;
                    }
                }
                
                //
                //Type 1b
                if ( defense == 0 && i + 3 < viewgb.getSizeGB() && j + 3 < viewgb.getSizeGB()){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j+1].getStone())
                            + Character.toString(viewgb.cells[i+2][j+2].getStone())
                            + Character.toString(viewgb.cells[i+3][j+3].getStone())
                            + Character.toString(viewgb.cells[i+3][j+2].getStone())
                            + Character.toString(viewgb.cells[i+3][j+1].getStone())
                            + Character.toString(viewgb.cells[i+3][j].getStone());
                    switch (ex){
                        case "_xx_xx_":
                            viewgb.cells[i+3][j+3].setStone('o');
                            defense = 1;
                            break;
                        case "xxx_xxx":
                            viewgb.cells[i+3][j+3].setStone('o');
                            defense = 1;
                            break;
                    }
                }
                //Type 2b
                if ( defense == 0 && i + 3 < viewgb.getSizeGB() && j + 3 < viewgb.getSizeGB()){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j].getStone())
                            + Character.toString(viewgb.cells[i+2][j].getStone())
                            + Character.toString(viewgb.cells[i+3][j].getStone())
                            + Character.toString(viewgb.cells[i+2][j+1].getStone())
                            + Character.toString(viewgb.cells[i+1][j+2].getStone())
                            + Character.toString(viewgb.cells[i][j+3].getStone());
                    switch (ex){
                        case "_xx_xx_":
                            viewgb.cells[i+3][j].setStone('o');
                            defense = 1;
                            break;
                        case "xxx_xxx":
                            viewgb.cells[i+3][j].setStone('o');
                            defense = 1;
                            break;
                    }
                }
                //Type 3b
                if ( defense == 0 && i + 3 < viewgb.getSizeGB() && j - 3 >= 0){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i][j-1].getStone())
                            + Character.toString(viewgb.cells[i][j-2].getStone())
                            + Character.toString(viewgb.cells[i][j-3].getStone())
                            + Character.toString(viewgb.cells[i+1][j-2].getStone())
                            + Character.toString(viewgb.cells[i+2][j-1].getStone())
                            + Character.toString(viewgb.cells[i+3][j].getStone());
                    switch (ex){
                        case "_xx_xx_":
                            viewgb.cells[i][j-3].setStone('o');
                            defense = 1;
                            break;
                        case "xxx_xxx":
                            viewgb.cells[i][j-3].setStone('o');
                            defense = 1;
                            break;
                    }
                }
                //Type 4b
                if ( defense == 0 && i - 3 >= 0 && j + 3 < viewgb.getSizeGB()){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i-1][j+1].getStone())
                            + Character.toString(viewgb.cells[i-2][j+2].getStone())
                            + Character.toString(viewgb.cells[i-3][j+3].getStone())
                            + Character.toString(viewgb.cells[i-2][j+3].getStone())
                            + Character.toString(viewgb.cells[i-1][j+3].getStone())
                            + Character.toString(viewgb.cells[i][j+3].getStone());
                    switch (ex){
                        case "_xx_xx_":
                            viewgb.cells[i-3][j+3].setStone('o');
                            defense = 1;
                            break;
                        case "xxx_xxx":
                            viewgb.cells[i-3][j+3].setStone('o');
                            defense = 1;
                            break;
                    }
                }
                if ( defense == 0 && i + 3 < viewgb.getSizeGB() && j - 3 >=0){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j].getStone())
                            + Character.toString(viewgb.cells[i+2][j].getStone())
                            + Character.toString(viewgb.cells[i+3][j].getStone())
                            + Character.toString(viewgb.cells[i+3][j-1].getStone())
                            + Character.toString(viewgb.cells[i+3][j-2].getStone())
                            + Character.toString(viewgb.cells[i+3][j-3].getStone());
                    switch (ex){
                        case "_xx_xx_":
                            viewgb.cells[i+3][j].setStone('o');
                            defense = 1;
                            break;
                        case "xxx_xxx":
                            viewgb.cells[i+3][j].setStone('o');
                            defense = 1;
                            break;
                    }
                }
                //Type 2a
                if ( defense == 0 && i + 3 < viewgb.getSizeGB() && j + 3 < viewgb.getSizeGB()){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i][j+1].getStone())
                            + Character.toString(viewgb.cells[i][j+2].getStone())
                            + Character.toString(viewgb.cells[i][j+3].getStone())
                            + Character.toString(viewgb.cells[i+1][j+3].getStone())
                            + Character.toString(viewgb.cells[i+2][j+3].getStone())
                            + Character.toString(viewgb.cells[i+3][j+3].getStone());
                    switch (ex){
                        case "_xx_xx_":
                            viewgb.cells[i][j+3].setStone('o');
                            defense = 1;
                            break;
                        case "xxx_xxx":
                            viewgb.cells[i][j+3].setStone('o');
                            defense = 1;
                            break;
                    }
                }
                //Type 3a
                if ( defense == 0 && i + 3 < viewgb.getSizeGB() && j - 3 >=0){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i][j-1].getStone())
                            + Character.toString(viewgb.cells[i][j-2].getStone())
                            + Character.toString(viewgb.cells[i][j-3].getStone())
                            + Character.toString(viewgb.cells[i+1][j-3].getStone())
                            + Character.toString(viewgb.cells[i+2][j-3].getStone())
                            + Character.toString(viewgb.cells[i+3][j-3].getStone());
                    switch (ex){
                        case "_xx_xx_":
                            viewgb.cells[i][j-3].setStone('o');
                            defense = 1;
                            break;
                        case "xxx_xxx":
                            viewgb.cells[i][j-3].setStone('o');
                            defense = 1;
                            break;
                    }
                }
                //Type 4a
                if ( defense == 0 && i + 3 < viewgb.getSizeGB() && j + 3 < viewgb.getSizeGB()){
                    String ex = Character.toString(viewgb.cells[i][j].getStone())
                            + Character.toString(viewgb.cells[i+1][j].getStone())
                            + Character.toString(viewgb.cells[i+2][j].getStone())
                            + Character.toString(viewgb.cells[i+3][j].getStone())
                            + Character.toString(viewgb.cells[i+3][j+1].getStone())
                            + Character.toString(viewgb.cells[i+3][j+2].getStone())
                            + Character.toString(viewgb.cells[i+3][j+3].getStone());
                    switch (ex){
                        case "_xx_xx_":
                            viewgb.cells[i+3][j].setStone('o');
                            defense = 1;
                            break;
                        case "xxx_xxx":
                            viewgb.cells[i+3][j].setStone('o');
                            defense = 1;
                            break;
                    }
                }
            }
        }
        return defense;
    }

}
