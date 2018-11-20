public class Main {
    public static void main(String[] args) {

        Course c = new Course(new Cross(400), new Wall(8), new Water(1));
        Team team = new Team("sport" ,new Human("Боб"), new Cat("Барсик"), new Dog("Бобик"));
        c.doIt(team);
        team.showResults();
    }
}
