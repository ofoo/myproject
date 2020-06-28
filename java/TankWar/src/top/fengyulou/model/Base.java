package top.fengyulou.model;

import top.fengyulou.model.wall.Wall;
import top.fengyulou.util.ImageUtil;

/**
 * 基地类
 */
public class Base extends Wall {
    /**
     * 基地构造方法
     *
     * @param x - 基地横坐标
     * @param y - 基地纵坐标
     */
    public Base(int x, int y) {
        super(x, y, ImageUtil.BASE_IMAGE_URL);//调用父类构造方法，使用默认基地图片
    }
}
