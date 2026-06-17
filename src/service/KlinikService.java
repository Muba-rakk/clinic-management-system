package service;

import entity.*;
import dao.*;

import java.util.List;
import java.util.Date;

public class KlinikService {

    private final PasienDAO pasienDAO;
    private final DokterDAO dokterDAO;
    private final ObatDAO obatDAO;
    private final PendaftaranDAO pendaftaranDAO;
    private final RekamMedisDAO rekamMedisDAO;
    private final ResepDAO resepDAO;
    private final ResepObatDAO resepObatDAO;
    private final PembayaranDAO pembayaranDAO;
    private final DetailPembayaranDAO detailPembayaranDAO;

    public KlinikService(PasienDAO pasienDAO, DokterDAO dokterDAO,
                         ObatDAO obatDAO, PendaftaranDAO pendaftaranDAO,
                         RekamMedisDAO rekamMedisDAO, ResepDAO resepDAO,
                         ResepObatDAO resepObatDAO, PembayaranDAO pembayaranDAO,
                         DetailPembayaranDAO detailPembayaranDAO) {
        this.pasienDAO = pasienDAO;
        this.dokterDAO = dokterDAO;
        this.obatDAO = obatDAO;
        this.pendaftaranDAO = pendaftaranDAO;
        this.rekamMedisDAO = rekamMedisDAO;
        this.resepDAO = resepDAO;
        this.resepObatDAO = resepObatDAO;
        this.pembayaranDAO = pembayaranDAO;
        this.detailPembayaranDAO = detailPembayaranDAO;
    }

    // ======================== PASIEN ========================

    public void tambahPasien(String nama, String alamat, String noTelepon,
                             String tanggalLahir, String jenisKelamin) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama pasien tidak boleh kosong!");
        }
        if (jenisKelamin == null || (!jenisKelamin.equals("L") && !jenisKelamin.equals("P"))) {
            throw new IllegalArgumentException("Jenis kelamin harus L atau P!");
        }

        Pasien pasien = new Pasien();
        pasien.setNama(nama.trim());
        pasien.setAlamat(alamat);
        pasien.setNoTelepon(noTelepon);
        pasien.setTanggalLahir(tanggalLahir);
        pasien.setJenisKelamin(jenisKelamin);

        pasienDAO.insert(pasien);
    }

    public List<Pasien> semuaPasien() {
        return pasienDAO.findAll();
    }

    public Pasien cariPasienById(int id) {
        return pasienDAO.findById(id);
    }

    public void ubahPasien(Pasien pasien) {
        if (pasien == null || pasien.getId() <= 0) {
            throw new IllegalArgumentException("Data pasien tidak valid!");
        }
        pasienDAO.update(pasien);
    }

    public void hapusPasien(int id) {
        pasienDAO.delete(id);
    }

    // ======================== DOKTER ========================

    public void tambahDokter(String nama, String spesialisasi,
                             String noTelepon, String jadwalPraktek) {
        if (nama == null || nama.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama dokter tidak boleh kosong!");
        }

        Dokter dokter = new Dokter();
        dokter.setNama(nama.trim());
        dokter.setSpesialisasi(spesialisasi);
        dokter.setNoTelepon(noTelepon);
        dokter.setJadwalPraktek(jadwalPraktek);

        dokterDAO.insert(dokter);
    }

    public List<Dokter> semuaDokter() {
        return dokterDAO.findAll();
    }

    public Dokter cariDokterById(int id) {
        return dokterDAO.findById(id);
    }

    public void ubahDokter(Dokter dokter) {
        if (dokter == null || dokter.getId() <= 0) {
            throw new IllegalArgumentException("Data dokter tidak valid!");
        }
        dokterDAO.update(dokter);
    }

    public void hapusDokter(int id) {
        dokterDAO.delete(id);
    }

    // ======================== OBAT ========================

    public void tambahObat(String namaObat, String satuan,
                           double harga, int stok) {
        if (namaObat == null || namaObat.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama obat tidak boleh kosong!");
        }
        if (harga <= 0) {
            throw new IllegalArgumentException("Harga obat harus lebih dari 0!");
        }
        if (stok < 0) {
            throw new IllegalArgumentException("Stok obat tidak boleh negatif!");
        }

        Obat obat = new Obat();
        obat.setNamaObat(namaObat.trim());
        obat.setSatuan(satuan);
        obat.setHargaSatuan(harga);
        obat.setStok(stok);

        obatDAO.insert(obat);
    }

    public List<Obat> semuaObat() {
        return obatDAO.findAll();
    }

    public Obat cariObatById(int id) {
        return obatDAO.findById(id);
    }

    public void ubahObat(Obat obat) {
        if (obat == null || obat.getId() <= 0) {
            throw new IllegalArgumentException("Data obat tidak valid!");
        }
        obatDAO.update(obat);
    }

    public void hapusObat(int id) {
        obatDAO.delete(id);
    }

    // ======================== PENDAFTARAN ========================

    public void daftarPasien(int pasienId, int dokterId, String keluhan) {
        // pastikan pasien dan dokter benar-benar ada di database
        Pasien pasien = pasienDAO.findById(pasienId);
        if (pasien == null) {
            throw new IllegalArgumentException("Pasien dengan ID " + pasienId + " tidak ditemukan!");
        }

        Dokter dokter = dokterDAO.findById(dokterId);
        if (dokter == null) {
            throw new IllegalArgumentException("Dokter dengan ID " + dokterId + " tidak ditemukan!");
        }

        Pendaftaran pendaftaran = new Pendaftaran();
        pendaftaran.setPasienId(pasienId);
        pendaftaran.setDokterId(dokterId);
        pendaftaran.setKeluhan(keluhan);
        pendaftaran.setTanggalDaftar(new Date());
        pendaftaran.setStatus("MENUNGGU");

        pendaftaranDAO.insert(pendaftaran);
    }

    public List<Pendaftaran> lihatAntrian(String status) {
        if (status == null || status.trim().isEmpty()) {
            return pendaftaranDAO.findAll();
        }
        return pendaftaranDAO.findByStatus(status.trim());
    }

    // ======================== REKAM MEDIS ========================

    public void periksaPasien(int pendaftaranId, String diagnosa,
                              String tindakan, String catatan) {
        // pastikan data pendaftaran ada dan status masih MENUNGGU
        Pendaftaran pendaftaran = pendaftaranDAO.findById(pendaftaranId);
        if (pendaftaran == null) {
            throw new IllegalArgumentException("Pendaftaran dengan ID " + pendaftaranId + " tidak ditemukan!");
        }
        if (!"MENUNGGU".equals(pendaftaran.getStatus())) {
            throw new IllegalArgumentException(
                "Status pendaftaran harus MENUNGGU, status saat ini: " + pendaftaran.getStatus()
            );
        }

        RekamMedis rm = new RekamMedis();
        rm.setPendaftaranId(pendaftaranId);
        rm.setDokterId(pendaftaran.getDokterId());
        rm.setPasienId(pendaftaran.getPasienId());
        rm.setDiagnosa(diagnosa);
        rm.setTindakan(tindakan);
        rm.setCatatanTambahan(catatan);
        rm.setTanggalPeriksa(new Date());

        rekamMedisDAO.insert(rm);
        pendaftaranDAO.updateStatus(pendaftaranId, "DIPERIKSA");
    }

    public RekamMedis lihatRekamMedis(int pendaftaranId) {
        return rekamMedisDAO.findByPendaftaranId(pendaftaranId);
    }

    public List<RekamMedis> riwayatPasien(int pasienId) {
        return rekamMedisDAO.findByPasienId(pasienId);
    }

    // ======================== RESEP ========================

    public void buatResep(int rekamMedisId, List<Integer> obatIds,
                          List<Integer> jumlahs, List<String> aturanPakai) {
        RekamMedis rm = rekamMedisDAO.findById(rekamMedisId);
        if (rm == null) {
            throw new IllegalArgumentException("Rekam medis dengan ID " + rekamMedisId + " tidak ditemukan!");
        }

        if (obatIds.size() != jumlahs.size() || jumlahs.size() != aturanPakai.size()) {
            throw new IllegalArgumentException(
                "Jumlah data obat, jumlah, dan aturan pakai tidak sama!"
            );
        }

        // cek apakah stok setiap obat mencukupi
        for (int i = 0; i < obatIds.size(); i++) {
            Obat obat = obatDAO.findById(obatIds.get(i));
            if (obat == null) {
                throw new IllegalArgumentException("Obat dengan ID " + obatIds.get(i) + " tidak ditemukan!");
            }
            if (obat.getStok() < jumlahs.get(i)) {
                throw new IllegalArgumentException(
                    "Stok " + obat.getNamaObat() + " tidak mencukupi! " +
                    "(tersedia: " + obat.getStok() + ", diminta: " + jumlahs.get(i) + ")"
                );
            }
        }

        // 1. simpan resep
        Resep resep = new Resep();
        resep.setRekamMedisId(rekamMedisId);
        resep.setTanggalResep(new Date());
        resep.setKeterangan("Resep dari pemeriksaan");
        resepDAO.insert(resep);

        // 2. simpan tiap item obat + kurangi stok
        for (int i = 0; i < obatIds.size(); i++) {
            int obatId = obatIds.get(i);
            int jumlah = jumlahs.get(i);
            String aturan = aturanPakai.get(i);

            ResepObat resepObat = new ResepObat();
            resepObat.setResepId(resep.getId());
            resepObat.setObatId(obatId);
            resepObat.setJumlah(jumlah);
            resepObat.setAturanPakai(aturan);
            resepObatDAO.insert(resepObat);

            Obat obat = obatDAO.findById(obatId);
            obatDAO.updateStok(obatId, obat.getStok() - jumlah);
        }

        // 3. tandai pendaftaran selesai
        pendaftaranDAO.updateStatus(rm.getPendaftaranId(), "SELESAI");
    }

    // ======================== PEMBAYARAN ========================

    public double prosesPembayaran(int pendaftaranId, String metodeBayar) {
        // pastikan pendaftaran sudah selesai diperiksa
        Pendaftaran pendaftaran = pendaftaranDAO.findById(pendaftaranId);
        if (pendaftaran == null) {
            throw new IllegalArgumentException("Pendaftaran dengan ID " + pendaftaranId + " tidak ditemukan!");
        }
        if (!"SELESAI".equals(pendaftaran.getStatus())) {
            throw new IllegalArgumentException(
                "Status pendaftaran harus SELESAI, status saat ini: " + pendaftaran.getStatus()
            );
        }

        // ambil data rekam medis dan resep untuk hitung biaya obat
        RekamMedis rm = rekamMedisDAO.findByPendaftaranId(pendaftaranId);
        if (rm == null) {
            throw new IllegalArgumentException("Belum ada rekam medis untuk pendaftaran ini!");
        }

        List<Resep> resepList = resepDAO.findByRekamMedisId(rm.getId());

        final double BIAYA_PENDAFTARAN = 10000;
        final double BIAYA_TINDAKAN = 25000;

        double totalBiayaObat = 0;
        for (Resep resep : resepList) {
            List<ResepObat> items = resepObatDAO.findByResepId(resep.getId());
            for (ResepObat item : items) {
                Obat obat = obatDAO.findById(item.getObatId());
                if (obat != null) {
                    totalBiayaObat += obat.getHargaSatuan() * item.getJumlah();
                }
            }
        }

        double totalBayar = BIAYA_PENDAFTARAN + BIAYA_TINDAKAN + totalBiayaObat;

        // simpan pembayaran
        Pembayaran pembayaran = new Pembayaran();
        pembayaran.setPendaftaranId(pendaftaranId);
        pembayaran.setTanggalBayar(new Date());
        pembayaran.setTotalBayar(totalBayar);
        pembayaran.setMetodeBayar(metodeBayar);
        pembayaranDAO.insert(pembayaran);

        // simpan rincian pembayaran: pendaftaran, tindakan, dan tiap obat
        DetailPembayaran dp1 = new DetailPembayaran();
        dp1.setPembayaranId(pembayaran.getId());
        dp1.setDeskripsi("Biaya Pendaftaran");
        dp1.setJumlahBiaya(BIAYA_PENDAFTARAN);
        detailPembayaranDAO.insert(dp1);

        DetailPembayaran dp2 = new DetailPembayaran();
        dp2.setPembayaranId(pembayaran.getId());
        dp2.setDeskripsi("Biaya Tindakan Medis");
        dp2.setJumlahBiaya(BIAYA_TINDAKAN);
        detailPembayaranDAO.insert(dp2);

        for (Resep resep : resepList) {
            List<ResepObat> items = resepObatDAO.findByResepId(resep.getId());
            for (ResepObat item : items) {
                Obat obat = obatDAO.findById(item.getObatId());
                if (obat != null) {
                    double subtotal = obat.getHargaSatuan() * item.getJumlah();
                    DetailPembayaran dp3 = new DetailPembayaran();
                    dp3.setPembayaranId(pembayaran.getId());
                    dp3.setDeskripsi("Obat: " + obat.getNamaObat() + " (" + item.getJumlah() + " " + obat.getSatuan() + ")");
                    dp3.setJumlahBiaya(subtotal);
                    detailPembayaranDAO.insert(dp3);
                }
            }
        }

        pendaftaranDAO.updateStatus(pendaftaranId, "SELESAI");
        return totalBayar;
    }

    public List<Pembayaran> laporanPendapatan(Date start, Date end) {
        return pembayaranDAO.findByTanggal(start, end);
    }
}
