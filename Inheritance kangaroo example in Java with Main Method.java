public class Kangaroo extends Animal {
    void jump() {
        System.out.println("Jumping...");
    }

    public static void main(String[] args) {
        Kangaroo k = new Kangaroo();
        k.eat();
        k.jump();
    }
}

class Animal {
    void eat() {
        System.out.println("Eating...");
    }
}
