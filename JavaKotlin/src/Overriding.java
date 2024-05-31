class Animal {
    public void bark() {
        System.out.println("Ahoooo!");
    }
}

class Dog extends Animal {
    @Override
    public void bark() {
        System.out.println("wall!!");
    }
}
