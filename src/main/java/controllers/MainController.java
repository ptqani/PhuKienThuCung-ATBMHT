package controllers;

import models.HashSignatureService;
import view.MainView;

import javax.swing.*;
import java.io.File;
import java.security.PrivateKey;
import java.util.Base64;

public class MainController {
    private MainView view;
    private HashSignatureService service;
    private PrivateKey privateKey; // Lưu trữ PrivateKey đã tải
    
    public MainController(MainView view, HashSignatureService service) {
        this.view = view;
        this.service = service;

    }
    public void fetchHash() {
        try {
            int orderId = Integer.parseInt(view.getOrderId());
            String hash = service.fetchHash(orderId);
            // Cập nhật hash vào Signature area
            view.setHash(hash);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void uploadSignature() {
        try {
            int orderId = Integer.parseInt(view.getOrderId());
            String signature = view.getSignature();
            String result = service.uploadSignature(orderId, signature);
            JOptionPane.showMessageDialog(view, result, "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadPrivateKey() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn Private Key");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int returnValue = fileChooser.showOpenDialog(view);
            if (returnValue != JFileChooser.APPROVE_OPTION) {
                return;
            }

            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();

            // Tải PrivateKey từ file
            this.privateKey = service.loadPrivateKey(filePath);

            // Chuyển PrivateKey thành chuỗi Base64 để hiển thị
            String privateKeyBase64 = Base64.getEncoder().encodeToString(privateKey.getEncoded());
            view.setPrivateKey(privateKeyBase64);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi tải key: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void signHash() {
        try {
            String hash = view.getHash();
            if (privateKey == null) {
                JOptionPane.showMessageDialog(view, "Lỗi: Chưa tải Private Key.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (hash == null || hash.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Lỗi: Chưa có hash để ký.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Ký hash bằng PrivateKey
            String signedData = service.signHashWithPrivateKey(hash, privateKey);
            view.setSignature(signedData);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi ký: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
