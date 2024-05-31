interface Document {
    void create();
    void edit();
    void save();
    void print();
}

class TextDocument implements Document {
    @Override
    public void create() {

    }

    @Override
    public void edit() {

    }

    @Override
    public void save() {

    }

    @Override
    public void print() {

    }
}

// PDFDocument 클래스는 edit() 메서드가 필요가 없음
// 하지만 필요하지 않은 메서드를 강제로 구현해야 함
class PDFDocument implements Document {
    @Override
    public void create() {

    }

    @Override
    public void edit() {

    }

    @Override
    public void save() {

    }

    @Override
    public void print() {

    }
}

// 이렇게 인터페이스를 분리하면,
// 클래스는 필요한 메서드만 구현하면 됨
interface EditableDocument {
    void edit();
}

interface PrintableDocument {
    void print();
}

interface NiceDocument {
    void create();
    void save();
}

class NiceTextDocument implements NiceDocument, EditableDocument, PrintableDocument {
    @Override
    public void edit() {

    }

    @Override
    public void print() {

    }

    @Override
    public void create() {

    }

    @Override
    public void save() {

    }
}

class NicePDFDocument implements NiceDocument, PrintableDocument {
    @Override
    public void print() {

    }

    @Override
    public void create() {

    }

    @Override
    public void save() {

    }
}