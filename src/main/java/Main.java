import base.exception.NotFoundException;
import domain.Admin;
import domain.User;
import util.ApplicationContext;
import util.SessionFactorySingleton;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws IOException, NotFoundException {

        Admin user = new Admin();
        File file = new File("C:\\Users\\98903\\Downloads\\hipster-shop-master\\home-service-system-p1\\src\\main\\resources\\static\\images.jpg");
        InputStream is = new FileInputStream(file);

        byte[] bytes = new byte[(int) file.length()];
        is.read(bytes);

        user = Admin.builder()
                .firstName("John")
                .lastName("Doe")
                .password("P@ssw0rd123")
                .phoneNumber("09123456789")
                .email("example@example.com")
                .imageData(bytes)
                .signUpDateTime(LocalDateTime.now())
                .build();


        byte[] imageData = ApplicationContext.getAdminService().findById(252L).getImageData();

        System.out.println(imageData);
        String path = "C:\\Users\\98903\\Downloads";

        BufferedImage image = null;
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
            image = ImageIO.read(bais);
// Now you have the image object, you can do further processing or display it
        } catch (IOException e) {
            e.printStackTrace();
        }

                File outputImageFile = new File("C:\\Users\\98903\\Downloads");

        try {
            ImageIO.write(image, "jpg", outputImageFile);
            System.out.println("Image saved successfully to: " + outputImageFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeByteToFile(byte[] byteArray, String filePath) {
        try {
            File file = new File(filePath);
            File directory = file.getParentFile();
            if (!directory.exists()) {
                directory.mkdirs();
            }

            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(byteArray);
            outputStream.close();
            System.out.println("آرایه بایت با موفقیت به فایل در مسیر زیر ذخیره شد: " + filePath);
        } catch (IOException e) {
            System.out.println("خطایی رخ داده است: " + e.getMessage());
            e.printStackTrace();
        }
    }
}