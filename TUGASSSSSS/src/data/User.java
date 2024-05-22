package data;

import books.*;
import com.main.*;
import java.util.ArrayList;

public class User {

    ArrayList<Book> bookList = new ArrayList<>();
    private String nama;
    private String NIM;
    private String fakultas;
    private String prodi;

    public User(String nama, String NIM, String fakultas, String prodi) {
        this.nama = nama;
        this.NIM = NIM;
        this.fakultas = fakultas;
        this.prodi = prodi;
    }

    public void displayBooks() {
        ArrayList<Book> bookList = LibrarySystem.getBookList();
        System.out.println("===== Daftar Buku =====");
        for (Book buku : bookList) {
            if (buku != null) {
                System.out.println("ID Buku    : " + buku.getId_buku());
                System.out.println("Judul Buku : " + buku.getJudul());
                System.out.println("Author     : " + buku.getAuthor());
                System.out.println("Category   : " + buku.getCategory());
                System.out.println("Stok Buku  : " + buku.getStockBuku());
                System.out.println("---------------------------------");
            }
        }
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    public void setNIM(String NIM) {
        this.NIM = NIM;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getFakultas() {
        return fakultas;
    }

    public String getNIM() {
        return NIM;
    }

    public String getNama() {
        return nama;
    }

    public String getProdi() {
        return prodi;
    }
}
