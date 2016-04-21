package com.example.asus.mytreeview.utils;

import com.example.asus.mytreeview.R;
import com.example.asus.mytreeview.utils.annotation.TreeNodeId;
import com.example.asus.mytreeview.utils.annotation.TreeNodeLabel;
import com.example.asus.mytreeview.utils.annotation.TreeNodePid;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2015/9/18.
 */
public class TreeHelper {
    /**
     * 将用户的数据转化为树形数据
     *
     * @param datas
     * @param <T>
     * @return
     */
    public static <T> List<Node> convertDatas2Nodes(List<T> datas) throws IllegalAccessException {

        List<Node> nodes = new ArrayList<Node>();
        Node node = null;
        for (T t : datas) {
            int id = -1;
            int pid = -1;
            String label = null;

            node = new Node();
            Class clazz = t.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.getAnnotation(TreeNodeId.class) != null) {
                    field.setAccessible(true);
                    id = field.getInt(t);
                }
                if (field.getAnnotation(TreeNodePid.class) != null) {
                    field.setAccessible(true);
                    pid = field.getInt(t);
                }
                if (field.getAnnotation(TreeNodeLabel.class) != null) {
                    field.setAccessible(true);
                    label = (String) field.get(t);
                }
            }

            node = new Node(id, pid, label);
            nodes.add(node);
        }

        /**
         * 设置node间的节点关系
         */
        for (int i = 0; i < nodes.size(); i++) {
            Node n = nodes.get(i);

            for (int j = i + 1; j < nodes.size(); j++) {
                Node m = nodes.get(j);
                if (m.getpId() == n.getId()) {

                    n.getChildren().add(m);
                    m.setParent(n);
                } else if (m.getId() == n.getpId()) {
                    m.getChildren().add(n);
                    n.setParent(m);
                }
            }
        }

        for (Node n : nodes) {
            setNodeIcon(n);
        }

        return nodes;
    }

    public static <T> List<Node> getSortedNodes(List<T> datas, int defaultExpandLevel) throws IllegalAccessException {

        List<Node> result = new ArrayList<Node>();
        List<Node> nodes = convertDatas2Nodes(datas);
//        获取树的根节点
        List<Node> rootNode = getRootNodes(nodes);

        for (Node node : rootNode) {
            addNode(result, node, defaultExpandLevel, 1);
//                                               参数1代表系统默认展开第一级
        }

        return result;
    }

    /**
     * 将一个节点的所有的孩子节点都放入result
     *
     * @param result
     * @param node
     * @param defaultExpandLevel
     * @param currentLevel
     */
    private static void addNode(List<Node> result, Node node, int defaultExpandLevel, int currentLevel) {

        result.add(node);
        if (defaultExpandLevel >= currentLevel) {
            node.setIsExpand(true);
        }
        if (node.isLeaf())
            return;

        for (int i = 0; i < node.getChildren().size(); i++) {
            addNode(result, node.getChildren().get(i), defaultExpandLevel, currentLevel + 1);

        }

    }

    /**
     * 过滤出可见的节点
     * @param nodes
     * @return
     */
    public static List<Node> filterVisibleNodes(List<Node> nodes) {

        List<Node> results = new ArrayList<Node>();

        for (Node node : nodes) {
            if (node.isRoot() || node.isParentExpand()) {

                setNodeIcon(node);
                results.add(node);
            }

        }
        return results;
    }


    /**
     * 从所有节点中过滤出跟节点
     *
     * @param nodes
     * @return
     */
    private static List<Node> getRootNodes(List<Node> nodes) {
        List<Node> root = new ArrayList<Node>();

        for (Node node : nodes) {
            if (node.isRoot()) {
                root.add(node);
            }
        }
        return root;
    }

    private static void setNodeIcon(Node n) {
        if (n.getChildren().size() > 0 && n.isExpand()) {
//            选择箭头向下的图标，表示当前树是展开的
            n.setIcon(R.mipmap.ic_launcher);

        } else if (n.getChildren().size() > 0 && !n.isExpand()) {
//            选择箭头向右的图标，表示当前树不是展开的
            n.setIcon(R.mipmap.ic_launcher);
        } else {
            n.setIcon(-1);
        }
    }

}
