package model;

import java.util.List;

/*�����Ϊ��λ��һ���⣬һ������ܰ�������С�ʣ�item��
 * 
 * @author Administrator
 *
 */
public class Bulk {
public int pid;//���
public List<Item> items;//һ��С��
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



