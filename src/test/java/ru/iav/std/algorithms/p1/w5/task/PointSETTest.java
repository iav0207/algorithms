package ru.iav.std.algorithms.p1.w5.task;

import ru.iav.std.algorithms.p1.w5.task.struct.PointSetSet;
import ru.iav.std.algorithms.p1.w5.task.struct.SetOfPoints;

/**
 * Created by takoe on 24.03.17.
 */
public class PointSETTest extends SetOfPointsTest {

    @Override
    SetOfPoints createSet() {
        return new PointSetSet();
    }

}
