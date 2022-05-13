package src.model;

import java.util.*;

public class Row implements List<NumberField> {
	private final Color color;
	private final boolean isAscending;
	private boolean isLocked;
	private final ArrayList<NumberField> numberFields = new ArrayList<>();
	private final HashMap<Integer, NumberField> numberFieldHashByValueMap = new HashMap<>();
	private static final int ROW_LENGTH = 11;

	public Row(Color color, boolean isAscending) {
		this.color = color;
		this.isAscending = isAscending;
		this.isLocked = false;

		int val = 13;
		for (int i = 0; i < 11; i++) {
			val = isAscending ? i + 2 : val - 1;
			NumberField numberField = new NumberField(val, i, this);
			numberFields.add(numberField);
			numberFieldHashByValueMap.put(val, numberField);
		}
	}

	public NumberField getLast() {
		return get(ROW_LENGTH - 1);
	}

	public NumberField getNumberFieldByValue(int value) {
		return numberFieldHashByValueMap.get(value);
	}

	public Color getColor() {
		return color;
	}

	public boolean isAscending() {
		return isAscending;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked() {
		isLocked = true;
	}

	public int getRowScore() {
		int amountOfCrosses = 0;
		int totalScore = 0;
		for (NumberField numberField : numberFields) {
			if (numberField.isCrossed()) {
				amountOfCrosses++;
				totalScore += amountOfCrosses;
			}
		}
		return totalScore;
	}

	public ArrayList<NumberField> getNumberFields() {
		return numberFields;
	}

	public void disableNumberFieldsBefore(int index) {
		final ListIterator<NumberField> iterator = listIterator(index);
		while (iterator.hasPrevious()) {
			NumberField n = iterator.previous();
			if (n.isCrossed()) break;
			n.setDisabled();
		}
	}

	public int getAmountOfCrossedNumbers() {
		int total = 0;
		for (NumberField numberField : numberFields) {
			if (numberField.isCrossed()) {
				total++;
			}
		}
		return total;
	}

	@Override
	public int size() {
		return numberFields.size();
	}

	@Override
	public boolean isEmpty() {
		return size() > 0;
	}

	@Override
	public boolean contains(Object o) {
		return numberFields.contains(o);
	}

	@Override
	public Iterator<NumberField> iterator() {
		return numberFields.iterator();
	}

	@Override
	public Object[] toArray() {
		return numberFields.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return numberFields.toArray(a);
	}

	@Override
	public boolean add(NumberField numberField) {
		return numberFields.add(numberField);
	}

	@Override
	public boolean remove(Object o) {
		return numberFields.remove(o);
	}

	@Override
	public boolean addAll(Collection c) {
		return numberFields.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection c) {
		return numberFields.addAll(index, c);
	}

	@Override
	public void clear() {
		numberFields.clear();
	}

	@Override
	public NumberField get(int index) {
		return numberFields.get(index);
	}

	@Override
	public NumberField set(int index, NumberField element) {
		return numberFields.set(index, element);
	}

	@Override
	public void add(int index, NumberField element) {
		numberFields.add(index, element);
	}

	@Override
	public NumberField remove(int index) {
		return numberFields.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return numberFields.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return numberFields.lastIndexOf(o);
	}

	@Override
	public ListIterator<NumberField> listIterator() {
		return numberFields.listIterator();
	}

	@Override
	public ListIterator<NumberField> listIterator(int index) {
		return numberFields.listIterator(index);
	}

	@Override
	public List<NumberField> subList(int fromIndex, int toIndex) {
		return numberFields.subList(fromIndex, toIndex);
	}

	@Override
	public boolean retainAll(Collection c) {
		return numberFields.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection c) {
		return numberFields.retainAll(c);
	}

	@Override
	public boolean containsAll(Collection c) {
		return numberFields.containsAll(c);
	}
}
