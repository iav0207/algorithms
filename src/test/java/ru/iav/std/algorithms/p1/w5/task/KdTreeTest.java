package ru.iav.std.algorithms.p1.w5.task;

import ru.iav.std.algorithms.p1.w5.task.struct.KdTreeSet;
import ru.iav.std.algorithms.p1.w5.task.struct.SetOfPoints;

/**
 * Created by takoe on 24.03.17.
 */
public class KdTreeTest extends SetOfPointsTest {

    @Override
    SetOfPoints createSet() {
        return new KdTreeSet();
    }

}