import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static String filename = "Check.txt";

    public static Scanner sc = new Scanner(System.in);
    public static String[] categories = new String[10];
    public static String[] subcategories = new String[10];
    public static String[][] prices = new String[10][10];
    private static String password = "pass";
    public static String WelcomeMessage = "";

    // Основной метод, запускающий выбор роли пользователя
    public static void main(String[] args) {
        roleChoose();

    }

    // Метод для выбора роли пользователя (Администратор, Клиент или Выход)
    public static void roleChoose() {
        boolean validInput = false;
        while (!validInput) {
            System.out.println("Welcome to the \"Magnum\" Supermarket\nChoose user you want to log in");
            System.out.println("\n1.Admin \n2.Client\n3.Exit");
            System.out.print("Choose Role: ");

            int roleChoose = sc.nextInt();
            sc.nextLine();
            switch (roleChoose) {
                case 1:
                    System.out.println("Enter admin password\n");
                    CheckAdminPassword();
                    validInput = true;
                    break;
                case 2:
                    ClientMode();
                    validInput = true;
                    break;
                case 3:
                    System.out.println("Goodbye! See you soon");
                    validInput = true;
                    break;
                default:
                    System.out.println("Command not found, please enter an existing command");
                    break;
            }
        }
    }


    // Метод для проверки пароля администратора
    public static void CheckAdminPassword() {
        String pass = sc.next();
        if (pass.equals(password)) {
            AdminMenu();
        } else if (pass.equals("0")) {
            roleChoose();
        } else {
            System.out.println("Please try again. Write 0 to back in login menu\n");
            CheckAdminPassword();
        }
    }


    // Метод для работы в режиме клиента
    public static void ClientMode() {
        System.out.println(WelcomeMessage);
        System.out.println("Client panel:\n0. Back to user select\n1. Show menu\n2. Add to Cart\n3. Show Cart\n4. Buy cart\n5. Add payment method");
        boolean valincheck = false;
        while (!valincheck) {
            switch (sc.nextInt()) {
                case 0:
                    roleChoose();
                    valincheck = true;
                    break;
                case 1:
                    valincheck = true;
                    ShowMenuClient();
                    break;
                case 2:
                    AddToCart();
                    valincheck = true;
                    break;
                case 3:
                    ShowCart();
                    valincheck = true;
                    break;
                case 5:
                    addPaymentbank();
                    valincheck = true;
                    break;
                case 4:
                    BuyCart();
                    valincheck = true;
                    break;
                default:
                    System.out.println("Try again");
                    ClientMode();
                    break;
            }
        }
    }

    // Метод для отображения меню клиента
    public static void ShowMenuClient() {
        System.out.println("0. Exit menu");
        for (int i = 0; i < categories.length; i++) {
            if (categories[i] != null) {
                System.out.println((i + 1) + ". " + categories[i]);
            }
        }
        System.out.println("\nEnter the number of the category to view subcategories or 0 to exit:");
        int choice = sc.nextInt();
        if (choice == 0) {
            ClientMode();
        } else if (choice > 0 && choice <= categories.length && categories[choice - 1] != null) {
            ShowSubcategoriesClient(choice - 1);
        } else {
            System.out.println("Invalid choice. Please try again.");
            ShowMenuClient();
        }
    }

    // Метод для отображения подкатегорий клиента
    public static void ShowSubcategoriesClient(int categoryNumber) {
        System.out.println("0. Back to menu");
        if (subcategories[categoryNumber] != null && !subcategories[categoryNumber].isEmpty()) {
            String[] subcategoryArray = subcategories[categoryNumber].split(",");
            for (int j = 0; j < subcategoryArray.length; j++) {
                System.out.print((j + 1) + ". " + subcategoryArray[j].trim());
                if (prices[categoryNumber][j] != null) {
                    System.out.println(". Price: " + prices[categoryNumber][j] + " kzt");
                } else {
                    System.out.println();
                }
            }
        } else {
            System.out.println("No subcategories found");
        }
        System.out.println("\nEnter 0 to go back to the main menu:");
        int choice = sc.nextInt();
        if (choice == 0) {
            ShowMenuClient();
        } else {
            System.out.println("Invalid choice. Please try again");
            ShowSubcategoriesClient(categoryNumber);
        }
    }


    // Метод для отображения меню администратора
    public static void AdminMenu() {
        System.out.println("\nYou are welcome. \nWhat you want do today ");
        System.out.println("0. Back to user select\n1. Managing category \n2. Managing subcategory \n3. Managing Price\n4. Display Menu\n5. Change Welcome message");
        int selectorForAdminMenu = sc.nextInt();
        boolean isValid = false;
        while (!isValid) {
            switch (selectorForAdminMenu) {
                case 0:
                    isValid = true;
                    roleChoose();
                    break;
                case 1:
                    isValid = true;
                    CategoryManagment();
                    break;
                case 2:
                    isValid = true;
                    SubcategoryManagment();
                    break;
                case 3:
                    isValid = true;
                    PriceManagment();
                    break;
                case 4:
                    isValid = true;
                    ShowMenu();
                    break;
                case 5:
                    isValid = true;
                    MessageEditor();
                    break;
            }

        }
    }

    // Метод для управления категориями
    public static void CategoryManagment() {
        System.out.println("\nWhat we should do with categories today?\n");
        System.out.println("0. Back to user select\n1. Create new category \n2. Remove category\n3. Show all category\n");
        int chooseWhatToDoWithCategory = sc.nextInt();

        boolean isvalid = false;
        while (!isvalid) {
            switch (chooseWhatToDoWithCategory) {
                case 0:
                    isvalid = true;
                    roleChoose();
                    break;
                case 1:
                    isvalid = true;
                    CreatingNewCategory();
                    break;
                case 2:
                    isvalid = true;
                    RemoveCategory();
                    break;
                case 3:
                    isvalid = true;
                    ShowCategory();
                    break;
                case 4:
                    isvalid = true;
                    AdminMenu();
                    break;
                default:
                    System.out.println("Command not found please enter existing command");
                    break;
            }
        }
    }

    // Метод для создания новой категории
    public static void CreatingNewCategory() {
        sc.nextLine();
        System.out.println("Enter name of new category");
        String categoryInput = sc.nextLine();
        String[] newCategories = categoryInput.split(",");
        for (String category : newCategories) {
            category = category.trim();
            for (int i = 0; i < categories.length; i++) {
                if (categories[i] == null) {
                    categories[i] = category;
                    subcategories[i] = "";
                    break;
                }
            }
        }
        System.out.println("\nCategory was added \n What you want to do next");
        System.out.println("\n1. Add more category\n 2. Back to category management\n 3. Back to admin menu ");
        boolean ifError = false;
        while (!ifError) {
            switch (sc.nextInt()) {
                case 1:
                    CreatingNewCategory();
                    ifError = true;
                    break;
                case 2:
                    CategoryManagment();
                    ifError = true;
                    break;
                case 3:
                    AdminMenu();
                    ifError = true;
                    break;
                default:
                    System.out.println("Command not found please enter existing command");
            }
        }
    }

    // Метод для удаления категории
    public static void RemoveCategory() {
        sc.nextLine();
        System.out.print("Write name of category you want to delete. Write 0 to back in category management\n ");
        String removeCategoryName = sc.nextLine();
        if (removeCategoryName.equals("0")) {
            CategoryManagment();
        }
        boolean containsCategory = false;
        for (int i = 0; i < categories.length; i++) {
            if (removeCategoryName.equals(categories[i])) {
                categories[i] = null;
                subcategories[i] = null;
                prices[i] = null;
                containsCategory = true;
                System.out.println("\nCategory was Deleted");
                break;
            }
        }
        if (!containsCategory) {
            System.out.println("\nCategory not found\nPlease Try again\n");
            RemoveCategory();
        }
        CategoryManagment();
    }

    // Метод для отображения всех категорий
    public static void ShowCategory() {
        System.out.println();
        for (int i = 0; i < categories.length; i++) {
            if (categories[i] != null) {
                System.out.println((i + 1) + ". " + categories[i]);
                if (subcategories[i] != null && !subcategories[i].isEmpty()) {
                    System.out.println("   Subcategories: " + subcategories[i]);
                }
            }
        }
        System.out.println("\nWhat we should do next\n1.Back to category management\n2.Back to admin menu");

        boolean ifError = false;
        while (!ifError) {
            switch (sc.nextInt()) {
                case 1:
                    CategoryManagment();
                    ifError = true;
                    break;
                case 2:
                    AdminMenu();
                    ifError = true;
                    break;
                default:
                    System.out.println("Command not found please enter existing command");
                    break;
            }
        }
    }

    // Метод для управления подкатегориями
    public static void SubcategoryManagment() {
        System.out.println("\nWelcome to subcategory management what you want to change");
        System.out.println("\n0. Back to user select\n1. Attach new subcategory to Category\n2. Remove subcategory\n3. Show menu\n4. Back to admin menu");
        boolean catchinvalidinput = false;

        while (!catchinvalidinput) {
            switch (sc.nextInt()) {
                case 0:
                    catchinvalidinput = true;
                    roleChoose();
                    break;
                case 1:
                    CreatingSubcategory();
                    catchinvalidinput = true;
                    break;
                case 2:
                    RemoveSubcategory();
                    catchinvalidinput = true;
                    break;
                case 3:
                    ShowMenu();
                    catchinvalidinput = true;
                    break;
                case 4:
                    AdminMenu();
                    catchinvalidinput = true;
                    break;
                default:
                    System.out.println("Command not found please enter existing command");
                    break;
            }
        }
    }

    // Метод для создания новой подкатегории
    public static void CreatingSubcategory() {
        sc.nextLine();
        System.out.println("\nEnter the name of the category to which you want to attach");
        String category = sc.nextLine();
        boolean categoryExist = false;
        for (int i = 0; i < categories.length; i++) {
            if (category.equals(categories[i])) {
                categoryExist = true;
                System.out.println("\nCategory found!\nEnter what you want to add to the subcategory");
                String newSubcategory = sc.nextLine();
                if (subcategories[i] == null || subcategories[i].isEmpty()) {
                    subcategories[i] = newSubcategory;
                } else {
                    subcategories[i] += ", " + newSubcategory;
                }
                break;
            }
        }
        if (!categoryExist) {
            System.out.println("Category not found. Please try again.");
        }
        SubcategoryManagment();
    }

    // Метод для удаления подкатегории
    public static void RemoveSubcategory() {
        sc.nextLine();
        System.out.println("\nEnter the name of the category from which you want to remove a subcategory");
        String category = sc.nextLine();

        boolean categoryExist = false;

        for (int i = 0; i < categories.length; i++) {
            if (category.equals(categories[i])) {

                categoryExist = true;
                System.out.println("\nCategory found!\nEnter the name of the subcategory you want to remove");

                String subcategoryToRemove = sc.nextLine();

                if (subcategories[i] != null && !subcategories[i].isEmpty()) {
                    String[] subcategoryArray = subcategories[i].split(",");
                    StringBuilder newSubcategories = new StringBuilder();

                    boolean subcategoryFound = false;
                    for (String subcategory : subcategoryArray) {
                        if (!subcategory.trim().equals(subcategoryToRemove.trim())) {
                            if (!newSubcategories.isEmpty()) {
                                newSubcategories.append(", ");
                            }
                            newSubcategories.append(subcategory.trim());
                        } else {
                            subcategoryFound = true;
                        }
                    }

                    if (subcategoryFound) {
                        subcategories[i] = newSubcategories.toString();
                        System.out.println("Subcategory removed");
                    } else {
                        System.out.println("Subcategory not found");
                    }
                } else {
                    System.out.println("No subcategories found for this category");
                }
                break;
            }
        }
        if (!categoryExist) {
            System.out.println("Category not found. Please try again");
        }
        SubcategoryManagment();
    }

    // Метод для управления ценами
    public static void PriceManagment() {
        System.out.println("\nWelcome to price management what you want to change");
        System.out.println("\n0. Back to user select\n1. Attach new price to Subcategory\n2. Remove price\n3. Show menu\n4. Back to admin menu");
        boolean catchinvalidinput = false;

        while (!catchinvalidinput) {
            switch (sc.nextInt()) {
                case 0:
                    roleChoose();
                    catchinvalidinput = true;
                    break;
                case 1:
                    CreatingPrice();
                    catchinvalidinput = true;
                    break;
                case 2:
                    RemovePrice();
                    catchinvalidinput = true;
                    break;
                case 3:
                    ShowMenu();
                    catchinvalidinput = true;
                    break;
                case 4:
                    AdminMenu();
                    catchinvalidinput = true;
                    break;
                default:
                    System.out.println("Command not found please enter existing command");
                    break;
            }
        }
    }

    // Метод для добавления новой цены к подкатегории
    public static void CreatingPrice() {
        sc.nextLine();
        System.out.println("\nEnter the name of the category to which the subcategory belongs");
        String category = sc.nextLine();

        boolean categoryExist = false;

        for (int i = 0; i < categories.length; i++) {
            if (category.equals(categories[i])) {
                categoryExist = true;

                System.out.println("\nCategory found!\nEnter the name of the subcategory to which you want to attach a price");
                String subcategory = sc.nextLine();
                String[] subcategoryArray = subcategories[i].split(",");
                boolean subcategoryExist = false;

                for (int j = 0; j < subcategoryArray.length; j++) {
                    if (subcategoryArray[j].trim().equals(subcategory.trim())) {
                        subcategoryExist = true;
                        System.out.println("\nSubcategory found!\nEnter the price");
                        String price = sc.nextLine();
                        prices[i][j] = price;
                        break;
                    }
                }
                if (!subcategoryExist) {
                    System.out.println("Subcategory not found. Please try again");
                }
                break;
            }
        }
        if (!categoryExist) {
            System.out.println("Category not found. Please try again");
        }
        PriceManagment();
    }

    // Метод для удаления цены из подкатегории
    public static void RemovePrice() {
        sc.nextLine();
        System.out.println("\nEnter the name of the category to which the subcategory belongs");
        String category = sc.nextLine();
        boolean categoryExist = false;
        for (int i = 0; i < categories.length; i++) {
            if (category.equals(categories[i])) {
                categoryExist = true;
                System.out.println("\nCategory found!\nEnter the name of the subcategory from which you want to remove the price");
                String subcategory = sc.nextLine();
                String[] subcategoryArray = subcategories[i].split(",");
                boolean subcategoryExist = false;
                for (int j = 0; j < subcategoryArray.length; j++) {
                    if (subcategoryArray[j].trim().equals(subcategory.trim())) {
                        subcategoryExist = true;
                        prices[i][j] = null;
                        System.out.println("Price removed.");
                        break;
                    }
                }
                if (!subcategoryExist) {
                    System.out.println("Subcategory not found. Please try again.");
                }
                break;
            }
        }
        if (!categoryExist) {
            System.out.println("Category not found. Please try again.");
        }
        PriceManagment();
    }

    // Метод для отображения меню администратора
    public static void ShowMenu() {
        System.out.println("0. Exit menu");
        for (int i = 0; i < categories.length; i++) {
            if (categories[i] != null) {
                System.out.println((i + 1) + ". " + categories[i]);
            }
        }
        System.out.println("\nEnter the number of the category to view subcategories or 0 to exit:");
        int choice = sc.nextInt();
        if (choice == 0) {
            AdminMenu();
        } else if (choice > 0 && choice <= categories.length && categories[choice - 1] != null) {
            ShowSubcategories(choice - 1);
        } else {
            System.out.println("Invalid choice. Please try again.");
            ShowMenu();
        }
    }

    // Метод для отображения подкатегорий администратора
    public static void ShowSubcategories(int CategoryNumber) {
        System.out.println("0. Back to menu");
        if (subcategories[CategoryNumber] != null && !subcategories[CategoryNumber].isEmpty()) {
            String[] subcategoryArray = subcategories[CategoryNumber].split(",");
            for (int j = 0; j < subcategoryArray.length; j++) {
                System.out.print((j + 1) + ". " + subcategoryArray[j].trim());
                if (prices[CategoryNumber][j] != null) {
                    System.out.println(". Price: " + prices[CategoryNumber][j] + " kzt");
                } else {
                    System.out.println();
                }
            }
        } else {
            System.out.println("No subcategories found.");
        }
        System.out.println("\nEnter 0 to go back to the main menu:");
        int choice = sc.nextInt();
        if (choice == 0) {
            ShowMenu();
        } else {
            System.out.println("Invalid choice. Please try again.");
            ShowSubcategories(CategoryNumber);
        }
    }

    // Метод для редактирования приветственного сообщения
    public static void MessageEditor() {
        sc.nextLine();
        System.out.println("Enter new welcome message");
        WelcomeMessage = sc.nextLine();
        System.out.println("Done!");
        AdminMenu();
    }


    //Команды для корзины

    public static String[] cart = new String[10];
    public static int cartIndex = 0;
    public static double totalSum = 0.0;

    // Метод для добавления товаров в корзину
    public static void AddToCart() {

        //проверяется номер категории
        System.out.println("\nWrite 0 to get back in Menu\nChoose number of category:");
        for (int i = 0; i < categories.length; i++) {
            if (categories[i] != null) {
                System.out.println((i + 1) + ". " + categories[i]);
            }
        }
        int categoryChoice = sc.nextInt() - 1;
        //выход из выбора
        if (categoryChoice == -1) {
            ClientMode();

            //если категория действительно существует
        } else if (categoryChoice >= 0 && categoryChoice < categories.length && categories[categoryChoice] != null) {
            System.out.println("\nWrite 0 to return back in menu\nEnter index of subcategory:");
            String[] subcategoryArray = subcategories[categoryChoice].split(",");
            for (int j = 0; j < subcategoryArray.length; j++) {
                System.out.print((j + 1) + ". " + subcategoryArray[j].trim());
                if (prices[categoryChoice][j] != null) {
                    System.out.println(". Price: " + prices[categoryChoice][j] + " kzt");
                } else {
                    System.out.println();
                }
            }
            int subcategoryChoice = sc.nextInt() - 1;
            if (subcategoryChoice == -1) {
                ClientMode();
            } else if (subcategoryChoice >= 0 && subcategoryChoice < subcategoryArray.length) {
                if (subcategoryArray[subcategoryChoice] != null && prices[categoryChoice][subcategoryChoice] != null) {
                    System.out.println("Enter quantity:");
                    int quantity = sc.nextInt();
                    if (quantity > 0) {
                        cart[cartIndex++] = subcategoryArray[subcategoryChoice].trim() + " x" + quantity;
                        totalSum += Double.parseDouble(prices[categoryChoice][subcategoryChoice]) * quantity;
                        System.out.println("Subcategory added. Total price: " + totalSum + " kzt");
                    } else {
                        System.out.println("Incorrect quantity");
                    }
                } else {
                    System.out.println("Incorrect select");
                }
                AddToCart();
            } else {
                System.out.println("Incorrect select");
                AddToCart();
            }
        } else {
            System.out.println("Incorrect select");
            AddToCart();
        }
    }


    public static void ShowCart() {
        //тут показывается корзина
        System.out.println("Cart:");
        for (int i = 0; i < cart.length; i++) {
            if (cart[i] != null) {
                System.out.println((i + 1) + ". " + cart[i]);
            }
        }
        System.out.println("Total sum: " + totalSum + " kzt");
        ClientMode();
    }

    public static boolean cardCheck = false;
    public static boolean dateCheck = false;
    public static boolean CVVcheck = false;

    // Метод для добавления банковской карты
    public static void addPaymentbank() {
        sc.nextLine();

        cardCheck = false;
        dateCheck = false;
        CVVcheck = false;


        //Здесь проверяется карту на корректность
        System.out.print("\nEnter your card number(16 digits): ");
        String s = sc.nextLine();
        if (s.length() == 16) {
            cardCheck = true;
            System.out.print("\nEnter your card date YY/MM");
            String cardDate = sc.nextLine();
            if (cardDate.length() == 5 && cardDate.contains("/")) {
                dateCheck = true;
                System.out.print("\nEnter your CVV (3 digits)");
                String cvv = sc.next();
                if (cvv.length() == 3) {
                    CVVcheck = true;
                }
            }
        }
        if (cardCheck && dateCheck && CVVcheck) {
            System.out.println("Bank card added");
            ClientMode();
        } else {
            System.out.println("Try again");
            addPaymentbank();
        }
    }


    // Метод для покупки товаров из корзины
    public static void BuyCart() {


        //тут пишется файл с чеком в check.txt
        if (cardCheck && dateCheck && CVVcheck) {
            System.out.println("\n----------------------\nThank you for purchase : " + totalSum + " kzt\n-------------------------\n");
            try (FileWriter fw = new FileWriter(filename, true)) {
                fw.write("\n---------------------------------------------------------------\nPurchase details:\n");
                for (String item : cart) {
                    if (item != null) {
                        fw.write(item + "\n");
                    }
                }
                fw.write("Total sum: " + totalSum + " kzt\n---------------------------------------------------------------\n");
            } catch (IOException e) {
                System.out.println("An error occurred while writing to the file.");
                e.printStackTrace();
            }
            cart = new String[10];
            cartIndex = 0;
            totalSum = 0.0;
        } else {
            System.out.println("Please enter card details.");
            addPaymentbank();
        }
        ClientMode();


    }
}