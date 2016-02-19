package test

import net.sf.jasperreports.engine.JREmptyDataSource
import org.codehaus.groovy.grails.plugins.jasper.JasperExportFormat
import org.codehaus.groovy.grails.plugins.jasper.JasperReportDef

class TestController {
    def jasperService
    def index() {
        def reportDef = new JasperReportDef(name:'report.jrxml',
                fileFormat:JasperExportFormat.PDF_FORMAT,
                dataSource: new JREmptyDataSource(),
                parameters: [testParam: 'Hello world']
        )
        response.reset();
//      response.setContentType("application/octet-stream"); // Force browse to download file
        response.setHeader("Content-disposition", "inline; filename='example.pdf'");
        response.outputStream << jasperService.generateReport(reportDef).toByteArray()
        response.outputStream.flush()
    }
}
