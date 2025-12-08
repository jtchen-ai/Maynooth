public class SportCarBuilder implements CarBuilder {

    private final Car car = new Car("SPORTS CAR"); // 初始化时指定汽车类型为 "SPORTS CAR"

    @Override
    public void buildBodyStyle() {
        // 为跑车设置车身样式
        // 示例：更低、更宽、更符合空气动力学的设计
        car.setBodyStyle("External dimensions: overall length (inches): 180.0, " +
                         "overall width (inches): 78.5, overall height (inches): 48.5, wheelbase (inches): 105.0," +
                         " front track (inches): 67.0, rear track (inches): 68.0 and curb to curb turning circle (feet): 38.0. " +
                         "Features: Carbon fiber monocoque, aerodynamic package with rear spoiler.");
    }

    @Override
    public void buildPower() {
        // 为跑车设置动力参数
        // 示例：更高的马力和扭矩
        car.setPower("650 hp @ 7,500 rpm; 550 ft lb of torque @ 5,000 rpm");
    }

    @Override
    public void buildEngine() {
        // 为跑车设置引擎类型
        // 示例：更大排量或涡轮增压引擎
        car.setEngine("5.2L V10 FSI");
    }

    @Override
    public void buildBreaks() {
        // 为跑车设置刹车系统
        // 示例：高性能刹车系统，如陶瓷刹车
        car.setBreaks("High-performance carbon-ceramic brakes: four ventilated and cross-drilled discs. " +
                      "ABS with sport calibration.");
    }

    @Override
    public void buildSeats() {
        // 为跑车设置座椅
        // 示例：运动型桶形座椅，可能只有两个座位
        car.setSeats("Two sport bucket seats with integrated headrests. Leather and Alcantara upholstery. " +
                     "Four-point racing harnesses compatibility.");
    }

    @Override
    public void buildWindows() {

        car.setWindows("Frameless door windows. Lightweight polycarbonate rear window.");
    }

    @Override
    public void buildFuelType() {

        car.setFuelType("Premium Unleaded Gasoline. 12 MPG city, 20 MPG highway, 15 MPG combined and 300 mi. range.");
    }

    @Override
    public Car getCar() {
        return car;
    }
}