/*
 * Copyright (c) 2009. 資拓科技. All right reserved.
 */
package ude.report.sample.ch04.pdf.notes;

import java.time.LocalDate;

import org.junit.Test;

import com.iisigroup.ude.report.data.source.BeanProperty;
import com.iisigroup.ude.report.data.source.CellDataSource;
import com.iisigroup.ude.report.data.source.Const;
import com.iisigroup.ude.report.data.source.datetime.YYYMMDD;
import com.iisigroup.ude.report.data.source.text.note.Notes;
import com.iisigroup.ude.report.itext2.table.TableiText;
import com.iisigroup.ude.report.itext2.table.celltype.cellcreator.NotesCell;
import com.iisigroup.ude.report.itext2.table.transfer.PDFNotesTable;
import com.iisigroup.ude.report.table.format.CellFormat;
import com.iisigroup.ude.util.lang8.date.UdeTimeUtils;

import ude.report.sample.AbstractSample;
import ude.report.sample.SampleVO;

public class Sample_Basic_Notes extends AbstractSample {

    @Test
    public void test_AsCellDataSource() {

        final SampleVO sampleDTO = new SampleVO();
        sampleDTO.setDate1(UdeTimeUtils.toDate(LocalDate.of(2013, 1, 10).atStartOfDay()));
        sampleDTO.setDate2(UdeTimeUtils.toDate(LocalDate.of(2013, 1, 17).atStartOfDay()));

        ;
        final Notes notes = new Notes();
        final CellDataSource money = Const.of("1,010");
        final CellDataSource date1 = YYYMMDD.withFormat("yyy年MM月dd日").apply(new BeanProperty("date1"));
        final CellDataSource date2 = YYYMMDD.withFormat("yyy年MM月dd日").apply(new BeanProperty("date2"));

        notes.addNote(1, "受處分人無正當理由，未於法定期間為離婚登記之申請，處新臺幣{?}元之罰鍰。", money);
        notes.addNote(2, "受處分人無正當理由，未於法定期間{?}至{?}，為離婚登記之申請，迨至103年02月26日申請，計逾時1091日以上。", date1, date2);
        notes.addNote(2, "戶籍法第48條第1項：戶籍登記之申請，應於事件發生或確定後30日為之。但出生登記應為60日。");
        notes.addNote(1, "戶籍法第79條：無正當理由，違反第48條第1項規定，未於法定期間，為戶籍登記之申請者，處新臺幣300元以上900元以下罰鍰；經催告而仍不為申請者，處新臺幣900元罰鍰。");
        notes.addNote(1, "依內政部函頒「戶籍罰鍰處罰金額基準表」規定，應處分如主旨。");
        notes.addNote(1, "罰鍰請於本裁處書送達收受之翌日起7日內向本所繳納，逾期未繳納且本處分經確定者，移送法務部行政執行署所屬行政執行處強制執行。");

        super.createPDF(pdfDocument -> {

            pdfDocument.writeText("* 以 CellDataSource 直接輸出");
            {
                final TableiText table0 = pdfDocument.createTable(1);
                table0.setDataObject(sampleDTO);
                table0.addCell(notes);
                table0.appendMe();
            }

            pdfDocument.writeText("* 搭配 PDFNotesTable 輸出");
            {
                final TableiText table0 = pdfDocument.createTable(1);
                table0.setDataObject(sampleDTO);
                table0.innerTable(new PDFNotesTable(notes, 8));
                table0.appendMe();
            }

            pdfDocument.writeText("* CellDataSource 搭配 CellType 輸出");
            {
                final TableiText table0 = pdfDocument.createTable(1);
                table0.setDataObject(sampleDTO);
                table0.addCell(notes, new CellFormat(new NotesCell()).setFontSize(10));
                table0.appendMe();
            }

            pdfDocument.writeText("* CellDataSource 搭配 CellType 輸出");
            {
                notes.setIndexFunction(1, Notes.SIMPLE_CHINESE);
                final TableiText table0 = pdfDocument.createTable(1);
                table0.setDataObject(sampleDTO);
                final NotesCell cellType = new NotesCell();
                cellType.getStyle(2) // 
                        //.setIndents(60)
                        .getNoteFormat().setFontSize(6);
                table0.addCell(notes, new CellFormat(cellType).setFontSize(10));
                table0.appendMe();
            }

        });
    }

}
