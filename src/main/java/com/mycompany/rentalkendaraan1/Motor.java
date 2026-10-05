package com.mycompany.RentalKendaraan1;

public class Motor extends Kendaraan {

    private static final double BIAYA_HELM = 10000;

    private int kapasitasMesinCC;

    public Motor(String platNomor, String merek, int tahunProduksi,
                 double tarifPerHari, int kapasitasMesinCC) {
        super(platNomor, merek, tahunProduksi, tarifPerHari);
        setKapasitasMesinCC(kapasitasMesinCC);
    }

    public int getKapasitasMesinCC() {
        return this.kapasitasMesinCC;
    }

    public void setKapasitasMesinCC(int kapasitasMesinCC) {
        if (kapasitasMesinCC < 50 || kapasitasMesinCC > 1500) {
            throw new IllegalArgumentException("Kapasitas mesin harus antara 50 dan 1500 cc.");
        }
        this.kapasitasMesinCC = kapasitasMesinCC;
    }

    @Override
    public void tampilkanInfo() {
        System.out.print("[Motor] ");
        super.tampilkanInfo();
        System.out.printf(" | Mesin: %d cc%n", this.kapasitasMesinCC);
    }

    @Override
    public double hitungBiaya(int hari) {
        return super.hitungBiaya(hari) + BIAYA_HELM;
    }
}
