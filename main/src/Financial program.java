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

record ExchangeRate(int currency, double amount) {

    public double getExchangeRateUSD() {
        return 94.8;
    }

    public double getExchangeRateEUR() {
        return 103.8;
    }

    public double getExchangeRateCNY() {
        return 13.1;
    }

    public double exchange() {
        double amount = amount();
        double excUSD = getExchangeRateUSD();
        double excEUR = getExchangeRateEUR();
        double excCNY = getExchangeRateCNY();
        int cny = currency();
        return switch (cny) {
            case 2 -> excUSD * amount;
            case 3 -> excEUR * amount;
            case 4 -> excCNY * amount;
            default -> amount;
        };
    }
}

record InterestRate(int currency, int years, double amount) {

    public double getInterestRateRUB() {
        return 7;
    }

    public double getInterestRateUSD() {
        return 1;
    }

    public double getInterestRateEUR() {
        return 0.8;
    }

    public double getInterestRateCNY() {
        return 1.5;
    }

    public double interest() {
        double interestRate;
        switch (currency()) {
            case 2 -> interestRate = getInterestRateUSD();
            case 3 -> interestRate = getInterestRateEUR();
            case 4 -> interestRate = getInterestRateCNY();
            default -> interestRate = getInterestRateRUB();
        }
        double amount = amount();
        for (int i = 1; i <= years(); i++) {
            amount = amount + amount * (interestRate / 100);

            if (i % 2 == 0) {
                amount = amount + amount / 100;
            }
        }
        return amount;
    }

}
