public class Human implements Competitor{
    String type;
    String name;
    int maxRunDistance;
    int maxJumpHeight;
    int maxSwingDistance;
    boolean onDistance;

    public boolean isOnDistance() {
        return onDistance;
    }

    public Human(String name) {
        this.name = name;
        this.maxRunDistance = 5000;
        this.maxJumpHeight = 20;
        this.maxSwingDistance = 200;
        this.onDistance = true;
    }

    public void run(int distance){
        if(distance <= maxRunDistance) {
            System.out.println(name + " " + " успешно справился с кроссом");
        }else{
            System.out.println(name + " " + " не смог преодолеть кросс");
            onDistance = false;
        }
    }

    public void jump(int height){
        if(height <= maxJumpHeight) {
            System.out.println(name + " " + " успешно справился с препятствием");
        }else{
            System.out.println(name + " " + " не смог преодолеть препятствие");
            onDistance = false;
        }
    }

    public void swim(int distance){
        if(maxSwingDistance == 0) {
            System.out.println(name + " " + " не умеет плавать");
            onDistance = false;
            return;
        }

        if(distance <= maxSwingDistance) {
            System.out.println(name + " " + " успешно проплыл дистанцию");
        }else{
            System.out.println(name + " " + " не смог проплыть дистанцию");
            onDistance = false;
        }
    }

    public void showResult(){
        System.out.println(name + " " +onDistance);
    }
}

