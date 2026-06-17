# STRUCTURE-DESIGN.md — Cetak Biru Sistem
## Sistem Manajemen Klinik — Tugas UAS PBO

---

## 1. Class Diagram (Relasi Antar Class)

```
┌─────────────┐     ┌──────────────────┐     ┌───────────────────┐
│  DatabaseConfig │     │  MainApp         │     │  KlinikService    │
│  (config)       │────▶│  (main/UI)       │────▶│  (service)        │
└─────────────┘     └──────────────────┘     └───────────────────┘
                                                      │
                                                      │ depends on (interface)
                                                      ▼
              ┌───────────────────────────────────────────────────┐
              │              DAO Interfaces (dao/)                │
              │  «interface»  «interface»  «interface»  ...       │
              │  PasienDAO    DokterDAO    ObatDAO     ...        │
              └───────────────────────────────────────────────────┘
                                                      │
                                                      │ implemented by
                                                      ▼
              ┌───────────────────────────────────────────────────┐
              │          DAO Implementations (dao/)               │
              │  PasienDAOImpl  DokterDAOImpl  ObatDAOImpl  ...   │
              └───────────────────────────────────────────────────┘
                                                      │
                                                      │ uses
                                                      ▼
              ┌───────────────────────────────────────────────────┐
              │          Entity Classes (entity/)                 │
              │  Pasien  Dokter  Obat  Pendaftaran  RekamMedis    │
              │  Resep  ResepObat  Pembayaran  DetailPembayaran  │
              └───────────────────────────────────────────────────┘
```

---

## 2. Entity Classes (POJO)

### 2.1 Pasien.java
**Package:** `entity`

| Access Modifier | Tipe | Nama Field | Keterangan |
|-----------------|------|------------|------------|
| `private` | `int` | `id` | Primary Key |
| `private` | `String` | `nama` | Nama lengkap pasien |
| `private` | `String` | `alamat` | Alamat rumah |
| `private` | `String` | `noTelepon` | Nomor HP |
| `private` | `String` | `tanggalLahir` | Format: YYYY-MM-DD |
| `private` | `String` | `jenisKelamin` | "L" / "P" |
| `private` | `Date` | `createdAt` | Auto dari DB (java.sql.Date) |

**Constructor:**
- `Pasien(String nama, String alamat, String noTelepon, String tanggalLahir, String jenisKelamin)`
- `Pasien()` — default

**Methods:** Getter & Setter untuk semua field.

---

### 2.2 Dokter.java
**Package:** `entity`

| Access Modifier | Tipe | Nama Field | Keterangan |
|-----------------|------|------------|------------|
| `private` | `int` | `id` | Primary Key |
| `private` | `String` | `nama` | Nama lengkap dokter |
| `private` | `String` | `spesialisasi` | Misal: "Umum", "Anak", "Mata" |
| `private` | `String` | `noTelepon` | Nomor HP |
| `private` | `String` | `jadwalPraktek` | Misal: "Senin-Jumat 08:00-16:00" |

**Constructor:**
- `Dokter(String nama, String spesialisasi, String noTelepon, String jadwalPraktek)`
- `Dokter()` — default

**Methods:** Getter & Setter untuk semua field.

---

### 2.3 Obat.java
**Package:** `entity`

| Access Modifier | Tipe | Nama Field | Keterangan |
|-----------------|------|------------|------------|
| `private` | `int` | `id` | Primary Key |
| `private` | `String` | `namaObat` | Nama obat |
| `private` | `String` | `satuan` | Satuan: "tablet", "botol", "strip", "kaplet" |
| `private` | `double` | `hargaSatuan` | Harga per satuan |
| `private` | `int` | `stok` | Jumlah stok tersedia |

**Constructor:**
- `Obat(String namaObat, String satuan, double hargaSatuan, int stok)`
- `Obat()` — default

**Methods:** Getter & Setter untuk semua field.

---

### 2.4 Pendaftaran.java
**Package:** `entity`

| Access Modifier | Tipe | Nama Field | Keterangan |
|-----------------|------|------------|------------|
| `private` | `int` | `id` | Primary Key |
| `private` | `int` | `pasienId` | FK → Pasien.id |
| `private` | `int` | `dokterId` | FK → Dokter.id |
| `private` | `Date` | `tanggalDaftar` | Tanggal pendaftaran |
| `private` | `String` | `keluhan` | Keluhan awal pasien |
| `private` | `String` | `status` | "MENUNGGU" / "DIPERIKSA" / "SELESAI" |

**Constructor:**
- `Pendaftaran(int pasienId, int dokterId, String keluhan)`
- `Pendaftaran()` — default

**Methods:** Getter & Setter untuk semua field.

---

### 2.5 RekamMedis.java
**Package:** `entity`

| Access Modifier | Tipe | Nama Field | Keterangan |
|-----------------|------|------------|------------|
| `private` | `int` | `id` | Primary Key |
| `private` | `int` | `pendaftaranId` | FK → Pendaftaran.id (UNIQUE) |
| `private` | `int` | `dokterId` | FK → Dokter.id |
| `private` | `int` | `pasienId` | FK → Pasien.id |
| `private` | `String` | `diagnosa` | Diagnosa dokter |
| `private` | `String` | `tindakan` | Tindakan medis |
| `private` | `String` | `catatanTambahan` | Catatan opsional |
| `private` | `Date` | `tanggalPeriksa` | Tanggal pemeriksaan |

**Constructor:**
- `RekamMedis(int pendaftaranId, int dokterId, int pasienId, String diagnosa, String tindakan)`
- `RekamMedis()` — default

**Methods:** Getter & Setter untuk semua field.

---

### 2.6 Resep.java
**Package:** `entity`

| Access Modifier | Tipe | Nama Field | Keterangan |
|-----------------|------|------------|------------|
| `private` | `int` | `id` | Primary Key |
| `private` | `int` | `rekamMedisId` | FK → RekamMedis.id |
| `private` | `Date` | `tanggalResep` | Tanggal resep dibuat |
| `private` | `String` | `keterangan` | Keterangan umum (opsional) |

**Constructor:**
- `Resep(int rekamMedisId, String keterangan)`
- `Resep()` — default

**Methods:** Getter & Setter untuk semua field.

---

### 2.7 ResepObat.java
**Package:** `entity`

| Access Modifier | Tipe | Nama Field | Keterangan |
|-----------------|------|------------|------------|
| `private` | `int` | `id` | Primary Key |
| `private` | `int` | `resepId` | FK → Resep.id |
| `private` | `int` | `obatId` | FK → Obat.id |
| `private` | `int` | `jumlah` | Jumlah obat |
| `private` | `String` | `aturanPakai` | Misal: "3x sehari setelah makan" |

**Constructor:**
- `ResepObat(int resepId, int obatId, int jumlah, String aturanPakai)`
- `ResepObat()` — default

**Methods:** Getter & Setter untuk semua field.

---

### 2.8 Pembayaran.java
**Package:** `entity`

| Access Modifier | Tipe | Nama Field | Keterangan |
|-----------------|------|------------|------------|
| `private` | `int` | `id` | Primary Key |
| `private` | `int` | `pendaftaranId` | FK → Pendaftaran.id (UNIQUE) |
| `private` | `Date` | `tanggalBayar` | Tanggal pembayaran |
| `private` | `double` | `totalBayar` | Total yang dibayar |
| `private` | `String` | `metodeBayar` | "TUNAI" / "TRANSFER" |

**Constructor:**
- `Pembayaran(int pendaftaranId, double totalBayar, String metodeBayar)`
- `Pembayaran()` — default

**Methods:** Getter & Setter untuk semua field.

---

### 2.9 DetailPembayaran.java
**Package:** `entity`

| Access Modifier | Tipe | Nama Field | Keterangan |
|-----------------|------|------------|------------|
| `private` | `int` | `id` | Primary Key |
| `private` | `int` | `pembayaranId` | FK → Pembayaran.id |
| `private` | `String` | `deskripsi` | Deskripsi item (misal: "Biaya Pendaftaran", "Obat: Paracetamol") |
| `private` | `double` | `jumlahBiaya` | Biaya item tersebut |

**Constructor:**
- `DetailPembayaran(int pembayaranId, String deskripsi, double jumlahBiaya)`
- `DetailPembayaran()` — default

**Methods:** Getter & Setter untuk semua field.

---

## 3. DAO Interface Layer

Semua DAO interface berada di package `dao`.

### 3.1 PasienDAO.java
```java
package dao;

import entity.Pasien;
import java.util.List;

public interface PasienDAO {
    void insert(Pasien pasien);
    List<Pasien> findAll();
    Pasien findById(int id);
    void update(Pasien pasien);
    void delete(int id);
}
```

### 3.2 DokterDAO.java
```java
package dao;

import entity.Dokter;
import java.util.List;

public interface DokterDAO {
    void insert(Dokter dokter);
    List<Dokter> findAll();
    Dokter findById(int id);
    void update(Dokter dokter);
    void delete(int id);
}
```

### 3.3 ObatDAO.java
```java
package dao;

import entity.Obat;
import java.util.List;

public interface ObatDAO {
    void insert(Obat obat);
    List<Obat> findAll();
    Obat findById(int id);
    void update(Obat obat);
    void delete(int id);
    void updateStok(int id, int stokBaru);  // khusus update stok
}
```

### 3.4 PendaftaranDAO.java
```java
package dao;

import entity.Pendaftaran;
import java.util.List;

public interface PendaftaranDAO {
    void insert(Pendaftaran pendaftaran);
    List<Pendaftaran> findAll();
    Pendaftaran findById(int id);
    List<Pendaftaran> findByStatus(String status);  // filter by status
    void updateStatus(int id, String status);       // ubah status
}
```

### 3.5 RekamMedisDAO.java
```java
package dao;

import entity.RekamMedis;
import java.util.List;

public interface RekamMedisDAO {
    void insert(RekamMedis rekamMedis);
    RekamMedis findById(int id);
    RekamMedis findByPendaftaranId(int pendaftaranId);  // UNIQUE
    List<RekamMedis> findByPasienId(int pasienId);       // riwayat pasien
}
```

### 3.6 ResepDAO.java
```java
package dao;

import entity.Resep;
import java.util.List;

public interface ResepDAO {
    void insert(Resep resep);
    Resep findById(int id);
    List<Resep> findByRekamMedisId(int rekamMedisId);
}
```

### 3.7 ResepObatDAO.java
```java
package dao;

import entity.ResepObat;
import java.util.List;

public interface ResepObatDAO {
    void insert(ResepObat resepObat);
    List<ResepObat> findByResepId(int resepId);
}
```

### 3.8 PembayaranDAO.java
```java
package dao;

import entity.Pembayaran;
import java.util.List;
import java.util.Date;

public interface PembayaranDAO {
    void insert(Pembayaran pembayaran);
    Pembayaran findById(int id);
    Pembayaran findByPendaftaranId(int pendaftaranId);  // UNIQUE
    List<Pembayaran> findByTanggal(Date start, Date end); // laporan
    List<Pembayaran> findAll();
}
```

### 3.9 DetailPembayaranDAO.java
```java
package dao;

import entity.DetailPembayaran;
import java.util.List;

public interface DetailPembayaranDAO {
    void insert(DetailPembayaran detail);
    List<DetailPembayaran> findByPembayaranId(int pembayaranId);
}
```

---

## 4. DAO Implementations (JDBC)

Setiap DAOImpl mengimplementasikan interface DAO masing-masing dan memiliki:

- **Dependency Injection**: Menerima `Connection` melalui constructor
- **PreparedStatement**: Semua query menggunakan parameter binding
- **try-with-resources**: Untuk Connection, PreparedStatement, dan ResultSet
- **Private helper methods**: Untuk mapping ResultSet ke objek Entity (optional)

### Contoh Template DAOImpl:

```java
package dao;

import entity.Pasien;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PasienDAOImpl implements PasienDAO {
    private Connection conn;

    // Constructor Injection — Connection dari luar
    public PasienDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Pasien pasien) {
        String sql = "INSERT INTO pasien (nama, alamat, no_telepon, tanggal_lahir, jenis_kelamin) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, pasien.getNama());
            ps.setString(2, pasien.getAlamat());
            ps.setString(3, pasien.getNoTelepon());
            ps.setString(4, pasien.getTanggalLahir());
            ps.setString(5, pasien.getJenisKelamin());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Gagal insert pasien: " + e.getMessage(), e);
        }
    }

    // ... method lainnya mengikuti pola yang sama
}
```

**Catatan untuk Ariq (DAO Developer):** Lihat `TODO-SPECS.md` untuk spes lengkap setiap method di semua DAOImpl.

---

## 5. Service Layer

**Class:** `KlinikService` di package `service`

### Constructor Injection
```java
public class KlinikService {
    private PasienDAO pasienDAO;
    private DokterDAO dokterDAO;
    private ObatDAO obatDAO;
    private PendaftaranDAO pendaftaranDAO;
    private RekamMedisDAO rekamMedisDAO;
    private ResepDAO resepDAO;
    private ResepObatDAO resepObatDAO;
    private PembayaranDAO pembayaranDAO;
    private DetailPembayaranDAO detailPembayaranDAO;

    // Constructor Injection — semua DAO diterima dari luar
    public KlinikService(PasienDAO pasienDAO, DokterDAO dokterDAO, ObatDAO obatDAO,
                         PendaftaranDAO pendaftaranDAO, RekamMedisDAO rekamMedisDAO,
                         ResepDAO resepDAO, ResepObatDAO resepObatDAO,
                         PembayaranDAO pembayaranDAO, DetailPembayaranDAO detailPembayaranDAO) {
        this.pasienDAO = pasienDAO;
        this.dokterDAO = dokterDAO;
        // ... set semua
    }
}
```

### Daftar Method Bisnis

| Return | Method Signature | Deskripsi |
|--------|-----------------|-----------|
| `void` | `tambahPasien(String nama, String alamat, String noTelepon, String tanggalLahir, String jenisKelamin)` | Validasi & tambah pasien baru |
| `List<Pasien>` | `semuaPasien()` | Ambil semua pasien |
| `Pasien` | `cariPasienById(int id)` | Cari pasien berdasarkan ID |
| `void` | `ubahPasien(Pasien pasien)` | Ubah data pasien |
| `void` | `hapusPasien(int id)` | Hapus pasien |
| `void` | `tambahDokter(String nama, String spesialisasi, String noTelepon, String jadwalPraktek)` | Validasi & tambah dokter baru |
| `List<Dokter>` | `semuaDokter()` | Ambil semua dokter |
| `Dokter` | `cariDokterById(int id)` | Cari dokter |
| `void` | `ubahDokter(Dokter dokter)` | Ubah dokter |
| `void` | `hapusDokter(int id)` | Hapus dokter |
| `void` | `tambahObat(String namaObat, String satuan, double harga, int stok)` | Validasi & tambah obat |
| `List<Obat>` | `semuaObat()` | Ambil semua obat |
| `Obat` | `cariObatById(int id)` | Cari obat |
| `void` | `ubahObat(Obat obat)` | Ubah obat |
| `void` | `hapusObat(int id)` | Hapus obat |
| `void` | `daftarPasien(int pasienId, int dokterId, String keluhan)` | Daftarkan pasien ke dokter |
| `List<Pendaftaran>` | `lihatAntrian(String status)` | Lihat antrian by status |
| `Pendaftaran` | `cariPendaftaranById(int id)` | Cari pendaftaran |
| `void` | `periksaPasien(int pendaftaranId, String diagnosa, String tindakan, String catatan)` | Dokter periksa pasien |
| `RekamMedis` | `lihatRekamMedis(int pendaftaranId)` | Lihat rekam medis per pendaftaran |
| `List<RekamMedis>` | `riwayatPasien(int pasienId)` | Riwayat rekam medis pasien |
| `void` | `buatResep(int rekamMedisId, List<Integer> obatIds, List<Integer> jumlahs, List<String> aturanPakai)` | Buat resep beserta obat-obatnya |
| `double` | `prosesPembayaran(int pendaftaranId, String metodeBayar)` | Proses pembayaran, return total |
| `List<Pembayaran>` | `laporanPendapatan(Date start, Date end)` | Laporan pendapatan per periode |

---

## 6. Database Schema (MySQL)

### Database: `dbklinik`

### Tabel 1: pasien
```sql
CREATE TABLE pasien (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nama VARCHAR(100) NOT NULL,
    alamat TEXT,
    no_telepon VARCHAR(20),
    tanggal_lahir DATE,
    jenis_kelamin ENUM('L', 'P'),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;
```

### Tabel 2: dokter
```sql
CREATE TABLE dokter (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nama VARCHAR(100) NOT NULL,
    spesialisasi VARCHAR(50),
    no_telepon VARCHAR(20),
    jadwal_praktek VARCHAR(100)
) ENGINE=InnoDB;
```

### Tabel 3: obat
```sql
CREATE TABLE obat (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nama_obat VARCHAR(100) NOT NULL,
    satuan VARCHAR(20),
    harga_satuan DOUBLE NOT NULL,
    stok INT DEFAULT 0
) ENGINE=InnoDB;
```

### Tabel 4: pendaftaran
```sql
CREATE TABLE pendaftaran (
    id INT PRIMARY KEY AUTO_INCREMENT,
    pasien_id INT NOT NULL,
    dokter_id INT NOT NULL,
    tanggal_daftar DATE NOT NULL,
    keluhan TEXT,
    status ENUM('MENUNGGU', 'DIPERIKSA', 'SELESAI') DEFAULT 'MENUNGGU',
    FOREIGN KEY (pasien_id) REFERENCES pasien(id) ON DELETE CASCADE,
    FOREIGN KEY (dokter_id) REFERENCES dokter(id) ON DELETE CASCADE
) ENGINE=InnoDB;
```

### Tabel 5: rekam_medis
```sql
CREATE TABLE rekam_medis (
    id INT PRIMARY KEY AUTO_INCREMENT,
    pendaftaran_id INT NOT NULL UNIQUE,
    dokter_id INT NOT NULL,
    pasien_id INT NOT NULL,
    diagnosa TEXT,
    tindakan TEXT,
    catatan_tambahan TEXT,
    tanggal_periksa DATE NOT NULL,
    FOREIGN KEY (pendaftaran_id) REFERENCES pendaftaran(id) ON DELETE CASCADE,
    FOREIGN KEY (dokter_id) REFERENCES dokter(id),
    FOREIGN KEY (pasien_id) REFERENCES pasien(id)
) ENGINE=InnoDB;
```

### Tabel 6: resep
```sql
CREATE TABLE resep (
    id INT PRIMARY KEY AUTO_INCREMENT,
    rekam_medis_id INT NOT NULL,
    tanggal_resep DATE NOT NULL,
    keterangan TEXT,
    FOREIGN KEY (rekam_medis_id) REFERENCES rekam_medis(id) ON DELETE CASCADE
) ENGINE=InnoDB;
```

### Tabel 7: resep_obat
```sql
CREATE TABLE resep_obat (
    id INT PRIMARY KEY AUTO_INCREMENT,
    resep_id INT NOT NULL,
    obat_id INT NOT NULL,
    jumlah INT NOT NULL,
    aturan_pakai VARCHAR(200),
    FOREIGN KEY (resep_id) REFERENCES resep(id) ON DELETE CASCADE,
    FOREIGN KEY (obat_id) REFERENCES obat(id)
) ENGINE=InnoDB;
```

### Tabel 8: pembayaran
```sql
CREATE TABLE pembayaran (
    id INT PRIMARY KEY AUTO_INCREMENT,
    pendaftaran_id INT NOT NULL UNIQUE,
    tanggal_bayar DATE NOT NULL,
    total_bayar DOUBLE NOT NULL,
    metode_bayar ENUM('TUNAI', 'TRANSFER') DEFAULT 'TUNAI',
    FOREIGN KEY (pendaftaran_id) REFERENCES pendaftaran(id) ON DELETE CASCADE
) ENGINE=InnoDB;
```

### Tabel 9: detail_pembayaran
```sql
CREATE TABLE detail_pembayaran (
    id INT PRIMARY KEY AUTO_INCREMENT,
    pembayaran_id INT NOT NULL,
    deskripsi VARCHAR(200),
    jumlah_biaya DOUBLE NOT NULL,
    FOREIGN KEY (pembayaran_id) REFERENCES pembayaran(id) ON DELETE CASCADE
) ENGINE=InnoDB;
```

---

## 7. SOLID Compliance Matrix

| Prinsip | Penerapan di Sistem | File Terkait |
|---------|-------------------|--------------|
| **SRP** | Entity = data saja. DAOImpl = SQL saja. Service = bisnis saja. MainApp = UI saja | Semua file |
| **OCP** | Interface DAO bisa diimplementasi ulang untuk database lain tanpa modifikasi | DAO Interface |
| **LSP** | Implementasi DAOImpl bisa diganti dengan versi lain tanpa efek samping | DAOImpl |
| **ISP** | Setiap DAO interface hanya punya method yang relevan untuk entity-nya | DAO Interface |
| **DIP** | Service bergantung pada Interface DAO, bukan implementasi konkret | `KlinikService.java` |

---

## 8. Mapping Entity ke Database

| Entity Class | Tabel Database | Mapping Field → Kolom |
|-------------|----------------|----------------------|
| `Pasien` | `pasien` | id→id, nama→nama, alamat→alamat, noTelepon→no_telepon, tanggalLahir→tanggal_lahir, jenisKelamin→jenis_kelamin, createdAt→created_at |
| `Dokter` | `dokter` | id→id, nama→nama, spesialisasi→spesialisasi, noTelepon→no_telepon, jadwalPraktek→jadwal_praktek |
| `Obat` | `obat` | id→id, namaObat→nama_obat, satuan→satuan, hargaSatuan→harga_satuan, stok→stok |
| `Pendaftaran` | `pendaftaran` | id→id, pasienId→pasien_id, dokterId→dokter_id, tanggalDaftar→tanggal_daftar, keluhan→keluhan, status→status |
| `RekamMedis` | `rekam_medis` | id→id, pendaftaranId→pendaftaran_id, dokterId→dokter_id, pasienId→pasien_id, diagnosa→diagnosa, tindakan→tindakan, catatanTambahan→catatan_tambahan, tanggalPeriksa→tanggal_periksa |
| `Resep` | `resep` | id→id, rekamMedisId→rekam_medis_id, tanggalResep→tanggal_resep, keterangan→keterangan |
| `ResepObat` | `resep_obat` | id→id, resepId→resep_id, obatId→obat_id, jumlah→jumlah, aturanPakai→aturan_pakai |
| `Pembayaran` | `pembayaran` | id→id, pendaftaranId→pendaftaran_id, tanggalBayar→tanggal_bayar, totalBayar→total_bayar, metodeBayar→metode_bayar |
| `DetailPembayaran` | `detail_pembayaran` | id→id, pembayaranId→pembayaran_id, deskripsi→deskripsi, jumlahBiaya→jumlah_biaya |

---

## 9. Arsitektur MainApp (Menu CLI)

```
=== SISTEM MANAJEMEN KLINIK ===
1. Manajemen Pasien
2. Manajemen Dokter
3. Manajemen Obat
4. Pendaftaran Pasien
5. Rekam Medis & Resep
6. Pembayaran
7. Laporan
0. Keluar
Pilih menu: _
```

Setiap menu akan memiliki sub-menu sesuai operasi CRUD dan method yang dipanggil dari `KlinikService`.

**Teknis:**
- `MainApp` menggunakan `Scanner(System.in)` untuk input
- Input dari user di-parse dan divalidasi secara sederhana
- Hasil operasi ditampilkan di console
- Exception handling: try-catch di setiap blok menu, error ditampilkan ke user
