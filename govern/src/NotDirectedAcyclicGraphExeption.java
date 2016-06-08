/**
 * Created by anatolii on 07.06.16.
 */
public class NotDirectedAcyclicGraphExeption extends RuntimeException
{
    public NotDirectedAcyclicGraphExeption()
    {
        super("The grapf is not a DAG");
    }
}
