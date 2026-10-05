package com.mycompany.RentalKendaraan1;

import java.time.Year;
import java.util.Locale;

public class Kendaraan {

    private String platNomor;
    private String merek;
    private int tahunProduksi;
    private double tarifPerHari;

    private static int totalKendaraanTerdaftar = 0;

    public Kendaraan(String platNomor, String merek, int tahunProduksi, double tarifPerHari) {
        setPlatNomor(platNomor);
        setMerek(merek);
        setTahunProduksi(tahunProduksi);
        setTarifPerHari(tarifPerHari);
        totalKendaraanTerdaftar++; 
    }

    public String getPlatNomor()    { return this.platNomor; }
    public String getMerek()        { return this.merek; }
    public int getTahunProduksi()   { return this.tahunProduksi; }
    public double getTarifPerHari() { return this.tarifPerHari; }

    public void setPlatNomor(String platNomor) {
        if (platNomor == null || platNomor.trim().isEmpty()) {
            throw new IllegalArgumentException("Plat nomor tidak boleh kosong.");
        }
        this.platNomor = platNomor.trim().toUpperCase();
    }

    public void setMerek(String merek) {
        if (merek == null || merek.trim().isEmpty()) {
            throw new IllegalArgumentException("Merek tidak boleh kosong.");
        }
        this.merek = merek.trim();
    }

    public void setTahunProduksi(int tahunProduksi) {
        int sekarang = Year.now().getValue();
        if (tahunProduksi < 1990 || tahunProduksi > sekarang) {
            throw new IllegalArgumentException("Tahun produksi harus antara 1990 dan " + sekarang + ".");
        }
        this.tahunProduksi = tahunProduksi;
    }

    public void setTarifPerHari(double tarifPerHari) {
        if (tarifPerHari <= 0) {
            throw new IllegalArgumentException("Tarif per hari harus lebih dari 0.");
        }
        this.tarifPerHari = tarifPerHari;
    }

    public static int getTotalKendaraanTerdaftar() {
        return totalKendaraanTerdaftar;
    }

    public static String rupiah(double nilai) {
        return "Rp" + String.format(Locale.forLanguageTag("id-ID"), "%,.0f", nilai);
    }

    public void tampilkanInfo() {
        System.out.printf("%-11s | %-14s | %-5d | %-12s", this.platNomor, this.merek,
                this.tahunProduksi, rupiah(this.tarifPerHari) + "/hr");
    }

    public double hitungBiaya(int hari) {
        return this.tarifPerHari * hari;
    }

    public double hitungBiaya(int hari, double diskonPersen) {
        if (diskonPersen < 0 || diskonPersen > 100) {
            throw new IllegalArgumentException("Diskon harus antara 0 dan 100 persen.");
        }
        double total = hitungBiaya(hari); // memakai versi override milik subclass
        return total - (total * diskonPersen / 100);
    }

    void setTersedia(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    double hitungDenda(int hariTerlambat) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
