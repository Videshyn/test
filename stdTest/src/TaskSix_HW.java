import java.io.*;
import java.util.Scanner;

public class TaskSix_HW {
    public static void main(String[] args) throws IOException {
        double[][] arr = createMas();
        printMas(arr);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file name:");
        String path = scanner.nextLine();
        try {
            writeMasInFile(path, arr);
        } catch (IOException ex) {
            System.out.println("Problem with file");
        } finally {
            scanner.close();
        }

        int N1 = 0, M1 = 0;
        try (FileReader fr = new FileReader(path)){
            String strN = scanner.next();
            String strM = scanner.next();
            N1 = Integer.parseInt(strN);
            M1 = Integer.parseInt(strM);
            System.out.println("N1 = " + N1 + " M1 = " + M1);
        } catch (IOException e) {
            System.out.println("Problem with file!");
        } finally {
            scanner.close();
        }

        try {
            double[][] arr_copy = readMasFromFile(path);
            for (int i = 0, n = arr_copy.length; i < n; i ++){
                for (int j = 0, l = arr_copy[0].length; j < l; j ++){
                    System.out.printf("%.4f \t", arr_copy[i][j]);
                }
                System.out.println();
            }
        }catch (IOException ex){
            System.out.println("Problem with reading file");
        }
        readMasFromFile(path);

    }

    public static double[][] createMas() {
        Scanner scanner = new Scanner(System.in);
        int N, M;
        while (true) {
            try {
                System.out.println("Enter N:");
                N = Integer.parseInt(scanner.nextLine());
                if (N <= 0) throw new Exception();
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Only numbers!");
            } catch (Exception e) {
                System.out.println("WRONG NUMBER!");
            }
        }

        while (true) {
            try {
                System.out.println("Enter M:");
                M = Integer.parseInt(scanner.nextLine());
                if (M <= 0) throw new Exception();
                break;
            } catch (NumberFormatException ex) {
                System.out.println("Only numbers!");
            } catch (Exception e) {
                System.out.println("WRONG NUMBER!");
            }
        }
        double[][] mas = new double[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                mas[i][j] = (-100 + Math.random() * (201));
                if (mas[i][j] > 100.0) mas[i][j] = 100.0;
            }
        }
        return mas;
    }

    public static void printMas(double[][] mas) {
        for (int i = 0, N = mas.length; i < N; i++) {
            for (int j = 0, M = mas[i].length; j < M; j++) {
                System.out.printf("%.4f \t", mas[i][j]);
            }
            System.out.println();
        }
    }

    public static void writeMasInFile(String path, double[][] mas) throws IOException {
        File file = new File(path);
        FileWriter fw = new FileWriter(file, false);
        fw.write(mas.length + "\t" + mas[0].length + "\n");
        for (int i = 0, N = mas.length; i < N; i++) {
            for (int j = 0, M = mas[i].length; j < M; j++) {
                fw.write(mas[i][j] + "\t");
            }
            fw.write("\n");
        }
        fw.close();
    }

    public static double[][] readMasFromFile(String path) throws IOException {
        FileReader fr = new FileReader(path);
        Scanner scanner = new Scanner(fr);
        int N1 = Integer.parseInt(scanner.next());
        int M1 = Integer.parseInt(scanner.next());
        double[][] arr_copy = new double[N1][M1];
        while (scanner.hasNext()) {
            for (int i = 0; i < N1; i++) {
                for (int j = 0; j < M1; j++) {
                    arr_copy[i][j] = Double.parseDouble(scanner.next());
                }
            }
        }
        fr.close();
        scanner.close();
        return arr_copy;
    }
}
