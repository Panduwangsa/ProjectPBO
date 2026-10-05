package com.mycompany.RentalKendaraan1;

import java.util.Scanner;

public class RentalKendaraan1 {

    public static void cariKendaraan(String merek, Kendaraan[] daftar, int jumlah) {
        System.out.println("\nHasil pencarian merek \"" + merek + "\":");
        boolean ada = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getMerek().toLowerCase().contains(merek.toLowerCase())) {
                System.out.printf("%d. ", i + 1);
                daftar[i].tampilkanInfo();
                ada = true;
            }
        }
        if (!ada) {
            System.out.println("Tidak ada kendaraan dengan merek tersebut.");
        }
    }

    public static void cariKendaraan(int tahun, Kendaraan[] daftar, int jumlah) {
        System.out.println("\nHasil pencarian tahun produksi " + tahun + ":");
        boolean ada = false;
        for (int i = 0; i < jumlah; i++) {
            if (daftar[i].getTahunProduksi() == tahun) {
                System.out.printf("%d. ", i + 1);
                daftar[i].tampilkanInfo();
                ada = true;
            }
        }
        if (!ada) {
            System.out.println("Tidak ada kendaraan pada tahun tersebut.");
        }
    }

    private static int bacaInt(Scanner sc, String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka bulat. Coba lagi.");
            }
        }
    }

    private static double bacaDouble(Scanner sc, String pesan) {
        while (true) {
            System.out.print(pesan);
            try {
                return Double.parseDouble(sc.nextLine().trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka. Coba lagi.");
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            Kendaraan[] daftarKendaraan = new Kendaraan[10];
            int jumlahKendaraan = 0;
            boolean isRunning = true;

            daftarKendaraan[jumlahKendaraan++] = new Mobil("BE 1234 AB", "Toyota Avanza", 2021, 350000, 7);
            daftarKendaraan[jumlahKendaraan++] = new Mobil("BE 5678 CD", "Honda Brio", 2022, 300000, 5);
            daftarKendaraan[jumlahKendaraan++] = new Mobil("BE 9012 EF", "Daihatsu Xenia", 2019, 320000, 7);
            daftarKendaraan[jumlahKendaraan++] = new Motor("BE 3456 GH", "Honda Vario", 2023, 100000, 160);
            daftarKendaraan[jumlahKendaraan++] = new Motor("BE 7890 IJ", "Yamaha NMAX", 2022, 150000, 155);

            System.out.println("===================================");
            System.out.println("     SELAMAT DATANG DI REKAN!      ");
            System.out.println("===================================");

            while (isRunning) {
                System.out.println("\nMenu Utama:");
                System.out.println("1. Tambah Kendaraan");
                System.out.println("2. Lihat Daftar Kendaraan");
                System.out.println("3. Cari Kendaraan (Fitur Overloading)");
                System.out.println("4. Hitung Biaya Sewa");
                System.out.println("5. Keluar");
                int pilihan = bacaInt(scanner, "Pilih Menu (1-5): ");

                switch (pilihan) {
                    case 1:
                        if (jumlahKendaraan < daftarKendaraan.length) {
                            System.out.println("\n-- Pilih Jenis Kendaraan --");
                            System.out.println("1. Mobil");
                            System.out.println("2. Motor");
                            int jenis = bacaInt(scanner, "Pilihan (1/2): ");

                            if (jenis != 1 && jenis != 2) {
                                System.out.println("Jenis kendaraan tidak valid.");
                                break;
                            }

                            System.out.print("Masukkan Plat Nomor: ");
                            String plat = scanner.nextLine();
                            System.out.print("Masukkan Merek: ");
                            String merek = scanner.nextLine();
                            int tahun = bacaInt(scanner, "Masukkan Tahun Produksi: ");
                            double tarif = bacaDouble(scanner, "Masukkan Tarif per Hari (Rp): ");

                            try {
                                if (jenis == 1) {
                                    int penumpang = bacaInt(scanner, "Masukkan Jumlah Penumpang: ");
                                    daftarKendaraan[jumlahKendaraan] =
                                            new Mobil(plat, merek, tahun, tarif, penumpang);
                                } else {
                                    int cc = bacaInt(scanner, "Masukkan Kapasitas Mesin (cc): ");
                                    daftarKendaraan[jumlahKendaraan] =
                                            new Motor(plat, merek, tahun, tarif, cc);
                                }
                                jumlahKendaraan++;
                                System.out.println("Sukses! Kendaraan berhasil ditambahkan.");
                            } catch (IllegalArgumentException e) {
                                System.out.println("Gagal menambahkan: " + e.getMessage());
                            }
                        } else {
                            System.out.println("Maaf, kapasitas garasi sudah penuh!");
                        }
                        break;

                    case 2:
                        System.out.println("\n--- Daftar Kendaraan Rental ---");
                        if (jumlahKendaraan == 0) {
                            System.out.println("Belum ada kendaraan yang terdaftar.");
                        } else {
                            for (int i = 0; i < jumlahKendaraan; i++) {
                                System.out.printf("%d. ", i + 1);
                                daftarKendaraan[i].tampilkanInfo(); // overriding
                            }
                            System.out.println("\n* Total Kendaraan yang Pernah Terdaftar: "
                                    + Kendaraan.getTotalKendaraanTerdaftar());
                            System.out.println("* Kendaraan di Garasi: " + jumlahKendaraan
                                    + "/" + daftarKendaraan.length);
                        }
                        System.out.print("Tekan Enter untuk melanjutkan...");
                        scanner.nextLine();
                        break;

                    case 3:
                        System.out.println("\n-- Fitur Cari Kendaraan --");
                        System.out.println("1. Cari berdasarkan Merek");
                        System.out.println("2. Cari berdasarkan Tahun Produksi");
                        int modeCari = bacaInt(scanner, "Pilih (1/2): ");

                        if (modeCari == 1) {
                            System.out.print("Masukkan Merek: ");
                            String kataKunci = scanner.nextLine();
                            cariKendaraan(kataKunci, daftarKendaraan, jumlahKendaraan);  // overload String
                        } else if (modeCari == 2) {
                            int angkaKunci = bacaInt(scanner, "Masukkan Tahun: ");
                            cariKendaraan(angkaKunci, daftarKendaraan, jumlahKendaraan); // overload int
                        } else {
                            System.out.println("Pilihan tidak valid.");
                        }
                        System.out.print("Tekan Enter untuk melanjutkan...");
                        scanner.nextLine();
                        break;

                    case 4:
                        if (jumlahKendaraan == 0) {
                            System.out.println("Belum ada kendaraan yang bisa disewa.");
                            break;
                        }
                        int nomor = bacaInt(scanner, "\nMasukkan nomor kendaraan (1-" + jumlahKendaraan + "): ");
                        if (nomor < 1 || nomor > jumlahKendaraan) {
                            System.out.println("Nomor kendaraan tidak valid.");
                            break;
                        }
                        int hari = bacaInt(scanner, "Lama sewa (hari): ");
                        if (hari <= 0) {
                            System.out.println("Lama sewa harus lebih dari 0 hari.");
                            break;
                        }
                        System.out.print("Pakai diskon? (y/n): ");
                        String pakaiDiskon = scanner.nextLine().trim();

                        Kendaraan dipilih = daftarKendaraan[nomor - 1];
                        double total;
                        if (pakaiDiskon.equalsIgnoreCase("y")) {
                            double diskon = bacaDouble(scanner, "Besar diskon (%): ");
                            try {
                                total = dipilih.hitungBiaya(hari, diskon); // overload 2 parameter
                            } catch (IllegalArgumentException e) {
                                System.out.println("Gagal: " + e.getMessage());
                                break;
                            }
                        } else {
                            total = dipilih.hitungBiaya(hari);             // overload 1 parameter
                        }
                        System.out.println("\n--- Rincian Sewa ---");
                        dipilih.tampilkanInfo();
                        System.out.printf("Lama sewa  : %d hari%n", hari);
                        System.out.println("Total biaya: " + Kendaraan.rupiah(total));
                        break;

                    case 5:
                        System.out.println("Terima kasih telah menggunakan Rental Kendaraan!");
                        isRunning = false;
                        break;

                    default:
                        System.out.println("Pilihan tidak valid. Silakan masukkan angka 1-5.");
                        break;
                }
            }
        }
    }
}
