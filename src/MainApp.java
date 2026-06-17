import config.DatabaseConfig;
import dao.*;
import entity.*;
import service.KlinikService;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    private final Scanner scanner = new Scanner(System.in);
    private final KlinikService service;

    public MainApp(KlinikService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        try (Connection conn = DatabaseConfig.getConnection()) {
            KlinikService service = new KlinikService(
                new PasienDAOImpl(conn),
                new DokterDAOImpl(conn),
                new ObatDAOImpl(conn),
                new PendaftaranDAOImpl(conn),
                new RekamMedisDAOImpl(conn),
                new ResepDAOImpl(conn),
                new ResepObatDAOImpl(conn),
                new PembayaranDAOImpl(conn),
                new DetailPembayaranDAOImpl(conn)
            );

            new MainApp(service).run();
        } catch (Exception e) {
            System.out.println("Aplikasi gagal dijalankan: " + e.getMessage());
        }
    }

    private void run() {
        int pilihan;
        do {
            tampilkanMenuUtama();
            pilihan = bacaInt("Pilih menu: ");
            try {
                switch (pilihan) {
                    case 1:
                        menuPasien();
                        break;
                    case 2:
                        menuDokter();
                        break;
                    case 3:
                        menuObat();
                        break;
                    case 4:
                        menuPendaftaran();
                        break;
                    case 5:
                        menuRekamMedisResep();
                        break;
                    case 6:
                        menuPembayaran();
                        break;
                    case 7:
                        menuLaporan();
                        break;
                    case 0:
                        System.out.println("Terima kasih.");
                        break;
                    default:
                        System.out.println("Menu tidak tersedia.");
                }
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            }
        } while (pilihan != 0);
    }

    private void tampilkanMenuUtama() {
        System.out.println("\n=== SISTEM MANAJEMEN KLINIK ===");
        System.out.println("1. Manajemen Pasien");
        System.out.println("2. Manajemen Dokter");
        System.out.println("3. Manajemen Obat");
        System.out.println("4. Pendaftaran Pasien");
        System.out.println("5. Rekam Medis & Resep");
        System.out.println("6. Pembayaran");
        System.out.println("7. Laporan");
        System.out.println("0. Keluar");
    }

    private void menuPasien() {
        System.out.println("\n1. Tambah  2. Lihat  3. Cari  4. Ubah  5. Hapus");
        int pilihan = bacaInt("Pilih: ");
        if (pilihan == 1) {
            service.tambahPasien(bacaText("Nama: "), bacaText("Alamat: "), bacaText("No telepon: "),
                bacaText("Tanggal lahir (YYYY-MM-DD): "), bacaText("Jenis kelamin (L/P): ").toUpperCase());
            System.out.println("Pasien berhasil ditambahkan.");
        } else if (pilihan == 2) {
            service.semuaPasien().forEach(System.out::println);
        } else if (pilihan == 3) {
            System.out.println(service.cariPasienById(bacaInt("ID pasien: ")));
        } else if (pilihan == 4) {
            Pasien pasien = service.cariPasienById(bacaInt("ID pasien: "));
            if (pasien == null) {
                System.out.println("Pasien tidak ditemukan.");
                return;
            }
            pasien.setNama(bacaText("Nama baru: "));
            pasien.setAlamat(bacaText("Alamat baru: "));
            pasien.setNoTelepon(bacaText("No telepon baru: "));
            pasien.setTanggalLahir(bacaText("Tanggal lahir baru (YYYY-MM-DD): "));
            pasien.setJenisKelamin(bacaText("Jenis kelamin baru (L/P): ").toUpperCase());
            service.ubahPasien(pasien);
            System.out.println("Pasien berhasil diubah.");
        } else if (pilihan == 5) {
            service.hapusPasien(bacaInt("ID pasien: "));
            System.out.println("Pasien berhasil dihapus.");
        }
    }

    private void menuDokter() {
        System.out.println("\n1. Tambah  2. Lihat  3. Cari  4. Ubah  5. Hapus");
        int pilihan = bacaInt("Pilih: ");
        if (pilihan == 1) {
            service.tambahDokter(bacaText("Nama: "), bacaText("Spesialisasi: "),
                bacaText("No telepon: "), bacaText("Jadwal praktek: "));
            System.out.println("Dokter berhasil ditambahkan.");
        } else if (pilihan == 2) {
            service.semuaDokter().forEach(System.out::println);
        } else if (pilihan == 3) {
            System.out.println(service.cariDokterById(bacaInt("ID dokter: ")));
        } else if (pilihan == 4) {
            Dokter dokter = service.cariDokterById(bacaInt("ID dokter: "));
            if (dokter == null) {
                System.out.println("Dokter tidak ditemukan.");
                return;
            }
            dokter.setNama(bacaText("Nama baru: "));
            dokter.setSpesialisasi(bacaText("Spesialisasi baru: "));
            dokter.setNoTelepon(bacaText("No telepon baru: "));
            dokter.setJadwalPraktek(bacaText("Jadwal praktek baru: "));
            service.ubahDokter(dokter);
            System.out.println("Dokter berhasil diubah.");
        } else if (pilihan == 5) {
            service.hapusDokter(bacaInt("ID dokter: "));
            System.out.println("Dokter berhasil dihapus.");
        }
    }

    private void menuObat() {
        System.out.println("\n1. Tambah  2. Lihat  3. Cari  4. Ubah  5. Hapus");
        int pilihan = bacaInt("Pilih: ");
        if (pilihan == 1) {
            service.tambahObat(bacaText("Nama obat: "), bacaText("Satuan: "),
                bacaDouble("Harga satuan: "), bacaInt("Stok: "));
            System.out.println("Obat berhasil ditambahkan.");
        } else if (pilihan == 2) {
            service.semuaObat().forEach(System.out::println);
        } else if (pilihan == 3) {
            System.out.println(service.cariObatById(bacaInt("ID obat: ")));
        } else if (pilihan == 4) {
            Obat obat = service.cariObatById(bacaInt("ID obat: "));
            if (obat == null) {
                System.out.println("Obat tidak ditemukan.");
                return;
            }
            obat.setNamaObat(bacaText("Nama obat baru: "));
            obat.setSatuan(bacaText("Satuan baru: "));
            obat.setHargaSatuan(bacaDouble("Harga baru: "));
            obat.setStok(bacaInt("Stok baru: "));
            service.ubahObat(obat);
            System.out.println("Obat berhasil diubah.");
        } else if (pilihan == 5) {
            service.hapusObat(bacaInt("ID obat: "));
            System.out.println("Obat berhasil dihapus.");
        }
    }

    private void menuPendaftaran() {
        System.out.println("\n1. Daftar pasien  2. Lihat antrian");
        int pilihan = bacaInt("Pilih: ");
        if (pilihan == 1) {
            service.daftarPasien(bacaInt("ID pasien: "), bacaInt("ID dokter: "), bacaText("Keluhan: "));
            System.out.println("Pendaftaran berhasil.");
        } else if (pilihan == 2) {
            String status = bacaText("Status (kosong untuk semua): ");
            service.lihatAntrian(status).forEach(System.out::println);
        }
    }

    private void menuRekamMedisResep() {
        System.out.println("\n1. Periksa  2. Lihat rekam medis  3. Riwayat pasien  4. Buat resep");
        int pilihan = bacaInt("Pilih: ");
        if (pilihan == 1) {
            service.periksaPasien(bacaInt("ID pendaftaran: "), bacaText("Diagnosa: "),
                bacaText("Tindakan: "), bacaText("Catatan: "));
            System.out.println("Rekam medis berhasil dibuat.");
        } else if (pilihan == 2) {
            System.out.println(service.lihatRekamMedis(bacaInt("ID pendaftaran: ")));
        } else if (pilihan == 3) {
            service.riwayatPasien(bacaInt("ID pasien: ")).forEach(System.out::println);
        } else if (pilihan == 4) {
            buatResep();
        }
    }

    private void buatResep() {
        int rekamMedisId = bacaInt("ID rekam medis: ");
        int totalItem = bacaInt("Jumlah item obat: ");
        List<Integer> obatIds = new ArrayList<>();
        List<Integer> jumlahs = new ArrayList<>();
        List<String> aturanPakai = new ArrayList<>();

        for (int i = 0; i < totalItem; i++) {
            System.out.println("Item obat ke-" + (i + 1));
            obatIds.add(bacaInt("ID obat: "));
            jumlahs.add(bacaInt("Jumlah: "));
            aturanPakai.add(bacaText("Aturan pakai: "));
        }

        service.buatResep(rekamMedisId, obatIds, jumlahs, aturanPakai);
        System.out.println("Resep berhasil dibuat.");
    }

    private void menuPembayaran() {
        double total = service.prosesPembayaran(
            bacaInt("ID pendaftaran: "),
            bacaText("Metode bayar (TUNAI/TRANSFER): ").toUpperCase()
        );
        System.out.println("Pembayaran berhasil. Total bayar: " + total);
    }

    private void menuLaporan() {
        Date start = Date.valueOf(bacaText("Tanggal mulai (YYYY-MM-DD): "));
        Date end = Date.valueOf(bacaText("Tanggal akhir (YYYY-MM-DD): "));
        service.laporanPendapatan(start, end).forEach(System.out::println);
    }

    private int bacaInt(String label) {
        while (true) {
            try {
                int value = Integer.parseInt(bacaText(label));
                if (value < 0) {
                    System.out.println("ID tidak boleh negatif.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka bulat.");
            }
        }
    }

    private double bacaDouble(String label) {
        while (true) {
            try {
                return Double.parseDouble(bacaText(label));
            } catch (NumberFormatException e) {
                System.out.println("Input harus angka.");
            }
        }
    }

    private String bacaText(String label) {
        System.out.print(label);
        return scanner.nextLine().trim();
    }
}
