class Bet {
    /*типы ставок (константы статик, использовать их можно не в контексте объекта, а в контексте класса.)
    значения 0, 1, 2, 3, 4 являются числовыми кодами, которые соответствуют типам ставок на скачках
   */
    public static final int FIRST_PLACE = 0; //ставка на лошадь, которая займет первое место в гонке
    public static final int LAST_PLACE = 1;// ставка на лошадь, которая займет последнее место в гонке
    public static final int SOMETHING = 2;// ставка на лошадь, которая займет одно из трех место в гонке
    public static final int PLACE_ONE_OF_TWO = 3;// ставка: одна из двух лошадей займет первое или второе место
    public static final int PLACE_ONE_OF_THREE = 4;//ставка: одна из трех лошадей займет первое, второе или третье место

    private User user;
    private int horseNum;
    private int amount;
    private int betType;

    //конструктор, который принимет параметры (пользователь, номер лошади, сумма ставки, тип ставки)
    public Bet(User user, int horseNum, int amount, int betType) {
        this.user = user;
        this.horseNum = horseNum;
        this.amount = amount;
        this.betType = betType;
    }

    public User getUser() {
        return user;
    }

    public int getHorseNum() {
        return horseNum;
    }

    public int getAmount() {
        return amount;
    }

    public int getBetType() {
        return betType;
    }

    //возвращает строку с названием типа ставки в соответствии с переданным в параметре betType значением
    public static String getBetTypeName(int betType) {
        return switch (betType) {
            case FIRST_PLACE -> "First place";
            case LAST_PLACE -> "Last place";
            case SOMETHING -> "Some";
            case PLACE_ONE_OF_TWO -> "Place 12";
            case PLACE_ONE_OF_THREE -> "Place 123";
            default -> "";
        };
    }
}