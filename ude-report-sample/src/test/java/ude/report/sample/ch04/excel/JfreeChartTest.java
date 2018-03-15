package ude.report.sample.ch04.excel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.commons.lang3.RandomUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.junit.Ignore;
import org.junit.Test;

import com.iisigroup.ude.report.itext2.text.font.CNS11643;
import com.iisigroup.ude.report.itext2.text.font.UdeFontFactory;

public class JfreeChartTest {

    /** Logger Object. */
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JfreeChartTest.class);

    @Test
    @Ignore
    public void listFonts() {
        final String[] fontList = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        for (int i = 0; i < fontList.length; i++) {
            LOGGER.trace(fontList[i]);
        }
    }

    private void createImage(final File imgFile) throws IOException {
        // 建立圓餅圖的資料集
        final DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("非常同意", RandomUtils.nextInt(0, 100));
        dataset.setValue("同意", RandomUtils.nextInt(0, 100));
        dataset.setValue("沒意見", RandomUtils.nextInt(0, 100));
        dataset.setValue("不同意", RandomUtils.nextInt(0, 100));
        dataset.setValue("非常不同意", RandomUtils.nextInt(0, 100));

        // 建立文字型態
        final Font awtFont = UdeFontFactory.INSTANCE.getFontFamily(CNS11643.SUNG).getAwtFont(0);
        final Font font1 = awtFont.deriveFont(Font.BOLD, 36);
        final Font font2 = awtFont.deriveFont(30F);

        final String title = "臺北市";
        final JFreeChart chart = ChartFactory.createPieChart(title, dataset, true, false, false);

        // 設定圖的背景為白色，預設為灰色
        chart.setBackgroundPaint(Color.white);
        // 設定標題, 用 TextTitle
        chart.setTitle(new TextTitle(chart.getTitle().getText(), font1));
        // 設定說明列
        chart.getLegend().setItemFont(font1);
        chart.addSubtitle(new TextTitle("說明列", font2));

        // 取得 PiePlot，此為圓餅圖中的資料形式
        final PiePlot plot = (PiePlot) chart.getPlot();
        // 設定圓餅圖底色.
        plot.setBackgroundPaint(Color.white);

        // 設定標籤文字
        plot.setLabelFont(font2);

        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({2})", //
                NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));

        // 產生圖檔
        ChartUtilities.saveChartAsPNG(imgFile, chart, 1920, 1080);
    }

    @Test
    public void testImg() throws IOException {
        final File imgFile = File.createTempFile("jfree", ".jpg");
        createImage(imgFile);
        LOGGER.debug("imgFile:{}", imgFile);
    }

}
