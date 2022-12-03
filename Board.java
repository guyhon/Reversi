;

public class Board {
    private palcement board[][]  ;
    private int length;

    public Board(int boardSize){
        length = boardSize;
        this.board = new palcement[boardSize][boardSize];
        boardInitialization(board);
    }

    private void boardInitialization(palcement[][] board) {
        boardInitializationAllGray(board);
        boardInitializationFuorAtStart(board);
    }

    private void boardInitializationFuorAtStart(palcement[][] board) {
        int middle = length/2-1;
        board[middle][middle] = palcement.RED;
        board[middle+1][middle+1] = palcement.RED;
        middle = length/2-1;
        board[middle+1][middle] = palcement.BLUE;
        board[middle][middle+1] = palcement.BLUE;
    }

    private void boardInitializationAllGray(palcement[][] board) {
        int i=0, j =0;
        for(;i<length;i++){
            for(;j<length;j++) {
                board[i][j] = palcement.GARY;
            }
            j=0;
        }
    }


    public palcement[][] getBoard() {
        return board;
    }

    public int getLength() {
        return length;
    }

//    public int numberOfReds(){
//        int reds=0;
//        for(int i=0;i<length;i++){
//            for(int j=0;j<length;j++) {
//                if( board[i][j]==palcement.RED)
//                    reds+=1;
//            }
//        }
//        return reds;
//    }
//
//    public int numberOfBlues(){
//        int blues=0;
//        for(int i=0;i<length;i++){
//            for(int j=0;j<length;j++) {
//                if( board[i][j]==palcement.BLUE)
//                    blues+=1;
//            }
//        }
//        return blues;
//    }

}
