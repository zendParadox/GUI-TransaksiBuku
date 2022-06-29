-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 29 Jun 2022 pada 16.21
-- Versi server: 10.4.14-MariaDB
-- Versi PHP: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `transaksi_tugasutama`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `barang`
--

CREATE TABLE `barang` (
  `ID_Barang` varchar(10) NOT NULL,
  `Nama_Barang` varchar(50) DEFAULT NULL,
  `Jenis` varchar(20) DEFAULT NULL,
  `Penulis` varchar(100) DEFAULT NULL,
  `HargaBeli` decimal(10,0) DEFAULT NULL,
  `HargaJual` decimal(10,0) DEFAULT NULL,
  `Stok` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `barang`
--

INSERT INTO `barang` (`ID_Barang`, `Nama_Barang`, `Jenis`, `Penulis`, `HargaBeli`, `HargaJual`, `Stok`) VALUES
('BR002', 'Geez n Ann', 'Novel', 'Rintik Sedu', '60000', '75000', 12),
('BR003', 'Catatan Seorang Demonstran', 'Novel', 'Soe Hok Gie', '98000', '120000', 14),
('BR004', 'Naruto', 'Komik', 'Masashi Kishimoto', '32000', '40000', 20),
('BR005', 'One Piece', 'Komik', 'Eiichiro Oda', '36000', '48000', 28),
('BR006', 'Boruto', 'Komik', 'Masashi Kishimoto', '42000', '48000', 40),
('BR008', 'Tan Malaka', 'Biografi', 'Taufik Adi Susilo', '48000', '60000', 31),
('BR009', 'Utsman Bin Affan', 'Biografi', 'Prof. Dr. Ali Muhammad', '112000', '140000', 11),
('BR010', 'Pemprograman Python', 'Karya Ilmiah', 'Rafli Ramadhani', '99000', '150000', 99),
('BR011', 'Network Security', 'Karya Ilmiah', 'Iwan Sofana', '104000', '130000', 21),
('BR012', 'Malin Kundang', 'Novel', 'Agung', '55000', '75000', 5),
('BR014', 'Belajar Matematika Dengan Asik', 'Karya Ilmiah', 'Wia', '50000', '75000', 21),
('BR015', 'Hubungan Antara Afganistan Dengan Rusia', 'Biografi', 'Zahran Rafif Prof', '100000', '120000', 2);

-- --------------------------------------------------------

--
-- Struktur dari tabel `login_admin`
--

CREATE TABLE `login_admin` (
  `id_loginadmin` varchar(20) NOT NULL,
  `nama_lengkap` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `login_admin`
--

INSERT INTO `login_admin` (`id_loginadmin`, `nama_lengkap`, `email`, `password`) VALUES
('', 'Romi Rafael', 'romiromiromi@gmail.com', '123'),
('LGN0001', 'Rafli Ramadhani', 'rafliramadhani', '123');

-- --------------------------------------------------------

--
-- Struktur dari tabel `penjualan`
--

CREATE TABLE `penjualan` (
  `NoFaktur` varchar(20) NOT NULL,
  `Tanggal` varchar(20) NOT NULL,
  `ID_Customer` varchar(10) NOT NULL,
  `TotalBeli` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `penjualan`
--

INSERT INTO `penjualan` (`NoFaktur`, `Tanggal`, `ID_Customer`, `TotalBeli`) VALUES
('TR0001', '13-12-2021', '001', '120000'),
('TR0002', '02-01-2022', '001', '430000'),
('TR0003', '20-01-2022', '005', '300000'),
('TR0004', '13-03-2022', '006', '144000'),
('TR0005', '13-03-2022', '', '120000');

-- --------------------------------------------------------

--
-- Struktur dari tabel `penjualanrinci`
--

CREATE TABLE `penjualanrinci` (
  `NoFaktur` varchar(20) NOT NULL,
  `ID_Barang` varchar(10) NOT NULL,
  `Nama_Barang` varchar(50) NOT NULL,
  `Jumlah` int(11) NOT NULL,
  `Harga` decimal(10,0) NOT NULL,
  `Total` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `penjualanrinci`
--

INSERT INTO `penjualanrinci` (`NoFaktur`, `ID_Barang`, `Nama_Barang`, `Jumlah`, `Harga`, `Total`) VALUES
('TR0001', 'BR001', 'Pulang', 2, '60000', '120000'),
('TR0001', 'BR004', 'Naruto', 3, '40000', '120000'),
('TR0002', 'BR013', 'Matematika Itu Asik', 2, '150000', '300000'),
('TR0002', 'BR011', 'Network Security', 1, '130000', '130000'),
('TR0003', 'BR012', 'Malin Kundang', 3, '75000', '225000'),
('TR0003', 'BR002', 'Geez n Ann', 1, '75000', '75000'),
('TR0004', 'BR006', 'Boruto', 3, '48000', '144000'),
('TR0005', 'BR015', 'Hubungan Antara Afganistan Dengan Rusia', 1, '120000', '120000');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `login_admin`
--
ALTER TABLE `login_admin`
  ADD PRIMARY KEY (`id_loginadmin`);

--
-- Indeks untuk tabel `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`NoFaktur`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
