package ru.job4j.ood.lsp;
/*
Класс SingleAutoTransmission нарушает правило "Постусловия (Postconditions)
не могут быть ослаблены в подклассе", т.е. перед переключением передачи метод должен
проверить что значение gear находится в допустимом диаппазоне, иначе измененеие
значения gear приведет к получению невалидного значения на выходе.
 */
public class SingleAutoTransmission extends AutomaticTransmission {
    private int maxGear;
    private int minGear;

    public SingleAutoTransmission(int maxGear, int minGear) {
        this.maxGear = maxGear;
        this.minGear = minGear;
    }

    @Override
    public int toggle(int gear, boolean flag) {
        if (flag) {
            gear++;
        } else {
            gear--;
        }
        return gear;
    }
}
