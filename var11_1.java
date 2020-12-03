import java.io.*;
import java.util.Scanner;

public class var11_1 {
    public static void main(String[] args) {
        try {
            File f1 = new File("Museum.txt");
            f1.delete();
            f1.createNewFile();
            RandomAccessFile rf = new RandomAccessFile(f1,"rw"); // чтение и запись
            Scanner sc = new Scanner(System.in);
            System.out.print("Введите количество Художественных Театров\n" + "=> ");
            int kol = sc.nextInt();
            sc.nextLine(); // очистка буфера после ввода числа
            String name, ruk, adress;
            int rating;

            for (int i = 0; i < kol; i++) {
                System.out.print("Введите название " + (i + 1) + " театра => ");
                name = sc.next();
                rf.seek(rf.length()); // поиск конца файла
                rf.writeUTF(name);
                for (int j = 0; j < 20 - name.length(); j++)
                    rf.writeByte(1); // добавление байтов до 20-ти любой цифрой (=1)

                System.out.print("Введите его руководителя => ");
                ruk = sc.next();
                rf.writeUTF(ruk);
                for (int j = 0; j < 20 - ruk.length(); j++)
                    rf.writeByte(1); // добавление байтов до кол=20 любым числом

                System.out.print("Введите адрес музея => ");
                adress = sc.next();
                rf.writeUTF(adress);
                for (int j = 0; j < 20 - adress.length(); j++)
                    rf.writeByte(1); // добавление байтов до кол=20 любым числом

                System.out.print("Введите рейтинг музея => ");
                rating = sc.nextInt();
                sc.nextLine(); // очистка буфера
                rf.writeInt(rating); // запись оклада
            }
            rf.close();
            File f2 = new File("Moscow Museum.txt");
            f2.delete();
            f2.createNewFile();
            rf = new RandomAccessFile(f1, "r");
            RandomAccessFile rf2 = new RandomAccessFile(f2,"rw"); // чтение и запись
            rf.seek(0); // перемещение в начало файла
            System.out.println("Информация о музеях из Москвы");
            for (int i = 0; i < kol; i++) {
                name = rf.readUTF();
                for (int j = 0; j < 20 - name.length(); j++){
                    rf.readByte();
                }

                ruk = rf.readUTF();
                for (int j = 0; j < 20 - ruk.length(); j++){
                    rf.readByte();
                }

                adress = rf.readUTF();
                for (int j = 0; j < 20 - adress.length(); j++){
                    rf.readByte();
                }

                rating = rf.readInt();
                if(adress.contains("Moscow") || adress.contains("Москва")){
                    System.out.println(name + "\t" + ruk + "\t" + adress + "\t" + rating + "\t");

                    rf2.writeUTF(name);
                    for (int j = 0; j < 20 - name.length(); j++)
                        rf2.writeByte(1); // добавление байтов до 20-ти любой цифрой (=1)

                    rf2.writeUTF(ruk);
                    for (int j = 0; j < 20 - ruk.length(); j++)
                        rf2.writeByte(1); // добавление байтов до кол=20 любым числом

                    rf2.writeUTF(adress);
                    for (int j = 0; j < 20 - adress.length(); j++)
                        rf2.writeByte(1); // добавление байтов до кол=20 любым числом

                    rf2.writeInt(rating);
                }
            }
            rf.close();
        } catch (IOException e) {
            System.out.println("End of file " + e);
        } }
}
