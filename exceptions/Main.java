import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        try {
            // Чтение файла
             File file = new File("inf_26_04_21_26.txt");
             if (file.length() == 0) {
                 throw new EmptyFileException("Файл пуст и не содержит данных.");
             }
             BufferedReader reader = new BufferedReader(new FileReader(file));
            // Множество для быстрой проверки наличия числа
            Set<Integer> ns = new HashSet<>();
            // Запись чисел в массив
            String line = reader.readLine();
            if (line.equals("")) {
                reader.close();
                throw new InvalidDataFormatException("Не удалось прочитать количество элементов.");
            }
            int[] n = new int[Integer.parseInt(line.trim())];
            int number, ind = 0;
            while ((line = reader.readLine()) != null) {
                number = Integer.parseInt(line.trim());
                n[ind] = number;
                ns.add(number);
                ind++;
            }
            reader.close();

            int c = 0;  // счетчик пар
            int m = 0;  // максимальная сумма
            // Перебор всех пар
            for (int i = 0; i < n.length - 1; i++) {
                for (int j = i + 1; j < n.length; j++) {
                    // Проверка на одинаковую четность
                    if ((n[i] + n[j]) % 2 == 0) {
                        int s = n[i] + n[j];
                        if (ns.contains(s)) {
                            c++;
                            if (s > m) {
                                m = s;
                            }
                        }
                    }
                }
            }
            System.out.println(c + " " + m);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
