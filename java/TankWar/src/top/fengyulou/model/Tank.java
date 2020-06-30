package top.fengyulou.model;

import top.fengyulou.frame.GamePanel;
import top.fengyulou.model.wall.GrassWall;
import top.fengyulou.model.wall.Wall;
import top.fengyulou.type.Direction;
import top.fengyulou.type.TankType;
import top.fengyulou.util.ImageUtil;

import java.awt.*;
import java.util.List;

/**
 * 坦克类
 */
public class Tank extends VisibleImage {
    GamePanel gamePanel;//游戏面板
    Direction direction;//移动方向
    protected boolean alive = true;//是否存活
    protected int speed = 3;//移动速度
    private boolean attackCoolDown = true;//攻击冷却状态
    private int attackCoolDownTime = 500;//攻击冷却时间，毫秒
    TankType type;//坦克类型
    private String upImage;//向上移动时的图片
    private String downImage;//向下移动时的图片
    private String rightImage;//向右移动时的图片
    private String leftImage;//向左移动时的图片

    /**
     * 坦克构造方法
     *
     * @param x         - 初始化横坐标
     * @param y         - 初始化纵坐标
     * @param url       - 图片路径
     * @param gamePanel - 游戏面板
     * @param type      - 坦克类型
     */
    public Tank(int x, int y, String url, GamePanel gamePanel, TankType type) {
        super(x, y, url);
        this.gamePanel = gamePanel;
        this.type = type;
        direction = Direction.UP;//初始化方向向上
        switch (type) {
            case player1://如果是玩家1
                upImage = ImageUtil.PLAYER1_UP_IMAGE_URL;
                downImage = ImageUtil.PLAYER1_DOWN_IMAGE_URL;
                rightImage = ImageUtil.PLAYER1_RIGHT_IMAGE_URL;
                leftImage = ImageUtil.PLAYER1_LEFT_IMAGE_URL;
                break;
            case player2://如果是玩家2
                upImage = ImageUtil.PLAYER2_UP_IMAGE_URL;
                downImage = ImageUtil.PLAYER2_DOWN_IMAGE_URL;
                rightImage = ImageUtil.PLAYER2_RIGHT_IMAGE_URL;
                leftImage = ImageUtil.PLAYER2_LEFT_IMAGE_URL;
                break;
            case bot://如果是电脑
                upImage = ImageUtil.BOT_UP_IMAGE_URL;
                downImage = ImageUtil.BOT_DOWN_IMAGE_URL;
                rightImage = ImageUtil.BOT_RIGHT_IMAGE_URL;
                leftImage = ImageUtil.BOT_LEFT_IMAGE_URL;
                break;
        }
    }

    /**
     * 向左移动
     */
    public void leftward() {
        if (direction != Direction.LEFT) {//如果移动之前的方向不是左移
            setImage(leftImage);
        }
        direction = Direction.LEFT;//移动方向设为左

    }

    /**
     * 判断是否撞到墙块
     *
     * @param x
     * @param y
     * @return
     */
    public boolean hitWall(int x, int y) {
        Rectangle next = new Rectangle(x, y, width, height);//创建坦克移动后的目标区域
        List<Wall> walls = gamePanel.getWalls();//获取所有墙块
        for (int i = 0, lengh = walls.size(); i < lengh; i++) {//遍历所有墙块
            Wall w = walls.get(i);//获取墙块对象
            if (w instanceof GrassWall) {//如果是草地
                continue;//执行下一次循环
            } else if (w.hit(next)) {//如果撞到墙块
                return true;//返回撞到墙块
            }
        }
        return false;
    }

    /**
     * 判断是否撞到其他坦克
     * @param x - 自身坦克的横坐标
     * @param y - 自身坦克的纵坐标
     * @return 撞到任意坦克，则返回true
     */
    boolean hitTank(int x,int y){
        Rectangle next=new Rectangle(x,y,width,height);//创建坦克移动后的目标区域
        List<Tank> tanks = gamePanel.getTanks();//获取所有坦克
        for (int i = 0,length=tanks.size(); i < length; i++) {
            Tank t = tanks.get(i);//获取tank对象
            if (!this.equals(t)) {//如果此坦克与自身不是同一个对象
                if (t.isAlive()&&t.hit(next)) {//如果此坦克存活并且与自身相撞
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 移动到面板的边界
     */
    protected void moveToBorder(){
        if (x<0){//如果坦克横坐标小于0
            x=0;//让坦克横坐标等于0
        }else if(x>gamePanel.getWidth()-width){//如果坦克横坐标超出了最大范围
            x=gamePanel.getWidth()-width;//让坦克横坐标保持最大值
        }
        if (y<0){//如果坦克纵坐标小于0
            y=0;//让坦克纵坐标等于0
        }else if(y>gamePanel.getHeight()-height){//如果坦克纵坐标超出了最大范围
            y=gamePanel.getHeight()-height;//让坦克纵坐标保持最大值
        }
    }

    /**
     * 获取坦克头点
     * @return 头点对象
     */
    private Point getHeadPoint(){

    }

    /**
     * 坦克是否存活
     * @return 存活状态
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * 设置存活状态
     * @param alive - 存活状态
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
