/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch06.repeater;

import com.iisigroup.ude.report.itext2.PDFDocument;
import com.iisigroup.ude.report.itext2.table.TableiText;
import com.iisigroup.ude.report.itext2.table.transfer.PDFDuplicatedTableTransfer;
import com.iisigroup.ude.report.table.TreeTableMetadata;
import com.iisigroup.ude.report.table.band.BandType;
import com.iisigroup.ude.report.table.band.TreeBlock;
import com.lowagie.text.PageSize;

import ude.report.sample.AbstractSample;

public abstract class Sample_BaseRepeatTable extends AbstractSample {

    protected void setPageSizeA5R(final PDFDocument pdfDocument) {
        pdfDocument.setPageSize(PageSize.A5.rotate());
    }

    protected void setPageSizeA4(final PDFDocument pdfDocument) {
        pdfDocument.setPageSize(PageSize.A4);
    }

    protected void appendBand(final TreeTableMetadata metadata, final BandType type) {
        metadata.addBand(type, () -> {
            final TreeBlock treeBlock = new TreeBlock();
            treeBlock.append(type.name());
            return treeBlock;
        });
    }

    protected void outputNextTable(final PDFDocument pdfDocument) {
        final TableiText nextTable = pdfDocument.createTable(100, 2);
        nextTable.addCell("Next");
        nextTable.addCell("Table");
        nextTable.setSpacing(0);
        nextTable.appendMe();
    }

    protected abstract PDFDuplicatedTableTransfer createPDFTransfer(final PDFDocument pdfDocument,
            final TreeTableMetadata metadata);

}
