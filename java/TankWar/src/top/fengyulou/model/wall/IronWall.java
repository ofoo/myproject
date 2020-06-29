package top.fengyulou.model.wall;

import top.fengyulou.util.ImageUtil;

/**
 * 铁墙类
 */
public class IronWall extends Wall {
    /**
     * 砖墙构造方法
     *
     * @param x
     * @param y
     */
    public IronWall(int x, int y) {
        super(x, y, ImageUtil.IRONWALL_IMAGE_URL);//调用父类构造方法，使用默认铁墙图片
    }
}
