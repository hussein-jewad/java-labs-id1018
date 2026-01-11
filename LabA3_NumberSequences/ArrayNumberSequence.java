// ArrayNumberSequence.java
/****************************************************************
 ArrayNumberSequence represents a sequence of real numbers.
 Such a sequence is defined by the interface NumberSequence.
 The class uses an array to store the numbers in the sequence.
 Author
 Fadil Galjic
 ****************************************************************/
public class ArrayNumberSequence implements NumberSequence {
    // numbers in the sequence
    private double[] numbers;

    // create the sequence
    public ArrayNumberSequence(double[] numbers) {
        if (numbers.length < 2)
            throw new IllegalArgumentException("not a sequence");
        this.numbers = new double[numbers.length];
        for (int i = 0; i < numbers.length; i++)
            this.numbers[i] = numbers[i];
    }

    // toString returns the character string representing this
// sequence
    public String toString() {
        String s = "";
        for (int i = 0; i < numbers.length - 1; i++)
            s = s + numbers[i] + ", ";
        s = s + numbers[numbers.length - 1];
        return s;
    }

    // add code here
    public int length() {
        return numbers.length;
    }

    public double upperBound() {
        double upper = numbers[0];
        for (int i = 1; i < numbers.length; i++)
            if (numbers[i] > upper) upper = numbers[i];
        return upper;
    }

    public double lowerBound() {
        double lower = numbers[0];
        for (int i = 1; i < numbers.length; i++)
            if (numbers[i] < lower) lower = numbers[i];
        return lower;
    }

    public double numberAt(int position) throws IndexOutOfBoundsException {
        if (position < 0 || position >= numbers.length)
            throw new IndexOutOfBoundsException("Position is too large or negative");
        return numbers[position];
    }

    public int positionOf(double number) {
        for (int i = 0; i < numbers.length; i++)
            if (number == numbers[i]) return i;
        return -1;
    }

    public boolean isIncreasing() {
        for (int i = 0; i < numbers.length - 1; i++)
            if (numbers[i] >= numbers[i + 1]) return false;
        return true;
    }

    public boolean isDecreasing() {
        for (int i = 0; i < numbers.length - 1; i++)
            if (numbers[i] <= numbers[i + 1]) return false;
        return true;
    }

    public boolean contains(double number) {
        for (int i = 0; i < numbers.length; i++)
            if (numbers[i] == number) return true;
        return false;
    }

    public void add(double number) {
        double[] newNumbers = new double[numbers.length + 1];
        for (int i = 0; i < numbers.length; i++)
            newNumbers[i] = numbers[i];
        newNumbers[numbers.length] = number;
        numbers = newNumbers;
    }

    public void insert(int position, double number) throws IndexOutOfBoundsException {
        if (position < 0 || position > numbers.length) throw new IndexOutOfBoundsException("Position is too large or negative");
        double[] newNumbers = new double[numbers.length + 1];
        for (int i = 0; i < position; i++)
            newNumbers[i] = numbers[i];
        newNumbers[position] = number;
        for (int i = position + 1; i <= numbers.length; i++)
            newNumbers[i] = numbers[i - 1];
        numbers = newNumbers;
    }

    public void removeAt(int position) throws IndexOutOfBoundsException, IllegalStateException {
        if (position < 0 || position >= numbers.length) throw new IndexOutOfBoundsException("Position is too large or negative");
        if (numbers.length <= 2) throw new IllegalStateException("too few numbers");
        double[] newNumbers = new double[numbers.length - 1];
        for (int i = 0; i < position; i++)
            newNumbers[i] = numbers[i];
        for (int i = position + 1; i < numbers.length; i++)
            newNumbers[i - 1] = numbers[i];
        numbers = newNumbers;
    }

    public double[] asArray() {
        double[] newArray = new double[numbers.length];
        for (int i = 0; i < numbers.length; i++)
            newArray[i] = numbers[i];
        return newArray;
    }
}
