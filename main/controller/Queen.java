package main.controller;

public class Queen extends Piece {
    public Queen(boolean color) {
        super(color);
    }

    public boolean canMove(int r1, int c1, int r2, int c2, Piece[][] chessboard) {
        if (r1 == r2 && c1 == c2) {
            return false; // Same position
        }

        if (chessboard[r2][c2] != null) {
            if (chessboard[r1][c1].getColor() == chessboard[r2][c2].getColor())
            {
                return false;
            }
        }

        if (r1 == r2) { // Check for horizontal movement
            return isHorizontalClear(r1, c1, c2, chessboard);
        }

        if (c1 == c2) { // Check for vertical movement
            return isVerticalClear(r1, c1, r2, chessboard);
        }

        if (Math.abs(r1 - r2) == Math.abs(c1 - c2)) { // Check for diagonal movement
            return isDiagonalClear(r1, c1, r2, c2, chessboard);
        }

        return false; // Queen cannot move to the specified position
    }

    private boolean isHorizontalClear(int r, int c1, int c2, Piece[][] chessboard) {
        int minC = Math.min(c1, c2);
        int maxC = Math.max(c1, c2);
        for (int i = minC + 1; i < maxC; i++) {
            if (chessboard[r][i] != null) {
                return false; // Path is blocked lmao
            }
        }
        return true; // Path is clear oh
    }

    private boolean isVerticalClear(int r1, int c, int r2, Piece[][] chessboard) {
        int minR = Math.min(r1, r2);
        int maxR = Math.max(r1, r2);
        for (int i = minR + 1; i < maxR; i++) {
            if (chessboard[i][c] != null) {
                return false; // Path is blocked lmao
            }
        }
        return true; // Path is clear oh
    }

    private boolean isDiagonalClear(int r1, int c1, int r2, int c2, Piece[][] chessboard) {
        int dr = Integer.signum(r2 - r1);
        int dc = Integer.signum(c2 - c1);
        int r = r1 + dr;
        int c = c1 + dc;
        while (r != r2 || c != c2) {
            if (chessboard[r][c] != null) {
                return false; // Path is blocked lmao
            }
            r += dr;
            c += dc;
        }
        return true; // Path is clear oh
    }
}
