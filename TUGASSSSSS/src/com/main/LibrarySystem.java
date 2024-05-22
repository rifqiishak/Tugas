package com.main;

import java.util.*;

import books.*;
import data.*;
import exception.custom.illegalAdminAccess;

public class LibrarySystem {

    Scanner Input = new Scanner(System.in);

    private static ArrayList<Student> userStudent = new ArrayList<>();
    private static ArrayList<Book> bookList = new ArrayList<>();

    public static ArrayList<Student> getUserStudent() {
        return userStudent;
    }

    public static ArrayList<Book> getBookList() {
        return bookList;
    }

    public static void addBook(Book book) {
        bookList.add(book);
    }

    public static Student student = new Student(null, null, null, null);
    public static Admin admin = new Admin(null, null, null, null);

    static LibrarySystem main = new LibrarySystem();

    public static void main(String[] args) {

        Student userStudent = new Student("Rifqi Maulana Ishak", "202310370311252", "Teknik", "Informatika");
        getUserStudent().add(userStudent);

        Book bookList = new Book("388c-e681-9152", "Pemograman Java OOP", "Anan", "Novel", 10, 5);
        getBookList().add(bookList);

        Book historyBook = new HistoryBook("14567", "G-30 S PKI", "Adi", "Sejarah", 8, 8);
        addBook(historyBook);

        Book storyBook = new StoryBook("5678", "Pencari Surga", "Agus", "Cerita", 11, 9);
        addBook(storyBook);

        Book textBook = new TextBook("91011", "Si Kancil", "Andre", "Novel", 20, 10);
        addBook(textBook);

        main.Menu();
        admin.addStudent();
        admin.inputBook();
    }

    public void Menu() {
        int pilihan = 0;
        do {
            try {
                System.out.println("====== Library System ======");
                System.out.println("1. Login Sebagai Mahasiswa");
                System.out.println("2. Login Sebagai Admin");
                System.out.println("3. Exit");
                System.out.print("Pilih Opsi (1-3): ");
                pilihan = Input.nextInt();
                switch (pilihan) {
                    case 1:
                        loginStudent();
                        break;
                    case 2:
                        loginAdmin();
                        break;
                    case 3:
                        exit();
                        break;
                    default:
                        System.out.println("Pilihan Tidak Valid!!\nPilih Nomor (1-3) !!!");
                }
            } catch (InputMismatchException ime) {
                System.out.println("Input harus berupa angka. Silakan coba lagi.");
                Input.next();
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            }
        } while (pilihan != 3);
    }

    void loginStudent() {
        System.out.print("Masukan NIM : ");
        String NIM = Input.next();

        while (NIM.length() != 15) {
            System.out.println("NIM harus 15 digit angka.");
            System.out.print("Masukan NIM : ");
            NIM = Input.next();
        }

        if (checkNim(NIM)) {
            student.menuStudent();
        } else {
            System.out.println("User Not Found!! ");
        }
    }

    boolean checkNim(String NIM) {
        for (Student student : userStudent) {
            if (student != null && student.getNIM().equals(NIM)) {
                return true;
            }
        }
        return false;
    }

    void loginAdmin() {

        System.out.print("Masukan Username (admin) : ");
        String username = Input.next();
        Input.nextLine();
        System.out.print("Masukan Password (admin) : ");
        String pw = Input.next();

        try {
            if (admin.isAdmin(username, pw)) {
                admin.menuAdmin();
            } else {
                System.out.println("Admin User Not Found!!");
            }
        } catch (illegalAdminAccess e) {
            System.out.println("Akses tidak diizinkan: " + e.getMessage());
        }

    }

    static void exit() {
        System.out.println("Terima Kasih!!!");
        System.exit(0);
    }

}
