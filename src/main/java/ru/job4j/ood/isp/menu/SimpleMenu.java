package ru.job4j.ood.isp.menu;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        boolean rsl = false;
        boolean childNotFound = findItem(childName).isEmpty();
        Optional<ItemInfo> parentItemInfo = findItem(parentName);
        if (childNotFound && parentName == Menu.ROOT) {
            rootElements.add(new SimpleMenuItem(childName, actionDelegate));
            rsl = true;
        } else if (childNotFound && parentItemInfo.isPresent()) {
            parentItemInfo.get().menuItem.getChildren().
                    add(new SimpleMenuItem(childName, actionDelegate));
            rsl = true;
        }
        return rsl;
}

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        return findItem(itemName).map(el -> new MenuItemInfo(el.menuItem,
                el.getNumber()));
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        return new Iterator<>() {
            private Iterator<ItemInfo> dfsIterator = new DFSIterator();

            @Override
            public boolean hasNext() {
                return dfsIterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                MenuItemInfo rsl;
                ItemInfo itemInfo = dfsIterator.next();
                MenuItem menuItem = itemInfo.menuItem;
                if (menuItem.getChildren().size() != 0) {
                    rsl = new MenuItemInfo(menuItem.getName(), menuItem.getChildren().
                            stream().map(MenuItem::getName).collect(Collectors.toList()),
                            menuItem.getActionDelegate(),
                            itemInfo.getNumber());
                } else {
                    rsl = new MenuItemInfo(menuItem, itemInfo.getNumber());
                }
                return rsl;
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        Optional<ItemInfo> rsl = Optional.empty();
        if (name != null) {
            Iterator<ItemInfo> iterator = new DFSIterator();
            while (iterator.hasNext()) {
                ItemInfo itemInfo = iterator.next();
                if (name.equals(itemInfo.getMenuItem().getName())) {
                    rsl = Optional.of(itemInfo);
                    break;
                }
            }
        }
        return rsl;
    }

private static class SimpleMenuItem implements MenuItem {

    private String name;
    private List<MenuItem> children = new ArrayList<>();
    private ActionDelegate actionDelegate;

    public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
        this.name = name;
        this.actionDelegate = actionDelegate;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<MenuItem> getChildren() {
        return children;
    }

    @Override
    public ActionDelegate getActionDelegate() {
        return actionDelegate;
    }
}

private class DFSIterator implements Iterator<ItemInfo> {

    private Deque<MenuItem> stack = new LinkedList<>();

    private Deque<String> numbers = new LinkedList<>();

    DFSIterator() {
        int number = 1;
        for (MenuItem item : rootElements) {
            stack.addLast(item);
            numbers.addLast(String.valueOf(number++).concat("."));
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public ItemInfo next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        MenuItem current = stack.removeFirst();
        String lastNumber = numbers.removeFirst();
        List<MenuItem> children = current.getChildren();
        int currentNumber = children.size();
        for (var i = children.listIterator(children.size()); i.hasPrevious();) {
            stack.addFirst(i.previous());
            numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
        }
        return new ItemInfo(current, lastNumber);
    }

}

private class ItemInfo {

    private MenuItem menuItem;
    private String number;

    public ItemInfo(MenuItem menuItem, String number) {
        this.menuItem = menuItem;
        this.number = number;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public String getNumber() {
        return number;
    }
}

}
