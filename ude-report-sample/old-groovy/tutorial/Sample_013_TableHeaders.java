/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package com.iisigroup.ude.report.itext2.sample.tutorial;

import static com.iisigroup.ude.report.itext2.header.HeaderPosition.*;

import com.iisigroup.ude.report.common.SimpleRectangle;
import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.itext2.PageCounter;
import com.iisigroup.ude.report.itext2.commons.LayoutInfo;
import com.iisigroup.ude.report.itext2.header.AbstractHeaderTable;
import com.iisigroup.ude.report.itext2.sample.AbstractITextSample;
import com.iisigroup.ude.report.itext2.sample.SampleContent;
import com.iisigroup.ude.report.itext2.table.TableiText;
import com.iisigroup.ude.report.table.format.Border;
import com.iisigroup.ude.report.table.format.CellFormat;
import com.iisigroup.ude.report.utils.Coordinate;
import com.iisigroup.ude.report.utils.LengthUnit;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfTemplate;

import org.junit.Before;
import org.junit.Test;

public class Sample_013_TableHeaders extends AbstractITextSample {

    private String noteText;

    @Before
    public void setup() {
        this.noteText = "";
        super.setContentGenerater(new SampleContent() {
            @Override
            public void generatePDFContent(final PDFDocument pdfDocument) {
                pdfDocument.writeText(Sample_013_TableHeaders.this.noteText);
                for (int i = 1; i <= 100; i++) {
                    pdfDocument.writeText(" ");
                }
            }
        });
        this.setPageSize(PageSize.A4.rotate());
    }

    /**
     * @return
     */
    private static LayoutInfo createLayout() {
        final LayoutInfo layoutInfo = new LayoutInfo(36f, 36f, 80f, 80f);
        return layoutInfo;
    }

    @Test
    public void test_PAGE_TABLE_Normal() {
        this.noteText = "\n\n\n\n\n使用表格輸出置左，\n頁首輸出滿版、高度3列\n頁尾輸出固定長度";
        final LayoutInfo layoutInfo = createLayout();
        layoutInfo.setHeader(LeftHeader, new TABLE_HEADER(12, 1));
        layoutInfo.setHeader(LeftFooter, new TABLE_HEADER(12, 1, LengthUnit.CM.trans(8)));
        layoutInfo.setHeader(RightFooter, new TABLE_HEADER(12, 1, LengthUnit.CM.trans(6)));
        this.setLayoutInfo(layoutInfo);
        this.generateTestReport();
    }

    @Test
    public void test_PAGE_TABLE_HEIGHT0() {
        this.noteText = "\n\n\n\n\n\n使用表格輸出置中，\n頁首輸出滿版、高度0\n頁尾輸出固定長度";
        final LayoutInfo layoutInfo = createLayout();
        layoutInfo.setHeader(CenterHeader, new TABLE_HEADER(12, 0));
        layoutInfo.setHeader(CenterFooter, new TABLE_HEADER(12, 0, LengthUnit.CM.trans(8)));
        this.setLayoutInfo(layoutInfo);
        this.generateTestReport();
    }

    @Test
    public void test_PAGE_TABLE_HEIGHT2() {
        this.noteText = "\n\n\n\n\n使用表格輸出置右，\n頁首輸出滿版、高度2列\n頁尾輸出固定長度";
        final LayoutInfo layoutInfo = createLayout();
        layoutInfo.setHeader(RightHeader, new TABLE_HEADER(12, 0.5f));
        layoutInfo.setHeader(RightFooter, new TABLE_HEADER(12, 0.5f, LengthUnit.CM.trans(8)));
        this.setLayoutInfo(layoutInfo);
        this.generateTestReport();
    }

    static class TABLE_HEADER extends AbstractHeaderTable {

        final float padding = 5;

        private final float heightRadio;

        private float fixedWidth = 0;

        public TABLE_HEADER(final int fontSize, final float heightRadio) {
            super(fontSize);
            this.heightRadio = heightRadio;
        }

        public TABLE_HEADER(final int fontSize, final float heightRadio, final float fixedWidth) {
            super(fontSize);
            this.heightRadio = heightRadio;
            this.fixedWidth = fixedWidth;
        }

        @Override
        protected TableiText createTable(final PDFDocument pdfDoc, final PageCounter counter) {
            final float[] widths = { 2.5f, 2f };

            final String pageNoStr = String.format("%04d", counter.getPageNow());
            final TableiText table = pdfDoc.createTable(Coordinate.CM_BL, widths);
            final CellFormat cf1 = super.createCellFormat() //
                    .setPadding(this.padding)//
                    .setAlignH(CellFormat.AlignH.RIGHT)//
                    .setBorder(Border.NR);
            final CellFormat cf2 = super.createCellFormat()//
                    .setPadding(this.padding)//
                    .setAlignH(CellFormat.AlignH.LEFT) //
                    .setBorder(Border.NL);

            table.addCell("製表日期：", cf1);
            table.addCell("104/01/02", cf2);
            table.addCell("頁　　次：", cf1);
            table.addCell(pageNoStr, cf2);
            table.addCell("總 頁 數：", cf1);
            final int width = this.templateFontSize * 5;
            final int section = counter.getSection();
            final PdfTemplate template = super.getTemplate(pdfDoc.getPdfWriter(), section, width);
            table.addCell(super.createTemplateCell(table, cf2, template));
            return table;
        }

        @Override
        protected float getTableHeight(final SimpleRectangle headerRect, final float defaultHeight) {
            //int rowH = (this.fontSize + this.padding * 2);
            //LOGGER.info("rowH : {}, default : {}", rowH, defaultHeight);
            return this.heightRadio * super.getTableHeight(headerRect, defaultHeight);
        }

        @Override
        protected float getTableWidth(final SimpleRectangle headerRect) {
            if (this.fixedWidth <= 0) {
                return super.getTableWidth(headerRect);
            } else {
                return this.fixedWidth;
            }
        }

        @Override
        protected String getTemplateText(final PageCounter counter, final int section) {
            return getPagesBySection(counter, section) + "頁";

        }
    }

}
