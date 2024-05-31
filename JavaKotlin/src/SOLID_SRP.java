// ReportGenerator 클래스는 한 클래스가 여러 책임을 담당함
// SRP 위반
class ReportGenerator {
    public void generatePDFReport() {

    }

    public void generateCSVReport() {

    }

    public void sendEmail() {

    }
}

// SRP 적용 후
// 각 클래스는 단일 책임을 가져서 확장성/유지보수성 증가
class PDFReportGenerator {
    public void generatePDFReport() {

    }
}

class CSVReportGenerator {
    public void generateCSVReport() {

    }
}

class EmailSender {
    public void sendEmail() {

    }
}

