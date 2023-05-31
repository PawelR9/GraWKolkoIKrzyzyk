import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        char symbol = 'O';
        char[][] plansza = new char[3][3];
        for (int i = 0; i < plansza.length; i++) {
            for (int j = 0; j < plansza.length; j++) {
                plansza[i][j] = ' ';
            }
        }

        boolean kontunuowac = true;
        int licznikRuchow = 0;
        while (kontunuowac) {
            wyswietlPlansze(plansza);
            boolean ruchPrawidlowy = wykonajRuch(plansza, symbol);
            if (ruchPrawidlowy && licznikRuchow < 9) {
                licznikRuchow++;
                boolean wygranaWWierszach = sprawdzWiersze(plansza, symbol);
                boolean wygranaWKolumnach = sprawdzKolumny(plansza, symbol);
                boolean wygranaPoSkosie = sprawdzSkos1(plansza, symbol);
                boolean wygranaPoSkosie2 = sprawdzSkos2(plansza, symbol);
                if (wygranaWWierszach || wygranaWKolumnach || wygranaPoSkosie || wygranaPoSkosie2) {
                    wyswietlPlansze(plansza);
                    System.out.println("Wygrywa gracz " + symbol);
                    kontunuowac = false;
                } else if (licznikRuchow == 9) {
                    wyswietlPlansze(plansza);
                    System.out.println("Remis");
                    kontunuowac = false;
                }
                symbol = symbol == 'O' ? 'X' : 'O';
            }
        }

    }

    public static void wyswietlPlansze(char[][] plansza) {
        System.out.println(plansza[0][0] + " | " + plansza[0][1] + " | " + plansza[0][2]);
        System.out.println("---------");
        System.out.println(plansza[1][0] + " | " + plansza[1][1] + " | " + plansza[1][2]);
        System.out.println("---------");
        System.out.println(plansza[2][0] + " | " + plansza[2][1] + " | " + plansza[2][2]);
    }

    public static boolean wykonajRuch(char[][] plansza, char symbol) {
        System.out.println("Ruch wykonuje " + symbol);
        System.out.println("Podaj wiersz");
        int wiersz = new Scanner(System.in).nextInt() - 1;
        System.out.println("Podaj kolumnê");
        int kolumna = new Scanner(System.in).nextInt() - 1;
        boolean ruchPrawidlowy = plansza[wiersz][kolumna] == ' ';
        if (!ruchPrawidlowy) {
            System.out.println("Ruch nieprawid³owy");
            return false;
        }
        plansza[wiersz][kolumna] = symbol;
        return true;
    }

    public static boolean sprawdzWiersze(char[][] plansza, char symbol) {
        for (int wiersz = 0; wiersz < plansza.length; wiersz++) {
            boolean wygrana = true;
            for (int kolumna = 0; kolumna < plansza.length; kolumna++) {
                if (plansza[wiersz][kolumna] != symbol) {
                    wygrana = false;
                    break;
                }
            }
            if (wygrana) {
                return true;
            }
        }
        return false;
    }

    public static boolean sprawdzKolumny(char[][] plansza, char symbol) {
        for (int kolumna = 0; kolumna < plansza.length; kolumna++) {
            boolean wygrana = true;
            for (int wiersz = 0; wiersz < plansza.length; wiersz++) {
                if (plansza[wiersz][kolumna] != symbol) {
                    wygrana = false;
                    break;
                }
            }
            if (wygrana) {
                return true;
            }
        }
        return false;
    }

    public static boolean sprawdzSkos1(char[][] plansza, char symbol) {
        for (int i = 0; i < plansza.length; i++) {
            if (plansza[i][i] != symbol) {
                return false;
            }
        }
        return true;
    }


    public static boolean sprawdzSkos2(char[][] plansza, char symbol) {
        for (int i = 0; i < plansza.length; i++) {
            if (plansza[i][plansza.length - i - 1] != symbol) {
                return false;
            }
        }
        return true;
    }
}