import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите номер валюты:\n1 – рубли;\n2 – доллары;\n3 – евро;\n4 – юани.\n");
        int currency = sc.nextInt();

        System.out.println("Введите начальную сумму в выбранной валюте:");
        double amount = sc.nextDouble();

        System.out.println("Введите количество лет для расчёта:");
        int years = sc.nextInt();

        String symbol;
        switch (currency) {
            case 2 -> symbol = " USD";
            case 3 -> symbol = " EUR";
            case 4 -> symbol = " CNY";
            default -> symbol = " RUB";
        }

        InterestRate intRate = new InterestRate(currency, years, amount);

        System.out.println("К окончанию срока сумма составит: " + intRate.interest() + symbol);

        // если валюта – не рубли
        if (currency != 1) {
            amount = intRate.interest();
            ExchangeRate excRate = new ExchangeRate(currency, amount);
            double roubles = excRate.exchange();
            // Выведите на экран сообщение с результатом: "В рублях это будет: ... руб."
            System.out.println("В рублях это будет: " + roubles + " руб.");
        }

        // выведите на экран сообщение о завершении работы программы: "Работа с программой завершена"
        System.out.println("Работа с программой завершена");
    }
}

class ExchangeRate {
    private double exchangeRateUSD = 94.8;
    private double exchangeRateEUR = 103.8;
    private double exchangeRateCNY = 13.1;

    private int currency;
    private double amount;

    public double getExchangeRateUSD() {
        return exchangeRateUSD;
    }

    public double getExchangeRateEUR() {
        return exchangeRateEUR;
    }

    public double getExchangeRateCNY() {
        return exchangeRateCNY;
    }

    public int getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }

    public ExchangeRate(int currency, double amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public double exchange() {
        double amount = getAmount();
        double excUSD = getExchangeRateUSD();
        double excEUR = getExchangeRateEUR();
        double excCNY = getExchangeRateCNY();
        return switch (currency) {
            case 2 -> excUSD * amount;
            case 3 -> excEUR * amount;
            case 4 -> excCNY * amount;
            default -> amount;
        };
    }
}

class InterestRate {
    private double interestRateRUB = 7;
    private double interestRateUSD = 1;
    private double interestRateEUR = 0.8;
    private double interestRateCNY = 1.5;

    private int currency, years;
    private double amount;

    public double getInterestRateRUB() {
        return interestRateRUB;
    }

    public double getInterestRateUSD() {
        return interestRateUSD;
    }

    public double getInterestRateEUR() {
        return interestRateEUR;
    }

    public double getInterestRateCNY() {
        return interestRateCNY;
    }

    public int getCurrency() {
        return currency;
    }
    public int getYears() {
        return years;
    }
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public InterestRate() {}

    public InterestRate(int currency, int years, double amount) {
        this.currency = currency;
        this.years = years;
        this.amount = amount;
    }

    public double interest() {
        double interestRate;
        switch (getCurrency()) {
            case 2 -> interestRate = getInterestRateUSD();
            case 3 -> interestRate = getInterestRateEUR();
            case 4 -> interestRate = getInterestRateCNY();
            default -> interestRate = getInterestRateRUB();
        }
        double amount = getAmount();
        for (int i = 1; i <= years; i++) {
           amount = amount + amount * (interestRate / 100);

            if (i % 2 == 0) {
                amount = amount + amount / 100;
            }
        }
        return amount;
    }

}
