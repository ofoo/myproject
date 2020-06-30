package top.fengyulou.model;

import top.fengyulou.frame.GamePanel;
import top.fengyulou.type.Direction;
import top.fengyulou.type.GameType;
import top.fengyulou.type.TankType;
import top.fengyulou.util.ImageUtil;

import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * 电脑坦克类
 */
public class Bot extends Tank {
    private Random random = new Random();//随即类
    private Direction dir;//移动方向
    private int fresh = GamePanel.FRESH;//刷新时间，采用游戏面板的刷新时间
    private int MoveTimer = 0;//移动计时器

    /**
     * 机器人构造方法
     *
     * @param x         - 横坐标
     * @param y         - 纵坐标
     * @param gamePanel - 游戏面板
     * @param type      - 坦克类型
     */
    public Bot(int x, int y, GamePanel gamePanel, TankType type) {
        //调用父类的构造方法，使用默认机器人坦克图片
        super(x, y, ImageUtil.BOT_DOWN_IMAGE_URL, gamePanel, type);
        dir = Direction.DOWN;//移动方向默认向下
        setAttackCoolDownTime(1000);//设置攻击冷却时间
    }

    /**
     * 获取随机方向
     *
     * @return
     */
    private Direction randomDirection() {
        int rnum = random.nextInt(4);//获取随机数，范围在0~3
        switch (rnum) {//判断随机数
            case 0://如果是0
                return Direction.UP;//返回向上
            case 1://如果是1
                return Direction.RIGHT;//返回向右
            case 2://如果是2
                return Direction.LEFT;//返回向左
            default:
                return Direction.DOWN;//返回向下
        }
    }

    /**
     * 重写碰到坦克方法
     * @param x - 自身坦克的横坐标
     * @param y - 自身坦克的纵坐标
     * @return
     */
    boolean hitTank(int x, int y) {
        Rectangle next = new Rectangle(x, y, width, height);//创建碰撞位置
        List<Tank> tanks = gamePanel.getTanks();//获取所有坦克集合
        for (int i = 0, lengh = tanks.size(); i < lengh; i++) {//遍历坦克集合
            Tank t=tanks.get(i);//获取坦克对象
            if (!this.equals(t)) {//如果此坦克对象与本对象不是同一个
                //如果对方是存活的，并且与本对对象发生碰撞
                if (t.isAlive()&&t.hit(next)) {
                    if (t instanceof Bot) {//如果对方也是电脑
                        dir=randomDirection();//随机调整移动方向
                    }
                    return true;//发生碰撞
                }
            }
        }
        return false;//未发生碰撞
    }

    /**
     * 重写攻击方法，每次攻击只有4%概率会触发父类攻击方法
     */
    public void attack(){
        int rnum = random.nextInt(100);//创建随机数，范围在0~99
        if (rnum<4){//如果随机数小于4
            super.attack();//执行父类攻击方法
        }
    }

    /**
     * 机器人展开行动
     */
    public void go(){
        if (isAttackCoolDown()) {//如果攻击冷却时间结束
            attack();//攻击
        }
        if (MoveTimer>=300) {//如果移动计时器记录超过3秒
            dir=randomDirection();//随意调整移动方向
            MoveTimer=0;//重置移动计时器
        }else{
            MoveTimer+=fresh;//计时器按照刷新时间递增
        }
        switch (dir){//判断移动方向
            case UP://如果方向向上
                upward();//向上移动
                break;
            case DOWN://如果方向向下
                downward();//向下移动
                break;
            case RIGHT://如果方向向右
                rightward();//向右移动
                break;
            case LEFT://如果方向向左
                leftward();//向左移动
                break;

        }
    }
}
