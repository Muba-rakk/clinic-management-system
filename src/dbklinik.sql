CREATE DATABASE IF NOT EXISTS dbklinik;
USE dbklinik;

DROP TABLE IF EXISTS detail_pembayaran;
DROP TABLE IF EXISTS pembayaran;
DROP TABLE IF EXISTS resep_obat;
DROP TABLE IF EXISTS resep;
DROP TABLE IF EXISTS rekam_medis;
DROP TABLE IF EXISTS pendaftaran;
DROP TABLE IF EXISTS obat;
DROP TABLE IF EXISTS dokter;
DROP TABLE IF EXISTS pasien;

CREATE TABLE pasien (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nama VARCHAR(100) NOT NULL,
    alamat TEXT,
    no_telepon VARCHAR(20),
    tanggal_lahir DATE,
    jenis_kelamin ENUM('L', 'P'),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

CREATE TABLE dokter (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nama VARCHAR(100) NOT NULL,
    spesialisasi VARCHAR(50),
    no_telepon VARCHAR(20),
    jadwal_praktek VARCHAR(100)
) ENGINE=InnoDB;

CREATE TABLE obat (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nama_obat VARCHAR(100) NOT NULL,
    satuan VARCHAR(20),
    harga_satuan DOUBLE NOT NULL,
    stok INT DEFAULT 0
) ENGINE=InnoDB;

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

CREATE TABLE resep (
    id INT PRIMARY KEY AUTO_INCREMENT,
    rekam_medis_id INT NOT NULL,
    tanggal_resep DATE NOT NULL,
    keterangan TEXT,
    FOREIGN KEY (rekam_medis_id) REFERENCES rekam_medis(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE resep_obat (
    id INT PRIMARY KEY AUTO_INCREMENT,
    resep_id INT NOT NULL,
    obat_id INT NOT NULL,
    jumlah INT NOT NULL,
    aturan_pakai VARCHAR(200),
    FOREIGN KEY (resep_id) REFERENCES resep(id) ON DELETE CASCADE,
    FOREIGN KEY (obat_id) REFERENCES obat(id)
) ENGINE=InnoDB;

CREATE TABLE pembayaran (
    id INT PRIMARY KEY AUTO_INCREMENT,
    pendaftaran_id INT NOT NULL UNIQUE,
    tanggal_bayar DATE NOT NULL,
    total_bayar DOUBLE NOT NULL,
    metode_bayar ENUM('TUNAI', 'TRANSFER') DEFAULT 'TUNAI',
    FOREIGN KEY (pendaftaran_id) REFERENCES pendaftaran(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE detail_pembayaran (
    id INT PRIMARY KEY AUTO_INCREMENT,
    pembayaran_id INT NOT NULL,
    deskripsi VARCHAR(200),
    jumlah_biaya DOUBLE NOT NULL,
    FOREIGN KEY (pembayaran_id) REFERENCES pembayaran(id) ON DELETE CASCADE
) ENGINE=InnoDB;

INSERT INTO pasien (nama, alamat, no_telepon, tanggal_lahir, jenis_kelamin) VALUES
('Heri Prompter', 'Jl. Melati No. 10', '081234567890', '1995-03-12', 'L'),
('Susi Sashimi', 'Jl. Kenanga No. 5', '081298765432', '1998-07-21', 'P'),
('Furiya Oslo', 'Jl. Mawar No. 8', '082112345678', '1990-11-03', 'L');

INSERT INTO dokter (nama, spesialisasi, no_telepon, jadwal_praktek) VALUES
('dr. Rina Pratama', 'Umum', '081111111111', 'Senin-Jumat 08:00-14:00'),
('dr. Hendra Saputra', 'Anak', '082222222222', 'Selasa-Sabtu 09:00-15:00');

INSERT INTO obat (nama_obat, satuan, harga_satuan, stok) VALUES
('Paracetamol', 'tablet', 1500, 100),
('Amoxicillin', 'kapsul', 3000, 80),
('Vitamin C', 'tablet', 1000, 120),
('OBH Combi', 'botol', 18000, 30),
('Cetirizine', 'tablet', 2500, 60);
