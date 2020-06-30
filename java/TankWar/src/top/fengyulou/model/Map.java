package top.fengyulou.model;

import top.fengyulou.model.wall.BrickWall;
import top.fengyulou.model.wall.Wall;
import top.fengyulou.util.MapIO;

import java.util.ArrayList;
import java.util.List;

/**
 * 地图类
 */
public class Map {
    private static List<Wall> walls = new ArrayList<>();//地图中所有墙块的集合

    private Map() {
    }

    /**
     * 获取地图对象中的所有墙块
     *
     * @return 墙块集合
     */
    public List<Wall> getWalls() {
        return walls;
    }

    /**
     * 获取地图对象
     *
     * @param level - 关卡数
     * @return 指定关卡的地图对象
     */
    public static Map getMap(String level) {
        walls.clear();//墙块集合清空
        walls.addAll(MapIO.readMap(level));//读取指定关卡的墙块集合
        //基地砖墙
        for (int a = 347; a <= 407; a += 20) {//循环基地撞墙的横坐标
            for (int b = 367; b <= 387; b += 20) {//循环基地砖墙的纵坐标
                if (a >= 367 && a <= 387 && b >= 532) {//如果墙块与基地发生重合
                    continue;//执行下一次村换
                } else {
                    walls.add(new BrickWall(a, b));//墙块集合中添加墙块
                }
            }
        }
        return new Map();//返回新的地图对象
    }

    /**
     * 获取地图对象
     *
     * @param level - 关卡数
     * @return 指定关卡的地图对象
     */
    public static Map getMap(int level) {
        return getMap(String.valueOf(level));//调用重载方法
    }
}
