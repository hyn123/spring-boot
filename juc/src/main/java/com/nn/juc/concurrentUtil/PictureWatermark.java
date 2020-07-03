package com.nn.juc.concurrentUtil;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 图片水印
 *
 * @author 刘泽 【liuze713@gmail.com】
 * @create 2018-04-11 15:35
 **/
@Component
public class PictureWatermark {
    /**
     * 图片格式：JPG
     */
    private static final String PICTRUE_FORMATE_JPG = "jpg";

    /**
     * 添加文字水印
     *
     * @param targetImg 目标图片路径，如：C://myPictrue//1.jpg
     * @param pressText 水印文字， 如：中国证券网
     * @param fontName  字体名称，    如：宋体
     * @param fontStyle 字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC)
     * @param fontSize  字体大小，单位为像素
     * @param color     字体颜色
     * @param x         水印文字距离目标图片左侧的偏移量，如果x<0, 则在正中间
     * @param y         水印文字距离目标图片上侧的偏移量，如果y<0, 则在正中间
     * @param alpha     透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
     */
    public static void pressText(String targetImg, String pressText, String fontName, int fontStyle, int fontSize, Color color, int x, int y, float alpha) {
        try {
            File file = new File(targetImg);
            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image, 0, 0, width, height, null);
            g.setFont(new Font(fontName, fontStyle, fontSize));
            g.setColor(color);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            int width_1 = fontSize;
            int height_1 = fontSize;
            int widthDiff = width - width_1;
            int heightDiff = height - height_1;
            if (x < 0) {
                x = widthDiff / 2;
            } else if (x > widthDiff) {
                x = widthDiff;
            }
            if (y < 0) {
                y = heightDiff / 2;
            } else if (y > heightDiff) {
                y = heightDiff;
            }
            String[] split = pressText.split("\\+");
            for (int i = 0; i < split.length; i++) {
                //高度* i 是第一行字 * 2 是第二行
                g.drawString(split[i], x, y + (fontSize * i));
            }
            g.dispose();
            ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void waterPicture(String path, String jd, String wd) {
        File file = new File(path);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //先看打几行 预留第一行的像素 30 字体大小 3 是显示几行字
        int fontsize = 30;
        int height = bi.getHeight() - fontsize * 3;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(new Date());
        String s = "+" + format;
        if (!StringUtils.isEmpty(jd)) {
            s += "+" + jd + " , " + wd ;
        }
        pressText(path, s, "微软雅黑", Font.TYPE1_FONT, fontsize, Color.RED, 10, height, 1f);
    }

    @Scheduled(fixedDelay = 600000)
    public  void test() {
//        for (int i = 1; i <= 4; i++) {
        File file = new File("/application/test/picture/1572925545312.jpg");
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //先看打几行 预留第一行的像素 30 字体大小 3 是显示几行字
        int fontsize = 20;
        int height = bi.getHeight() - fontsize * 4;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(new Date());
        String s = "+" + format;
        s += "+" + "0.00000" + " , " + "0.000000" ;
        s+= "+地址：北京市朝阳区安定路23号";
        pressText("/application/test/picture/1572925545312.jpg", s, "宋体", Font.PLAIN, fontsize, Color.RED, 10, height, 1f);
//        }
        System.out.println("完成");
    }
}
