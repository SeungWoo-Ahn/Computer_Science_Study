class Rectangle {
    protected int width;
    protected int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int calculateArea() {
        return width * height;
    }
}

// Rectangle 클래스의 메서드가 재정의되면
// Square 의 동작이 변경되므로 리스코프치환 원칙을 위배함
class Square extends Rectangle {
    @Override
    public void setWidth(int width) {
        this.width = width;
        this.height = width;
    }

    @Override
    public void setHeight(int height) {
        this.width = height;
        this.height = height;
    }
}

// Shape 인터페이스로 대체 가능성을 보장함
// 동작을 변경하기 않고 Square 클래스가 Rectangle 클래스를 올바르게 대체함
interface Shape {
    int calculateArea();
}

class NiceRectangle implements Shape {
    private int width;
    private int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int calculateArea() {
        return width * height;
    }
}

class NiceSquare implements Shape {
    private int side;

    public void setSide(int side) {
        this.side = side;
    }

    @Override
    public int calculateArea() {
        return side * side;
    }
}