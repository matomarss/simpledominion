package sk.uniba.fmph.dcs;

public class Couple<T>
{
    private T value1;
    private T value2;

    public Couple(T value1, T value2)
    {
        this.value1 = value1;
        this.value2 = value2;
    }

    public T getFirst()
    {
        return value1;
    }
    public T getSecond()
    {
        return value2;
    }
    public void setFirst(T value)
    {
        value1 = value;
    }
    public void setSecond(T value)
    {
        value2 = value;
    }
}
