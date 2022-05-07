package demo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

interface NestedInteger {
    boolean isInteger();
    Integer getInteger();
    List<NestedInteger> getList();
}

class NestedIntegerImpl implements NestedInteger{
    Integer value;
    List<NestedInteger> list;
    public NestedIntegerImpl(Integer value) {
        this.value = value;
    }
    public NestedIntegerImpl(List<NestedInteger> list) {
        this.list = list;
    }
    @Override
    public boolean isInteger() {
        return value != null;
    }
    @Override
    public Integer getInteger() {
        return value;
    }
    @Override
    public List<NestedInteger> getList() {
        return list;
    }
}	

class NestedIterator implements Iterator<Integer> {
    public static void main(String[] args) {
        NestedIntegerImpl nestedInteger1 = new NestedIntegerImpl(1);
        NestedIntegerImpl nestedInteger2 = new NestedIntegerImpl(2);
        ArrayList<NestedInteger> list1 = new ArrayList<>();
        list1.add(nestedInteger1);
        list1.add(nestedInteger1);
        NestedIntegerImpl nestedInteger4 = new NestedIntegerImpl(list1);
        ArrayList<NestedInteger> list3 = new ArrayList<>();
        list3.add(nestedInteger4);
        list3.add(nestedInteger2);
        list3.add(nestedInteger4);


        NestedIterator nestedIterator = new NestedIterator(list3);
        while (nestedIterator.hasNext()){
            System.out.println(nestedIterator.next());
        }
    }
    List<NestedInteger> nestedList;
    NestedIterator nextIterator;
    int index;
    public NestedIterator(List<NestedInteger> nestedList) {
        this.nestedList = nestedList;
        this.index = 0;
    }

    @Override
    public Integer next() {
        NestedInteger nestedInteger = nestedList.get(index);
        if(nestedInteger == null){
            index++;
            return null;
        }
        if(nestedInteger.isInteger()){
            index++;
            return nestedInteger.getInteger();
        }
        nextIterator = nextIterator == null ? new NestedIterator(nestedInteger.getList()) : nextIterator;
        if(nextIterator.hasNext()){
            return nextIterator.next();
        }
        return next();
    }
    @Override
    public boolean hasNext() {
        if(nestedList == null || nestedList.size() <= index){
            return false;
        }
        if(nextIterator != null){
            if(!nextIterator.hasNext()){
                index++;
                nextIterator = null;
                return hasNext();
            }
            return true;
        }
        if(!nestedList.get(index).isInteger()){
            if(nestedList.get(index).getList().isEmpty()){
                index++;
                return hasNext();
            }
            nextIterator = new NestedIterator(nestedList.get(index).getList());
            return hasNext();
        }
        return true;
    }
}
