# TODO-SPECS.md — Spesifikasi Detail Method
## Sistem Manajemen Klinik — Tugas UAS PBO 

---

Dokumen ini berisi **spesifikasi teknis untuk setiap method** yang harus diimplementasikan oleh masing-masing anggota. Gunakan dokumen ini sebagai acuan saat menulis kode.

---

## Bagian A: Spesifikasi Entity (Rahman)

### A.1 Pasien.java

**Package:** `entity` | **File:** `src/entity/Pasien.java`

```java
package entity;

import java.util.Date;

public class Pasien {
    private int id;
    private String nama;
    private String alamat;
    private String noTelepon;
    private String tanggalLahir;   // Format: "YYYY-MM-DD"
    private String jenisKelamin;   // "L" atau "P"
    private Date createdAt;        // java.util.Date

    // Constructor 1: Default (tanpa parameter)
    public Pasien() { }

    // Constructor 2: Dengan parameter (tanpa id dan createdAt — auto-generate)
    public Pasien(String nama, String alamat, String noTelepon,
                  String tanggalLahir, String jenisKelamin) {
        this.nama = nama;
        this.alamat = alamat;
        this.noTelepon = noTelepon;
        this.tanggalLahir = tanggalLahir;
        this.jenisKelamin = jenisKelamin;
    }

    // Getter dan Setter untuk SEMUA field (id, nama, alamat, noTelepon,
    // tanggalLahir, jenisKelamin, createdAt)
    // Contoh:
    // public int getId() { return id; }
    // public void setId(int id) { this.id = id; }
    // public String getNama() { return nama; }
    // public void setNama(String nama) { this.nama = nama; }
    // ... (lengkapi semua)

    @Override
    public String toString() {
        return "Pasien{id=" + id + ", nama='" + nama + "', alamat='" + alamat + "'}";
    }
}
```

**Yang harus dibuat:** Semua getter & setter untuk 7 field.

---

### A.2 Dokter.java

**Package:** `entity` | **File:** `src/entity/Dokter.java`

```java
package entity;

public class Dokter {
    private int id;
    private String nama;
    private String spesialisasi;
    private String noTelepon;
    private String jadwalPraktek;

    public Dokter() { }
    public Dokter(String nama, String spesialisasi, String noTelepon, String jadwalPraktek) {
        this.nama = nama;
        this.spesialisasi = spesialisasi;
        this.noTelepon = noTelepon;
        this.jadwalPraktek = jadwalPraktek;
    }

    // Getter & Setter untuk semua field
    // + toString()
}
```

### A.3 Obat.java

**Package:** `entity` | **File:** `src/entity/Obat.java`

```java
package entity;

public class Obat {
    private int id;
    private String namaObat;
    private String satuan;        // "tablet", "botol", "strip"
    private double hargaSatuan;
    private int stok;

    public Obat() { }
    public Obat(String namaObat, String satuan, double hargaSatuan, int stok) {
        this.namaObat = namaObat;
        this.satuan = satuan;
        this.hargaSatuan = hargaSatuan;
        this.stok = stok;
    }

    // Getter & Setter + toString()
}
```

### A.4 Pendaftaran.java

**Package:** `entity` | **File:** `src/entity/Pendaftaran.java`

```java
package entity;

import java.util.Date;

public class Pendaftaran {
    private int id;
    private int pasienId;
    private int dokterId;
    private Date tanggalDaftar;   // java.util.Date
    private String keluhan;
    private String status;        // "MENUNGGU", "DIPERIKSA", "SELESAI"

    public Pendaftaran() { }
    public Pendaftaran(int pasienId, int dokterId, String keluhan) {
        this.pasienId = pasienId;
        this.dokterId = dokterId;
        this.keluhan = keluhan;
        this.status = "MENUNGGU";
        this.tanggalDaftar = new Date();
    }

    // Getter & Setter + toString()
}
```

### A.5 RekamMedis.java

**Package:** `entity` | **File:** `src/entity/RekamMedis.java`

```java
package entity;

import java.util.Date;

public class RekamMedis {
    private int id;
    private int pendaftaranId;
    private int dokterId;
    private int pasienId;
    private String diagnosa;
    private String tindakan;
    private String catatanTambahan;
    private Date tanggalPeriksa;

    public RekamMedis() { }
    public RekamMedis(int pendaftaranId, int dokterId, int pasienId,
                      String diagnosa, String tindakan) {
        this.pendaftaranId = pendaftaranId;
        this.dokterId = dokterId;
        this.pasienId = pasienId;
        this.diagnosa = diagnosa;
        this.tindakan = tindakan;
        this.tanggalPeriksa = new Date();
    }

    // Getter & Setter + toString()
}
```

### A.6 Resep.java

**Package:** `entity` | **File:** `src/entity/Resep.java`

```java
package entity;

import java.util.Date;

public class Resep {
    private int id;
    private int rekamMedisId;
    private Date tanggalResep;
    private String keterangan;

    public Resep() { }
    public Resep(int rekamMedisId, String keterangan) {
        this.rekamMedisId = rekamMedisId;
        this.keterangan = keterangan;
        this.tanggalResep = new Date();
    }

    // Getter & Setter + toString()
}
```

### A.7 ResepObat.java

**Package:** `entity` | **File:** `src/entity/ResepObat.java`

```java
package entity;

public class ResepObat {
    private int id;
    private int resepId;
    private int obatId;
    private int jumlah;
    private String aturanPakai;   // "3x sehari setelah makan"

    public ResepObat() { }
    public ResepObat(int resepId, int obatId, int jumlah, String aturanPakai) {
        this.resepId = resepId;
        this.obatId = obatId;
        this.jumlah = jumlah;
        this.aturanPakai = aturanPakai;
    }

    // Getter & Setter + toString()
}
```

### A.8 Pembayaran.java

**Package:** `entity` | **File:** `src/entity/Pembayaran.java`

```java
package entity;

import java.util.Date;

public class Pembayaran {
    private int id;
    private int pendaftaranId;
    private Date tanggalBayar;
    private double totalBayar;
    private String metodeBayar;   // "TUNAI" atau "TRANSFER"

    public Pembayaran() { }
    public Pembayaran(int pendaftaranId, double totalBayar, String metodeBayar) {
        this.pendaftaranId = pendaftaranId;
        this.totalBayar = totalBayar;
        this.metodeBayar = metodeBayar;
        this.tanggalBayar = new Date();
    }

    // Getter & Setter + toString()
}
```

### A.9 DetailPembayaran.java

**Package:** `entity` | **File:** `src/entity/DetailPembayaran.java`

```java
package entity;

public class DetailPembayaran {
    private int id;
    private int pembayaranId;
    private String deskripsi;     // "Biaya Pendaftaran", "Obat: Paracetamol"
    private double jumlahBiaya;

    public DetailPembayaran() { }
    public DetailPembayaran(int pembayaranId, String deskripsi, double jumlahBiaya) {
        this.pembayaranId = pembayaranId;
        this.deskripsi = deskripsi;
        this.jumlahBiaya = jumlahBiaya;
    }

    // Getter & Setter + toString()
}
```

---

## Bagian B: Spesifikasi DAO Interface (Ariq)

### B.1 Pola Umum Semua DAO Interface

**Lokasi:** `src/dao/`

Setiap interface DAO berada di package `dao` dan meng-import entity terkait serta `java.util.List`.

```java
package dao;

import entity.NamaEntity;
import java.util.List;

public interface NamaEntityDAO {
    void insert(NamaEntity obj);
    // method lain sesuai kebutuhan
}
```

### B.1 PasienDAO.java

**File:** `src/dao/PasienDAO.java`

| Method | Parameter | Return | Deskripsi |
|--------|-----------|--------|-----------|
| `insert` | `Pasien pasien` | `void` | Simpan pasien baru ke tabel `pasien` |
| `findAll` | - | `List<Pasien>` | Ambil semua data pasien |
| `findById` | `int id` | `Pasien` | Cari pasien berdasarkan primary key |
| `update` | `Pasien pasien` | `void` | Update data pasien (berdasarkan id) |
| `delete` | `int id` | `void` | Hapus pasien berdasarkan id |

### B.2 DokterDAO.java

**File:** `src/dao/DokterDAO.java`

| Method | Parameter | Return | Deskripsi |
|--------|-----------|--------|-----------|
| `insert` | `Dokter dokter` | `void` | Simpan dokter baru |
| `findAll` | - | `List<Dokter>` | Ambil semua dokter |
| `findById` | `int id` | `Dokter` | Cari dokter by id |
| `update` | `Dokter dokter` | `void` | Update data dokter |
| `delete` | `int id` | `void` | Hapus dokter |

### B.3 ObatDAO.java

**File:** `src/dao/ObatDAO.java`

| Method | Parameter | Return | Deskripsi |
|--------|-----------|--------|-----------|
| `insert` | `Obat obat` | `void` | Simpan obat baru |
| `findAll` | - | `List<Obat>` | Ambil semua obat |
| `findById` | `int id` | `Obat` | Cari obat by id |
| `update` | `Obat obat` | `void` | Update data obat |
| `delete` | `int id` | `void` | Hapus obat |
| `updateStok` | `int id, int stokBaru` | `void` | Update stok obat (set stok = stokBaru) |

### B.4 PendaftaranDAO.java

**File:** `src/dao/PendaftaranDAO.java`

| Method | Parameter | Return | Deskripsi |
|--------|-----------|--------|-----------|
| `insert` | `Pendaftaran p` | `void` | Simpan pendaftaran baru |
| `findAll` | - | `List<Pendaftaran>` | Ambil semua pendaftaran |
| `findById` | `int id` | `Pendaftaran` | Cari pendaftaran by id |
| `findByStatus` | `String status` | `List<Pendaftaran>` | Filter pendaftaran by status ("MENUNGGU"/"DIPERIKSA"/"SELESAI") |
| `updateStatus` | `int id, String status` | `void` | Update status pendaftaran |

### B.5 RekamMedisDAO.java

**File:** `src/dao/RekamMedisDAO.java`

| Method | Parameter | Return | Deskripsi |
|--------|-----------|--------|-----------|
| `insert` | `RekamMedis rm` | `void` | Simpan rekam medis baru |
| `findById` | `int id` | `RekamMedis` | Cari rekam medis by id |
| `findByPendaftaranId` | `int pendaftaranId` | `RekamMedis` | Cari rekam medis berdasarkan pendaftaran (UNIQUE) |
| `findByPasienId` | `int pasienId` | `List<RekamMedis>` | Riwayat rekam medis pasien |

### B.6 ResepDAO.java

**File:** `src/dao/ResepDAO.java`

| Method | Parameter | Return | Deskripsi |
|--------|-----------|--------|-----------|
| `insert` | `Resep resep` | `void` | Simpan resep baru |
| `findById` | `int id` | `Resep` | Cari resep by id |
| `findByRekamMedisId` | `int rekamMedisId` | `List<Resep>` | Cari resep berdasarkan rekam medis |

### B.7 ResepObatDAO.java

**File:** `src/dao/ResepObatDAO.java`

| Method | Parameter | Return | Deskripsi |
|--------|-----------|--------|-----------|
| `insert` | `ResepObat ro` | `void` | Simpan item obat dalam resep |
| `findByResepId` | `int resepId` | `List<ResepObat>` | Ambil semua obat dalam resep tertentu |

### B.8 PembayaranDAO.java

**File:** `src/dao/PembayaranDAO.java`

| Method | Parameter | Return | Deskripsi |
|--------|-----------|--------|-----------|
| `insert` | `Pembayaran p` | `void` | Simpan pembayaran baru |
| `findById` | `int id` | `Pembayaran` | Cari pembayaran by id |
| `findByPendaftaranId` | `int pendaftaranId` | `Pembayaran` | Cari pembayaran by pendaftaran (UNIQUE) |
| `findByTanggal` | `Date start, Date end` | `List<Pembayaran>` | Cari pembayaran dalam rentang tanggal (untuk laporan) |
| `findAll` | - | `List<Pembayaran>` | Ambil semua pembayaran |

### B.9 DetailPembayaranDAO.java

**File:** `src/dao/DetailPembayaranDAO.java`

| Method | Parameter | Return | Deskripsi |
|--------|-----------|--------|-----------|
| `insert` | `DetailPembayaran dp` | `void` | Simpan detail pembayaran |
| `findByPembayaranId` | `int pembayaranId` | `List<DetailPembayaran>` | Ambil semua item dalam pembayaran |

---

## Bagian C: Spesifikasi DAOImpl (Ariq)

### C.1 Pola Umum Semua DAOImpl

**Aturan untuk SETIAP method di DAOImpl:**

1. **Constructor Injection**: Terima `Connection conn` sebagai parameter constructor
2. **PreparedStatement**: WAJIB menggunakan `PreparedStatement`, jangan `Statement`
3. **try-with-resources**: WAJIB untuk `Connection` (dari luar, hati-hati) dan `PreparedStatement` + `ResultSet`
4. **Exception Handling**: Tangkap `SQLException`, lempar `RuntimeException` dengan pesan yang jelas
5. **Column Mapping**: Gunakan nama kolom (bukan indeks) di `ResultSet.getString("nama_kolom")`

### C.2 Template DAOImpl

```java
package dao;

import entity.NamaEntity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NamaEntityDAOImpl implements NamaEntityDAO {
    private Connection conn;

    // Constructor Injection
    public NamaEntityDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(NamaEntity obj) {
        String sql = "INSERT INTO nama_tabel (kolom1, kolom2) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setTipe(1, obj.getField1());
            ps.setTipe(2, obj.getField2());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal insert: " + e.getMessage(), e);
        }
    }

    // ... method lainnya
}
```

### C.3 Detail Implementasi per DAOImpl

#### PasienDAOImpl.java

**Query INSERT:**
```sql
INSERT INTO pasien (nama, alamat, no_telepon, tanggal_lahir, jenis_kelamin)
VALUES (?, ?, ?, ?, ?)
```
- `ps.setString(1, pasien.getNama())`
- `ps.setString(2, pasien.getAlamat())`
- `ps.setString(3, pasien.getNoTelepon())`
- `ps.setString(4, pasien.getTanggalLahir())`
- `ps.setString(5, pasien.getJenisKelamin())`

**Query SELECT all:**
```sql
SELECT * FROM pasien
```
Loop `while (rs.next())`, buat objek `Pasien`, set semua field dari ResultSet, tambahkan ke List.

**Query SELECT by id:**
```sql
SELECT * FROM pasien WHERE id = ?
```
`ps.setInt(1, id)`, return `Pasien` atau `null` jika tidak ditemukan.

**Query UPDATE:**
```sql
UPDATE pasien SET nama=?, alamat=?, no_telepon=?, tanggal_lahir=?, jenis_kelamin=? WHERE id=?
```
Set parameter sesuai field, `ps.setInt(6, pasien.getId())`.

**Query DELETE:**
```sql
DELETE FROM pasien WHERE id = ?
```
`ps.setInt(1, id)`.

---

#### DokterDAOImpl.java

**INSERT:**
```sql
INSERT INTO dokter (nama, spesialisasi, no_telepon, jadwal_praktek) VALUES (?, ?, ?, ?)
```

**SELECT all:**
```sql
SELECT * FROM dokter
```

**SELECT by id:**
```sql
SELECT * FROM dokter WHERE id = ?
```

**UPDATE:**
```sql
UPDATE dokter SET nama=?, spesialisasi=?, no_telepon=?, jadwal_praktek=? WHERE id=?
```

**DELETE:**
```sql
DELETE FROM dokter WHERE id = ?
```

---

#### ObatDAOImpl.java

**INSERT:**
```sql
INSERT INTO obat (nama_obat, satuan, harga_satuan, stok) VALUES (?, ?, ?, ?)
```

**SELECT all:**
```sql
SELECT * FROM obat
```

**SELECT by id:**
```sql
SELECT * FROM obat WHERE id = ?
```

**UPDATE:**
```sql
UPDATE obat SET nama_obat=?, satuan=?, harga_satuan=?, stok=? WHERE id=?
```

**DELETE:**
```sql
DELETE FROM obat WHERE id = ?
```

**updateStok:**
```sql
UPDATE obat SET stok = ? WHERE id = ?
```

---

#### PendaftaranDAOImpl.java

**INSERT:**
```sql
INSERT INTO pendaftaran (pasien_id, dokter_id, tanggal_daftar, keluhan, status)
VALUES (?, ?, ?, ?, ?)
```
Note: `tanggalDaftar` bertipe `java.util.Date`, simpan ke database sebagai `java.sql.Date`:
```java
ps.setDate(3, new java.sql.Date(pendaftaran.getTanggalDaftar().getTime()));
```
`status` di-set "MENUNGGU".

**SELECT all:**
```sql
SELECT * FROM pendaftaran
```

**SELECT by id:**
```sql
SELECT * FROM pendaftaran WHERE id = ?
```

**findByStatus:**
```sql
SELECT * FROM pendaftaran WHERE status = ?
```

**updateStatus:**
```sql
UPDATE pendaftaran SET status = ? WHERE id = ?
```

---

#### RekamMedisDAOImpl.java

**INSERT:**
```sql
INSERT INTO rekam_medis (pendaftaran_id, dokter_id, pasien_id, diagnosa, tindakan, catatan_tambahan, tanggal_periksa)
VALUES (?, ?, ?, ?, ?, ?, ?)
```
Tanggal: `new java.sql.Date(rekamMedis.getTanggalPeriksa().getTime())`

**findById:**
```sql
SELECT * FROM rekam_medis WHERE id = ?
```

**findByPendaftaranId:**
```sql
SELECT * FROM rekam_medis WHERE pendaftaran_id = ?
```
(Ini UNIQUE — return satu objek atau null)

**findByPasienId:**
```sql
SELECT * FROM rekam_medis WHERE pasien_id = ?
```
Return List (bisa lebih dari satu — riwayat pasien).

---

#### ResepDAOImpl.java

**INSERT:**
```sql
INSERT INTO resep (rekam_medis_id, tanggal_resep, keterangan) VALUES (?, ?, ?)
```

**findById:**
```sql
SELECT * FROM resep WHERE id = ?
```

**findByRekamMedisId:**
```sql
SELECT * FROM resep WHERE rekam_medis_id = ?
```

---

#### ResepObatDAOImpl.java

**INSERT:**
```sql
INSERT INTO resep_obat (resep_id, obat_id, jumlah, aturan_pakai) VALUES (?, ?, ?, ?)
```

**findByResepId:**
```sql
SELECT * FROM resep_obat WHERE resep_id = ?
```

---

#### PembayaranDAOImpl.java

**INSERT:**
```sql
INSERT INTO pembayaran (pendaftaran_id, tanggal_bayar, total_bayar, metode_bayar)
VALUES (?, ?, ?, ?)
```

**findById:**
```sql
SELECT * FROM pembayaran WHERE id = ?
```

**findByPendaftaranId:**
```sql
SELECT * FROM pembayaran WHERE pendaftaran_id = ?
```

**findByTanggal:**
```sql
SELECT * FROM pembayaran WHERE tanggal_bayar BETWEEN ? AND ?
```
```java
ps.setDate(1, new java.sql.Date(start.getTime()));
ps.setDate(2, new java.sql.Date(end.getTime()));
```

**findAll:**
```sql
SELECT * FROM pembayaran
```

---

#### DetailPembayaranDAOImpl.java

**INSERT:**
```sql
INSERT INTO detail_pembayaran (pembayaran_id, deskripsi, jumlah_biaya) VALUES (?, ?, ?)
```

**findByPembayaranId:**
```sql
SELECT * FROM detail_pembayaran WHERE pembayaran_id = ?
```

---

## Bagian D: Spesifikasi Service Layer (Winata)

### D.1 Struktur Class

**Package:** `service` | **File:** `src/service/KlinikService.java`

```java
package service;

import entity.*;
import dao.*;
import java.util.List;
import java.util.Date;

public class KlinikService {
    // 9 DAO — semua berupa INTERFACE (DIP)
    private PasienDAO pasienDAO;
    private DokterDAO dokterDAO;
    private ObatDAO obatDAO;
    private PendaftaranDAO pendaftaranDAO;
    private RekamMedisDAO rekamMedisDAO;
    private ResepDAO resepDAO;
    private ResepObatDAO resepObatDAO;
    private PembayaranDAO pembayaranDAO;
    private DetailPembayaranDAO detailPembayaranDAO;

    // Constructor — terima semua DAO dari luar (Injection)
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
}
```

### D.2 Spesifikasi Method Bisnis

---

#### D.2.1 Manajemen Pasien

**Method: `tambahPasien(String nama, String alamat, String noTelepon, String tanggalLahir, String jenisKelamin)`**

| Aspek | Detail |
|-------|--------|
| **Tujuan** | Menambahkan pasien baru ke database |
| **Validasi** | `nama` tidak boleh null atau kosong. `jenisKelamin` harus "L" atau "P" |
| **Error** | Lempar `IllegalArgumentException` jika validasi gagal |
| **Alur** | Buat objek `Pasien` → panggil `pasienDAO.insert(pasien)` |
| **Return** | `void` |

**Method: `semuaPasien()`**

| Aspek | Detail |
|-------|--------|
| **Tujuan** | Mengambil daftar semua pasien |
| **Alur** | Panggil `pasienDAO.findAll()` |
| **Return** | `List<Pasien>` |

**Method: `cariPasienById(int id)`**

| Aspek | Detail |
|-------|--------|
| **Tujuan** | Mencari pasien berdasarkan ID |
| **Alur** | Panggil `pasienDAO.findById(id)` |
| **Return** | `Pasien` atau `null` jika tidak ditemukan |

**Method: `ubahPasien(Pasien pasien)`**

| Aspek | Detail |
|-------|--------|
| **Tujuan** | Mengubah data pasien yang sudah ada |
| **Validasi** | `pasien.getId()` harus > 0 |
| **Alur** | Panggil `pasienDAO.update(pasien)` |
| **Return** | `void` |

**Method: `hapusPasien(int id)`**

| Aspek | Detail |
|-------|--------|
| **Tujuan** | Menghapus pasien dari database |
| **Alur** | Panggil `pasienDAO.delete(id)` |
| **Return** | `void` |

---

#### D.2.2 Manajemen Dokter

(Pola sama dengan Manajemen Pasien — delegasi ke `dokterDAO`)

- `tambahDokter(String nama, String spesialisasi, String noTelepon, String jadwalPraktek)` → validasi nama tidak null
- `semuaDokter()` → `dokterDAO.findAll()`
- `cariDokterById(int id)` → `dokterDAO.findById(id)`
- `ubahDokter(Dokter dokter)` → `dokterDAO.update(dokter)`
- `hapusDokter(int id)` → `dokterDAO.delete(id)`

---

#### D.2.3 Manajemen Obat

(Pola sama, tambahan validasi stok)

- `tambahObat(String namaObat, String satuan, double harga, int stok)` → validasi stok >= 0, harga > 0
- `semuaObat()` → `obatDAO.findAll()`
- `cariObatById(int id)` → `obatDAO.findById(id)`
- `ubahObat(Obat obat)` → `obatDAO.update(obat)`
- `hapusObat(int id)` → `obatDAO.delete(id)`

---

#### D.2.4 Pendaftaran

**Method: `daftarPasien(int pasienId, int dokterId, String keluhan)`**

| Aspek | Detail |
|-------|--------|
| **Tujuan** | Mendaftarkan pasien ke dokter tertentu |
| **Validasi** | Pastikan `pasienId` dan `dokterId` ada di database (panggil `cariPasienById()` dan `cariDokterById()`) |
| **Alur** | Buat objek `Pendaftaran` → `pendaftaran.setStatus("MENUNGGU")` → `pendaftaranDAO.insert(pendaftaran)` |
| **Return** | `void` |

**Method: `lihatAntrian(String status)`**

| Aspek | Detail |
|-------|--------|
| **Tujuan** | Melihat daftar antrian berdasarkan status |
| **Alur** | Jika `status` null atau kosong, panggil `pendaftaranDAO.findAll()`. Jika tidak, panggil `pendaftaranDAO.findByStatus(status)` |
| **Return** | `List<Pendaftaran>` |

---

#### D.2.5 Rekam Medis

**Method: `periksaPasien(int pendaftaranId, String diagnosa, String tindakan, String catatan)`**

| Aspek | Detail |
|-------|--------|
| **Tujuan** | Dokter melakukan pemeriksaan dan mencatat rekam medis |
| **Validasi** | `pendaftaranId` harus ada. Status pendaftaran harus "MENUNGGU" (belum diperiksa) |
| **Alur** | 1. Cari `Pendaftaran` by id → dapatkan `pasienId` dan `dokterId`<br>2. Buat objek `RekamMedis( pendaftaranId, dokterId, pasienId, diagnosa, tindakan )`<br>3. `rekamMedisDAO.insert(rekamMedis)`<br>4. `pendaftaranDAO.updateStatus(pendaftaranId, "DIPERIKSA")` |
| **Return** | `void` |

**Method: `lihatRekamMedis(int pendaftaranId)`**

| Aspek | Detail |
|-------|--------|
| **Alur** | `rekamMedisDAO.findByPendaftaranId(pendaftaranId)` |
| **Return** | `RekamMedis` |

**Method: `riwayatPasien(int pasienId)`**

| Aspek | Detail |
|-------|--------|
| **Alur** | `rekamMedisDAO.findByPasienId(pasienId)` |
| **Return** | `List<RekamMedis>` |

---

#### D.2.6 Resep Obat

**Method: `buatResep(int rekamMedisId, List<Integer> obatIds, List<Integer> jumlahs, List<String> aturanPakai)`**

| Aspek | Detail |
|-------|--------|
| **Tujuan** | Dokter membuat resep berisi satu atau lebih obat |
| **Validasi** | Pastikan `rekamMedisId` ada. `obatIds.size()` == `jumlahs.size()` == `aturanPakai.size()`. Stok obat tersedia untuk setiap item |
| **Alur** | 1. Buat objek `Resep`, insert via `resepDAO.insert(resep)`<br>2. Dapatkan id resep yang baru dibuat (*perlu method `getGeneratedKeys`*)<br>3. Loop untuk setiap item:<br>&nbsp;&nbsp;- Buat `ResepObat(resepId, obatId, jumlah, aturanPakai)`<br>&nbsp;&nbsp;- `resepObatDAO.insert(resepObat)`<br>&nbsp;&nbsp;- Baca stok obat saat ini dari `obatDAO.findById(obatId).getStok()`<br>&nbsp;&nbsp;- Hitung stok baru = stok - jumlah<br>&nbsp;&nbsp;- `obatDAO.updateStok(obatId, stokBaru)`<br>4. `pendaftaranDAO.updateStatus(pendaftaranId, "SELESAI")` (setelah periksa + resep selesai) |
| **⚠️ Catatan** | Untuk `getGeneratedKeys`: lihat spesifikasi teknis di bagian bawah dokumen ini |
| **Return** | `void` |

---

#### D.2.7 Pembayaran

**Method: `prosesPembayaran(int pendaftaranId, String metodeBayar)`**

| Aspek | Detail |
|-------|--------|
| **Tujuan** | Memproses pembayaran pasien setelah selesai diperiksa |
| **Validasi** | Pastikan `pendaftaranId` ada. Status pendaftaran harus "SELESAI" |
| **Alur** | 1. Dapatkan data `Pendaftaran`<br>2. Dapatkan `RekamMedis` terkait<br>3. Dapatkan `List<Resep>` terkait<br>4. Hitung total:<br>&nbsp;&nbsp;- Biaya pendaftaran: Rp 10.000 (gunakan konstanta)<br>&nbsp;&nbsp;- Biaya tindakan: Rp 25.000 (gunakan konstanta)<br>&nbsp;&nbsp;- Biaya obat: jumlahkan `hargaSatuan * jumlah` dari tiap item resep<br>5. Buat objek `Pembayaran`, insert via `pembayaranDAO.insert`<br>6. Dapatkan id pembayaran (via `getGeneratedKeys`)<br>7. Buat 3 `DetailPembayaran`:<br>&nbsp;&nbsp;- "Biaya Pendaftaran" → 10000<br>&nbsp;&nbsp;- "Biaya Tindakan Medis" → 25000<br>&nbsp;&nbsp;- "Obat: [nama obat]" → subtotal per obat<br>8. Insert semua detail via `detailPembayaranDAO.insert()`<br>9. Update status pendaftaran → "SELESAI" |
| **Return** | `double` — total bayar |

**Method: `laporanPendapatan(Date start, Date end)`**

| Aspek | Detail |
|-------|--------|
| **Alur** | `pembayaranDAO.findByTanggal(start, end)` |
| **Return** | `List<Pembayaran>` |

---

### D.3 Catatan Teknis: getGeneratedKeys

Saat melakukan INSERT pada tabel dengan `AUTO_INCREMENT`, kita perlu mendapatkan ID yang baru saja di-generate oleh database. Ini dilakukan dengan:

```java
// Saat insert, beri tahu PreparedStatement bahwa kita ingin mengambil generated keys
PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
ps.executeUpdate();

// Ambil generated keys
ResultSet rs = ps.getGeneratedKeys();
if (rs.next()) {
    int generatedId = rs.getInt(1);
    // Set id ke objek entity
    objek.setId(generatedId);
}
```

Ini penting untuk method `buatResep()` (mendapatkan `resepId`) dan `prosesPembayaran()` (mendapatkan `pembayaranId`).

---

## Bagian E: Spesifikasi MainApp & Config (Androni)

### E.1 DatabaseConfig.java

**Package:** `config` | **File:** `src/config/DatabaseConfig.java`

```java
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DatabaseConfig {
    private static String URL;
    private static String USER;
    private static String PASSWORD;

    // Static initializer: baca dari file .env dulu, fallback ke System.getenv()
    static {
        bacaFileEnv();
        if (URL == null) URL = System.getenv("DB_URL");
        if (USER == null) USER = System.getenv("DB_USER");
        if (PASSWORD == null) PASSWORD = System.getenv("DB_PASSWORD");
    }

    private static void bacaFileEnv() {
        String envPath = ".env";
        try (BufferedReader reader = new BufferedReader(new FileReader(envPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;

                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    switch (key) {
                        case "DB_URL"      -> URL = value;
                        case "DB_USER"     -> USER = value;
                        case "DB_PASSWORD" -> PASSWORD = value;
                    }
                }
            }
        } catch (IOException e) {
            // File .env tidak ditemukan — nanti fallback ke System.getenv()
        }
    }

    public static Connection getConnection() throws SQLException {
        if (URL == null || USER == null) {
            throw new SQLException(
                "Kredensial database tidak ditemukan. " +
                "Buat file .env (copy dari .env.example) atau set environment variable DB_URL, DB_USER, DB_PASSWORD."
            );
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
```

### E.2 MainApp.java — Struktur Menu

**Package:** (default/tanpa package) | **File:** `src/MainApp.java`

**Struktur main method:**
```java
import java.util.*;
import config.DatabaseConfig;
import entity.*;
import dao.*;
import service.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class MainApp {
    private static final double BIAYA_PENDAFTARAN = 10000;
    private static final double BIAYA_TINDAKAN = 25000;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try (Connection conn = DatabaseConfig.getConnection()) {
            // Buat semua DAOImpl
            PasienDAO pasienDAO = new PasienDAOImpl(conn);
            DokterDAO dokterDAO = new DokterDAOImpl(conn);
            // ... buat semua DAOImpl

            // Buat Service
            KlinikService service = new KlinikService(
                pasienDAO, dokterDAO, obatDAO, pendaftaranDAO,
                rekamMedisDAO, resepDAO, resepObatDAO,
                pembayaranDAO, detailPembayaranDAO
            );

            // Loop menu
            while (true) {
                tampilkanMenu();
                int pilihan = Integer.parseInt(scanner.nextLine().trim());
                switch (pilihan) {
                    case 1: menuPasien(service); break;
                    case 2: menuDokter(service); break;
                    case 3: menuObat(service); break;
                    case 4: menuPendaftaran(service); break;
                    case 5: menuRekamMedis(service); break;
                    case 6: menuPembayaran(service); break;
                    case 7: menuLaporan(service); break;
                    case 0: System.out.println("Terima kasih!"); return;
                    default: System.out.println("Pilihan tidak valid!");
                }
            }
        } catch (SQLException e) {
            System.err.println("Koneksi database gagal: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Input harus berupa angka!");
        }
    }
}
```

**Spesifikasi method tampilkanMenu():**
```java
private static void tampilkanMenu() {
    System.out.println("\n=== SISTEM MANAJEMEN KLINIK ===");
    System.out.println("1. Manajemen Pasien");
    System.out.println("2. Manajemen Dokter");
    System.out.println("3. Manajemen Obat");
    System.out.println("4. Pendaftaran Pasien");
    System.out.println("5. Rekam Medis & Resep");
    System.out.println("6. Pembayaran");
    System.out.println("7. Laporan");
    System.out.println("0. Keluar");
    System.out.print("Pilih menu: ");
}
```

**Spesifikasi method menuPasien(KlinikService service):**
```java
private static void menuPasien(KlinikService service) {
    System.out.println("\n--- MANAJEMEN PASIEN ---");
    System.out.println("1. Tambah Pasien");
    System.out.println("2. Lihat Semua Pasien");
    System.out.println("3. Cari Pasien");
    System.out.println("4. Ubah Pasien");
    System.out.println("5. Hapus Pasien");
    System.out.println("0. Kembali");
    System.out.print("Pilih: ");

    int pilih = Integer.parseInt(scanner.nextLine().trim());
    try {
        switch (pilih) {
            case 1:
                System.out.print("Nama: "); String nama = scanner.nextLine();
                // ... input field lain
                service.tambahPasien(nama, alamat, noTelepon, tanggalLahir, jenisKelamin);
                System.out.println("Pasien berhasil ditambahkan!");
                break;
            case 2:
                List<Pasien> list = service.semuaPasien();
                // Tampilkan dalam format tabel
                for (Pasien p : list) {
                    System.out.println(p.getId() + " | " + p.getNama() + " | " + p.getAlamat());
                }
                break;
            case 3:
                System.out.print("ID Pasien: ");
                int id = Integer.parseInt(scanner.nextLine());
                Pasien p = service.cariPasienById(id);
                if (p != null) {
                    System.out.println(p.getId() + " | " + p.getNama() + " | " + p.getAlamat());
                } else {
                    System.out.println("Pasien tidak ditemukan!");
                }
                break;
            // case 4: ubah, case 5: hapus
        }
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
```

**⚠️ Buat method menu serupa untuk:**
- `menuDokter(KlinikService service)` — sub-menu: tambah, lihat, cari, ubah, hapus
- `menuObat(KlinikService service)` — sub-menu: tambah, lihat, cari, ubah, hapus
- `menuPendaftaran(KlinikService service)` — sub-menu: daftar, lihat antrian
- `menuRekamMedis(KlinikService service)` — sub-menu: periksa, lihat rekam medis, riwayat pasien
- `menuPembayaran(KlinikService service)` — sub-menu: proses bayar
- `menuLaporan(KlinikService service)` — sub-menu: laporan pendapatan per periode

Setiap method menu harus **membaca input dari user** menggunakan `scanner.nextLine()`, **memanggil method service** yang sesuai, dan **menampilkan hasil** ke console.

---

## Bagian F: Spesifikasi dbklinik.sql (Androni)

**File:** `src/dbklinik.sql`

Buat file SQL yang berisi:

1. `CREATE DATABASE IF NOT EXISTS dbklinik;`
2. `USE dbklinik;`
3. 9 `CREATE TABLE` statement (lihat STRUCTURE-DESIGN.md bagian 6 untuk DDL lengkap)
4. Data sample untuk testing:

```sql
-- Data sample
INSERT INTO pasien (nama, alamat, no_telepon, tanggal_lahir, jenis_kelamin) VALUES
('Budi Santoso', 'Jl. Merdeka No. 1', '081234567890', '1990-05-12', 'L'),
('Siti Rahma', 'Jl. Sudirman No. 5', '082345678901', '1995-08-22', 'P'),
('Ahmad Hidayat', 'Jl. Gatot Subroto No. 10', '083456789012', '1985-01-30', 'L');

INSERT INTO dokter (nama, spesialisasi, no_telepon, jadwal_praktek) VALUES
('Dr. Andi Pratama', 'Umum', '087654321098', 'Senin-Jumat 08:00-16:00'),
('Dr. Dewi Lestari', 'Anak', '087654321099', 'Senin-Sabtu 09:00-15:00');

INSERT INTO obat (nama_obat, satuan, harga_satuan, stok) VALUES
('Paracetamol', 'tablet', 5000, 100),
('Amoxicillin', 'kapsul', 10000, 50),
('Vitamin C', 'tablet', 3000, 200),
('Antasida', 'tablet', 4000, 150),
('Dextromethorphan', 'botol', 15000, 30);
```

---

## 📋 Ringkasan Semua Method yang Harus Dibuat

| Anggota | Jumlah Method | File |
|---------|:-------------:|------|
| **Rahman** | ~70 getter/setter | 9 entity |
| **Ariq** | ~45 method DAOImpl | 18 DAO files |
| **Winata** | ~20 method service | 1 KlinikService |
| **Androni** | ~10 method menu | 1 MainApp + 3 config |

**Total: ~145 method untuk seluruh sistem.**
