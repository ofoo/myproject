package top.fengyulou.model.wall;

import top.fengyulou.util.ImageUtil;

/**
 * 河流类
 */
public class RiverWall extends Wall {
    /**
     * 砖墙构造方法
     *
     * @param x
     * @param y
     */
    public RiverWall(int x, int y) {
        super(x, y, ImageUtil.RIVERWALL_IMAGE_URL);//调用父类构造方法，使用默认河流图片
    }
}
