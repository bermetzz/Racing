import java.util.ArrayList;
import java.util.Random;


public class Racing {
    private final ArrayList<Horse> horses; //список лошадей
    private final ArrayList<Bet> bets; //список ставок
    private int horseNumWon; //номер победившей лошади
    private int totalPrizePool; //общий призовой фонд
    private int betTypeWon; //тип победившей ставки

    //инициализация
    public Racing() {
        horses = new ArrayList<>();
        bets = new ArrayList<>();
        horseNumWon = -1; //пока не известно, какая лошадь выиграла гонку
        totalPrizePool = 0; //пока никто не сделал ставок на гонку
        betTypeWon = -1; //пока не известно, какой тип ставки выиграл
    }

    //метод, который добавляет новую лошадь
    public void addHorse(String name) {
        horses.add(new Horse(name));
    }

    //сделать ставку
    public void makeBet(User user, int horseNum, int amount, int betType) {
        if (horseNum < 0 || horseNum >= horses.size()) {
            System.out.println("Неверный № лошади.");
        }

        if (amount < 0) {
            System.out.println("Неверная сумма ставки.");

        } else bets.add(new Bet(user, horseNum, amount, betType));
    }

    //начать гонку
    public void startRace() {
        Random random = new Random();
        horseNumWon = random.nextInt(horses.size()); //рандомно вычисляется номер побежившей лошади
        totalPrizePool = 0;

        /*проходимся циклом по списку ставок, проверяем, совпадает ли номер лошади, на которую была
        сделана ставка с номером победившей лошади, если да -> выполняется расчет выигрыша с помощью метода
        calculatePayout, который зависит от типа ставки (bet), и добавляется выигрыш в баланс пользователя
        (bet.getUser().addMoney(payout)).Также общая сумма призового фонда увеличивается на сумму выигрыша
        текущей ставки.Тип выигравшей ставки (betTypeWon) устанавливается равным типу текущей ставки.
         */
        for (Bet bet : bets) {
            if (bet.getHorseNum() == horseNumWon) {
                int payout = calculatePayout(bet);
                bet.getUser().addMoney(payout);
                totalPrizePool += payout;
                betTypeWon = bet.getBetType();
            }
        }
    }


    //расчет выигрыша для конкретной ставки
    private int calculatePayout(Bet bet) {
        int payout = 0;

        switch (bet.getBetType()) {
            case Bet.FIRST_PLACE -> { //если 1 лошадь
                if (bet.getHorseNum() == horseNumWon) {
                    payout = bet.getAmount() * horses.size() * 2;
                }
            }
            case Bet.LAST_PLACE -> { //если последняя лошадь
                if (bet.getHorseNum() == (horses.size() - 1)) {
                    payout = bet.getAmount() * horses.size() * 2;
                }
            }
            case Bet.SOMETHING -> payout = bet.getAmount() * horses.size(); //одно из 3 мест
            case Bet.PLACE_ONE_OF_TWO -> payout = bet.getAmount() * horses.size() / 2; //одна из двух лошадей-одно из двух мест
            case Bet.PLACE_ONE_OF_THREE -> payout = bet.getAmount() * horses.size() / 3; ////одна из трех лошадей-одно из трех мест
            default -> {}
        }
        return payout;
    }

    public void results() {
        System.out.println("Победила лошадь: " + horses.get(horseNumWon).getName());
        System.out.println("Общий призовой фонд: " + totalPrizePool);

        //проверка, какая ставка выиграшная
        switch (betTypeWon) {
            case Bet.FIRST_PLACE -> System.out.println("виграшная ставка: First place");
            case Bet.LAST_PLACE -> System.out.println("виграшная ставка: Last place");
            case Bet.SOMETHING -> System.out.println("виграшная ставка: Some");
            case Bet.PLACE_ONE_OF_TWO -> System.out.println("виграшная ставка: Place12");
            case Bet.PLACE_ONE_OF_THREE -> System.out.println("виграшная ставка: Place123");
            default -> {
            }
        }

        //вывод
        for (Bet bet : bets) {
            int payout = calculatePayout(bet);

            if (payout > 0) {
                System.out.println("Пользователь " + bet.getUser().getName() + " выиграл " + payout + " со ставкой на лошадь " +
                        horses.get(bet.getHorseNum()).getName() + " тип " + Bet.getBetTypeName(bet.getBetType()));
            } else {
                System.out.println("Пользователь " + bet.getUser().getName() + " проиграл со ставкой на лошадь " +
                        horses.get(bet.getHorseNum()).getName() + " тип " + Bet.getBetTypeName(bet.getBetType()));
            }
        }
    }
}
