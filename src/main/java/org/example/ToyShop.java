package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class Toy {
    int id;
    String name;
    int frequency;

    public Toy(int id, String name, int frequency) {
        this.id = id;
        this.name = name;
        this.frequency = frequency;
    }
}

public class ToyShop {
    private PriorityQueue<Toy> toyQueue = new PriorityQueue<>(Comparator.comparingInt(t -> -t.frequency));

    public void put(String input) {
        String[] parts = input.split(" ");
        if (parts.length >= 3) {
            int id = Integer.parseInt(parts[0]);
            String name = parts[1];
            int frequency = Integer.parseInt(parts[2]);
            Toy toy = new Toy(id, name, frequency);
            toyQueue.add(toy);
        }
    }

    public int get() {
        int randomValue = new Random().nextInt(100) + 1;

        if (randomValue <= 20) {
            return 1;
        } else if (randomValue <= 40) {
            return 2;
        } else {
            return 3;
        }
    }

    public void simulateAndWriteToFile(int numOfIterations) {
        try {
            FileWriter writer = new FileWriter("output.txt");

            for (int i = 0; i < numOfIterations; i++) {
                int toyId = get();
                Toy selectedToy = null;

                for (Toy toy : toyQueue) {
                    if (toy.id == toyId) {
                        selectedToy = toy;
                        break;
                    }
                }

                if (selectedToy != null) {
                    writer.write(selectedToy.name + "\n");
                }
            }

            writer.close();
            System.out.println("Результаты записаны в файл output.txt");
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ToyShop toyShop = new ToyShop();
        toyShop.put("1 конструктор 2");
        toyShop.put("2 робот 2");
        toyShop.put("3 кукла 6");

        toyShop.simulateAndWriteToFile(10);
    }
}
