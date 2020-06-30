package top.fengyulou.frame;

import top.fengyulou.type.GameType;
import top.fengyulou.util.ImageUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

/**
 * 登录面板
 */
public class LoginPanel extends JPanel implements KeyListener {
    private MainFrame frame;//主窗体
    private GameType type;//游戏模式
    private Image background;//背景图片
    private Image tank;//坦克图片
    private int y1 = 370, y2 = 430;//坦克图标可选择的两个Y坐标
    private int tankY = y1;//坦克图标Y坐标

    /**
     * 登录面板构造方法
     *
     * @param frame - 主窗体
     */
    public LoginPanel(MainFrame frame) {
        this.frame = frame;
        addListener();//添加组件监听
        try {
            background = ImageIO.read(new File(ImageUtil.LOGIN_BACKGROUND_IMAGE_URL));//读取背景图片
            tank = ImageIO.read(new File(ImageUtil.PLAYER1_RIGHT_IMAGE_URL));//读取坦克图标
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重写绘图方法
     */
    @Override
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);//绘制背景图片，填满整个面板
        Font font = new Font("黑体", Font.BOLD, 35);//创建字体
        g.setFont(font);//使用字体
        g.setColor(Color.WHITE);//使用白色
        g.drawString("1 PLAYER", 350, 400);//绘制第一行文字
        g.drawString("2 PLAYER", 350, 460);//绘制第二行文字
        g.drawImage(tank, 280, tankY, this);//绘制坦克图标
    }

    /**
     * 跳转关卡面板
     */
    private void gotoLevelPanel(){
        frame.removeKeyListener(this);//主窗体删除键盘监听
        frame.setPanel(new LevelPanel(LevelPanel.nex));
    }

    /**
     * 添加组件监听
     */
    private void addListener() {
        frame.addKeyListener(this);//主窗体载入键盘监听，本类已实现KeyListener接口
    }
}
