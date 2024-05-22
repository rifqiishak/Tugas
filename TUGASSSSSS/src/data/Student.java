package data;

import java.util.*;

import books.*;
import util.iMenu;

import com.main.LibrarySystem;

public class Student extends User implements iMenu {

    Scanner Input = new Scanner(System.in);
    List<Book> borrowedBooks = new ArrayList<>();
    LibrarySystem main = new LibrarySystem();

    public Student(String nama, String NIM, String fakultas, String prodi) {
        super(nama, NIM, fakultas, prodi);
    }

    public void menuStudent() {
        int pilihan = 0;
        do {
            try {
                System.out.println("==== Menu Mahasiswa ====");
                System.out.println("1. Tampilkan Buku yang Dipinjam");
                System.out.println("2. Pinjam Buku");
                System.out.println("3. Kembalikan Buku");
                System.out.println("4. Logout");
                System.out.print("Pilih opsi (1-4): ");
                String input = Input.nextLine();

                if (input.isEmpty()) {
                    throw new Exception("Input tidak boleh kosong.");
                }

                pilihan = Integer.parseInt(input);

                switch (pilihan) {
                    case 1:
                        this.showBorrowedBooks();
                        break;
                    case 2:
                        this.choiceBook();
                        break;
                    case 3:
                        this.returnBooks();
                        break;
                    case 4:
                        System.out.println("Anda telah berhasil logout.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Pilih nomor (1-4)!");
                }
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
                pilihan = 0;
            }
        } while (pilihan != 4);
    }

    public void displayInfo() {
        System.out.println("===== Data Diri Mahasiswa =====");
        System.out.println("Nama: " + getNama());
        System.out.println("NIM: " + getNIM());
        System.out.println("Fakultas: " + getFakultas());
        System.out.println("Prodi: " + getProdi());
    }

    public void showBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Tidak ada buku yang dipinjam.");
        } else {
            System.out.println("===== Daftar Buku Dipinjam =====");
            for (Book book : borrowedBooks) {
                System.out.println("ID Buku    : " + book.getId_buku());
                System.out.println("Judul Buku : " + book.getJudul());
                System.out.println("Author     : " + book.getAuthor());
                System.out.println("Category   : " + book.getCategory());
                System.out.println("Durasi     : " + book.getDuration() + " hari");
                System.out.println("---------------------------------");
            }
        }
    }

    public void logOut() {
        boolean hasBorrowedBooks = false;
        for (int i = 0; i < borrowedBooks.size(); i++) {
            if (borrowedBooks.get(i) != null) {
                hasBorrowedBooks = true;
                break;
            }
        }

        if (!hasBorrowedBooks) {
            System.out.println("Anda telah berhasil logout.");
        } else {
            System.out.println("Apakah kamu yakin untuk meminjam semua buku tersebut?");
            System.out.println("Input Y (iya) atau T (tidak):");

            String choice = Input.nextLine();
            if (choice.equalsIgnoreCase("Y")) {
                System.out.println("Peminjaman buku berhasil dilakukan.");
            } else {
                System.out.println("Logout dibatalkan.");
                menuStudent();
            }
        }
    }

    public void choiceBook() {
        ArrayList<Book> bookList = LibrarySystem.getBookList();
        displayBooks();
        System.out.println("99. Kembali ke Menu");
        System.out.print("Pilih buku yang ingin dipinjam (input ID buku atau 99 untuk kembali): ");
        String bookId = Input.next();
        if (bookId.equals("99")) {
            return;
        }

        Book bookToBorrow = null;
        for (Book buku : bookList) {
            if (buku.getId_buku().equals(bookId)) {
                bookToBorrow = buku;
                break;
            }
        }

        if (bookToBorrow == null) {
            System.out.println("Buku tidak ditemukan.");
        } else if (bookToBorrow.getStockBuku() <= 0) {
            System.out.println("Stok buku habis!");
        } else {
            System.out.print("Masukkan durasi peminjaman (maksimal 14 hari): ");
            int duration = Input.nextInt();
            Input.nextLine();
            if (duration > 14) {
                System.out.println("Anda hanya dapat meminjam buku maksimal selama 14 hari.");
            } else {
                bookToBorrow.setStockBuku(bookToBorrow.getStockBuku() - 1);
                bookToBorrow.setDuration(duration);
                borrowedBooks.add(bookToBorrow);
                System.out.println("Peminjaman buku berhasil dilakukan.");
            }
        }
    }

    public void returnBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Anda belum meminjam buku apapun.");
            return;
        }

        System.out.println("===== Buku yang Dipinjam =====");
        for (Book book : borrowedBooks) {
            System.out.println("ID Buku    : " + book.getId_buku());
            System.out.println("Judul Buku : " + book.getJudul());
            System.out.println("Author     : " + book.getAuthor());
            System.out.println("Category   : " + book.getCategory());
            System.out.println("Durasi     : " + book.getDuration() + " hari");
            System.out.println("---------------------------------");
        }
        System.out.print("Apakah kamu yakin untuk mengembalikan semua buku tersebut? (Y/T): ");
        String choice = Input.next();
        if (choice.equalsIgnoreCase("Y")) {
            for (Book book : borrowedBooks) {
                book.setStockBuku(book.getStockBuku() + 1);
            }
            borrowedBooks.clear();
            System.out.println("Pengembalian buku berhasil dilakukan.");
        } else {
            System.out.println("Pengembalian buku dibatalkan.");
        }
    }

    @Override
    public void menu() {
        System.out.println("==== Student Menu ====");
        System.out.println("1. Tampilkan Buku yang Dipinjam");
        System.out.println("2. Pinjam Buku");
        System.out.println("3. Kembalikan Buku");
        System.out.println("4. Pinjam Buku atau Keluar");
    }

}
