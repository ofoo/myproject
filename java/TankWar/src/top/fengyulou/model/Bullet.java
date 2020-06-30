package top.fengyulou.model;

import top.fengyulou.frame.GamePanel;
import top.fengyulou.type.Direction;
import top.fengyulou.type.TankType;

import java.awt.*;
import java.util.List;


/**
 * 子弹类
 */
public class Bullet extends VisibleImage {
    Direction direction;//移动方向
    static final int LENGTH=8;//子弹的（正方体）边长
    private GamePanel gamePanel;//游戏面板
    private int speed=7;//移动速度
    private boolean alive=true;//子弹是否存活（有效）
    Color color = Color.ORANGE;//子弹颜色，橙色
    TankType owner;//发出子弹的坦克类型

    /**
     * 子弹构造方法
     * @param x - 子弹的初始化横坐标
     * @param y - 子弹的初始化纵坐标
     * @param direction - 子弹发射方向
     * @param gamePanel - 游戏面板对象
     * @param owner - 发出子弹的坦克类型
     */
    public Bullet(int x,int y,Direction direction,GamePanel gamePanel,TankType owner){
        super(x,y,LENGTH,LENGTH);//调用父类构造方法
        this.direction=direction;
        this.gamePanel=gamePanel;
        this.owner=owner;
        init();//初始化组件
    }

    /**
     * 初始化组件
     */
    private void init(){
        Graphics g = image.getGraphics();//获取图片的绘图方法
        g.setColor(Color.WHITE);//使用白色绘图
        g.fillRect(0,0,LENGTH,LENGTH);//绘制一个铺满整个图片的白色实心矩形
        g.setColor(color);//使用子弹颜色
        g.fillOval(0,0,LENGTH,LENGTH);//绘制一个铺满整个图片的实心圆形
        g.setColor(Color.BLACK);//使用黑色
        g.drawOval(0,0,LENGTH-1,LENGTH-1);//给圆形绘制一个黑色的边框，防止绘出界，宽高减小1像素
    }

    /**
     * 子弹移动
     */
    public void move(){
        switch (direction){//判断移动方向
            case UP://如果向上
                upward();//向上移动
                break;
            case DOWN://如果向上
                downward();//向下移动
                break;
            case LEFT://如果向左
                leftward();//向右移动
                break;
            case RIGHT://如果向右
                rightward();//向右移动
                break;
        }
    }

    /**
     * 向右移动
     */
    private void rightward() {
        x+=speed;//横坐标增加
        moveToBorder();//移动出面板边界时销毁子弹
    }

    /**
     * 向左移动
     */
    private void leftward() {
        x-=speed;//横坐标减少
        moveToBorder();//移动出面板边界时销毁子弹
    }

    /**
     * 向下移动
     */
    private void downward() {
        y+=speed;//纵坐标增加
        moveToBorder();//移动出面板边界时销毁子弹
    }

    /**
     * 向上移动
     */
    private void upward() {
        y-=speed;//纵坐标减少
        moveToBorder();//移动出面板边界时销毁子弹
    }

    /**
     * 击中坦克
     */
    public void hitTank(){
        List<Tank> tanks = gamePanel.getTanks();//获取所有坦克的集合
        for (int i = 0,lengh=tanks.size(); i < lengh; i++) {//遍历坦克集合
            Tank t = tanks.get(i);//获取坦克对象
            if (t.isAL)
        }
    }

    /**
     * 移动出面板边界时销毁子弹
     */
    private void moveToBorder() {
        if(x<0||x>gamePanel.getWidth()-getWidth()||y<0||y>gamePanel.getHeight()-getHeight()){
            dispose();//销毁子弹
        }
    }

    /**
     * 销毁子弹
     */
    private synchronized void dispose() {
        alive=false;//存活（有效）状态变为false
    }

    /**
     * 获取子弹存活状态
     * @return
     */
    public boolean isAlive() {
        return alive;
    }
}
