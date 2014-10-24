import java.util.Comparator;

/**
 * Created by arunk on 10/14/14.
 */
public class CellComparator implements Comparator<Cell> {
    @Override
    public int compare(Cell x, Cell y)
    {
        if (x.distance < y.distance)
        {
            return -1;
        }
        if (x.distance > y.distance)
        {
            return 1;
        }
        return 0;
    }
}
