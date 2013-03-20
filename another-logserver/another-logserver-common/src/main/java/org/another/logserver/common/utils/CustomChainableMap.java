/*
 * Copyright (C) 2013 Jean Paul Manjarres Correal
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.another.logserver.common.utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author <a href="mailto:paul.manjarres@gmail.com">Jean Paul Manjarres
 *         Correal. </a> 19/03/2013
 *
 */
public class CustomChainableMap<K, V> implements Map<K, V> {

	/** Backing map implementation */
	private Map<K, V> backingMap;

	/** Default constructor */
	public CustomChainableMap() {
		this.backingMap = new HashMap<>();
	}

	/**
	 * Creates a chaining map based on a map implementation.
	 *
	 * @param mapImpl
	 */
	public CustomChainableMap(Map<K, V> mapImpl) {
		this.backingMap = mapImpl;
	}

	/**
	 * Version of the put method that allows chaining.
	 *
	 * @param key
	 * @param value
	 * @return
	 */
	public CustomChainableMap<K, V> cPut(K key, V value) {
		this.getBackingMap().put(key, value);
		return this;
	}

	/**
	 * Version of the putAll method that allows chaining.
	 *
	 * @param m
	 * @return
	 */
	public CustomChainableMap<K, V> cPutAll(Map<? extends K, ? extends V> m) {
		this.getBackingMap().putAll(m);
		return this;
	}

	/**
	 * Version of the remove method that allows chaining.
	 *
	 * @param key
	 * @return
	 */
	public CustomChainableMap<K, V> cRemove(Object key) {
		this.getBackingMap().remove(key);
		return this;
	}

	/**
	 * Returns the current backing map.
	 *
	 * @return
	 */
	public Map<K, V> getBackingMap() {
		return backingMap;
	}

	// //////////////////////////////////////////////////////
	// REGULAR MAP METHODS

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Map#clear()
	 */
	@Override
	public void clear() {
		this.getBackingMap().clear();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Map#containsKey(java.lang.Object)
	 */
	@Override
	public boolean containsKey(Object key) {
		return this.getBackingMap().containsKey(key);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Map#containsValue(java.lang.Object)
	 */
	@Override
	public boolean containsValue(Object value) {
		return this.getBackingMap().containsValue(value);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Map#entrySet()
	 */
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		return this.getBackingMap().entrySet();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Map#get(java.lang.Object)
	 */
	@Override
	public V get(Object key) {
		return this.getBackingMap().get(key);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Map#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return this.getBackingMap().isEmpty();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Map#keySet()
	 */
	@Override
	public Set<K> keySet() {
		return this.getBackingMap().keySet();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Map#put(java.lang.Object, java.lang.Object)
	 */
	@Override
	public V put(K key, V value) {
		return this.getBackingMap().put(key, value);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Map#putAll(java.util.Map)
	 */
	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		this.getBackingMap().putAll(m);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Map#remove(java.lang.Object)
	 */
	@Override
	public V remove(Object key) {
		return this.getBackingMap().remove(key);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Map#size()
	 */
	@Override
	public int size() {
		return this.getBackingMap().size();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Map#values()
	 */
	@Override
	public Collection<V> values() {
		return this.getBackingMap().values();
	}

}
