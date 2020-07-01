package top.fengyulou.frame;

import top.fengyulou.model.*;
import top.fengyulou.model.wall.Wall;
import top.fengyulou.type.GameType;
import top.fengyulou.type.TankType;
import top.fengyulou.util.ImageUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

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

    public GamePanel(MainFrame frame,int level,GameType gameType){
        this.frame=frame;
        this.level=level;
        setBackground(Color.WHITE);//面板使用白色背景
        init();//初始化组件
        Thread t = FreshThread;//创建游戏帧刷新线程
        t.start();//启动线程
        addListener();//开启监听
    }

    /**
     * 组件初始化
     */
    private void init(){
        bullets=new ArrayList<>();//实例化子弹集合
        allTanks=new ArrayList<>();//实例化所有坦克集合
        walls=new ArrayList<>();//实例化所有墙块集合
        boomImage=new ArrayList<>();//实例化爆炸效果集合

        image = new BufferedImage(749,572,BufferedImage.TYPE_INT_RGB);//实例化主图片，采用面板实际大小
        g2=image.createGraphics();//获取主图片绘图对象

        playerTanks=new ArrayList<>();//实例化玩家坦克集合
        play1=new Tank(278,537, ImageUtil.PLAYER1_UP_IMAGE_URL,this, TankType.player1);//实例化玩家1
        if (gameType==GameType.TWO_PLAYER){//如果是双人模式
            play2=new Tank(448,537,ImageUtil.PLAYER2_UP_IMAGE_URL,this,TankType.player2);//实例化玩家2
            playerTanks.add(play2);//玩家坦克集合添加玩家2
        }
        playerTanks.add(play1);//玩家坦克集合添加玩家1

        botTanks=new Vector<>();//实例化电脑坦克集合
        botTanks.add(new Bot(botX[0],1,this,TankType.bot));//在第一个位置添加电脑
        botTanks.add(new Bot(botX[1],1,this,TankType.bot));//在第二个位置添加电脑
        botTanks.add(new Bot(botX[2],1,this,TankType.bot));//在第三个位置添加电脑
        botReadyCount-=3;//准备出场的坦克总数减去初始化数量
        allTanks.addAll(playerTanks);//所有坦克集合添加玩家坦克集合
        allTanks.addAll(botTanks);//所有坦克集合添加电脑坦克集合
        base = new Base(367,532);//实例化基地
        initWalls();//初始化地图中的墙块
    }

    /**
     * 初始化地图中的墙块
     */
    public void initWalls(){
        Map map = Map.getMap(level);//获取当前关卡的地图对象
        walls.addAll(map.getWalls());//墙块集合添加当前地图中所有墙块
        walls.add(base);//墙块集合添加基地
    }

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
