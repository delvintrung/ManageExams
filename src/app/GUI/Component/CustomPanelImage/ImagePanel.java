package app.GUI.Component.CustomPanelImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImagePanel extends JPanel {
	private BufferedImage image;

	 public ImagePanel(String imagePath) {
		 try {
	            File imgFile = new File(imagePath);
	            if (!imgFile.exists()) {
	                System.out.println("❌ Lỗi: File ảnh không tồn tại! Đường dẫn: " + imgFile.getAbsolutePath());
	                return;
	            }

	            image = ImageIO.read(imgFile);

	            if (image == null) {
	                System.out.println("⚠️ Cảnh báo: Không thể đọc file ảnh. Kiểm tra định dạng!");
	                return;
	            }

	            setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("Không thể tải ảnh.");
	        }
	    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            int panelWidth = getWidth();
            int panelHeight = getHeight();

            int newWidth = panelWidth;
            int newHeight = (image.getHeight() * newWidth) / image.getWidth();

            if (newHeight > panelHeight) {
                newHeight = panelHeight;
                newWidth = (image.getWidth() * newHeight) / image.getHeight();
            }

            int x = (panelWidth - newWidth) / 2;
            int y = (panelHeight - newHeight) / 2;

            g.drawImage(image, x, y, newWidth, newHeight, this);
        }
    }

    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hiển thị ảnh");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        ImagePanel imagePanel = new ImagePanel("./src/image/questions/900_istockphoto-1334419989-612x612.jpg");
        frame.add(imagePanel);
        frame.pack(); 
        frame.setVisible(true);
    }

}
