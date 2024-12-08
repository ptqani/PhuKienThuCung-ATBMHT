CREATE DATABASE IF NOT EXISTS shop_thu_cung;
USE shop_thu_cung;

-- Tạo bảng 'category' để lưu các loại sản phẩm
CREATE TABLE IF NOT EXISTS `category` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(50) NOT NULL
);
-- Tạo bảng 'product' để lưu thông tin về sản phẩm
CREATE TABLE IF NOT EXISTS `product` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL,
    `image` VARCHAR(255),
    `price` INT,
    `sellprice` INT,
    `title` VARCHAR(100),
    `description` TEXT,
    `cateID` INT,
    `id_product` INT,
    FOREIGN KEY (`cateID`) REFERENCES `category`(`id`)
);

CREATE TABLE IF NOT EXISTS `user` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(50) NOT NULL UNIQUE,
    `email` VARCHAR(100) NOT NULL UNIQUE,
	`phone` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `role` ENUM('admin', 'user', 'guest') DEFAULT 'user',
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Thêm dữ liệu vào bảng category
INSERT INTO `category` (`name`)
VALUES
('Thú cưng'),
('Phụ kiện thú cưng'),
('Dụng cụ huấn luyện'),
('Thức ăn cho thú cưng');
USE shop_thu_cung;
INSERT INTO `product` (`name`, `image`, `price`, `sellprice`, `title`, `description`, `cateID`, `id_product`)
VALUES
('Chó con Poodle', 'assets/images/products/p1.jpg', 2500000, 2300000, 'Chó Poodle dễ thương', 'Chó con Poodle dễ thương, đáng yêu, phù hợp với mọi gia đình.', 1, 201),
('Mèo Anh lông ngắn', 'assets/images/products/p2.jpg', 3000000, 2800000, 'Mèo Anh lông ngắn', 'Mèo Anh lông ngắn, tính cách hiền hòa, dễ nuôi.', 1, 202),
('Chó Golden Retriever', 'assets/images/products/p3.jpg', 5000000, 4500000, 'Chó Golden Retriever', 'Chó Golden Retriever thông minh, trung thành, dễ huấn luyện.', 1, 203),
('Bộ đồ chơi cho mèo', 'assets/images/products/p4.jpg', 200000, 180000, 'Đồ chơi cho mèo', 'Bộ đồ chơi thú vị giúp mèo vận động và vui chơi mỗi ngày.', 2, 204),
('Khay cát cho mèo', 'assets/images/products/p5.jpg', 150000, 120000, 'Khay cát cho mèo', 'Khay cát vệ sinh cho mèo, dễ dàng vệ sinh, bền bỉ theo thời gian.', 2, 205),
('Vòng cổ cho chó', 'assets/images/products/p6.jpg', 100000, 90000, 'Vòng cổ cho chó', 'Vòng cổ siêu bền cho chó, có thể điều chỉnh kích thước.', 3, 206),
('Bát ăn cho chó', 'assets/images/products/p7.jpg', 250000, 220000, 'Bát ăn cho chó', 'Bát ăn cho chó làm từ inox, dễ dàng vệ sinh, chống trơn trượt.', 3, 207),
('Chuồng cho chó', 'assets/images/products/p8.jpg', 1200000, 1100000, 'Chuồng cho chó', 'Chuồng cho chó chắc chắn, tiện dụng để nuôi thú cưng trong nhà.', 3, 208),
('Giường cho chó', 'assets/images/products/p9.jpg', 350000, 300000, 'Giường cho chó', 'Giường cho chó thiết kế thoải mái, dễ dàng giặt sạch.', 3, 209),
('Cửa sổ cho mèo', 'assets/images/products/p10.jpg', 800000, 700000, 'Cửa sổ cho mèo', 'Cửa sổ lắp trên tường để mèo có thể ngắm cảnh, dễ dàng lắp đặt.', 3, 210),
('Thức ăn cho chó', 'assets/images/products/p11.jpg', 350000, 320000, 'Thức ăn cho chó', 'Thức ăn cho chó chất lượng cao, bổ sung đầy đủ dinh dưỡng.', 4, 211),
('Thức ăn cho mèo', 'assets/images/products/p12.jpg', 300000, 270000, 'Thức ăn cho mèo', 'Thức ăn cho mèo thơm ngon, dễ tiêu hóa, bổ sung vitamin.', 4, 212),
('Bảng huấn luyện cho chó', 'assets/images/products/p13.jpg', 400000, 350000, 'Bảng huấn luyện cho chó', 'Bảng huấn luyện giúp dạy chó cách cư xử đúng mực.', 2, 213),
('Dây dắt cho chó', 'assets/images/products/p14.jpg', 180000, 150000, 'Dây dắt cho chó', 'Dây dắt chắc chắn cho chó khi dắt đi dạo, có thể điều chỉnh.', 3, 214),
('Mũ bảo hiểm cho chó', 'assets/images/products/p15.jpg', 300000, 250000, 'Mũ bảo hiểm cho chó', 'Mũ bảo hiểm cho chó khi đi xe, bảo vệ an toàn cho thú cưng.', 3, 215),
('Lồng vận chuyển cho thú cưng', 'assets/images/products/p16.jpg', 500000, 450000, 'Lồng vận chuyển cho thú cưng', 'Lồng vận chuyển tiện dụng, phù hợp cho việc di chuyển xa.', 3, 216),
('Áo ấm cho chó', 'assets/images/products/p17.jpg', 200000, 180000, 'Áo ấm cho chó', 'Áo ấm cho chó, giữ ấm cho thú cưng trong mùa đông.', 3, 217),
('Giày cho chó', 'assets/images/products/p18.jpg', 150000, 130000, 'Giày cho chó', 'Giày cho chó bảo vệ bàn chân khi đi dạo ngoài trời.', 3, 218),
('Balo đựng thú cưng', 'assets/images/products/p19.jpg', 600000, 550000, 'Balo đựng thú cưng', 'Balo đựng thú cưng giúp bạn dễ dàng mang thú cưng theo mọi nơi.', 3, 219),
('Túi xách cho thú cưng', 'assets/images/products/p20.jpg', 400000, 380000, 'Túi xách cho thú cưng', 'Túi xách dành cho mèo và chó nhỏ, dễ dàng mang theo khi di chuyển.', 3, 220),
('Bộ chăm sóc lông cho mèo', 'assets/images/products/p21.jpg', 250000, 220000, 'Bộ chăm sóc lông cho mèo', 'Bộ chăm sóc lông cho mèo, giúp lông mượt mà và không rối.', 2, 221),
('Bộ làm sạch cho chó', 'assets/images/products/p22.jpg', 300000, 270000, 'Bộ làm sạch cho chó', 'Bộ sản phẩm giúp làm sạch cơ thể cho chó sau khi đi dạo ngoài trời.', 2, 222),
('Đệm nằm cho chó', 'assets/images/products/p23.jpg', 500000, 450000, 'Đệm nằm cho chó', 'Đệm nằm thoải mái cho chó, thích hợp cho những chú chó lớn.', 3, 223),
('Túi đựng đồ cho thú cưng', 'assets/images/products/p24.jpg', 150000, 130000, 'Túi đựng đồ cho thú cưng', 'Túi đựng đồ tiện lợi cho chủ nuôi thú cưng mang theo khi ra ngoài.', 3, 224),
('Bàn chải lông cho chó', 'assets/images/products/p25.jpg', 120000, 100000, 'Bàn chải lông cho chó', 'Bàn chải lông cho chó, giúp loại bỏ lông rụng và giữ lông sạch sẽ.', 2, 225),
('Cọ vệ sinh tai cho chó', 'assets/images/products/p26.jpg', 70000, 60000, 'Cọ vệ sinh tai cho chó', 'Cọ vệ sinh tai cho chó, giúp vệ sinh tai và phòng ngừa vi khuẩn.', 2, 226),
('Máy sấy lông cho thú cưng', 'assets/images/products/p27.jpg', 800000, 750000, 'Máy sấy lông cho thú cưng', 'Máy sấy lông chuyên dụng cho chó và mèo, giúp lông khô nhanh chóng.', 2, 227),
('Giày thể thao cho chó', 'assets/images/products/p28.jpg', 250000, 230000, 'Giày thể thao cho chó', 'Giày thể thao cho chó, chống trơn trượt, bảo vệ chân khi chạy ngoài trời.', 3, 228),
('Bộ chăm sóc móng cho thú cưng', 'assets/images/products/p29.jpg', 150000, 130000, 'Bộ chăm sóc móng cho thú cưng', 'Bộ dụng cụ chăm sóc móng cho chó và mèo, dễ sử dụng và an toàn.', 2, 229),
('Bảng tên cho chó', 'assets/images/products/p30.jpg', 50000, 45000, 'Bảng tên cho chó', 'Bảng tên cho chó, giúp nhận diện và bảo vệ thú cưng dễ dàng hơn.', 3, 230);
