import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String[] products = {"Хлеб", "Молоко", "Масло"};
        int[] price = {100, 200, 300};
        int[] productBasket = new int[products.length];

        printProductList(products, price);

        while (true) {
            System.out.println("Выберите товар и количество или введите `end`");
            String product = scanner.nextLine();
            if (product.equals("end")) {
                break;
            } else {
                int[] inputPair = parseUserInput(product);
                if (inputPair[0] == -1 || inputPair[1] == -1) {
                    continue;
                }
                int productIndex = inputPair[0] - 1;
                int productQuantity = inputPair[1];

                if (productIndex >= 0 && productIndex < products.length) {
                    productBasket[productIndex] += productQuantity;
                } else {
                    System.out.println("Продукт с таким номером не найден");
                }
            }
        }
        printCompletedBasket(products, price, productBasket);
    }


    private static int[] parseUserInput(String inputFromUser) {
        String[] pair = inputFromUser.split(" ");
        int[] userInput = new int[2];
        if (pair.length < 2) {
            System.out.println("Вы забыли ввести код товара или количество");
            userInput[0] = -1;
            userInput[1] = -1;
            return userInput;
        }
        userInput[0] = Integer.parseInt(pair[0]);
        userInput[1] = Integer.parseInt(pair[1]);
        return userInput;
    }

    private static void printCompletedBasket(String[] products, int[] price, int[] basket) {
        System.out.println("\nВаша корзина: ");
        int totalSum = 0;

        for (int i = 0; i < products.length; i++) {
            int count = basket[i];
            if (count > 0) {
                int currentPrice = price[i];
                int currentSum = currentPrice * count;
                totalSum += currentSum;
                System.out.printf("%s %d шт %d руб/шт %d руб в сумме\n", products[i], count, currentPrice, currentSum);
            }
        }
        System.out.printf("Итого: %d руб\n", totalSum);
    }

    private static void printProductList(String[] products, int[] price) {
        System.out.println("Список возможных товаров для покупки:");
        int index = 1;
        for (String product : products) {
            System.out.printf("%d. %s - %d руб/шт\n", index, product, price[index - 1]);
            index++;
        }
    }
}
