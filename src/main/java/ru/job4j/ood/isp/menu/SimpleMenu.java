package ru.job4j.ood.isp.menu;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleMenu implements Menu {

    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        if (parentName != null && findItem(parentName).isPresent()) {
            findItem(parentName).get().menuItem.getChildren().
                    add(new SimpleMenuItem(childName, actionDelegate));
        } else {
            rootElements.add(new SimpleMenuItem(childName, actionDelegate));
        }
        return true;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        Optional<MenuItemInfo> rsl = Optional.empty();
        Optional<ItemInfo> itemInfo = findItem(itemName);
        if (itemInfo.isPresent() && itemInfo.get().menuItem.getChildren().size() != 0) {
            rsl = Optional.of(new MenuItemInfo(itemName, itemInfo.get().menuItem.getChildren().
                    stream().map(MenuItem::getName).collect(Collectors.toList()),
                    itemInfo.get().menuItem.getActionDelegate(),
                    itemInfo.get().getNumber()));
        } else if (itemInfo.isPresent()) {
            rsl = Optional.of(new MenuItemInfo(itemInfo.get().menuItem,
                    itemInfo.get().getNumber()));
        }
        return rsl;
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
        Iterator<ItemInfo> iterator = new DFSIterator();
        while (iterator.hasNext()) {
            ItemInfo itemInfo = iterator.next();
            if (name.equals(itemInfo.getMenuItem().getName())) {
                rsl = Optional.of(itemInfo);
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
