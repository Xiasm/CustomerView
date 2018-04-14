package com.xsm.widgets.timepicker.adapter;


import com.xsm.widgets.timepicker.model.TimeCarrier;
import com.xsm.widgets.timepicker.wheelview.adapter.WheelAdapter;

import java.util.List;

/**
 * The simple Array wheel adapter
 * @param <T> the element type
 */
public class ArrayWheelAdapter<T> implements WheelAdapter {
	

	// items
	private List<T> items;

	/**
	 * Constructor
	 * @param items the items
	 */
	public ArrayWheelAdapter(List<T> items) {
		this.items = items;

	}
	
	@Override
	public Object getItem(int index) {
		if (index >= 0 && index < items.size()) {
			T t = items.get(index);
			if (t instanceof TimeCarrier) {
				return ((TimeCarrier) t).getShowStr();
			} else {
				return t.toString();
			}
		}
		return "";
	}

	@Override
	public int getItemsCount() {
		return items.size();
	}

	@Override
	public int indexOf(Object o){
		int index = -1;
		for (int i = 0; i < items.size(); i++) {
			String showStr = ((TimeCarrier) items.get(i)).getShowStr();
			if (showStr.equals(o.toString())) {
				index = i;
				break;
			}
		}
		return index;
	}

}
