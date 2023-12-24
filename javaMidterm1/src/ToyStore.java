import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ToyStore {

    private List<Toy> toys = new ArrayList<>();

    public void addToy(int id, String name, int quantity, double weight) {
        Toy toy = new Toy(id, name, quantity, weight);
        toys.add(toy);
    }

    // Основной метод. Создаем список игрушек, где с помощью рандомного метода сравнения (случайное число и вес),
    // выдаем(записываем в файл) игрушку и сразу удаляем ее из списка.
    public void play() {
        List<Toy> prizeQueue = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int randomIndex = random.nextInt(toys.size());
            Toy selectedToy = toys.get(randomIndex);
            double randomValue = random.nextDouble() * 100;
            if (randomValue < selectedToy.getWeight()) {
                prizeQueue.add(selectedToy);
                selectedToy.decreaseQuantity();
            }
        }

        // Записываем призовые игрушки в файл
        try (FileWriter writer = new FileWriter("prizes.txt")) {
            for (Toy prize : prizeQueue) {
                writer.write("ID: " + prize.getId() + ", Name: " + prize.getName() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
