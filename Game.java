public class Game {

    private Board board;
    private boolean redTurn= true;
    private boolean click = false;
    private boolean closure = false;

    private palcement[][] b;
    private palcement gary = palcement.GARY;
    private palcement R = palcement.RED;
    private palcement B = palcement.BLUE;
    private palcement g = palcement.GLOW;
    private final int BOARD_SIZE;

    public Game(int i) {
        Board board = new Board(i);
        this.b = board.getBoard();
        this.BOARD_SIZE = board.getLength();
        this.redTurn = false;
        this.click = false;
    }

    public boolean opponentColor(palcement p) {
        if (redTurn && p == palcement.BLUE)
            return true;
        if (!redTurn && p == palcement.RED)
            return true;
        return false;
    }

    public void changeColor(int i, int j){
        if(click=true) {
            if (redTurn)
                b[i][j] = palcement.RED;
            else
                b[i][j] = palcement.BLUE;
        }
    }

    public boolean notValidClosure(int x, int y){
        boolean isPalcementOutOfBoard = isOutOfRange(x,0, BOARD_SIZE) || isOutOfRange(y,0, BOARD_SIZE);
        if(isPalcementOutOfBoard)
            return true;
        boolean isPalcmementIsGray = (b[x][y]!=palcement.BLUE&&b[x][y]!=palcement.RED);
        if(isPalcmementIsGray)
            return true;
        return false;
    }

    public boolean isOutOfRange(int num,int min, int max){

        return num < min || max <= num;
    }
    public void palcementThatGlow(){
        for(int i = 0; i< BOARD_SIZE; i++){
            for(int j = 0; j< BOARD_SIZE; j++){
                if(b[i][j]== gary){
                   if(IfThereIsCloseOpponent(i,j)){
                        b[i][j]=palcement.GLOW;
                   }
                }
            }
        }
    }

    public boolean CloseOpponent(int i,int j){
        if(i<0||j<0||i>= BOARD_SIZE ||j>= BOARD_SIZE)
            return false;
        if (opponentColor(b[i][j]))
            return true;
        return false;
    }


    public boolean IfThereIsCloseOpponent(int i,int j){
        for(int k=-1;k<2;k++){
            for(int n=-1;n<2;n++){
                if(CloseOpponent(i+k,j+n))
                   if(colorClosure(i+k,j+n,k,n))
                       return true;
            }
        }
        return false;
    }

    public void flipPalcement(int i,int j){
        for(int k=-1;k<=1;k++){
            for(int n=-1;n<=1;n++){
                changeOnDirection(i, j, k, n);
            }
        }
    }

    private void changeOnDirection(int i, int j, int k, int n) {
        if(CloseOpponent(i + k, j + n)) {
            if (colorClosure(i + k, j + n, k, n)) {
                changeColorsClosure(i + k, j + n, k, n);
            }
        }
    }


    public boolean colorClosure(int i, int j, int k, int n){
        while(!notValidClosure(i,j)){
            if(redTurn==true&&b[i][j]==palcement.RED||redTurn==false&&b[i][j]==palcement.BLUE) {
                return true;
            }
           // colorClosure(i+=k,j+=n,k,n);
            i+=k;
            j+=n;
        }
        return false;
    }

    public void changeColorsClosure(int i, int j, int k, int n){
        while(!notValidClosure(i,j)){
            if(redTurn==true&&b[i][j]==palcement.RED||redTurn==false&&b[i][j]==palcement.BLUE) {
                break;
            }
            if(click==true)
                changeColor(i,j);
            i+=k;
            j+=n;
        }

    }



    public void click(int i,int j){
       if(b[i][j]==palcement.GLOW){
            click=true;
            changeColor(i,j);
            flipPalcement(i,j);
            click=false;
            redTurn = !redTurn;
       }
    }



    public void stopGlowing(){
        for(int i = 0; i< BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if(b[i][j]==palcement.GLOW)
                    b[i][j]=palcement.GARY;
            }
        }
    }

    public boolean boardIsFull(){
        for(int i = 0; i< BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if(b[i][j]==palcement.GLOW)
                   return false;
            }
        }
        return true;
    }

    public int getNumberOfReds(){
        int reds=0;
        for(int i = 0; i< BOARD_SIZE; i++){
            for(int j = 0; j< BOARD_SIZE; j++) {
                if( b[i][j]==palcement.RED)
                    reds+=1;
            }
        }
        return reds;
    }

    public int getNumberOfBlus(){
        int blues=0;
        for(int i = 0; i< BOARD_SIZE; i++){
            for(int j = 0; j< BOARD_SIZE; j++) {
                if( b[i][j]==palcement.BLUE)
                    blues+=1;
            }
        }
        return blues;
    }


    public palcement[][] getBoard() {

        return b;
    }

    public int getBOARD_SIZE() {

        return BOARD_SIZE;
    }
    public void setRedTurn(boolean redTurn) {

        this.redTurn = redTurn;
    }

    public boolean isRedTurn() {

        return redTurn;
    }


}
