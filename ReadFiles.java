import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFiles {

    public static String[][] readFileToMatrix(String filePath) throws IOException {
        List<String[]> lines = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
        String line;

        while ((line = br.readLine()) != null) {
            // Divide la línea en partes separadas por tabulación
            String[] values = line.split("\t");
            lines.add(values);
            // System.out.println(line);
            // System.out.println("---------------");
        }
        br.close();

        // Determina las dimensiones de la matriz
        int numRows = lines.size();
        int numCols = lines.get(0).length;

        // Crea la matriz
        String[][] matrix = new String[numRows][numCols];

        // Llena la matriz con los valores del archivo
        for (int i = 0; i < numRows; i++) {
            String[] row = lines.get(i);
            for (int j = 0; j < numCols; j++) {
                matrix[i][j] = row[j];
            }
        }

        return matrix;
    }

}
