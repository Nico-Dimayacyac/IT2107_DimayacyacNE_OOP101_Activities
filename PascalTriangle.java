import java.util.Scanner;

public class PascalTriangle {
    public static void main(String[]args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int rows = input.nextInt();

        int number;

        for (int i = 0; i < rows; i++) {
            number = 1;
            for (int j = 1; j <= rows - i; j++) {
                System.out.printf(" ");
            }
        for (int j = 0; j <= i; j++) {
            System.out.printf(number + " ");
            number = number * (i - j) / (j + 1);
            }

            System.out.println();
        }

        input.close();
    }
}