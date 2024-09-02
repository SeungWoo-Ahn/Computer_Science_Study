package Polymorphism;

class Person {
    public void eat(String food) {
        System.out.println(STR."I eat \{food}");
    }

    // 매개변수의 개수 변화
    public void eat(String food1, String food2) {
        System.out.println(STR."I eat \{food1} and \{food2}");
    }

    // 매개변수의 타입 변화
    public void eat(int gram) {
        System.out.println(STR."I eat \{gram}g");
    }

    /*
    메서드의 타입 변화만으론 오버로딩 성립이 안됨
    public String eat(String food) {
        return "I eat something";
    }
    */
}