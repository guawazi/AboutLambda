package cn.duozhuan.aboutlambda.bean;

/**
 * Created by Administrator on 2017/6/23 0023.
 */

public class UserBean {
    public UserBean(int group, String name) {
        this.group = group;
        this.name = name;
    }

    public UserBean() {
    }
    private int group;
    private String name;

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
