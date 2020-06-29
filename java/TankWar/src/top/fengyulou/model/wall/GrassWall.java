package top.fengyulou.model.wall;

import top.fengyulou.util.ImageUtil;

/**
 * 草地类
 */
public class GrassWall extends Wall {
    /**
     * 砖墙构造方法
     *
     * @param x
     * @param y
     */
    public GrassWall(int x, int y) {
        super(x, y, ImageUtil.GRASSWALL_IMAGE_URL);//调用父类构造方法，使用默认草地图片
    }
}
