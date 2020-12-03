import java.io.*;
import java.util.Scanner;

class Museum implements Serializable {
    String name;
    String ruk;
    String address;
    double rating;
}
public class var11_2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException{
        Scanner sc=new Scanner(System.in);
        File f1=new File("MuseumS.txt");
        File f2=new File("MuseumS Moscow.txt");
        f1.createNewFile();
        f2.createNewFile();
        System.out.println("Кол-во музеев: ");
        int count = sc.nextInt();
        int k=0;
        FileOutputStream fot = new FileOutputStream(f1);
        ObjectOutputStream oos1 = new ObjectOutputStream(fot);
        FileOutputStream fOSAnswer = new FileOutputStream(f2);
        ObjectOutputStream oos2 = new ObjectOutputStream(fOSAnswer);
        Museum museum = new Museum();
        sc.nextLine();
        for (int i = 0; i < count; i++) {
            museum = new Museum();
            System.out.println("Введите название музея:");
            museum.name = sc.nextLine();
            System.out.println("Введите руководителя музея:");
            museum.ruk = sc.nextLine();
            System.out.println("Введите адрес музея:");
            museum.address = sc.nextLine();
            oos1.writeObject(museum);
            if (museum.address.equals("Москва")){
                oos2.writeObject(museum);
                k++;
            }
            System.out.println("Введите рейтинг музея:");
            museum.rating = sc.nextInt();
            sc.nextLine();
        }
        FileInputStream fis = new FileInputStream(f2);
        ObjectInputStream oin = new ObjectInputStream(fis);
        System.out.println("Музеи в Москве ->");
        if (k!=0){
            for (int i = 0; i < k; i++) {
                museum = (Museum) oin.readObject();
                System.out.println(museum.name + "\t" + museum.ruk + "\t" + museum.address + "\t" + museum.rating);
            }
        }else{
            System.out.println("Вы не ввели музеи Москвы ");
        }
        oos1.flush();
        oos1.close();
        oos2.flush();
        oos2.close();
    }
}