public class Team {
    public String name_team;
    Competitor competitors[] = new Competitor[3];
    public Team(String name_team, Human human, Cat cat, Dog dog) {
        this.name_team = name_team;
        competitors[0] = human;
        competitors[1] = cat;
        competitors[2] = dog;
    }

    public void showResults(){
        System.out.println("==========================");
        for (int i = 0; i < competitors.length; i++) {
            competitors[i].showResult();
        }
    }
}
