package src.model;

import java.util.*;

public class DicePool implements List<Die> {
	private ArrayList<Die> dice = new ArrayList<>();
	private final boolean isPublic;

	public DicePool() {
		for (Color color : Color.values()) {
			dice.add(new ColoredDie(color));
		}
		isPublic = false;
	}

	public DicePool(boolean isPublic) {
		for (int i = 0; i < 2; i++) {
			dice.add(new Die());
		}
		this.isPublic = isPublic;
	}

	public void remove(Die die) {
		dice.remove(die);
	}

	public ArrayList<Die> getDice() {
		return dice;
	}

	public void throwDice() {
		for (Die die : dice) {
			die.throwDie();
		}
	}

	public boolean isPublic() {
		return isPublic;
	}

	@Override
	public int size() {
		return dice.size();
	}

	@Override
	public boolean isEmpty() {
		return dice.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return dice.contains(o);
	}

	@Override
	public Iterator<Die> iterator() {
		return dice.iterator();
	}

	@Override
	public Object[] toArray() {
		return dice.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return dice.toArray(a);
	}

	@Override
	public boolean add(Die die) {
		return dice.add(die);
	}

	@Override
	public boolean remove(Object o) {
		return dice.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return dice.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends Die> c) {
		return dice.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends Die> c) {
		return dice.addAll(index, c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return dice.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return dice.retainAll(c);
	}

	@Override
	public void clear() {
		dice.clear();
	}

	@Override
	public Die get(int index) {
		return dice.get(index);
	}

	public Die get(Color color) {
		for (Die die : getDice()) {
			final ColoredDie colDie = (ColoredDie) die;
			if (colDie.getColor() == color) {
				return colDie;
			}
		}
		return null;
	}

	@Override
	public Die set(int index, Die element) {
		return dice.set(index, element);
	}

	@Override
	public void add(int index, Die element) {
		dice.add(index, element);
	}

	@Override
	public Die remove(int index) {
		return dice.remove(index);
	}

	@Override
	public int indexOf(Object o) {
		return dice.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return dice.lastIndexOf(o);
	}

	@Override
	public ListIterator<Die> listIterator() {
		return dice.listIterator();
	}

	@Override
	public ListIterator<Die> listIterator(int index) {
		return dice.listIterator(index);
	}

	@Override
	public List<Die> subList(int fromIndex, int toIndex) {
		return dice.subList(fromIndex, toIndex);
	}
}
