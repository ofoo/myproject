package top.fengyulou.frame;

import top.fengyulou.model.Base;
import top.fengyulou.model.Boom;
import top.fengyulou.model.Bullet;
import top.fengyulou.model.Tank;
import top.fengyulou.model.wall.Wall;
import top.fengyulou.type.GameType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

/**
 * 游戏面板
 */
public class GamePanel extends JPanel implements KeyListener {
    /**
     * 游戏界面刷新时间：20毫秒
     */
    public static final int FRESH=20;
    private BufferedImage image;//在面板中显示的著图片
    private Graphics2D g2;//图片的绘图对象
    private MainFrame frame;//主窗体
    private GameType gameType;//游戏模式
    private Tank play1,play2;//玩家1 玩家2
    private boolean y_key,s_key,w_key,a_key,d_key,up_key,down_key,left_key,right_key,num1_key;//按键是否按下标志，左侧单词是按键名
    private int level;//关卡值
    private List<Bullet> bullets;//所有子弹集合
    private volatile List<Tank> allTanks;//所有坦克集合
    private List<Tank> botTanks;//电脑坦克集合
    private final int botCount=20;//电脑坦克总数
    private int botReadyCount=botCount;//准备出场的电脑坦克总数
    private int botSurplusCount=botCount;//电脑坦克剩余量
    private int botMaxInMap=6;//场上最大坦克数
    private int botX[] = {10,367,754};//电脑坦克出生的3个横坐标位置
    private List<Tank> playerTanks;//玩家坦克集合
    private volatile boolean finish=false;//游戏是否结束
    private Base base;//基地
    private List<Wall> walls;//所有墙块
    private List<Boom> boomImage;//坦克阵亡后的操作效果集合
    private Random r = new Random();//随机数对象
    private int createBotTimer=0;//生产电脑计时器
    private Tank survivor;//（玩家）幸存者，用于绘制最后一个爆炸效果

    /**
     * 获取所有墙块集合
     * @return 所有墙块
     */
    public List<Wall> getWalls(){
        return walls;
    }

    /**
     * 获取所有坦克集合
     * @return 所有坦克
     */
    public List<Tank> getTanks(){
        return allTanks;
    }

    /**
     * 向子弹集合中添加子弹
     * @param b - 添加的子弹
     */
    public void addBullet(Bullet b){
        bullets.add(b);//子弹集合中添加子弹
    }
}
