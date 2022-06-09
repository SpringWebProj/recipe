package com.linkface.util;

import java.util.HashMap;
import java.util.Map;

public class Node {

	// 자식 노드
	public Map<Character, Node> childNode = new HashMap<>();
	// 마지막 노드값
	public boolean isLastNode = false;
	
}
