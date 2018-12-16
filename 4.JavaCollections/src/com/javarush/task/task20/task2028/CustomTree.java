package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/* 
Построй дерево(1)
*/
public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {

    Entry<String> root;

    public CustomTree() {
        this.root = new Entry<>("root");
    }

    @Override
    public boolean add(String elementName) {
        return super.add(elementName);
    }



    @Override
    public String get(int index) {
        throw new UnsupportedOperationException() ;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    public String getParent(String s) {
        return null;
    }

    static class Entry<T> implements Serializable{
        String elementName;
        int lineNumber;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String elementName) {
            this.elementName = elementName;
            this.availableToAddLeftChildren = true;
            this.availableToAddRightChildren = true;
        }

        void checkChildren(){
            if (this.leftChild != null){
                this.availableToAddLeftChildren = false;
            }
            if (this.rightChild != null){
                this.availableToAddRightChildren = false;
            }
        }

        boolean isAvailableToAddChildren(){
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }
}
