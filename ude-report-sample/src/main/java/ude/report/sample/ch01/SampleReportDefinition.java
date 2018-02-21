package ude.report.sample.ch01;

import java.time.LocalDateTime;
import java.util.Set;

import com.iisigroup.ude.report.DocumentFormat;
import com.iisigroup.ude.util.lang8.date.Now;
import com.iisigroup.ude.util.lang8.date.RocDateUtils;

interface SampleReportDefinition {

    String getReportName();

    String getReportCode();

    Set<DocumentFormat> getSuppertedFormats();

    default String toFileName(final DocumentFormat format) {
        final LocalDateTime localDateTime = Now.localDateTime();
        final String timeString = RocDateUtils.format(localDateTime, "yyyMMdd-h-m-s");
        return getReportCode() + "_" + timeString + "." + format.getExtFileName();
    }
}
