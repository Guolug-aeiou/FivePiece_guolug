package FivePiece.util;

import FivePiece.Init.ChessPieces;

public class Check {
    /**
     * 水平扫描
     *
     * @param chessPieces 一个棋子对象
     * @return 是否连着5个棋子
     */
    public static int level(ChessPieces[][] chessPieces) {
        int countBlack;
        int countWhite;
        for (int i = 0; i < chessPieces.length; i++) {
            countWhite = 0;
            countBlack = 0;
            for (int j = 0; j < chessPieces[0].length; j++) {
                if (chessPieces[i][j].getIsCamp() == 1) {
                    //黑方
                    countBlack++;
                    countWhite = 0;
                    if (countBlack == 5) {
                        return 1;
                    }
                } else if (chessPieces[i][j].getIsCamp() == 2) {
                    //黑方
                    countBlack = 0;
                    countWhite++;
                    if (countWhite == 5) {
                        return 2;
                    }
                } else {
                    countWhite = 0;
                    countBlack = 0;
                }
            }
        }
        return 0;
    }

    public static int vertical(ChessPieces[][] chessPieces) {
        int countBlack;
        int countWhite;
        for (int j = 0; j < chessPieces[0].length; j++) {
            countWhite = 0;
            countBlack = 0;
            for (int i = 0; i < chessPieces.length; i++) {
                if (chessPieces[i][j].getIsCamp() == 1) {
                    // 黑方
                    countBlack++;
                    countWhite = 0;
                    if (countBlack == 5) {
                        return 1;
                    }
                } else if (chessPieces[i][j].getIsCamp() == 2) {
                    // 白方
                    countBlack = 0;
                    countWhite++;
                    if (countWhite == 5) {
                        return 2;
                    }
                } else {
                    countWhite = 0;
                    countBlack = 0;
                }
            }
        }
        return 0;
    }

    /**
     * 主对角线检测（左上到右下）
     */
    public static int leadingDiagonal(ChessPieces[][] chessPieces) {
        int rows = chessPieces.length;
        int cols = chessPieces[0].length;
        
        // 遍历所有可能的起始点
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i > rows - 5 || j > cols - 5) continue; // 如果剩余空间不足5个则跳过
                
                int currentPlayer = chessPieces[i][j].getIsCamp();
                if (currentPlayer == 0) continue; // 空位跳过
                
                // 检查连续5个是否相同
                boolean win = true;
                for (int k = 1; k < 5; k++) {
                    if (chessPieces[i + k][j + k].getIsCamp() != currentPlayer) {
                        win = false;
                        break;
                    }
                }
                if (win) return currentPlayer;
            }
        }
        return 0;
    }

    /**
     * 副对角线检测（右上到左下）
     */
    public static int counterDiagonal(ChessPieces[][] chessPieces) {
        int rows = chessPieces.length;
        int cols = chessPieces[0].length;
        
        // 遍历所有可能的起始点
        for (int i = 0; i < rows; i++) {
            for (int j = cols - 1; j >= 0; j--) {
                if (i > rows - 5 || j < 4) continue; // 如果剩余空间不足5个则跳过
                
                int currentPlayer = chessPieces[i][j].getIsCamp();
                if (currentPlayer == 0) continue; // 空位跳过
                
                // 检查连续5个是否相同
                boolean win = true;
                for (int k = 1; k < 5; k++) {
                    if (chessPieces[i + k][j - k].getIsCamp() != currentPlayer) {
                        win = false;
                        break;
                    }
                }
                if (win) return currentPlayer;
            }
        }
        return 0;
    }

    /**
     * 检查是否获胜的总方法
     */
    public static int checkWin(ChessPieces[][] chessPieces) {
        int result;
        
        // 依次检查四个方向
        if ((result = level(chessPieces)) != 0) return result;
        if ((result = vertical(chessPieces)) != 0) return result;
        if ((result = leadingDiagonal(chessPieces)) != 0) return result;
        if ((result = counterDiagonal(chessPieces)) != 0) return result;
        
        return 0; // 没有获胜
    }
}
