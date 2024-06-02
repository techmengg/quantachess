package main.model;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.io.File;
import javax.imageio.ImageIO;

public class ChessGUI {

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private Image[][] chessPieceImages = new Image[2][6];
    private JPanel chessBoard;
    private final JLabel message = new JLabel("Chess Champ is ready to play!");
    private static final String COLS = "ABCDEFGH";
    private static final int QUEEN = 0, KING = 1, ROOK = 2, KNIGHT = 3, BISHOP = 4, PAWN = 5;
    private static final int[] STARTING_ROW = { ROOK, KNIGHT, BISHOP, KING, QUEEN, BISHOP, KNIGHT, ROOK };
    private static final int BLACK = 0, WHITE = 1;
    private static Board chess = new Board();
    private static boolean turn = true;

    private boolean isDarkMode = true; // This should set default theme color to white
    private JButton selectedButton = null;  // To keep track of the selected piece
    private int selectedRow = -1;  // Row of the selected piece
    private int selectedCol = -1;  // Column of the selected piece

    private JButton newButton;
    private JButton themeButton;

    ChessGUI() {
        initializeGui();
        toggleTheme(); // Set dark theme as default
    }

    public final void initializeGui() {
        // Create the images for the chess pieces
        createImages();

        // Set up the main GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        gui.setBackground(new Color(43, 43, 43));
        
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        tools.setBackground(new Color(60, 63, 65));
        tools.setForeground(Color.WHITE);
        gui.add(tools, BorderLayout.PAGE_START);

        newButton = createStyledButton("New", e -> setupNewGame());
        themeButton = createStyledButton("Theme", e -> toggleTheme());

        tools.add(newButton);
        tools.add(themeButton);
        
        tools.addSeparator();
        message.setForeground(Color.BLACK);
        tools.add(message);

        chessBoard = new JPanel(new GridLayout(0, 9)) {
            @Override
            public final Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension((int) d.getWidth(), (int) d.getHeight());
                } else if (c.getWidth() > d.getWidth() && c.getHeight() > d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                int s = Math.min(w, h);
                return new Dimension(s, s);
            }
        };
        chessBoard.setBorder(new CompoundBorder(new EmptyBorder(8, 8, 8, 8), new LineBorder(Color.DARK_GRAY)));
        chessBoard.setBackground(new Color(43, 43, 43));
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.setBackground(new Color(43, 43, 43));
        boardConstrain.add(chessBoard);
        gui.add(boardConstrain);

        Insets buttonMargin = new Insets(0, 0, 0, 0);
        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                b.setFocusPainted(false);
                b.setOpaque(true);
                b.setBorderPainted(false);
                ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((jj % 2 == 1 && ii % 2 == 1) || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(new Color(170, 162, 58));
                } else {
                    b.setBackground(new Color(58, 95, 170));
                }
                final int x = ii;
                final int y = jj;

                b.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttonClicked(b, x, y);
                    }
                });
                chessBoardSquares[jj][ii] = b;
            }
        }

        chessBoard.add(new JLabel(""));
        for (int ii = 0; ii < 8; ii++) {
            JLabel label = new JLabel(COLS.substring(ii, ii + 1), SwingConstants.CENTER);
            label.setForeground(Color.WHITE);
            chessBoard.add(label);
        }
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                switch (jj) {
                    case 0:
                        JLabel rowLabel = new JLabel("" + (9 - (ii + 1)), SwingConstants.CENTER);
                        rowLabel.setForeground(Color.WHITE);
                        chessBoard.add(rowLabel);
                    default:
                        chessBoard.add(chessBoardSquares[jj][ii]);
                }
            }
        }
    }

    private JButton createStyledButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        button.addActionListener(actionListener);
        return button;
    }

    private void buttonClicked(JButton b, int row, int col) {
        if (selectedButton == null) {
            if (chess.getChessBoard()[row][col] != null) {
                selectedButton = b;
                selectedRow = row;
                selectedCol = col;
            }
        } else if (chess.validate(selectedRow, selectedCol, row, col, chess.getChessBoard(), turn)) {
            chessBoardSquares[col][row].setIcon(selectedButton.getIcon());
            selectedButton.setIcon(new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB)));
            int promote = chess.promote(row, col, chess.getChessBoard());
            if (promote == 1) {
                chessBoardSquares[col][row].setIcon(new ImageIcon(chessPieceImages[WHITE][KING]));
            } else if (promote == 2) {
                chessBoardSquares[col][row].setIcon(new ImageIcon(chessPieceImages[BLACK][KING]));
            }
            selectedButton = null;
            selectedRow = -1;
            selectedCol = -1;
            turn = !turn;
        } else {
            selectedButton = null;
            selectedRow = -1;
            selectedCol = -1;
        }
    }

    public final JComponent getGui() {
        return gui;
    }

    private void createImages() {
        try {
            File file = new File("main/img/pieces.png");
            BufferedImage bi = ImageIO.read(file);
            for (int ii = 0; ii < 2; ii++) {
                for (int jj = 0; jj < 6; jj++) {
                    chessPieceImages[ii][jj] = bi.getSubimage(jj * 64, ii * 64, 64, 64);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void setupNewGame() {
        message.setText("Make your move!");

        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                chessBoardSquares[ii][jj].setIcon(null);
            }
        }

        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
            chessBoardSquares[ii][0].setIcon(new ImageIcon(chessPieceImages[BLACK][STARTING_ROW[ii]]));
        }
        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
            chessBoardSquares[ii][1].setIcon(new ImageIcon(chessPieceImages[BLACK][PAWN]));
        }

        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
            chessBoardSquares[ii][6].setIcon(new ImageIcon(chessPieceImages[WHITE][PAWN]));
        }
        for (int ii = 0; ii < STARTING_ROW.length; ii++) {
            chessBoardSquares[ii][7].setIcon(new ImageIcon(chessPieceImages[WHITE][STARTING_ROW[ii]]));
        }

        turn = true;
        selectedButton = null;
        selectedRow = -1;
        selectedCol = -1;

        chess = new Board();
    }

    private void toggleTheme() {
        Color bgColor, darkSquareColor, lightSquareColor, toolbarColor, textColor, buttonBgColor, buttonFgColor;

        if (isDarkMode) {
            bgColor = new Color(245, 245, 245);
            darkSquareColor = new Color(192, 192, 192);
            lightSquareColor = new Color(255, 255, 255);
            toolbarColor = new Color(225, 225, 225);
            textColor = Color.BLACK;
            buttonBgColor = new Color(225, 225, 225);
            buttonFgColor = Color.BLACK;
        } else {
            bgColor = new Color(43, 43, 43);
            darkSquareColor = new Color(64, 64, 64);
            lightSquareColor = new Color(32, 32, 32);
            toolbarColor = new Color(60, 63, 65);
            textColor = Color.WHITE;
            buttonBgColor = new Color(60, 63, 65);
            buttonFgColor = Color.WHITE;
        }

        gui.setBackground(bgColor);
        chessBoard.setBackground(bgColor);

        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                if ((jj % 2 == 1 && ii % 2 == 1) || (jj % 2 == 0 && ii % 2 == 0)) {
                    chessBoardSquares[jj][ii].setBackground(darkSquareColor);
                } else {
                    chessBoardSquares[jj][ii].setBackground(lightSquareColor);
                }
            }
        }

        for (Component c : chessBoard.getComponents()) {
            if (c instanceof JLabel) {
                c.setForeground(textColor);
            }
        }

        for (Component c : gui.getComponents()) {
            if (c instanceof JToolBar) {
                JToolBar toolbar = (JToolBar) c;
                toolbar.setBackground(toolbarColor);
                for (Component btn : toolbar.getComponents()) {
                    if (btn instanceof JButton) {
                        btn.setBackground(buttonBgColor);
                        btn.setForeground(buttonFgColor);
                    }
                }
            }
        }

        newButton.setBackground(buttonBgColor);
        newButton.setForeground(buttonFgColor);
        themeButton.setBackground(buttonBgColor);
        themeButton.setForeground(buttonFgColor);

        message.setForeground(textColor);
        isDarkMode = !isDarkMode;
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                ChessGUI cg = new ChessGUI();
                JFrame f = new JFrame("ByteBoard");
                f.add(cg.getGui());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);
                f.pack();
                f.setMinimumSize(f.getSize());
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}