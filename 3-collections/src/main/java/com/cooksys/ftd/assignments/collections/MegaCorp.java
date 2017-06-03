package com.cooksys.ftd.assignments.collections;

import com.cooksys.ftd.assignments.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.collections.model.Capitalist;
import com.cooksys.ftd.assignments.collections.model.FatCat;
import com.cooksys.ftd.assignments.collections.model.WageSlave;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;
import java.util.HashSet;
import java.util.HashMap;

public class MegaCorp implements Hierarchy<Capitalist, FatCat> {

	private HashSet<Capitalist> capitalists;
	private HashMap<FatCat, Set<Capitalist>> hierarchy;
	
	public MegaCorp() {
		this.capitalists = new HashSet<Capitalist>();
		this.hierarchy = new HashMap<FatCat, Set<Capitalist>>();
	}
	
	private boolean isFatCat(Capitalist capitalist) { return capitalist instanceof FatCat; }
	private boolean isWageSlave(Capitalist capitalist) { return capitalist instanceof WageSlave; }
/*	private HashSet<Capitalist> initParent(FatCat parent) {
		boolean added = capitalists.add((Capitalist) parent);
		if (added) hierarchy.put(parent, new HashSet<Capitalist>());
		return (HashSet<Capitalist>) hierarchy.get(parent);
	}
	private boolean updateParent(Capitalist capitalist) {
		Boolean has_parent = capitalist.hasParent();
		if (has_parent) {
			initParent(capitalist.getParent())
				.add(capitalist);
		}
		return has_parent;
	}*/
	private boolean addCapitalist(Capitalist c) { return capitalists.add(c); }
	private boolean addParent(FatCat f) {
		if (hierarchy.containsKey(f)) return false;
		else {
			hierarchy.put(f, new HashSet<Capitalist>());
			return true;
		}
	}
	private boolean updateParent(FatCat p, Capitalist c) {
		if (!hierarchy.containsKey(p)) return false;
		else {
			hierarchy.get(p).add(c);
			return true;
		}
	}
	
    /**
     * Adds a given element to the hierarchy.
     * <p>
     * If the given element is already present in the hierarchy,
     * do not add it and return false
     * <p>
     * If the given element has a parent and the parent is not part of the hierarchy,
     * add the parent and then add the given element
     * <p>
     * If the given element has no parent but is a Parent itself,
     * add it to the hierarchy
     * <p>
     * If the given element has no parent and is not a Parent itself,
     * do not add it and return false
     *
     * @param capitalist the element to add to the hierarchy
     * @return true if the element was added successfully, false otherwise
     */
    @Override
    public boolean add(Capitalist capitalist) {
    	if (has(capitalist)) return false;
    	if (isFatCat(capitalist)) {
    		if (addCapitalist(capitalist)) {
    			if (capitalist.hasParent()) {
    				add(capitalist.getParent());
        			updateParent(capitalist.getParent(), capitalist);
    			}
    			addParent((FatCat) capitalist);
    			return true;
    		} 
    		else return false;
    	} 
    	else if (isWageSlave(capitalist)) {
    		if (capitalist.hasParent()) {
    			if(addCapitalist(capitalist)) {
    				add(capitalist.getParent());	
    				updateParent(capitalist.getParent(), capitalist);
    			}
    			return true;
    		}
    		else return false;
    	} 
    	else return false;
    }

    /**
     * @param capitalist the element to search for
     * @return true if the element has been added to the hierarchy, false otherwise
     */
    @Override
    public boolean has(Capitalist capitalist) {
    	return capitalists.contains(capitalist);
    }

    /**
     * @return all elements in the hierarchy,
     * or an empty set if no elements have been added to the hierarchy
     */
    @Override
    public Set<Capitalist> getElements() {
    	HashSet<Capitalist> cs = new HashSet<Capitalist>();
    	for (Capitalist c : capitalists) cs.add(c);
        return cs;
    }

    /**
     * @return all parent elements in the hierarchy,
     * or an empty set if no parents have been added to the hierarchy
     */
    @Override
    public Set<FatCat> getParents() {
    	HashSet<FatCat> parents = new HashSet<FatCat>();
    	if (!hierarchy.isEmpty()) for (FatCat f : hierarchy.keySet()) parents.add(f);
        return parents;
    }

    /**
     * @param fatCat the parent whose children need to be returned
     * @return all elements in the hierarchy that have the given parent as a direct parent,
     * or an empty set if the parent is not present in the hierarchy or if there are no children
     * for the given parent
     */
    @Override
    public Set<Capitalist> getChildren(FatCat fatCat) {
    	HashSet<Capitalist> children = new HashSet<Capitalist>();
    	if (hierarchy.containsKey(fatCat)) for (Capitalist c : hierarchy.get(fatCat)) children.add(c);
        return children;
    }
    
/*    public Set<Capitalist> getChildren(FatCat fatCat) {

        return hierarchy.get(fatCat);
    }
*/
    /**
     * @return a map in which the keys represent the parent elements in the hierarchy,
     * and the each value is a set of the direct children of the associate parent, or an
     * empty map if the hierarchy is empty.
     */
    @Override
    public Map<FatCat, Set<Capitalist>> getHierarchy() {
        HashMap<FatCat, Set<Capitalist>> h = new HashMap<FatCat, Set<Capitalist>>();
        for (FatCat f : hierarchy.keySet()) h.put(f, getChildren(f));
    	return h;
    }

    /**
     * @param capitalist
     * @return the parent chain of the given element, starting with its direct parent,
     * then its parent's parent, etc, or an empty list if the given element has no parent
     * or if its parent is not in the hierarchy
     */
    @Override
    public List<FatCat> getParentChain(Capitalist capitalist) {
        throw new NotImplementedException();
    }
}
