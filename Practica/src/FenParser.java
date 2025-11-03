public class FenParser {

    private static boolean esPieza(char c) {
        String piezas = "KQRBNPkqrbnp";
        if (piezas.indexOf(c) != -1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Parsea y valida la FEN completa y devuelve la matriz 8x8 lista para dibujar.
     */
    public static char[][] parser(String fen) throws Exception {
        if (fen == null) {
            throw new Exception("La cadena FEN es nula");
        }
        fen = fen.trim();
        if (fen.equals("")) {
            throw new Exception("La cadena FEN está vacía");
        }

        // separar por espacios (uno o más)
        String[] campos = fen.split("\\s+");
        if (campos.length != 6) {
            throw new Exception("La FEN debe tener 6 partes separadas por espacios");
        }

        String piezas = campos[0]; // colocación de piezas
        String turno = campos[1]; // w|b
        String enroque = campos[2]; // KQkq|-
        String enPassant = campos[3]; // a3|a6|-
        String medioMov = campos[4]; // >= 0
        String movCompleto = campos[5]; // >= 1

        // 1) Parsear la colocación de piezas y construir el tablero
        String[] filas = piezas.split("/");
        if (filas.length != 8) {
            throw new Exception("Debe haber exactamente 8 filas separadas por '/'");
        }

        char[][] board = new char[8][8]; // fila 0 = rank 8 (arriba), fila 7 = rank 1 (abajo)

        for (int r = 0; r < 8; r++) {
            String fila = filas[r];
            int c = 0;

            for (int i = 0; i < fila.length(); i++) {
                char ch = fila.charAt(i);

                if (Character.isDigit(ch)) {
                    int n = Character.getNumericValue(ch);
                    for (int k = 0; k < n; k++) {
                        if (c >= 8) {
                            throw new Exception("La fila " + (r + 1) + " tiene más de 8 casillas");
                        }
                        board[r][c] = ' ';
                        c++;
                    }
                } else if (esPieza(ch)) {
                    if (c >= 8) {
                        throw new Exception("La fila " + (r + 1) + " tiene más de 8 casillas");
                    }
                    board[r][c] = ch;
                    c++;
                } else {
                    throw new Exception("Carácter inválido en piezas: " + ch + " (fila " + (r + 1) + ")");
                }
            }

            if (c != 8) {
                throw new Exception("La fila " + (r + 1) + " no suma 8 casillas");
            }
        }

        // 2) Validar turno
        if (!turno.equals("w") && !turno.equals("b")) {
            throw new Exception("Turno inválido: debe ser 'w' o 'b'");
        }

        // 3) Validar enroque
        if (!enroque.equals("-")) {
            for (int i = 0; i < enroque.length(); i++) {
                char ch = enroque.charAt(i);
                if ("KQkq".indexOf(ch) == -1) {
                    throw new Exception("Enroque inválido: " + ch);
                }
            }
        }

        // 4) Validar en passant
        if (!enPassant.equals("-")) {
            if (enPassant.length() != 2) {
                throw new Exception("En Passant inválido: " + enPassant);
            }
            char file = enPassant.charAt(0); // a..h
            char rank = enPassant.charAt(1); // 3 o 6
            if ("abcdefgh".indexOf(file) == -1 || "36".indexOf(rank) == -1) {
                throw new Exception("En Passant inválido: " + enPassant);
            }
        }

        // 5) Validar contadores
        int half;
        int full;
        try {
            half = Integer.parseInt(medioMov);
            full = Integer.parseInt(movCompleto);
        } catch (NumberFormatException e) {
            throw new Exception("Halfmove o Fullmove deben ser números");
        }
        if (half < 0) {
            throw new Exception("Halfmove clock debe ser >= 0");
        }
        if (full < 1) {
            throw new Exception("Fullmove counter debe ser >= 1");
        }

        // todo ok → devolver la matriz
        return board;
    }
}
