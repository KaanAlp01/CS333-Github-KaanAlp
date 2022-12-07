package connect4;


public class main {

    public static void main(String[] args) {
        boolean winner = false;
        connect4 game = new connect4();
        game.initiateBoard();

        while(!winner){
        game.playerTurn();
        game.showBoard();
        if(game.getState()){
            break;
        }
        game.computerTurn();
        game.showBoard();
        winner = game.getState();
        }
    }
}


