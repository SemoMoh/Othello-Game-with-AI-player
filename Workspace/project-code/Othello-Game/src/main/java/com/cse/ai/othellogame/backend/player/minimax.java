package com.cse.ai.othellogame.backend.player;

import com.cse.ai.othellogame.backend.game.Board;

import java.awt.*;
import java.util.ArrayList;

public class minimax {
    public static ArrayList<Point> getReversePoints(int[][] board,int player,int i,int j){

        ArrayList<Point> allReversePoints = new ArrayList<>();

        int mi , mj , c;
        int oplayer = ((player == 1) ? 2 : 1);

        //move up
        ArrayList<Point> mupts = new ArrayList<>();
        mi = i - 1;
        mj = j;
        while(mi>0 && board[mi][mj] == oplayer){
            mupts.add(new Point(mi,mj));
            mi--;
        }
        if(mi>=0 && board[mi][mj] == player && mupts.size()>0){
            allReversePoints.addAll(mupts);
        }


        //move down
        ArrayList<Point> mdpts = new ArrayList<>();
        mi = i + 1;
        mj = j;
        while(mi<7 && board[mi][mj] == oplayer){
            mdpts.add(new Point(mi,mj));
            mi++;
        }
        if(mi<=7 && board[mi][mj] == player && mdpts.size()>0){
            allReversePoints.addAll(mdpts);
        }

        //move left
        ArrayList<Point> mlpts = new ArrayList<>();
        mi = i;
        mj = j - 1;
        while(mj>0 && board[mi][mj] == oplayer){
            mlpts.add(new Point(mi,mj));
            mj--;
        }
        if(mj>=0 && board[mi][mj] == player && mlpts.size()>0){
            allReversePoints.addAll(mlpts);
        }

        //move right
        ArrayList<Point> mrpts = new ArrayList<>();
        mi = i;
        mj = j + 1;
        while(mj<7 && board[mi][mj] == oplayer){
            mrpts.add(new Point(mi,mj));
            mj++;
        }
        if(mj<=7 && board[mi][mj] == player && mrpts.size()>0){
            allReversePoints.addAll(mrpts);
        }

        //move up left
        ArrayList<Point> mulpts = new ArrayList<>();
        mi = i - 1;
        mj = j - 1;
        while(mi>0 && mj>0 && board[mi][mj] == oplayer){
            mulpts.add(new Point(mi,mj));
            mi--;
            mj--;
        }
        if(mi>=0 && mj>=0 && board[mi][mj] == player && mulpts.size()>0){
            allReversePoints.addAll(mulpts);
        }

        //move up right
        ArrayList<Point> murpts = new ArrayList<>();
        mi = i - 1;
        mj = j + 1;
        while(mi>0 && mj<7 && board[mi][mj] == oplayer){
            murpts.add(new Point(mi,mj));
            mi--;
            mj++;
        }
        if(mi>=0 && mj<=7 && board[mi][mj] == player && murpts.size()>0){
            allReversePoints.addAll(murpts);
        }

        //move down left
        ArrayList<Point> mdlpts = new ArrayList<>();
        mi = i + 1;
        mj = j - 1;
        while(mi<7 && mj>0 && board[mi][mj] == oplayer){
            mdlpts.add(new Point(mi,mj));
            mi++;
            mj--;
        }
        if(mi<=7 && mj>=0 && board[mi][mj] == player && mdlpts.size()>0){
            allReversePoints.addAll(mdlpts);
        }

        //move down right
        ArrayList<Point> mdrpts = new ArrayList<>();
        mi = i + 1;
        mj = j + 1;
        while(mi<7 && mj<7 && board[mi][mj] == oplayer){
            mdrpts.add(new Point(mi,mj));
            mi++;
            mj++;
        }
        if(mi<=7 && mj<=7 && board[mi][mj] == player && mdrpts.size()>0){
            allReversePoints.addAll(mdrpts);
        }

        return allReversePoints;
    }
    public static int[][] getNewBoardAfterMove(int[][] board, Point move , int player){
        //get clone of old board
        int[][] newboard = new int[8][8];
        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 8; l++) {
                newboard[k][l] = board[k][l];
            }
        }

        //place piece
        newboard[move.x][move.y] = player;
        //reverse pieces
        ArrayList<Point> rev = getReversePoints(newboard,player,move.x,move.y);
        for(Point pt : rev){
            newboard[pt.x][pt.y] = player;
        }

        return newboard;
    }

    public static boolean canPlay(int[][] board,int player,int i,int j){

        if(board[i][j] != 0) return false;

        int mi , mj , c;
        int oplayer = ((player == 1) ? 2 : 1);

        //move up
        mi = i - 1;
        mj = j;
        c = 0;
        while(mi>0 && board[mi][mj] == oplayer){
            mi--;
            c++;
        }
        if(mi>=0 && board[mi][mj] == player && c>0) return true;


        //move down
        mi = i + 1;
        mj = j;
        c = 0;
        while(mi<7 && board[mi][mj] == oplayer){
            mi++;
            c++;
        }
        if(mi<=7 && board[mi][mj] == player && c>0) return true;

        //move left
        mi = i;
        mj = j - 1;
        c = 0;
        while(mj>0 && board[mi][mj] == oplayer){
            mj--;
            c++;
        }
        if(mj>=0 && board[mi][mj] == player && c>0) return true;

        //move right
        mi = i;
        mj = j + 1;
        c = 0;
        while(mj<7 && board[mi][mj] == oplayer){
            mj++;
            c++;
        }
        if(mj<=7 && board[mi][mj] == player && c>0) return true;

        //move up left
        mi = i - 1;
        mj = j - 1;
        c = 0;
        while(mi>0 && mj>0 && board[mi][mj] == oplayer){
            mi--;
            mj--;
            c++;
        }
        if(mi>=0 && mj>=0 && board[mi][mj] == player && c>0) return true;

        //move up right
        mi = i - 1;
        mj = j + 1;
        c = 0;
        while(mi>0 && mj<7 && board[mi][mj] == oplayer){
            mi--;
            mj++;
            c++;
        }
        if(mi>=0 && mj<=7 && board[mi][mj] == player && c>0) return true;

        //move down left
        mi = i + 1;
        mj = j - 1;
        c = 0;
        while(mi<7 && mj>0 && board[mi][mj] == oplayer){
            mi++;
            mj--;
            c++;
        }
        if(mi<=7 && mj>=0 && board[mi][mj] == player && c>0) return true;

        //move down right
        mi = i + 1;
        mj = j + 1;
        c = 0;
        while(mi<7 && mj<7 && board[mi][mj] == oplayer){
            mi++;
            mj++;
            c++;
        }
        if(mi<=7 && mj<=7 && board[mi][mj] == player && c>0) return true;

        //when all hopes fade away
        return false;
    }

    public static ArrayList<Point> getAllPossibleMoves(int[][] board, int player){
        ArrayList<Point> result = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(canPlay(board,player,i,j)){
                    result.add(new Point(i,j));
                }
            }
        }
        System.out.println("number of possible moves : " + result.size());
        return result;
    }


    public static int evalDiscDiff(int[][] board , int player){
        int oplayer = (player==1) ? 2 : 1;

        int mySC = getPlayerStoneCount(board,player);
        int opSC = getPlayerStoneCount(board,oplayer);
        System.out.println("the value of node heuristic is "+ 100 * (mySC - opSC) / (mySC + opSC)+"**************************");

        return 100 * (mySC - opSC) / (mySC + opSC);
    }

    public static int getPlayerStoneCount(int[][] board, int player){
        int score = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if(board[i][j] == player) score++;
            }
        }
        return score;
    }
    public static int[][] resetBoard(){
        int[][]board = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j]=0;
            }
        }
        //initial board state
        board[3][3] = 2;
        board[3][4] = 1;
        board[4][3] = 1;
        board[4][4] = 2;
        return board;
    }
    public static boolean isGameFinished(int[][] board){
        return !(hasAnyMoves(board,1) || hasAnyMoves(board,2));
    }
    public static boolean hasAnyMoves(int[][] board, int player){
        return getAllPossibleMoves(board,player).size() > 0;
    }
    //returns minimax value for a given node (without A/B pruning)
    public static int MM(int[][] node,int player,int depth,boolean max){
        AIPlayer.nodesExplored++;
        System.out.println("at depth "+ depth+" the board is: ////////////////////////////");
        printBoard(node);
        //if terminal reached or depth limit reached evaluate
        if(depth == 0 || isGameFinished(node)){
            //BoardPrinter bpe = new BoardPrinter(node,"Depth : " + depth);
            return evalDiscDiff(node,player);
        }
        int oplayer = (player==1) ? 2 : 1;
        //if no moves available then forfeit turn
        if((max && !hasAnyMoves(node,player)) || (!max && !hasAnyMoves(node,oplayer))){
            System.out.println("Forfeit State Reached !");
            return MM(node,player,depth-1,!max);
        }
        int score;
        if(max){
            //maximizing
            score = Integer.MIN_VALUE;
            for(Point move : getAllPossibleMoves(node,player)){ //my turn
                //create new node
                int[][] newNode = getNewBoardAfterMove(node,move,player);
                //recursive call
                int childScore = MM(newNode,player,depth-1,false);
                if(childScore > score) score = childScore;
            }
        }else{
            //minimizing
            score = Integer.MAX_VALUE;
            for(Point move : getAllPossibleMoves(node,oplayer)){ //opponent turn
                //create new node
                int[][] newNode = getNewBoardAfterMove(node,move,oplayer);
                //recursive call
                int childScore = MM(newNode,player,depth-1,true);
                if(childScore < score) score = childScore;
            }
        }
        return score;
    }

    public static void printBoard(int [][] board){
        StringBuilder sb = new StringBuilder();
        sb.append("  a b c d e f g h \n");
        for (int row = 0; row < 8; row++) {
            sb.append(row+1 +" ");
            for (int col = 0; col < 8; col++) {
                sb.append(board[row][col]).append(" ");
            }
            sb.append("\n");
        }
        sb.append("  1 2 3 4 5 6 7 8");
        System.out.println(sb);
    }


    public static void main(String[] args)
    {
        Board b = new Board();
        int[][] board = resetBoard();
        printBoard(board);
        AIPlayer ai = new AIPlayer(b,'B', 2);
        System.out.println("the best move is  " + ai.makeMove(board, 2,3));
//        ArrayList<Point> points = getAllPossibleMoves(board,2);
//        for (Point p:points)
//        {
//            board[p.x][p.y]=5;
//        }
//        printBoard(board);
    }


}
