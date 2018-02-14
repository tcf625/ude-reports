/*
 * Copyright (c) 2010-2020 IISI.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of IISI.
 */
package com.iisigroup.ude.report.itext2.sample.tutorial

import static com.iisigroup.ude.report.itext2.header.AbstractPageHeader.PagePattern.*;
import static com.iisigroup.ude.report.itext2.header.HeaderPosition.*;

import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.itext2.PageCounter;
import com.iisigroup.ude.report.itext2.commons.LayoutInfo;
import com.iisigroup.ude.report.itext2.font.CHTFontFactories;
import com.iisigroup.ude.report.itext2.font.CHTFontFactory;
import com.iisigroup.ude.report.itext2.font.CNS11643;
import com.iisigroup.ude.report.itext2.font.FontInfo;
import com.iisigroup.ude.report.itext2.font.FontStyle;
import com.iisigroup.ude.report.itext2.font.SubPhrase;
import com.iisigroup.ude.report.itext2.header.AbstractHeaderTable;
import com.iisigroup.ude.report.itext2.header.HeaderPosition;
import com.iisigroup.ude.report.itext2.header.PageHeaderCH;
import com.iisigroup.ude.report.itext2.header.PageHeaderEN;
import com.iisigroup.ude.report.itext2.header.StringHeader;
import com.iisigroup.ude.report.itext2.sample.AbstractITextSampleGroovy
import com.iisigroup.ude.report.itext2.sample.SampleContent;
import com.iisigroup.ude.report.itext2.table.TableiText;
import com.iisigroup.ude.report.utils.Coordinate
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfTemplate;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

/**
 *
 */
class Sample_011_Headers extends AbstractITextSampleGroovy {

    private String noteText = "";

    //####################################################################
    //## [Method] sub-block :
    //####################################################################

    @Before
    void setup() {
        this.noteText = "";
        super.setContentGenerater({ PDFDocument pdfDocument  ->
            pdfDocument.writeText(this.noteText);
            for (int i = 1; i <= 100; i++) {
                pdfDocument.writeText(" ");
            }
        });
        this.setPageSize(PageSize.A4.rotate());
    }

    //####################################################################
    //## [Method] sub-block : 基本頁首頁尾
    //####################################################################

    @Test
    void test_BASIC_BASELINE(){
        super.setPageSize(PageSize.A6.rotate())
        final LayoutInfo layoutInfo = getPdfManager().createLayoutInfo();
        final String text = CHINESE_BLOCK + "中文" + CHINESE_BLOCK + "\n" + CHINESE_BLOCK + "第二行" + CHINESE_BLOCK ;
        layoutInfo.setHeader(HeaderPosition.LeftHeader, text, 18);
        layoutInfo.setHeader(HeaderPosition.RightHeader, text, 10);
        layoutInfo.setHeader(HeaderPosition.CenterHeader, text, 10);
        layoutInfo.setHeader(HeaderPosition.LeftFooter, text, 18);
        layoutInfo.setHeader(HeaderPosition.RightFooter, text, 10);
        layoutInfo.setHeader(HeaderPosition.CenterFooter, text, 10);
        this.setLayoutInfo(layoutInfo);
        this.noteText = "使用預設SimpleStringHeader及換行範例，顯示對齊位置";
    }


    @Test
    void test_BASIC(){

        super.setPageSize(PageSize.A6.rotate())

        final LayoutInfo layoutInfo = new LayoutInfo(30, 30, 40, 30);
        final CHTFontFactory risFontFactory = CHTFontFactories.INSTANCE.getFactory(CNS11643.SUNG);
        final FontInfo textBgGray10 = risFontFactory.createFontInfo(10, FontStyle.NORMAL, Color.BLACK);
        final FontInfo textBgGray12 = risFontFactory.createFontInfo(14, FontStyle.BOLD, Color.BLACK, Color.LIGHT_GRAY);

        final StringHeader strHeader1 = new StringHeader(//
                new SubPhrase(textBgGray10, "多種樣式、特殊字、換行範例\n"),//
                new SubPhrase(textBgGray10, "|𠀡|𠀖|"),//
                new SubPhrase(textBgGray12, "|𠀡|𠀖|" + "第２字面中文\n"),//
                new SubPhrase(textBgGray10, "|𠀡|𠀖|") //
                );

        layoutInfo.setHeader(HeaderPosition.CenterHeader, strHeader1);
        layoutInfo.setHeader(HeaderPosition.CenterFooter, strHeader1);
        this.setLayoutInfo(layoutInfo);
        this.noteText += "\n"
        this.noteText += "* 注意當區塊文字過長時，會互相重疊，不會自動調整。若要自動調整，應參考 TABLEHEADER。\n"
        this.noteText += "* 有換行時，若預留空間不足，則輸出內容會超出頁首、尾範圍。\n"
    }



    @Test
    void test_MultiLine(){
        final String line2 = "第一行\n第二行\n \n第四行";
        final String line3 = "第一行\n第二行\n第三行(第四行為空)\n";
        final String line4 = "第一行\n第二行\n第三行\n\n第五行";
        final LayoutInfo layoutInfo = new LayoutInfo(36f, 36f, 50f, 36f);
        layoutInfo.setHeader(LeftHeader, line2, 8);
        layoutInfo.setHeader(CenterHeader, line3, 8);
        layoutInfo.setHeader(RightHeader, line4, 8);
        layoutInfo.setHeader(LeftFooter, line2, 8);
        layoutInfo.setHeader(CenterFooter, line3, 8);
        layoutInfo.setHeader(RightFooter, line4, 8);
        this.noteText += "\n置左：第一行\\n第二行\\n \\n第四行"
        this.noteText += "\n置中：第一行\\n第二行\\n第三行(第四行為空)\\n"
        this.noteText += "\n置右：第一行\\n第二行\\n第三行\\n\\n第五行"
        this.noteText += "\n空白列應自行加入一個空白字元，否則會跳過顯示(如右)!";
        this.setLayoutInfo(layoutInfo);


    }

    @Test
    void test_PAGE_Basic(){
        this.setPageSize(PageSize.A5.rotate());
        this.noteText += "基本頁次輸出(預設前後綴文字)\n"
        this.noteText += "　左上-" + "英文頁次"
        this.noteText += "／中上-" + "英文頁次／總頁數"
        this.noteText += "／右上-" + "英文總頁數\n"
        this.noteText += "　左下-" + "中文頁次"
        this.noteText += "／中下-" + "中文頁次／總頁數"
        this.noteText += "／右下-" + "中文總頁數"
        final LayoutInfo layoutInfo = new LayoutInfo();
        layoutInfo.setHeader(LeftHeader, new PageHeaderEN(PAGE, 14));
        layoutInfo.setHeader(CenterHeader, new PageHeaderEN(BOTH, 14));
        layoutInfo.setHeader(RightHeader, new PageHeaderEN(TOTAL_PAGES, 14));
        layoutInfo.setHeader(LeftFooter, new PageHeaderCH(PAGE, 14));
        layoutInfo.setHeader(CenterFooter, new PageHeaderCH(BOTH, 14));
        layoutInfo.setHeader(RightFooter, new PageHeaderCH(TOTAL_PAGES, 14));
        this.setLayoutInfo(layoutInfo);

    }

    @Test
    void test_PAGE_PREFIX_SUFFIX(){
        this.setPageSize(PageSize.A5.rotate());
        this.noteText = "頁次輸出(自定前後綴文字)\n";
        final LayoutInfo layoutInfo = new LayoutInfo();
        layoutInfo.setHeader(LeftHeader, new PageHeaderEN(PAGE, 14).setPrefix("頁次 Page："));
        layoutInfo.setHeader(CenterHeader, new PageHeaderEN(BOTH, 14).setPrefix(" - ").setConjunction("／").setSuffix(" - "));
        layoutInfo.setHeader(RightHeader, new PageHeaderEN(TOTAL_PAGES, 14).setPrefix("總頁數 Total Pages："));

        layoutInfo.setHeader(LeftFooter, new PageHeaderCH(PAGE, 14).setPrefix("頁次：第").setSuffix("頁").setPageFormat("%04d"));
        layoutInfo.setHeader(CenterFooter, new PageHeaderCH(BOTH, 14).setPrefix("頁次：第").setConjunction("頁／共有").setTotalPagesFormat("%4d").setPageFormat("%04d"));
        layoutInfo.setHeader(RightFooter, new PageHeaderCH(TOTAL_PAGES, 14).setPrefix("總頁數：共").setSuffix("頁").setTotalPagesFormat("%4d"));
        this.setLayoutInfo(layoutInfo);
    }

    @Test
    void test_PAGE_SECTION(){
        this.noteText = "分節輸出，第一節2頁、第二節4頁、...、第五節10頁。\n\n" + "頁尾顯示格式化中文頁次輸出。";
        final LayoutInfo layoutInfo = super.getPdfManager().createLayoutInfo();
        layoutInfo.setHeader(LeftHeader, new PageHeaderCH(SECTION, 10).setPrefix("第").setSuffix("節").setSectionFormat("%02d"));
        layoutInfo.setHeader(CenterHeader, new PageHeaderCH(SECTION_AND_PAGE, 10).setPrefix("節內頁次："));
        layoutInfo.setHeader(RightHeader, new PageHeaderCH(SECTION_PAGES, 10).setPrefix("本節總頁數："));
        layoutInfo.setHeader(LeftFooter, new PageHeaderCH(PAGE, 10).setPageFormat("%04d"));
        layoutInfo.setHeader(CenterFooter, new PageHeaderCH(BOTH, 10).setPageFormat("%4d").setTotalPagesFormat("%4d"));
        layoutInfo.setHeader(RightFooter, new PageHeaderCH(TOTAL_PAGES, 10).setTotalPagesFormat("%03d"));
        this.setLayoutInfo(layoutInfo);
        this.groovyReport({ PDFDocument pdfDocument ->
            for (int s = 1; s <= 5; s++) {
                for (int i = 1; i <= s * 2; i++) {
                    pdfDocument.writeText(this.noteText);
                    pdfDocument.writeText("TEST PAGE: " + s + "-" + i);
                    pdfDocument.writeText("");
                    pdfDocument.writeText("PageCounter 資訊");
                    PageCounter pageCounter = pdfDocument.getPageCounter();
                    pdfDocument.writeText("getSection   :       " + pageCounter.getSection());
                    pdfDocument.writeText("目前頁次(節內): " + pageCounter.getPageNow());
                    pdfDocument.writeText("");
                    pdfDocument.writeText("getPageTotal : (目前) " + pageCounter.getPageTotal());
                    pdfDocument.writeText("PDF 文件頁數: " + pageCounter.getPagePDF());
                    pdfDocument.newPage();
                    //pdfDocument.setSkipPager(false);
                }
                pdfDocument.newSection();
            }
        });
    }

    @Test
    void test_TABLE_Basic(){

        final AbstractHeaderTable tableHeader = new AbstractHeaderTable(12) {
                    @Override
                    protected TableiText createTable(PDFDocument pdfDoc, PageCounter counter) throws DocumentException {
                        float[] widths = [2.5f, 3f];
                        final TableiText table = pdfDoc.createTable(Coordinate.CM_BL, widths );
                        table.addCell("製表日期：");
                        table.addCell("101/01/03");
                        table.addCell("頁　　次：");
                        table.addCell(String.format("%04d", counter.getPageNow()));
                        table.addCell("總 頁 數：");
                        final int templateWidth = this.templateFontSize * 5;
                        final PdfTemplate template = super.getTemplate(pdfDoc.getPdfWriter(), 0, templateWidth);
                        table.addCell(createTemplateCell(table, null, template));
                        return table;
                    }
                };

        final AbstractHeaderTable tableHeader2 = new AbstractHeaderTable(12) {
                    @Override
                    protected TableiText createTable(PDFDocument pdfDoc, PageCounter counter) throws DocumentException {
                        float[] widths = [4.5f, 1f];
                        final TableiText table = pdfDoc.createTable(Coordinate.CM_BL, widths );
                        table.addCell("製表日期：");
                        table.addCell("101/01/03");
                        table.addCell("總 頁 數：(重疊測試)");
                        final int templateWidth = this.templateFontSize * 5;
                        final PdfTemplate template = super.getTemplate(pdfDoc.getPdfWriter(), 0, templateWidth);
                        table.addCell(createTemplateCell(table, null, template));
                        return table;
                    }
                };

        this.noteText = "表格\n自動滿版處理";
        final LayoutInfo layoutInfo = new LayoutInfo(36, 36, 90, 90);
        layoutInfo.setHeader(LeftHeader, tableHeader);
        layoutInfo.setHeader(LeftFooter, tableHeader);
        layoutInfo.setHeader(CenterFooter, tableHeader2);
        this.setLayoutInfo(layoutInfo);
    }


}
