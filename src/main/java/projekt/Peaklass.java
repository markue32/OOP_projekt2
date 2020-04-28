package projekt;

import java.util.Scanner;

public class Peaklass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Buss b1 = new Buss(5, 12);

        System.out.println("\nTeretulemast piletiostu keskkonda!\n");
        System.out.println("Ostes vähemalt 4 piletit on teil võimalus saada üks pilet tasuta!");
        System.out.println("Mida rohkem pileteid ostate seda suurem on võidu võimalus.");

        while (b1.vabad_kohad() != 0) {
            // Kuni on vabu kohti saab neid ka osta.
            System.out.println("\n" + "\u001B[36mJärgmine ostja\u001B[0m");
            b1.bussiplaan();
            System.out.println("Mitu piletit soovite?");
            int arv = Integer.parseInt(sc.nextLine());  // Kasutajalt küsitakse kohtade arv, mida ta soovib osta
            Müügisüsteem.müü(b1, arv);                  // Ostjale müüjakse bussis b1 vastav arv kohti
        }

        System.out.println("\n\u001B[36mRohkem vabu kohti ei ole!\u001B[0m\n");
        // Kui kõik kohad on müüdud, väljastatakse reisi andmed
        System.out.println("Bussireisi tulu: " + b1.tulu() + " eurot");
        System.out.println("Reisijad:");
        for (Piletiostja reisija: b1.getReisijad()) {
            System.out.println("    " + reisija);
        }
    }
}
