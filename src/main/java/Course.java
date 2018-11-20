public class Course {
    public Obstacle[] obstacles = new Obstacle[3];

    public Course(Obstacle cross, Obstacle wall, Obstacle water) {
        this.obstacles[0] = cross;
        this.obstacles[1] = wall;
        this.obstacles[2] = water;
    }

    public void doIt(Team team){
        System.out.println("Комманда " + team.name_team);
        for (int i = 0; i < team.competitors.length; i++) {
            for (int j = 0; j < obstacles.length; j++) {
                obstacles[j].doIt(team.competitors[i]);
            }
        }
    }
}
