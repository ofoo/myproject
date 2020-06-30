package top.fengyulou.model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 可显示图象抽象类
 */
public abstract class VisibleImage {
    /**
     * 图象横坐标
     */
    public int x;
    /**
     * 图象纵坐标
     */
    public int y;
    /**
     * 图象的宽
     */
    int width;
    /**
     * 图象的高
     */
    int height;
    /**
     * 图象对象
     */
    BufferedImage image;

    /**
     * 构造方法
     *
     * @param x     - 横坐标
     * @param y     - 纵坐标
     * @param width - 宽
     * @param hight - 高
     */
    public VisibleImage(int x, int y, int width, int hight) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = hight;
        //实例化图片
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    /**
     * 构造方法
     *
     * @param x   - 横坐标
     * @param y   - 纵坐标
     * @param url - 图片路径
     */
    public VisibleImage(int x, int y, String url) {
        this.x = x;//横坐标
        this.y = y;//纵坐标
        try {
            image = ImageIO.read(new File(url));//获取此路径的图片对象
            this.width = image.getWidth();//宽为图片宽
            this.height = image.getHeight();//高为图片高
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取图片
     *
     * @return
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * 设置图片
     *
     * @param image
     */
    public void setImage(BufferedImage image) {
        this.image = image;
    }

    /**
     * 设置图片
     *
     * @param url
     */
    public void setImage(String url) {
        try {
            this.image = ImageIO.read(new File(url));//获取指定位置的图片
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否发生碰撞
     *
     * @param v - 目标图片对象
     * @return 如果两者相交，则返回true，否则返回false
     */
    public boolean hit(VisibleImage v) {
        return hit(v.getBounds());
    }

    /**
     * 判断是否发生碰撞
     *
     * @param r - 目标边界
     * @return 如果两者相交，则返回true，否则返回false
     */
    public boolean hit(Rectangle r) {
        if (r == null) {//如果目标为空
            return false;//返回不发生碰撞
        }
        return getBounds().intersects(r);
    }

    /**
     * 获取边界对象
     *
     * @return
     */
    public Rectangle getBounds() {
        //创建一个坐标在(x,y)位置，宽高为(width,height)的矩形边界对象并返回
        return new Rectangle(x, y, width, height);
    }

    /**
     * 获取图象的宽
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * 设置宽
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * 获取高
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     * 设置高
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * 重写toString方法，可以直接显示此抽象类的所有信息
     *
     * @return
     */
    @Override
    public String toString() {
        return "VisibleImage{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", image=" + image +
                '}';
    }
}
