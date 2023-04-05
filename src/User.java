class User {
    private final String name;
    private int money;

    public User(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    //метод, который добавляет выплату
    public void addMoney(int amount) {
        money += amount;
    }
}