package ru.rrishbuldin.carpet_washing.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CarpetUtils {
    /**
     * Рассчитывает площадь в м² по длине и ширине в см
     * @param lengthCm длина в сантиметрах
     * @param widthCm ширина в сантиметрах
     * @return площадь в квадратных метрах (BigDecimal, 2 знака после запятой)
     */
    public static BigDecimal calculateAreaInSquareMeters(int lengthCm, int widthCm) {
        int areaCm2 = lengthCm * widthCm; // площадь в см²
        return new BigDecimal(areaCm2)
                .divide(new BigDecimal(10_000), 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateCarpetPrice(BigDecimal pricePerM2, BigDecimal area) {
        return pricePerM2.multiply(area);
    }
}
