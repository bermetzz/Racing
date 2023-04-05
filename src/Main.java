public class Main {
    public static void main(String[] args) {
        Racing racing = new Racing();
        racing.addHorse("Horse №1");
        racing.addHorse("Horse №2");
        racing.addHorse("Horse №3");
        racing.addHorse("Horse №4");
        racing.addHorse("Horse №5");
        racing.addHorse("Horse №6");

        User user1 = new User("Irina", 300);
        User user2 = new User("Nina", 200);
        User user3 = new User("Lena", 200);

        //сделать ставку
        racing.makeBet(user2, 1, 20, Bet.FIRST_PLACE);
        racing.makeBet(user1, 0, 30, Bet.SOMETHING);
        racing.makeBet(user3, 5, 20, Bet.PLACE_ONE_OF_THREE);

        //старт гонки
        racing.startRace();
        //результаты
        racing.results();
    }
}
