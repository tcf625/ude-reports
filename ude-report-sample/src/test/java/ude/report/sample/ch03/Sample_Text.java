/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch03;

import java.awt.Color;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.iisigroup.ude.report.itext2.font.CHTFontFactories;
import com.iisigroup.ude.report.itext2.font.CHTFontFactory;
import com.iisigroup.ude.report.itext2.font.FontInfo;
import com.iisigroup.ude.report.itext2.font.FontStyle;
import com.iisigroup.ude.report.itext2.font.ParagraphBuilder;
import com.iisigroup.ude.report.itext2.font.WindowsFont;
import com.iisigroup.ude.report.table.format.DocumentAlign;
import com.iisigroup.ude.report.utils.ReportTextUtils;

import ude.report.sample.AbstractSample;

public class Sample_Text extends AbstractSample {

    @Test
    public void test_basicText() {
        super.createPDF(pdfDocument -> {
            // pdfDocument.setFontSize(12); // set Default Size
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
    public void test_changeFo廿nt() {
        super.createPDF(pdfDocument -> {
            pdfDocument.writeText("中文，第二字面：「" + "𠀝" + "」(下/上)");
            final CHTFontFactory kaiFactory = CHTFontFactories.INSTANCE.getFactory(WindowsFont.KAI);
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
            final CHTFontFactory kaiFactory = CHTFontFactories.INSTANCE.getFactory(WindowsFont.KAI);
            final FontInfo redFont = pdfDocument.getFontFactory().createFontInfo(10, FontStyle.STRIKETHRU, Color.RED);
            final FontInfo kaiFont = kaiFactory.createFontInfo(10, FontStyle.UNDERLINE, Color.BLUE, Color.LIGHT_GRAY);
            final ParagraphBuilder builder = pdfDocument.paragraphBuilder();
            builder.addText("中文，第二字面：")      //
                    .addText("𠀝", redFont)     //
                    .addText("(下/上)", kaiFont) //
                    .appendMe();

        });
    }

}
