package com.cooksys.ftd.assignments.collections.model;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class FatCat implements Capitalist {
	private String name;
	private int salary;
	private FatCat owner;
	
    public FatCat(String name, int salary) {
    	this.name = name;
    	this.salary = salary;
    }

    public FatCat(String name, int salary, FatCat owner) {
    	this.name = name;
    	this.salary = salary;
    	this.owner = owner;
    }

    /**
     * @return the name of the capitalist
     */
    @Override
    public String getName() {
    	return this.name;
    }

    /**
     * @return the salary of the capitalist, in dollars
     */
    @Override
    public int getSalary() {
    	return this.salary;
    }

    /**
     * @return true if this element has a parent, or false otherwise
     */
    @Override
    public boolean hasParent() {
    	return this.owner != null;
    }

    /**
     * @return the parent of this element, or null if this represents the top of a hierarchy
     */
    @Override
    public FatCat getParent() {
    	return this.owner;
    }
}
