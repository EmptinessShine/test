import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/*

Написать фулл код за час анрил так что сорян((


Я бы мог написать фулл код, но это займет больше времени да и лучше если сама напишешь там можно больше фишек для себя закрепить


ЭТО ПРОСТО ПРИМЕР КАК НАДО ПИСАТЬ// Если есть дедлайн в неделю сможешь написать тем более в тг я могу помогать ес че
бтв тг: @SSSh1ne_1



пж только без ирл :((
 */



//пароль: pass
public class Test1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // в целом инициализация через хэшмапы для категорий и подкатегорий будет удобнее ибо к ним обращаться проще
        Map<String, String> categories = new HashMap<>();
        Map<String, Double> subcategories = new HashMap<>();

        //флаг нужен чтобы код постоянно работал если выходишь у тебя цикл прерывается и код оффается
        boolean isRunning = true;

        while (isRunning) {

            // так должен выглядеть начальный экран по требованию у вас к сожалению через один джава файл поэтому ток так могу
            System.out.println("Какое-то сообщение мол привет");
            System.out.println("1. Admin mode");
            System.out.println("2. client");
            System.out.println("3. exit");
            System.out.print("CHoose mode: ");
            int choice = sc.nextInt();
            sc.nextLine();

            //можно по сути использоваться switch-case через > переходить в классы но вы скорее всего не проходили такое так что тут ты
            //бегаешь классам таким образом

            if (choice == 1) {
                adminMode(sc, categories, subcategories);
            } else if (choice == 2) {
                clientMode(categories, subcategories);
            } else if (choice == 3) {
                System.out.println("бб");
                isRunning = false;
            } else {
                System.out.println("еррор типа.");
            }
        }
    }

    //надеюсь умеете обьекты по классам передавать если нет то в тг пиши хелпану
    private static void adminMode(Scanner scanner, Map<String, String> categories, Map<String, Double> subcategories) {
        System.out.print("введите пароль: ");
        String password = scanner.nextLine();

        // Тут задается значение пароля можно кнш через static final если шарите за такое то лучше все таки поменять
        if (!password.equals("pass")) {
            System.out.println("инкоррект");
            return;
        }

        //флаг для админ мода, тоже прерывается когда значение на фолс меняется
        boolean inAdminMode = true;
        while (inAdminMode) {
            System.out.println("админ мод:");
            System.out.println("1. Категории");
            System.out.println("2. подкатегории");
            System.out.println("3. назад");
            System.out.print("choose: ");
            int action = scanner.nextInt();
            scanner.nextLine();
            //так же дефолтный переход в каждый класс ты классы снизу инициализируешь после
            if (action == 1) {
                categories(scanner, categories);
            } else if (action == 2) {
                subcategories(scanner, categories, subcategories);
            } else if (action == 3) {
                inAdminMode = false;
            } else {
                System.out.println("Неверный выбор.");
            }
        }
    }

    //дальше работать чисто по классам по сути не тяжело




    //сase 1 то есть первая категория private ну хз мб требовать будут можешь public-и задавать если не требуют
    // в целом переходы по классам должны выглядеть так
    private static void categories(Scanner scanner, Map<String, String> categories) {
        System.out.println("1. Добавить категории");
        System.out.println("2. Удалить категорию");
        System.out.print("Ваш выбор: ");
        int choice = scanner.nextInt();
        scanner.nextLine();


        //тут как в видео тип через , если создавать категории
        if (choice == 1) {
            System.out.print("для добавления катег ");
            String[] categoryNames = scanner.nextLine().split(",");
            for (String category : categoryNames) {
                category = category.trim();
                if (!category.isEmpty()) {
                    categories.put(category, category);
                    System.out.println("added: " + category);
                }
            }
        } else if (choice == 2) {
            System.out.print("для удаления катег");
            String categoryToRemove = scanner.nextLine();
            if (categories.remove(categoryToRemove) != null) {
                System.out.println("deleted");
            } else {
                System.out.println("404 not found");
            }
        } else {
            System.out.println("errror.");
        }
    }

    private static void subcategories(Scanner scanner, Map<String, String> categories, Map<String, Double> subcategories) {
        System.out.print("category name ");
        String category = scanner.nextLine();

        if (!categories.containsKey(category)) {
            System.out.println("not found");
            return;
        }

        System.out.println("1. add subcat");
        System.out.println("2. del subcat");
        System.out.println("3. change price");
        System.out.print("Ваш выбор: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("subcat name: ");
            String subcategory = scanner.nextLine();
            subcategories.put(category +  "   "+ subcategory, 0.0);
            System.out.println("added");
        } else if (choice == 2) {
            System.out.print("delete subcat: ");
            String subcategory = scanner.nextLine();
            if (subcategories.remove(category + "  " + subcategory) != null) {
                System.out.println("deleted");
            } else {
                System.out.println("not found");
            }
        } else if (choice == 3) {
            System.out.print("subcat name ");
            String subcategory = scanner.nextLine();
            String key = category + " " + subcategory;

            if (subcategories.containsKey(key)) {
                System.out.print("new price: ");
                double price = scanner.nextDouble();
                scanner.nextLine();
                subcategories.put(key, price);
                System.out.println("updated");
            } else {
                System.out.println("not found");
            }
        } else {
            System.out.println("error");
        }
    }

    private static void clientMode(Map<String, String> categories, Map<String, Double> subcategories) {
        System.out.println("Menu>:");
        // надеюсь энтрисеты вы тоже проходили если нет то в тг
        for (String category : categories.keySet()) {
            System.out.println("category: " + category);
            for (Map.Entry<String, Double> entry : subcategories.entrySet()) {
                if (entry.getKey().startsWith(category + " ")) {
                    System.out.println("  " + entry.getKey().split(">")[1] + ": тенге или че там" + entry.getValue());
                }
            }
        }
    }
}
