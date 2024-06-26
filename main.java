import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

// P1: No está leyendo toda la entrada, osea, el último caracter lo ignora

public class main {
    public static void main(String[] args) {

        String filePath = "./prueba2.txt"; 
        String matrixFilePath = "./matriz-estados.txt"; 
        ReadFiles readFiles = new ReadFiles();

        try {
            String[][] matrix = readFiles.readFileToMatrix(matrixFilePath);
            // for (String[] row : matrix) {
            //     for (String value : row) {
            //         System.out.print(value + "\t");
            //     }
            //     System.out.println();
            // }
            String res = tokensString(filePath, matrix);
            //#3 Escribir los correos válidos en un archivo 
            PrintWriter writer = new PrintWriter("tokens.out");
            writer.println(res);
            writer.close();
            System.out.println(res);
        } catch (Exception e) {
            System.out.println("Error reading the file: ");
            System.out.println(e);
        }

    }

    public static String tokensString(String inputFile, String[][] matrix){
        String tokens = "";
    
        int indexLetra = 1;
        int indexEstado = 1;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line;

            while ((line = reader.readLine()) != null) {
                for (int column = 0; column < line.length(); column++) {
                    String actualChar = line.charAt(column)+"";
                    // Se reemplazó la E por el valor literal de " " ya que JAVA omitía el espacio en blanco asignado a actualChar
                    if(actualChar.equals(" ")){
                        actualChar = "E";
                    }
                    while (!matrix[0][indexLetra].equals(actualChar)) {
                        indexLetra++;
                    }
                    // Se ha encontrado un token
                    if(!matrix[indexEstado][indexLetra].equals("-1") && isFinalState(matrix[indexEstado][indexLetra])){
                        tokens += matrix[indexEstado][indexLetra]+" ";
                        indexEstado = 1;
                    }else{
                        // Si el estado es de error, lee todos los caractéres hasta encontrar vacíos
                        if(matrix[indexEstado][indexLetra].equals("-1")){
                            while (column < line.length() && line.charAt(column) != ' ') {
                                column++;
                            }
                            tokens += "ERROR"+" ";
                            indexEstado = 1;
                        }else{
                            // Debe continuar leyendo los caracteres
                            String indexEstadoSearched = matrix[indexEstado][indexLetra];
                            indexEstado = findRealStateIndex(indexEstadoSearched, matrix);
                            // System.out.println("Busqueda del siguiente estado\nValor:"+indexEstado);
                        }
                    }
                    indexLetra = 1;

                }
                tokens += "\n";
            }
            reader.close();
            if(tokens.length() == 0) return "0";
            return tokens;
        } catch (IOException e) {
            throw new Error(e);
            //e.printStackTrace();
        }
    }

    public static boolean isFinalState(String value){
        int numValue = Integer.valueOf(value);
        return numValue % 5 == 0 && numValue >= 1000 ;
    }

    public static int findRealStateIndex(String indexSearched, String matrix[][]){
        int fila = 0;
        while (matrix[fila][0] != null && !matrix[fila][0].equals(indexSearched)) {
            fila++;
        }
        return fila;
    }
}
