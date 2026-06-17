# Product Requirements Document (PRD)
## Sistem Manajemen Klinik — Tugas UAS PBO

---

## 1. Informasi Proyek

| Atribut | Detail |
|---------|--------|
| **Nama Proyek** | Sistem Manajemen Klinik (Clinic Management System) |
| **Mata Kuliah** | Pemrograman Berbasis Objek |
| **Topik** | JDBC & Integrasi OOP dengan Database |
| **Platform** | Console-based CLI + MySQL |
| **Kelompok** | 4 Orang |

### Tim Pengembang

| Nama | Role | Kontribusi |
|------|------|------------|
| **Winata** | Team Leader & Service Layer | KlinikService, koordinasi tim, review kode |
| **Rahman** | Entity Layer | 9 class entity POJO |
| **Ariq** | DAO Layer | 9 interface DAO + 9 DAOImpl JDBC |
| **Androni** | MainApp & Config | DatabaseConfig, MainApp CLI, SQL script, setup proyek |

---

## 2. Latar Belakang

Sistem Manajemen Klinik adalah aplikasi berbasis console Java yang dibangun sebagai proyek UAS mata kuliah Pemrograman Berbasis Objek. Aplikasi ini mengimplementasikan:

- **Java Database Connectivity (JDBC)** untuk persistensi data ke MySQL
- **DAO Pattern** untuk pemisahan layer akses data
- **Prinsip SOLID** untuk arsitektur yang bersih dan maintainable
- **OOP Principles**: Encapsulation, Inheritance, Polymorphism, Abstraction

---

## 3. Tujuan

1. Membangun sistem informasi klinik yang dapat mengelola data pasien, dokter, obat, pendaftaran, rekam medis, resep, dan pembayaran
2. Menerapkan DAO pattern dan SOLID principles secara disiplin
3. Menghasilkan kode yang aman dari SQL Injection (PreparedStatement)
4. Menghasilkan kode yang bersih dan mudah dipelihara

---

## 4. Target Pengguna

| Pengguna | Deskripsi |
|----------|-----------|
| **Staff Administrasi** | Mendaftarkan pasien, mengelola data pasien & dokter, melakukan pembayaran |
| **Dokter** | Melakukan pemeriksaan, mengisi rekam medis, membuat resep obat |
| **Kasir** | Memproses pembayaran pasien |
| **Manajer Klinik** | Melihat laporan pendapatan dan data operasional |

*(Sistem menggunakan console yang sama dengan menu berbeda untuk tiap peran)*

---

## 5. Fitur Sistem (Functional Requirements)

### F-01: Manajemen Pasien
- Tambah data pasien baru
- Lihat daftar semua pasien
- Cari pasien berdasarkan ID
- Ubah data pasien
- Hapus data pasien

### F-02: Manajemen Dokter
- Tambah data dokter baru
- Lihat daftar semua dokter
- Cari dokter berdasarkan ID
- Ubah data dokter
- Hapus data dokter

### F-03: Manajemen Obat
- Tambah data obat baru (nama, satuan, harga, stok)
- Lihat daftar semua obat
- Cari obat berdasarkan ID
- Ubah data obat
- Hapus data obat

### F-04: Pendaftaran Pasien
- Daftarkan pasien ke dokter tertentu
- Masukkan keluhan awal pasien
- Lihat status antrian (MENUNGGU / DIPERIKSA / SELESAI)

### F-05: Rekam Medis
- Dokter mengisi diagnosa dan tindakan medis
- Lihat riwayat rekam medis pasien berdasarkan ID pasien

### F-06: Resep Obat
- Dokter membuat resep untuk pasien
- Resep berisi satu atau lebih obat dengan aturan pakai
- Stok obat berkurang saat resep dibuat

### F-07: Pembayaran
- Hitung total biaya: biaya pendaftaran + biaya obat + biaya tindakan
- Rincian pembayaran per item (detail)
- Pilih metode bayar (TUNAI / TRANSFER)

### F-08: Laporan
- Laporan pendapatan per periode (harian/bulanan)
- Riwayat pembayaran pasien

---

## 6. Acceptance Criteria

### F-01 Manajemen Pasien
- [ ] Admin dapat menambahkan pasien baru dengan data: nama, alamat, no telepon, tanggal lahir, jenis kelamin
- [ ] Admin dapat melihat daftar semua pasien dalam format tabel
- [ ] Admin dapat mencari pasien berdasarkan ID
- [ ] Admin dapat mengupdate data pasien
- [ ] Admin dapat menghapus pasien
- [ ] Sistem menolak jika nama pasien kosong

### F-02 Manajemen Dokter
- [ ] Admin dapat menambahkan dokter dengan data: nama, spesialisasi, no telepon, jadwal praktek
- [ ] Admin dapat melihat daftar semua dokter
- [ ] Admin dapat mencari dokter berdasarkan ID
- [ ] Admin dapat mengupdate data dokter
- [ ] Admin dapat menghapus dokter

### F-03 Manajemen Obat
- [ ] Admin dapat menambahkan obat dengan data: nama, satuan, harga, stok
- [ ] Admin dapat melihat daftar semua obat
- [ ] Admin dapat mencari obat berdasarkan ID
- [ ] Admin dapat mengupdate data obat
- [ ] Admin dapat menghapus obat

### F-04 Pendaftaran
- [ ] Staff dapat mendaftarkan pasien yang sudah terdaftar ke dokter yang tersedia
- [ ] Staff memasukkan keluhan awal pasien
- [ ] Status awal pendaftaran adalah "MENUNGGU"
- [ ] Staff dapat melihat daftar antrian

### F-05 Rekam Medis
- [ ] Dokter dapat mengisi diagnosa dan tindakan untuk pasien yang sudah diperiksa
- [ ] Rekam medis terhubung dengan pendaftaran yang sesuai
- [ ] Dokter dapat melihat riwayat rekam medis pasien

### F-06 Resep
- [ ] Dokter dapat membuat resep yang berisi satu atau lebih obat
- [ ] Setiap item resep mencantumkan jumlah dan aturan pakai
- [ ] Stok obat berkurang otomatis saat resep dibuat
- [ ] Sistem menolak jika stok obat tidak mencukupi

### F-07 Pembayaran
- [ ] Kasir dapat memproses pembayaran untuk pasien yang sudah selesai diperiksa
- [ ] Total biaya dihitung otomatis dari: biaya pendaftaran + biaya obat + biaya tindakan
- [ ] Rincian pembayaran ditampilkan per item
- [ ] Pembayaran dapat dilakukan secara TUNAI atau TRANSFER

### F-08 Laporan
- [ ] Manajer dapat melihat laporan pendapatan berdasarkan rentang tanggal
- [ ] Laporan menampilkan total pendapatan dan rincian per transaksi

---

## 7. Non-Functional Requirements

| Kategori | Requirement |
|----------|-------------|
| **Keamanan** | Semua query SQL menggunakan `PreparedStatement` (`java.sql.PreparedStatement`). Dilarang menggunakan `Statement` untuk input dinamis |
| **Keamanan** | Kredensial database (URL, user, password) diambil dari environment variable (`System.getenv()`), tidak boleh hardcode |
| **Resource** | Semua koneksi database menggunakan `try-with-resources` untuk menjamin resource ditutup otomatis |
| **Arsitektur** | Wajib menggunakan DAO pattern: Entity → Interface DAO → DAOImpl |
| **Arsitektur** | Wajib memisahkan entity, DAO, service, dan config dalam package berbeda |
| **SOLID** | Kelas Entity hanya berisi data, tidak mengandung logic JDBC (SRP) |
| **SOLID** | Service bergantung pada Interface DAO, bukan implementasi konkret (DIP) |
| **Konvensi** | Semua nama variabel, method, dan class menggunakan camelCase |
| **Database** | MySQL dengan InnoDB engine untuk foreign key support |

---

## 8. Technology Stack

| Komponen | Teknologi |
|----------|-----------|
| Bahasa Pemrograman | Java SE (Standar Edition) |
| Database | MySQL (via XAMPP / Laragon) |
| JDBC Driver | MySQL Connector/J |
| Tools | VS Code, Terminal |
| Version Control | Git + GitHub |

---

## 9. Glossary

| Istilah | Definisi |
|---------|----------|
| POJO | Plain Old Java Object — class Java sederhana tanpa ketergantungan framework |
| DAO | Data Access Object — pola desain untuk memisahkan logika akses data |
| DIP | Dependency Inversion Principle — bergantung pada abstraksi, bukan konkret |
| SRP | Single Responsibility Principle — satu kelas satu tanggung jawab |
| CRUD | Create, Read, Update, Delete — operasi dasar manipulasi data |
| CLI | Command Line Interface — antarmuka berbasis teks terminal |
