/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch03;

import java.awt.Color;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.iisigroup.ude.report.common.FontStyle;
import com.iisigroup.ude.report.itext2.text.font.CNS11643;
import com.iisigroup.ude.report.itext2.text.font.FontFamily;
import com.iisigroup.ude.report.itext2.text.font.FontInfo;
import com.iisigroup.ude.report.itext2.text.font.ITextFont;
import com.iisigroup.ude.report.itext2.text.font.UdeFontFactory;
import com.iisigroup.ude.report.itext2.text.font.WindowsFont;
import com.iisigroup.ude.report.table.format.DocumentAlign;
import com.iisigroup.ude.report.utils.ReportTextUtils;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;

import ude.report.sample.AbstractSample;

public class Sample_Text extends AbstractSample {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz-ABCDEFGHIJKLMNOPQRSTUVWXYZ  : ";

    @Test
    public void test_basicText() {
        super.createPDF(pdfDocument -> {
            final Font fuente = FontFactory.getFont("times-roman");
            final Document document = pdfDocument.getDocument();
            try {
                document.add(new Paragraph("TEST"));
                document.add(new Paragraph("TEST", fuente));
            } catch (final DocumentException e) {
                throw new RuntimeException(e);
            }

            final String text = "中文，第二字面：「" + "𠀝" + "」(下/上)";
            pdfDocument.writeText(text + "Size:10", 10);
            pdfDocument.writeText(text + "Size:12 (Default)");
            pdfDocument.writeText(text + "Size:14", 14);
            pdfDocument.writeText(text + "Size:16", 16);
            pdfDocument.writeText(text + "Size:18", 18);
            pdfDocument.writeText(text, 10, DocumentAlign.RIGHT);
            pdfDocument.writeText(text, 10, DocumentAlign.CENTER);
        });
    }

    @Test
    public void test_engFonts() {
        super.createPDF(pdfDocument -> {

            //FontFactory.registerDirectories()
            //
            //FontFactory.register("c:/windows/fonts/times.ttf");
            //
            //font.Times\ New\ Roman.0 = C:/windows/fonts/times.ttf
            //font.Times\ New\ Roman.0 = D:/windows/fonts/times.ttf
            //font.Times\ New\ Roman.0 = C:/winnt/fonts/times.ttf
            //font.Times\ New\ Roman.0 = D:/winnt/fonts/times.ttf

            final UdeFontFactory instance = UdeFontFactory.INSTANCE;

            pdfDocument.writeText(ALPHABET + pdfDocument.getFontInfo().fontType.getName());

            pdfDocument.setFontInfo(instance.getFontFamily(WindowsFont.KAI).createFontInfo(12, Color.BLUE));
            pdfDocument.writeText(ALPHABET + pdfDocument.getFontInfo().fontType.getName());

            pdfDocument.setFontInfo(instance.getFontFamily(ITextFont.TIMES_ROMAN).createFontInfo(12, Color.BLUE));
            pdfDocument.writeText(ALPHABET + pdfDocument.getFontInfo().fontType.getName());

            pdfDocument.setFontInfo(instance.getFontFamily("Times New Roman").createFontInfo(12, Color.BLUE));
            pdfDocument.writeText(ALPHABET + pdfDocument.getFontInfo().fontType.getName());

            pdfDocument.setFontInfo(instance.getFontFamily("Times New Roman").createFontInfo(14, Color.BLUE));
            pdfDocument.writeText(ALPHABET + pdfDocument.getFontInfo().fontType.getName());

            pdfDocument.setFontInfo(UdeFontFactory.INSTANCE.getFontFamily("times new roman").createFontInfo(12));
            pdfDocument.writeText(ALPHABET + pdfDocument.getFontInfo().fontType.getName());

            pdfDocument.setFontInfo(UdeFontFactory.INSTANCE.getFontFamily(CNS11643.KAI).createFontInfo(12));
            pdfDocument.writeText(ALPHABET + pdfDocument.getFontInfo().fontType.getName());

            // FontFactory.registerDirectories();
            // ::  will enumerate all the directories known operating systems use to store fonts and register all the fonts found there.
            // ::  影響啟動速度。

            //            final ParagraphBuilder builder = pdfDocument.paragraphBuilder().addText(ALPHABET, fontInfo);
            //            builder.appendMe();

        });
    }

    @Test
    public void test_changeFont() {
        super.createPDF(pdfDocument -> {
            pdfDocument.writeText("中文，第二字面：「" + "𠀝" + "」(下/上)");
            final FontFamily kaiFactory = UdeFontFactory.INSTANCE.getFontFamily(WindowsFont.KAI);
            pdfDocument.setFontInfo(kaiFactory.createFontInfo(10, Color.BLUE));
            pdfDocument.writeText("轉成標楷體/藍色字，無第二字面可供顯示。");
            pdfDocument.writeText("中文，第二字面：「" + "𠀝" + "」(下/上)");
            pdfDocument.writeText("中文，第二字面：「" + "𠀝" + "」(下/上)", 18);
        });
    }

    @Test
    public void test_changeLines() {
        // ! [TEST_INVOKE]
        super.createPDF(pdfDocument -> {
            pdfDocument.setFontSize(12); // set Default Size
            pdfDocument.writeText("列高範例：");
            // !
            pdfDocument.writeText("default size (12)");
            pdfDocument.writeText(toBlockText(7, 5));
            // !
            pdfDocument.writeText("size (10)");
            pdfDocument.writeText(toBlockText(7, 4), 10);
            // !
            pdfDocument.writeText("設列高 = X 1.0");
            final FontInfo font10 = pdfDocument.getFontInfo().asSize(10);
            pdfDocument.paragraphBuilder() //
                    .addText(toBlockText(7, 4), font10)//
                    .setMultipliedLeading(1F) //
                    .appendMe();
        });
    }

    private String toBlockText(final int r1, final int r2) {
        final String str = StringUtils.repeat(ReportTextUtils.CHINESE_BLOCK, r1);
        final String fullBlank = StringUtils.repeat(ReportTextUtils.FULL_BLANK, r2);
        final String text = StringUtils.repeat(str + fullBlank, 100 / (r1 + r2));
        return text;
    }

    @Test
    public void test_mixFonts() {
        super.createPDF(pdfDocument -> {
            final FontFamily kaiFactory = UdeFontFactory.INSTANCE.getFontFamily(WindowsFont.KAI);
            final FontInfo redFont = pdfDocument.getFontFamily().createFontInfo(10, FontStyle.STRIKETHRU, Color.RED);
            final FontInfo kaiFont = kaiFactory.createFontInfo(10, FontStyle.UNDERLINE, Color.BLUE, Color.LIGHT_GRAY);

            final String longText1 = StringUtils.repeat(ReportTextUtils.CHINESE_BLOCK, 10) + "\n";
            final String longText = StringUtils.repeat(longText1, 10);

            pdfDocument.paragraphBuilder().addText("中文，第二字面：")      //
                    .addText("𠀝", redFont)     //
                    .addText("(下/上)", kaiFont) //
                    .addText(longText)  //
                    .appendMe();

            pdfDocument.paragraphBuilder().addText("中文，第二字面：")      //
                    .addText("𠀝", redFont)     //
                    .addText("(下/上)", kaiFont) //
                    .addText(longText)  //
                    .appendMe();

            pdfDocument.paragraphBuilder().addText("中文，第二字面：")      //
                    .setLeading(20, 0)   //
                    .addText("𠀝", redFont)     //
                    .addText("(下/上)", kaiFont) //
                    .addText(longText)  //
                    .appendMe();

        });
    }

}
