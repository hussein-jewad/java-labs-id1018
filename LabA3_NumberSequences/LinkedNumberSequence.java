// LinkedNumberSequence.java
/****************************************************************
 LinkedNumberSequence represents a sequence of real numbers.
 Such a sequence is defined by the interface NumberSequence.
 The class uses linked nodes to store the numbers in the sequence.
 Author
 Fadil Galjic
 ****************************************************************/
public class LinkedNumberSequence implements NumberSequence
{
    private class Node
    {
        public double number;
        public Node next;
        public Node (double number)
        {
            this.number = number;
            next = null;
        }
    }
    // the first node in the node-sequence
    private Node first;
    // create the sequence
    public LinkedNumberSequence (double[] numbers)
    {
        if (numbers.length < 2)
            throw new IllegalArgumentException("not a sequence");
        first = new Node(numbers[0]);
        Node n = first;
        for (int i = 1; i < numbers.length; i++)
        {
            n.next = new Node(numbers[i]);
            n = n.next;
        }
    }
    // toString returns the character string representing this
// sequence
    public String toString ()
    {
        String s = "";
        Node n = first;
        while (n.next != null)
        {
            s = s + n.number + ", ";
            n = n.next;
        }
        s = s + n.number;
        return s;
    }
// add code here
public int length()
{
    int count = 0;
    Node n = first;

    while (n != null)
    {
        count++;
        n = n.next;
    }
    return count;
}

    public double upperBound()
    {
        double max = first.number;
        Node n = first.next;

        while (n != null)
        {
            if (n.number > max)
                max = n.number;
            n = n.next;
        }
        return max;
    }

    public double lowerBound()
    {
        double min = first.number;
        Node n = first.next;

        while (n != null)
        {
            if (n.number < min)
                min = n.number;
            n = n.next;
        }
        return min;
    }

    public double numberAt(int position) throws IndexOutOfBoundsException
    {
        if (position < 0)
            throw new IndexOutOfBoundsException("Position is too large or negative");

        Node n = first;
        int index = 0;

        while (n != null)
        {
            if (index == position)
                return n.number;
            n = n.next;
            index++;
        }

        throw new IndexOutOfBoundsException("Position is too large or negative");
    }

    public int positionOf(double number)
    {
        Node n = first;
        int index = 0;

        while (n != null)
        {
            if (n.number == number)
                return index;
            n = n.next;
            index++;
        }
        return -1;
    }

    public boolean isIncreasing()
    {
        Node n = first;

        while (n.next != null)
        {
            if (n.number >= n.next.number)
                return false;
            n = n.next;
        }
        return true;
    }

    public boolean isDecreasing()
    {
        Node n = first;

        while (n.next != null)
        {
            if (n.number <= n.next.number)
                return false;
            n = n.next;
        }
        return true;
    }

    public boolean contains(double number)
    {
        Node n = first;

        while (n != null)
        {
            if (n.number == number)
                return true;
            n = n.next;
        }
        return false;
    }

    public void add(double number)
    {
        Node n = first;

        while (n.next != null)
            n = n.next;

        n.next = new Node(number);
    }

    public void insert(int position, double number) throws IndexOutOfBoundsException
    {
        if (position < 0)
            throw new IndexOutOfBoundsException("Position is too large or negative");

        if (position == 0)
        {
            Node newNode = new Node(number);
            newNode.next = first;
            first = newNode;
            return;
        }

        Node n = first;
        int index = 0;

        while (n != null)
        {
            if (index == position - 1)
            {
                Node newNode = new Node(number);
                newNode.next = n.next;
                n.next = newNode;
                return;
            }
            n = n.next;
            index++;
        }

        throw new IndexOutOfBoundsException("Position is too large or negative");
    }

    public void removeAt(int position)
            throws IndexOutOfBoundsException, IllegalStateException
    {
        if (length() <= 2)
            throw new IllegalStateException("too few numbers");

        if (position < 0)
            throw new IndexOutOfBoundsException("Position is too large or negative");

        if (position == 0)
        {
            first = first.next;
            return;
        }

        Node n = first;
        int index = 0;

        while (n.next != null)
        {
            if (index == position - 1)
            {
                n.next = n.next.next;
                return;
            }
            n = n.next;
            index++;
        }

        throw new IndexOutOfBoundsException("Position is too large or negative");
    }

    public double[] asArray()
    {
        double[] array = new double[length()];
        Node n = first;
        int i = 0;

        while (n != null)
        {
            array[i++] = n.number;
            n = n.next;
        }
        return array;
    }
}
