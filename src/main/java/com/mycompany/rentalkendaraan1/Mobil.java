package com.mycompany.RentalKendaraan1;

public class Mobil extends Kendaraan {

    private int jumlahPenumpang;

    public Mobil(String platNomor, String merek, int tahunProduksi,
                 double tarifPerHari, int jumlahPenumpang) {
        super(platNomor, merek, tahunProduksi, tarifPerHari);
        setJumlahPenumpang(jumlahPenumpang);
    }

    public int getJumlahPenumpang() {
        return this.jumlahPenumpang;
    }

    public void setJumlahPenumpang(int jumlahPenumpang) {
        if (jumlahPenumpang < 2 || jumlahPenumpang > 12) {
            throw new IllegalArgumentException("Jumlah penumpang harus antara 2 dan 12.");
        }
        this.jumlahPenumpang = jumlahPenumpang;
    }

    @Override
    public void tampilkanInfo() {
        System.out.print("[Mobil] ");
        super.tampilkanInfo();
        System.out.printf(" | Kapasitas: %d orang%n", this.jumlahPenumpang);
    }

    @Override
    public double hitungBiaya(int hari) {
        return super.hitungBiaya(hari) * 1.10; // + asuransi 10%
    }
}