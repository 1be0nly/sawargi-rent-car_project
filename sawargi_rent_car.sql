-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 02, 2021 at 06:02 PM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 7.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sawargi_rent_car`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_kembali`
--

CREATE TABLE `tb_kembali` (
  `id_trans` varchar(32) NOT NULL,
  `Nama` varchar(32) NOT NULL,
  `Kendaraan` varchar(32) NOT NULL,
  `Nopol` varchar(32) NOT NULL,
  `Supir` varchar(32) NOT NULL,
  `Tgl_sewa` varchar(32) NOT NULL,
  `Tgl_kembali` varchar(32) NOT NULL,
  `Jum_hari` int(11) NOT NULL,
  `Total` int(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_kembali`
--

INSERT INTO `tb_kembali` (`id_trans`, `Nama`, `Kendaraan`, `Nopol`, `Supir`, `Tgl_sewa`, `Tgl_kembali`, `Jum_hari`, `Total`) VALUES
('KT001', 'asdsad', 'R35', 'D 1 EA', 'yayang', '02-08-2021', '05-08-2021', 3, 8250000),
('KT002', 'Yoga', 'Nmax', 'D 69 EA', '-', '07-08-2021', '14-08-2021', 7, 1400000),
('KT003', 'Yoga', 'Aerox 155', 'D 69 AE', 'yeyeng', '02-08-2021', '05-08-2021', 3, 825000),
('KT004', 'asdsad', 'R35', 'D 1 EA', 'yayang', '05-08-2021', '06-08-2021', 1, 2750000),
('KT005', 'Yoga', 'R35', 'D 1 EA', 'yayang', '03-07-2021', '10-08-2021', 38, 104500000);

-- --------------------------------------------------------

--
-- Table structure for table `tb_kendaran`
--

CREATE TABLE `tb_kendaran` (
  `Jenis` varchar(5) NOT NULL,
  `Nama` varchar(20) NOT NULL,
  `Nopol` varchar(17) NOT NULL,
  `Tahun` int(5) NOT NULL,
  `Merk` text NOT NULL,
  `Harga` int(10) NOT NULL,
  `Status` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_kendaran`
--

INSERT INTO `tb_kendaran` (`Jenis`, `Nama`, `Nopol`, `Tahun`, `Merk`, `Harga`, `Status`) VALUES
('Mobil', 'R35', 'D 1 EA', 2076, 'Nissan', 2500000, 'Ada'),
('Mobil', 'R33', 'D 33 ERA', 1993, 'Nissan', 25000, 'Ada'),
('Motor', 'Aerox 155', 'D 69 AE', 2019, 'Yamaha', 25000, 'Ada'),
('Motor', 'Nmax', 'D 69 EA', 2077, 'Yamaha', 200000, 'Ada');

-- --------------------------------------------------------

--
-- Table structure for table `tb_penyewa`
--

CREATE TABLE `tb_penyewa` (
  `No_ID` varchar(11) NOT NULL,
  `Nik` int(11) NOT NULL,
  `Nama` varchar(11) NOT NULL,
  `Telp` int(11) NOT NULL,
  `Alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_penyewa`
--

INSERT INTO `tb_penyewa` (`No_ID`, `Nik`, `Nama`, `Telp`, `Alamat`) VALUES
('KP001', 123, 'asdsad', 12324, 'adfs'),
('KP002', 1234, 'Yoga', 123, 'permata biru'),
('KP003', 123456, 'ibe', 123213123, 'gk tau');

-- --------------------------------------------------------

--
-- Table structure for table `tb_supir`
--

CREATE TABLE `tb_supir` (
  `id_supir` varchar(5) NOT NULL,
  `Nama` varchar(10) NOT NULL,
  `NIK` int(20) NOT NULL,
  `Status` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_supir`
--

INSERT INTO `tb_supir` (`id_supir`, `Nama`, `NIK`, `Status`) VALUES
('KS001', 'yayang', 123, 'Ada'),
('KS002', 'yiying', 1234, 'Ada'),
('KS003', 'yuyung', 12345, 'Ada');

-- --------------------------------------------------------

--
-- Table structure for table `tb_transaksi_tmp`
--

CREATE TABLE `tb_transaksi_tmp` (
  `id_trans` varchar(11) NOT NULL,
  `Nama` text NOT NULL,
  `Kendaraan` text NOT NULL,
  `Nopol` varchar(11) NOT NULL,
  `Harga` int(11) NOT NULL,
  `Supir` varchar(10) NOT NULL,
  `Tgl_pinjam` varchar(10) NOT NULL,
  `Tgl_kembali` varchar(10) NOT NULL,
  `Jum_hari` int(11) NOT NULL,
  `Total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tb_user`
--

CREATE TABLE `tb_user` (
  `id` varchar(11) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_user`
--

INSERT INTO `tb_user` (`id`, `username`, `password`) VALUES
('KU001', 'admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_kembali`
--
ALTER TABLE `tb_kembali`
  ADD PRIMARY KEY (`id_trans`);

--
-- Indexes for table `tb_kendaran`
--
ALTER TABLE `tb_kendaran`
  ADD PRIMARY KEY (`Nopol`);

--
-- Indexes for table `tb_penyewa`
--
ALTER TABLE `tb_penyewa`
  ADD PRIMARY KEY (`No_ID`),
  ADD UNIQUE KEY `Nik` (`Nik`);

--
-- Indexes for table `tb_supir`
--
ALTER TABLE `tb_supir`
  ADD PRIMARY KEY (`id_supir`),
  ADD UNIQUE KEY `NIK` (`NIK`);

--
-- Indexes for table `tb_transaksi_tmp`
--
ALTER TABLE `tb_transaksi_tmp`
  ADD PRIMARY KEY (`id_trans`);

--
-- Indexes for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
