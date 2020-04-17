package com.guoguo.murongqing;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guoguo.murongqing.dao.zhongyao.CategoryDao;
import com.guoguo.murongqing.dao.zhongyao.ZhongyaoDao;
import com.guoguo.murongqing.entity.zhongyao.Zhongyao;
import com.guoguo.murongqing.service.zhongyao.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MurongqingApplicationTests {
    @Autowired
    private ZhongyaoDao zhongyaoDao;
    @Autowired
    private CategoryDao categoryDao;

    @Test
    public void contextLoads() {
        String json1="[{\"name\":\"发散风寒药\"},{\"name\":\"麻黄\",\"guijing\":\"心、肺、膀胱、大肠\",\"gongyong\":\"发汗平喘\",\"jiliang\":\"5分-3钱\"},{\"name\":\"桂枝\",\"guijing\":\"肺、膀胱\",\"gongyong\":\"发汗、解肌、温通经络、通阳\",\"jiliang\":\"5分-3钱\"},{\"name\":\"防风\",\"guijing\":\"膀胱、肝、肺\",\"gongyong\":\"解表，祛风湿。\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"荆芥\",\"guijing\":\"肺、肝\",\"gongyong\":\"解表祛风、散热止血。\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"苏叶（紫苏）\",\"guijing\":\"肺、脾\",\"gongyong\":\"理气宽胸、发散风寒。\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"藿香\",\"guijing\":\"肺、脾、胃\",\"gongyong\":\"解表化浊\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"细辛\",\"guijing\":\"心肾、肺肝\",\"gongyong\":\"散风祛寒，行水开窍。\",\"jiliang\":\"3分-1钱\"}]";
        String json2="[{\"name\":\"发散风热药\"},{\"name\":\"柴胡\",\"guijing\":\"肝、胆、心包、三焦\",\"gongyong\":\"疏散解郁，升举中气\",\"jiliang\":\"8分-1.5钱\"},{\"name\":\"葛根\",\"guijing\":\"脾、胃\",\"gongyong\":\"解肌退热，止渴生津\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"升麻\",\"guijing\":\"脾、胃、肺、大肠\",\"gongyong\":\"散风、解毒、升阳\",\"jiliang\":\"8分-1.5钱\"},{\"name\":\"薄荷\",\"guijing\":\"肺\",\"gongyong\":\"发汗、散风热\",\"jiliang\":\"5分-1.5钱\"},{\"name\":\"牛蒡子\",\"guijing\":\"十二经\",\"gongyong\":\"疏散风热，清热解毒\",\"jiliang\":\"1.5-3钱\"}]";
        String json3="[{\"name\":\"涌吐药\"},{\"name\":\"瓜蒂\",\"guijing\":\"胃\",\"gongyong\":\"吐热痰及宿食\",\"jiliang\":\"1-2钱\"}]";
        String json4="[{\"name\":\"止吐药\"},{\"name\":\"半夏\",\"guijing\":\"脾、胃、肺\",\"gongyong\":\"燥湿化痰、降逆止呕\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"生姜\",\"guijing\":\"脾、胃、肺\",\"gongyong\":\"发表散寒，温中（指脾胃）止呕化痰行水\",\"jiliang\":\"1-2钱\"},{\"name\":\"旋复花\",\"guijing\":\"肺、胃\",\"gongyong\":\"降气、化痰\",\"jiliang\":\"1-3钱\"}]";
        String json5="[{\"name\":\"峻下药\"},{\"name\":\"大黄\",\"guijing\":\"肝、脾、胃、大肠\",\"gongyong\":\"下肠胃积滞泻血分实热\",\"jiliang\":\"1-3钱\"},{\"name\":\"番泻叶\",\"guijing\":\"胃、大肠\",\"gongyong\":\"通大便、泻积滞\",\"jiliang\":\"3分-1.5钱\"}]";
        String json6="[{\"name\":\"润下药\"},{\"name\":\"麻仁\",\"guijing\":\"脾、胃、大肠\",\"gongyong\":\"润燥滑肠\",\"jiliang\":\"3-5钱\"},{\"name\":\"蜂蜜\",\"guijing\":\"心、肺、脾、胃、大肠\",\"gongyong\":\"润燥，补中，解毒\",\"jiliang\":\"3钱-1两\"}]";
        String json7="[{\"name\":\"利尿药\"},{\"name\":\"木通\",\"guijing\":\"心、肾、小肠、膀胱\",\"gongyong\":\"清热，利尿\",\"jiliang\":\"8分-1.5钱\"},{\"name\":\"车前子\",\"guijing\":\"肝、肾、小肠\",\"gongyong\":\"利尿，清热\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"茯苓\",\"guijing\":\"心、肾、肺、脾、胃\",\"gongyong\":\"利尿，渗湿\",\"jiliang\":\"2-4钱\"},{\"name\":\"泽泻\",\"guijing\":\"膀胱、肾\",\"gongyong\":\"利尿，渗湿，泻火\",\"jiliang\":\"2-4钱\"},{\"name\":\"通草\",\"guijing\":\"肺、胃\",\"gongyong\":\"利尿，清湿热\",\"jiliang\":\"8分-1.5钱\"},{\"name\":\"滑石\",\"guijing\":\"胃、膀胱\",\"gongyong\":\"利尿，清湿热\",\"jiliang\":\"3-4钱\"}]";
        String json8="[{\"name\":\"祛风湿药\"},{\"name\":\"羌活\",\"guijing\":\"肝、肾、膀胱\",\"gongyong\":\"发表，散风湿\",\"jiliang\":\"1-3钱\"},{\"name\":\"独活\",\"guijing\":\"肝、肾\",\"gongyong\":\"祛风，渗湿\",\"jiliang\":\"1-2钱\"},{\"name\":\"苍术\",\"guijing\":\"脾、胃\",\"gongyong\":\"健脾、燥湿、发汗\",\"jiliang\":\"1-3钱\"},{\"name\":\"秦艽\",\"guijing\":\"胃、大肠、肝、胆\",\"gongyong\":\"祛风湿，和血舒筋\",\"jiliang\":\"1-3钱\"},{\"name\":\"五加皮\",\"guijing\":\"肝、肾\",\"gongyong\":\"祛风湿，壮筋骨\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"威灵仙\",\"guijing\":\"膀胱\",\"gongyong\":\"祛风湿，通经络\",\"jiliang\":\"1-4钱\"}]";
        String json9="[{\"name\":\"祛寒药\"},{\"name\":\"附子\",\"guijing\":\"通行十二经\",\"gongyong\":\"回阳，救逆，补火，助阳，祛风寒湿邪\",\"jiliang\":\"1-3钱\"},{\"name\":\"肉桂\",\"guijing\":\"肝、肾\",\"gongyong\":\"补火助阳，祛寒止痛\",\"jiliang\":\"3分-1.5钱\"},{\"name\":\"干姜\",\"guijing\":\"心、肺、脾、胃、肾、大肠\",\"gongyong\":\"温中祛寒，回阳通脉\",\"jiliang\":\"5分-1.5钱\"},{\"name\":\"吴茱萸\",\"guijing\":\"肝、肾、脾、胃\",\"gongyong\":\"温中散寒，开郁止痛\",\"jiliang\":\"5-8分\"},{\"name\":\"茴香\",\"guijing\":\"肝、肾、脾、胃\",\"gongyong\":\"温中散寒，止痛\",\"jiliang\":\"5分-1.5钱\"},{\"name\":\"草果\",\"guijing\":\"脾、胃\",\"gongyong\":\"燥湿、祛寒、除痰，截瘧\",\"jiliang\":\"8分-1.5钱\"},{\"name\":\"艾叶\",\"guijing\":\"肝、脾、肾\",\"gongyong\":\"祛寒湿，暖子宫，调经止血\",\"jiliang\":\"1-3钱\"}]";
        String json10="[{\"name\":\"清热降火药\"},{\"name\":\"石膏\",\"guijing\":\"肺、胃、三焦\",\"gongyong\":\"清热、降火、止渴、除烦\",\"jiliang\":\"5钱-2两\"},{\"name\":\"知母\",\"guijing\":\"肺、胃、肾、大肠\",\"gongyong\":\"滋阴降火，润燥滑肠\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"玄参\",\"guijing\":\"肾\",\"gongyong\":\"滋阴降火，解毒\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"栀子\",\"guijing\":\"心、肺、胃\",\"gongyong\":\"清热泻火\",\"jiliang\":\"1-3钱\"},{\"name\":\"夏枯草\",\"guijing\":\"肝、胆\",\"gongyong\":\"清肝火、散郁结\",\"jiliang\":\"1-3钱\"},{\"name\":\"芦根\",\"guijing\":\"肺、脾、胃、肾\",\"gongyong\":\"清胃火，除烦渴，止呕哕\",\"jiliang\":\"5钱-1两\"},{\"name\":\"决明子\",\"guijing\":\"肝、脾\",\"gongyong\":\"清肝火，明目通便\",\"jiliang\":\"1-3钱\"},{\"name\":\"夜明砂\",\"guijing\":\"肝\",\"gongyong\":\"清热，散血，明目\",\"jiliang\":\"1-3钱\"},{\"name\":\"黄连\",\"guijing\":\"心、肝、胆、脾、胃、大肠\",\"gongyong\":\"泻火，燥湿，清热，解毒\",\"jiliang\":\"3分-1钱\"},{\"name\":\"黄苓\",\"guijing\":\"心、肺、肝、胆、大小肠\",\"gongyong\":\"泻火，清湿热\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"黄柏\",\"guijing\":\"膀胱、肾\",\"gongyong\":\"泻火，清下焦湿热\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"龙胆草\",\"guijing\":\"肝、胆\",\"gongyong\":\"泻肝胆火，除下焦湿热\",\"jiliang\":\"8分-1.5钱\"},{\"name\":\"茵陈\",\"guijing\":\"膀胱\",\"gongyong\":\"清利湿热\",\"jiliang\":\"3-4钱\"}]";
        String json11="[{\"name\":\"清热凉血药\"},{\"name\":\"紫草\",\"guijing\":\"心包、肝\",\"gongyong\":\"预防麻疹，凉血，活血，解毒，滑肠\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"丹皮\",\"guijing\":\"心、心包、肝、肾\",\"gongyong\":\"清热凉血，散淤血\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"白头翁\",\"guijing\":\"胃、大肠\",\"gongyong\":\"清热凉血\",\"jiliang\":\"1.5-3钱\"}]";
        String json12="[{\"name\":\"清热解毒药\"},{\"name\":\"金银花\",\"guijing\":\"肺、胃、心、脾\",\"gongyong\":\"清热解毒\",\"jiliang\":\"3-4钱\"},{\"name\":\"连翘\",\"guijing\":\"心、肺、胆、大肠、三焦\",\"gongyong\":\"清热，解毒，散结\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"蒲公英\",\"guijing\":\"脾、胃、肾\",\"gongyong\":\"清热解毒\",\"jiliang\":\"2-5钱\"},{\"name\":\"马齿苋\",\"guijing\":\"心\",\"gongyong\":\"清热解毒，散血消肿\",\"jiliang\":\"3-5钱\"}]";
        String json13="[{\"name\":\"止咳平喘药\"},{\"name\":\"杏仁\",\"guijing\":\"肺、大肠\",\"gongyong\":\"祛痰止咳，定喘润燥\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"桔梗\",\"guijing\":\"肺\",\"gongyong\":\"宣肺、祛痰排脓\",\"jiliang\":\"8分-1.5钱\"},{\"name\":\"前胡\",\"guijing\":\"肺、脾、肝、膀胱\",\"gongyong\":\"降气祛痰，散热\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"枇杷叶\",\"guijing\":\"肺、胃\",\"gongyong\":\"清肺和胃，降气化痰\",\"jiliang\":\"1-3钱\"},{\"name\":\"白果\",\"guijing\":\"肺\",\"gongyong\":\"斂肺气，定痰喘，止带浊\",\"jiliang\":\"1-3钱\"},{\"name\":\"旋复花\",\"guijing\":\"肺、胃\",\"gongyong\":\"降气、化痰\",\"jiliang\":\"1~3钱\"},{\"name\":\"莱菔子\",\"guijing\":\"脾、肺\",\"gongyong\":\"理气，化痰消积\",\"jiliang\":\"1.5-3钱\"}]";
        String json14="[{\"name\":\"清热化痰药\"},{\"name\":\"川贝母（辛平）\",\"guijing\":\"心、肺\",\"gongyong\":\"润肺、清火、化燥痰\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"浙贝母（苦寒）\",\"guijing\":\"心、肺\",\"gongyong\":\"开郁、化痰、散结\",\"jiliang\":\"\"},{\"name\":\"海藻\",\"guijing\":\"胃、肾\",\"gongyong\":\"消痰软坚\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"括蔞\",\"guijing\":\"肺、胃、大肠\",\"gongyong\":\"润肺化痰，清热滑肠\",\"jiliang\":\"3-4钱\"},{\"name\":\"白芥子\",\"guijing\":\"肺\",\"gongyong\":\"利气豁痰，消肿止痛\",\"jiliang\":\"1-3钱\"},{\"name\":\"皂荚\",\"guijing\":\"肺、大肠\",\"gongyong\":\"通窍，消痰，溃坚\",\"jiliang\":\"5分-1.5钱\"},{\"name\":\"白附子\",\"guijing\":\"胃\",\"gongyong\":\"逐寒湿，祛风痰，鎭痉\",\"jiliang\":\"8分-1.5钱\"}]";
        String json15="[{\"name\":\"调气药\"},{\"name\":\"香附\",\"guijing\":\"肝、三焦\",\"gongyong\":\"理气解郁，调经止痛\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"木香\",\"guijing\":\"肺、肝、脾\",\"gongyong\":\"健胃调气，止痛\",\"jiliang\":\"5分-1.5钱\"},{\"name\":\"橘皮\",\"guijing\":\"肺、脾\",\"gongyong\":\"理气健脾、燥湿化痰\",\"jiliang\":\"1-3钱\"},{\"name\":\"青皮\",\"guijing\":\"肝、胆\",\"gongyong\":\"疏肝、破气，散结止痛\",\"jiliang\":\"1-3钱\"},{\"name\":\"砂仁\",\"guijing\":\"脾、胃、肝、大肠\",\"gongyong\":\"行气，开胃，消食\",\"jiliang\":\"5分-1钱\"},{\"name\":\"厚朴\",\"guijing\":\"脾、胃、大肠\",\"gongyong\":\"温中下气，散滿、燥湿、消痰、破积\",\"jiliang\":\"8分-2钱\"},{\"name\":\"枳实\",\"guijing\":\"脾、胃\",\"gongyong\":\"破气消积，化痰除痞\",\"jiliang\":\"1-2钱\"}]";
        String json16="[{\"name\":\"理气药\"},{\"name\":\"丹参\",\"guijing\":\"心、肝\",\"gongyong\":\"祛瘀生新，活血调经\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"赤芍\",\"guijing\":\"肝、脾\",\"gongyong\":\"散郁活血，泻肝清热\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"蒲黄\",\"guijing\":\"肝、脾\",\"gongyong\":\"行淤血（生用）止血（炒用）\",\"jiliang\":\"1-3钱\"},{\"name\":\"五灵脂\",\"guijing\":\"肝\",\"gongyong\":\"行淤止痛\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"鸡血藤\",\"guijing\":\"肝、肾\",\"gongyong\":\"活血通络\",\"jiliang\":\"3-5钱\"},{\"name\":\"牛膝\",\"guijing\":\"肝、肾\",\"gongyong\":\"散淤血（生用）补肝肾（熟用）\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"益母草\",\"guijing\":\"心、肝\",\"gongyong\":\"祛瘀行瘀，润燥滑肠\",\"jiliang\":\"1.5-3钱\"}]";
        String json17="[{\"name\":\"活血药\"},{\"name\":\"桃仁\",\"guijing\":\"心、肝\",\"gongyong\":\"破血行淤，润燥滑肠\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"红花\",\"guijing\":\"肝\",\"gongyong\":\"破瘀生新，通经活血\",\"jiliang\":\"8分-1.5钱\"},{\"name\":\"川穹\",\"guijing\":\"肝、心包、胆\",\"gongyong\":\"搜风止痛，理气活血\",\"jiliang\":\"8分-1.5钱\"},{\"name\":\"乳香\",\"guijing\":\"心、肝、脾\",\"gongyong\":\"调气活血，舒筋止痛\",\"jiliang\":\"8分-2钱\"},{\"name\":\"没药\",\"guijing\":\"肝\",\"gongyong\":\"行气，散淤，止痛\",\"jiliang\":\"8分-1.5钱\"}]";
        String json18="[{\"name\":\"止血药\"},{\"name\":\"白芨\",\"guijing\":\"肺\",\"gongyong\":\"补肺，生肌，止血\",\"jiliang\":\"8分-1.5钱\"},{\"name\":\"仙鹤草\",\"guijing\":\"肺、脾、胃、大肠\",\"gongyong\":\"收斂止血，补虚\",\"jiliang\":\"2-4钱\"},{\"name\":\"旱莲草\",\"guijing\":\"肝、肾\",\"gongyong\":\"补肾阴，止血\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"侧柏叶\",\"guijing\":\"肺、肝、肾\",\"gongyong\":\"凉血，止血，清湿热\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"地榆\",\"guijing\":\"肝、肾、大肠、胃\",\"gongyong\":\"凉血，止血\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"槐花\",\"guijing\":\"肝、大肠\",\"gongyong\":\"凉血，止血，清湿热\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"三七（参三七）\",\"guijing\":\"肝、胃\",\"gongyong\":\"行淤止血，消肿定痛\",\"jiliang\":\"5分-1钱\"},{\"name\":\"乌贼骨\",\"guijing\":\"肝、肾\",\"gongyong\":\"通血脉，祛寒湿，止血\",\"jiliang\":\"1.5-3钱\"}]";
        String json19="[{\"name\":\"补气药\"},{\"name\":\"党参\",\"guijing\":\"脾、肺\",\"gongyong\":\"补气生津\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"黄芪\",\"guijing\":\"脾、肺\",\"gongyong\":\"补气固表\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"白术\",\"guijing\":\"脾、胃\",\"gongyong\":\"健脾燥湿\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"甘草\",\"guijing\":\"十二经\",\"gongyong\":\"补脾润肺，解毒，调和药性\",\"jiliang\":\"5分-3钱\"},{\"name\":\"山药\",\"guijing\":\"脾、胃\",\"gongyong\":\"补脾土，固胃阳\",\"jiliang\":\"3-6钱\"},{\"name\":\"大枣\",\"guijing\":\"脾、胃\",\"gongyong\":\"补脾胃，调营卫\",\"jiliang\":\"2-5钱\"},{\"name\":\"龙眼肉\",\"guijing\":\"心、脾\",\"gongyong\":\"补心脾\",\"jiliang\":\"1-3钱\"}]";
        String json20="[{\"name\":\"补血药\"},{\"name\":\"当归\",\"guijing\":\"心、肝、脾\",\"gongyong\":\"补血活血，润燥滑肠\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"芍药\",\"guijing\":\"肝、脾、肺\",\"gongyong\":\"柔肝，养血，斂阴\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"川穹\",\"guijing\":\"肝、心包、胆\",\"gongyong\":\"搜风止痛，理气活血\",\"jiliang\":\"8分-1.5钱\"},{\"name\":\"阿胶\",\"guijing\":\"肺、肝、肾\",\"gongyong\":\"滋阴，养血，润肺，止血，安胎\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"地黄\",\"guijing\":\"心、肝、肾\",\"gongyong\":\"滋肾补血\",\"jiliang\":\"2-4钱\"}]";
        String json21="[{\"name\":\"助阳药\"},{\"name\":\"肉苁蓉\",\"guijing\":\"肾\",\"gongyong\":\"滋肾滑肠\",\"jiliang\":\"2-3钱\"},{\"name\":\"巴戟天\",\"guijing\":\"肾\",\"gongyong\":\"补肾、祛风湿\",\"jiliang\":\"1.5-4钱\"},{\"name\":\"鹿茸\",\"guijing\":\"肝、肾、心包\",\"gongyong\":\"补精髓、助阳、强筋骨\",\"jiliang\":\"\"},{\"name\":\"胡芦巴\",\"guijing\":\"肝、肾\",\"gongyong\":\"温肾阳，逐寒湿\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"益智仁\",\"guijing\":\"脾、心、肾\",\"gongyong\":\"煖脾、温肾\",\"jiliang\":\"1-3钱\"},{\"name\":\"杜仲\",\"guijing\":\"肝、肾\",\"gongyong\":\"补肝肾、续筋骨\",\"jiliang\":\"2-3钱\"},{\"name\":\"川断\",\"guijing\":\"肝、肾\",\"gongyong\":\"补肝肾、续筋骨\",\"jiliang\":\"1.5-3钱\"}]";
        String json22="[{\"name\":\"养阴药\"},{\"name\":\"沙参\",\"guijing\":\"肺\",\"gongyong\":\"养阴清肺，除虚热\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"石斛\",\"guijing\":\"肺、胃、肾\",\"gongyong\":\"滋阴益胃，生津\",\"jiliang\":\"2-4钱\"},{\"name\":\"天冬\",\"guijing\":\"肺、肾\",\"gongyong\":\"滋阴润肺，清热化痰\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"麦冬\",\"guijing\":\"心、肺、胃\",\"gongyong\":\"润肺清心，养胃生津\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"枸杞子\",\"guijing\":\"肺、肝、肾\",\"gongyong\":\"补肝肾，明目\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"龟板\",\"guijing\":\"肾、心、肝\",\"gongyong\":\"养阴、潜阳\",\"jiliang\":\"3-8钱\"},{\"name\":\"鳖甲\",\"guijing\":\"肺、肝、脾\",\"gongyong\":\"养阴潜阳，软坚散结\",\"jiliang\":\"3-4钱\"}]";
        String json23="[{\"name\":\"芳香开窍药\"},{\"name\":\"菖蒲\",\"guijing\":\"心\",\"gongyong\":\"辟秽开窍，宣气逐痰\",\"jiliang\":\"8分-1.5钱\"},{\"name\":\"麝香\",\"guijing\":\"十二经\",\"gongyong\":\"辟秽开窍，通经络，消肿止痛\",\"jiliang\":\"3-5厘\"}]";
        String json24="[{\"name\":\"安神定志药\"},{\"name\":\"茯神\",\"guijing\":\"心、胃、肾\",\"gongyong\":\"宁心安神\",\"jiliang\":\"\"},{\"name\":\"酸枣仁\",\"guijing\":\"心、脾、胆、肝\",\"gongyong\":\"宁心安神，生津斂汗\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"柏子仁\",\"guijing\":\"心、脾\",\"gongyong\":\"安神止汗，润燥，通便\",\"jiliang\":\"2-3钱\"},{\"name\":\"远志\",\"guijing\":\"心、肾\",\"gongyong\":\"安神，益智，散郁化痰\",\"jiliang\":\"1-1.5钱\"},{\"name\":\"合欢皮\",\"guijing\":\"心、脾\",\"gongyong\":\"调和心脾，消肿生肌\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"磁石\",\"guijing\":\"肝、肾\",\"gongyong\":\"潜阳纳气，平喘\",\"jiliang\":\"3钱-1两\"}]";
        String json25="[{\"name\":\"鎭惊熄风药\"},{\"name\":\"石决明\",\"guijing\":\"肝、肺\",\"gongyong\":\"平肝潜阳，明目\",\"jiliang\":\"3钱-1两\"},{\"name\":\"天麻\",\"guijing\":\"肝\",\"gongyong\":\"祛风、鎭痉\",\"jiliang\":\"8分-1.5钱\"},{\"name\":\"钩藤\",\"guijing\":\"肝、心包\",\"gongyong\":\"清热平肝，熄风鎭痉\",\"jiliang\":\"3-4钱\"},{\"name\":\"蜈蚣\",\"guijing\":\"肝\",\"gongyong\":\"祛风、鎭痉\",\"jiliang\":\"5分-1.5钱\"},{\"name\":\"全蝎\",\"guijing\":\"肝\",\"gongyong\":\"祛风、鎭痉\",\"jiliang\":\"1-3分\"},{\"name\":\"地龙\",\"guijing\":\"肝\",\"gongyong\":\"祛风，活血，解热，平喘\",\"jiliang\":\"1-3钱\"},{\"name\":\"白姜蚕\",\"guijing\":\"肺、肝、胃\",\"gongyong\":\"祛风、化痰\",\"jiliang\":\"1.5-3钱\"}]";
        String json26="[{\"name\":\"斂汗涩精药\"},{\"name\":\"浮小麦\",\"guijing\":\"心\",\"gongyong\":\"除热止汗\",\"jiliang\":\"3-4钱\"},{\"name\":\"麻黄根\",\"guijing\":\"心、肺\",\"gongyong\":\"止汗\",\"jiliang\":\"1-3钱\"},{\"name\":\"龙骨\",\"guijing\":\"肝、胆、心、肾\",\"gongyong\":\"重鎭固涩，斂神浅阳\",\"jiliang\":\"3钱-1两\"},{\"name\":\"牡蛎\",\"guijing\":\"肝、胆、肾\",\"gongyong\":\"益阳浅阳，化痰，软坚\",\"jiliang\":\"3钱-1两\"},{\"name\":\"五味子\",\"guijing\":\"肺、肾\",\"gongyong\":\"斂肺，涩精，止汗\",\"jiliang\":\"5-8分\"},{\"name\":\"桑螵蛸\",\"guijing\":\"肝、肾\",\"gongyong\":\"益精固肾\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"莲须\",\"guijing\":\"心、肾\",\"gongyong\":\"清心固肾\",\"jiliang\":\"2分-2钱\"},{\"name\":\"金樱子\",\"guijing\":\"肾、膀胱、大肠\",\"gongyong\":\"涩精固阳\",\"jiliang\":\"1-3钱\"},{\"name\":\"山茱萸\",\"guijing\":\"肝、肾\",\"gongyong\":\"补肝肾，涩精止汗口\",\"jiliang\":\"1.5-3钱\"}]";
        String json27="[{\"name\":\"涩肠滞泻药\"},{\"name\":\"乌梅\",\"guijing\":\"肝、脾、肺、大肠\",\"gongyong\":\"斂肺涩肠、杀虫生津\",\"jiliang\":\"8分-1.5钱\"},{\"name\":\"木瓜\",\"guijing\":\"肺、脾、肝\",\"gongyong\":\"和脾，舒筋，平肝\",\"jiliang\":\"1-4钱\"},{\"name\":\"石榴皮\",\"guijing\":\"肺、肾、大肠\",\"gongyong\":\"涩肠，杀虫\",\"jiliang\":\"8分-1.5钱\"},{\"name\":\"诃子\",\"guijing\":\"肺、大肠\",\"gongyong\":\"斂肺、涩肠\",\"jiliang\":\"8分-1.5钱\"}]";
        String json28="[{\"name\":\"消化药\"},{\"name\":\"山楂\",\"guijing\":\"脾、胃、肝\",\"gongyong\":\"破气消积，行淤化痰\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"麦芽\",\"guijing\":\"脾、胃\",\"gongyong\":\"健脾消食\",\"jiliang\":\"3-4钱\"},{\"name\":\"谷芽\",\"guijing\":\"脾、胃\",\"gongyong\":\"健脾消食\",\"jiliang\":\"3-4钱\"},{\"name\":\"神曲\",\"guijing\":\"脾、胃\",\"gongyong\":\"消食行气\",\"jiliang\":\"2-4钱\"},{\"name\":\"鸡内金\",\"guijing\":\"肝、脾\",\"gongyong\":\"消水谷\",\"jiliang\":\"1-3钱\"}]";
        String json29="[{\"name\":\"利胆药\"},{\"name\":\"玉金\",\"guijing\":\"心、肺、肝\",\"gongyong\":\"利胆、理气\",\"jiliang\":\"1.5-3钱\"},{\"name\":\"梔子\",\"guijing\":\"心、肺\",\"gongyong\":\"利胆，清热泻火\",\"jiliang\":\"1-3钱\"},{\"name\":\"大黄\",\"guijing\":\"脾、大肠、肝、心包、胃\",\"gongyong\":\"利胆\",\"jiliang\":\"\"},{\"name\":\"茵陈\",\"guijing\":\"膀胱\",\"gongyong\":\"利胆、清利湿热\",\"jiliang\":\"\"},{\"name\":\"金钱草\",\"guijing\":\"肾、胆、膀胱\",\"gongyong\":\"湿热胆、清利利\",\"jiliang\":\"\"}]";
        String json30="[{\"name\":\"软坚药\"},{\"name\":\"海藻\",\"guijing\":\"肝、胃、肾\",\"gongyong\":\"软坚、涤热\",\"jiliang\":\"\"},{\"name\":\"昆布\",\"guijing\":\"肝、胃、肾\",\"gongyong\":\"软坚\",\"jiliang\":\"\"},{\"name\":\"皂角\",\"guijing\":\"肺、大肠、肝\",\"gongyong\":\"搜风泻热，通窍化痰\",\"jiliang\":\"\"},{\"name\":\"夏枯草\",\"guijing\":\"肝\",\"gongyong\":\"清肝火，解内热，散结气\",\"jiliang\":\"\"}]";
        String json31="[{\"name\":\"驱虫药\"},{\"name\":\"使君子\",\"guijing\":\"脾、胃\",\"gongyong\":\"杀虫，健脾，消食\",\"jiliang\":\"\"},{\"name\":\"槟榔\",\"guijing\":\"胃、大肠\",\"gongyong\":\"杀虫\",\"jiliang\":\"\"},{\"name\":\"苦栋子\",\"guijing\":\"肝、心包、小肠、膀胱\",\"gongyong\":\"杀虫\",\"jiliang\":\"\"},{\"name\":\"南瓜子\",\"guijing\":\"脾、肺、胃\",\"gongyong\":\"补中益气，杀虫\",\"jiliang\":\"\"}]";

        List<String> jsonList = new ArrayList<>();
        jsonList.add(json1);
        jsonList.add(json2);
        jsonList.add(json3);
        jsonList.add(json4);
        jsonList.add(json5);
        jsonList.add(json6);
        jsonList.add(json7);
        jsonList.add(json8);
        jsonList.add(json9);
        jsonList.add(json10);
        jsonList.add(json11);
        jsonList.add(json12);
        jsonList.add(json13);
        jsonList.add(json14);
        jsonList.add(json15);
        jsonList.add(json16);
        jsonList.add(json17);
        jsonList.add(json18);
        jsonList.add(json19);
        jsonList.add(json20);
        jsonList.add(json21);
        jsonList.add(json22);
        jsonList.add(json23);
        jsonList.add(json24);
        jsonList.add(json25);
        jsonList.add(json26);
        jsonList.add(json27);
        jsonList.add(json28);
        jsonList.add(json29);
        jsonList.add(json30);
        jsonList.add(json31);

        Gson gson = new Gson();
        Type typeOfT = new TypeToken<List<Map<String, Object>>>() {
        }.getType();

        List<Zhongyao> zhongyaoList = new ArrayList<>();
        int index = 1;
        for (String json : jsonList) {
            System.out.println("index: "+(index++));
            List<Map<String,String>> list = gson.fromJson(json, typeOfT);
            Long categoryId=0L;
            for (int i = 0; i < list.size(); i++) {
                System.out.println("i: "+(i));
                Map<String, String> map = list.get(i);
                if (i>0){
                    Zhongyao zhongyao = new Zhongyao();
                    zhongyao.setName(map.get("name"));
                    zhongyao.setCategoryId(categoryId);
                    zhongyao.setDosage(map.get("jiliang"));
                    zhongyaoList.add(zhongyao);
                }else if (i==0){
                    // 根据名称查询重要分类
                    String name = map.get("name");
                    categoryId = categoryDao.getCategoryByName(name);
                }
            }
        }
        for (Zhongyao zhongyao : zhongyaoList) {
            zhongyaoDao.addZhongyao(zhongyao);
        }
    }

}
