package com.guoguo.warehouse.qr;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.iherus.codegen.Codectx;
import org.iherus.codegen.qrcode.QrcodeConfig;
import org.iherus.codegen.qrcode.QreyesFormat;
import org.iherus.codegen.qrcode.SimpleQrcodeGenerator;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class QrTest {

    String content = "http://192.168.43.14:8080/warehouse/goods/quantity/update/10";
    String logo = "E:\\myproject\\warehouse\\src\\main\\resources\\static\\img\\picture.png";
    String img = "C:\\Users\\GC\\Desktop\\timg.png";

    String logoUrl="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1586456772898&di=8848baf1a906e1ef6b30e4b180bee6d4&imgtype=0&src=http%3A%2F%2Fpic.90sjimg.com%2Fdesign%2F01%2F30%2F40%2F09%2F58d7306b686c6.png";

    /**
     * 默认配置
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        // -->writeToFile:
        new SimpleQrcodeGenerator().generate(content).toFile(img);

        // -->writeToStream:
        OutputStream out = null;
        try {
            out = new FileOutputStream(img);
            new SimpleQrcodeGenerator().generate(content).toStream(out);
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    /**
     * 本地 Logo
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        new SimpleQrcodeGenerator().setLogo(logo).generate(content).toFile(img);
    }

    /**
     * 在线 Logo
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {
        new SimpleQrcodeGenerator().setRemoteLogo(logoUrl).generate(content).toFile(img);
    }

    /**
     * 自定义配置
     * @throws Exception
     */
    @Test
    public void test4() throws Exception {
        QrcodeConfig config = new QrcodeConfig()
                .setBorderSize(2)
                .setPadding(10)
                .setMasterColor("#00BFFF")
                .setLogoBorderColor("#B0C4DE");
        new SimpleQrcodeGenerator(config).setLogo(logo).generate(content).toFile(img);
    }

    /**
     * 自定义码眼样式（v1.3.0新增）
     * 推荐使用
     * @throws Exception
     */
    @Test
    public void test5() throws Exception {
        QrcodeConfig config = new QrcodeConfig()
                .setBorderSize(2)
                .setPadding(10)
                .setMasterColor("#778899")
                .setLogoBorderColor("#B0C4DE")
                .setCodeEyesPointColor("#BC8F8F")
                .setCodeEyesFormat(QreyesFormat.DR2_BORDER_C_POINT);

        new SimpleQrcodeGenerator(config).setLogo(logo).generate(content).toFile(img);
    }

    /**
     * 圆形logo（v1.3.1新增）
     * @throws Exception
     */
    @Test
    public void test6() throws Exception {
        QrcodeConfig config = new QrcodeConfig()
                .setMasterColor("#5F9EA0")
                .setLogoBorderColor("#FFA07A")
                .setLogoShape(Codectx.LogoShape.CIRCLE);
        new SimpleQrcodeGenerator(config).setLogo(logo).generate(content).toFile(img);
    }

}
