/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.rsSample;

import org.junit.Test;

import com.iisigroup.ude.report.itext2.table.TableiText;
import com.iisigroup.ude.report.itext2.utils.barcode.Barcode39Drawer;
import com.iisigroup.ude.report.table.format.Border;
import com.iisigroup.ude.report.table.format.CellFormat;
import com.iisigroup.ude.report.table.format.CellFormat.AlignV;
import com.iisigroup.ude.report.table.format.DocumentAlign;
import com.iisigroup.ude.report.utils.Coordinate;
import com.iisigroup.ude.report.utils.LengthUnit;
import ude.report.sample.AbstractSample;

public class Sample_RS_BarcodeTable extends AbstractSample {

    @SuppressWarnings("deprecation")
    @Test
    public void test() {
        super.createPDF(pdfDocument -> {

            final float[] widths = { 52.5F, 37.0F, 52.5F, 37.0F };
            final TableiText table = pdfDocument.createTable(LengthUnit.MM, widths);

            table.setHorizontalAlignment(DocumentAlign.LEFT);

            table.innerTable(new float[] { 1, 2, 1 }, 4, subTable -> {
                final CellFormat defaultFormat = subTable.getDefaultFormat();
                subTable.setBorder(Border.A); // ! 外框
                defaultFormat.setBorder(Border.N);    // ! 內框
                defaultFormat.setMinHeightInCM(1.2F); // ! ROW-HEIGHT
                defaultFormat.setAlignV(AlignV.MIDDLE);

                final Barcode39Drawer barCode39 = new Barcode39Drawer(Coordinate.CM_TL);
                subTable.addCell(barCode39.factory("A123456789", 3.8F, 0.6F, false));
                subTable.addCell("申請書", new CellFormat().setFontSize(18));
                subTable.addCell("TX");
            });

            table.innerTable(new float[] { 1, 1, 1, 1 }, 4, subTable -> {
                final CellFormat defaultFormat = subTable.getDefaultFormat();
                defaultFormat.setMinHeightInCM(1.2F); // ! ROW-HEIGHT
                defaultFormat.setAlignV(AlignV.MIDDLE);
                final Barcode39Drawer barCode39 = new Barcode39Drawer(Coordinate.CM_TL);
                subTable.addCell(barCode39.factory("A123456789", 3.8F, 0.6F, false));
                subTable.addCell("ID");
                subTable.addCell(barCode39.factory("A123456789", 3.8F, 0.6F, false));
                subTable.addCell("ID");
            });

            table.addCell("NAME", 2);
            table.addCell("NAME", 2);

            //            final CellFormat picCell = new CellFormat().setAlign(AlignH.CENTER, AlignV.MIDDLE).setMinHeightInCM(5.2F);
            //            final Line line1 = new Line(new PointF(0.5F, 0.5F), new PointF(0.5F, 1));
            //            final Line line2 = new Line(new PointF(0.5F, 0.5F), new PointF(0.8F, 0.5F));
            //            picCell.setCellType(new LinesDrawer(Coordinate.CM_TL, line1, line2));

            //            table.addCell("依XXX\n相片XXX\nA", picCell);
            //            table.addCell("B");
            //            table.addCell("C");
            //            table.addCell("D");

            table.addCell("NAME", 2);
            table.addCell("NAME", 2);

            table.appendMe();

        });
    }

}
