package controllers.key;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet(name = "download-key", value = "/download-key")
public class PrivateKeyDownload extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String fileName = request.getParameter("file");
            String privateKeyFilePath = getServletContext().getRealPath("/") + fileName;  // Đường dẫn tới file trên server

            // Set content type to let the browser know it's a downloadable file
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

            // Đọc file và ghi vào response
            try (FileInputStream fileInputStream = new FileInputStream(privateKeyFilePath);
                 OutputStream outStream = response.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
            }
            // Xóa file private key sau khi tải về
            new File(privateKeyFilePath).delete();
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error downloading the private key.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
