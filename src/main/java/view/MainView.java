// Gói view
package view;

import controllers.MainController;
import models.HashSignatureService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private JTextField orderIdField;
    private JTextArea signatureArea;
    private JTextArea hashArea; // Thêm JTextArea để hiển thị hash
    private JButton fetchHashButton;
    private JButton uploadSignatureButton;
    private JButton loadKeyButton;
    private JButton signHashButton;
    private JTextField privateKeyField;
    private MainController controller;

    public MainView() {
        // Tạo giao diện và các thành phần
        setTitle("Tool Chữ Ký Điện Tử");
        setSize(800, 600); // Kích thước giao diện lớn hơn để thoải mái
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(30, 30)); // Thêm không gian giữa các phần

        // Tạo các Panel chính
        JPanel inputPanel = createInputPanel();
        JPanel signaturePanel = createSignaturePanel();

        // Thêm các panel vào giao diện chính
        add(inputPanel, BorderLayout.NORTH);
        add(signaturePanel, BorderLayout.CENTER);

        // Thiết lập layout tổng thể cho giao diện
        getContentPane().setBackground(Color.WHITE);

        // Khởi tạo controller sau khi giao diện đã được tạo xong
        controller = new MainController(this, new HashSignatureService());

        // Thêm action listener cho các nút
        addFetchHashListener(e -> controller.fetchHash());
        addUploadSignatureListener(e -> controller.uploadSignature());
        addLoadKeyListener(e -> controller.loadPrivateKey());
        addSignHashListener(e -> controller.signHash());
    }

    // Phần tạo giao diện input
    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Sử dụng GridBagLayout để linh hoạt trong việc căn chỉnh
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các phần tử

        // Thêm các trường thông tin
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nhập ID Đơn Hàng:"), gbc);

        gbc.gridx = 1;
        orderIdField = new JTextField(20);
        panel.add(orderIdField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Private Key:"), gbc);

        gbc.gridx = 1;
        privateKeyField = new JTextField(20);
        privateKeyField.setEditable(false); // Không cho phép chỉnh sửa
        panel.add(privateKeyField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        loadKeyButton = new JButton("Tải Private Key");
        loadKeyButton.setPreferredSize(new Dimension(180, 40)); // Tăng kích thước nút
        panel.add(loadKeyButton, gbc);

        gbc.gridx = 1;
        fetchHashButton = new JButton("Tải Hash");
        fetchHashButton.setPreferredSize(new Dimension(180, 40)); // Tăng kích thước nút
        panel.add(fetchHashButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        signHashButton = new JButton("Ký Hash");
        signHashButton.setPreferredSize(new Dimension(180, 40)); // Tăng kích thước nút
        panel.add(signHashButton, gbc);

        return panel;
    }

    // Phần tạo giao diện chữ ký và hash
    private JPanel createSignaturePanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // Khu vực hiển thị hash
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Hash:"), gbc);

        gbc.gridx = 1;
        hashArea = new JTextArea(3, 30);
        hashArea.setFont(new Font("Arial", Font.PLAIN, 14));
        hashArea.setEditable(false);
        hashArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panel.add(new JScrollPane(hashArea), gbc);

        // Khu vực chữ ký
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Chữ Ký:"), gbc);

        gbc.gridx = 1;
        signatureArea = new JTextArea(5, 30);
        signatureArea.setFont(new Font("Arial", Font.PLAIN, 14));
        signatureArea.setEditable(false);
        signatureArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panel.add(new JScrollPane(signatureArea), gbc);

        // Nút tải chữ ký
        gbc.gridx = 1;
        gbc.gridy = 2;
        uploadSignatureButton = new JButton("Tải Chữ Ký");
        uploadSignatureButton.setPreferredSize(new Dimension(200, 40));
        panel.add(uploadSignatureButton, gbc);

        return panel;
    }

    // Lấy dữ liệu từ giao diện
    public String getOrderId() {
        return orderIdField.getText();
    }

    public String getPrivateKey() {
        return privateKeyField.getText();
    }

    public String getSignature() {
        return signatureArea.getText();
    }

    public void setPrivateKey(String privateKey) {
        privateKeyField.setText(privateKey);
    }

    public void setHash(String hash) {
        hashArea.setText(hash);
    }
    public String getHash() {
      return hashArea.getText();
    }
    public void setSignature(String signedData) {
        signatureArea.setText(signedData);
    }

    // Các action listener cho nút
    public void addFetchHashListener(ActionListener listener) {
        fetchHashButton.addActionListener(listener);
    }

    public void addUploadSignatureListener(ActionListener listener) {
        uploadSignatureButton.addActionListener(listener);
    }

    public void addLoadKeyListener(ActionListener listener) {
        loadKeyButton.addActionListener(listener);
    }

    public void addSignHashListener(ActionListener listener) {
        signHashButton.addActionListener(listener);
    }

    // Hàm main để chạy ứng dụng
    public static void main(String[] args) {
        MainView view = new MainView();
        view.setVisible(true);
    }
}
