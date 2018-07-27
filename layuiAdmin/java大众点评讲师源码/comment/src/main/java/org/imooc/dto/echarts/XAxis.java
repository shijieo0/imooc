package org.imooc.dto.echarts;

import java.util.ArrayList;
import java.util.List;

public class XAxis {
	private List<String> data;
	
	public XAxis() {
		this.data = new ArrayList<>();
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}
}