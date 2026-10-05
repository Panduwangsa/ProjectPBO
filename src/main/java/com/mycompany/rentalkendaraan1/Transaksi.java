package com.mycompany.RentalKendaraan1;

public class Transaksi {

    private static int counterId = 0;

    private int id;
    private Kendaraan kendaraan;
    private String namaPenyewa;
    private int lamaHari;
    private double diskonPersen;
    private double biayaSewa;
    private double denda;
    private boolean selesai;

    public Transaksi(Kendaraan kendaraan, String namaPenyewa, int lamaHari, double diskonPersen) {
        if (kendaraan == null) {
            throw new IllegalArgumentException("Kendaraan tidak boleh kosong.");
        }
        setNamaPenyewa(namaPenyewa);
        setLamaHari(lamaHari);
        this.kendaraan = kendaraan;
        this.diskonPersen = diskonPersen;
        this.biayaSewa = kendaraan.hitungBiaya(lamaHari, diskonPersen); // overloading + overriding
        this.denda = 0;
        this.selesai = false;
        this.id = ++counterId;
    }

    public int getId()                { return this.id; }
    public Kendaraan getKendaraan()   { return this.kendaraan; }
    public String getNamaPenyewa()    { return this.namaPenyewa; }
    public int getLamaHari()          { return this.lamaHari; }
    public double getDiskonPersen()   { return this.diskonPersen; }
    public double getBiayaSewa()      { return this.biayaSewa; }
    public double getDenda()          { return this.denda; }
    public boolean isSelesai()        { return this.selesai; }
    public double getTotalBayar()     { return this.biayaSewa + this.denda; }

    public void setNamaPenyewa(String namaPenyewa) {
        if (namaPenyewa == null || namaPenyewa.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama penyewa tidak boleh kosong.");
        }
        this.namaPenyewa = namaPenyewa.trim();
    }

    public void setLamaHari(int lamaHari) {
        if (lamaHari <= 0) {
            throw new IllegalArgumentException("Lama sewa harus lebih dari 0 hari.");
        }
        this.lamaHari = lamaHari;
    }

    public void selesaikan(int hariTerlambat) {
        this.denda = kendaraan.hitungDenda(hariTerlambat);
        this.selesai = true;
        this.kendaraan.setTersedia(true);
    }

    public void tampilkan() {
        System.out.printf("#%-3d | %-10s | %-11s | %2d hr | %-12s | %-12s | %s%n",
                this.id, this.namaPenyewa, kendaraan.getPlatNomor(), this.lamaHari,
                Kendaraan.rupiah(this.biayaSewa), Kendaraan.rupiah(this.denda),
                this.selesai ? "Selesai" : "Berjalan");
    }
}