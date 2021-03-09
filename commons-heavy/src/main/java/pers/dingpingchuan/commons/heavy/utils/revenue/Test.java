package pers.dingpingchuan.commons.heavy.utils.revenue;

public class Test {

    public static void main(String[] args) {

        double ratio = 0.05;// 年化收益率
        int year = 20;//持续的时间（单位是年）
        double everyYearRent = 73200;

        int afterDingtouYear = 50;
        //每月还款 16400  20年  首付140
        // 1. 首付以及首付20年的利息
        double shoufu = calculateCompound(1400000, ratio, year);
        // 2. 还贷定投20年
        double dingtou = calculate(16400, 0.1, year);

        //3. 20年房租费用
        double rent = calculateHouseRent(everyYearRent, ratio, year);

        // 4.房租20年之后价格
        double houseRevenue = calculateCompound(350, ratio, year);

        double revenueWithoutHouse = (shoufu + dingtou - rent)/10000;

        double rent40 = calculateHouseRent(everyYearRent, ratio, afterDingtouYear);

        double revenueWithoutHouse40 = ( calculateCompound(dingtou+shoufu, ratio, afterDingtouYear-year)-rent40)/10000;

        double houseRevenue40 = calculateCompound(350, ratio, afterDingtouYear);

        System.out.println("首付以及收益：" + shoufu + ", 贷款定投收益：" + dingtou + ", 房租总费用：" + rent
                + ", " + year + "年之后房子总价格：" + houseRevenue);
        System.out.println("不买房子" + year + "年之后现金有" + revenueWithoutHouse+"万元");
        System.out.println("房子" + year + "年之后价值" + houseRevenue+"万元");
        System.out.println("----------");
        System.out.println("不买房子" + afterDingtouYear + "年之后现金有" + revenueWithoutHouse40+"万元");
        System.out.println("房子" + afterDingtouYear + "年之后价值" + houseRevenue40+"万元");
    }

    public static double calculate(double everyMonthDeposit, double ratio, double year) {

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
        return endMoney;
    }

    /**
     * @param everyYear 目前每个年的房租
     * @param ratio     房租每年平均增长率
     * @param year      租房年限
     * @return 总房租
     */
    public static double calculateHouseRent(double everyYear, double ratio, int year) {
        double wholeHouseRent = 0;

        double everyYearFinalRent = everyYear;
        for (int i = 1; i <= year; i++) {
            System.out.println("第" + i + "年,当年的租金:" + everyYearFinalRent);

            wholeHouseRent += everyYearFinalRent;
            everyYearFinalRent = everyYearFinalRent * (1 + ratio);

            System.out.println("第" + i + "年,累计的租金:" + wholeHouseRent);
            System.out.println("---------------");
        }
        return wholeHouseRent;
    }


    /**
     * @param principal 本金
     * @param ratio     年化率
     * @param year      年限
     * @return 总资产
     */
    public static double calculateCompound(double principal, double ratio, int year) {
        double finalInterest = 0;
        double finalRevenue = principal;
        for (int i = 1; i <= year; i++) {

            double interest = finalRevenue * ratio;
            finalInterest += interest;
            finalRevenue += interest;
            System.out.println("第" + i + "年,当年的本金和利息总计:" + finalRevenue);
            System.out.println("---------------");
        }
        System.out.println("总利息:" + finalInterest);

        return finalRevenue;
    }
}
