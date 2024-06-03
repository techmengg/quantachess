package main.model;

public class check {


    private boolean isEnemyHorizontal(int r, int c1, int c2, Piece[][] chessboard) {
        int minC = Math.min(c1, c2);
        int maxC = Math.max(c1, c2);
        for (int i = minC + 1; i < maxC; i++) {
            if (chessboard[r][i] == null) {
                return false; //No one detected 
            }
            else if (chessboard[r][i] != null && chessboard[r][i].getColor() != chessboard[r][c].color  )
        }
        return true; // Enemy Detected
    }

    private boolean isEnemyVerticle(int r1, int c, int r2, Piece[][] chessboard) {
        int minR = Math.min(r1, r2);
        int maxR = Math.max(r1, r2);
        for (int i = minR + 1; i < maxR; i++) {
            if (chessboard[i][c] != null) {
                return false; // Path is blocked
            }
        }
        return true; // Path is clear
    }

    private boolean isEnemyDiagonal(int r1, int c1, int r2, int c2, Piece[][] chessboard) {
        int dr = Integer.signum(r2 - r1);
        int dc = Integer.signum(c2 - c1);
        int r = r1 + dr;
        int c = c1 + dc;
        while (r != r2 || c != c2) {
            if (chessboard[r][c] != null) {
                return false; // Path is blocked
            }
            r += dr;
            c += dc;
        }
        return true; // Path is clear
    }
}
