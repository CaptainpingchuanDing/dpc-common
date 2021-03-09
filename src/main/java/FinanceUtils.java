public class FinanceUtils {

    public static void main(String[] args) {

        calculateCompound(3600, 30, 0.1);
//        caculateCompound(100, 20, 0.1);
        calculate(300,0.1,30);

    }

    public static double calculateCompound(double capital, long year, double ratio) {
        if (year < 0||ratio<=0) {
            System.out.println("ratio和 year不能小于0");
            return capital;
        }
        double finalRevenue = capital;
        for (int i = 1; i <= year; i++) {
            finalRevenue = finalRevenue + finalRevenue * ratio;
//            System.out.println("第" + i + "年,money: " + finalRevenue);
        }
        System.out.println(year + "年后总资产: " + finalRevenue);
        System.out.println(year + "年后总收益率: " + (finalRevenue / capital) * 100 + "%");
        System.out.println();

        return finalRevenue;
    }

    public static void calculate(double everyMonthDeposit, double ratio, double year) {

        double endMoney = 0;
        double revenue = 0;
        double finalRevenue = 0;
        double principal = 0;
        for (int i = 1; i <= year; i++) {
            for (int j = 0; j < 12; j++) {
                revenue = (endMoney + everyMonthDeposit) * ratio / 12;
                finalRevenue = finalRevenue + revenue;
                principal = principal + everyMonthDeposit;
                endMoney = everyMonthDeposit + endMoney + revenue;

            }
            System.out.println("第" + i + "年,money:" + endMoney + revenue + "  累计收益：" + finalRevenue);
        }
        System.out.println("---------------");

        System.out.println("总金额:" + endMoney);
        System.out.println("总本金:" + principal);
        System.out.println("总收益:" + finalRevenue);
    }
}
