package top.fengyulou.model.wall;

import top.fengyulou.util.ImageUtil;

/**
 * 砖墙类
 */
public class BrickWall extends Wall {
    /**
     * 砖墙构造方法
     *
     * @param x
     * @param y
     */
    public BrickWall(int x, int y) {
        super(x, y, ImageUtil.BRICKWALL_IMAGE_URL);//调用父类构造方法，使用默认砖墙图片
    }
}
