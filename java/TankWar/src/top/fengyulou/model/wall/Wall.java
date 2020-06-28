package top.fengyulou.model.wall;

import top.fengyulou.model.VisibleImage;

import java.util.Objects;

/**
 * 砖墙类
 */
public class Wall extends VisibleImage {
    private boolean alive=true;//墙块是否存活（有效）

    /**
     * 构造方法
     * @param x - 初始化横坐标
     * @param y - 初始化纵坐标
     * @param url - 初始化图像路径
     */
    public Wall(int x, int y, String url) {
        super(x, y, url);//调用父类构造方法
    }

    /**
     * 返回是否有效
     * @return
     */
    public boolean isAlive(){
        return alive;
    }

    /**
     * 设置是否有效
     * @param alive - 是否有效
     */
    public void setAlive(boolean alive){
        this.alive=alive;
    }

    /**
     * 重新判断方法，如果两个墙的坐标相同，则认为两个墙块是同一个墙块
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Wall) {//如果传入的对象是墙块或其他子类对象
            Wall w = (Wall) object;//强制转为墙块对象
            if (w.x==x && w.y==y) {//如果两个墙块坐标相同
                return true;//两个墙块是相同的
            }
        }
        return super.equals(object);//返回父类方法
    }
}
