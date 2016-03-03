package model;

import java.util.List;

/*以题号为单位的一道题，一道题可能包括几个小问（item）
 * 
 * @author Administrator
 *
 */
public class Bulk {
public int pid;//题号
public List<Item> items;//一道小题
public int getPid() {
	return pid;
}
public void setPid(int pid) {
	this.pid = pid;
}
public List<Item> getItems() {
	return items;
}
public void setItems(List<Item> items) {
	this.items = items;
}

}



