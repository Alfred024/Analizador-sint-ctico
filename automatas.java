import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class automatas {
    public static void main(String[] args) throws FileNotFoundException {
        //#1 Convertir el .txt a la matriz de estados
        String estadosFile = "matriz-estados.txt";
        char[][] matrizEstados = populatematrizEstados(estadosFile);

        //#2 Obtener el String con los emails válidos
        String correosValidos = automataEmails("automata01.in.txt", matrizEstados);   
        System.out.println(correosValidos); 

        //#3 Escribir los correos válidos en un archivo 
        PrintWriter writer = new PrintWriter("automata01.out");
        writer.println(correosValidos);
        writer.close();
    }
    
    public static String automataEmails(String emailsFile, char[][] matrizEstados){
        String correoValido = "";
        String correosValidos = "";
    
        int indexChar = 1;
        int indexEstado = 1;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(emailsFile));
            String line;

            while ((line = reader.readLine()) != null) {
                for (int column = 0; column < line.length(); column++) {
                    char charVal = line.charAt(column);
                    while (matrizEstados[0][indexChar] != charVal) {
                        indexChar++;
                    }
            
                    if(matrizEstados[indexEstado][indexChar] != 48 && matrizEstados[indexEstado][indexChar] != 57){
                        correoValido += charVal;
                    }else{
                        correoValido = "";
                    }
                    
                    indexEstado = (matrizEstados[indexEstado][indexChar]-48)+1; // El índice de los estados empieza una posición después del inicio
        
                    if(indexEstado == 9){
                        correosValidos += correoValido+"\n";
                        correoValido = "";
                        indexEstado = 1;
                    }
        
                    if(indexEstado == 10){
                        while (column < line.length() && line.charAt(column) != ' ') {
                            column++;
                        }
                        indexEstado = 1;
                    }
                    indexChar = 1;
                }
            }
            reader.close();
            if(correosValidos.length() == 0) return "0";
            return correosValidos;
        } catch (IOException e) {
            throw new Error(e);
            //e.printStackTrace();
        }
    }

    // TODO: Debe calcular el tamaño de la matríz dinámicamente
    public static char[][] populatematrizEstados(String fileName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            int numRows = 9; 
            int numCols = 45; 

            char[][] matrizEstados = new char[numRows][numCols];

            String line;
            int row = 0;
            while ((line = reader.readLine()) != null && row < numRows) {
                for (int column = 0; column < numCols && column < line.length(); column++) {
                    matrizEstados[row][column] = line.charAt(column);
                }
                row++;
            }
            reader.close();

            return matrizEstados;
            
        } catch (IOException e) {
            throw new Error(e);
            //e.printStackTrace();
        }
    }

    public static String fileToString(String fileName){
        StringBuilder fileStringied = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String linea;
            while ((linea = reader.readLine()) != null) {
                fileStringied.append(linea).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            throw new Error(e);
            //e.printStackTrace();
        }
        return fileStringied.toString();
    }
}
