
public class main {
    public static void main(String[] args) {


        String filePath = "./matriz-estados.txt"; // Reemplaza con la ruta de tu archivo
        ReadFiles readFiles = new ReadFiles();

        try {
            String[][] matrix = readFiles.readFileToMatrix(filePath);
            for (String[] row : matrix) {
                for (String value : row) {
                    System.out.print(value + "\t");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println("Error reading the file: ");
            System.out.println(e);
        }


    }

    public static String tokensString(String inputFile, String[][] matrix){

        return "";
    }
}
