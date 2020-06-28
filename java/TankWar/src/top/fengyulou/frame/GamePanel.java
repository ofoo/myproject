package top.fengyulou.frame;

import top.fengyulou.model.Tank;
import top.fengyulou.model.wall.Wall;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * 游戏面板
 */
public class GamePanel extends JPanel implements KeyListener {
    /**
     * 游戏界面刷新时间：20毫秒
     */
    public static final int FRESH=20;
    private volatile List<Tank> allTanks;//所有坦克集合
    private List<Wall> walls;//所有墙块

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
}
